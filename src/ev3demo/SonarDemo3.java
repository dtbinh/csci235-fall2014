package ev3demo;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class SonarDemo3 {
	public static void main(String[] args) {
		EV3UltrasonicSensor sonar = new EV3UltrasonicSensor(SensorPort.S3);
		float[] value = new float[1];
		boolean goingLeft = true;
		while (!Button.ESCAPE.isDown()) {
			if (Button.LEFT.isDown()) {
				goingLeft = true;
			} else if (Button.RIGHT.isDown()) {
				goingLeft = false;
			}
			
			if (goingLeft) {
				avoidLeft(sonar, value);
			} else {
				avoidRight(sonar, value);
			}
		}
		sonar.close();
		Motor.A.stop();
		Motor.B.stop();		
	}
	
	public static void avoidLeft(EV3UltrasonicSensor sonar, float[] value) {
		sonar.getDistanceMode().fetchSample(value, 0);
		if (value[0] < 0.3) {
			Motor.A.backward();
			Motor.B.forward();
		} else {
			Motor.A.forward();
			Motor.B.forward();
		}
	}
	
	public static void avoidRight(EV3UltrasonicSensor sonar, float[] value) {
		sonar.getDistanceMode().fetchSample(value, 0);
		if (value[0] < 0.3) {
			Motor.A.forward();
			Motor.B.backward();
		} else {
			Motor.A.forward();
			Motor.B.forward();
		}
	}
}
