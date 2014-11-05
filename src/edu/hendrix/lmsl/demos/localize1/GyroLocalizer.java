package edu.hendrix.lmsl.demos.localize1;

import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;

public class GyroLocalizer extends AbstractLocalizer {
	private EV3GyroSensor gyro;
	private float[] value;
	private Port gyroPort;

	public GyroLocalizer(NXTRegulatedMotor left, NXTRegulatedMotor right, Port port) {
		super(left, right);
		this.gyro = new EV3GyroSensor(port);
		gyroPort = port;
		value = new float[gyro.getAngleMode().sampleSize()];
	}

	@Override
	protected double getUpdatedTheta() {
		gyro.getAngleMode().fetchSample(value, 0);
		return Math.toRadians(value[0]);
	}
	
	public void totalReset() {
		gyro.close();
		gyro = new EV3GyroSensor(gyroPort);
		super.reset(new Location(0, 0, 0));
	}
	
	public void close() {
		gyro.close();
	}
}
