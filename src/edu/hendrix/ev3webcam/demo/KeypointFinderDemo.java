package edu.hendrix.ev3webcam.demo;

import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.img.IntImage;
import edu.hendrix.img.KeypointFinder;
import edu.hendrix.img.SobelGradient;

public class KeypointFinderDemo extends AbstractCameraDemo {
	private SobelGradient edger = new SobelGradient();

	@Override
	public void show(YUYVImage grabbed) {
		KeypointFinder kf = new KeypointFinder(8, 8, edger.process(new IntImage(grabbed)));
		kf.getKeypointImage().displayLCD(0, 0);
	}

	@Override
	public void finish() {}
	
	public static void main(String[] args) {
		new KeypointFinderDemo().run();
	}
}
