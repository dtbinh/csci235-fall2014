package edu.hendrix.img.features;

import edu.hendrix.img.IntImage;

public class View {
	private IntImage src;
	private int xStart, yStart, width, height;
	
	public View(IntImage src, int xCenter, int yCenter, int width, int height) {
		this.src = src;
		this.xStart = xCenter - width/2;
		this.yStart = yCenter - height/2;
		this.width = width;
		this.height = height;
	}
	
	public int get(int x, int y) {
		return src.safeGet(x + xStart, y + yStart);
	}
	
	public boolean inBounds(int x, int y) {
		return x >= 0 && y >= 0 && x < width && y < height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
