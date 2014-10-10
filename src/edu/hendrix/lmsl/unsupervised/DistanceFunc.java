package edu.hendrix.lmsl.unsupervised;

import java.util.ArrayList;

abstract public class DistanceFunc<T extends SupportsArithmetic<T>> {
	abstract public long distance(T img1, T img2);
	
	public final static long square(long value) {
		return value * value;
	}
	
	public T centroidOf(ArrayList<T> imgs) {
		T first = imgs.get(0);
		T sum = first.getAddIdentity();
		for (T img: imgs) {
			sum.addTo(img);
		}
		sum.divBy(imgs.size());
		return sum;
	}
	
	public void train(T target, T other, double scale) {
		T start = target.duplicate();
		target.subtractFrom(other);
		target.multBy(scale);
		target.addTo(start);
	}
	
	public T centroidOf(T img1, T img2) {
		ArrayList<T> imgs = new ArrayList<T>();
		imgs.add(img1);
		imgs.add(img2);
		return centroidOf(imgs);
	}
}
