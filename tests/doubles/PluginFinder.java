package doubles;

import java.io.File;
import java.util.ArrayList;
import plugins.Plugin;
import execution.PluginObserver;

public class PluginFinder extends finder.PluginFinder {

	public PluginFinder(File directory) {
		super(directory);
	}

	public PluginLoader createPluginLoader() {
		return new PluginLoader();
	}
	
	protected Plugin createPlugin(){
		return new Plugin(){

			public String transform(String text) {
				return "transform(" + text + ")";
			}

			public String getLabel() {
				return "fake";
			}
		};
	}
	
	public Value putCache(File file) {
		Plugin plugin = createPlugin();
		long lastModified = file.lastModified();
		Value value = new Value(plugin, lastModified);
		cache.put(file.getName(), value);
		return value;
	}
	
	public ArrayList<PluginObserver> getObservers() {
		return observers;
	}
}
