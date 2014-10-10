package edu.hendrix.lmsl.unsupervised.controllers.fuzzy;

import edu.hendrix.lmsl.storage.Chooser;
import edu.hendrix.lmsl.storage.Deleter;
import edu.hendrix.lmsl.storage.FuzzyGNGStorage;
import edu.hendrix.lmsl.unsupervised.controllers.Flag;

public class FuzzyGNGDeleter extends Deleter<FuzzyGNGMoves<Flag>, FuzzyGNGStorage<Flag>>{
	public FuzzyGNGDeleter() {
		super(FuzzyGNGStorage.getEV3Storage(Flag.class), new Chooser<FuzzyGNGMoves<Flag>, FuzzyGNGStorage<Flag>>());
	}

	public static void main(String[] args) {
		new FuzzyGNGDeleter().run();
	}
}
