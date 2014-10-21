package edu.hendrix.lmsl.demos.localize1;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import edu.hendrix.lmsl.FlagSet;
import edu.hendrix.lmsl.FlagSetters;

public class Sensors extends FlagSetters<FlagName> {

	public Sensors() {
		super(FlagName.class);
		addSetter(new ButtonFlagger());
		addSetter(new Localizers(Motor.A, Motor.D, new EV3GyroSensor(SensorPort.S4)));
	}

	@Override
	public FlagSet<FlagName> makeFlagSet() {
		return new FlagSet<FlagName>(FlagName.class);
	}

}
