package edu.hendrix.img.database.clunky;

import edu.hendrix.img.database.FeatureImageMatcher;
import edu.hendrix.img.database.YUYVImageList;
import edu.hendrix.img.features.FeatureSet;

public class FeatureImageMatchDemo2 extends ImageMatchDemo2<FeatureSet,FeatureImageMatcher> {

	@Override
	public FeatureImageMatcher makeMatcherFrom(YUYVImageList images) {
		return new FeatureImageMatcher(images);
	}

	public static void main(String[] args) {
		new FeatureImageMatchDemo2().run();
	}
}
