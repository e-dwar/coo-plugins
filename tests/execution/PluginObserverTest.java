package execution;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import event.PluginAddedEvent;
import plugins.FakePlugin;
import plugins.Plugin;

public abstract class PluginObserverTest<O extends PluginObserver> {

	protected O pluginObserver;
	protected Plugin plugin;
	
	@Before
	public void init() {
		pluginObserver = createPluginObserver();
		plugin = new FakePlugin();
	}
	
	
	public abstract O createPluginObserver();
	
	@Test
	public void addPluginTest(){
		assertTrue(pluginObserver.getPlugins().isEmpty());
		pluginObserver.addPlugin(plugin);
		assertEquals(1, pluginObserver.getPlugins().size());
		assertTrue(pluginObserver.getPlugins().contains(plugin));
	}
	
	@Test
	public void updatePluginTest(){
		assertTrue(pluginObserver.getPlugins().isEmpty());
		pluginObserver.getPlugins().add(plugin);
		assertEquals(1, pluginObserver.getPlugins().size());
		pluginObserver.updatePlugin(plugin);
		assertEquals(1, pluginObserver.getPlugins().size());
	}
	
	@Test
	public void deletePluginTest(){
		pluginObserver.addPlugin(plugin);
		assertEquals(1, pluginObserver.getPlugins().size());
		assertTrue(pluginObserver.getPlugins().contains(plugin));
		pluginObserver.deletePlugin(plugin);
		assertEquals(0, pluginObserver.getPlugins().size());
		assertFalse(pluginObserver.getPlugins().contains(plugin));
	}

}
