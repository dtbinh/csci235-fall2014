package edu.hendrix.lmsl.unsupervised.controllers.fuzzy;

import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import edu.hendrix.ev3webcam.Webcam;
import edu.hendrix.img.IntImage;
import edu.hendrix.lmsl.EnumHistogram;
import edu.hendrix.lmsl.storage.Chooser;
import edu.hendrix.lmsl.storage.FuzzyGNGStorage;
import edu.hendrix.lmsl.unsupervised.controllers.Flag;

public class FuzzyGNGLiveViewer extends FuzzyGNGController {
	
	public FuzzyGNGLiveViewer() {
		super();
	}
	
	@Override
	protected FuzzyGNGMoves<Flag> makeGNG() {
		FuzzyGNGStorage<Flag> storage = FuzzyGNGStorage.getEV3Storage(Flag.class);
		Chooser<FuzzyGNGMoves<Flag>, FuzzyGNGStorage<Flag>> chooser = new Chooser<FuzzyGNGMoves<Flag>, FuzzyGNGStorage<Flag>>();
		chooser.choose(storage);
		return chooser.getSelected();
	}
	
	public void runController() {
		try {
			Webcam.start(176, 144);
			while (!Button.ESCAPE.isDown()) {
				performCurrentAction();
				IntImage grabbed = FuzzyGNGController.grabGNGImage(false);
				int node = getNodeFor(grabbed);
				EnumHistogram<Flag> flags = getChoiceFor(node);
				LCD.clear();
				LCD.drawString("Node " + node, 0, 0);
				LCD.drawString(flags == null ? "No move" : flags.toShortString(), 0, 3);
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
		new FuzzyGNGLiveViewer().runController();
	}
}
