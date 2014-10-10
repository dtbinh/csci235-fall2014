package edu.hendrix.img;

import edu.hendrix.ev3webcam.BooleanImage;

public class Eroder {
	private double thresh2mean;
	
	public Eroder(double thresh2mean) {
		this.thresh2mean = thresh2mean;
	}

	public BooleanImage process(IntImage input) {
		int threshold = input.mean();
		if (thresh2mean > 0.5) {
			threshold = threshold + (int)((255 - threshold) * (thresh2mean - 0.5) / 0.5);
		} else if (thresh2mean < 0.5) {
			threshold = (int)(threshold * thresh2mean / 0.5);
		}
		
		BooleanImage intermediate = new BooleanImage(input.getWidth(), input.getHeight());
		for (int y = 0; y < input.getHeight(); ++y) {
			for (int x = 0; x < input.getWidth(); ++x) {
				intermediate.set(x, y, input.get(x, y) >= threshold);
			}
		}
		
		BooleanImage result = new BooleanImage(input.getWidth(), input.getHeight());
		for (int y = 0; y < input.getHeight(); ++y) {
			for (int x = 0; x < input.getWidth(); ++x) {
				int neighborsOn = intermediate.numActiveNeighbors(x, y);
				result.set(x, y, 2 <= neighborsOn && neighborsOn <= 5);
			}
		}
		return result;
	}
}
