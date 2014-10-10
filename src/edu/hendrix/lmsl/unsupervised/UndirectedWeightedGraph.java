package edu.hendrix.lmsl.unsupervised;

import java.util.*;

public class UndirectedWeightedGraph<T,W> implements Iterable<T> {
	private Map<T,Map<T, W>> weights;
	private TreeMap<Integer,T> nodeNums;
	private Map<T,Integer> numNodes;
	
	public UndirectedWeightedGraph() {
		weights = new LinkedHashMap<T,Map<T,W>>();
		nodeNums = new TreeMap<Integer,T>();
		numNodes = new HashMap<T,Integer>();
	}
	
	public void addAllTo(UndirectedWeightedGraph<T,W> src) {
		for (T node: src) {
			this.addNode(node);
			for (T other: src) {
				if (src.hasEdge(node, other)) {
					this.updateEdge(node, other, src.getWeight(node, other));
				}
			}
		}
	}
	
	public boolean equals(Object other) {
		if (other instanceof UndirectedWeightedGraph<?,?>) {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			UndirectedWeightedGraph that = (UndirectedWeightedGraph<T,W>)other;
			return this.weights.equals(that.weights);
		} else {
			return false;
		}
	}
	
	public boolean hasNode(T node) {
		return weights.containsKey(node);
	}
	
	public void addNode(T node) {
		addNodeWithLabel(node, getHighestLabel() + 1);
	}
	
	public void addNodeWithLabel(T node, int label) {
		if (hasLabel(label)) {
			throw new IllegalArgumentException(label + " is already in use");
		}
		weights.put(node, new LinkedHashMap<T,W>());
		nodeNums.put(label, node);
		numNodes.put(node, label);
	}
	
	public int getHighestLabel() {return nodeNums.isEmpty() ? -1 : nodeNums.lastKey();}
	public int getLowestLabel() {return nodeNums.firstKey();}
	public int getLabelFor(T node) {return numNodes.get(node);}
	public T getNodeFor(int label) {return nodeNums.get(label);}
	public boolean hasLabel(int label) {return nodeNums.containsKey(label);}
	
	public TreeSet<Integer> getAllLabels() {return new TreeSet<Integer>(nodeNums.keySet());}
	
	private void addIfAbsent(T node) {
		if (!hasNode(node)) {
			addNode(node);
		}
	}
	
	public void updateEdge(T one, T two, W weight) {
		addIfAbsent(one);
		addIfAbsent(two);
		weights.get(one).put(two, weight);
		weights.get(two).put(one, weight);
	}
	
	public boolean hasEdge(T one, T two) {
		return weights.get(one).containsKey(two);
	}
	
	public W getWeight(T one, T two) {
		return weights.get(one).get(two);
	}
	
	public W getWeightBetween(int label1, int label2) {
		return getWeight(getNodeFor(label1), getNodeFor(label2));
	}
	
	public void removeEdge(T one, T two) {
		weights.get(one).remove(two);
		weights.get(two).remove(one);
	}
	
	public void removeNode(T node) {
		for (T other: weights.get(node).keySet()) {
			weights.get(other).remove(node);
		}
		weights.remove(node);
		nodeNums.remove(numNodes.remove(node));
	}
	
	public int size() {return weights.size();}

	@Override
	public Iterator<T> iterator() {
		return weights.keySet().iterator();
	}
	
	public Iterable<T> allNeighborsOf(T node) {
		return weights.get(node).keySet();
	}
	
	public ArrayList<Integer> allNeighborLabelsOf(int label) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (T t: allNeighborsOf(getNodeFor(label))) {
			result.add(getLabelFor(t));
		}
		return result;
	}
	
	public boolean hasNeighbors(T node) {
		return weights.get(node).size() > 0;
	}
	
	public boolean hasNeighborLabel(int nodeLabel, int possibleNeighbor) {
		return allNeighborLabelsOf(nodeLabel).contains(possibleNeighbor);
	}
	
	public String getAdjacencyList() {
		StringBuilder result = new StringBuilder();
		for (int node: nodeNums.keySet()) {
			result.append(node);
			result.append(':');
			for (int neighbor: allNeighborLabelsOf(node)) {
				result.append(neighbor);
				result.append('@');
				result.append(getWeightBetween(node, neighbor));
				result.append(',');
			}
			if (result.charAt(result.length() - 1) == ',') {
				result.delete(result.length() - 1, result.length());
			}
			result.append(';');
		}
		if (result.length() > 0 && result.charAt(result.length() - 1) == ';') {
			result.delete(result.length() - 1, result.length());
		}
		return result.toString();
	}
}
