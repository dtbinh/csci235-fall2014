package edu.hendrix.lmsl.demos.gngdemo;

import edu.hendrix.lmsl.Selector;

public class DriveSelector extends Selector<Flags,Modes> {

	public DriveSelector() {
		super(Flags.class);
		this.addSelection(Flags.FORWARD, Modes.FORWARD);
		this.addSelection(Flags.BACK, Modes.BACK);
		this.addSelection(Flags.LEFT, Modes.LEFT);
		this.addSelection(Flags.RIGHT, Modes.RIGHT);
		this.addSelection(Flags.STOP, Modes.STOP);
	}

	@Override
	protected Modes getDefaultMode() {
		return Modes.NO_CHANGE;
	}

}
