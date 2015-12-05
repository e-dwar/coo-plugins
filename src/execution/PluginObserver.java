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
	public void addPlugin(Plugin plugin){
		plugins.add(plugin);
	}
	
	public void deletePlugin(Plugin plugin){
		plugins.remove(plugin);
	}

	public void updatePlugin(Plugin plugin) {
		Plugin toDelete = null;
		for (Plugin pluginTemp : plugins) {
			if (pluginTemp.getLabel().equals(plugin.getLabel())) {
				toDelete = pluginTemp;
			}
		}
		plugins.remove(toDelete);
		plugins.add(plugin);
	}

	public ArrayList<Plugin> getPlugins() {
		return this.plugins;
	}

	public void update(PluginEvent event) {
		event.visitPluginObserver(this);
	}
}
