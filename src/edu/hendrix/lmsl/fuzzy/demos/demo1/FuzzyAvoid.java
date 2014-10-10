package edu.hendrix.lmsl.fuzzy.demos.demo1;

import lejos.hardware.motor.Motor;
import edu.hendrix.lmsl.FlagSet;
import edu.hendrix.lmsl.fuzzy.FuzzyMode;
import edu.hendrix.lmsl.fuzzy.FuzzyRule;

public class FuzzyAvoid extends FuzzyMode<FlagName,ModeName> {

	public FuzzyAvoid() {
		super(FlagName.class, ModeName.class);
		
		this.addRule(new FuzzyRule<FlagName>(Motor.A, -600, 600) {
			@Override
			protected double inferFrom(FlagSet<FlagName> flags) {
				return flags.getFuzzy(FlagName.CLEAR);
			}});
		
		this.addRule(new FuzzyRule<FlagName>(Motor.D, 0, 600) {
			@Override
			protected double inferFrom(FlagSet<FlagName> flags) {
				return flags.getFuzzy(FlagName.CLEAR);
			}});		
	}
	
	public static void main(String[] args) {
		new FuzzyAvoid().runController(new Sensors(), ModeName.FUZZY_AVOID);
	}
}
