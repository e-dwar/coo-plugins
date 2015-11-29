package run;


import javax.swing.Timer;

import finder.Event;
import finder.PluginFinder;
import execution.*;


public class Main {

		
	public static void main(String[] args) {

		final PluginFinder finder = new PluginFinder("dropins");
		PluginListener plugin1 = new PluginAddedLogger();
		PluginListener plugin2 = new PluginDeletedLogger();
		PluginListener plugin3 = new PluginUpdatedLogger();

		finder.addListeners(plugin1,Event.add);
		finder.addListeners(plugin2,Event.delete);
		finder.addListeners(plugin3,Event.update);
		
		new Timer(1000, finder).start();
		while(true);
	}

}