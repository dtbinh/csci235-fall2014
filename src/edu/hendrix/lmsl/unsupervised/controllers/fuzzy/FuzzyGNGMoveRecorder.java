package edu.hendrix.lmsl.unsupervised.controllers.fuzzy;

import edu.hendrix.lmsl.EnumHistogram;
import edu.hendrix.lmsl.storage.FuzzyGNGStorage;
import edu.hendrix.lmsl.unsupervised.controllers.AbstractGNGMoveRecorder;
import edu.hendrix.lmsl.unsupervised.controllers.Flag;

public class FuzzyGNGMoveRecorder extends AbstractGNGMoveRecorder<EnumHistogram<Flag>, FuzzyGNGMoves<Flag>, FuzzyGNGStorage<Flag>> {
	public static void main(String[] args) {
		new FuzzyGNGMoveRecorder().runController();
	}

	@Override
	protected FuzzyGNGMoves<Flag> makeBasicGNG() {
		return new FuzzyGNGMoves<Flag>(Flag.class, 2, 2);
	}

	@Override
	protected FuzzyGNGStorage<Flag> getStorage() {
		return FuzzyGNGStorage.getEV3Storage(Flag.class);
	}
}
