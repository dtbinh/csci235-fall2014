package edu.hendrix.ev3webcam;

import java.util.EnumMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YUVPixel {
	private EnumMap<YUV,Integer> values = new EnumMap<YUV,Integer>(YUV.class);
	private int hashCode;
	public final static Pattern format = Pattern.compile("YUV\\((\\d+)\\,(\\d+)\\,(\\d+)\\)");
	
	private void setHashCode() {
		hashCode = values.get(YUV.Y) << 16 + values.get(YUV.U) << 8 + values.get(YUV.V);
	}
	
	public YUVPixel(int y, int u, int v) {
		values.put(YUV.Y, y);
		values.put(YUV.U, u);
		values.put(YUV.V, v);
		setHashCode();
	}
	
	public YUVPixel(YUYVImage img, int x, int y) {
		for (YUV yuv: YUV.values()) {
			values.put(yuv, yuv.get(img, x, y));
		}
		setHashCode();
	}
	
	public YUVPixel(String src) {
		Matcher m = format.matcher(src);
		if (m.matches()) {
			for (int i = 0; i < YUV.values().length; ++i) {
				values.put(YUV.values()[i], Integer.parseInt(m.group(i+1)));
			}
			setHashCode();
		} else {
			throw new IllegalArgumentException(src + " is not a match");
		}
	}
	
	public YUVPixel(YUVPixel that) {
		this.values = new EnumMap<YUV,Integer>(that.values);
		this.hashCode = that.hashCode;
	}
	
	@Override
	public String toString() {return "YUV(" + values.get(YUV.Y) + "," + values.get(YUV.U) + "," + values.get(YUV.V) + ")";}
	
	@Override
	public int hashCode() {return hashCode;}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof YUVPixel) {
			YUVPixel that = (YUVPixel)other;
			return this.values.equals(that.values);
		} else {
			return false;
		}
	}
	
	public int get(YUV yuv) {return values.get(yuv);}
}
