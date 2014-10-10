package edu.hendrix.lmsl;

import java.util.EnumSet;

abstract public class AbstractLayer<F extends Enum<F>, M extends Enum<M> & ModeNameInterface> {
	public void printStats() {
		printStats(0);
	}
	
	abstract public void begin(ActionMap<M> actions);
	
	abstract public void inProgress(FlagSet<F> flagsUp, ActionMap<M> actions);
	
	abstract public void end(ActionMap<M> actions);
	
	abstract public ModeStack<M> getCurrentModeStack();
	
	abstract public EnumSet<M> getAllTargetModes();
	
	abstract public void printStats(int spaces);
}
