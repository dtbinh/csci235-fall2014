package edu.hendrix.img.database;

import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.img.IntImage;
import edu.hendrix.lmsl.demos.localize1.Location;
import edu.hendrix.lmsl.unsupervised.funcs.Euclidean;

public class IntImageData extends ImageData<IntImage> {
	private static Euclidean f = new Euclidean();

	public IntImageData(YUYVImage src, Location where) {
		super(src, where);
	}

	@Override
	public IntImage process(YUYVImage src) {
		//return new IntImage(src);
		return IntImage.toShrunkenGrayInts(src, 2);
	}

	@Override
	protected double distanceTo(IntImage srcProc, IntImage otherProc) {
		return f.distance(srcProc, otherProc);
	}

}
