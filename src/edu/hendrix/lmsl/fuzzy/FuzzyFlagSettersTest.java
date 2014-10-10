package edu.hendrix.lmsl.fuzzy;

import static org.junit.Assert.*;

import org.junit.Test;

public class FuzzyFlagSettersTest {
	public final static double MARGIN = 0.001;

	@Test
	public void testRising() {
		assertEquals(0.0, FuzzyFlagSetters.encodeRising(0, 1, 5), MARGIN);
		assertEquals(1.0, FuzzyFlagSetters.encodeRising(6, 1, 5), MARGIN);
		assertEquals(0.0, FuzzyFlagSetters.encodeRising(1, 1, 5), MARGIN);
		assertEquals(1.0, FuzzyFlagSetters.encodeRising(5, 1, 5), MARGIN);
		assertEquals(0.25, FuzzyFlagSetters.encodeRising(2, 1, 5), MARGIN);
		assertEquals(0.5, FuzzyFlagSetters.encodeRising(3, 1, 5), MARGIN);
		assertEquals(0.75, FuzzyFlagSetters.encodeRising(4, 1, 5), MARGIN);
	}
	
	@Test
	public void testFalling() {
		assertEquals(1.0, FuzzyFlagSetters.encodeFalling(0, 1, 5), MARGIN);
		assertEquals(0.0, FuzzyFlagSetters.encodeFalling(6, 1, 5), MARGIN);
		assertEquals(1.0, FuzzyFlagSetters.encodeFalling(1, 1, 5), MARGIN);
		assertEquals(0.0, FuzzyFlagSetters.encodeFalling(5, 1, 5), MARGIN);
		assertEquals(0.75, FuzzyFlagSetters.encodeFalling(2, 1, 5), MARGIN);
		assertEquals(0.5, FuzzyFlagSetters.encodeFalling(3, 1, 5), MARGIN);
		assertEquals(0.25, FuzzyFlagSetters.encodeFalling(4, 1, 5), MARGIN);
	}
	
	@Test
	public void testTriangle() {
		assertEquals(0.0, FuzzyFlagSetters.encodeTriangle(0, 1, 5, 9), MARGIN);
		assertEquals(0.0, FuzzyFlagSetters.encodeTriangle(10, 1, 5, 9), MARGIN);
		assertEquals(0.0, FuzzyFlagSetters.encodeTriangle(1, 1, 5, 9), MARGIN);
		assertEquals(0.0, FuzzyFlagSetters.encodeTriangle(9, 1, 5, 9), MARGIN);
		assertEquals(0.25, FuzzyFlagSetters.encodeTriangle(2, 1, 5, 9), MARGIN);
		assertEquals(0.5, FuzzyFlagSetters.encodeTriangle(3, 1, 5, 9), MARGIN);
		assertEquals(0.75, FuzzyFlagSetters.encodeTriangle(4, 1, 5, 9), MARGIN);
		assertEquals(1.0, FuzzyFlagSetters.encodeTriangle(5, 1, 5, 9), MARGIN);
		assertEquals(0.75, FuzzyFlagSetters.encodeTriangle(6, 1, 5, 9), MARGIN);
		assertEquals(0.5, FuzzyFlagSetters.encodeTriangle(7, 1, 5, 9), MARGIN);
		assertEquals(0.25, FuzzyFlagSetters.encodeTriangle(8, 1, 5, 9), MARGIN);
	}
	
	@Test
	public void testTrapezoid() {
		assertEquals(0.0, FuzzyFlagSetters.encodeTrapezoid(0, 1, 5, 6, 10), MARGIN);
		assertEquals(0.0, FuzzyFlagSetters.encodeTrapezoid(11, 1, 5, 6, 10), MARGIN);
		assertEquals(0.0, FuzzyFlagSetters.encodeTrapezoid(1, 1, 5, 6, 10), MARGIN);
		assertEquals(0.0, FuzzyFlagSetters.encodeTrapezoid(10, 1, 5, 6, 10), MARGIN);
		assertEquals(0.25, FuzzyFlagSetters.encodeTrapezoid(2, 1, 5, 6, 10), MARGIN);
		assertEquals(0.5, FuzzyFlagSetters.encodeTrapezoid(3, 1, 5, 6, 10), MARGIN);
		assertEquals(0.75, FuzzyFlagSetters.encodeTrapezoid(4, 1, 5, 6, 10), MARGIN);
		assertEquals(1.0, FuzzyFlagSetters.encodeTrapezoid(5, 1, 5, 6, 10), MARGIN);
		assertEquals(1.0, FuzzyFlagSetters.encodeTrapezoid(5.5, 1, 5, 6, 10), MARGIN);
		assertEquals(1.0, FuzzyFlagSetters.encodeTrapezoid(6, 1, 5, 6, 10), MARGIN);
		assertEquals(0.75, FuzzyFlagSetters.encodeTrapezoid(7, 1, 5, 6, 10), MARGIN);
		assertEquals(0.5, FuzzyFlagSetters.encodeTrapezoid(8, 1, 5, 6, 10), MARGIN);
		assertEquals(0.25, FuzzyFlagSetters.encodeTrapezoid(9, 1, 5, 6, 10), MARGIN);
	}
	

}
