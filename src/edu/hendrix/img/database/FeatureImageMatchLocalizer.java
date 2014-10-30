package edu.hendrix.img.database;

import edu.hendrix.img.features.FeatureSet;

public class FeatureImageMatchLocalizer extends ImageMatchLocalizer<FeatureSet,FeatureImageMatcher>{

	@Override
	public FeatureImageMatcher makeMatcherFrom(YUYVImageList images) {
		return new FeatureImageMatcher(images);
	}

	public static void main(String[] args) {
		new FeatureImageMatchLocalizer().run();
	}
}
