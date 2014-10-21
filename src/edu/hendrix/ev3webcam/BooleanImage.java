package edu.hendrix.ev3webcam;

import java.util.ArrayList;
import java.util.BitSet;

import lejos.hardware.lcd.LCD;

public class BooleanImage {
	private BitSet values;
	private int width, height;
	
	public BooleanImage(int width, int height) {
		values = new BitSet(width * height);
		this.width = width;
		this.height = height;
	}
	
	public BooleanImage(BooleanImage that) {
		this(that.getWidth(), that.getHeight());
		this.values.or(that.values);
	}
	
	public BooleanImage(YUYVImage img) {
		this(img.getWidth(), img.getHeight());
		int mean = img.getMeanY();
		for (int x = 0; x < img.getWidth(); ++x) {
			for (int y = 0; y < img.getHeight(); ++y) {
				this.set(x,y, img.get(YUV.Y, x, y) > mean);
			}
		}
	}
	
	public int numValuesSet() {return values.cardinality();}
	
	public int xCentroid() {
		int total = 0;
		for (int x = 0; x < getWidth(); ++x) {
			for (int y = 0; y < getHeight(); ++y) {
				if (isOn(x, y)) {total += x;}
			}
		}
		return total / values.cardinality();
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int y = 0; y < getHeight(); ++y) {
			for (int x = 0; x < getWidth(); ++x) {
				result.append(isOn(x, y) ? '*' : '.');
			}
			result.append('\n');
		}
		return result.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		return this.toString().equals(other.toString());
	}
	
	@Override
	public int hashCode() {return toString().hashCode();}
	
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	
	public boolean inBounds(int x, int y) {return x >= 0 && y >= 0 && x < getWidth() && y < getHeight();}
	
	public boolean isOn(int x, int y) {return values.get(toIndex(x,y));}
	
	public void set(int x, int y, boolean isOn) {values.set(toIndex(x,y), isOn);}
	
	public void flip(int x, int y) {
		set(x, y, !isOn(x, y));
	}
	
	private final int toIndex(int x, int y) {
		return y * width + x;
	}
	
	private final Point fromIndex(int i) {
		return new Point(i % width, i / width);
	}
	
	public ArrayList<Point> getSetPoints() {
		ArrayList<Point> result = new ArrayList<Point>();
		for (int i = values.nextSetBit(0); i >= 0; i = values.nextSetBit(i+1)) {
			result.add(fromIndex(i));
		}
		return result;
	}
	
	public void flipColumn(int x) {
		for (int y = 0; y < getHeight(); ++y) {
			flip(x, y);
		}
	}
	
	public void flipRow(int y) {
		for (int x = 0; x < getWidth(); ++x) {
			flip(x, y);
		}
	}
	
	public void flipRectangle(int x, int y, int width, int height) {
		int xMin = x - width/2;
		int xMax = x + width/2;
		int yMin = y - height/2;
		int yMax = y + height/2;
		for (int x1 = xMin; x1 <= xMax; ++x1) {
			flip(x1, yMin);
			flip(x1, yMax);
		}
		for (int y1 = yMin; y1 <= yMax; ++y1) {
			flip(xMin, y1);
			flip(xMax, y1);
		}
	}
	
	public void displayLCD(int xStart, int yStart) {
		for (int y = 0; y < getHeight(); ++y) {
			for (int x = 0; x < getWidth(); ++x) {
				LCD.setPixel(x + xStart, y + yStart, isOn(x, y) ? 1 : 0);
			}
		}
	}
	
	public int numActiveNeighbors(int x, int y) {
		return countIfOn(x-1, y-1) + countIfOn(x, y-1) + countIfOn(x+1, y-1) 
				+ countIfOn(x-1, y) + countIfOn(x+1, y) 
				+ countIfOn(x-1, y+1) + countIfOn(x, y+1) + countIfOn(x+1, y+1);
	}
	
	private int countIfOn(int x, int y) {
		return inBounds(x, y) && isOn(x, y) ? 1 : 0;
	}
	
	public void displayLCD() {displayLCD(0, 0);}
	
	int getScaledX(int xScreen) {
		return xScreen * width / LCD.SCREEN_WIDTH;
	}
	
	int getScaledY(int yScreen) {
		return yScreen * height / LCD.SCREEN_HEIGHT;
	}
	
	int getScaledIndex(int x, int y) {
		int rowPart = getScaledY(y) * width;
		int colPart = getScaledX(x);
		return rowPart + colPart;
	}
	
	public void displayScaled() {
		for (int x = 0; x < LCD.SCREEN_WIDTH; ++x) {
			for (int y = 0; y < LCD.SCREEN_HEIGHT; ++y) {
				LCD.setPixel(x,  y, values.get(getScaledIndex(x, y)) ? 1 : 0);
			}
		}
	}
}
