package ev3demo;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;

public class Program1 {
	public static void main(String[] args) {
		Motor.A.forward();
		Motor.B.forward();
		while (!Button.ESCAPE.isDown()) {}
		Motor.A.stop();
		Motor.B.stop();
	}
}
