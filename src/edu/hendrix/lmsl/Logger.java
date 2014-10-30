package edu.hendrix.lmsl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
	private File logFile;
	private static Logger singleton = null;
	
	private Logger() {
		String logFileName = System.getProperty("sun.java.command");
		logFileName = logFileName.substring(logFileName.lastIndexOf('.') + 1) + ".log";
		logFile = new File("/home/lejos/programs/" + logFileName);
		if (logFile.exists()) {
			logFile.delete();
		}
	}
	
	public static Logger instance() {
		if (singleton == null) {
			singleton = new Logger();
		}
		return singleton;
	}
	
	public void log(String line) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(logFile, logFile.exists()));
			pw.println(line);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
