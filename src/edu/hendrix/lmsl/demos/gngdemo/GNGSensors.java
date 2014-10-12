package edu.hendrix.lmsl.demos.gngdemo;

import java.io.IOException;

import edu.hendrix.lmsl.FlagSet;
import edu.hendrix.lmsl.FlagSetters;
import edu.hendrix.lmsl.unsupervised.controllers.GNGFlagSetter;
import edu.hendrix.lmsl.unsupervised.controllers.GNGNodeMoves;

public class GNGSensors extends FlagSetters<Flags> {

	public GNGSensors() throws IOException {
		super(Flags.class);
		addSetter(new GNGFlagSetter<Flags>(Flags.class));
	}
	
	public GNGSensors(GNGNodeMoves<Flags> gng) throws IOException {
		super(Flags.class);
		addSetter(new GNGFlagSetter<Flags>(gng));
	}

	@Override
	public FlagSet<Flags> makeFlagSet() {
		return new FlagSet<Flags>(Flags.class);
	}

}
