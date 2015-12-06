package exception;

public class PluginLoadingException extends Exception {

	public PluginLoadingException(String message) {
		super("Cannot load " + message + ".");
	}

	private static final long serialVersionUID = 1L;
}
