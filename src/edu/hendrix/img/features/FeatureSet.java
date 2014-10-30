package edu.hendrix.img.features;

import java.util.BitSet;
import java.util.Map.Entry;

import edu.hendrix.ev3webcam.Point;
import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.img.IntImage;
import edu.hendrix.img.KeypointFinder;

public class FeatureSet {
	private BitSet[] features;
	private int[] weights;
	
	private FeatureSet(int numFeatures) {
		features = new BitSet[numFeatures];
		weights = new int[numFeatures];
	}
	
	public FeatureSet(YUYVImage img) {
		//this(new IntImage(img));
		this(IntImage.toShrunkenGrayInts(img, 2));
	}
	
	private FeatureSet(IntImage img) {
		this(img, new Pattern1());
	}
	
	private FeatureSet(IntImage img, PointFieldPattern pattern) {
		int n = img.getWidth() / pattern.getFeatureWidth();
		int m = img.getHeight() / pattern.getFeatureHeight();
		features = new BitSet[n*m];
		weights = new int[n*m];
		
		KeypointFinder finder = new KeypointFinder(n, m, img);
		int i = 0;
		for (Entry<Point,Integer> keypoint: finder) {
			features[i] = pattern.featureFor(new View(img, keypoint.getKey().getX(), keypoint.getKey().getY(), pattern.getFeatureWidth(), pattern.getFeatureHeight()));
			weights[i] = keypoint.getValue();
			i += 1;
		}
	}
	
	public int numFeatures() {return features.length;}
	
	public static int hamming(BitSet one, BitSet two) {
		BitSet result = (BitSet)one.clone();
		result.xor(two);
		return result.cardinality();
	}
	
	public int distanceTo(FeatureSet that) {
		int total = 0;
		for (int i = 0; i < this.numFeatures(); ++i) {
			int min = hamming(this.features[i], that.features[0]);
			for (int j = 1; j < that.numFeatures(); ++j) {
				min = Math.min(min, hamming(this.features[i], that.features[j]));
			}
			total += min;
		}
		return total;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof FeatureSet) {
			FeatureSet that = (FeatureSet)other;
			if (this.numFeatures() == that.numFeatures()) {
				for (int i = 0; i < numFeatures(); ++i) {
					if (!this.features[i].equals(that.features[i]) || this.weights[i] != that.weights[i]) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("[");
		for (int i = 0; i < numFeatures(); ++i) {
			result.append(features[i].toString());
			result.append(":");
			result.append(weights[i]);
			result.append(";");
		}
		result.append("]");
		return result.toString();
	}
	
	public static BitSet bitsFromString(String src) {
		src = src.substring(1, src.length() - 1);
		BitSet result = new BitSet();
		for (String value: src.split(", ")) {
			result.set(Integer.parseInt(value));
		}
		return result;
	}
	
	@Override
	public int hashCode() {return toString().hashCode();}
	
	public static FeatureSet fromString(String src) {
		src = src.substring(1, src.length() - 1);
		String[] pairStrings = src.split(";");
		FeatureSet result = new FeatureSet(pairStrings.length);
		for (int i = 0; i < pairStrings.length; ++i) {
			String[] pair = pairStrings[i].split(":");
			result.features[i] = bitsFromString(pair[0]);
			result.weights[i] = Integer.parseInt(pair[1]);
		}
		return result;
	}
}
