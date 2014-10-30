package edu.hendrix.img.database;

import edu.hendrix.img.IntImage;

public class IntImageMatchLocalizer extends ImageMatchLocalizer<IntImage,IntImageMatcher> {

	@Override
	public IntImageMatcher makeMatcherFrom(YUYVImageList images) {
		return new IntImageMatcher(images);
	}

	public static void main(String[] args) {
		new IntImageMatchLocalizer().run();
	}
}
