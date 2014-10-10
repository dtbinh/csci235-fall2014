package edu.hendrix.ev3webcam;
import static org.junit.Assert.*;
import lejos.hardware.lcd.LCD;

import org.junit.*;


public class YUYVImageTest {
	
	@Test
	public void testIdentity() {
		byte[] bytes = new byte[2*LCD.SCREEN_WIDTH*LCD.SCREEN_HEIGHT];
		YUYVImage target = new YUYVImage(bytes, LCD.SCREEN_WIDTH, LCD.SCREEN_HEIGHT);
		for (int x = 0; x < LCD.SCREEN_WIDTH; ++x) {
			for (int y = 0; y < LCD.SCREEN_HEIGHT; ++y) {
				assertEquals(2 * (y * LCD.SCREEN_WIDTH + x), target.getScaledIndex(x, y));
			}
		}
	}
	
	@Test
	public void testIndices() {
		int width = 160;
		int height = 120;
		byte[] bytes = new byte[width*height];
		YUYVImage target = new YUYVImage(bytes, width, height);
		
		assertEquals(0, target.getScaledX(0));
		assertEquals(width / 2, target.getScaledX(LCD.SCREEN_WIDTH / 2));
		assertEquals(width - 1, target.getScaledX(LCD.SCREEN_WIDTH - 1));
		
		assertEquals(0, target.getScaledY(0));
		assertEquals(height / 2, target.getScaledY(LCD.SCREEN_HEIGHT / 2));
		assertEquals(height - 1, target.getScaledY(LCD.SCREEN_HEIGHT - 1));
		
		assertEquals(0, target.getScaledIndex(0, 0));
		assertEquals(19360, target.getScaledIndex(LCD.SCREEN_WIDTH / 2, LCD.SCREEN_HEIGHT / 2));
		assertEquals(38398, target.getScaledIndex(LCD.SCREEN_WIDTH - 1, LCD.SCREEN_HEIGHT - 1));
	}
	
	@Test
	public void testValues() {
		byte[] bytes = new byte[]{5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, -5, 80};
		YUYVImage target = new YUYVImage(bytes, 4, 2);
		assertEquals(8, target.getNumPixels());
		assertEquals(62, target.getMeanY());
		assertEquals(target.getNumPixels(), target.getWidth() * target.getHeight());
		
		assertEquals(5, target.get(YUV.Y, 0, 0));
		assertEquals(10, target.get(YUV.U, 0, 0));
		assertEquals(20, target.get(YUV.V, 0, 0));
		
		assertEquals(15, target.get(YUV.Y, 1, 0));
		assertEquals(10, target.get(YUV.U, 1, 0));
		assertEquals(20, target.get(YUV.V, 1, 0));
		
		assertEquals(25, target.get(YUV.Y, 2, 0));
		assertEquals(30, target.get(YUV.U, 2, 0));
		assertEquals(40, target.get(YUV.V, 2, 0));
		
		assertEquals(35, target.get(YUV.Y, 3, 0));
		assertEquals(30, target.get(YUV.U, 3, 0));
		assertEquals(40, target.get(YUV.V, 3, 0));
		
		assertEquals(45, target.get(YUV.Y, 0, 1));
		assertEquals(50, target.get(YUV.U, 0, 1));
		assertEquals(60, target.get(YUV.V, 0, 1));
		
		assertEquals(55, target.get(YUV.Y, 1, 1));
		assertEquals(50, target.get(YUV.U, 1, 1));
		assertEquals(60, target.get(YUV.V, 1, 1));
		
		assertEquals(65, target.get(YUV.Y, 2, 1));
		assertEquals(70, target.get(YUV.U, 2, 1));
		assertEquals(80, target.get(YUV.V, 2, 1));
		
		assertEquals(251, target.get(YUV.Y, 3, 1));
		assertEquals(70, target.get(YUV.U, 3, 1));
		assertEquals(80, target.get(YUV.V, 3, 1));

		YUYVImage target2 = YUYVImage.fromString(target.toString());
		assertEquals(target.toString(), target2.toString());
		assertEquals(target, target2);
		
		YUYVImage target3 = new YUYVImage(target.getWidth(), target.getHeight());
		assertNotEquals(target, target3);
	}
}
