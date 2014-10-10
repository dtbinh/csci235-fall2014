package edu.hendrix.lmsl.fuzzy;

import edu.hendrix.lmsl.FlagSet;
import lejos.hardware.motor.NXTRegulatedMotor;

abstract public class FuzzyRule<F extends Enum<F>> {
	
	private NXTRegulatedMotor motor;
	private int startSpeed, endSpeed;
	
	public FuzzyRule(NXTRegulatedMotor motor, int startSpeed, int endSpeed) {
		this.motor = motor;
		this.startSpeed = startSpeed;
		this.endSpeed = endSpeed;
	}
	
	public void activateMotor(FlagSet<F> flags) {
		setMotor(motor, defuzzify(inferFrom(flags), startSpeed, endSpeed));
	}
	
	public final static double and(double x, double y) {
		return x < y ? x : y;
	}
	
	public final static double or(double x, double y) {
		return x < y ? y : x;
	}
	
	public final static double not(double x) {
		return 1.0 - x;
	}
	
	public final static double mean(double... xs) {
		if (xs.length == 0) {return 0.5;}
		double total = 0.0;
		for (double x: xs) {total += x;}
		return total / xs.length;
	}
	
	abstract protected double inferFrom(FlagSet<F> flags);
	
	protected final static int defuzzify(double fuzzy, int speed0, int speed1) {
		if (speed0 > speed1) {
			return defuzzify(not(fuzzy), speed1, speed0);
		} else {
			return (int)(speed0 + fuzzy * (speed1 - speed0));
		}
	}
	
	protected final static void setMotor(NXTRegulatedMotor motor, int speed) {
		if (speed == 0) {
			motor.stop(true);
		} else {
			motor.setSpeed(speed);
			if (speed < 0) {
				motor.backward();
			} else {
				motor.forward();
			}
		}
	}
}
