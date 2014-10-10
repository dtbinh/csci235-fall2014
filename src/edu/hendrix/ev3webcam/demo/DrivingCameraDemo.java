package edu.hendrix.ev3webcam.demo;

import edu.hendrix.ev3webcam.BooleanImage;
import edu.hendrix.ev3webcam.YUYVImage;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

public class DrivingCameraDemo extends AbstractCameraDemo {
	private EV3TouchSensor left, right;
	private float[] values;
	
	public DrivingCameraDemo() {
		super();
		left = new EV3TouchSensor(SensorPort.S1);
		right = new EV3TouchSensor(SensorPort.S2);
		values = new float[2];
	}
	
	@Override
	public void run() {
		super.run();
		left.close();
		right.close();
	}

	@Override
	public void show(YUYVImage grabbed) {
		new BooleanImage(grabbed).displayLCD();
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

	@Override
	public void finish() {}
	
	public static void main(String[] args) {
		new DrivingCameraDemo().run();
	}
}
