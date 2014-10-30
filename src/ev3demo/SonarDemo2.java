package ev3demo;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class SonarDemo2 {
	public static void main(String[] args) {
		EV3UltrasonicSensor sonar = new EV3UltrasonicSensor(SensorPort.S3);
		float[] value = new float[1];
		while (!Button.ESCAPE.isDown()) {
			sonar.getDistanceMode().fetchSample(value, 0);
			if (value[0] < 0.3) {
				Motor.A.backward();
				Motor.B.backward();
			} else {
				Motor.A.stop();
				Motor.B.stop();
			}
		}
		sonar.close();
		Motor.A.stop();
		Motor.B.stop();
	}
}
