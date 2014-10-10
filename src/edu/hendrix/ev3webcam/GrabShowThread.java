package edu.hendrix.ev3webcam;

public class GrabShowThread extends GrabThread {
	public void process(YUYVImage grabbed) {
		grabbed.displayLCD();
	}
}
