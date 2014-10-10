package edu.hendrix.lmsl.unsupervised.funcs;

import edu.hendrix.img.IntImage;
import edu.hendrix.lmsl.unsupervised.DistanceFunc;

public class Euclidean extends DistanceFunc<IntImage> {
	@Override
	public long distance(IntImage img1, IntImage img2) {
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
