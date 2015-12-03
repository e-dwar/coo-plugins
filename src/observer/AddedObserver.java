package observer;

import plugins.Plugin;
import execution.PluginUI;

public class AddedObserver extends PluginObserver {

	public AddedObserver(PluginUI mainPluginUI) {
		super(mainPluginUI);
	}

	public void update(Plugin plugin){
		this.mainPluginUI = onPluginAdded(plugin);
	}
}
