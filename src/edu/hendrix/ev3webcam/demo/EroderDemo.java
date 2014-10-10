package edu.hendrix.ev3webcam.demo;

import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.img.Eroder;
import edu.hendrix.img.IntImage;

public class EroderDemo extends AbstractCameraDemo {
	private Eroder eroder = new Eroder(0.5);

	@Override
	public void show(YUYVImage grabbed) {
		eroder.process(new IntImage(grabbed)).displayLCD();
	}

	public static void main(String[] args) {
		new EroderDemo().run();
	}

	@Override
	public void finish() {}
}
