package plugins;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import exception.PluginLoadingException;

public class PluginLoader extends ClassLoader {

	public PluginLoader() {
		super(PluginLoader.class.getClassLoader());
	}

	// see http://stackoverflow.com/a/3971771/1636522
	public Plugin loadPlugin(File file) throws PluginLoadingException {
		Class<?> cls = null;
		String clsName = file.getName();
		int i = clsName.lastIndexOf(".");
		int size = (int) file.length();
		byte bytes[] = new byte[size];
		clsName = "plugins." + clsName.substring(0, i);
		try {
			FileInputStream fis = new FileInputStream(file);
			DataInputStream dis = new DataInputStream(fis);
			dis.readFully(bytes);
			dis.close();
			cls = defineClass(clsName, bytes, 0, bytes.length);
		} catch (IOException ioe) {
			try {
				cls = super.loadClass(clsName);
			} catch (ClassNotFoundException e) {
				throw new PluginLoadingException(e.getMessage());
			}
		}
		try {
			return (Plugin) cls.newInstance();
		} catch (Exception e) {
			throw new PluginLoadingException(e.getMessage());
		}
	}
}
