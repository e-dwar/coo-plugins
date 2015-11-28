import java.io.File;

public class PluginFinder
{
    private File file;
    private PluginFilter filter;

    public PluginFinder (String dir)
    {
        file = new File(dir);
        filter = new PluginFilter();
    }

    public File[] listBytecodeFiles ()
    {
        return file.listFiles(filter);
    }
}
