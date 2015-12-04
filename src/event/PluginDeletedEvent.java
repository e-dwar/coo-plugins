package event;

import execution.PluginObserver;
import plugins.Plugin;

public class PluginDeletedEvent extends PluginEvent {

	public PluginDeletedEvent(Plugin plugin) {
		super(plugin);
	}

	@Override
	public void visitPluginObserver(PluginObserver observer) {
		observer.deletePlugin(plugin);
	}
	
}
