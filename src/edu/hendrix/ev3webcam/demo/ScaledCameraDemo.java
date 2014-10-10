package edu.hendrix.ev3webcam.demo;

import edu.hendrix.ev3webcam.YUYVImage;

public class ScaledCameraDemo extends AbstractCameraDemo {
	public static void main(String[] args) {
		new ScaledCameraDemo().run();
	}

	@Override
	public void show(YUYVImage grabbed) {
		grabbed.displayScaled();
	}

	@Override
	public void finish() {}
}
