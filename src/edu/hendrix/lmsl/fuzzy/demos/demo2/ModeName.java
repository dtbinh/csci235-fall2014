package edu.hendrix.lmsl.fuzzy.demos.demo2;

import edu.hendrix.lmsl.ModeNameInterface;

public enum ModeName implements ModeNameInterface {
	FUZZY_LOOK;

	@Override
	public boolean continuePrevious() {
		return false;
	}

}
