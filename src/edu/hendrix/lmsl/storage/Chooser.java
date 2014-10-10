package edu.hendrix.lmsl.storage;

import java.io.FileNotFoundException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

public class Chooser<T, S extends Storage<T>> {
	private boolean picked;
	private String pickedFilename;
	private T thing;
	
	public Chooser() {
		picked = false;
		pickedFilename = "";
		thing = null;
	}
	
	public boolean choicesExist(S storage) {
		return storage.choices().length > 0;
	}
	
	public void choose(S storage) {
		String[] choices = storage.choices();
		if (choices.length == 0) {
			System.out.println("No GNGs available");
			while (!Button.ESCAPE.isDown());
		} else {
			int choice = choices.length - 1;
			displayChoice(choices[choice]);
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
				thing = storage.open(pickedFilename);
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
	
	public boolean isSelected() {
		return picked;
	}
	
	public String getSelectedFilename() {
		return pickedFilename;
	}
	
	public T getSelected() {
		return thing;
	}
}
