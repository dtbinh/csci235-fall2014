package edu.hendrix.lmsl;

import java.util.ArrayList;

public class SequenceSpecification<F extends Enum<F>, M extends Enum<M> & ModeNameInterface> {
	private ArrayList<FlagSet<F>> flagSets;
	private ArrayList<ModeStack<M>> modeStacks;
	private Class<M> modeType;
	private final static String splitter = "!@#";
	
	public SequenceSpecification(Class<M> modeType) {
		flagSets = new ArrayList<FlagSet<F>>();
		modeStacks = new ArrayList<ModeStack<M>>();
		this.modeType = modeType;
	}
	
	public void addStep(FlagSet<F> flagSet, ModeStack<M> modes) {
		flagSets.add(flagSet);
		modeStacks.add(modes);
	}
	
	public int size() {return flagSets.size();}
	
	public void removeLastStep() {
		flagSets.remove(flagSets.size() - 1);
		modeStacks.remove(modeStacks.size() - 1);
	}
	
	public boolean matchesSpecification(Layer<F,M> target) {
		return toSequence(target).matchesModes(modeStacks);
	}
	
	public Sequence<F,M> toSequence(Layer<F,M> target) {
		return new Sequence<F,M>(target, modeType, flagSets);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof SequenceSpecification<?,?>) {
			@SuppressWarnings("unchecked")
			SequenceSpecification<F,M> that = (SequenceSpecification<F,M>)other;
			return this.flagSets.equals(that.flagSets) && this.modeStacks.equals(that.modeStacks);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {return toString().hashCode();}
	
	@Override
	public String toString() {
		return flagSets.toString() + "\n" + splitter + "\n" + modeStacks.toString();
	}
}
