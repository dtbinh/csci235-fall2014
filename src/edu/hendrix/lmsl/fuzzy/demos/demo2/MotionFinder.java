package edu.hendrix.lmsl.fuzzy.demos.demo2;

import java.io.IOException;

import lejos.hardware.lcd.LCD;
import edu.hendrix.ev3webcam.BooleanImage;
import edu.hendrix.ev3webcam.Webcam;
import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.img.IntImage;

public class MotionFinder {
	private IntImage prev;
	private boolean hadMotion;
	private int xCentroid;
		
	public MotionFinder() throws IOException {
		prev = null;
		hadMotion = false;
		xCentroid = 0;
		Webcam.start(176, 144);
	}
	
	public void grab() {
		YUYVImage grabbed;
		try {
			grabbed = YUYVImage.grab();
			if (prev == null) {
				prev = new IntImage(grabbed);
			} else {
				IntImage current = new IntImage(grabbed);
				IntImage copy = new IntImage(current);
				current.absSubtract(prev);
				BooleanImage threshed = current.threshold(100);
				hadMotion = threshed.numValuesSet() > 0;
				if (hadMotion) {
					xCentroid = threshed.xCentroid();
					threshed.flipColumn(xCentroid);
				}
				threshed.displayLCD();
				prev = copy;
			}
		} catch (IOException e) {
			hadMotion = false;
		}
	}
	
	public boolean hasMotion() {
		return hadMotion;
	}
	
	public int getMotionX() {
		return xCentroid + getMinX();
	}
	
	public int getMinX() {
		return -Webcam.getWidth()/2;
	}
	
	public int getMaxX() {
		return -getMinX();
	}

	public void close() {
		try {
			double fps = Webcam.end();
			LCD.clear();
			System.out.println(fps + " fps");	
		} catch (IOException ioe) {
			System.out.println("Something bad happened when closing the webcam.");
		}
	}
}
