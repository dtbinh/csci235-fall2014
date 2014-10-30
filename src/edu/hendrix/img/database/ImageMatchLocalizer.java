package edu.hendrix.img.database;

import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import edu.hendrix.ev3webcam.Webcam;
import edu.hendrix.ev3webcam.YUYVImage;
import edu.hendrix.img.IntImage;
import edu.hendrix.lmsl.demos.localize1.GyroLocalizer;
import edu.hendrix.lmsl.demos.localize1.Location;
import edu.hendrix.lmsl.storage.Chooser;

abstract public class ImageMatchLocalizer<T,M extends ImageMatcher<T>> {
	private double checkDistance;
	
	public ImageMatchLocalizer() {
		this(Photographer.PHOTO_INTERVAL);
	}
	
	public ImageMatchLocalizer(double checkDistance) {
		this.checkDistance = checkDistance;
	}
	
	public double getImageShare(double imgDist) {
		// Place your solution here
		// This version simply replaces the original location
		// with that given for the winning image.
		return 1.0;
	}
	
	abstract public M makeMatcherFrom(YUYVImageList images);
	
	public void run() {
		ButtonMover mover = new ButtonMover(Motor.A, Motor.D, 300, 150);
		YUYVImageListStorage storage = YUYVImageListStorage.getEV3Storage();
		Chooser<YUYVImageList,YUYVImageListStorage> chooser = new Chooser<YUYVImageList,YUYVImageListStorage>();
		chooser.choose(storage);
		if (chooser.isSelected()) {
			LCD.clear();
			LCD.drawString("Processing image", 0, 0);
			LCD.drawString("database...", 0, 1);
			M matcher = makeMatcherFrom(chooser.getSelected());
			LCD.clear();
			LCD.drawString("Starting webcam...", 0, 0);
			
			EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S4);
			GyroLocalizer localizer = new GyroLocalizer(Motor.A, Motor.D, gyro);
			Location prev = new Location(0, 0, 0);
			try {
				Webcam.start();
				LCD.clear();
				while (!Button.ESCAPE.isDown()) {
					localizer.update();
					YUYVImage img = YUYVImage.grab();
					Location at = localizer.getLocation();
					if (at.distanceTo(prev) > checkDistance) {
						mover.stop();
						ImageMatch now = matcher.getBestMatch(img);
						IntImage match4 = IntImage.toShrunkenGrayInts(now.getImage(), 4);
						match4.displayLCD(0, LCD.SCREEN_HEIGHT / 2);
						prev = now.getLocation();
						localizer.reset(at.partialMeanWith(prev, getImageShare(now.getDistance())));
						mover.resume();
					} else {
						mover.move();
					}
					IntImage img4 = IntImage.toShrunkenGrayInts(img, 4);
					img4.displayLCD(0, 0);
					localizer.getLocation().display(7);
				}

			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Driver exception: " + e.getMessage());
			}
			gyro.close();
		}
	}
}
