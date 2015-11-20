public class PluginFinderMain
{
    public static void main (String[] args)
    {
        int i;
        PluginFinder finder = new PluginFinder(".");
        String[] files = finder.listBytecodeFiles();
        System.out.println("---- plugin finder");
        for (i = 0; i < files.length; i++) {
            System.out.println(files[i]);
        }
    }
}
