package edu.hendrix.img.features;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

import edu.hendrix.ev3webcam.Point;

public class PointFieldGenerator {
	public static void generate(int numPairs, int featureWidth, int featureHeight, String name) throws FileNotFoundException {
		Point[] one, two;
		one = new Point[numPairs];
		two = new Point[numPairs];
		Random r = new Random();
		for (int i = 0; i < numPairs; ++i) {
			one[i] = new Point(r.nextInt(featureWidth), r.nextInt(featureHeight));
			two[i] = new Point(r.nextInt(featureWidth), r.nextInt(featureHeight));
		}
		
		File outFile = new File(name + ".java");
		PrintStream out = new PrintStream(outFile);
		out.println("public class " + name + " extends PointFieldPattern {");
		out.println("\tpublic " + name + "() {");
		out.println("\t\tsuper(" + featureWidth + ", " + featureHeight + ");\n\t}");
		out.println("\tprotected PointPair[] makePairs() {");
		out.println("\t\tPointPair[] result = new PointPair[" + numPairs + "];");
		
		for (int i = 0; i < numPairs; ++i) {
			out.println("\t\tresult[" + i + "] = new PointPair(" + asConstructor(one[i]) + ", " + asConstructor(two[i]) + ");");
		}
		out.println("\t\treturn result;\n\t}\n}");
		out.close();
	}
	
	public static String asConstructor(Point p) {
		return "new Point(" + p.getX() + "," + p.getY() + ")";
	}
	
	public static void main(String[] args) throws NumberFormatException, FileNotFoundException {
		if (args.length < 4) {
			System.out.println("Usage: PointFieldGenerator numPairs featureWidth featureHeight name");
			System.exit(1);
		}
		
		generate(Integer.parseInt(args[0]), 
				Integer.parseInt(args[1]), 
				Integer.parseInt(args[2]), 
				args[3]);
	}
}
