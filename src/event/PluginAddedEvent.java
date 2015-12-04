package event;

import execution.PluginObserver;
import plugins.Plugin;

public class PluginAddedEvent extends PluginEvent {

	public PluginAddedEvent(Plugin plugin) {
		super(plugin);
	}

	@Override
	public void visitPluginObserver(PluginObserver observer) {
		observer.addPlugin(plugin);
	}
	
}
