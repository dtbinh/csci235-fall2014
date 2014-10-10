package lmsldemo;

import edu.hendrix.lmsl.Selector;

public class DirectionSelector extends Selector<FlagName, ModeName> {

	public DirectionSelector() {
		super(FlagName.class);
		addSelection(FlagName.PICK_LEFT, ModeName.AVOID_LEFT);
		addSelection(FlagName.PICK_RIGHT, ModeName.AVOID_RIGHT);
	}

	@Override
	protected ModeName getDefaultMode() {
		return ModeName.NO_CHANGE;
	}
}
