package edu.hendrix.lmsl;

import java.util.LinkedHashMap;

public class Histogram<T> extends SemiAbstractHistogram<T,LinkedHashMap<T,Integer>> {
	public Histogram() {
		super(new LinkedHashMap<T,Integer>());
	}
}
