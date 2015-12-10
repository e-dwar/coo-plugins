package doubles;

import java.io.File;
import plugins.Plugin;
import exception.PluginLoadingException;

public class PluginLoader extends plugins.PluginLoader {

	public Plugin loadPlugin(File file) throws PluginLoadingException {
		return new Plugin() {

			public String transform(String text) {
				return "transform(" + text + ")";
			}

			public String getLabel() {
				return "fake";
			}
		};
	}
}
