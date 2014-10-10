package edu.hendrix.lmsl.unsupervised.controllers;

import java.io.IOException;
import java.util.ArrayList;

import edu.hendrix.lmsl.storage.Chooser;
import edu.hendrix.lmsl.storage.Storage;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

abstract public class AbstractGNGMoveViewer<R,F extends Enum<F>,G extends AbstractGNGNodeMoves<R,F>> {
	private Storage<G> storage;
	private G gng;
	private boolean buttonsEnabled, selectionChanged, gngChanged;
	private int node;
	private ArrayList<Integer> nodeNums;
	
	public AbstractGNGMoveViewer() {
		storage = getStorage();
	}
	
	abstract public Storage<G> getStorage();
	abstract public Chooser<G, Storage<G>> makeChooser();
	
	public void run() {
		Chooser<G, Storage<G>> chooser = makeChooser();
		chooser.choose(storage);
		if (chooser.isSelected()) {
			gng = chooser.getSelected();
			viewEditGNG();
			if (gngChanged) {
				try {
					LCD.clear();
					LCD.drawString("Saving...", 0, 0);
					storage.save(gng);
				} catch (IOException e) {
					e.printStackTrace();
					LCD.clear();
					LCD.drawString("Can't save edited GNG", 0, 0);
				}
			}
			System.out.println("Exiting");
		}
	}
	
	protected void viewEditGNG() {
		nodeNums = new ArrayList<Integer>(gng.getAllNodeNums());
		node = 0;
		selectionChanged = buttonsEnabled = true;
		gngChanged = false;
		while (!Button.ESCAPE.isDown()) {
			if (buttonsEnabled) {
				selectNode();
			} else if (allButtonsUp()) {
				buttonsEnabled = true;
			} 
			if (selectionChanged) {
				viewNewNode();
			}
		}
	}
	
	public static boolean allButtonsUp() {
		return (Button.LEFT.isUp() && Button.RIGHT.isUp() && Button.UP.isUp() && Button.DOWN.isUp() && Button.ENTER.isUp() && Button.ESCAPE.isUp());
	}
	
	protected void viewNewNode() {
		selectionChanged = false;
		viewNode();
	}
	
	protected void leftButton() {
		node = node - 1;
		if (node < 0) {node += gng.numNodes();}
		selectionChanged = true;
	}
	
	protected void rightButton() {
		node = (node + 1) % gng.numNodes();
		selectionChanged = true;
	}
	
	protected int getRealNode() {
		return nodeNums.get(node);
	}
	
	abstract protected void upButton();
	abstract protected void downButton();
	abstract protected void enterButton();
	
	private void selectNode() {
		if (Button.LEFT.isDown()) {
			leftButton();
		}
		if (Button.RIGHT.isDown()) {
			rightButton();
		}
		
		if (Button.UP.isDown()) {
			upButton();
		}
		
		if (Button.DOWN.isDown()) {
			downButton();
		}
		
		if (Button.ENTER.isDown()) {
			enterButton();
		}
	}
	
	protected void viewNode() {
		LCD.clearDisplay();
		LCD.drawString("Node:" + getRealNode(), 0, 0);
		displayMove(1);
		gng.get(getRealNode()).displayLCD(50, 50);
		buttonsEnabled = false;
	}
	
	abstract protected void displayMove(int line);
	
	protected void displayMove(Object move, int line) {
		LCD.drawString(move == null ? "No move" : move.toString(), 0, line);
	}
	
	protected void makeChange() {
		changeGNG(gng);
		gngChanged = true;
	}
	
	abstract protected void changeGNG(G gng);
	
	protected R getCurrentMove() {
		return gng.getMoveFor(getRealNode());
	}
	
	protected G getGNG() {return gng;}
}
