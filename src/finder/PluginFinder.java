package finder;

import java.awt.event.*;
import java.io.File;
import java.util.*;
import exception.PluginLoadingException;
import execution.PluginObserver;
import plugins.*;
import event.*;

public class PluginFinder implements ActionListener {

	protected File directory;
	protected PluginFilter filter;
	protected HashMap<String, Value> cache;
	protected ArrayList<PluginObserver> observers;

	public PluginFinder(File directory) {
		this.directory = directory;
		this.observers = new ArrayList<PluginObserver>();
		this.cache = new HashMap<String, Value>();
		this.filter = new PluginFilter();
	}

	/**
	 * Adds an observer to <code>this<code> pluginFinder.
	 * 
	 * @param observer The observer to add.
	 */
	public void addObserver(PluginObserver observer) {
		observers.add(observer);
	}

	/**
	 * Notifies all observers when something changes in <code>directory</code>.
	 * 
	 * @param event
	 */
	public void updateObservers(PluginEvent event) {
		for (PluginObserver observer : observers) {
			observer.update(event);
		}
	}

	protected boolean hasBeenAdded(File file) {
		return !cache.containsKey(file.getName());
	}

	protected boolean hasBeenUpdated(File file) {
		Value value = cache.get(file.getName());
		return value.lastModified != file.lastModified();
	}

	protected class Value {
		public Plugin plugin;
		public long lastModified;

		public Value(Plugin plugin, long lastModified) {
			this.plugin = plugin;
			this.lastModified = lastModified;
		}
	};

	/**
	 * @see java.awt.event.ActionListener#actionPerfomed(ActionEvent)
	 */
	public void actionPerformed(ActionEvent actionEvent) {
		File[] files = directory.listFiles(filter);
		HashMap<String, Value> fresh = new HashMap<String, Value>();
		for (File file : files) {
			String name = file.getName();
			if (hasBeenAdded(file) || hasBeenUpdated(file)) {
				try {
					PluginLoader loader = createPluginLoader();
					Plugin plugin = loader.loadPlugin(file);
					Value value = new Value(plugin, file.lastModified());
					fresh.put(name, value);
					if (hasBeenAdded(file)) {
						updateObservers(new PluginAddedEvent(plugin));
					} else {
						cache.remove(name);
						updateObservers(new PluginUpdatedEvent(plugin));
					}
				} catch (PluginLoadingException e) {
					System.out.println(e.getMessage());
				}
			} else {
				fresh.put(name, cache.remove(name));
			}
		}
		for (Value value : cache.values()) {
			updateObservers(new PluginDeletedEvent(value.plugin));
		}
		cache = fresh;
	}
	
	protected PluginLoader createPluginLoader(){
		return new PluginLoader();
	}
}
