package execution;

import static org.junit.Assert.*;

import org.junit.Test;

public class PluginUserInterfaceTest extends PluginObserverTest<PluginUserInterface> {
	
	@Override
	public PluginUserInterface createPluginObserver() {
		return new PluginUserInterface();
	}
	
	@Test
	public void addPluginTest(){
		assertTrue(pluginObserver.getPluginFrame().getPlugins().isEmpty());
		super.addPluginTest();
		assertEquals(1, pluginObserver.getPluginFrame().getPlugins().size());
		assertTrue(pluginObserver.getPluginFrame().getPlugins().containsKey(plugin));
	}
	
	@Test
	public void updatePluginTest(){
		super.updatePluginTest();
	}
	
	@Test
	public void deletePluginTest(){
		pluginObserver.getPluginFrame().addPlugin(plugin);
		assertEquals(1, pluginObserver.getPluginFrame().getPlugins().size());
		assertTrue(pluginObserver.getPluginFrame().getPlugins().containsKey(plugin));
		super.deletePluginTest();
		assertEquals(0, pluginObserver.getPluginFrame().getPlugins().size());
		assertFalse(pluginObserver.getPluginFrame().getPlugins().containsKey(plugin));
	}

}
