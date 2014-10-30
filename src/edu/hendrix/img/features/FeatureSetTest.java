package edu.hendrix.img.features;

import static org.junit.Assert.*;

import org.junit.Test;

public class FeatureSetTest {

	@Test
	public void test() {
		FeatureSet target = FeatureSet.fromString("[{1, 2, 3}:2;{5, 8, 12}:4;]");
		FeatureSet regen = FeatureSet.fromString(target.toString());
		assertEquals(target.toString(), regen.toString());
		assertEquals(target, regen);
		
		FeatureSet target2 = FeatureSet.fromString("[{1, 2, 3}:2;{5, 8, 12}:3;]");
		assertNotEquals(target, target2);

		FeatureSet target3 = FeatureSet.fromString("[{1, 2, 3}:2;{5, 9, 12}:4;]");
		assertNotEquals(target, target3);
	}

}
