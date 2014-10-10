package edu.hendrix.ev3webcam.demo;

import edu.hendrix.img.SobelGradient;

public class SobelEdgeDemo {
	public static void main(String[] args) {
		new ProcessorDemo(new SobelGradient()).run();
	}
}
