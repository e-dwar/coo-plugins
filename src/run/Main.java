package run;

import javax.swing.Timer;

import finder.PluginFinder;
import execution.*;

public class Main {

	public static void main(String[] args) {

		final PluginFinder finder = new PluginFinder("dropins");
		PluginObserver observer = new PluginLogger();
		finder.addObserver(observer);
		new Timer(1000, finder).start();
		while (true);
	}

}