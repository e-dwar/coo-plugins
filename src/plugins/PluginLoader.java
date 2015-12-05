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

    @Override
    public Class<?> loadClass(String className) {
        return findClass(className);
    }

    @Override
    public Class<?> findClass(String className) {
        try {
            byte[] bytes = loadClassData(className);
            return defineClass(className, bytes, 0, bytes.length);
        } catch (IOException ioe) {
            try {
                return super.loadClass(className);
            } catch (ClassNotFoundException ignore) { }
            ioe.printStackTrace(System.out);
            return null;
        }
    }

    private byte[] loadClassData(String className) throws IOException {
        File f = new File("dropins/" + className.replaceAll("\\.", "/") + ".class");
        int size = (int) f.length();
        byte buff[] = new byte[size];
        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        dis.readFully(buff);
        dis.close();
        return buff;
    }
    
    public Plugin loadPlugin(String className) throws PluginLoadingException {
    	try {
    		return (Plugin)(this.loadClass(className).newInstance());
    	} catch (Exception e) {
    		throw new PluginLoadingException(e.getMessage());
    	}
    }
    
    public Plugin loadPlugin(File file) throws PluginLoadingException {
    	return this.loadPlugin(file.getName());
    }
}
