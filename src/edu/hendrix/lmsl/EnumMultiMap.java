package edu.hendrix.lmsl;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

public class EnumMultiMap<F extends Enum<F>, M extends Enum<M>> implements Iterable<EnumMultiMap<F,M>.Entry> {
	private EnumMap<F,EnumSet<M>> multiMap;
	
	public EnumMultiMap(Class<F> flagType) {
		multiMap = new EnumMap<F,EnumSet<M>>(flagType);
	}
	
	public EnumMultiMap(EnumMultiMap<F,M> that) {
		this.multiMap = new EnumMap<F,EnumSet<M>>(that.multiMap);
		for (java.util.Map.Entry<F, EnumSet<M>> entry: multiMap.entrySet()) {
			this.multiMap.put(entry.getKey(), EnumSet.copyOf(entry.getValue()));
		}
	}
	
	public void addMapping(F key, M value) {
		if (multiMap.containsKey(key)) {
			multiMap.get(key).add(value);
		} else {
			multiMap.put(key, EnumSet.of(value));
		}
	}
	
	public boolean containsKey(F key) {
		return multiMap.containsKey(key);
	}
	
	public EnumSet<M> getMappingsFor(F key) {
		return EnumSet.copyOf(multiMap.get(key));
	}
	
	public Set<F> keySet() {
		return multiMap.keySet();
	}
	
	public EnumSet<M> values() {
		EnumSet<M> result = null;
		for (EnumSet<M> value: multiMap.values()) {
			if (result == null) {
				result = EnumSet.copyOf(value);
			} else {
				result.addAll(value);
			}
		}
		return result;
	}
	
	@Override
	public String toString() {
		return multiMap.toString();
	}
	
	@Override
	public int hashCode() {
		return multiMap.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof EnumMultiMap<?,?>) {
			@SuppressWarnings("unchecked")
			EnumMultiMap<F,M> that = (EnumMultiMap<F,M>)other;
			return this.multiMap.equals(that.multiMap);
		} else {
			return false;
		}
	}
	
	public class Entry {
		private F key;
		private M value;
		private Entry(F key, M value) {
			this.key = key;
			this.value = value;
		}
		
		public F getKey() {return key;}
		public M getValue() {return value;}
	}

	@Override
	public Iterator<EnumMultiMap<F,M>.Entry> iterator() {
		return new EMMIter();
	}
	
	private class EMMIter implements Iterator<EnumMultiMap<F,M>.Entry> {
		private Iterator<F> outerIter;
		private Iterator<M> innerIter;
		private F outerKey;
		
		public EMMIter() {
			outerIter = multiMap.keySet().iterator();
			changeKey();
		}
		
		@Override
		public boolean hasNext() {
			return innerIter.hasNext() || outerIter.hasNext();
		}
		
		private void changeKey() {
			outerKey = outerIter.next();
			innerIter = multiMap.get(outerKey).iterator();
		}
		
		@Override
		public EnumMultiMap<F,M>.Entry next() {
			if (!innerIter.hasNext()) {
				changeKey();
			}
			M value = innerIter.next();
			
			return new Entry(outerKey, value);
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException("Can't remove from an EnumMultiMap iterator");
		}
	}
}
