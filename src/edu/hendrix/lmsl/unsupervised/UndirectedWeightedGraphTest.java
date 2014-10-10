package edu.hendrix.lmsl.unsupervised;

import static org.junit.Assert.*;

import org.junit.Test;

public class UndirectedWeightedGraphTest {

	@Test
	public void test() {
		UndirectedWeightedGraph<String,Integer> g = new UndirectedWeightedGraph<String,Integer>();
		g.updateEdge("A", "B", 5);
		assertEquals(1, g.getHighestLabel());
		assertEquals(0, g.getLowestLabel());
		g.updateEdge("A", "C", 10);
		assertEquals(2, g.getHighestLabel());
		assertEquals(0, g.getLowestLabel());
		assertTrue(g.hasEdge("A", "B"));
		assertTrue(g.hasEdge("A", "C"));
		assertTrue(g.hasEdge("B", "A"));
		assertTrue(g.hasEdge("C", "A"));
		assertFalse(g.hasEdge("B", "C"));
		assertFalse(g.hasEdge("C", "B"));
		
		g.updateEdge("A", "D", 15);
		assertEquals(3, g.getHighestLabel());
		assertEquals(0, g.getLowestLabel());
		assertEquals(0, g.getLabelFor("A"));
		assertEquals("A", g.getNodeFor(0));
		assertEquals(1, g.getLabelFor("B"));
		assertEquals("B", g.getNodeFor(1));
		assertEquals(2, g.getLabelFor("C"));
		assertEquals("C", g.getNodeFor(2));
		assertEquals(3, g.getLabelFor("D"));
		assertEquals("D", g.getNodeFor(3));
		
		assertEquals("0:1@5,2@10,3@15;1:0@5;2:0@10;3:0@15", g.getAdjacencyList());
		
		assertTrue(g.hasEdge("A", "D"));
		
		g.removeNode("D");
		assertFalse(g.hasEdge("A", "D"));
		assertFalse(g.hasNeighborLabel(0, 3));
		assertEquals(2, g.getHighestLabel());
		
		g.removeNode("A");
		assertEquals(1, g.getLowestLabel());
		assertEquals(2, g.getHighestLabel());
		
		assertEquals("1:;2:", g.getAdjacencyList());
	}

}
