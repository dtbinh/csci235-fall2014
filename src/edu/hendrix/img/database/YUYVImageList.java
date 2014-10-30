package edu.hendrix.img.database;

import java.util.ArrayList;

import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.lmsl.demos.localize1.Location;

public class YUYVImageList {
	private ArrayList<YUYVImage> images;
	private ArrayList<Location> locations;
	
	public YUYVImageList() {
		images = new ArrayList<YUYVImage>();
		locations = new ArrayList<Location>();
	}
	
	public void add(YUYVImage img, Location where) {
		images.add(img);
		locations.add(where);
	}
	
	public YUYVImage getImage(int i) {
		return images.get(i);
	}
	
	public Location getLocation(int i) {
		return locations.get(i);
	}
	
	public Location getLastLocation() {
		return getLocation(size() - 1);
	}
	
	public int size() {return images.size();}
	
	public static YUYVImageList fromString(String src) {
		YUYVImageList result = new YUYVImageList();
		for (String imgStr: src.split("\n")) {
			String[] parts = imgStr.split(";");
			result.locations.add(new Location(parts[0]));
			result.images.add(YUYVImage.fromString(parts[1]));
		}
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < size(); ++i) {
			result.append(locations.get(i).toString());
			result.append(";");
			result.append(images.get(i).toString());
			result.append("\n");
		}
		return result.toString();
	}
	
	@Override
	public int hashCode() {
		int sum = 0;
		for (YUYVImage img: images) {
			sum += img.hashCode();
		}
		return sum;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof YUYVImageList) {
			YUYVImageList that = (YUYVImageList)other;
			return this.images.equals(that.images);
		} else {
			return false;
		}
	}
}
