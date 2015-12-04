package event;

import execution.PluginObserver;
import plugins.Plugin;

public abstract class PluginEvent implements Event {

	protected Plugin plugin;

	public PluginEvent(Plugin plugin) {
		this.plugin = plugin;
	}

	public Plugin getPlugin() {
		return plugin;
	}
	
	public abstract void visitPluginObserver(PluginObserver observer);
}
