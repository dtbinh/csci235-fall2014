package edu.hendrix.lmsl;

import java.util.EnumMap;
import java.util.Map.Entry;

public class EnumHistogram<T extends Enum<T>> extends SemiAbstractHistogram<T,EnumMap<T,Integer>> {
	private Class<T> enumType;
	private EnumMap<T,String> abbreviations;
	
	public EnumHistogram(Class<T> enumType) {
		super(new EnumMap<T,Integer>(enumType));
		this.enumType = enumType;
		abbreviations = getAbbreviations(enumType);
	}
	
	public EnumMap<T,Double> getPortions() {
		EnumMap<T,Double> result = new EnumMap<T,Double>(enumType);
		super.getPortions(result);
		return result;
	}
	
	public String toShortString() {
		StringBuilder result = new StringBuilder();
		result.append("{");
		for (Entry<T, Integer> entry: this) {
			result.append(abbreviations.get(entry.getKey()) + " " + entry.getValue() + ", ");
		}
		if (result.length() > 1) {
			result.delete(result.length() - 2, result.length());
		}
		result.append("}");
		return result.toString();
	}
	
	public static <T extends Enum<T>> EnumMap<T,String> getAbbreviations(Class<T> enumType) {
		EnumMap<T,String> result = new EnumMap<T,String>(enumType);
		for (T t: enumType.getEnumConstants()) {
			result.put(t, t.toString().substring(0,findMinDistinctPrefixSizeFor(t, enumType)));
		}		
		return result;
	}
	
	public static int minDistinctPrefixSize(String s1, String s2) {
		int target = Math.min(s1.length(), s2.length());
		int result = 1;
		while (result <= target && s1.charAt(result - 1) == s2.charAt(result - 1)) {
			result += 1;
		}
		return result;
	}
	
	public static <T extends Enum<T>> int findMinDistinctPrefixSizeFor(T candidate, Class<T> type) {
		int max = 0;
		for (T other: type.getEnumConstants()) {
			if (!other.equals(candidate)) {
				max = Math.max(max, minDistinctPrefixSize(candidate.toString(), other.toString()));
			}
		}
		return max;
	}
	
	public static <T extends Enum<T>> EnumHistogram<T> fromString(Class<T> enumType, String src) {
		EnumHistogram<T> result = new EnumHistogram<T>(enumType);
		String[] pairs = src.substring(1, src.length() - 1).split(", ");
		for (String pair: pairs) {
			String[] kv = pair.split("=");
			result.setCountFor(Enum.valueOf(enumType,kv[0]), Integer.parseInt(kv[1]));
		}
		return result;
	}
}
