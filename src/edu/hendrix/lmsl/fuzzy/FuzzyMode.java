package edu.hendrix.lmsl.fuzzy;

import java.util.ArrayList;
import java.util.EnumSet;

import lejos.hardware.motor.Motor;
import edu.hendrix.lmsl.AbstractLayer;
import edu.hendrix.lmsl.ActionMap;
import edu.hendrix.lmsl.FlagSet;
import edu.hendrix.lmsl.Layer;
import edu.hendrix.lmsl.ModeNameInterface;
import edu.hendrix.lmsl.ModeStack;

public class FuzzyMode<F extends Enum<F>, M extends Enum<M> & ModeNameInterface> extends AbstractLayer<F,M> {
	private ArrayList<FuzzyRule<F>> rules;
	private Class<F> flagType;
	private Class<M> modeType;
	
	public FuzzyMode(Class<F> flagType, Class<M> modeType) {
		this.rules = new ArrayList<FuzzyRule<F>>();
		this.flagType = flagType;
		this.modeType = modeType;
	}
	
	public void addRule(FuzzyRule<F> rule) {
		rules.add(rule);
	}

	@Override
	public void begin(ActionMap<M> actions) {}

	@Override
	public void end(ActionMap<M> actions) {
		Motor.A.stop(true);
		Motor.B.stop(true);
		Motor.C.stop(true);
		Motor.D.stop(true);
	}

	@Override
	public void inProgress(FlagSet<F> flagsUp, ActionMap<M> actions) {
		for (FuzzyRule<F> rule: rules) {
			rule.activateMotor(flagsUp);
		}
	}

	@Override
	public ModeStack<M> getCurrentModeStack() {
		return new ModeStack<M>();
	}

	@Override
	public EnumSet<M> getAllTargetModes() {
		return EnumSet.noneOf(modeType);
	}

	@Override
	public void printStats(int spaces) {
		// TODO Auto-generated method stub
		
	}
	
	public void runController(FuzzyFlagSetters<F> flaggers, M modeTitle) {
		Layer<F,M> top = new Layer<F,M>(modeTitle, flagType, modeType);
		top.addLayer(modeTitle, this);
		top.runController(flaggers, new ActionMap<M>(modeType), true);
	}
}
