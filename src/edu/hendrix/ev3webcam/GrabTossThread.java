package edu.hendrix.ev3webcam;

public class GrabTossThread extends GrabThread {
	private YUYVImage lastGrabbed;
	
	public GrabTossThread() {
		super(false);
	}
	
	public void process(YUYVImage grabbed) {
		lastGrabbed = grabbed;
		grabbed.displayLCD();
	}
	
	public boolean hasImage() {
		return lastGrabbed != null;
	}
	
	public YUYVImage getLastGrabbed() {
		return lastGrabbed;
	}
}
