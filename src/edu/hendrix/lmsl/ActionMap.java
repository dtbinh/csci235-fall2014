package edu.hendrix.lmsl;

import java.util.EnumMap;

public class ActionMap<M extends Enum<M> & ModeNameInterface> {
	private EnumMap<M,Action> map;
	
	public ActionMap(Class<M> type) {
		map = new EnumMap<M,Action>(type);
	}
	
	public void bindAction(M modeName, Action action) {
		map.put(modeName, action);
	}
	
	public boolean hasActionFor(M modeName) {
		return map.containsKey(modeName);
	}
	
	public void begin(M modeName) {
		if (hasActionFor(modeName)) {
			map.get(modeName).begin();
		} else {
			System.out.println("Starting " + modeName);
		}
	}
	
	public void end(M modeName) {
		if (hasActionFor(modeName)) {
			map.get(modeName).end();
		} else {
			System.out.println("Finishing " + modeName);
		}
	}
}
