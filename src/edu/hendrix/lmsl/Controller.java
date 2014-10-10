package edu.hendrix.lmsl;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;

public class Controller<F extends Enum<F>, M extends Enum<M> & ModeNameInterface> {
	private Layer<F,M> top;
	private FlagSetters<F> flags;
	private ActionMap<M> actions;
	private boolean watching = false;
	
	public Controller(ActionMap<M> actions, FlagSetters<F> flags, Layer<F,M> top) {
		this.top = top;
		this.flags = flags;
		this.actions = actions;
	}
	
	public void watch() {
		watching = true;
	}
	
	public void control() {
		top.begin(actions);
		while (!Button.ESCAPE.isDown()) {
			FlagSet<F> status = flags.getFlags();
			if (watching) {
				Logger.instance().log(status.toString());
			}
			top.inProgress(status, actions);
		}
		top.end(actions);
		
		Motor.A.stop();
		Motor.B.stop();
		Motor.C.stop();
		Motor.D.stop();
		flags.closeAllSensors();
		LCD.clear();
		top.printStats();
	}
}
