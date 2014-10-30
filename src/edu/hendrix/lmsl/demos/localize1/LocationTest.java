package edu.hendrix.lmsl.demos.localize1;

import static org.junit.Assert.*;

import org.junit.Test;

public class LocationTest {
	
	public static final double ANGLE_DIFF_MAX = 0.0000000000001;
	
	public static void assertAngle(Location l1, Location l2) {
		assertEquals(l1.angleDifference(l2), 0, ANGLE_DIFF_MAX);
	}

	@Test
	public void test() {
		Location one = new Location(1, 2, 0);
		assertEquals(one, new Location(one.toString()));
		assertAngle(one, new Location(1, 2, Math.PI*2));
		Location two = new Location(2, 3, Math.PI/4);
		Location three = new Location(2, 3, 9*Math.PI/4);
		assertAngle(two, three);
		Location four = new Location(2, 3, 3*Math.PI/2);
		Location five = new Location(2, 3, -Math.PI/2);
		assertAngle(four, five);
		
		
	}

}
