package lmsldemo;

import edu.hendrix.lmsl.ModeNameInterface;

public enum ModeName implements ModeNameInterface {
	FORWARD, AVOID_LEFT, AVOID_RIGHT, GO_LEFT, GO_RIGHT, NO_CHANGE {public boolean continuePrevious() {return true;}};

	@Override
	public boolean continuePrevious() {
		return false;
	}
}
