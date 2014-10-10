package edu.hendrix.ev3webcam.demo;

import edu.hendrix.ev3webcam.BooleanImage;
import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.img.IntImage;

public class SubtractImageDemo extends AbstractCameraDemo {
	private IntImage prev = null;

	@Override
	public void show(YUYVImage grabbed) {
		if (prev == null) {
			prev = new IntImage(grabbed);
		} else {
			IntImage current = new IntImage(grabbed);
			IntImage copy = new IntImage(current);
			current.absSubtract(prev);
			BooleanImage threshed = current.threshold(20);
			if (threshed.numValuesSet() > 0) {
				threshed.flipColumn(threshed.xCentroid());
			}
			threshed.displayLCD();
			prev = copy;
		}
	}
	
	public static void main(String[] args) {
		new SubtractImageDemo().run();
	}

	@Override
	public void finish() {}
}
