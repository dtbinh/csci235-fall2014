package edu.hendrix.lmsl.demos.localize1;

import edu.hendrix.lmsl.Layer;

public class LocationDemoLayer extends Layer<FlagName,ModeName> {

	public LocationDemoLayer() {
		super(ModeName.STOP, new ButtonSelector(), ModeName.class);
	}
	
	public static void main(String[] args) {
		new LocationDemoLayer().runController(new Sensors(), new Actions(), false);
	}
}
