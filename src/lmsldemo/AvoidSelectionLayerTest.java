package lmsldemo;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.hendrix.lmsl.ModeStack;
import edu.hendrix.lmsl.Sequence;

public class AvoidSelectionLayerTest {

	@Test
	public void test() {
		AvoidSelectionLayer layer = new AvoidSelectionLayer();
		Sequence<FlagName,ModeName> s = new Sequence<FlagName,ModeName>(layer, ModeName.class);
		s.add(new AvoidFlagSet(FlagName.CLEAR));
		assertEquals(new ModeStack<ModeName>(ModeName.FORWARD, ModeName.AVOID_LEFT), s.getModes(0));
		s.add(new AvoidFlagSet(FlagName.CLEAR, FlagName.BLOCKED));
		assertEquals(new ModeStack<ModeName>(ModeName.GO_LEFT, ModeName.AVOID_LEFT), s.getModes(1));
		s.add(new AvoidFlagSet(FlagName.CLEAR, FlagName.PICK_RIGHT));
		assertEquals(new ModeStack<ModeName>(ModeName.FORWARD, ModeName.AVOID_RIGHT), s.getModes(2));
		s.add(new AvoidFlagSet(FlagName.BLOCKED));
		assertEquals(new ModeStack<ModeName>(ModeName.GO_RIGHT, ModeName.AVOID_RIGHT), s.getModes(3));
	
		System.out.println("Missing: " + s.getMissingModes(ModeName.class));
		assertTrue(s.allModesCovered(ModeName.class));
	}

}
