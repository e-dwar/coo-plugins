package run;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Timer;

import plugins.Plugin;
import plugins.PluginLoader;

import execution.PluginLogger;
import execution.PluginObserver;
import execution.PluginUserInterface;
import finder.PluginFinder;

public class Main {

	public static void main(String[] args) {
		main1();
	}
	@SuppressWarnings("unused")

	private static void main2() {
		// instructions pour tester le loader:
		//
		// 1. lancer le main dans eclipse
		// 2. aller dans le dossier du projet avec le terminal
		// 3. lancer la commande 'cp edwar/AlloCamel dropins/Allo.class'
		// 4. observer si les prints changent
		// 5. lancer la commande 'cp edwar/AlloVnr dropins/Allo.class'
		// 6. observer si les prints changent
		// 5. lancer la commande 'cp edwar/AlloDummy dropins/Allo.class'
		// 6. observer si les prints changent (AlloDummy n'implémente pas
		// Plugin)
		// 7. lancer la commande 'rm dropins/Allo.class'
		// 8. observer si les prints changent
		
		new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					File file = new File("dropins/Allo.class");
					PluginLoader loader = new PluginLoader();
					Plugin plugin = loader.loadPlugin(file);
					System.out.println(plugin.transform("allo"));
				} catch (Exception e) {
					System.out.println("Error from main():\n  " + e.getMessage());
				}
			}
		}).start();
		while (true);		
	}

	private static void main1() {
		PluginFinder finder = new PluginFinder("dropins");
		PluginObserver logger = new PluginLogger();
		PluginObserver ui = new PluginUserInterface();
		finder.addObserver(logger);
		finder.addObserver(ui);
		new Timer(1000, finder).start();
		while (true);
	}

}
