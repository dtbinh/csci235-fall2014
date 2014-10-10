package edu.hendrix.lmsl.unsupervised.controllers;

import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.lcd.LCD;
import edu.hendrix.ev3webcam.GrabTossThread;
import edu.hendrix.ev3webcam.Webcam;
import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.img.IntImage;

abstract public class AbstractGNGController<M, G extends AbstractGNGNodeMoves<M,Flag>> {

	private G gng;
	private boolean gngChanged;
	private KeyThread keypad;

	public AbstractGNGController() {
		gng = makeGNG();
		gngChanged = false;
		keypad = new KeyThread();
		keypad.start();
	}
	
	abstract protected G makeGNG();
	
	public boolean hasGNG() {return gng != null;}
	
	public void stopRobot() {
		keypad.stopRobot();
	}

	public Key getCurrentKey() {
		return keypad.getCurrentKey();
	}

	public void performCurrentAction() {
		keypad.performCurrentAction();
	}
	
	public void close() throws IOException {
		keypad.halt();

		double fps = Webcam.end();
		LCD.clear();
		System.out.println(String.format("%4.2f", fps) + " fps");	
		if (gngChanged) {saveGNG();}
		while (!Button.ESCAPE.isDown());
	}
	
	public void updateGNG(Flag currentFlag, IntImage img) {
		gng.update(currentFlag, img);
		gngChanged = true;
	}
	
	public int getNodeFor(IntImage img) {
		return gng.getNodeFor(img);
	}
	
	public M getChoiceFor(int node) {
		return gng.getMoveFor(node);
	}
	
	public IntImage getImageFor(int node) {
		return gng.get(node);
	}
	
	public void saveGNG() throws IOException {
		System.out.println(gng.numNodes() + " nodes");
		toStorage(gng);
	}
	
	abstract protected void toStorage(G gng) throws IOException;
	
	public static IntImage grabGNGImage(boolean displayImage) throws IOException {
		YUYVImage grabbed = YUYVImage.grab();
		if (displayImage) {grabbed.displayLCD();}
		return IntImage.toShrunkenGrayInts(grabbed, 4);
	}
	
	public static IntImage grabGNGImage(GrabTossThread grabber) {
		while (!grabber.hasImage());
		return IntImage.toShrunkenGrayInts(grabber.getLastGrabbed(), 4);
	}
}