package ev3demo;

import edu.hendrix.lmsl.Logger;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;

public class GyroDemo2 {
	public static final int x0 = LCD.SCREEN_WIDTH / 2;
	public static final int y0 = LCD.SCREEN_HEIGHT / 2;
	
	public static void main(String[] args) {
		Motor.A.setSpeed(100);
		Motor.D.setSpeed(100);
		EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S4);
		float[] value = new float[1];
		gyro.getAngleMode().fetchSample(value, 0);
		LocationTrackerWheel tracker = new LocationTrackerWheel();
		double lastAngle = 0;
		double lastWheel = 0;
		DotLine gyroLine = null;
		DotLine wheelLine = null;
		while (!Button.ESCAPE.isDown()) {
			
			gyro.getAngleMode().fetchSample(value, 0);
			double angle = Math.toRadians(value[0]);
			if (angle != lastAngle || gyroLine == null) {
				if (gyroLine != null) {gyroLine.erase();}
				gyroLine = new DotLine(x0, y0, angle, y0);
				gyroLine.render();
				lastAngle = angle;
			}
			
			tracker.updatedCount(Motor.A.getTachoCount(), Motor.D.getTachoCount());
			double wheel = tracker.getTheta();
			if (wheel != lastWheel || wheelLine == null) {
				if (wheelLine != null) {wheelLine.erase();}
				wheelLine = new DotLine(x0, y0, wheel, y0);
				wheelLine.render();
				lastWheel = wheel;
			}
			
			Logger.instance().log(tracker.toString() + ": " + String.format("%6.2f", angle));
			if (Button.LEFT.isDown()) {
				Motor.A.backward();
				Motor.D.forward();
			} else if (Button.RIGHT.isDown()) {
				Motor.A.forward();
				Motor.D.backward();
			} else if (Button.UP.isDown()) {
				Motor.A.forward();
				Motor.D.forward();
			} else if (Button.DOWN.isDown()) {
				Motor.A.backward();
				Motor.D.backward();
			} else if (Button.ENTER.isDown()) {
				Motor.A.stop();
				Motor.D.stop();
			}
		}
		gyro.close();
	}
}
