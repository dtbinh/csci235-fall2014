package edu.hendrix.lmsl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Util {
	
	/**
	 * Reads in a File and dumps it into a String.
	 * 
	 * @param f
	 * @return a String containing all text from f.
	 * @throws FileNotFoundException
	 */
	public static String fileToString(File f) throws FileNotFoundException {
		Scanner s = new Scanner(f);
		StringBuilder sb = new StringBuilder();
		while (s.hasNextLine()) {
			sb.append(s.nextLine() + '\n');
		}
		s.close();
		return sb.toString();
	}
	
	/**
	 * Takes a String and dumps it into a File.
	 * @throws IOException 
	 * 
	 * 
	 */
	public static void stringToFile(File f, String s) throws IOException {
		PrintWriter p = new PrintWriter(new FileWriter(f));
		p.println(s);
		p.close();
	}
}
