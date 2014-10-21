package edu.hendrix.lmsl.demos.localize1;

import lejos.hardware.motor.NXTRegulatedMotor;

abstract public class AbstractLocalizer {
	private NXTRegulatedMotor left, right;
	private int leftCount, rightCount;
	private double x, y, theta;
	private double changeLeft, changeRight;
	
	public final static double RADIUS = 2.36, BASE = 12.1;
	
	public static double counts2cm(int counts) {
		return RADIUS * Math.toRadians(counts);
	}
	
	public AbstractLocalizer(NXTRegulatedMotor left, NXTRegulatedMotor right) {
		this.left = left;
		this.right = right;
	}
	
	public void update() {
		int leftUpdate = left.getTachoCount();
		int rightUpdate = right.getTachoCount();
		changeLeft = counts2cm(leftUpdate - leftCount);
		changeRight = counts2cm(rightUpdate - rightCount);
		leftCount = leftUpdate;
		rightCount = rightUpdate;
		
		double r = (changeLeft + changeRight) / 2;
		theta = getUpdatedTheta();
		x += r * Math.cos(theta);
		y += r * Math.sin(theta);
	}
	
	abstract protected double getUpdatedTheta();
	
	public double getLeftChange() {return changeLeft;}
	public double getRightChange() {return changeRight;}
	
	public double getX() {return x;}
	public double getY() {return y;}
	public double getTheta() {return theta;}
	
	public String toString() {
		return String.format("(%6.2f,%6.2f,%6.2f)", x, y, theta);
	}
}
