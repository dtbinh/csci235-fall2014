package edu.hendrix.lmsl.fuzzy.demos.demo2;

import java.io.IOException;

import lejos.hardware.motor.Motor;
import edu.hendrix.lmsl.FlagSet;
import edu.hendrix.lmsl.fuzzy.FuzzyMode;
import edu.hendrix.lmsl.fuzzy.FuzzyRule;

public class FuzzyLook extends FuzzyMode<FlagName,ModeName> {

	public FuzzyLook() {
		super(FlagName.class, ModeName.class);
		
		this.addRule(new FuzzyRule<FlagName>(Motor.A, -600, 600) {
			@Override
			protected double inferFrom(FlagSet<FlagName> flags) {
				return mean(not(flags.getFuzzy(FlagName.LEFT)), flags.getFuzzy(FlagName.RIGHT));
			}});
		
		this.addRule(new FuzzyRule<FlagName>(Motor.D, -600, 600) {
			@Override
			protected double inferFrom(FlagSet<FlagName> flags) {
				return mean(flags.getFuzzy(FlagName.LEFT), not(flags.getFuzzy(FlagName.RIGHT)));
			}});		
	}
	
	public static void main(String[] args) throws IOException {
		new FuzzyLook().runController(new Sensors(), ModeName.FUZZY_LOOK);
	}
}
