package edu.hendrix.ev3webcam.demo;

import java.io.IOException;

import edu.hendrix.ev3webcam.Webcam;
import edu.hendrix.ev3webcam.YUYVImage;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

abstract public class AbstractCameraDemo {
	public void run() {
		try {
			Webcam.start();
			while (!Button.ESCAPE.isDown()) {
				show(YUYVImage.grab());
			}

			double fps = Webcam.end();
			LCD.clear();
			System.out.println(String.format("%5.2f fps", fps));
			finish();
			while (!Button.ESCAPE.isDown());
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Driver exception: " + ioe.getMessage());
		}
	}
	
	abstract public void show(YUYVImage grabbed);
	
	abstract public void finish();
}
