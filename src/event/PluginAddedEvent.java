package event;

import execution.PluginObserver;
import plugins.Plugin;

public class PluginAddedEvent extends PluginEvent {

	/*
	 * Constructors
	 */
	public PluginAddedEvent(Plugin plugin) {
		super(plugin);
	}

	/*
	 * Methods
	 */
	@Override
	public void visitPluginObserver(PluginObserver observer) {
		observer.addPlugin(plugin);
	}
	
	public boolean equals(Object o) {
		return o instanceof PluginAddedEvent;
	}
	
}
