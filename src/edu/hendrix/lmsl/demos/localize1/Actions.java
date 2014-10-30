package edu.hendrix.lmsl.demos.localize1;

import lejos.hardware.motor.Motor;
import edu.hendrix.lmsl.ActionMap;
import edu.hendrix.lmsl.Action;

public class Actions extends ActionMap<ModeName> {

	public Actions() {
		super(ModeName.class);
		
		this.bindAction(ModeName.FORWARD, new Action(){

			@Override
			public void begin() {
				Motor.A.setSpeed(400);
				Motor.D.setSpeed(400);
				Motor.A.forward();
				Motor.D.forward();
			}

			@Override
			public void end() {
			}});
		
		this.bindAction(ModeName.GO_LEFT, new Action(){

			@Override
			public void begin() {
				Motor.A.setSpeed(200);
				Motor.D.setSpeed(200);
				Motor.A.backward();
				Motor.D.forward();
			}

			@Override
			public void end() {
			}});
		
		this.bindAction(ModeName.GO_RIGHT, new Action(){

			@Override
			public void begin() {
				Motor.A.setSpeed(200);
				Motor.D.setSpeed(200);
				Motor.A.forward();
				Motor.D.backward();
			}

			@Override
			public void end() {
			}});
		
		this.bindAction(ModeName.STOP, new Action() {

			@Override
			public void begin() {
				Motor.A.stop(true);
				Motor.D.stop();
			}

			@Override
			public void end() {	
			}});
	}

}
