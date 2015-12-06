package finder;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import plugins.Plugin;
import plugins.PluginLoader;

import event.*;
import exception.PluginLoadingException;
import execution.*;

public class PluginFinder implements ActionListener {

	static protected HashMap<String,Plugin> mainMap;

	protected File file;
	protected PluginFilter filter;
	protected HashMap<String,File> newMap;
	protected ArrayList<PluginObserver> observers;
	protected ArrayList<Plugin> adds;
	protected ArrayList<Plugin> updates;
	protected ArrayList<Plugin> deletions;

	public PluginFinder(String dir) {
		// if(!new File(dir).exists()) throw new NotAFileException();
		file = new File(dir);
		filter = new PluginFilter();
		newMap = new HashMap<String,File>();
		mainMap = new HashMap<String,Plugin>();
		observers = new ArrayList<PluginObserver>();
		adds = new ArrayList<Plugin>();
		updates = new ArrayList<Plugin>();
		deletions = new ArrayList<Plugin>();
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
	 * @throws PluginLoadingException 
	 */
	public ArrayList<Plugin> addPlugin() throws PluginLoadingException {
		PluginLoader loader = new PluginLoader();
		for (String name: newMap.keySet()) {
			if (!mainMap.containsKey(name)) {
				Plugin plugin = loader.loadPlugin(newMap.get(name));
				adds.add(plugin);
				mainMap.put(name,plugin);
			}
		}
		return adds;
	}

	/**
	 * Check if a file has been updated between 2 consecutive calls
	 * 
	 * @return true if a file was updated between 2 calls
	 * @throws PluginLoadingException 
	 */
	public ArrayList<Plugin> updatePlugin() throws PluginLoadingException {
		for (String name : newMap.keySet()) {
			for (String pname : mainMap.keySet()) {
				if (name.equals(pname)) {
					if (System.currentTimeMillis() - newMap.get(name).lastModified() <= 1000) {
						PluginLoader loader = new PluginLoader();
						Plugin plugin = loader.loadPlugin(newMap.get(name));
						updates.add(plugin);
						mainMap.put(name,plugin);
					}
				}
			}
		}
		return updates;
	}

	/**
	 * Check if files were deleted between 2 consecutive calls
	 * 
	 * @return
	 * @return true file has been deleted
	 * @throws PluginLoadingException 
	 */
	public ArrayList<Plugin> deletePlugin() throws PluginLoadingException {
		for (String name : mainMap.keySet()) {
			if (!newMap.containsKey(name)) {
				Plugin plugin = mainMap.get(name);
				deletions.add(plugin);
				mainMap.remove(name);
			}
		}
		return deletions;
	}
	
	/**
	 * Execute the action performed of
	 * <code>this<code>registered PluginObservers.
	 * 
	 * @param the action event to be executed.
	 */
	public void actionPerformed(ActionEvent e) {
		PluginEvent event = null;
		File[] files = file.listFiles(filter);
		for (int i = 0; i < files.length; i++) {
			newMap.put(files[i].getName(), files[i]);
		}
        try {
			addPlugin();
			updatePlugin();
			deletePlugin();
		} catch (PluginLoadingException e1) {
			System.out.println("exception");
		}	
		if (adds.size() != 0) {
			for (Plugin plugin : adds) {
				event = new PluginAddedEvent(plugin);
				this.updateObservers(event);
			}
		}
		if (updates.size() != 0) {
			for (Plugin plugin : updates) {
				event = new PluginUpdatedEvent(plugin);
				this.updateObservers(event);
			}
		}
		if (deletions.size() != 0) {
			for (Plugin plugin : deletions) {
				event = new PluginDeletedEvent(plugin);
				mainMap.remove(plugin.getLabel());
				this.updateObservers(event);
			}
		}
		newMap.clear();
		adds.clear();
		updates.clear();
		deletions.clear();
	}

	public void updateObservers(PluginEvent event) {
		for (PluginObserver observer : observers) {
			observer.update(event);
		}
	}

}
