package observer;

import plugins.Plugin;
import execution.PluginUI;

public class UpdatedObserver extends PluginObserver {

	public UpdatedObserver(PluginUI mainPluginUI) {
		super(mainPluginUI);
	}
	
	public void update(Plugin plugin){
		this.mainPluginUI = onPluginUpdated(plugin);
	}
}
