package lmsldemo;

import edu.hendrix.lmsl.Selector;

public class LeftAvoidSelector extends Selector<FlagName,ModeName> {

	public LeftAvoidSelector() {
		super(FlagName.class);
		super.addSelection(FlagName.BLOCKED, ModeName.GO_LEFT);
	}
	
	@Override
	protected ModeName getDefaultMode() {
		return ModeName.FORWARD;
	}
}
