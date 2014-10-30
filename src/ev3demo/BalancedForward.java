package ev3demo;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;

public class BalancedForward {	
	
	public static final int x0 = LCD.SCREEN_WIDTH / 2;
	public static final int y0 = LCD.SCREEN_HEIGHT / 2;
	public static final int tolerance = 3;

	public static void main(String[] args) {
		Motor.A.setSpeed(300);
		Motor.D.setSpeed(300);
		EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S4);
		float[] value = new float[1];
		double lastAngle = 0;
		DotLine gyroLine = null;
		while (!Button.ESCAPE.isDown()) {
			gyro.getAngleMode().fetchSample(value, 0);
			if (value[0] < -tolerance) {
				Motor.A.backward();
			} else if (value[0] > tolerance) {
				Motor.D.backward();
			} else {
				Motor.A.forward();
				Motor.D.forward();
			}
			
			double angle = Math.toRadians(value[0]);
			if (angle != lastAngle || gyroLine == null) {
				if (gyroLine != null) {gyroLine.erase();}
				gyroLine = new DotLine(x0, y0, angle, y0);
				gyroLine.render();
				lastAngle = angle;
			}
		}
		Motor.A.stop(true);
		Motor.D.stop();
		gyro.close();
	}
}
