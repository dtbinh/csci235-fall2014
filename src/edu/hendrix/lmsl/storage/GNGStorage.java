package edu.hendrix.lmsl.storage;

import edu.hendrix.lmsl.unsupervised.controllers.GNGNodeMoves;

public class GNGStorage<F extends Enum<F>> extends Storage<GNGNodeMoves<F>> {
	private Class<F> flagType;

	public GNGStorage(Class<F> flagType, String baseDir) {
		super(baseDir);
		this.flagType = flagType;
	}

	@Override
	public String getDirName() {return "gngFiles";}

	@Override
	public String getDefaultName() {return "gng.txt";}

	@Override
	public GNGNodeMoves<F> fromString(String src) {
		return new GNGNodeMoves<F>(flagType, src);
	}

	public static <F extends Enum<F>> GNGStorage<F> getEV3Storage(Class<F> flagType) {
		return new GNGStorage<F>(flagType, "/home/root");
	}
	
	public static <F extends Enum<F>> GNGStorage<F> getPCStorage(Class<F> flagType) {
		return new GNGStorage<F>(flagType, System.getProperty("user.home"));
	}
}
