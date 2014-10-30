package edu.hendrix.img.database;

import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.lmsl.demos.localize1.Location;

abstract public class ImageData<T> {
	private YUYVImage src;
	private Location where;
	private T processed;
	
	public ImageData(YUYVImage src, Location where) {
		this.src = src;
		this.where = where;
		processed = process(src);
	}
	
	public double distanceTo(YUYVImage other) {
		return distanceTo(processed, process(other));
	}
	
	public YUYVImage getSourceImage() {return src;}
	
	public Location getLocation() {return where;}
	
	abstract public T process(YUYVImage src);
	
	abstract protected double distanceTo(T srcProc, T otherProc);
}
