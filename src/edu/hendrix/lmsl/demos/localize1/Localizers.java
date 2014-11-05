package edu.hendrix.lmsl.demos.localize1;

import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.Port;
import edu.hendrix.lmsl.FlagSet;
import edu.hendrix.lmsl.FlagSetter;

public class Localizers implements FlagSetter<FlagName> {
	private GyroLocalizer gyro;
	private WheelLocalizer wheel;
	
	public Localizers(NXTRegulatedMotor left, NXTRegulatedMotor right, Port gyroPort) {
		this.gyro = new GyroLocalizer(left, right, gyroPort);
		this.wheel = new WheelLocalizer(left, right);
	}

	@Override
	public void pollSensor(FlagSet<FlagName> flags) {
		gyro.update();
		gyro.getLocation().display(2);
		wheel.update();
		wheel.getLocation().display(3);		
	}

	@Override
	public void close() {gyro.close();}
}
