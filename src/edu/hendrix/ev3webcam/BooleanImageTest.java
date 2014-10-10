package edu.hendrix.ev3webcam;

import static org.junit.Assert.*;

import org.junit.Test;

public class BooleanImageTest {

	@Test
	public void test() {
		BooleanImage img = new BooleanImage(4, 2);
		img.set(0, 0, true);
		img.set(3, 0, true);
		img.set(2, 1, true);
		assertEquals("*..*\n..*.\n", img.toString());
	}

}
