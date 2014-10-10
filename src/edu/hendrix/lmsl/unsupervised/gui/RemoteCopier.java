package edu.hendrix.lmsl.unsupervised.gui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class RemoteCopier extends Thread {	
	private String[] args;
	private ArrayList<RemoteCopyListener> listeners;
	
	private RemoteCopier(String cmd, String src, String dest, RemoteCopyListener listener) {
		this.args = new String[]{cmd, "-r", src, dest};
		listeners = new ArrayList<RemoteCopyListener>();
		listeners.add(listener);
	}
	
	public static String getHomeDir() {
		return System.getProperty("user.home");
	}
	
	public static void downloadUnix(String dirName, RemoteCopyListener listener) {
		new RemoteCopier("scp", "root@10.0.1.1:/home/root/" + dirName, getHomeDir(), listener).start();
	}
	
	public static void uploadUnix(String dirName, RemoteCopyListener listener) {
		new RemoteCopier("scp", getHomeDir() + "/" + dirName, "root@10.0.1.1:/home/root", listener).start();
	}
	
	@Override
	public void run() {		
		try {
			Process p = Runtime.getRuntime().exec(args);
			PrintWriter writer = new PrintWriter(p.getOutputStream());
			writer.println();
			int result = p.waitFor();
			writer.close();
			notifyListener(result == 0);
		} catch (IOException | InterruptedException e) {
			notifyListener(false);
		}
	}
	
	private void notifyListener(boolean success) {
		for (RemoteCopyListener rcl: listeners) {
			rcl.complete(success);
		}
	}

	public static void downloadWin(String string, RemoteCopyListener listener) {
		JOptionPane.showMessageDialog(null, "Sorry, no Windows implementation yet");
	}

	public static void uploadWin(String string, RemoteCopyListener listener) {
		JOptionPane.showMessageDialog(null, "Sorry, no Windows implementation yet");
	}
}
