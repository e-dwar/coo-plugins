package execution;

import plugins.Plugin;

public class PluginLogger extends PluginObserver {
	
	/*
	 * Methods
	 */	
	
	@Override
	public void addPlugin(Plugin plugin) {
		plugins.add(plugin);
		System.out.println("The plugin " + plugin.getLabel() + " has been added.");
	}

	@Override
	public void updatePlugin(Plugin plugin) {
		super.updatePlugin(plugin);
		System.out.println("The plugin " + plugin.getLabel() + " has been updated.");
	}

	@Override
	public void deletePlugin(Plugin plugin) {
		plugins.remove(plugin);
		System.out.println("The plugin " + plugin.getLabel() + " has been deleted.");
	}
}
