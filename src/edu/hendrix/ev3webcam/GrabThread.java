package edu.hendrix.ev3webcam;

import java.io.IOException;

import lejos.hardware.lcd.LCD;

abstract public class GrabThread extends Thread {
	private boolean running = false, dead = false, stopCam = true;
	
	public GrabThread() {this(true);}
	public GrabThread(boolean stopCam) {this.stopCam = stopCam;}
	
	abstract public void process(YUYVImage grabbed);
	
	@Override
	public void run() {
		try {
			Webcam.start();
			running = true;
			while (running) {
				process(YUYVImage.grab());
			}

			if (stopCam) {
				double fps = Webcam.end();
				LCD.clear();
				System.out.println(String.format("%5.2f fps", fps));
			}
			running = false;
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Driver exception: " + ioe.getMessage());
			dead = true;
		}		
	}
	
	public void halt() {running = false;}
	public boolean isRunning() {return running && !dead;}
	public boolean isDead() {return dead;}
}
