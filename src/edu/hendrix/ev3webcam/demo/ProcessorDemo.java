package edu.hendrix.ev3webcam.demo;

import edu.hendrix.ev3webcam.BooleanImage;
import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.img.IntImage;
import edu.hendrix.img.Processor;

public class ProcessorDemo extends AbstractCameraDemo {
	private Processor proc;
	
	public ProcessorDemo(Processor proc) {
		this.proc = proc;
	}

	@Override
	public void show(YUYVImage grabbed) {
		BooleanImage img = proc.process(new IntImage(grabbed)).threshold();
		img.displayLCD();
	}

	@Override
	public void finish() {}
}
