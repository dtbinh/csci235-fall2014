package edu.hendrix.lmsl;

import lejos.hardware.Device;
import lejos.robotics.SampleProvider;

abstract public class EV3SensorSetter<F extends Enum<F>> implements FlagSetter<F> {
	private Device sensorDevice;
	private SampleProvider sensorSamples;
	private float[] sample;
	
	public <DSP extends Device & SampleProvider> EV3SensorSetter(DSP both) {
		this(both, both);
	}
	
	public EV3SensorSetter(Device sensorDevice, SampleProvider sensorSamples) {
		this.sensorDevice = sensorDevice;
		this.sensorSamples = sensorSamples;
		sample = new float[sensorSamples.sampleSize()];
	}
	
	public void pollSensor(FlagSet<F> flags) {
		sensorSamples.fetchSample(sample, 0);
		setFlags(sample, flags);
	}
	
	abstract public void setFlags(float[] sample, FlagSet<F> flags);
	
	public void close() {sensorDevice.close();}
}
