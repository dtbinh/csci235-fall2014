package edu.hendrix.lmsl.unsupervised.controllers;

import java.io.IOException;
import java.util.LinkedHashMap;

import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.lcd.LCD;
import edu.hendrix.ev3webcam.Webcam;
import edu.hendrix.img.IntImage;
import edu.hendrix.lmsl.storage.Chooser;
import edu.hendrix.lmsl.storage.Storage;
import edu.hendrix.lmsl.storage.YesNoChooser;
import edu.hendrix.lmsl.unsupervised.controllers.fuzzy.FuzzyGNGController;

abstract public class AbstractGNGMoveRecorder<M, G extends AbstractGNGNodeMoves<M,Flag>, S extends Storage<G>> extends AbstractGNGController<M,G> {

	private LinkedHashMap<Key,Flag> key2Flag;
	
	abstract protected G makeBasicGNG();
	
	protected G makeGNG() {
		Chooser<G, S> getOld = new Chooser<G, S>();
		S storage = getStorage();
		if (getOld.choicesExist(storage)) {
			YesNoChooser useOld = new YesNoChooser("Use existing GNG?", false);
			useOld.choose();
			if (useOld.isYes()) {
				getOld.choose(storage);
				if (getOld.isSelected()) {
					return getOld.getSelected();
				}
			} else {
				LCD.clearDisplay();
				LCD.drawString("Starting up...", 0, 2);
			}
		}
		
		return makeBasicGNG();
	}
	
	public AbstractGNGMoveRecorder() {
		super();
		key2Flag = new LinkedHashMap<Key,Flag>();		
		key2Flag.put(Button.UP, Flag.FORWARD);
		key2Flag.put(Button.DOWN, Flag.BACK);
		key2Flag.put(Button.LEFT, Flag.LEFT);
		key2Flag.put(Button.RIGHT, Flag.RIGHT);
		key2Flag.put(Button.ENTER, Flag.STOP);
		key2Flag.put(Button.ESCAPE, Flag.STOP);
	}
	
	public void runController() {
		YesNoChooser allChooser = new YesNoChooser("Record ALL moves?", true);
		allChooser.choose();
		boolean recordAll = allChooser.isYes();
		LCD.clearDisplay();
		LCD.drawString("Starting webcam...", 0, 2);
		Key lastKey = Button.ENTER;
		try {
			try {
				Webcam.start(176, 144);
				Flag currentFlag = Flag.STOP;
				while (!Button.ESCAPE.isDown()) {
					IntImage img = FuzzyGNGController.grabGNGImage(true);
					Key currentKey = getCurrentKey();
					Flag newFlag = key2Flag.containsKey(currentKey) ? key2Flag.get(currentKey) : currentFlag;
					if (currentFlag != newFlag) {
						performCurrentAction();
						currentFlag = newFlag;
					}
					if (currentFlag != Flag.STOP && (recordAll || currentKey != lastKey)) {
						updateGNG(currentFlag, img);
					}
					lastKey = currentKey;
				}
			} catch (Exception exc) {
				exc.printStackTrace();
			} finally {
				close();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Driver exception: " + ioe.getMessage());
		} 
	}
	
	abstract protected S getStorage();

	@Override
	protected void toStorage(G gng) throws IOException {
		getStorage().save((G)gng);
	}
}
