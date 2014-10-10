package edu.hendrix.lmsl.fuzzy.demos.gng;

import edu.hendrix.lmsl.ModeNameInterface;

public enum ModeName implements ModeNameInterface {
	FUZZY_GNG;

	@Override
	public boolean continuePrevious() {
		return false;
	}

}
