package edu.hendrix.lmsl;

import static org.junit.Assert.*;

import org.junit.Test;

public class SequenceTest {
	private enum Flag {ONE, TWO, THREE}
	private enum Mode implements ModeNameInterface {FOUR, FIVE, SIX;

	@Override
	public boolean continuePrevious() {
		// TODO Auto-generated method stub
		return false;
	}}
	
	private class TestSelector1 extends Selector<Flag,Mode> {
		public TestSelector1() {
			super(Flag.class);
			this.addSelection(Flag.ONE, Mode.FIVE);
			this.addSelection(Flag.TWO, Mode.SIX);
		}

		@Override
		protected Mode getDefaultMode() {
			return Mode.FOUR;
		}
		
	}
	
	private class TestLayer1 extends Layer<Flag,Mode> {
		public TestLayer1() {
			super(Mode.FOUR, Flag.class, Mode.class);
			this.addAction(Mode.FOUR, new TestSelector1());
			this.addAction(Mode.FIVE, new TestSelector1());
			this.addAction(Mode.SIX, new TestSelector1());
		}
	}

	@Test
	public void test() {
		Sequence<Flag,Mode> s = new Sequence<Flag,Mode>(new TestLayer1(), Flag.class, Mode.class, "[ONE]\n[ONE, TWO]\n[TWO, THREE]\n");
		assertTrue(s.matchesModes(ModeStack.getModeStacksFrom(Mode.class, "FIVE;\nFIVE;\nSIX;\n")));
	}

	@Test
	public void specTest() {
		SequenceSpecification<Flag,Mode> ss = new SequenceSpecification<Flag,Mode>(Mode.class);
		ss.addStep(new FlagSet<Flag>(Flag.class, "[ONE]"), new ModeStack<Mode>(Mode.class, "FIVE;"));
		ss.addStep(new FlagSet<Flag>(Flag.class, "[ONE, TWO]"), new ModeStack<Mode>(Mode.class, "FIVE;"));
		ss.addStep(new FlagSet<Flag>(Flag.class, "[TWO, THREE]"), new ModeStack<Mode>(Mode.class, "SIX;"));
		
		System.out.println(ss);
		
	}
}
