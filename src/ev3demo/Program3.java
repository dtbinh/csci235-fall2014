package ev3demo;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class Program3 {
	public static void main(String[] args) {
		EV3UltrasonicSensor sonar = new EV3UltrasonicSensor(SensorPort.S3);
		float[] value = new float[1];
		while (!Button.ESCAPE.isDown()) {
			sonar.getDistanceMode().fetchSample(value, 0);
			System.out.println(value[0]);
		}
		sonar.close();
	}
}
