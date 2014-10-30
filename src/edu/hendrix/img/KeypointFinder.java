package edu.hendrix.img;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import edu.hendrix.ev3webcam.Point;
import edu.hendrix.img.IntImage;

// Concept: Competitive thresholding
//
// Break the image into n x m blocks
//
// In each block, compute the single point corresponding to the 
// average of all the points, weighted by intensity.  The minimum
// intensity value is 1.  The value of the single point is the
// maximum value of its block.

public class KeypointFinder implements Iterable<Entry<Point,Integer>> {
	private IntImage keypointImage;
	private Map<Point,Integer> keypoints;
	
	public KeypointFinder(int n, int m, IntImage input) {
		keypoints = new LinkedHashMap<Point,Integer>();
		keypointImage = new IntImage(input.getWidth(), input.getHeight());
		
		int divWidth = input.getWidth() / n;
		int divHeight = input.getHeight() / m;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				int totalX = 0, totalY = 0, totalValue = 0, maxValue = 0;
				for (int x = i * divWidth; x < (i+1) * divWidth; ++x) {
					for (int y = j * divHeight; y < (j+1) * divHeight; ++y) {
						int value = Math.max(1, input.get(x, y));
						maxValue = Math.max(maxValue, value);
						totalValue += value;
						totalX += x * value;
						totalY += y * value;
						keypointImage.set(x, y, 0);
					}
				}

				int meanX = totalX / totalValue;
				int meanY = totalY / totalValue;
				keypointImage.set(meanX, meanY, maxValue);
				keypoints.put(new Point(meanX, meanY), maxValue);
			}
		}
	}

	@Override
	public Iterator<Entry<Point, Integer>> iterator() {
		return keypoints.entrySet().iterator();
	}
	
	public IntImage getKeypointImage() {
		return keypointImage;
	}
}
