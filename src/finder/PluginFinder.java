package finder;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

import plugins.FakePlugin;
import plugins.Plugin;

import event.*;
import execution.*;

public class PluginFinder implements ActionListener {

	static protected ArrayList<File> pastList;

	protected File file;
	protected PluginFilter filter;
	protected ArrayList<File> newList;
	protected ArrayList<PluginObserver> observers;

	public PluginFinder(String dir) {
		// if(!new File(dir).exists()) throw new NotAFileException();
		file = new File(dir);
		filter = new PluginFilter();
		newList = new ArrayList<File>();
		pastList = new ArrayList<File>();
		observers = new ArrayList<PluginObserver>();
	}

	/**
	 * Add an observer to <code>this<code> pluginFinder.
	 */
	public void addObserver(PluginObserver observer) {
		observers.add(observer);
	}

	/**
	 * Check if files were added between 2 consecutive calls
	 * 
	 * @return true a file has been added
	 */
	public boolean hasBeenAdded() {
		for (File file : newList) {
			if (!pastList.contains(file)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if files were deleted between 2 consecutive calls
	 * 
	 * @return
	 * @return true file has been deleted
	 */
	public boolean hasBeenDeleted() {
		for (File file : pastList) {
			if (!newList.contains(file)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if a file has been updated between 2 consecutive calls
	 * 
	 * @return true if a file was updated between 2 calls
	 */
	public boolean hasBeenUpdated() {
		for (File file : newList) {
			for (File pfile : pastList) {
				if (file.equals(pfile)) {
					if (System.currentTimeMillis() - file.lastModified() <= 1000) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Execute the action performed of
	 * <code>this<code>registered PluginObservers.
	 * 
	 * @param the action event to be executed.
	 */
	public void actionPerformed(ActionEvent e) {
		PluginEvent event = null;
		Plugin plugin = new FakePlugin();
		File[] files = file.listFiles(filter);
		for (int i = 0; i < files.length; i++) {
			newList.add(files[i]);
		}
		if (hasBeenAdded()) {
			event = new PluginAddedEvent(plugin);
		}
		if (hasBeenUpdated()) {
			event = new PluginUpdatedEvent(plugin);
		}
		if (hasBeenDeleted()) {
			event = new PluginDeletedEvent(plugin);
		}
		if (event != null) {
			this.updateObservers(event);
		}
		pastList.clear();
		pastList.addAll(newList);
		newList.clear();
	}

	public void updateObservers(PluginEvent event) {
		for (PluginObserver observer : observers) {
			observer.update(event);
		}
	}

}