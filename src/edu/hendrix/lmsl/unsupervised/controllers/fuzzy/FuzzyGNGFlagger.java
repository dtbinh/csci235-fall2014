package edu.hendrix.lmsl.unsupervised.controllers.fuzzy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map.Entry;

import lejos.hardware.lcd.LCD;
import edu.hendrix.ev3webcam.Webcam;
import edu.hendrix.img.IntImage;
import edu.hendrix.lmsl.storage.FuzzyGNGStorage;
import edu.hendrix.lmsl.unsupervised.controllers.GNGController;
import edu.hendrix.lmsl.fuzzy.FuzzyFlagSet;
import edu.hendrix.lmsl.fuzzy.FuzzyFlagSetter;

public class FuzzyGNGFlagger<F extends Enum<F>> implements FuzzyFlagSetter<F> {
	
	private FuzzyGNGMoves<F> gng;
	
	public FuzzyGNGFlagger(Class<F> flagType) throws IOException {
		this(flagType, FuzzyGNGStorage.getEV3Storage(flagType).choices()[FuzzyGNGStorage.getEV3Storage(flagType).choices().length - 1]);
	}
	
	public FuzzyGNGFlagger(Class<F> flagType, String choice) throws IOException {
		FuzzyGNGStorage<F> storage = FuzzyGNGStorage.getEV3Storage(flagType);
		for (String option: storage.choices()) {
			if (option.equals(choice)) {
				setGNG(storage.open(option));
				return;
			}
		}
		
		throw new FileNotFoundException("Cannot find \"" + choice + "\"");
	}

	public FuzzyGNGFlagger(FuzzyGNGMoves<F> gng) throws IOException {
		setGNG(gng);
	}
	
	private void setGNG(FuzzyGNGMoves<F> gng) throws IOException {
		this.gng = gng;
		gng.purgeMoveFreeNodes();
		Webcam.start(176, 144);
	}

	@Override
	public void pollSensor(FuzzyFlagSet<F> flags) {
		try {
			IntImage grabbed = GNGController.grabGNGImage(true);
			int node = gng.getNodeFor(grabbed);
			if (gng.hasMoveFor(node)) {
				EnumMap<F,Double> portions = gng.getMoveFor(node).getPortions();
				for (Entry<F, Double> entry: portions.entrySet()) {
					flags.set(entry.getKey(), entry.getValue());
				}
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
