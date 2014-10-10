package edu.hendrix.lmsl.fuzzy.demos.gng;

import java.io.IOException;

import edu.hendrix.lmsl.fuzzy.FuzzyFlagSet;
import edu.hendrix.lmsl.fuzzy.FuzzyFlagSetters;
import edu.hendrix.lmsl.unsupervised.controllers.fuzzy.FuzzyGNGFlagger;
import edu.hendrix.lmsl.unsupervised.controllers.fuzzy.FuzzyGNGMoves;

public class FuzzyGNGSensors extends FuzzyFlagSetters<Flags> {

	public FuzzyGNGSensors(FuzzyGNGMoves<Flags> gng) throws IOException {
		super(Flags.class);
		addFuzzySetter(new FuzzyGNGFlagger<Flags>(gng));
	}

	@Override
	public FuzzyFlagSet<Flags> makeFlagSet() {
		return new FuzzyFlagSet<Flags>(Flags.class);
	}

}
