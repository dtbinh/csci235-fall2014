package edu.hendrix.lmsl.fuzzy;

import java.util.ArrayList;

import edu.hendrix.lmsl.FlagSetters;

abstract public class FuzzyFlagSetters<F extends Enum<F>> extends FlagSetters<F> {
	private ArrayList<FuzzyFlagSetter<F>> fuzzySetters;
	
	public static double encodeRising(double value, double start, double end) {
		return value > end ? 1.0 : value < start ? 0.0 : (value - start) / (end - start);
	}
	
	public static double encodeFalling(double value, double start, double end) {
		return value > end ? 0.0 : value < start ? 1.0 : (end - value) / (end - start);
	}
	
	public static double encodeTriangle(double value, double start, double peak, double end) {
		return value > end ? 0.0 : value < start ? 0.0 : value > peak ? (end - value) / (end - peak) : (value - start) / (peak - start);
	}
	
	public static double encodeTrapezoid(double value, double start, double peakStart, double peakEnd, double end) {
		return value > end ? 0.0 : value < start ? 0.0 : value > peakStart && value < peakEnd ? 1.0 : value >= peakEnd ? (end - value) / (end - peakEnd) : (value - start) / (peakStart - start);
	}

	public FuzzyFlagSetters(Class<F> flagType) {
		super(flagType);
		fuzzySetters = new ArrayList<FuzzyFlagSetter<F>>();
	}
	
	public void addFuzzySetter(FuzzyFlagSetter<F> setter) {
		fuzzySetters.add(setter);
	}
	
	abstract public FuzzyFlagSet<F> makeFlagSet();
	
	@Override
	public FuzzyFlagSet<F> getFlags() {
		FuzzyFlagSet<F> flags = makeFlagSet();
		super.pollSensors(flags);
		for (FuzzyFlagSetter<F> setter: fuzzySetters) {
			setter.pollSensor(flags);
		}
		return flags;
	}
	
	@Override
	public void closeAllSensors() {
		super.closeAllSensors();
		for (FuzzyFlagSetter<F> flagger: fuzzySetters) {
			flagger.close();
		}
	}
}
