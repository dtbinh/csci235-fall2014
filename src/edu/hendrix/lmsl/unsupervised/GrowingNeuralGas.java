package edu.hendrix.lmsl.unsupervised;

// Based on GNG-U
//
// Updates after each image is given to it.

import java.util.*;
import java.io.*;

abstract public class GrowingNeuralGas<T extends SupportsArithmetic<T>> {
	private UndirectedWeightedGraph<Neuron,Integer> nodes;
	private DistanceFunc<T> func;
	private int maxEdgeAge = 100, lambda, countdown;
	private double k;
	private double neighborRate = 0.0006, learningRate = 0.05, errorDecay = 0.0005, splitDecay = 0.5;
	private T first;
	private Neuron mostRecentWinner;
	private TreeSet<Integer> safeNodes;
	
	abstract protected T tFromString(String src);
	
	public GrowingNeuralGas(DistanceFunc<T> func, int lambda, double k) {
		nodes = new UndirectedWeightedGraph<Neuron,Integer>();
		this.func = func;
		this.lambda = this.countdown = lambda;
		this.k = k;
		mostRecentWinner = null;
		safeNodes = new TreeSet<Integer>();
	}
	
	private GrowingNeuralGas(DistanceFunc<T> func, String src, String[] params) {
		this(func, Integer.parseInt(params[1].trim()), Integer.parseInt(params[2]), Integer.parseInt(params[3]), Double.parseDouble(params[4]), Double.parseDouble(params[5]), Double.parseDouble(params[6]), Double.parseDouble(params[7]), Double.parseDouble(params[8]));
		findAndAddNodes(src);
		findAndAddEdges(params[0]);
	}
	
	private void findAndAddEdges(String adjacency) {
		for (String nodeEdges: adjacency.split(";")) {
			String[] parts = nodeEdges.split(":");
			if (parts.length == 2) {
				int node = Integer.parseInt(parts[0]);
				for (String target: parts[1].split(",")) {
					String[] edgeParts = target.split("@");
					int edgeNode = Integer.parseInt(edgeParts[0]);
					int edgeWeight = Integer.parseInt(edgeParts[1]);
					updateEdge(getNeuronFor(node), getNeuronFor(edgeNode), edgeWeight);
				}
			}
		}
	}

	private void findAndAddNodes(String src) {
		int start = src.indexOf('[');
		do {
			int labelStart = start + 1;
			start = src.indexOf(' ', labelStart);
			int label = Integer.parseInt(src.substring(labelStart, start));
			start += 1;
			int end = src.indexOf(']', start);
			GrowingNeuralGas<T>.Neuron n = nFromString(src.substring(start, end));
			addNodeWithLabel(n, label);
			start = src.indexOf('[', end);
		} while (start != -1);
	}
	
	private static String[] breakUp(String src) {
		src = src.replace("\r", "");
		String paramStr = src.substring(src.lastIndexOf(']') + 2).trim();
		return paramStr.split("\n");
	}
	
	public GrowingNeuralGas(DistanceFunc<T> func, String src) {
		this(func, src, breakUp(src));
	}
	
	public GrowingNeuralGas(DistanceFunc<T> func, int maxEdgeAge, int lambda, int countdown, double k, double neighborRate, double learningRate, double errorDecay, double splitDecay) {
		this(func, lambda, k);
		this.maxEdgeAge = maxEdgeAge;
		this.countdown = countdown;
		this.neighborRate = neighborRate;
		this.learningRate = learningRate;
		this.errorDecay = errorDecay;
		this.splitDecay = splitDecay;
	}
	
	protected void addNodeWithLabel(Neuron node, int label) {
		nodes.addNodeWithLabel(node, label);
	}
	
	protected void updateEdge(Neuron one, Neuron two, int weight) {
		nodes.updateEdge(one, two, weight);
	}
	
	public DistanceFunc<T> getDistanceFunction() {return func;}
	
	public void addAll(GrowingNeuralGas<T> that) {
		this.nodes.addAllTo(that.nodes);
	}
	
	public void makeSafe(int node) {
		safeNodes.add(node);
	}
	
	public void removeSafetyFrom(int node) {
		safeNodes.remove(node);
	}
	
	public boolean isSafe(int node) {
		return safeNodes.contains(node);
	}
	
	public boolean isSafe(Neuron node) {
		return isSafe(getCategoryFor(node));
	}
 	
	public boolean hasMostRecentWinner() {
		return mostRecentWinner != null;
	}
	
	public Neuron getMostRecentWinner() {
		return mostRecentWinner;
	}
	
	public int getMostRecentCategory() {
		return getCategoryFor(getMostRecentWinner());
	}
	
