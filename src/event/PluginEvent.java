package event;

import java.awt.event.ActionEvent;

public class PluginEvent extends ActionEvent {

	/*
	 * Attributes
	 */
	
	private static final long serialVersionUID = 1L;
	
	protected String nameOfPlugin;
	

	/*
	 * Constructors
	 */
	
	public PluginEvent(Object source, int id, String command) {
		super(source, id, command);
	}

	public PluginEvent(Object source, int id, String command, int modifiers) {
		super(source, id, command, modifiers);
	}

	public PluginEvent(Object source, int id, String command, long when, int modifiers) {
		super(source, id, command, when, modifiers);
	}
	
	/*
	 * Methods
	 */

}
