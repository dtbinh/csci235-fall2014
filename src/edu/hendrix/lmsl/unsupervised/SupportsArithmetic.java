package edu.hendrix.lmsl.unsupervised;

public interface SupportsArithmetic<T> {
	public void addTo(T addend);
	public void subtractFrom(T minuend);
	public void multBy(double scalar);
	public void divBy(int scalar);
	
	public T getAddIdentity();
	public T duplicate();
}
