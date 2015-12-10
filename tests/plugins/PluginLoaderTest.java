package plugins;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class PluginLoaderTest {

	protected PluginLoader pluginLoader;
	protected String pathname = "/home/plugin.class";
	protected File file = new File(pathname);

	@Before
	public void init(){
		pluginLoader = new PluginLoader();
		
	}

	@Test
	public void toClassNameTest() {
		String expectedResult = "plugins." + "plugin";
		assertEquals(expectedResult, pluginLoader.toClassName(file));
	}
	
}
