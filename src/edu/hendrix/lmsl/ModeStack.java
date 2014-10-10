package edu.hendrix.lmsl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.EnumSet;

public class ModeStack<M extends Enum<M> & ModeNameInterface> {
	private ArrayList<M> modes;
	
	public ModeStack() {
		modes = new ArrayList<M>();
	}
	
	@SafeVarargs
	public ModeStack(M...ms) {
		this();
		for (M m: ms) {addToTop(m);}
	}
	
	public ModeStack(Class<M> modeType, String src) {
		this();
		for (String modeName: src.split(";")) {
			addToTop(Enum.valueOf(modeType, modeName));
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof ModeStack) {
			@SuppressWarnings("unchecked")
			ModeStack<M> that = (ModeStack<M>)other;
			return this.modes.equals(that.modes);
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < numModes(); ++i) {
			result.append(nthFromBottom(i));
			result.append(";");
		}
		return result.toString();
	}
	
	public void addToTop(M mode) {
		modes.add(mode);
	}
	
	public int numModes() {return modes.size();}
	
	public M nthFromTop(int n) {
		return modes.get(modes.size() - n - 1);
	}
	
	public M nthFromBottom(int n) {
		return modes.get(n);
	}
	
	public EnumSet<M> modesInStack(Class<M> modeType) {
		EnumSet<M> result = EnumSet.noneOf(modeType);
		for (M mode: modes) {
			result.add(mode);
		}
		return result;
	}
	
	public static <M extends Enum<M> & ModeNameInterface> ArrayList<ModeStack<M>> getModeStacksFrom(Class<M> modeType, File f) throws FileNotFoundException {
		return getModeStacksFrom(modeType, Util.fileToString(f));
	}

	public static <M extends Enum<M> & ModeNameInterface> ArrayList<ModeStack<M>> getModeStacksFrom(Class<M> modeType, String src) {
		ArrayList<ModeStack<M>> result = new ArrayList<ModeStack<M>>();
		for (String line: src.split("\n")) {
			result.add(new ModeStack<M>(modeType, line));
		}
		return result;
	}

}
