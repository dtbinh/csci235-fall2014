package edu.hendrix.lmsl.fuzzy.demos.demo2;

import java.io.IOException;

import edu.hendrix.ev3webcam.Webcam;
import edu.hendrix.lmsl.fuzzy.FuzzyFlagSet;
import edu.hendrix.lmsl.fuzzy.FuzzyFlagSetter;
import edu.hendrix.lmsl.fuzzy.FuzzyFlagSetters;

public class Sensors extends FuzzyFlagSetters<FlagName> {
	private MotionFinder finder;

	public Sensors() throws IOException {
		super(FlagName.class);
		finder = new MotionFinder();
		
		this.addFuzzySetter(new FuzzyFlagSetter<FlagName>() {

			@Override
			public void pollSensor(FuzzyFlagSet<FlagName> flags) {
				finder.grab();
				if (finder.hasMotion()) {
					int x = finder.getMotionX();
					if (x < 0) {
						flags.set(FlagName.RIGHT, 0);
						flags.set(FlagName.LEFT, (double)x / finder.getMinX());
					} else {
						flags.set(FlagName.RIGHT, (double)x / finder.getMaxX());
						flags.set(FlagName.LEFT, 0);
					}
				}
			}

			@Override
			public void close() {
				try {
					Webcam.end();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}});
	}

	@Override
	public FuzzyFlagSet<FlagName> makeFlagSet() {
		return new FuzzyFlagSet<FlagName>(FlagName.class);
	}	
}
