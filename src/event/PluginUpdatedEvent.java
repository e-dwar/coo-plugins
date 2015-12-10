package event;

import execution.PluginObserver;
import plugins.Plugin;

public class PluginUpdatedEvent extends PluginEvent {

	/*
	 * Constructors
	 */
	public PluginUpdatedEvent(Plugin plugin) {
		super(plugin);
	}

	/*
	 * Methods
	 */
	@Override
	public void visitPluginObserver(PluginObserver observer) {
		observer.updatePlugin(plugin);
	}
	
	public boolean equals(Object o) {
		return o instanceof PluginUpdatedEvent;
	}
	
}
