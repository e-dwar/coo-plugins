package event;

import execution.PluginObserver;
import plugins.Plugin;

public abstract class PluginEvent implements Event {

	/*
	 * Attributes
	 */
	protected Plugin plugin;

	/*
	 * Constructors
	 */
	public PluginEvent(Plugin plugin) {
		this.plugin = plugin;
	}

	/*
	 * Methods
	 */
	public Plugin getPlugin() {
		return plugin;
	}

	public abstract void visitPluginObserver(PluginObserver observer);
	public abstract boolean equals(Object o);
}
