package edu.hendrix.lmsl.demos.gngdemo;

import java.io.IOException;

import edu.hendrix.lmsl.Layer;

public class GNGDriveLayer extends Layer<Flags,Modes> {
	public GNGDriveLayer() {
		super(Modes.STOP, new DriveSelector(), Modes.class);
	}
	
	public static void main(String[] args) throws IOException {
		new GNGDriveLayer().runController(new GNGSensors(), new Actions(), false);
	}
}