	public Neuron bestMatchFor(T target) {
		return findTop2(target).best;
	}
	
	public int bestCategoryFor(T target) {
		return getCategoryFor(bestMatchFor(target));
	}
	
	public int numNodes() {return nodes.size();}
	
	public Set<Integer> getAllCategories() {return nodes.getAllLabels();}
	
	public T get(int category) {
		return nodes.getNodeFor(category).getIdealInput();
	}
	
	public ArrayList<Integer> getNeighborsOf(int category) {
		return nodes.allNeighborLabelsOf(category);
	}
	
	public int getCategoryFor(Neuron n) {
		return nodes.getLabelFor(n);
	}
	
	public boolean hasCategory(int category) {
		return nodes.hasLabel(category);
	}
	
	public int getLowestCategoryNumber() {
		return nodes.getLowestLabel();
	}
	
	public Neuron getNeuronFor(int category) {
		return nodes.getNodeFor(category);
	}
	
	public long getDistanceTo(T img, int category) {
		return func.distance(get(category), img);
	}
	
	private Top2 findTop2(T input) {
		Top2 result = new Top2();
		for (Neuron candidate: nodes) {
			result.update(candidate, input);
		}
		if (result.best == null) {
			throw new IllegalStateException("No best!");
		}
		if (result.next == null) {
			throw new IllegalStateException("No next!");
		}
		mostRecentWinner = result.best;
		makeSafe(getCategoryFor(mostRecentWinner));
		return result;
	}
	
	// On next call to trainOnce(), a new node will be added
	public void forceNewNode() {
		countdown = 1;
	}
	
	public int forceSpecificNode(int nearbyNode, T target) {
		/*
		Neuron newNode = new Neuron(target);
		nodes.addNode(newNode);
		nodes.updateEdge(newNode, getNeuronFor(nearbyNode), 0);
		return getCategoryFor(newNode);
		*/
		Neuron parent = getNeuronFor(nearbyNode);
		Neuron newNode = new Neuron(new Neuron(target), parent);
		nodes.addNode(newNode);
		nodes.updateEdge(parent, newNode, 0);
		return getCategoryFor(newNode);
	}

	public void trainOnce(T sample) {
		if (first == null) {
			first = sample;
		} else if (numNodes() == 0) {
			nodes.updateEdge(new Neuron(first), new Neuron(sample), 0);
		} else if (numNodes() == 1) {
			throw new IllegalStateException("This should not happen!");
		} else {
			updateWinner(sample);
			countdown -= 1;
			if (countdown == 0) {
				countdown = lambda;
				addNewNode();
			}
			if (numNodes() > 2) {
				purgeNode();
			}
			reduceAllErrors();
		}
	}
	
	public void assertHas(Neuron n) {
		if (!nodes.hasNode(n)) {
			throw new IllegalStateException("Checked node is not present");
		}
	}
	
	private Top2 updateWinner(T input) {
		Top2 best = findTop2(input);
		assertHas(best.best);
		trainBest(input, best);
		assertHas(best.best);
		trainNeighbors(input, best);
		purgeOldEdges(best.best);
		return best;
	}
	
	private void trainBest(T input, Top2 best) {
		nodes.updateEdge(best.best, best.next, 0);
		best.best.updateError(best.bestDist, best.nextDist);
		func.train(best.best.getIdealInput(), input, learningRate);	
	}
	
	private void trainNeighbors(T input, Top2 best) {
		for (Neuron target: nodes.allNeighborsOf(best.best)) {
			func.train(target.getIdealInput(), input, neighborRate);
			if (target != best.next) {
				nodes.updateEdge(best.best, target, nodes.getWeight(best.best, target) + 1);
			}
		}
	}
	
	private void purgeOldEdges(Neuron node) {
		ArrayList<Neuron> deadEdges = new ArrayList<Neuron>();
		for (Neuron neighbor: nodes.allNeighborsOf(node)) {
			if (nodes.getWeight(node, neighbor) > maxEdgeAge) {
				deadEdges.add(neighbor);
			}
		}
		
		for (Neuron neighbor: deadEdges) {
			nodes.removeEdge(node, neighbor);
		}
		
		purgeNeighborlessNodes();
	}
	
	public void deleteNode(int nodeNum) {
		if (!isSafe(nodeNum)) {
			nodes.removeNode(nodes.getNodeFor(nodeNum));
		}
	}
	
	private void purgeNode() {
		double biggestError = mostErroneous(nodes).error;
		Neuron useless = leastUseful();
		if (!isSafe(useless) && biggestError / useless.utility > k) {
			nodes.removeNode(useless);
		}
	}
	
