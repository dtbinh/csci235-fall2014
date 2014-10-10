package edu.hendrix.ev3webcam;

import lejos.hardware.Button;

public class GrabTest {
	public static void main(String[] args) throws java.io.IOException {
		int goal = 100;

		Webcam.start();
		System.out.println(Webcam.getWidth() + "x" + Webcam.getHeight());
		for (int i = 0; i < goal; ++i) {
			Webcam.grabFrame();
			System.out.print(".");
		}
		System.out.println();

		double fps = Webcam.end();
		System.out.println(fps + " frames/s");
		while (!Button.ESCAPE.isDown());
	}
}
