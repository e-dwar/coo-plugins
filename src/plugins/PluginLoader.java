package plugins;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import exception.PluginLoadingException;

public class PluginLoader extends ClassLoader {

	public PluginLoader() {
		super(PluginLoader.class.getClassLoader());
	}

	public Plugin loadPlugin(File file) throws PluginLoadingException {
		Class<?> cls = null;
		String clsName = file.getName();
		int i = clsName.lastIndexOf(".");
		clsName = clsName.substring(0, i);
		int size = (int) file.length();
		byte bytes[] = new byte[size];
		try {
			FileInputStream fis = new FileInputStream(file);
			DataInputStream dis = new DataInputStream(fis);
			dis.readFully(bytes);
			dis.close();
			cls = defineClass(clsName, bytes, 0, bytes.length);
			cls = super.loadClass(clsName);
			return (Plugin) cls.newInstance();
		} catch (Exception e) {
			throw new PluginLoadingException(e.getMessage());
		}
	}
}
