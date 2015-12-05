package execution;

import plugins.Plugin;

public class PluginLogger extends PluginObserver {
	
	/*
	 * Constructors
	 */
	public PluginLogger(){
		super();
	}
	
	/*
	 * Methods
	 */	
	@Override
	public void addPlugin(Plugin plugin) {
		super.addPlugin(plugin);
		System.out.println("The plugin " + plugin.getLabel() + " has been added.");
	}

	@Override
	public void updatePlugin(Plugin plugin) {
		super.updatePlugin(plugin);
		System.out.println("The plugin " + plugin.getLabel() + " has been updated.");
	}

	@Override
	public void deletePlugin(Plugin plugin) {
		super.deletePlugin(plugin);
		System.out.println("The plugin " + plugin.getLabel() + " has been deleted.");
	}
}
