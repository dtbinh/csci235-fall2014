package edu.hendrix.lmsl;

import static org.junit.Assert.*;

import org.junit.Test;

public class ModeStackTest {
	
	private enum Tester implements ModeNameInterface {
		ONE, TWO, THREE, FOUR, NO_CHANGE {public boolean continuePrevious() {return true;}};

		@Override
		public boolean continuePrevious() {
			return false;
		}
		
	}

	@Test
	public void test() {
		ModeStack<Tester> target = new ModeStack<Tester>();
		target.addToTop(Tester.ONE);
		assertEquals("ONE;", target.toString());
		target.addToTop(Tester.TWO);
		assertEquals("ONE;TWO;", target.toString());
		assertEquals(Tester.ONE, target.nthFromTop(1));
		assertEquals(Tester.TWO, target.nthFromTop(0));
		assertEquals(target, new ModeStack<Tester>(Tester.class, target.toString()));
	}

}
