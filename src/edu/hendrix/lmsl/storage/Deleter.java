package edu.hendrix.lmsl.storage;

import lejos.hardware.lcd.LCD;
import edu.hendrix.lmsl.storage.Chooser;

public class Deleter<T, S extends Storage<T>> {
	private S storage;
	private Chooser<T,S> chooser;
	
	public Deleter(S storage, Chooser<T,S> chooser) {
		this.storage = storage;
		this.chooser = chooser;
	}
	
	public void run() {
		chooser.choose(storage);
		if (chooser.isSelected()) {
			YesNoChooser yesNo = new YesNoChooser("Are you sure?", false);
			yesNo.choose();
			if (yesNo.isYes()) {
				LCD.clear();
				if (storage.delete(chooser.getSelectedFilename())) {
					LCD.drawString("Deleted", 0, 0);
				} else {
					LCD.drawString("Deletion failed", 0, 0);
				}
			}
		}		
	}
}
