package edu.hendrix.lmsl.demos.localize1;

import edu.hendrix.lmsl.Selector;

public class ButtonSelector extends Selector<FlagName,ModeName> {

	public ButtonSelector() {
		super(FlagName.class);
		this.addSelection(FlagName.PICK_FORWARD, ModeName.FORWARD);
		this.addSelection(FlagName.PICK_LEFT, ModeName.GO_LEFT);
		this.addSelection(FlagName.PICK_RIGHT, ModeName.GO_RIGHT);
		this.addSelection(FlagName.PICK_STOP, ModeName.STOP);
	}

	@Override
	protected ModeName getDefaultMode() {
		return ModeName.NO_CHANGE;
	}

}
