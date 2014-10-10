package edu.hendrix.lmsl.fuzzy;

public interface FuzzyFlagSetter<F extends Enum<F>> {
	public void pollSensor(FuzzyFlagSet<F> flags);
	public void close();
}
