package edu.hendrix.lmsl.unsupervised.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import edu.hendrix.lmsl.unsupervised.controllers.Flag;

@SuppressWarnings("serial")
public class FlagEditorPanel extends JPanel {
	private JButton update;
	private JComboBox<Flag> flags;
	private ArrayList<FlagSwapListener> listeners;
	
	public FlagEditorPanel() {
		super();
		setLayout(new FlowLayout());
		flags = new JComboBox<Flag>();
		for (Flag flag: Flag.values()) {
			flags.addItem(flag);
		}
		add(flags);
		
		update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (FlagSwapListener fsl: listeners) {
					fsl.updatedFlag((Flag)flags.getSelectedItem());
				}
			}});
		add(update);
		
		listeners = new ArrayList<FlagSwapListener>();
	}
	
	public void addFlagSwapListener(FlagSwapListener fsl) {
		listeners.add(fsl);
	}
}
