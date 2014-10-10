package edu.hendrix.lmsl.unsupervised;

import edu.hendrix.img.IntImage;
import edu.hendrix.lmsl.unsupervised.funcs.Euclidean;

public class IntImageGas extends GrowingNeuralGas<IntImage> {

	public IntImageGas(DistanceFunc<IntImage> func, int lambda, double k) {
		super(func, lambda, k);
	}
	
	public IntImageGas(DistanceFunc<IntImage> func, int maxEdgeAge, int lambda, int countdown, double k, double neighborRate, double learningRate, double errorDecay, double splitDecay) {
		super(func, maxEdgeAge, lambda, countdown, k, neighborRate, learningRate, errorDecay, splitDecay);
	}
	
	public IntImageGas(String src) {
		super(new Euclidean(), src);
	}
	
	public IntImageGas(IntImageGas that) {
		this(that.toString());
	}
	
	@Override
	protected IntImage tFromString(String src) {
		return IntImage.fromString(src);
	}
}
