package lmsldemo;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import edu.hendrix.lmsl.FlagSet;
import edu.hendrix.lmsl.FlagSetters;

public class Sensors extends FlagSetters<FlagName> {

	public Sensors() {
		super(FlagName.class);
		addSetter(new UltrasonicFlagger(new EV3UltrasonicSensor(SensorPort.S3)));
		addSetter(new ButtonFlagger());
	}

	@Override
	public FlagSet<FlagName> makeFlagSet() {
		return new AvoidFlagSet();
	}

}
