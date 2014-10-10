package edu.hendrix.lmsl.unsupervised.gui;

import edu.hendrix.lmsl.storage.GNGStorage;
import edu.hendrix.lmsl.unsupervised.controllers.Flag;
import edu.hendrix.lmsl.unsupervised.controllers.GNGNodeMoves;

@SuppressWarnings("serial")
public class GNGViewFrame extends GenericViewFrame<Flag, GNGNodeMoves<Flag>, GNGStorage<Flag>, FlagEditorPanel, GNGViewPanel> {
	public GNGViewFrame() {
		super("gngFiles");
	}
	
	public static void main(String[] args) {
		new GNGViewFrame().setVisible(true);
	}

	@Override
	protected GNGStorage<Flag> getStorage() {
		return GNGStorage.getPCStorage(Flag.class);
	}

	@Override
	protected GNGViewPanel makeViewPanel(GNGNodeMoves<Flag> viewTarget) {
		return new GNGViewPanel(viewTarget);
	}
}
