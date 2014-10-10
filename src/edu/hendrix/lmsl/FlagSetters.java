package edu.hendrix.lmsl;

import java.util.ArrayList;

abstract public class FlagSetters<F extends Enum<F>> {
	private ArrayList<FlagSetter<F>> setters;
	
	public FlagSetters(Class<F> flagType) {
		setters = new ArrayList<FlagSetter<F>>();
	}
	
	public void addSetter(FlagSetter<F> setter) {
		setters.add(setter);
	}
	
	abstract public FlagSet<F> makeFlagSet();
	
	public FlagSet<F> getFlags() {
		FlagSet<F> result = makeFlagSet();
		pollSensors(result);
		return result;
	}
	
	protected void pollSensors(FlagSet<F> flags) {
		for (FlagSetter<F> sensor: setters) {
			sensor.pollSensor(flags);
		}
	}
	
	public void closeAllSensors() {
		for (FlagSetter<F> flagger: setters) {
			flagger.close();
		}
	}
}
