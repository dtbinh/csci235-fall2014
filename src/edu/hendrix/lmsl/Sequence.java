package edu.hendrix.lmsl;

import java.util.ArrayList;
import java.util.EnumSet;

public class Sequence<F extends Enum<F>, M extends Enum<M> & ModeNameInterface> {
	private ArrayList<FlagSet<F>> flags;
	private ArrayList<ModeStack<M>> modes;
	private ModeStack<M> initialModes;
	private Layer<F,M> top;
	private ActionMap<M> actions;
	private Class<M> modeType;
	
	public Sequence(Layer<F,M> start, Class<M> modeType) {
		this.modeType = modeType;
		flags = new ArrayList<FlagSet<F>>();
		modes = new ArrayList<ModeStack<M>>();
		this.top = start;
		actions = new ActionMap<M>(modeType);
		initialModes = top.getCurrentModeStack();
	}
	
	public Sequence(Layer<F,M> start, Class<M> modeType, ArrayList<FlagSet<F>> flagsOnly) {
		this(start, modeType);
		for (FlagSet<F> flag: flagsOnly) {
			add(flag);
		}
	}
	
	public Sequence(Layer<F,M> start, Class<F> flagType, Class<M> modeType, String flagSets) {
		this(start, modeType, FlagSet.getFlagSetsFrom(flagType, flagSets));
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size(); ++i) {
			sb.append(flags.get(i).toString());
			sb.append('\n');
			sb.append(modes.get(i).toString());
			sb.append('\n');
		}
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Sequence<?,?>) {
			@SuppressWarnings("unchecked")
			Sequence<F,M> that = (Sequence<F,M>)other;
			return this.top.equals(that.top) && this.flags.equals(that.flags) && this.modes.equals(that.modes);
		} else {
			return false;
		}
	}
	
	public void add(FlagSet<F> flagsUp) {
		top.inProgress(flagsUp, actions);
		flags.add(flagsUp);
		modes.add(top.getCurrentModeStack());
	}
	
	public void removeLast() {
		if (size() == 0) {throw new IllegalStateException("Nothing to remove");}
		flags.remove(flags.size() - 1);
		modes.remove(modes.size() - 1);
	}
	
	public int size() {return flags.size();}
	
	public Sequence<F,M> getPrefix(int length) {
		if (length > size() || length < 0) {throw new IllegalArgumentException("Invalid length: " + length);}
		Sequence<F,M> result = new Sequence<F,M>(top, modeType);
		for (int i = 0; i < length; ++i) {
			add(new FlagSet<F>(getFlags(i)));
		}
		return result;
	}
	
	public FlagSet<F> getFlags(int n) {
		return flags.get(n);
	}
	
	public ModeStack<M> getModes(int n) {
		return n < 0 ? initialModes : modes.get(n);
	}
	
	public EnumSet<M> getMissingModes(Class<M> modeType) {
		EnumSet<M> missing = EnumSet.allOf(modeType);
		for (ModeStack<M> stack: modes) {
			missing.removeAll(stack.modesInStack(modeType));
		}
		for (M mode: missing) {
			if (mode.continuePrevious()) {missing.remove(mode);}
		}
		return missing;
	}
	
	public boolean allModesCovered(Class<M> modeType) {
		return getMissingModes(modeType).isEmpty();
	}
	
	public boolean matchesModes(ArrayList<ModeStack<M>> targetModes) {
		return modes.equals(targetModes);
	}
}
