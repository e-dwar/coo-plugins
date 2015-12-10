package event;

import execution.PluginObserver;
import plugins.Plugin;

public class PluginDeletedEvent extends PluginEvent {

	/*
	 * Constructors
	 */
	public PluginDeletedEvent(Plugin plugin) {
		super(plugin);
	}

	/*
	 * Methods
	 */
	@Override
	public void visitPluginObserver(PluginObserver observer) {
		observer.deletePlugin(plugin);
	}
	
	public boolean equals(Object o) {
		return o instanceof PluginDeletedEvent;
	}
	
}
