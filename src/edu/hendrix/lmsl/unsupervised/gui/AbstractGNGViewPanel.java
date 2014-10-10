package edu.hendrix.lmsl.unsupervised.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.hendrix.lmsl.unsupervised.controllers.AbstractGNGNodeMoves;
import edu.hendrix.lmsl.unsupervised.controllers.Flag;

@SuppressWarnings("serial")
abstract public class AbstractGNGViewPanel<R,G extends AbstractGNGNodeMoves<R,Flag>, E extends JPanel> extends JPanel {
	private G gng;
	private ArrayList<Integer> nodeNums;
	private int currentNode;
	private GNGNodePanel nodePanel;
	private JTextField nodeField;
	private JCheckBox withMovesOnly;
	private E editor;
	private boolean isChanged = false;
	
	abstract protected E makeEditor();
	
	public void markChanged() {isChanged = true;}
	
	public boolean isChanged() {return isChanged;}
	
	public G getGNG() {return gng;}
	
	protected void refreshNode() {
		nodePanel.reset(gng.get(getNodeNum()), gng.getMoveFor(getNodeNum()));
		nodePanel.repaint();
	}
	
	private void refreshNodeNums() {
		nodeNums = new ArrayList<Integer>(gng.getAllNodeNums());		
		if (currentNode >= nodeNums.size()) {
			currentNode = 0;
		}
	}
	
	public AbstractGNGViewPanel(G gng) {
		this.gng = gng;
		currentNode = 0;
		refreshNodeNums();
		setLayout(new BorderLayout());
		JPanel top = new JPanel();
		JButton left = new JButton("Previous");
		left.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetCurrentNode(-1);
			}});
		JButton right = new JButton("Next");
		right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetCurrentNode(1);
			}});
		nodeField = new JTextField(4);
		nodeField.setEditable(false);
		top.add(left);
		top.add(nodeField);
		top.add(right);
		
		JPanel scale = new JPanel();
		JButton grow = new JButton("Grow");
		grow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nodePanel.grow();
			}});
		
		JButton shrink = new JButton("Shrink");
		shrink.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nodePanel.shrink();
			}});
		scale.add(grow);
		scale.add(shrink);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(3, 1));
		buttons.add(top);

		JPanel otherStuff = new JPanel();
		withMovesOnly = new JCheckBox("With moves only");
		otherStuff.add(withMovesOnly);
		JButton deleteNode = new JButton("Delete node");
		deleteNode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractGNGViewPanel.this.gng.forceDeleteNode(getNodeNum());
				refreshNodeNums();
				setNodeField();
				refreshNode();
			}});
		otherStuff.add(deleteNode);
		buttons.add(otherStuff);
		buttons.add(scale);
		
		add(buttons, BorderLayout.NORTH);
		nodePanel = new GNGNodePanel(gng.get(getNodeNum()), gng.getMoveFor(getNodeNum()));
		setNodeField();
		add(nodePanel, BorderLayout.CENTER);
		
		editor = makeEditor();
		add(editor, BorderLayout.SOUTH);
	}
	
	public int getNodeNum() {return nodeNums.get(currentNode);}
	
	protected void resetCurrentNode(int update) {
		currentNode = getUpdatedIndex(currentNode, update);
		while (withMovesOnly.isSelected() && !gng.hasMoveFor(getNodeNum())) {
			currentNode = getUpdatedIndex(currentNode, update);
		}
		setNodeField();
		refreshNode();
	}
		
	private int getUpdatedIndex(int index, int update) {
		index += update;
		if (index < 0) {index = nodeNums.size() - 1;}
		if (index >= nodeNums.size()) {index = 0;}
		return index;
	}
	
	private void setNodeField() {
		nodeField.setText(Integer.toString(getNodeNum()));
	}
}
