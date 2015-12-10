package frame;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import plugins.FakePlugin;
import plugins.Plugin;

public class PluginFrameTest {
	
	protected PluginFrame pluginFrame;
	protected Plugin plugin;
	
	@Before
	public void init(){
		pluginFrame = new PluginFrame();
		plugin = new FakePlugin();
	}

	@Test
	public void searchPluginTest() {
		assertNull(pluginFrame.searchPlugin(plugin));
		pluginFrame.addPlugin(plugin);
		assertEquals(plugin, pluginFrame.searchPlugin(plugin));
	}

}
