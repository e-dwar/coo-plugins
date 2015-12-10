package doubles;

import java.io.File;

import plugins.Plugin;
import plugins.PluginLoader;
import exception.PluginLoadingException;

public class PluginFinder extends finder.PluginFinder {

	public PluginFinder(File directory) {
		super(directory);
	}

	public PluginLoader createPluginLoader() {
		return new PluginLoader() {
			
			public Plugin loadPlugin (File file) throws PluginLoadingException {
				return new Plugin(){

					public String transform(String text) {
						return "transform(" + text + ")";
					}

					public String getLabel() {
						return "fake";
					}
					
				};
			}
		};
	}
}
