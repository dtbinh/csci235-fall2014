package edu.hendrix.lmsl.fuzzy.demos.gng;

import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import edu.hendrix.lmsl.FlagSet;
import edu.hendrix.lmsl.fuzzy.FuzzyMode;
import edu.hendrix.lmsl.fuzzy.FuzzyRule;
import edu.hendrix.lmsl.storage.Chooser;
import edu.hendrix.lmsl.storage.FuzzyGNGStorage;
import edu.hendrix.lmsl.unsupervised.controllers.fuzzy.FuzzyGNGMoves;

public class FuzzyGNGDriveMode extends FuzzyMode<Flags,ModeName> {

	public FuzzyGNGDriveMode() {
		super(Flags.class, ModeName.class);
		
		this.addRule(new FuzzyRule<Flags>(Motor.A, 0, 600) {
			@Override
			protected double inferFrom(FlagSet<Flags> flags) {
				return or(flags.getFuzzy(Flags.FORWARD), flags.getFuzzy(Flags.RIGHT));
			}});
		
		this.addRule(new FuzzyRule<Flags>(Motor.D, 0, 600) {
			@Override
			protected double inferFrom(FlagSet<Flags> flags) {
				return or(flags.getFuzzy(Flags.FORWARD), flags.getFuzzy(Flags.LEFT));
			}});		
	}
	
	public static void main(String[] args) throws IOException {
		FuzzyGNGStorage<Flags> storage = FuzzyGNGStorage.getEV3Storage(Flags.class);
		Chooser<FuzzyGNGMoves<Flags>, FuzzyGNGStorage<Flags>> chooser = new Chooser<FuzzyGNGMoves<Flags>, FuzzyGNGStorage<Flags>>();
		chooser.choose(storage);
		if (chooser.isSelected()) {
			new FuzzyGNGDriveMode().runController(new FuzzyGNGSensors(chooser.getSelected()), ModeName.FUZZY_GNG);
		} else {
			LCD.clearDisplay();
			LCD.drawString("No GNG chosen", 0, 3);
			while (!Button.ESCAPE.isDown());
		}
	}
}
