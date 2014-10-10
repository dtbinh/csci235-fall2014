package edu.hendrix.lmsl;

import java.util.EnumMap;
import java.util.EnumSet;

abstract public class Selector<F extends Enum<F>, M extends Enum<M> & ModeNameInterface> {
	private EnumMap<F,M> flag2mode;
	
	public Selector(Class<F> flagType) {
		flag2mode = new EnumMap<F,M>(flagType);
	}
	
	public void addSelection(F flag, M mode) {
		flag2mode.put(flag, mode);
	}
	
	abstract protected M getDefaultMode();

	public M nextMode(FlagSet<F> flagsUp) {
		FlagSet<F> pertinentFlags = flagsUp.intersection(flag2mode.keySet());
		if (pertinentFlags.isEmpty()) {
			return getDefaultMode();
		} else {
			return flag2mode.get(pertinentFlags.iterator().next());
		}
	}
	
	public EnumSet<M> getTargetModes() {
		return EnumSet.copyOf(flag2mode.values());
	}
}
