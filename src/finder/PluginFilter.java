package finder;
import java.io.File;
import java.io.FilenameFilter;

public class PluginFilter implements FilenameFilter {
	
	/**
	 * Accept method 
	 */
    public boolean accept (File dir, String name) {
        return name.endsWith(".class");
    }
}
