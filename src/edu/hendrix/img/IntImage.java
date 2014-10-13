package edu.hendrix.img;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import lejos.hardware.lcd.LCD;
import edu.hendrix.ev3webcam.BooleanImage;
import edu.hendrix.ev3webcam.YUV;
import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.lmsl.unsupervised.SupportsArithmetic;

public class IntImage implements SupportsArithmetic<IntImage> {
	private int[][] ints;
	
	public IntImage(int width, int height) {
		ints = new int[width][height];
	}
	
	public IntImage(int[][] ints) {
		this.ints = ints;
	}
	
	public IntImage(IntImage that) {
		this(that.getWidth(), that.getHeight());
		for (int x = 0; x < getWidth(); ++x) {
			for (int y = 0; y < getHeight(); ++y) {
				this.ints[x][y] = that.ints[x][y];
			}
		}
	}
	
	public final boolean inBounds(int x, int y) {
		return x >= 0 && y >= 0 && x < getWidth() && y < getHeight();
	}
	
	public boolean matchesSizeWith(IntImage other) {
		return this.getWidth() == other.getWidth() && this.getHeight() == other.getHeight();
	}
	
	public int mean() {
		int sum = 0;
		for (int x = 0; x < getWidth(); ++x) {
			for (int y = 0; y < getHeight(); ++y) {
				sum += ints[x][y];
			}
		}
		return sum / (getWidth() * getHeight());
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof IntImage) {
			IntImage that = (IntImage)other;
			if (this.getWidth() != that.getWidth() || this.getHeight() != that.getHeight()) {
				return false;
			} else {
				for (int x = 0; x < getWidth(); ++x) {
					for (int y = 0; y < getHeight(); ++y) {
						if (this.get(x,y) != that.get(x, y)) {
							return false;
						}
					}
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	public static IntImage toShrunkenGrayInts(YUYVImage src, int scale) {
		int width = src.getWidth() / scale;
		int height = src.getHeight() / scale;
		IntImage result = new IntImage(width, height);
		for (int x = 0; x < src.getWidth(); x += scale) {
			for (int y = 0; y < src.getHeight(); y += scale) {
				result.set(x/scale, y/scale, YUV.Y.get(src, x, y));
			}
		}
		return result;
	}
	
	public IntImage(YUYVImage input) {
		ints = new int[input.getWidth()][input.getHeight()];
		for (int x = 0; x < input.getWidth(); ++x) {
			for (int y = 0; y < input.getHeight(); ++y) {
				ints[x][y] = YUV.Y.get(input, x, y);
			}
		}
	}
	
	public static IntImage toGrayInts(BufferedImage src) {
		return new IntImage(src);
	}
	
	private IntImage(BufferedImage input) {
		ints = new int[input.getWidth()][input.getHeight()];
		for (int x = 0; x < input.getWidth(); ++x) {
			for (int y = 0; y < input.getHeight(); ++y) {
				ints[x][y] = gray(input.getRGB(x, y));
			}
		}
	}
	
	public BooleanImage threshold() {
		return threshold(mean());
	}
	
	public BooleanImage threshold(int thresh) {
		BooleanImage result = new BooleanImage(getWidth(), getHeight());
		for (int x = 0; x < getWidth(); ++x) {
			for (int y = 0; y < getHeight(); ++y) {
				result.set(x, y, ints[x][y] >= thresh);
			}
		}
		return result;
	}
	
	public int get(int x, int y) {
		return ints[x][y];
	}
	
	public int safeGet(int x, int y) {
		return inBounds(x, y) ? get(x, y) : 0;
	}
	
	public void set(int x, int y, int value) {
		ints[x][y] = Math.min(255, value);
	}
	
	@Override
	public void addTo(IntImage addend) {
		for (int x = 0; x < getWidth(); ++x) {
			for (int y = 0; y < getHeight(); ++y) {
				ints[x][y] += addend.get(x, y);
			}
		}
	}
	
	@Override
	public void subtractFrom(IntImage minuend) {
		for (int x = 0; x < getWidth(); ++x) {
			for (int y = 0; y < getHeight(); ++y) {
				ints[x][y] = minuend.get(x, y) - ints[x][y];
			}
		}
	}
	
	public void absSubtract(IntImage minuend) {
		for (int x = 0; x < getWidth(); ++x) {
			for (int y = 0; y < getHeight(); ++y) {
				ints[x][y] = Math.abs(minuend.get(x, y) - ints[x][y]);
			}
		}
	}
	
	@Override
	public void multBy(double scalar) {
		for (int x = 0; x < getWidth(); ++x) {
			for (int y = 0; y < getHeight(); ++y) {
				ints[x][y] *= scalar;
			}
		}
	}
	
	@Override
	public void divBy(int scalar) {
		for (int x = 0; x < getWidth(); ++x) {
			for (int y = 0; y < getHeight(); ++y) {
				ints[x][y] /= scalar;
			}
		}
	}
	
	public int getWidth() {
		return ints.length;
	}
	
	public int getHeight() {
		return ints[0].length;
	}
	
	public void displayLCD(int xStart, int yStart) {
		int mean = mean();
		for (int x = 0; x < getWidth(); ++x) {
			for (int y = 0; y < getHeight(); ++y) {
				LCD.setPixel(x + xStart, y + yStart, ints[x][y] < mean ? 0 : 1);
			}
		}
	}
	
	@Override
	// From: http://www.eternallyconfuzzled.com/tuts/algorithms/jsw_tut_hashing.aspx
	public int hashCode() {
		long h = 0;
		
		for (int x = 0; x < getWidth(); ++x) {
			for (int y = 0; y < getHeight(); ++y) {
				h ^= ( h << 5 ) + ( h >> 2 ) + ints[x][y];
			}
		}
		
		return (int)h;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int y = 0; y < getHeight(); ++y) {
			for (int x = 0; x < getWidth(); ++x) {
				result.append(get(x, y));
				result.append(" ");
			}
			result.append("\n");
		}
		return result.toString();
	}
	
	public static IntImage fromString(String s) {
		ArrayList<String[]> values = new ArrayList<String[]>();
		int width = 0;
		for (String row: s.split("\n")) {
			values.add(row.trim().split(" "));
			width = values.get(0).length;
		}
		int height = values.size();
		IntImage result = new IntImage(width, height);
		int y = 0;
		for (String[] row: values) {
			int x = 0;
			for (String entry: row) {
				try {
					int value = Integer.parseInt(entry);
					result.set(x, y, value);
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
					System.out.println("x: " + x + " y: " + y);
					System.exit(1);
				}
				x += 1;
			}
			y += 1;
		}
		return result;
	}
	
	public void drawLine(int x1, int y1, int x2, int y2) {
		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);

		int sx = (x1 < x2) ? 1 : -1;
		int sy = (y1 < y2) ? 1 : -1;

		int err = dx - dy;

		while (true) {
			ints[x1][y1] = 255;

		    if (x1 == x2 && y1 == y2) {
		        return;
		    }

		    int e2 = 2 * err;

		    if (e2 > -dy) {
		        err = err - dy;
		        x1 = x1 + sx;
		    }

		    if (e2 < dx) {
		        err = err + dx;
		        y1 = y1 + sy;
		    }
		}		
	}

	public BufferedImage toBufferedImage() {
		BufferedImage result = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < getWidth(); ++x) {
			for (int y = 0; y < getHeight(); ++y) {
				result.setRGB(x, y, extendGray(ints[x][y]));
			}
		}
		return result;
	}
	
	public static final int extendGray(int g) {
		return 0xff << 24 | g << 16 | g << 8 | g;
	}
	
	public static final int red(int rgb) {
		return (rgb & 0x00FF0000) >>> 16;
	}
	
	public static final int green(int rgb) {
		return (rgb & 0x0000FF00) >>> 8;
	}
	
	public static final int blue(int rgb) {
		return rgb & 0x000000FF;
	}
	
	public static final int gray(int rgb) {
		return (red(rgb) + green(rgb) + blue(rgb)) / 3;
	}

	@Override
	public IntImage getAddIdentity() {
		return new IntImage(getWidth(), getHeight());
	}

	@Override
	public IntImage duplicate() {
		return new IntImage(this);
	}
}
