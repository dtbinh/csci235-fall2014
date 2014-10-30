package edu.hendrix.img.database;

import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.img.IntImage;
import edu.hendrix.lmsl.demos.localize1.Location;

public class IntImageMatcher extends ImageMatcher<IntImage> {

	public IntImageMatcher(YUYVImageList images) {
		super(images);
	}

	@Override
	public ImageData<IntImage> makeImageData(YUYVImage img, Location where) {
		return new IntImageData(img, where);
	}

}
