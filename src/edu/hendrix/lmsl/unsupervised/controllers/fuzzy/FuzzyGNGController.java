package edu.hendrix.lmsl.unsupervised.controllers.fuzzy;

import java.io.IOException;

import edu.hendrix.lmsl.EnumHistogram;
import edu.hendrix.lmsl.storage.FuzzyGNGStorage;
import edu.hendrix.lmsl.unsupervised.controllers.AbstractGNGController;
import edu.hendrix.lmsl.unsupervised.controllers.Flag;

public class FuzzyGNGController extends AbstractGNGController<EnumHistogram<Flag>, FuzzyGNGMoves<Flag>> {

	public FuzzyGNGController() {
		super();
	}

	@Override
	protected FuzzyGNGMoves<Flag> makeGNG() {
		return new FuzzyGNGMoves<Flag>(Flag.class, 2, 2);
	}

	@Override
	protected void toStorage(FuzzyGNGMoves<Flag> gng) throws IOException {
		FuzzyGNGStorage.getEV3Storage(Flag.class).save(gng);
	}
}