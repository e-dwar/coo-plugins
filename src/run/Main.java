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
import finder.PluginFinder;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {
		main1();
	}

	private static void main1() {
		PluginFinder finder = new PluginFinder(new File("dropins"));
		PluginObserver logger = new PluginLogger();
		PluginObserver ui = new PluginUserInterface();
		finder.addObserver(logger);
		finder.addObserver(ui);
		new Timer(1000, finder).start();
		while (true);
	}

}
