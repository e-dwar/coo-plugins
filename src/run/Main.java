package run;

import javax.swing.Timer;

import finder.PluginFinder;
import execution.*;

public class Main {

	public static void main(String[] args) {

		PluginFinder finder = new PluginFinder("dropins");
		PluginObserver logger = new PluginLogger();
		PluginObserver ui = new PluginUserInterface();
		finder.addObserver(logger);
		finder.addObserver(ui);
		new Timer(1000, finder).start();
		while (true);
	}

}