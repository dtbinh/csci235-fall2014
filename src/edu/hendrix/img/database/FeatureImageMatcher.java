package edu.hendrix.img.database;

import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.img.features.FeatureSet;
import edu.hendrix.lmsl.demos.localize1.Location;

public class FeatureImageMatcher extends ImageMatcher<FeatureSet> {

	public FeatureImageMatcher(YUYVImageList images) {
		super(images);
	}

	@Override
	public ImageData<FeatureSet> makeImageData(YUYVImage img, Location where) {
		return new FeatureImageData(img, where);
	}

}
