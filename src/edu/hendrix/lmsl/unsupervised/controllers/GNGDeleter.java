package edu.hendrix.lmsl.unsupervised.controllers;

import edu.hendrix.lmsl.storage.Chooser;
import edu.hendrix.lmsl.storage.Deleter;
import edu.hendrix.lmsl.storage.GNGStorage;

public class GNGDeleter extends Deleter<GNGNodeMoves<Flag>, GNGStorage<Flag>>{
	public GNGDeleter() {
		super(GNGStorage.getEV3Storage(Flag.class), new Chooser<GNGNodeMoves<Flag>, GNGStorage<Flag>>());
	}

	public static void main(String[] args) {
		new GNGDeleter().run();
	}
}
