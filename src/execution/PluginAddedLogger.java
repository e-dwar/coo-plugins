package execution;

import java.awt.event.ActionEvent;

public class PluginAddedLogger extends PluginListener {

	/*
	 * Methods
	 */
	public PluginAddedLogger() {
		
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("a plugin has beed added.");
	}

}
