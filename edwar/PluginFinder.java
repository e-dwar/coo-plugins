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

    public String[] listBytecodeFiles ()
    {
        return file.list(filter);
    }
}
