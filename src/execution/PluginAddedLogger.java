package execution;

import java.awt.event.ActionEvent;

public class PluginAddedLogger extends PluginListener {

	/*
	 * Methods
	 */
	public void actionPerformed(ActionEvent event) {
		System.out.println("a plugin has been added.");
	}

}
