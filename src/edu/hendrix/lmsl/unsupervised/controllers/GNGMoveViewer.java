package edu.hendrix.lmsl.unsupervised.controllers;

import edu.hendrix.lmsl.storage.Chooser;
import edu.hendrix.lmsl.storage.GNGStorage;
import edu.hendrix.lmsl.storage.Storage;

public class GNGMoveViewer extends AbstractGNGMoveViewer<Flag,Flag,GNGNodeMoves<Flag>> {
	public static void main(String[] args) {
		new GNGMoveViewer().run();
	}
	
	private boolean gngChanging;
	private Flag tentativeChange;
	
	@Override
	public Storage<GNGNodeMoves<Flag>> getStorage() {
		return GNGStorage.getEV3Storage(Flag.class);
	}
	
	@Override
	public Chooser<GNGNodeMoves<Flag>, Storage<GNGNodeMoves<Flag>>> makeChooser() {
		return new Chooser<GNGNodeMoves<Flag>, Storage<GNGNodeMoves<Flag>>>();
	}
	
	@Override
	protected void viewEditGNG() {
		gngChanging = false;
		super.viewEditGNG();
	}
	
	@Override
	protected void upButton() {
		if (tentativeChange == null) {
			tentativeChange = Flag.values()[0];
		} else {
			int i = 0;
			while (tentativeChange != Flag.values()[i]) {
				i += 1;
			}
			if (i < Flag.values().length - 1) {
				tentativeChange = Flag.values()[i+1];
			} else {
				tentativeChange = null;
			}
		}
		viewNode();
	}
	
	@Override
	protected void downButton() {
		if (tentativeChange == null) {
			tentativeChange = Flag.values()[Flag.values().length - 1];
		} else {
			int i = 0;
			while (tentativeChange != Flag.values()[i]) {
				i += 1;
			}
			if (i > 0) {
				tentativeChange = Flag.values()[i-1];
			} else {
				tentativeChange = null;
			}
		}
		viewNode();
	}

	@Override
	protected void enterButton() {
		if (gngChanging) {
			gngChanging = false;
			makeChange();
			viewNewNode();
		}
	}
	
	@Override
	protected void displayMove(int line) {
		displayMove(getCurrentMove(), 1);
		if (gngChanging) {
			displayMove(tentativeChange, 2);
		}
	}
	
	@Override
	protected void changeGNG(GNGNodeMoves<Flag> gng) {
		gng.forceMoveChange(getRealNode(), tentativeChange);
	}
	
	@Override
	public void viewNode() {
		gngChanging = (tentativeChange != getCurrentMove());
		super.viewNode();
	}
	
	@Override
	protected void viewNewNode() {
		tentativeChange = getCurrentMove();
		super.viewNewNode();
	}
}
