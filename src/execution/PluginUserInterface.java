package execution;

import plugins.Plugin;

import finder.PluginFinder;
import frame.PluginFrame;

public class PluginUserInterface extends PluginUI {

	/*
	 * Attributes
	 */
	protected PluginFrame pluginFrame;
	
	/*
	 * Constructors
	 */
	public PluginUserInterface(PluginFinder pluginFinder){
		super(pluginFinder);
		pluginFrame = new PluginFrame();
	}

	
	
	/*
	 * Methods
	 */
	@Override
	public void onPluginAdded(Plugin plugin) {
		plugins.add(plugin);
		/*
		 * Appelle la méthode dans PluginFrame qui ajoute un élément au menu
		 */
	}

	@Override
	public void onPluginUpdated(Plugin plugin) {
		updatePlugin(plugin);
		/*
		 * Appelle la méthode qui met à jour le plugin dans le menu
		 */
	}

	@Override
	public void onPluginDeleted(Plugin plugin) {
		plugins.remove(plugin);
		/*
		 * Appelle la méthode qui supprime le plugin du menu.
		 */
	}


}
