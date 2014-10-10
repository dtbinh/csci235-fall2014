package edu.hendrix.lmsl.unsupervised.controllers.fuzzy;

import lejos.hardware.lcd.LCD;
import edu.hendrix.lmsl.EnumHistogram;
import edu.hendrix.lmsl.storage.Chooser;
import edu.hendrix.lmsl.storage.FuzzyGNGStorage;
import edu.hendrix.lmsl.storage.Storage;
import edu.hendrix.lmsl.unsupervised.controllers.AbstractGNGMoveViewer;
import edu.hendrix.lmsl.unsupervised.controllers.Flag;

public class FuzzyGNGMoveViewer extends AbstractGNGMoveViewer<EnumHistogram<Flag>,Flag,FuzzyGNGMoves<Flag>>{
	private int currentFlagIndex = 0;
	private int tentativeCount;
	
	public static void main(String[] args) {
		new FuzzyGNGMoveViewer().run();
	}
	
	@Override
	public Storage<FuzzyGNGMoves<Flag>> getStorage() {
		return FuzzyGNGStorage.getEV3Storage(Flag.class);
	}

	@Override
	public Chooser<FuzzyGNGMoves<Flag>, Storage<FuzzyGNGMoves<Flag>>> makeChooser() {
		return new Chooser<FuzzyGNGMoves<Flag>, Storage<FuzzyGNGMoves<Flag>>>();
	}

	@Override
	protected void upButton() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void downButton() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void enterButton() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void leftButton() {
		if (currentFlagIndex == 0) {
			currentFlagIndex = Flag.values().length - 1;
			super.leftButton();
		} else {
			currentFlagIndex -= 1;
			viewNode();
		}
		tentativeCount = getCurrentCount();
	}
	
	@Override
	protected void rightButton() {
		if (currentFlagIndex == Flag.values().length - 1) {
			currentFlagIndex = 0;
			super.rightButton();
		} else {
			currentFlagIndex += 1;
			viewNode();
		}
		tentativeCount = getCurrentCount();
	}
	
	@Override
	protected void viewNode() {
		super.viewNode();
		Flag current = Flag.values()[currentFlagIndex];
		String msg = current.toString() + " " + getCurrentCount();
		LCD.drawString(msg, 0, 2);
	}
	
	public boolean hasCurrentCount() {
		return getGNG().hasMoveFor(getRealNode());
	}
	
	public int getCurrentCount() {
		return hasCurrentCount() 
				? getGNG().getMoveFor(getRealNode()).getCountFor(Flag.values()[currentFlagIndex])
				: 0;
	}

	@Override
	protected void displayMove(int line) {
		String msg = getCurrentMove() == null ? "No moves" : getCurrentMove().toShortString();
		displayMove(msg, 1);
	}

	@Override
	protected void changeGNG(FuzzyGNGMoves<Flag> gng) {
		// TODO Auto-generated method stub
		
	}

}
