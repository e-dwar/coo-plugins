package event;

public class UpdateEvent extends PluginEvent {

	private static final long serialVersionUID = 1L;

	public UpdateEvent(Object source, int id, String command) {
		super(source, id, command);
	}

	public UpdateEvent(Object source, int id, String command, int modifiers) {
		super(source, id, command, modifiers);
	}

	public UpdateEvent(Object source, int id, String command, long when, int modifiers) {
		super(source, id, command, when, modifiers);
	}

}
