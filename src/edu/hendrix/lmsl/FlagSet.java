package edu.hendrix.lmsl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

public class FlagSet<F extends Enum<F>> implements Iterable<F> {
	private EnumSet<F> flags;
	private EnumMultiMap<F,F> excludedBy;
	private EnumMultiMap<F,F> excludes;
	
	public FlagSet(Class<F> flagType) {
		flags = EnumSet.noneOf(flagType);
		excludedBy = new EnumMultiMap<F,F>(flagType);
		excludes = new EnumMultiMap<F,F>(flagType);
	}
	
	@SafeVarargs
	public FlagSet(Class<F> flagType, F...startFlags) {
		this(flagType);
		for (F flag: startFlags) {
			add(flag);
		}
	}
	
	public FlagSet(FlagSet<F> that) {
		this.flags = EnumSet.copyOf(that.flags);
		this.excludedBy = new EnumMultiMap<F,F>(that.excludedBy);
		this.excludes = new EnumMultiMap<F,F>(that.excludes);
	}
	
	public FlagSet(Class<F> flagType, String src) {
		this(flagType);
		src = src.trim();
		if (src.charAt(0) != '[' || src.charAt(src.length() - 1) != ']') {
			throw new IllegalArgumentException("Bad FlagSet format: " + src);
		}
		for (String s: src.substring(1, src.length()-1).split(", ")) {
			add(Enum.valueOf(flagType, s));
		}
	}
	
	public FlagSet(Class<F> flagType, File f) throws FileNotFoundException {
		this(flagType, Util.fileToString(f));
	}

	public static <F extends Enum<F>> ArrayList<FlagSet<F>> getFlagSetsFrom(Class<F> flagType, File f) throws FileNotFoundException {
		return getFlagSetsFrom(flagType, Util.fileToString(f));
	}

	public static <F extends Enum<F>> ArrayList<FlagSet<F>> getFlagSetsFrom(Class<F> flagType, String src) {
		ArrayList<FlagSet<F>> result = new ArrayList<FlagSet<F>>();
		for (String line: src.split("\n")) {
			if (isFlagSetString(line)) {
				result.add(new FlagSet<F>(flagType, line));
			}
		}
		return result;
	}
	
	public static boolean isFlagSetString(String line) {
		line = line.trim();
		return (line.charAt(0) == '[' && line.charAt(line.length() - 1) == ']');
	}
	
	public static <F extends Enum<F>> ArrayList<FlagSet<F>> makeReducedFlagSets(ArrayList<FlagSet<F>> flagSets) {
		ArrayList<FlagSet<F>> reduced = new ArrayList<FlagSet<F>>();
		for (FlagSet<F> flagSet: flagSets) {
			if (reduced.size() == 0 || !reduced.get(reduced.size() - 1).equals(flagSet)) {
				reduced.add(flagSet);
			}
		}
		return reduced;
	}
		
	public void addExclusion(F excluder, F excluded) {
		excludedBy.addMapping(excluded, excluder);
		excludes.addMapping(excluder, excluded);
		
		if (flags.contains(excluded) && flags.contains(excluder)) {
			flags.remove(excluded);
		}
	}
	
	public boolean excluded(F flag) {
		if (excludedBy.containsKey(flag)) {
			EnumSet<F> intersection = excludedBy.getMappingsFor(flag);
			intersection.retainAll(flags);
			return !intersection.isEmpty();
		} else {
			return false;
		}
	}
	
	public void add(F flag) {
		if (!excluded(flag)) {
			flags.add(flag);
			if (excludes.containsKey(flag)) {
				flags.retainAll(EnumSet.complementOf(excludes.getMappingsFor(flag)));
			}
		}
	}
	
	public boolean contains(F flag) {
		return flags.contains(flag);
	}
	
	public double getFuzzy(F flag) {
		return contains(flag) ? 1.0 : 0.0;
	}
	
	public boolean isEmpty() {
		return flags.isEmpty();
	}

	public FlagSet<F> intersection(Set<F> that) {
		FlagSet<F> result = new FlagSet<F>(this);
		result.flags.retainAll(that);
		return result;
	}
	
	@Override
	public String toString() {
		return flags.toString();
	}
	
	@Override
	public int hashCode() {
		return flags.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof FlagSet) {
			@SuppressWarnings("unchecked")
			FlagSet<F> that = (FlagSet<F>)other;
			return this.flags.equals(that.flags);
		} else {
			return false;
		}
	}

	@Override
	public Iterator<F> iterator() {
		return flags.iterator();
	}
}
