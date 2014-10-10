package lmsldemo;

import edu.hendrix.lmsl.Controller;
import edu.hendrix.lmsl.Layer;
import edu.hendrix.lmsl.FlagSetters;

public class RightAvoidLayer extends Layer<FlagName,ModeName> {
	public RightAvoidLayer() {
		super(ModeName.FORWARD, FlagName.class, ModeName.class);
		this.addAction(ModeName.GO_RIGHT, new RightAvoidSelector());
		this.addAction(ModeName.FORWARD, new RightAvoidSelector());
	}
	
	public static void main(String[] args) {
		FlagSetters<FlagName> s = new Sensors();
		Controller<FlagName,ModeName> c = new Controller<FlagName,ModeName>(new Actions(), s, new RightAvoidLayer());
		c.control();
	}
}
