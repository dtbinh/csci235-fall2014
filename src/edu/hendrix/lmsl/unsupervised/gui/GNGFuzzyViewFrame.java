package edu.hendrix.lmsl.unsupervised.gui;

import edu.hendrix.lmsl.EnumHistogram;
import edu.hendrix.lmsl.storage.FuzzyGNGStorage;
import edu.hendrix.lmsl.unsupervised.controllers.Flag;
import edu.hendrix.lmsl.unsupervised.controllers.fuzzy.FuzzyGNGMoves;

@SuppressWarnings("serial")
public class GNGFuzzyViewFrame extends GenericViewFrame<EnumHistogram<Flag>, FuzzyGNGMoves<Flag>, FuzzyGNGStorage<Flag>, HistogramEditorPanel, FuzzyGNGViewPanel> {

	public GNGFuzzyViewFrame() {
		super("gngFuzzyFiles");
	}
	
	public static void main(String[] args) {
		new GNGFuzzyViewFrame().setVisible(true);
	}

	@Override
	protected FuzzyGNGStorage<Flag> getStorage() {
		return FuzzyGNGStorage.getPCStorage(Flag.class);
	}

	@Override
	protected FuzzyGNGViewPanel makeViewPanel(FuzzyGNGMoves<Flag> viewTarget) {
		return new FuzzyGNGViewPanel(viewTarget);
	}
}
