package edu.hendrix.img.database.clunky;

import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import edu.hendrix.ev3webcam.Webcam;
import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.img.IntImage;
import edu.hendrix.img.database.ButtonMover;
import edu.hendrix.img.database.ImageMatch;
import edu.hendrix.img.database.ImageMatcher;
import edu.hendrix.img.database.YUYVImageList;
import edu.hendrix.img.database.YUYVImageListStorage;
import edu.hendrix.lmsl.demos.localize1.GyroLocalizer;
import edu.hendrix.lmsl.storage.Chooser;

abstract public class ImageMatchDemo<T,M extends ImageMatcher<T>> {
	
	abstract public M makeMatcherFrom(YUYVImageList images);

	public void run() {
		ButtonMover mover = new ButtonMover(Motor.A, Motor.D, 300, 150);
		EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S4);
		GyroLocalizer localizer = new GyroLocalizer(Motor.A, Motor.D, gyro);
		YUYVImageListStorage storage = YUYVImageListStorage.getEV3Storage();
		Chooser<YUYVImageList,YUYVImageListStorage> chooser = new Chooser<YUYVImageList,YUYVImageListStorage>();
		chooser.choose(storage);
		if (chooser.isSelected()) {
			try {
				LCD.clear();
				LCD.drawString("Processing images...", 0, 0);
				M matcher = makeMatcherFrom(chooser.getSelected());
				LCD.clear();
				Webcam.start();
				
				while (!Button.ESCAPE.isDown()) {
					YUYVImage img = YUYVImage.grab();
					ImageMatch matched = matcher.getBestMatch(img, localizer.getLocation(), 4);
					YUYVImage match = matched.getImage();
					IntImage img4 = IntImage.toShrunkenGrayInts(img, 4);
					IntImage match4 = IntImage.toShrunkenGrayInts(match, 4);
					img4.displayLCD(0, 0);
					match4.displayLCD(0, LCD.SCREEN_HEIGHT / 2);
					matched.getLocation().display(7);
					
					mover.move();
					localizer.update();
				}

				double fps = Webcam.end();
				gyro.close();
				LCD.clear();
				System.out.println(String.format("%5.2f fps", fps));
				while (!Button.ESCAPE.isDown());
			} catch (IOException ioe) {
				ioe.printStackTrace();
				System.out.println("Driver exception: " + ioe.getMessage());
			}
		}
	}	
}
