package edu.hendrix.img;

import edu.hendrix.ev3webcam.BooleanImage;
import edu.hendrix.img.IntImage;

// Concept: Competitive thresholding
//
// Break the image into n x n blocks
//
// In each block, compute the single point corresponding to the 
// average of all the points, weighted by intensity.  The minimum
// intensity value is 1.

public class KeypointFinder {
	
	private int n;
	
	public KeypointFinder(int n) {
		this.n = n;
	}

	public BooleanImage process(IntImage input) {
		BooleanImage keypoints = new BooleanImage(input.getWidth(), input.getHeight());
		int divWidth = input.getWidth() / n;
		int divHeight = input.getHeight() / n;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				int totalX = 0, totalY = 0, totalValue = 0;
				for (int x = i * divWidth; x < (i+1) * divWidth; ++x) {
					for (int y = j * divHeight; y < (j+1) * divHeight; ++y) {
						int value = Math.max(1, input.get(x, y));
						totalValue += value;
						totalX += x * value;
						totalY += y * value;
						keypoints.set(x, y, false);
					}
				}

				int meanX = totalX / totalValue;
				int meanY = totalY / totalValue;
				keypoints.set(meanX, meanY, true);
			}
		}
		return keypoints;
	}
}
