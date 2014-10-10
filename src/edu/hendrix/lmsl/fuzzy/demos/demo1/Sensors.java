package edu.hendrix.lmsl.fuzzy.demos.demo1;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import edu.hendrix.lmsl.fuzzy.FuzzyFlagSet;
import edu.hendrix.lmsl.fuzzy.FuzzyFlagSetter;
import edu.hendrix.lmsl.fuzzy.FuzzyFlagSetters;

public class Sensors extends FuzzyFlagSetters<FlagName> {
	private EV3UltrasonicSensor sonar = new EV3UltrasonicSensor(SensorPort.S3);
	private float[] values;

	public Sensors() {
		super(FlagName.class);
		values = new float[sonar.getDistanceMode().sampleSize()];
		
		this.addFuzzySetter(new FuzzyFlagSetter<FlagName>() {

			@Override
			public void pollSensor(FuzzyFlagSet<FlagName> flags) {
				sonar.getDistanceMode().fetchSample(values, 0);
				flags.set(FlagName.CLEAR, encodeRising(values[0], 0.15, 0.6));
			}

			@Override
			public void close() {
				sonar.close();
			}});
	}

	@Override
	public FuzzyFlagSet<FlagName> makeFlagSet() {
		FuzzyFlagSet<FlagName> result = new FuzzyFlagSet<FlagName>(FlagName.class);
		result.addInverseFor(FlagName.CLEAR, FlagName.BLOCKED);
		return result;
	}	
}
