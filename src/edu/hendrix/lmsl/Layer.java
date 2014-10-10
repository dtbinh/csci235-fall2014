package edu.hendrix.lmsl;

import java.util.EnumMap;
import java.util.EnumSet;

public class Layer<F extends Enum<F>, M extends Enum<M> & ModeNameInterface> extends AbstractLayer<F,M> {
	private M start;
	private M current;
	private EnumMap<M,AbstractLayer<F,M>> name2layer;
	private EnumMap<M,Selector<F,M>> name2selector;
	private Class<M> modeType;
	private Selector<F,M> defaultSelector;
	private EnumHistogram<M> modeCounts;

	public Layer(M start, Class<F> flagType, Class<M> modeType) {
		this(start, new LoopBackSelector<F,M>(flagType, start), modeType);
	}
	
	public Layer(M start, Selector<F,M> defaultSelector, Class<M> modeType) {
		this.start = this.current = start;
		this.modeType = modeType;
		name2layer = new EnumMap<M,AbstractLayer<F,M>>(modeType);
		name2selector = new EnumMap<M,Selector<F,M>>(modeType);
		this.defaultSelector = defaultSelector;
		modeCounts = new EnumHistogram<M>(modeType);
	}
	
	public void ensureSanity() {
		if (defaultSelector == null) {
			if (!name2selector.containsKey(start)) {
				throw new IllegalStateException("No selector for mode " + start);
			}
			
			EnumSet<M> targets = getAllTargetModes();
			targets.removeAll(name2selector.keySet());
			if (!targets.isEmpty()) {
				throw new IllegalStateException("No selector for modes " + targets);
			}
		}
	}
	
	private M getNextMode(FlagSet<F> flagsUp) {
		return (name2selector.containsKey(current) ? name2selector.get(current) : defaultSelector).nextMode(flagsUp);
	}
	
	public EnumSet<M> getAllTargetModes() {
		EnumSet<M> result = EnumSet.noneOf(modeType);
		for (Selector<F, M> selector: name2selector.values()) {
			result.addAll(selector.getTargetModes());
		}
		return result;
	}
	
	public void runController(FlagSetters<F> flaggers, ActionMap<M> actions, boolean watch) {
		Controller<F,M> c = new Controller<F,M>(actions, flaggers, this);
		if (watch) c.watch();
		c.control();
	}
	
	public void addAction(M name, Selector<F,M> selector) {
		name2selector.put(name, selector);
	}
	
	public void addLayer(M name, AbstractLayer<F,M> layer) {
		name2layer.put(name, layer);
	}
	
	public void addLayer(M name, AbstractLayer<F,M> layer, Selector<F,M> selector) {
		name2selector.put(name, selector);
		addLayer(name, layer);
	}
	
	public M getCurrentMode() {return current;}
	
	public ModeStack<M> getCurrentModeStack() {
		ModeStack<M> result = name2layer.containsKey(current)
				? name2layer.get(current).getCurrentModeStack() 
				:  new ModeStack<M>();
		result.addToTop(current);
		return result;
	}
	
	public void begin(ActionMap<M> actions) {
		changeCurrent(start, actions);
	}
	
	public void changeCurrent(M nextAction, ActionMap<M> actions) {
		current = nextAction;
		if (name2layer.containsKey(current)) {
			name2layer.get(current).begin(actions);
		} else {
			actions.begin(current);
		}
	}
	
	public void end(ActionMap<M> actions) {
		if (name2layer.containsKey(current)) {
			name2layer.get(current).end(actions);
		} else {
			actions.end(current);
		}
	}
	
	public void inProgress(FlagSet<F> flagsUp, ActionMap<M> actions) {
		M nextName = getNextMode(flagsUp);
		if (!nextName.continuePrevious() && !nextName.equals(current)) {
			end(actions);
			changeCurrent(nextName, actions);
		}
		
		if (name2layer.containsKey(current))	{
			name2layer.get(current).inProgress(flagsUp, actions);
		}
		modeCounts.bump(current);
	}
	
	public void printStats(int spaces) {
		String lead = "";
		for (int i = 0; i < spaces; ++i) {lead += " ";}
		for (M mode: getAllTargetModes()) {
			Logger.instance().log(lead + mode + ":" + modeCounts.getCountFor(mode));
			if (name2layer.containsKey(mode)) {
				name2layer.get(mode).printStats(spaces + 1);
			}
		}
	}
}
