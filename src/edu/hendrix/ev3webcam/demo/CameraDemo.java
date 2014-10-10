package edu.hendrix.ev3webcam.demo;

import edu.hendrix.ev3webcam.YUYVImage;


public class CameraDemo extends AbstractCameraDemo {

	@Override
	public void show(YUYVImage grabbed) {
		grabbed.displayLCD();
	}
	
	public static void main(String[] args) {
		new CameraDemo().run();
	}

	@Override
	public void finish() {}
}
