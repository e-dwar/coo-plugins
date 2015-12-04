package execution;

import java.util.ArrayList;

import finder.Event;
import finder.PluginFinder;
import plugins.Plugin;

public abstract class PluginUI {
	
	/*
	 * Attributes
	 */
	protected PluginFinder pluginFinder;
	protected ArrayList<Plugin> plugins;
	
	/*
	 * Constructors
	 */
	public PluginUI(PluginFinder pluginFinder){
		this.pluginFinder = pluginFinder;
		pluginFinder.addObserver(Event.ADD, new AddedObserver(this));
		pluginFinder.addObserver(Event.UPDATE, new UpdatedObserver(this));
		pluginFinder.addObserver(Event.DELETE, new DeletedObserver(this));
	}

	/*
	 * Methods
	 */
	public abstract void onPluginAdded(Plugin plugin);
	public abstract void onPluginUpdated(Plugin plugin);
	public abstract void onPluginDeleted(Plugin plugin);
		
	public void updatePlugin(Plugin plugin){
		Plugin toDelete = null;
		for(Plugin pluginTemp : plugins){
			if(pluginTemp.getLabel().equals(plugin.getLabel())){
				toDelete = pluginTemp;
			}
		}
		plugins.remove(toDelete);
		plugins.add(plugin);
	}
	
	public ArrayList<Plugin> getPlugins(){
		return this.plugins;
	}
	
}
