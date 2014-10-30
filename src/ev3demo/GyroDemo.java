package ev3demo;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;

public class GyroDemo {
	public static void main(String[] args) {
		Motor.A.setSpeed(100);
		Motor.D.setSpeed(100);
		EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S4);
		float[] value = new float[1];
		double lastAngle = 0;
		DotLine line = null;
		while (!Button.ESCAPE.isDown()) {
			gyro.getAngleMode().fetchSample(value, 0);
			double angle = value[0] * Math.PI / 180;
			if (angle != lastAngle || line == null) {
				if (line != null) {line.erase();}
				line = new DotLine(LCD.SCREEN_WIDTH / 2, LCD.SCREEN_HEIGHT / 2, angle, LCD.SCREEN_HEIGHT / 2);
				line.render();
				lastAngle = angle;
			}
			if (Button.LEFT.isDown()) {
				Motor.A.backward();
				Motor.D.forward();
			} else if (Button.RIGHT.isDown()) {
				Motor.A.forward();
				Motor.D.backward();
			} /*else if (Button.UP.isDown()) {
				Motor.A.forward();
				Motor.D.forward();
			} else if (Button.DOWN.isDown()) {
				Motor.A.backward();
				Motor.D.backward();
			}*/ else if (Button.ENTER.isDown()) {
				Motor.A.stop();
				Motor.D.stop();
			}
		}
		gyro.close();
	}
}
