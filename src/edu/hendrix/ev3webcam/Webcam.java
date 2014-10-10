package edu.hendrix.ev3webcam;

import java.io.IOException;

public class Webcam {
	static {System.loadLibrary("webcam");}

	private static int width, height;
	private static int frames;
	private static long start, duration;

	public static int getBufferSize() {return width * height * 2;}

	public static byte[] makeBuffer() {return new byte[getBufferSize()];}

	public static byte[] grabFrame() throws IOException {
		byte[] result = makeBuffer();
		grab(result);
		frames += 1;
		return result;
	}
	/*
	// I see no reason to use this.  There is no performance advantage over grabFrame(), 
	// and generating a new image every time is conceptually simpler.  
	public static void grabInto(byte[] bytes) throws IOException {
		grab(bytes);
		frames += 1;
	}
	*/

	public static void start() throws IOException {
		start(160, 120);
	}

	public static void start(int w, int h) throws IOException {
		width = w;
		height = h;
		setup();
		start = System.currentTimeMillis();
		frames = 0;
	}

	public static double end() throws IOException {
		duration = System.currentTimeMillis() - start;
		dispose();
		return 1000.0 * frames / duration;
	}

	private static native void setup() throws IOException;

	private static native void grab(byte[] img) throws IOException;

	private static native void dispose() throws IOException;
	
	public static int getWidth() {return width;}
	
	public static int getHeight() {return height;}
}
