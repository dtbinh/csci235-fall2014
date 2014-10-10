package edu.hendrix.lmsl.storage;

import edu.hendrix.lmsl.unsupervised.controllers.fuzzy.FuzzyGNGMoves;

public class FuzzyGNGStorage<F extends Enum<F>> extends Storage<FuzzyGNGMoves<F>> {
	private Class<F> flagType;

	public FuzzyGNGStorage(Class<F> flagType, String baseDir) {
		super(baseDir);
		this.flagType = flagType;
	}

	@Override
	public String getDirName() {return "gngFuzzyFiles";}

	@Override
	public String getDefaultName() {return "gng.txt";}

	@Override
	public FuzzyGNGMoves<F> fromString(String src) {
		return new FuzzyGNGMoves<F>(flagType, src);
	}

	public static <F extends Enum<F>> FuzzyGNGStorage<F> getEV3Storage(Class<F> flagType) {
		return new FuzzyGNGStorage<F>(flagType, "/home/root");
	}
	
	public static <F extends Enum<F>> FuzzyGNGStorage<F> getPCStorage(Class<F> flagType) {
		return new FuzzyGNGStorage<F>(flagType, System.getProperty("user.home"));
	}
}
