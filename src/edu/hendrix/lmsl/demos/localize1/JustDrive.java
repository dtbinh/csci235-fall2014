package edu.hendrix.lmsl.demos.localize1;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;

public class JustDrive {
	public static void main(String[] args) {
		Motor.A.forward();
		Motor.D.forward();
		while (!Button.ENTER.isDown()) {}
		LCD.drawInt(Motor.A.getTachoCount(), 0, 3);
		Motor.A.stop(true);
		Motor.D.stop();
		while (!Button.ESCAPE.isDown()) {}
	}
}
