package lmsldemo;

import edu.hendrix.lmsl.EV3SensorSetter;
import edu.hendrix.lmsl.FlagSet;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class UltrasonicFlagger extends EV3SensorSetter<FlagName> {

	public UltrasonicFlagger(EV3UltrasonicSensor sonar) {
		super(sonar, sonar.getDistanceMode());
	}

	@Override
	public void setFlags(float[] sample, FlagSet<FlagName> flags) {
		if (sample[0] < 0.5) {
			flags.add(FlagName.BLOCKED);
		} else {
			flags.add(FlagName.CLEAR);
		}
	}
}
