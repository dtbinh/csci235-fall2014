package edu.hendrix.ev3webcam;

import java.awt.image.BufferedImage;
import java.io.IOException;

import lejos.hardware.lcd.LCD;

public class YUYVImage {
	private byte[] pix;
	private int width, height;
	
	YUYVImage(byte[] pix, int width, int height) {
		this.pix = pix;
		this.width = width;
		this.height = height;
	}
	
	public YUYVImage(int width, int height) {
		this(new byte[2*width*height], width, height);
	}
	
	public BufferedImage toBufferedImage() {
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int y = 0; y < getHeight(); ++y) {
			for (int x = 0; x < getWidth(); ++x) {
				int c = get(YUV.Y, x, y) - 16;
				int d = get(YUV.U, x, y) - 128;
				int e = get(YUV.V, x, y) - 128;
				int r = clamp((298*c + 409*e + 128) >> 8) << 16;
				int g = clamp((298*c - 100*d - 208*e + 128) >> 8) << 8;
				int b = clamp((298*c + 516*d + 128) >> 8);
				result.setRGB(x, y, 0xff000000 | r | g | b);
			}
		}
		return result;
	}
	
	public static int clamp(int value) {
		return Math.min(255, Math.max(0, value));
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append('{');
		result.append(width);
		result.append(',');
		result.append(height);
		result.append(':');
		for (byte p: pix) {
			result.append(p);
			result.append(',');
		}
		result.delete(result.length() - 1, result.length());
		result.append('}');
		return result.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof YUYVImage) {
			YUYVImage that = (YUYVImage)other;
			if (this.width != that.width || this.height != that.height) {
				return false;
			} else {
				for (int i = 0; i < this.pix.length; ++i) {
					if (this.pix[i] != that.pix[i]) {
						return false;
					}
				}
				return true;
			}
		} else {
			return false;
		}
	}
	
	public static YUYVImage fromString(String src) {
		src = src.trim();
		if (src.startsWith("{") && src.endsWith("}")) {
			src = src.substring(1, src.length() - 1);
			String[] parts = src.split(":");
			String[] dimensions = parts[0].split(",");
			YUYVImage result = new YUYVImage(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));
			String[] bytes = parts[1].split(",");
			for (int i = 0; i < bytes.length; ++i) {
				result.pix[i] = Byte.parseByte(bytes[i]);
			}
			return result;
		} else {
			throw new IllegalArgumentException("Input string in wrong format for YUYVImage");
		}
	}
	
	public static YUYVImage grab() throws IOException {
		return new YUYVImage(Webcam.grabFrame(), Webcam.getWidth(), Webcam.getHeight());
	}
	
	public int getNumPixels() {return pix.length / 2;}
	
	public int getWidth() {return width;}
	
	public int getHeight() {return height;}
	
	public int get(YUV type, int x, int y) {
		return type.get(this, x, y);
	}
	
	public YUVPixel get(int x, int y) {
		return new YUVPixel(this, x, y);
	}
	
	final int getValueAt(int index) {
		return pix[index] & 0xFF;
	}
	
	final int getPairBase(int x, int y) {
		return 2 * (y * width + (x - x % 2));
	}
	
	int getScaledX(int xScreen) {
		return xScreen * width / LCD.SCREEN_WIDTH;
	}
	
	int getScaledY(int yScreen) {
		return yScreen * height / LCD.SCREEN_HEIGHT;
	}
	
	int getScaledIndex(int x, int y) {
		int rowPart = getScaledY(y) * width * 2;
		int colPart = getScaledX(x) * 2;
		return rowPart + colPart;
	}
	
	public int getMeanY() {
		int total = 0;
		for (int i = 0; i < pix.length; i += 2) {
			total += getValueAt(i);
		}
		return total / getNumPixels();
	}
	
	public void displayLCD() {
		new BooleanImage(this).displayLCD();
	}
	
	public void displayScaled() {
		new BooleanImage(this).displayScaled();
	}
}
