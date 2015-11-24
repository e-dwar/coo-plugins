package event;

public class DeleteEvent extends PluginEvent {

	private static final long serialVersionUID = 1L;

	public DeleteEvent(Object source, int id, String command) {
		super(source, id, command);
	}

	public DeleteEvent(Object source, int id, String command, int modifiers) {
		super(source, id, command, modifiers);
	}

	public DeleteEvent(Object source, int id, String command, long when, int modifiers) {
		super(source, id, command, when, modifiers);
	}

}
