package edu.hendrix.lmsl.unsupervised.funcs;

import edu.hendrix.img.IntImage;
import edu.hendrix.lmsl.Logger;
import edu.hendrix.lmsl.unsupervised.DistanceFunc;

public class Euclidean extends DistanceFunc<IntImage> {
	@Override
	public long distance(IntImage img1, IntImage img2) {
		if (!img1.matchesSizeWith(img2)) {
			Logger.instance().log("image size mismatch");
			Logger.instance().log("img1: (" + img1.getWidth() + "," + img1.getHeight() + ")");
			Logger.instance().log("img2: (" + img2.getWidth() + "," + img2.getHeight() + ")");
			throw new IllegalArgumentException("Size mismatch");
		}
		long ssd = 0;
		for (int x = 0; x < img1.getWidth(); ++x) {
			for (int y = 0; y < img1.getHeight(); ++y) {
				long diff = img1.get(x, y) - img2.get(x, y);
				ssd += diff*diff;
			}
		}
		return ssd;
	}
}
