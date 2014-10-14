package edu.hendrix.lmsl.unsupervised.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.hendrix.img.IntImage;
import edu.hendrix.lmsl.unsupervised.IntImageGas;

public class GNGNodeMoves<F extends Enum<F>> extends AbstractGNGNodeMoves<F,F> {

	public GNGNodeMoves(IntImageGas gng) {super(gng);}
	
	public GNGNodeMoves(int lambda, double k) {super(lambda, k);}
	
	public GNGNodeMoves(Class<F> flagType, String s) {super(flagType, s);}
	
	@Override
	public F moveFromString(Class<F> enumType, String src) {
		return Enum.valueOf(enumType, src);
	}

	public static <F extends Enum<F>> GNGNodeMoves<F> fromFile(Class<F> flagType, File f) throws FileNotFoundException {
		Scanner scanner = new Scanner(f);
		GNGNodeMoves<F> result = new GNGNodeMoves<F>(flagType, scanner.useDelimiter("\\A").next());
		scanner.close();
		return result;
	}
		
	public GNGNodeMoves<F> includesMovesOnly(Class<F> flagType) {
		GNGNodeMoves<F> result = new GNGNodeMoves<F>(flagType, this.toString());
		result.purgeMoveFreeNodes();
		return result;
	}
	
	public void update(F move, IntImage img) {
		gng.trainOnce(img);
		if (gng.hasMostRecentWinner()) {
			int winner = gng.getMostRecentCategory();
			countWinner(winner);
			if (hasMoveFor(winner)) {
				if (!getMoveFor(winner).equals(move)) {
					addMoveFor(gng.forceSpecificNode(winner, img), move);
				}
			} else {
				addMoveFor(winner, move);
			}
		}
	}

	@Override
	public boolean purge(F candidate) {
		return false;
	}
}
