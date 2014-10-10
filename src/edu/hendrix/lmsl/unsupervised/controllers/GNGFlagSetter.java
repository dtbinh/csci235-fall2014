package edu.hendrix.lmsl.unsupervised.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import lejos.hardware.lcd.LCD;
import edu.hendrix.ev3webcam.Webcam;
import edu.hendrix.img.IntImage;
import edu.hendrix.lmsl.FlagSet;
import edu.hendrix.lmsl.FlagSetter;
import edu.hendrix.lmsl.storage.GNGStorage;

public class GNGFlagSetter<F extends Enum<F>> implements FlagSetter<F> {
	
	private GNGNodeMoves<F> gng = null;
	
	public GNGFlagSetter(Class<F> flagType) throws IOException {
		this(flagType, GNGStorage.getEV3Storage(flagType).choices()[GNGStorage.getEV3Storage(flagType).choices().length - 1]);
	}
	
	public GNGFlagSetter(Class<F> flagType, String choice) throws IOException {
		GNGStorage<F> storage = GNGStorage.getEV3Storage(flagType);
		for (String option: storage.choices()) {
			if (option.equals(choice)) {
				gng = storage.open(option);
				gng.purgeMoveFreeNodes();
			}
		}
		
		if (gng == null) {
			throw new FileNotFoundException("Cannot find \"" + choice + "\"");
		} else {
			Webcam.start(176, 144);
		}
	}

	@Override
	public void pollSensor(FlagSet<F> flags) {
		try {
			IntImage grabbed = GNGController.grabGNGImage(true);
			int node = gng.getNodeFor(grabbed);
			if (gng.hasMoveFor(node)) {
				flags.add(gng.getMoveFor(node));
			}
		} catch (IOException e) {
			LCD.clear();
			LCD.drawString("Unable to grab image", 50, 50);
		}
	}

	@Override
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