	private void purgeNeighborlessNodes() {
		ArrayList<Neuron> garbage = new ArrayList<Neuron>();
		for (Neuron node: nodes) {
			if (!nodes.hasNeighbors(node) && !isSafe(node)) {
				garbage.add(node);
			}
		}
		
		for (Neuron node: garbage) {
			nodes.removeNode(node);
		}
	}
	
	private void addNewNode() {
		Neuron worst = mostErroneous(nodes);
		Neuron other = mostErroneous(nodes.allNeighborsOf(worst));
		Neuron newNode = new Neuron(worst, other);
		nodes.addNode(newNode);
		nodes.updateEdge(worst, newNode, 0);
		nodes.updateEdge(other, newNode, 0);
		nodes.removeEdge(worst, other);
	}
	
	Neuron nFromString(String nStr) {
		int headerStop = nStr.indexOf('\n');
		String[] header = nStr.substring(0, headerStop).split("\\s+");
		String imgStr = nStr.substring(headerStop + 1);
		Neuron result = new Neuron(tFromString(imgStr), Double.parseDouble(header[0]), Double.parseDouble(header[1]));
		return result;
	}
	
	private Neuron mostErroneous(Iterable<Neuron> candidates) {
		Neuron worst = null;
		for (Neuron node: candidates) {
			if (worst == null || node.error > worst.error) {
				worst = node;
			}
		}
		return worst;
	}
	
	private Neuron leastUseful() {
		Neuron useless = null;
		for (Neuron node: nodes) {
			if (useless == null || useless.utility > node.utility) {
				useless = node;
			}
		}
		return useless;
	}
	
	private void reduceAllErrors() {
		for (Neuron node: nodes) {
			node.reduceError();
		}
	}
	
	public class Neuron {
		
		private Neuron(T t) {
			this(t, 0, 0);
		}
		
		Neuron(T t, double error, double utility) {
			refVector = t;
			this.error = error;
			this.utility = utility;
		}
		
		private Neuron(Neuron one, Neuron two) {
			refVector = func.centroidOf(one.refVector, two.refVector);
			error = (one.error + two.error) / 2;
			utility = Math.max(one.utility, two.utility);
			one.error *= splitDecay;
			two.error *= splitDecay;
			one.utility *= splitDecay;
			two.utility *= splitDecay;
		}
		
		private T refVector;
		private double error, utility;
		
		public T getIdealInput() {return refVector;}
		
		private void updateError(double closest, double nextClosest) {
			error += closest;
			utility += nextClosest - closest;
		}
		
		private void reduceError() {
			error -= errorDecay * error;
			utility -= errorDecay * utility; 
		}
		
		// NB: Neurons are stored in the graph BY REFERENCE.  This is because using their values
		// would require them to change hash table slots every time they learn.  That is pointless.
		// This is a rare case where the default hashCode() and equals() make sense!
		@Override
		public String toString() {
			StringBuilder result = new StringBuilder();
			result.append(error);
			result.append(" ");
			result.append(utility);
			result.append('\n');
			result.append(refVector);
			return result.toString();
		}
	}
	
	private class Top2 {
		private Neuron best, next;
		private double bestDist, nextDist;
		
		Top2() {
			best = next = null;
			bestDist = nextDist = Double.MAX_VALUE;
		}
		
		void update(Neuron candidate, T input) {
			double dist = func.distance(candidate.getIdealInput(), input);
			if (best == null) {
				best = candidate;
				bestDist = dist;
			} else if (next == null || dist < nextDist) {
				next = candidate;
				nextDist = dist;
				if (dist < bestDist) {
					next = best;
					nextDist = bestDist;
					best = candidate;
					bestDist = dist;
				}
			}
		}
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (Neuron n: nodes) {
			result.append('[');
			result.append(this.getCategoryFor(n));
			result.append(' ');
			result.append(n.toString());
			result.append("]\n");
		}
		result.append(nodes.getAdjacencyList());
		result.append("\n");
		result.append(maxEdgeAge);
		result.append("\n");
		result.append(lambda);
		result.append("\n");
		result.append(countdown);
		result.append("\n");
		result.append(k);
		result.append("\n");
		result.append(neighborRate);
		result.append("\n");
		result.append(learningRate);
		result.append("\n");
		result.append(errorDecay);
		result.append("\n");
		result.append(splitDecay);
		result.append("\n");
		
		return result.toString();
	}
	
	public void toFile(File save) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(save));
		pw.print(toString());
		pw.close();		
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof GrowingNeuralGas) {
			// I hate to do this, but...
			return this.toString().equals(other.toString());
		} else {
			return false;
		}
	}
	
	public Iterable<Neuron> allNeighborsOf(Neuron n) {
		return nodes.allNeighborsOf(n);
	}
	
	@Override
	public int hashCode() {return toString().hashCode();}
}
