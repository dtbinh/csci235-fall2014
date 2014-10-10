package edu.hendrix.lmsl.unsupervised.controllers.fuzzy;

import edu.hendrix.img.IntImage;
import edu.hendrix.lmsl.EnumHistogram;
import edu.hendrix.lmsl.unsupervised.IntImageGas;
import edu.hendrix.lmsl.unsupervised.controllers.AbstractGNGNodeMoves;

public class FuzzyGNGMoves<F extends Enum<F>> extends AbstractGNGNodeMoves<EnumHistogram<F>, F> {
	private Class<F> flagType;
	
	private void setFlagType(Class<F> flagParam) {
		this.flagType = flagParam;
	}
	
	public FuzzyGNGMoves(Class<F> flagType, IntImageGas gng) {
		super(gng);
		setFlagType(flagType);
	}
	
	public FuzzyGNGMoves(Class<F> flagType, int lambda, double k) {
		super(lambda, k);
		setFlagType(flagType);
	}
	
	public FuzzyGNGMoves(Class<F> flagType, String s) {
		super(flagType, s);
		setFlagType(flagType);
	}
	
	@Override
	public void update(F move, IntImage img) {
		gng.trainOnce(img);
		if (gng.hasMostRecentWinner()) {
			try {
				int winner = gng.getMostRecentCategory();
				countWinner(winner);
				if (!hasMoveFor(winner)) {
					addMoveFor(winner, new EnumHistogram<F>(flagType));
				}
				getMoveFor(winner).bump(move);
			} catch (NullPointerException npe) {
				System.out.println("hasMostRecent: " + gng.hasMostRecentWinner());
				System.out.println("getMostRecent: " + gng.getMostRecentWinner());
				throw npe;
			}
		}
	}

	@Override
	public EnumHistogram<F> moveFromString(Class<F> enumType, String src) {
		return EnumHistogram.fromString(enumType, src);
	}
}
