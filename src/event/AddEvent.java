package event;

public class AddEvent extends PluginEvent {

	private static final long serialVersionUID = 1L;
	
	public AddEvent(Object source, int id, String command) {
		super(source, id, command);
	}

	public AddEvent(Object source, int id, String command, int modifiers) {
		super(source, id, command, modifiers);
	}

	public AddEvent(Object source, int id, String command, long when, int modifiers) {
		super(source, id, command, when, modifiers);
	}
	

}
