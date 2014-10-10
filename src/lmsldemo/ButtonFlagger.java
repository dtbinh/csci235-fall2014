package lmsldemo;

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
	}

	@Override
	public void close() {}
}
