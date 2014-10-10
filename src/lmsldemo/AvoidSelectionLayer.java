package lmsldemo;

import edu.hendrix.lmsl.Layer;

public class AvoidSelectionLayer extends Layer<FlagName,ModeName> {

	public AvoidSelectionLayer() {
		super(ModeName.AVOID_LEFT, FlagName.class, ModeName.class);
		this.addLayer(ModeName.AVOID_LEFT, new LeftAvoidLayer(), new DirectionSelector());
		this.addLayer(ModeName.AVOID_RIGHT, new RightAvoidLayer(), new DirectionSelector());
	}
	
	public static void main(String[] args) {
		new AvoidSelectionLayer().runController(new Sensors(), new Actions(), true);
	}
}
