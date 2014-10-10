package edu.hendrix.lmsl.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

abstract public class Storage<T> {
	public static final String EV3_BASE = "/home/root";
	public static final String PC_BASE = System.getProperty("user.home") + File.separator + "ev3files";
	
	private String storageDir;
	
	abstract public String getDirName();
	abstract public String getDefaultName();
	abstract public T fromString(String src);
	
	public Storage(String baseDir) {
		storageDir = baseDir + File.separator + getDirName() + File.separator;
	}
	
	public T open(String choice) throws FileNotFoundException {
		return fromString(altOpen(choice, getDefaultName()));
	}
	
	public T openMostRecent() throws FileNotFoundException {
		String[] picks = choices();
		if (picks.length == 0) {
			throw new FileNotFoundException("No files in storage");
		}
		return open(picks[picks.length - 1]);
	}
	
	public String altOpen(String choice, String filename) throws FileNotFoundException {
		File target = new File(storageDir + choice + File.separator + filename);
		Scanner scanner = new Scanner(target);
		String result = scanner.useDelimiter("\\A").next();
		scanner.close();
		return result;
	}
	
	public String altOpenMostRecent(String filename) throws FileNotFoundException {
		String[] picks = choices();
		return altOpen(picks[picks.length - 1], filename);
	}
	
	public String[] choices() {
		File target = new File(storageDir);
		return target.exists() ? target.list() : new String[0];
	}
	
	public void save(T gng) throws IOException {
		ensureDirectory();
		File out = new File(getNextFilename());
		output(gng.toString(), out);
	}
	
	public void altSave(String rep, String filename) throws IOException {
		String[] picks = choices();
		File out = new File(storageDir + picks[picks.length - 1] + File.separator + filename);
		output(rep, out);
	}
	
	public void update(T gng, String choice) throws IOException {
		File target = new File(storageDir + choice);
		File out = new File(getNextFilenameIn(target));
		output(gng.toString(), out);
	}
	
	public boolean delete(String choice) {
		return altDelete(choice, this.getDefaultName());
	}
	
	public boolean altDelete(String choice, String filename) {
		File target = new File(storageDir + choice + File.separator + filename);
		boolean deleteWorked = target.delete();
		if (deleteWorked) {
			File dir = new File(storageDir + choice);
			if (dir.isDirectory() && dir.list().length == 0) {
				deleteWorked = dir.delete();
			}
		}
		return deleteWorked;
	}
	
	private void output(String contents, File out) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(out));
		pw.println(contents);
		pw.close();
	}
	
	private void ensureDirectory() throws IOException {
		File target = new File(storageDir);
		if (!target.exists()) {
			if (!target.mkdir()) {throw new IOException("Failed to create directory");}
		}
		
		if (!target.isDirectory()) {throw new IOException(storageDir + " is not a directory");}
	}
	
	public String getNextName() {
		File target = new File(storageDir);
		String[] files = target.list();
		return files.length == 0 ? "1" : Integer.toString(1 + Integer.parseInt(files[files.length - 1]));
	}
	
	private String getFilenamePreview(File target) {
		String[] files = target.list();
		return storageDir + (files.length == 0 ? "1" : Integer.toString(1 + Integer.parseInt(files[files.length - 1])));
	}
	
	private String getNextFilename() {
		File target = new File(storageDir);
		return getNextFilenameIn(target);
	}
	
	private String getNextFilenameIn(File target) {
		String dirname = getFilenamePreview(target);
		File dir = new File(dirname);
		dir.mkdir();
		return dirname + File.separator + getDefaultName();
	}
}
