package edu.hendrix.lmsl.unsupervised.gui;

import edu.hendrix.lmsl.EnumHistogram;
import edu.hendrix.lmsl.unsupervised.controllers.fuzzy.FuzzyGNGMoves;
import edu.hendrix.lmsl.unsupervised.controllers.Flag;

@SuppressWarnings("serial")
public class FuzzyGNGViewPanel extends AbstractGNGViewPanel<EnumHistogram<Flag>, FuzzyGNGMoves<Flag>, HistogramEditorPanel> {
	private HistogramEditorPanel editor;
	
	public FuzzyGNGViewPanel(FuzzyGNGMoves<Flag> gng) {
		super(gng);
		for (int nodeNum: gng.getAllNodeNums()) {
			if (!gng.hasMoveFor(nodeNum)) {
				gng.forceMoveChange(nodeNum, new EnumHistogram<Flag>(Flag.class));
			}
		}
	}

	@Override
	protected HistogramEditorPanel makeEditor() {
		editor = new HistogramEditorPanel(this);
		resetEditor();
		return editor;
	}
	
	private void resetEditor() {
		editor.resetTo(getGNG().getMoveFor(getNodeNum()));
	}

	@Override
	protected void resetCurrentNode(int update) {
		super.resetCurrentNode(update);
		resetEditor();
	}
}
