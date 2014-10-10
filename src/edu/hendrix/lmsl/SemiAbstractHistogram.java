package edu.hendrix.lmsl;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class SemiAbstractHistogram<T, M extends Map<T,Integer>> implements Iterable<Entry<T,Integer>> {
	private M counts;
	
	protected SemiAbstractHistogram(M map) {
		counts = map;
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
	
	protected <P extends Map<T,Double>> void getPortions(P portions) {
		int total = getTotalCount();
		for (Entry<T,Integer> entry: this) {
			portions.put(entry.getKey(), (double)entry.getValue() / (double)total);
		}
	}
	
	public int getTotalCount() {
		int total = 0;
		for (int value: counts.values()) {
			total += value;
		}
		return total;
	}

	@Override
	public Iterator<Entry<T,Integer>> iterator() {
		return counts.entrySet().iterator();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof SemiAbstractHistogram<?,?>) {
			@SuppressWarnings("unchecked")
			SemiAbstractHistogram<T,M> that = (SemiAbstractHistogram<T,M>)other;
			return this.counts.equals(that.counts);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public String toString() {
		return counts.toString();
	}
}
