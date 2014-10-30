package ev3demo;

import lejos.hardware.motor.Motor;

public class DriveEncoder {
	public static void main(String[] args) {
		Motor.A.forward();
		Motor.D.forward();
		// Drive about 2 feet
		while (Motor.A.getTachoCount() < 1280) {}
		Motor.A.stop(true);
		Motor.D.stop();
	}
}
