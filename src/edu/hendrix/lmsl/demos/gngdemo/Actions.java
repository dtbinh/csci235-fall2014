package edu.hendrix.lmsl.demos.gngdemo;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import edu.hendrix.lmsl.ActionMap;
import edu.hendrix.lmsl.Action;

public class Actions extends ActionMap<Modes> {
	
	private NXTRegulatedMotor left = Motor.A;
	private NXTRegulatedMotor right = Motor.D;

	public Actions() {
		super(Modes.class);
		
		this.bindAction(Modes.FORWARD, new Action(){

			@Override
			public void begin() {
				left.forward();
				right.forward();
			}

			@Override
			public void end() {
			}});
		
		this.bindAction(Modes.BACK, new Action(){

			@Override
			public void begin() {
				left.backward();
				right.backward();
			}

			@Override
			public void end() {
			}});
		
		this.bindAction(Modes.STOP, new Action(){

			@Override
			public void begin() {
				left.stop(true);
				right.stop();
			}

			@Override
			public void end() {
			}});
		
		this.bindAction(Modes.LEFT, new Action(){

			@Override
			public void begin() {
				left.backward();
				right.forward();
			}

			@Override
			public void end() {
			}});
		
		this.bindAction(Modes.RIGHT, new Action(){

			@Override
			public void begin() {
				left.forward();
				right.backward();
			}

			@Override
			public void end() {
			}});
	}

}
