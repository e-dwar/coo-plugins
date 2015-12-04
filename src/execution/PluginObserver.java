package execution;

import java.util.ArrayList;

import event.Observer;
import event.PluginEvent;
import finder.PluginFinder;

import plugins.Plugin;

public abstract class PluginObserver implements Observer<PluginEvent> {

	protected PluginFinder pluginFinder;
	protected ArrayList<Plugin> plugins;

	public abstract void addPlugin(Plugin plugin);

	public abstract void deletePlugin(Plugin plugin);

	public PluginObserver() {
		plugins = new ArrayList<Plugin>();
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
