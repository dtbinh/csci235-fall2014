package edu.hendrix.lmsl.demos.localize1;

import lejos.hardware.lcd.LCD;

public class DotLine {
	private int[] xs, ys;
	
	public DotLine(int xStart, int yStart, double angle, int dist) {
		xs = new int[dist];
		ys = new int[dist];
		for (int i = 0; i < dist; ++i) {
			xs[i] = xStart + (int)(i * Math.sin(angle));
			ys[i] = yStart + (int)(i * -Math.cos(angle));
		}
	}
	
	public void render() {
		plot(1);
	}
	
	public void erase() {
		plot(0);
	}
	
	public int size() {return xs.length;}
	
	public void plot(int color) {
		for (int i = 0; i < size(); ++i) {
			dot(xs[i], ys[i], color);
		}
	}
	
	public static void dot(int x, int y, int color) {
		LCD.setPixel(x, y, color);
		LCD.setPixel(x-1, y-1, color);
		LCD.setPixel(x-1, y+1, color);
		LCD.setPixel(x+1, y+1, color);
		LCD.setPixel(x+1, y-1, color);
		LCD.setPixel(x, y+1, color);
		LCD.setPixel(x, y-1, color);
		LCD.setPixel(x+1, y, color);
		LCD.setPixel(x-1, y, color);
	}
}
