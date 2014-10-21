package edu.hendrix.img.features;

import java.util.BitSet;

import edu.hendrix.ev3webcam.BooleanImage;
import edu.hendrix.ev3webcam.Point;
import edu.hendrix.img.IntImage;
import edu.hendrix.img.KeypointFinder;

public class FeatureSet {
	private BitSet[] features;
	
	public FeatureSet(IntImage img, PointFieldPattern pattern) {
		features = new BitSet[pattern.getNumPairs()];
		
		KeypointFinder finder = new KeypointFinder(pattern.getNumPairs());
		BooleanImage keypoints = finder.process(img);
		int i = 0;
		for (Point set: keypoints.getSetPoints()) {
			features[i++] = pattern.featureFor(new View(img, set.getX(), set.getY(), pattern.getFeatureWidth(), pattern.getFeatureHeight()));
		}
	}
	
	
}
