package lmsldemo;

import lejos.hardware.motor.Motor;
import edu.hendrix.lmsl.ActionMap;
import edu.hendrix.lmsl.Action;

public class Actions extends ActionMap<ModeName> {

	public Actions() {
		super(ModeName.class);
		
		this.bindAction(ModeName.FORWARD, new Action(){

			@Override
			public void begin() {
				Motor.A.forward();
				Motor.B.forward();
			}

			@Override
			public void end() {
			}});
		
		this.bindAction(ModeName.GO_LEFT, new Action(){

			@Override
			public void begin() {
				Motor.A.backward();
				Motor.B.forward();
			}

			@Override
			public void end() {
			}});
		
		this.bindAction(ModeName.GO_RIGHT, new Action(){

			@Override
			public void begin() {
				Motor.A.forward();
				Motor.B.backward();
			}

			@Override
			public void end() {
			}});
	}

}
