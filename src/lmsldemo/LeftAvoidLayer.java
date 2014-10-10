package lmsldemo;

import edu.hendrix.lmsl.Controller;
import edu.hendrix.lmsl.Layer;
import edu.hendrix.lmsl.FlagSetters;

public class LeftAvoidLayer extends Layer<FlagName,ModeName> {
	public LeftAvoidLayer() {
		super(ModeName.FORWARD, FlagName.class, ModeName.class);
		this.addAction(ModeName.GO_LEFT, new LeftAvoidSelector());
		this.addAction(ModeName.FORWARD, new LeftAvoidSelector());
	}
	
	public static void main(String[] args) {
		FlagSetters<FlagName> s = new Sensors();
		Controller<FlagName,ModeName> c = new Controller<FlagName,ModeName>(new Actions(), s, new LeftAvoidLayer());
		c.control();
	}
}
