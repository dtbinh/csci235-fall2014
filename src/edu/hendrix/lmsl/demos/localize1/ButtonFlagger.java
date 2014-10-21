package edu.hendrix.lmsl.demos.localize1;

import lejos.hardware.Button;
import edu.hendrix.lmsl.FlagSet;
import edu.hendrix.lmsl.FlagSetter;

public class ButtonFlagger implements FlagSetter<FlagName> {

	@Override
	public void pollSensor(FlagSet<FlagName> flags) {
		if (Button.LEFT.isDown()) {
			flags.add(FlagName.PICK_LEFT);
		}
		if (Button.RIGHT.isDown()) {
			flags.add(FlagName.PICK_RIGHT);
		}
		if (Button.UP.isDown()) {
			flags.add(FlagName.PICK_FORWARD);
		}
		if (Button.ENTER.isDown()) {
			flags.add(FlagName.PICK_STOP);
		}
	}

	@Override
	public void close() {}
}
