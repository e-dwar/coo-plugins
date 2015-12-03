package observer;

import plugins.Plugin;
import execution.PluginUI;

public class DeletedObserver extends PluginObserver {

	public DeletedObserver(PluginUI mainPluginUI) {
		super(mainPluginUI);
	}

	public void update(Plugin plugin){
		this.mainPluginUI = onPluginDeleted(plugin);
	}
}
