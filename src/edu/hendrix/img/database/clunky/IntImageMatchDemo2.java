package edu.hendrix.img.database.clunky;

import edu.hendrix.img.IntImage;
import edu.hendrix.img.database.IntImageMatcher;
import edu.hendrix.img.database.YUYVImageList;

public class IntImageMatchDemo2 extends ImageMatchDemo2<IntImage,IntImageMatcher> {

	@Override
	public IntImageMatcher makeMatcherFrom(YUYVImageList images) {
		return new IntImageMatcher(images);
	}

	public static void main(String[] args) {
		new IntImageMatchDemo2().run();
	}
}
