package edu.hendrix.img.database;

import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.motor.NXTRegulatedMotor;

public class ButtonMover {
	private final static Key STOP = Button.ENTER, 
			LEFT = Button.LEFT,
			RIGHT = Button.RIGHT,
			GO = Button.UP;
	
	private NXTRegulatedMotor left, right;
	private int forwardSpeed, turnSpeed;
	private Key prev;

	public ButtonMover(NXTRegulatedMotor left, NXTRegulatedMotor right) {
		this(left, right, 600, 600);
	}
	
	public ButtonMover(NXTRegulatedMotor left, NXTRegulatedMotor right, int forwardSpeed, int turnSpeed) {
		this.left = left;
		this.right = right;
		this.forwardSpeed = forwardSpeed;
		this.turnSpeed = turnSpeed;
		prev = STOP;
	}
	
	public void stop() {
		left.stop(true);
		right.stop();
	}
	
	private void left() {
		setTurn();
		left.backward();
		right.forward();
		prev = LEFT;
	}
	
	private void right() {
		setTurn();
		left.forward();
		right.backward();
		prev = RIGHT;
	}
	
	private void forward() {
		setForward();
		left.forward();
		right.forward();
		prev = GO;
	}
	
	public void move() {
		if (LEFT.isDown()) {
			left();
		} else if (RIGHT.isDown()) {
			right();
		} else if (GO.isDown()) {
			forward();
		} else {
			stop();
			prev = STOP;
		}		
	}
	
	public void resume() {
		if (prev == LEFT) {
			left();
		} else if (prev == RIGHT) {
			right();
		} else if (prev == GO) {
			forward();
		} else {
			stop();
		}
	}
	
	private void setTurn() {
		left.setSpeed(turnSpeed);
		right.setSpeed(turnSpeed);
	}
	
	private void setForward() {
		left.setSpeed(forwardSpeed);
		right.setSpeed(forwardSpeed);
	}
}
