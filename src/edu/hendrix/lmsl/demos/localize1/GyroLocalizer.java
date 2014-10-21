package edu.hendrix.lmsl.demos.localize1;

import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.sensor.EV3GyroSensor;

public class GyroLocalizer extends AbstractLocalizer {
	private EV3GyroSensor gyro;
	private float[] value;

	public GyroLocalizer(NXTRegulatedMotor left, NXTRegulatedMotor right, EV3GyroSensor gyro) {
		super(left, right);
		this.gyro = gyro;
		value = new float[gyro.getAngleMode().sampleSize()];
	}

	@Override
	protected double getUpdatedTheta() {
		gyro.getAngleMode().fetchSample(value, 0);
		return Math.toRadians(value[0]);
	}

}
