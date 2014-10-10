package edu.hendrix.lmsl;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class FlagSetTest {
	private enum Flag {CLEAR, PICK_RIGHT}

	@Test
	public void test() {
		FlagSet<Flag> target = new FlagSet<Flag>(Flag.class, "[CLEAR, PICK_RIGHT]");
		assertEquals("[CLEAR, PICK_RIGHT]", target.toString());
	}

	@Test
	public void testArray() {
		String src = "[CLEAR, PICK_RIGHT]\n[CLEAR]\n[PICK_RIGHT]\n[CLEAR, PICK_RIGHT]\n";
		ArrayList<FlagSet<Flag>> flagsets = FlagSet.getFlagSetsFrom(Flag.class, src);
		StringBuilder rebuild = new StringBuilder();
		for (FlagSet<Flag> flags: flagsets) {
			rebuild.append(flags.toString() +"\n");
		}
		assertEquals(src, rebuild.toString());
	}
}
