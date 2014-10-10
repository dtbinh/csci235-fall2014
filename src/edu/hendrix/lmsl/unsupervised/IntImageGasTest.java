package edu.hendrix.lmsl.unsupervised;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.hendrix.img.IntImage;
import edu.hendrix.lmsl.unsupervised.funcs.Euclidean;

public class IntImageGasTest {

	@Test
	public void testAddFirstNodes() {
		IntImage img1 = new IntImage(3, 2);
		IntImage img2 = new IntImage(3, 2);
		IntImage img3 = new IntImage(new int[][]{{1, 1}, {0, 1}, {0, 0}});
		assertEquals(img1.getWidth(), img3.getWidth());
		assertEquals(img1.getHeight(), img3.getHeight());
		
		IntImageGas gng = new IntImageGas(new Euclidean(), 10, 2);
		gng.trainOnce(img1);
		gng.trainOnce(img2);
		assertEquals(2, gng.numNodes());
		gng.trainOnce(img3);
		assertEquals(2, gng.numNodes());
		
		assertEquals(gng.toString(), new IntImageGas(gng.toString()).toString());
		assertEquals(gng, new IntImageGas(gng.toString()));
	}

}
