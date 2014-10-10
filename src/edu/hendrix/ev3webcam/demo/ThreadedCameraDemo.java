package edu.hendrix.ev3webcam.demo;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import edu.hendrix.ev3webcam.GrabThread;

public class ThreadedCameraDemo {
	private EV3TouchSensor left, right;
	private float[] values;
	private GrabThread grabber;
	
	public ThreadedCameraDemo(GrabThread grabber) {
		this.grabber = grabber;
		left = new EV3TouchSensor(SensorPort.S1);
		right = new EV3TouchSensor(SensorPort.S2);
		values = new float[2];
	}
	
	public void run() {
		grabber.start();
		while (!grabber.isDead() && !grabber.isRunning());
		while (!Button.ESCAPE.isDown() && grabber.isRunning()) {
			left.fetchSample(values, 0);
			right.fetchSample(values, 1);
			
			if (values[0] < 0.5) {
				Motor.A.stop();
			} else {
				Motor.A.forward();
			}
			
			if (values[1] < 0.5) {
				Motor.B.stop();
			} else {
				Motor.B.forward();
			}
		}
		grabber.halt();
		left.close();
		right.close();
	}
}
