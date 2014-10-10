package edu.hendrix.lmsl.unsupervised.gui;

import edu.hendrix.lmsl.unsupervised.controllers.Flag;
import edu.hendrix.lmsl.unsupervised.controllers.GNGNodeMoves;

@SuppressWarnings("serial")
public class GNGViewPanel extends AbstractGNGViewPanel<Flag,GNGNodeMoves<Flag>,FlagEditorPanel> implements FlagSwapListener {

	public GNGViewPanel(GNGNodeMoves<Flag> gng) {
		super(gng);
	}

	@Override
	protected FlagEditorPanel makeEditor() {
		FlagEditorPanel editor = new FlagEditorPanel();
		editor.addFlagSwapListener(this);
		return editor;
	}

	@Override
	public void updatedFlag(Flag updated) {
		if (!getGNG().hasMoveFor(getNodeNum()) || getGNG().getMoveFor(getNodeNum()) != updated) {
			getGNG().forceMoveChange(getNodeNum(), updated);
			refreshNode();
			markChanged();
		}
	}
}
