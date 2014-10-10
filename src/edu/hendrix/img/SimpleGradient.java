package edu.hendrix.img;

public class SimpleGradient implements Processor {
	
	@Override
	public IntImage process(IntImage input) {
		IntImage result = new IntImage(input.getWidth(), input.getHeight());
		for (int x = 0; x < input.getWidth(); ++x) {
			for (int y = 0; y < input.getHeight(); ++y) {
				int upDown = input.safeGet(x, y + 1) - input.safeGet(x, y - 1);
				int leftRight = input.safeGet(x + 1, y) - input.safeGet(x - 1, y);
				result.set(x, y, Math.abs(upDown) + Math.abs(leftRight));
			}
		}
		return result;
	}
}
