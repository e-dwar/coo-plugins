package frame;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class PluginFrame extends JFrame {
	
	/*
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Constructors
	 */
	public PluginFrame() throws HeadlessException {
	}

	public PluginFrame(GraphicsConfiguration gc) {
		super(gc);
	}

	public PluginFrame(String title) throws HeadlessException {
		super(title);
	}

	public PluginFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}
	
	/*
	 * Methods
	 */

}
