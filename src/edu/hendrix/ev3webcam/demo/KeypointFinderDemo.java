package edu.hendrix.ev3webcam.demo;

import edu.hendrix.ev3webcam.BooleanImage;
import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.img.IntImage;
import edu.hendrix.img.KeypointFinder;
import edu.hendrix.img.SobelGradient;

public class KeypointFinderDemo extends AbstractCameraDemo {
	private KeypointFinder kf = new KeypointFinder(8);
	private SobelGradient edger = new SobelGradient();

	@Override
	public void show(YUYVImage grabbed) {
		BooleanImage img = kf.process(edger.process(new IntImage(grabbed)));
		img.displayLCD();
	}

	@Override
	public void finish() {}
	
	public static void main(String[] args) {
		new KeypointFinderDemo().run();
	}
}
