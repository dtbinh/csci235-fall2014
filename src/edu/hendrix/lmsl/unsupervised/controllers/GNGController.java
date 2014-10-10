package edu.hendrix.lmsl.unsupervised.controllers;

import java.io.IOException;
import edu.hendrix.lmsl.storage.GNGStorage;

public class GNGController extends AbstractGNGController<Flag,GNGNodeMoves<Flag>> {

	public GNGController() {
		super();
	}
	
	protected GNGNodeMoves<Flag> makeGNG() {
		return new GNGNodeMoves<Flag>(2, 40);
	}

	@Override
	protected void toStorage(GNGNodeMoves<Flag> gng) throws IOException {
		GNGStorage.getEV3Storage(Flag.class).save((GNGNodeMoves<Flag>)gng);
	}
}