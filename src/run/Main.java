package run;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import plugins.Plugin;
import plugins.PluginLoader;

public class Main {

	public static void main(String[] args) {

		// PluginFinder finder = new PluginFinder("dropins");
		// PluginObserver logger = new PluginLogger();
		// PluginObserver ui = new PluginUserInterface();
		// finder.addObserver(logger);
		// finder.addObserver(ui);
		// new Timer(1000, finder).start();

		// instructions pour tester le loader:
		//
		//  1. lancer le main dans eclipse
		//  2. aller dans le dossier du projet avec le terminal
		//  3. lancer la commande 'cp edwar/AlloCamel dropins/Allo.class'
		//  4. observer si les prints changent
		//  5. lancer la commande 'cp edwar/AlloVnr dropins/Allo.class'
		//  6. observer si les prints changent
		//  5. lancer la commande 'cp edwar/AlloDummy dropins/Allo.class'
		//  6. observer si les prints changent (AlloDummy n'implémente pas Plugin)

		new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					PluginLoader loader = new PluginLoader();
					Plugin plugin = loader.loadPlugin("Allo");
					System.out.println(plugin.transform("allo"));
				} catch (Exception e) {
					System.out.println("actionPerformed: " + e.getMessage());
				}
			}
		}).start();

		while (true)
			;
	}

}