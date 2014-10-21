package edu.hendrix.lmsl.demos.localize1;

import lejos.hardware.motor.NXTRegulatedMotor;

public class WheelLocalizer extends AbstractLocalizer {

	public WheelLocalizer(NXTRegulatedMotor left, NXTRegulatedMotor right) {
		super(left, right);
	}

	@Override
	protected double getUpdatedTheta() {
		double th = (getRightChange() - getLeftChange()) / BASE;
		return th + getTheta();
	}

}
