package plugins;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PluginTest {

	protected Plugin plugin;
	
	@Before
	public void init(){
		plugin = new FakePlugin();
	}
	
	@Test
	public void transformTest(){
		String text = "Text";
		text = plugin.transform(text);
		assertNull(text);
	}
	
	@Test
	public void getLabel(){
		assertEquals("FakePlugin", plugin.getLabel());
	}
}
