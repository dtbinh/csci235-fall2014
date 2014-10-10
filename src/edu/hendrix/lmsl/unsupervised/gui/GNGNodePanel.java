package edu.hendrix.lmsl.unsupervised.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import edu.hendrix.img.IntImage;

@SuppressWarnings("serial")
public class GNGNodePanel extends JPanel {
	private IntImage gngNode;
	private Object gngFlag;
	private final int TEXT_HEIGHT = 12, SPACING = 4, OFFSET = TEXT_HEIGHT + SPACING;
	private int scale;
	
	public GNGNodePanel(IntImage gngNode, Object gngFlag) {
		super();
		setBackground(Color.white);
		reset(gngNode, gngFlag);
		scale = 2;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(gngNode.getWidth() * scale, gngNode.getHeight() * scale + OFFSET);
	}
	
	public void reset(IntImage gngNode, Object gngFlag) {
		this.gngNode = gngNode;
		this.gngFlag = gngFlag;
	}
	
	public void grow() {
		scale *= 2;
		repaint();
	}
	
	public void shrink() {
		if (scale >= 2) {
			scale /= 2;
			repaint();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(gngNode.toBufferedImage().getScaledInstance(gngNode.getWidth() * scale, gngNode.getHeight() * scale, 0), 0, OFFSET, null);
		g.drawString(gngFlag == null ? "No move" : gngFlag.toString(), 0, TEXT_HEIGHT);
	}
}
