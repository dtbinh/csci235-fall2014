package ev3demo;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;

public class ButtonControl {
	public static void main(String[] args) {
		while (!Button.ESCAPE.isDown()) {
			if (Button.UP.isDown()) {
				Motor.A.forward();
				Motor.B.forward();
			} else if (Button.DOWN.isDown()) {
				Motor.A.backward();
				Motor.B.backward();
			} else if (Button.LEFT.isDown()) {
				Motor.A.backward();
				Motor.B.forward();
			} else if (Button.RIGHT.isDown()) {
				Motor.A.forward();
				Motor.B.backward();
			} else {
				Motor.A.stop();
				Motor.B.stop();
			}
		}
		
		Motor.A.stop();
		Motor.B.stop();
	}
}
