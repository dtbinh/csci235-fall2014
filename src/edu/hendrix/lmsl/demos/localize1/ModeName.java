package edu.hendrix.lmsl.demos.localize1;

import edu.hendrix.lmsl.ModeNameInterface;

public enum ModeName implements ModeNameInterface {
	FORWARD, GO_LEFT, GO_RIGHT, STOP, NO_CHANGE {public boolean continuePrevious() {return true;}};

	@Override
	public boolean continuePrevious() {
		return false;
	}
}
