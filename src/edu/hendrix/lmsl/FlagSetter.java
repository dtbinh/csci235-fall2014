package edu.hendrix.lmsl;

public interface FlagSetter<F extends Enum<F>> {
	public void pollSensor(FlagSet<F> flags);
	public void close();
}
