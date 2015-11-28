package execution;

import java.awt.event.ActionEvent;

public class PluginUpdatedLogger extends PluginListener {

	/*
	 * Methods
	 */
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("a plugin has beed updated.");
	}

}
