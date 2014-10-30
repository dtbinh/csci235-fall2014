package edu.hendrix.lmsl.demos.localize1;

import lejos.hardware.lcd.LCD;

public class Location {
	private double x, y, theta;
	
	public Location(double x, double y, double theta) {
		this.x = x;
		this.y = y;
		setTheta(theta);
	}
	
	private void setTheta(double t) {
		theta = Math.asin(Math.sin(t));
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + "," + theta + ")";
	}
	
	public Location(String src) {
		src = src.substring(1, src.length() - 1);
		String[] parts = src.split(",");
		x = Double.parseDouble(parts[0]);
		y = Double.parseDouble(parts[1]);
		setTheta(Double.parseDouble(parts[2]));
	}
	
	public Location partialMeanWith(Location other, double otherPart) {
		double thisPart = 1.0 - otherPart;
		double x2 = this.x * thisPart + other.x * otherPart;
		double y2 = this.y * thisPart + other.y * otherPart;
		
		double thetaOffset = this.angleDifference(other) * otherPart;
		return new Location(x2, y2, this.theta + thetaOffset);
	}
	
	public double distanceTo(Location that) {
		return Math.sqrt(Math.pow(x - that.x, 2) + Math.pow(y - that.y, 2));
	}
	
	public double angleDifference(Location that) {
		return Math.atan2(Math.sin(this.theta - that.theta), Math.cos(this.theta - that.theta));
	}
	
	public void display(int row) {
		int deg = (int)(Math.toDegrees(theta)) % 360;
		LCD.drawString(String.format("%4.1f,%4.1f,%3d   ",x,y,deg), 0, row);
	}
	
	public double getX() {return x;}
	public double getY() {return y;}
	public double getTheta() {return theta;}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Location) {
			Location that = (Location)other;
			return this.x == that.x && this.y == that.y && this.theta == that.theta;
		} else {
			return false;
		}
	}
}
