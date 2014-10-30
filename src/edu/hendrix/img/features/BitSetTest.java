package edu.hendrix.img.features;

import java.util.BitSet;

public class BitSetTest {
	public static void main(String[] args) {
		BitSet bits = new BitSet();
		bits.set(2, 4);
		bits.set(7, 10);
		System.out.println(bits);
	}
}
