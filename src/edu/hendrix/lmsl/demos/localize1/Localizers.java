package edu.hendrix.lmsl.demos.localize1;

import lejos.hardware.lcd.LCD;
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
		LCD.drawString("G:" + gyro.getX() + "   ", 0, 0);
		LCD.drawString("G:" + gyro.getY() + "   ", 0, 1);
		LCD.drawString("G:" + gyro.getTheta() + "   ", 0, 2);
		wheel.update();
		LCD.drawString("W:" + wheel.getX() + "   ", 0, 3);		
		LCD.drawString("W:" + wheel.getY() + "   ", 0, 4);		
		LCD.drawString("W:" + wheel.getTheta() + "   ", 0, 5);		
	}

	@Override
	public void close() {gyroSensor.close();}
}
