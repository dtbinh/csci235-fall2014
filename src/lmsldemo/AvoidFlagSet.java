package lmsldemo;

import edu.hendrix.lmsl.FlagSet;

public class AvoidFlagSet extends FlagSet<FlagName> {

	public AvoidFlagSet(FlagName... startFlags) {
		super(FlagName.class, startFlags);
		addExclusion(FlagName.BLOCKED, FlagName.CLEAR);
	}

	public AvoidFlagSet(AvoidFlagSet other) {
		super(other);
	}
}
