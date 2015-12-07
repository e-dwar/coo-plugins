package execution;

import java.util.ArrayList;

import event.Observer;
import event.PluginEvent;
import finder.PluginFinder;

import plugins.Plugin;

public abstract class PluginObserver implements Observer<PluginEvent> {

	/*
	 * Attributes
	 */
	protected PluginFinder pluginFinder;
	protected ArrayList<Plugin> plugins;

	/*
	 * Constructors
	 */
	public PluginObserver() {
		plugins = new ArrayList<Plugin>();
	}
	
	/*
	 * Methods
	 */
	/**
	 * Adds a new plugin to the list plugins.
	 * @param plugin
	 */
	public void addPlugin(Plugin plugin){
		plugins.add(plugin);
	}
	
	/**
	 * Delete the given plugin from the list plugins.
	 * @param plugin
	 */
	public void deletePlugin(Plugin plugin){
		plugins.remove(plugin);
	}

	/**
	 * Update the given plugin from the list plugins.
	 * @param plugin
	 */
	public void updatePlugin(Plugin plugin) {
		this.deletePlugin(plugin);
		this.addPlugin(plugin);
	}

	/**
	 * @return the list of plugins.
	 */
	public ArrayList<Plugin> getPlugins() {
		return this.plugins;
	}

	/**
	 * Calls the methods which matchs with the type of the event.
	 * If event is an instance of PluginAddedEvent, it will calls the method addPlugin()
	 */
	public void update(PluginEvent event) {
		event.visitPluginObserver(this);
	}
}
