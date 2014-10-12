package edu.hendrix.lmsl.demos.gngdemo;

import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import edu.hendrix.lmsl.Layer;
import edu.hendrix.lmsl.storage.Chooser;
import edu.hendrix.lmsl.storage.GNGStorage;
import edu.hendrix.lmsl.unsupervised.controllers.GNGNodeMoves;

public class GNGDriveLayer extends Layer<Flags,Modes> {
	public GNGDriveLayer() {
		super(Modes.STOP, new DriveSelector(), Modes.class);
	}
	
	public static void main(String[] args) throws IOException {
		GNGStorage<Flags> storage = GNGStorage.getEV3Storage(Flags.class);
		Chooser<GNGNodeMoves<Flags>, GNGStorage<Flags>> chooser = new Chooser<GNGNodeMoves<Flags>, GNGStorage<Flags>>();
		chooser.choose(storage);
		if (chooser.isSelected()) {
			new GNGDriveLayer().runController(new GNGSensors(chooser.getSelected()), new Actions(), false);
		} else {
			LCD.clearDisplay();
			LCD.drawString("No GNG chosen", 0, 3);
			while (!Button.ESCAPE.isDown());
		}
		
		new GNGDriveLayer().runController(new GNGSensors(), new Actions(), false);
	}
}
