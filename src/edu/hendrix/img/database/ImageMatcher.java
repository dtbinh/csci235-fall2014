package edu.hendrix.img.database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.lmsl.demos.localize1.Location;

abstract public class ImageMatcher<T> {
	private ArrayList<ImageData<T>> database;
	
	public ImageMatcher(YUYVImageList images) {
		database = new ArrayList<ImageData<T>>();
		for (int i = 0; i < images.size(); ++i) {
			database.add(makeImageData(images.getImage(i), images.getLocation(i)));
		}
	}
	
	abstract public ImageData<T> makeImageData(YUYVImage img, Location where);
	
	public ImageMatch getBestMatch(YUYVImage input) {
		ImageMatch best = null;
		for (ImageData<T> data: database) {
			double sim = data.distanceTo(input);
			if (best == null || best.getDistance() > sim) {
				best = new ImageMatch(data.getSourceImage(), data.getLocation(), sim);
			}
		}
		return best;
	}

	public ImageMatch getBestMatch(YUYVImage input, Location at, int numCandidates) {
		ImageMatch best = null;
		for (ImageData<T> data: topNClosestTo(at, numCandidates)) {
			double sim = data.distanceTo(input);
			if (best == null || best.getDistance() > sim) {
				best = new ImageMatch(data.getSourceImage(), data.getLocation(), sim);
			}
		}
		return best;
	}
	
	public ArrayList<ImageData<T>> topNClosestTo(Location at, int n) {
		Set<Integer> bestSet = new HashSet<Integer>();
		ArrayList<ImageData<T>> result = new ArrayList<ImageData<T>>();
		for (int i = 0; i < n; ++i) {
			int best = 0;
			double bestDist = database.get(0).getLocation().distanceTo(at);
			for (int j = 1; j < database.size(); ++j) {
				if (!bestSet.contains(j)) {
					double dist = database.get(j).getLocation().distanceTo(at);
					if (dist < bestDist) {
						bestDist = dist;
						best = j;
					}
				}
			}
			result.add(database.get(best));
			bestSet.add(best);
		}
		return result;
	}

	/*
	public ImageMatch getBestMatchWithin(YUYVImage input, Location at, double maxDistance) {
		ImageMatch best = null;
		for (ImageData<T> data: database) {
			if (data.getLocation().distanceTo(at) <= maxDistance) {
				double sim = data.distanceTo(input);
				if (best == null || best.getDistance() > sim) {
					best = new ImageMatch(data.getSourceImage(), data.getLocation(), sim);
				}
			}
		}
		return best;
	}
	*/
}
