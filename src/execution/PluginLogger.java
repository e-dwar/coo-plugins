package execution;

import finder.PluginFinder;
import plugins.Plugin;

public class PluginLogger extends PluginUI {

	/*
	 * Constructors
	 */
	public PluginLogger(PluginFinder pluginFinder){
		super(pluginFinder);
	}
	
	/*
	 * Methods
	 */	
	
	@Override
	public void onPluginAdded(Plugin plugin) {
		plugins.add(plugin);
		System.out.println("The plugin " + plugin.getLabel() + " has been added.");
	}

	@Override
	public void onPluginUpdated(Plugin plugin) {
		updatePlugin(plugin);
		System.out.println("The plugin " + plugin.getLabel() + " has been updated.");
	}

	@Override
	public void onPluginDeleted(Plugin plugin) {
		plugins.remove(plugin);
		System.out.println("The plugin " + plugin.getLabel() + " has been deleted.");
	}

}
