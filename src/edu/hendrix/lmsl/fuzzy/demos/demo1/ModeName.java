package edu.hendrix.lmsl.fuzzy.demos.demo1;

import edu.hendrix.lmsl.ModeNameInterface;

public enum ModeName implements ModeNameInterface {
	FUZZY_AVOID;

	@Override
	public boolean continuePrevious() {
		return false;
	}

}
