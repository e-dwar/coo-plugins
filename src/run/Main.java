package run;


import javax.swing.Timer;

import finder.PluginFinder;
import execution.*;


public class Main {

		
	public static void main(String[] args) {

		PluginFinder finder = new PluginFinder("./Dropins");
		PluginListener plugin1 = new PluginAddedLogger();
		
		finder.addListeners(plugin1);
	
		new Timer(1000, finder).start();
		while(true);
	}

}
