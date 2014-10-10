package edu.hendrix.lmsl.demos.gngdemo;

import edu.hendrix.lmsl.ModeNameInterface;

public enum Modes implements ModeNameInterface {
	FORWARD, LEFT, RIGHT, BACK, STOP, NO_CHANGE {public boolean continuePrevious() {return false;}};

	@Override
	public boolean continuePrevious() {
		return false;
	}

}
