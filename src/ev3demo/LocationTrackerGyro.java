package ev3demo;

public class LocationTrackerGyro {
	private int leftCount, rightCount;
	private double x, y, theta;
	
	public final static double RADIUS = 2.36;
	
	public LocationTrackerGyro() {
		leftCount = rightCount = 0;
		theta = 0;
	}
	
	public static double counts2cm(int counts) {
		return RADIUS * Math.toRadians(counts);
	}
	
	public void updatedCount(int left, int right, double gyro) {
		double changeL = counts2cm(left - leftCount);
		double changeR = counts2cm(right - rightCount);
		leftCount = left;
		rightCount = right;
		
		double r = (changeL + changeR) / 2;
		
		theta = Math.toRadians(gyro);
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
