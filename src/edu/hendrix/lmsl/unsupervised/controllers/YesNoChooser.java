package edu.hendrix.lmsl.unsupervised.controllers;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

public class YesNoChooser {
	private boolean yes;
	private String question;
	
	public YesNoChooser(String question, boolean yesIsDefault) {
		this.question = question;
		this.yes = yesIsDefault;
	}
	
	public void refresh() {
		LCD.clear();
		LCD.drawString(question, 0, 0);
		LCD.drawString(yes ? "Yes" : "No", 0, 1);
	}

	public void choose() {
		refresh();
		boolean enabled = true;
		while (!Button.ENTER.isDown() && !Button.ESCAPE.isDown()) {
			if (Button.LEFT.isDown() || Button.RIGHT.isDown() || Button.UP.isDown() || Button.DOWN.isDown()) {
				if (enabled) {
					yes = !yes;
					enabled = false;
					refresh();
				}
			} else {
				enabled = true;
			}
		}
	}
	
	public boolean isYes() {
		return yes;
	}
}
