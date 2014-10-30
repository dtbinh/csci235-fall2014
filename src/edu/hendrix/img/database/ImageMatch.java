package edu.hendrix.img.database;

import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.lmsl.demos.localize1.Location;

public class ImageMatch {
	private YUYVImage img;
	private Location where;
	private double similarity;
	
	public ImageMatch(YUYVImage img, Location where, double similarity) {
		this.img = img;
		this.where = where;
		this.similarity = similarity;
	}
	
	public YUYVImage getImage() {return img;}
	
	public double getDistance() {return similarity;}
	
	public Location getLocation() {return where;}
}
