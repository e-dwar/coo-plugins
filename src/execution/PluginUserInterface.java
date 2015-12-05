package execution;

import plugins.Plugin;
import frame.PluginFrame;

public class PluginUserInterface extends PluginObserver {

	/*
	 * Attributes
	 */
	protected PluginFrame pluginFrame;
	
	/*
	 * Constructors
	 */
	public PluginUserInterface(){
		super();
		pluginFrame = new PluginFrame();
	}

	
	
	/*
	 * Methods
	 */
	@Override
	public void addPlugin(Plugin plugin) {
		super.addPlugin(plugin);
		pluginFrame.addPlugin(plugin);
	}

	@Override
	public void updatePlugin(Plugin plugin) {
		super.updatePlugin(plugin);
		pluginFrame.update(plugin);
	}

	@Override
	public void deletePlugin(Plugin plugin) {
		super.deletePlugin(plugin);
		pluginFrame.delete(plugin);
	}


}
