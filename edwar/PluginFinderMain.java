import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class PluginFinderMain
{
    public static void main (String[] args)
    {
        PluginFinder finder = new PluginFinder(".");

        new Timer(2 * 1000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                File[] files = finder.listBytecodeFiles();
                System.out.println("---- " + files.length + " files found ----");
                for (int i = 0; i < files.length; i++) {
                    System.out.println(files[i]);
                }
            }
        }).start();
        while (true);
    }
}
