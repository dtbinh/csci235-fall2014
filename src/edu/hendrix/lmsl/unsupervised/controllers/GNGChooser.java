package edu.hendrix.lmsl.unsupervised.controllers;

import java.io.FileNotFoundException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import edu.hendrix.lmsl.storage.GNGStorage;

public class GNGChooser {
	private boolean picked;
	private String pickedFilename;
	private GNGNodeMoves<Flag> gng;
	
	public GNGChooser() {
		picked = false;
		pickedFilename = "";
	}
	
	public void choose(GNGStorage<Flag> storage) {
		String[] choices = storage.choices();
		if (choices.length == 0) {
			System.out.println("No GNGs available");
			while (!Button.ESCAPE.isDown());
		} else {
			displayChoice(choices[0]);
			int choice = 0;
			while (!Button.ENTER.isDown()) {
				if (Button.ESCAPE.isDown()) {
					return;
				}
				
				int newChoice = choice;
				if (Button.LEFT.isDown()) {
					while (Button.LEFT.isDown());
					newChoice = choice - 1;
					if (newChoice < 0) {newChoice += choices.length;}
				}
				if (Button.RIGHT.isDown()) {
					while (Button.RIGHT.isDown());
					newChoice = (choice + 1) % choices.length;
				}
				if (newChoice != choice) {
					choice = newChoice;
					displayChoice(choices[choice]);
				}
			}
			
			try {
				LCD.clear();
				pickedFilename = choices[choice];
				LCD.drawString("Opening \"" + pickedFilename + "\"...", 0, 0);
				gng = storage.open(pickedFilename);
				picked = true;
			} catch (FileNotFoundException e) {
				LCD.clear();
				System.out.println("File \"" + choices[choice] + "\" not found");
				System.out.println("Press ESCAPE to exit");
				while (!Button.ESCAPE.isDown());
			}
		}
	}
	
	public static void displayChoice(String choice) {
		LCD.clear();
		LCD.drawString("File: \"" + choice + "\"", 0, 0);
	}
	
	public boolean gngIsSelected() {
		return picked;
	}
	
	public String getSelectedFilename() {
		return pickedFilename;
	}
	
	public GNGNodeMoves<Flag> getSelectedGNG() {
		return gng;
	}
}
