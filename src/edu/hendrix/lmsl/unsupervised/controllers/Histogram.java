package edu.hendrix.lmsl.unsupervised.controllers;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Histogram<T> implements Iterable<Entry<T,Integer>> {
	private Map<T,Integer> counts;
	
	public Histogram() {
		counts = new LinkedHashMap<T,Integer>();
	}
	
	public void bump(T t) {
		if (!counts.containsKey(t)) {
			counts.put(t, 0);
		}
		counts.put(t, counts.get(t) + 1);
	}
	
	public int getCountFor(T t) {
		return counts.containsKey(t) ? counts.get(t) : 0;
	}
	
	public void setCountFor(T t, int target) {
		counts.put(t, target);
	}

	@Override
	public Iterator<Entry<T,Integer>> iterator() {
		return counts.entrySet().iterator();
	}
}
