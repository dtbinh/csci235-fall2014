package edu.hendrix.lmsl;

public class LoopBackSelector<F extends Enum<F>, M extends Enum<M> & ModeNameInterface> extends Selector<F,M> {
	private M soloMode;

	public LoopBackSelector(Class<F> flagType, M soloMode) {
		super(flagType);
		this.soloMode = soloMode;
	}

	@Override
	protected M getDefaultMode() {
		return soloMode;
	}

}
