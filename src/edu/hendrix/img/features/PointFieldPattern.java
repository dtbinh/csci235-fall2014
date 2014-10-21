package edu.hendrix.img.features;

import java.util.BitSet;

import edu.hendrix.ev3webcam.Point;

abstract public class PointFieldPattern {
	private PointPair[] pairs;
	private int featureWidth, featureHeight;
	
	protected class PointPair {
		Point a, b;
		PointPair(Point a, Point b) {
			this.a = a;
			this.b = b;
		}
		
		public boolean briefFeature(View v) {
			return v.get(a.getX(), a.getY()) < v.get(b.getX(), b.getY());
		}
	}
	
	abstract protected PointPair[] makePairs();
	
	public PointFieldPattern(int featureWidth, int featureHeight) {
		pairs = makePairs();
		this.featureWidth = featureWidth;
		this.featureHeight = featureHeight;
	}
	
	public BitSet featureFor(View img) {
		BitSet result = new BitSet(pairs.length);
		int bit = 0;
		for (PointPair pair: pairs) {
			result.set(bit++, pair.briefFeature(img));
		}
		return result;
	}
	
	public int getNumPairs() {return pairs.length;}
	
	public int getFeatureWidth() {return featureWidth;}
	
	public int getFeatureHeight() {return featureHeight;}
}
