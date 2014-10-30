package edu.hendrix.lmsl.demos.localize1;

import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.sensor.EV3GyroSensor;
import edu.hendrix.lmsl.FlagSet;
import edu.hendrix.lmsl.FlagSetter;

public class Localizers implements FlagSetter<FlagName> {
	private GyroLocalizer gyro;
	private WheelLocalizer wheel;
	private EV3GyroSensor gyroSensor;
	
	public Localizers(NXTRegulatedMotor left, NXTRegulatedMotor right, EV3GyroSensor gyro) {
		this.gyro = new GyroLocalizer(left, right, gyro);
		this.wheel = new WheelLocalizer(left, right);
		gyroSensor = gyro;
	}

	@Override
	public void pollSensor(FlagSet<FlagName> flags) {
		gyro.update();
		gyro.getLocation().display(2);
		wheel.update();
		wheel.getLocation().display(3);		
	}

	@Override
	public void close() {gyroSensor.close();}
}
