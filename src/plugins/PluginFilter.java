package plugins;
import java.io.File;
import java.io.FilenameFilter;

public class PluginFilter implements FilenameFilter
{
    public boolean accept (File dir, String name)
    {
        return name.endsWith(".class");
    }
}
