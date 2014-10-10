package edu.hendrix.lmsl.unsupervised.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.hendrix.lmsl.storage.Storage;
import edu.hendrix.lmsl.unsupervised.controllers.AbstractGNGNodeMoves;
import edu.hendrix.lmsl.unsupervised.controllers.Flag;

@SuppressWarnings("serial")
abstract public class GenericViewFrame<R, G extends AbstractGNGNodeMoves<R,Flag>, S extends Storage<G>, E extends JPanel, V extends AbstractGNGViewPanel<R,G,E>> extends JFrame implements RemoteCopyListener {
	private JMenuItem open, save, download, upload;
	private JTabbedPane allGNGs;
	private LinkedHashMap<String,V> name2panel;
	private boolean isWindows;
	private String dirName;
	
	abstract protected S getStorage();
	abstract protected V makeViewPanel(G viewTarget);
	
	public GenericViewFrame(String dirName) {
		super();
		this.dirName = dirName;
		setSize(400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		isWindows = System.getProperty("os.name").toLowerCase().startsWith("win");

		allGNGs = new JTabbedPane();
		setContentPane(allGNGs);
		
		name2panel = new LinkedHashMap<String,V>();
		
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		setJMenuBar(bar);
		bar.add(file);
		open = new JMenuItem("Open");
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openAll();
			}});
		file.add(open);
		
		save = new JMenuItem("Save changes locally");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				S storage = getStorage();
				boolean anyChanged = false;
				for (Entry<String, V> choice: name2panel.entrySet()) {
					if (choice.getValue().isChanged()) {
						anyChanged = true;
						try {
							String newName = storage.getNextName();
							storage.save(choice.getValue().getGNG());
							JOptionPane.showMessageDialog(null, "Updated version of \"" + choice.getKey() + "\" saved as \"" + newName + "\"");
							openChoice(storage, newName);
							allGNGs.setSelectedIndex(allGNGs.getComponentCount() - 1);
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "Save failed for changed GNG \"" + choice.getKey() + "\"");
						}
					} 
				}
				if (!anyChanged) {
					JOptionPane.showMessageDialog(null, "No saves attempted; none changed.");
				}
			}});
		file.add(save);
		
		download = new JMenuItem("Download from EV3");
		download.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isWindows) {
					RemoteCopier.downloadWin(GenericViewFrame.this.dirName, GenericViewFrame.this);
				} else {
					RemoteCopier.downloadUnix(GenericViewFrame.this.dirName, GenericViewFrame.this);
				}
				JOptionPane.showMessageDialog(GenericViewFrame.this, "Download started...");
			}});
		file.add(download);
		
		upload = new JMenuItem("Upload to EV3");
		upload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isWindows) {
					RemoteCopier.uploadWin(GenericViewFrame.this.dirName, GenericViewFrame.this);
				} else {
					RemoteCopier.uploadUnix(GenericViewFrame.this.dirName, GenericViewFrame.this);
				}
				JOptionPane.showMessageDialog(GenericViewFrame.this, "Upload started...");
			}});
		file.add(upload);
	}

	@Override
	public void complete(boolean success) {
		JOptionPane.showMessageDialog(this, "File transfer " + (success ? "succeeded" :"failed"));
	}
	
	private void openAll() {
		S storage = getStorage();
		for (String choice: storage.choices()) {
			try {
				openChoice(storage, choice);
			} catch (FileNotFoundException exc) {
				JOptionPane.showMessageDialog(GenericViewFrame.this, "Could not open \"" + choice + "\"");
			}
		}
	}
	
	private void openChoice(S storage, String choice) throws FileNotFoundException {
		V panel = makeViewPanel(storage.open(choice));
		allGNGs.addTab(choice, panel);
		name2panel.put(choice, panel);		
	}
}
