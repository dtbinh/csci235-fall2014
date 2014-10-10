package edu.hendrix.lmsl.unsupervised.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import edu.hendrix.img.IntImage;
import edu.hendrix.lmsl.Histogram;
import edu.hendrix.lmsl.unsupervised.IntImageGas;
import edu.hendrix.lmsl.unsupervised.funcs.Euclidean;

abstract public class AbstractGNGNodeMoves<R, F extends Enum<F>> {
	protected IntImageGas gng;
	private Map<Integer,R> node2move;
	private Histogram<Integer> node2hits;
	
	private final static String DELIMETER = "Z";
	
	abstract public R moveFromString(Class<F> enumType, String src);
	
	public AbstractGNGNodeMoves() {
		node2move = new TreeMap<Integer,R>();
		node2hits = new Histogram<Integer>();
	}
	
	public AbstractGNGNodeMoves(IntImageGas gng) {
		this();
		this.gng = new IntImageGas(gng);
	}
	
	public AbstractGNGNodeMoves(int lambda, double k) {
		this();
		gng = new IntImageGas(new Euclidean(), lambda, k);
	}
	
	public AbstractGNGNodeMoves(Class<F> flagType, String s) {
		this();
		s = s.replace("\r", "");
		String[] parts = s.split(DELIMETER);
		this.gng = new IntImageGas(parts[0].trim());
		
		String[] lines = parts[1].split("\n");
		for (String line: lines) {
			if (line.contains(":")) {
				String[] pair = line.split(":");
				int category = Integer.parseInt(pair[0]);
				R move = moveFromString(flagType, pair[1]);
				this.node2move.put(category, move);
				int count = Integer.parseInt(pair[2]);
				this.node2hits.setCountFor(category, count);
			}
		}
	}
	
	public int numNodes() {return gng.numNodes();}
	
	public int getLowestLabel() {
		return gng.getLowestCategoryNumber();
	}
	
	abstract public void update(F move, IntImage img);
	
	public void forceMoveChange(int node, R newMove) {
		if (newMove == null) {
			gng.removeSafetyFrom(node);
			node2move.remove(node);
		} else {
			addMoveFor(node, newMove);
		}
	}
	
	public void forceDeleteNode(int nodeNum) {
		gng.removeSafetyFrom(nodeNum);
		gng.deleteNode(nodeNum);
	}
	
	protected void addMoveFor(int node, R m) {
		node2move.put(node, m);
		gng.makeSafe(node);
	}
	
	protected void countWinner(int node) {
		node2hits.bump(node);
	}
	
	public boolean hasMoveFor(int node) {
		return node2move.containsKey(node);
	}
	
	public R getMoveFor(int node) {
		return node2move.get(node);
	}
	
	public void purgeMoveFreeNodes() {
		for (int node: gng.getAllCategories()) {
			if (!hasMoveFor(node)) {
				gng.deleteNode(node);
			}
		}
	}
	
	public int getHitCountFor(int node) {
		return node2hits.getCountFor(node);
	}
	
	public int getNodeFor(IntImage input) {
		return gng.getCategoryFor(gng.bestMatchFor(input));
	}
	
	public boolean hasNode(int node) {
		return gng.hasCategory(node);
	}
	
	public IntImage get(int node) {
		return gng.get(node);
	}
	
	public ArrayList<Integer> getNeighborsOf(int node) {
		return gng.getNeighborsOf(node);
	}
	
	public Set<Integer> getAllNodeNums() {
		return gng.getAllCategories();
	}
	
	public Iterable<IntImageGas.Neuron> allNeighborsOf(IntImageGas.Neuron node) {
		return gng.allNeighborsOf(node);
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(gng.toString());
		result.append(DELIMETER);
		result.append('\n');
		for (Map.Entry<Integer,R> n: node2move.entrySet()) {
			if (gng.hasCategory(n.getKey())) {
				result.append(n.getKey());
				result.append(':');
				result.append(n.getValue());
				result.append(':');
				result.append(node2hits.getCountFor(n.getKey()));
				result.append('\n');
			}
		}
		return result.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		// Terrible, but easy
		return toString().equals(other.toString());
	}
	
	@Override
	public int hashCode() {return toString().hashCode();}
	
	public void toFile(File f) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(f));
		pw.print(toString());
		pw.close();	
	}
	
	public IntImageGas getGNGCopy() {
		return new IntImageGas(gng);
	}
}
