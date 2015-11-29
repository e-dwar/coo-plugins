package execution;

import java.awt.event.ActionEvent;

import frame.PluginFrame;

public class PluginUserInterface extends PluginListener {

	/*
	 * Attributes
	 */
	protected PluginFrame pluginFrame;
	
	/*
	 * Constructors
	 */
	public PluginUserInterface(){
		pluginFrame = new PluginFrame();
	}
	
	/*
	 * Methods
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("I've received somthing !!!");
		pluginFrame.update();
	}

}
