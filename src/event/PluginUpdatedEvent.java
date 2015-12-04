package event;

import execution.PluginObserver;
import plugins.Plugin;

public class PluginUpdatedEvent extends PluginEvent {

	public PluginUpdatedEvent(Plugin plugin) {
		super(plugin);
	}

	@Override
	public void visitPluginObserver(PluginObserver observer) {
		observer.updatePlugin(plugin);
	}
	
}
