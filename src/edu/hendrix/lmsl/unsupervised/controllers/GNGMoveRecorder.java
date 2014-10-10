package edu.hendrix.lmsl.unsupervised.controllers;

import edu.hendrix.lmsl.storage.GNGStorage;

public class GNGMoveRecorder extends AbstractGNGMoveRecorder<Flag, GNGNodeMoves<Flag>, GNGStorage<Flag>> {
	public static void main(String[] args) {
		new GNGMoveRecorder().runController();
	}

	@Override
	protected GNGNodeMoves<Flag> makeBasicGNG() {
		return new GNGNodeMoves<Flag>(2, 40);
	}

	@Override
	protected GNGStorage<Flag> getStorage() {
		return GNGStorage.getEV3Storage(Flag.class);
	}
}
