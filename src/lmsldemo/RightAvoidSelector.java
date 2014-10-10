package lmsldemo;

import edu.hendrix.lmsl.Selector;

public class RightAvoidSelector extends Selector<FlagName,ModeName> {

	public RightAvoidSelector() {
		super(FlagName.class);
		super.addSelection(FlagName.BLOCKED, ModeName.GO_RIGHT);
	}
	
	@Override
	protected ModeName getDefaultMode() {
		return ModeName.FORWARD;
	}
}
