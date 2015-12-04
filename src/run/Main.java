package run;

import javax.swing.Timer;

import finder.PluginFinder;
import execution.*;

public class Main {

	public static void main(String[] args) {

		PluginFinder finder = new PluginFinder("dropins");
		PluginObserver logger = new PluginLogger();
		finder.addObserver(logger);
		new Timer(1000, finder).start();
		while (true);
	}

}