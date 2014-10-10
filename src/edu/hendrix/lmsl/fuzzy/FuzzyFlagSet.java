package edu.hendrix.lmsl.fuzzy;

import java.util.EnumMap;

import edu.hendrix.lmsl.FlagSet;

public class FuzzyFlagSet<F extends Enum<F>> extends FlagSet<F> {
	private EnumMap<F,Double> flags;
	private EnumMap<F,F> indep2dep;
	
	public FuzzyFlagSet(Class<F> flagType) {
		super(flagType);
		flags = new EnumMap<F,Double>(flagType);
		indep2dep = new EnumMap<F,F>(flagType);
	}
	
	public FuzzyFlagSet(FuzzyFlagSet<F> that) {
		super(that);
		this.flags = new EnumMap<F,Double>(that.flags);
	}
	
	public void addInverseFor(F independent, F dependent) {
		indep2dep.put(independent, dependent);
	}
	
	@Override
	public void add(F flag) {
		set(flag, 1.0);
	}
	
	public void set(F flag, double fuzzyValue) {
		super.add(flag);
		flags.put(flag, fuzzyValue);
		if (indep2dep.containsKey(flag)) {
			flags.put(indep2dep.get(flag), 1.0 - fuzzyValue);
		}
	}
	
	@Override
	public double getFuzzy(F flag) {
		return flags.containsKey(flag) ? flags.get(flag) : 0.0;
	}
	
	@Override
	public String toString() {
		return flags.toString();
	}
	
	@Override
	public int hashCode() {
		return flags.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof FuzzyFlagSet && super.equals(other)) {
			@SuppressWarnings("unchecked")
			FuzzyFlagSet<F> that = (FuzzyFlagSet<F>)other;
			return this.flags.equals(that.flags);
		} else {
			return false;
		}
	}
}
