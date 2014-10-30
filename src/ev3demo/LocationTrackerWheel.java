package ev3demo;

public class LocationTrackerWheel {
	private int leftCount, rightCount;
	private double x, y, theta;
	
	public final static double RADIUS = 2.36, BASE = 12.1;
	
	public LocationTrackerWheel() {
		leftCount = rightCount = 0;
		theta = 0;
	}
	
	public static double counts2cm(int counts) {
		return RADIUS * Math.toRadians(counts);
	}
	
	public void updatedCount(int left, int right) {
		double changeL = counts2cm(left - leftCount);
		double changeR = counts2cm(right - rightCount);
		leftCount = left;
		rightCount = right;
		
		double r = (changeR + changeL) / 2;
		double th = (changeR - changeL) / BASE;
		
		theta += th;
		x += r * Math.cos(theta);
		y += r * Math.sin(theta);
	}
	
	public double getX() {return x;}
	public double getY() {return y;}
	public double getTheta() {return theta;}
	
	public String toString() {
		return String.format("(%6.2f,%6.2f,%6.2f)", x, y, theta);
	}
}
