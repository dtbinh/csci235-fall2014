package edu.hendrix.lmsl.unsupervised.controllers;

import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import edu.hendrix.ev3webcam.Webcam;
import edu.hendrix.img.IntImage;
import edu.hendrix.lmsl.storage.Chooser;
import edu.hendrix.lmsl.storage.GNGStorage;

public class GNGLiveViewer extends GNGController {
	
	public GNGLiveViewer() {
		super();
	}
	
	@Override
	protected GNGNodeMoves<Flag> makeGNG() {
		GNGStorage<Flag> storage = GNGStorage.getEV3Storage(Flag.class);
		Chooser<GNGNodeMoves<Flag>, GNGStorage<Flag>> chooser = new Chooser<GNGNodeMoves<Flag>, GNGStorage<Flag>>();
		chooser.choose(storage);
		return chooser.getSelected();
	}
	
	public void runController() {
		try {
			Webcam.start(176, 144);
			while (!Button.ESCAPE.isDown()) {
				performCurrentAction();
				IntImage grabbed = GNGController.grabGNGImage(false);
				int node = getNodeFor(grabbed);
				Flag flag = getChoiceFor(node);
				LCD.clear();
				LCD.drawString("Node " + node, 0, 0);
				LCD.drawString(flag == null ? "No move" : flag.toString(), 0, 1);
				LCD.drawString("Live", 0, 5);
				LCD.drawString("Image", 0, 6);
				grabbed.displayLCD(80, 80);
				getImageFor(node).displayLCD(80, 0);
			}
			close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Driver exception: " + ioe.getMessage());
		}
	}
	
	public static void main(String[] args) throws IOException {
		new GNGLiveViewer().runController();
	}
}
