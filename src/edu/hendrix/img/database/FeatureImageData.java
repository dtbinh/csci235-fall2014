package edu.hendrix.img.database;

import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.img.features.FeatureSet;
import edu.hendrix.lmsl.demos.localize1.Location;

public class FeatureImageData extends ImageData<FeatureSet> {
	public FeatureImageData(YUYVImage src, Location where) {
		super(src, where);
	}

	@Override
	public FeatureSet process(YUYVImage src) {
		return new FeatureSet(src);
	}

	@Override
	protected double distanceTo(FeatureSet srcProc, FeatureSet otherProc) {
		return srcProc.distanceTo(otherProc);
	}

}
