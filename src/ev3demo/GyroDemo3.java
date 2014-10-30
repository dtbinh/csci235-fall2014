package ev3demo;

import edu.hendrix.lmsl.Logger;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;

public class GyroDemo3 {
	public static final int x0 = LCD.SCREEN_WIDTH / 2;
	public static final int y0 = LCD.SCREEN_HEIGHT / 2;
	
	public static void main(String[] args) {
		Motor.A.setSpeed(100);
		Motor.D.setSpeed(100);
		EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S4);
		float[] value = new float[1];
		LocationTrackerGyro tracker = new LocationTrackerGyro();
		while (!Button.ESCAPE.isDown()) {
			
			gyro.getAngleMode().fetchSample(value, 0);
			
			tracker.updatedCount(Motor.A.getTachoCount(), Motor.D.getTachoCount(), value[0]);
			
			Logger.instance().log(tracker.toString());
			LCD.drawString(String.format("%6.2f     ", tracker.getX()), 0, 0);
			LCD.drawString(String.format("%6.2f     ", tracker.getY()), 0, 1);
			LCD.drawString(String.format("%6.2f     ", tracker.getTheta()), 0, 2);
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
