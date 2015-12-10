package finder;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class PluginFilterTest {
	
	protected PluginFilter pluginFilter;
	protected String pathname = "/home/plugin.class";
	protected File file = new File(pathname);
	
	@Before
	public void init(){
		pluginFilter = new PluginFilter();
	}

	@Test
	public void acceptTest() {
		String needToBeAccepted = "plugin.class";
		String needToBeRejected = "plugin.lambda";
		assertTrue(pluginFilter.accept(file, needToBeAccepted));
		assertFalse(pluginFilter.accept(file, needToBeRejected));
	}

}
