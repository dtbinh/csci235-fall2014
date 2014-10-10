package edu.hendrix.lmsl.unsupervised.controllers;

import java.util.LinkedHashMap;

import edu.hendrix.lmsl.Action;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class KeyThread extends Thread {
	private Key lastDown = Button.ENTER;
	private LinkedHashMap<Key,Action> key2Action;
	private NXTRegulatedMotor left = Motor.A;
	private NXTRegulatedMotor right = Motor.D;
	private boolean running = true;
	
	public KeyThread() {
		key2Action = new LinkedHashMap<Key,Action>();
		key2Action.put(Button.UP, new Up());
		key2Action.put(Button.DOWN, new Down());
		key2Action.put(Button.LEFT, new Left());
		key2Action.put(Button.RIGHT, new Right());
		key2Action.put(Button.ENTER, new Stopper());
		key2Action.put(Button.ESCAPE, new Stopper());
	}
	
	public void halt() {
		running = false;
	}
	
	public void performCurrentAction() {
		key2Action.get(lastDown).begin();
	}
	
	public Key getCurrentKey() {
		return lastDown;
	}
	
	public void stopRobot() {
		left.stop(true);
		right.stop();
	}

	@Override
	public void run() {
		while (running) {
			for (Key key: key2Action.keySet()) {
				if (key.isDown()) {lastDown = key;}
			}
			/*
			if (Button.LEFT.isDown()) {
				lastDown = Button.LEFT;
				left.setSpeed(120);
				right.setSpeed(120);
				left.backward();
				right.forward();
			} else if (Button.RIGHT.isDown()) {
				lastDown = Button.RIGHT;
				left.setSpeed(120);
				right.setSpeed(120);
				left.forward();
				right.backward();
			} else if (Button.UP.isDown()) {
				lastDown = Button.UP;
				left.setSpeed(360);
				right.setSpeed(360);
				left.forward();
				right.forward();
			} else if (Button.DOWN.isDown()) {
				lastDown = Button.DOWN;
				left.setSpeed(360);
				right.setSpeed(360);
				left.backward();
				right.backward();
			} else if (Button.ENTER.isDown() || Button.ESCAPE.isDown()) {
				lastDown = Button.ENTER;
				stopRobot();
			}
			*/
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				running = false;
			}
		}
		stopRobot();
	}
	
	abstract private class MyAction implements Action {
		private int speed;
		public MyAction(int speed) {this.speed = speed;}
		public void setSpeed() {
			left.setSpeed(speed);
			right.setSpeed(speed);
		}
		
		public void end() {}
	}
	
	private class Up extends MyAction {
		public Up() {
			super(360);
		}

		@Override
		public void begin() {
			setSpeed();
			left.forward();
			right.forward();
		}
	}
	
	private class Down extends MyAction {
		public Down() {
			super(360);
		}

		@Override
		public void begin() {
			setSpeed();
			left.backward();
			right.backward();
		}
	}
	
	private class Left extends MyAction {
		public Left() {
			super(120);
		}

		@Override
		public void begin() {
			setSpeed();
			left.backward();
			right.forward();
		}
	}
	
	private class Right extends MyAction {
		public Right() {
			super(120);
		}

		@Override
		public void begin() {
			setSpeed();
			left.forward();
			right.backward();
		}
	}
	
	private class Stopper implements Action {
		@Override
		public void begin() {
			stopRobot();
		}

		@Override
		public void end() {}
	}
}
