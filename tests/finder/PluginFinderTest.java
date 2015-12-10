package finder;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import doubles.PluginFinder;
import event.PluginAddedEvent;
import execution.PluginObserver;
import finder.PluginFinder.Value;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.runner.RunWith;
import org.jmock.lib.legacy.ClassImposteriser;
import plugins.Plugin;

@RunWith(JMock.class)
@SuppressWarnings("deprecation")
public class PluginFinderTest extends TestCase {

	protected PluginFinder finder;
	protected Mockery context = new JUnit4Mockery() {
		{
			setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	@Before
	@SuppressWarnings("serial")
	public void before() {
		finder = new PluginFinder(new File("dropins") {
			public File[] listFiles(FilenameFilter filter) {
				return new File[] { createFile("name", 1) };
			}
		});
	}
	
	@Test
	public void hasBeenAdded() {
		File file = new File("poimp");
		assertEquals(true, finder.hasBeenAdded(file));
		finder.putCache(file);
		assertEquals(false, finder.hasBeenAdded(file));
	}
	
	@Test
	public void hasBeenUpdated() {
		File file = new File("poimp");
		assertEquals(false, finder.hasBeenUpdated(file));
		Value value = finder.putCache(file);
		assertEquals(false, finder.hasBeenUpdated(file));
		value.lastModified++;
		assertEquals(true, finder.hasBeenUpdated(file));
	}

	@Test
	public void addObserverTest() {
		ArrayList<PluginObserver> observers = finder.getObservers();
		PluginObserver observer = new PluginObserver() {};
		assertEquals(0, observers.size());
		finder.addObserver(observer);
		assertEquals(1, observers.size());
		assertEquals(observer, observers.get(0));
	}

	@Test
	public void updateObserversTest() {
		final PluginAddedEvent addEv = new PluginAddedEvent(createPlugin());
		final PluginObserver observer1 = context.mock(PluginObserver.class, "observer1");
		final PluginObserver observer2 = context.mock(PluginObserver.class, "observer2");
		context.checking(new Expectations() {
			{
				oneOf(observer1).update(addEv);
				oneOf(observer2).update(addEv);
			}
		});
		finder.addObserver(observer1);
		finder.addObserver(observer2);
		finder.updateObservers(addEv);
	}

	@Test
	public void actionPerformedTest() {
		ActionEvent ev = new ActionEvent(finder, 0, null);
		final PluginAddedEvent addEv = new PluginAddedEvent(createPlugin());
		final PluginObserver observer = context.mock(PluginObserver.class);
		context.checking(new Expectations() {
			{
				oneOf(observer).update(addEv);
			}
		});
		finder.addObserver(observer);
		finder.actionPerformed(ev);
	}

	@SuppressWarnings("serial")
	private File createFile(String name, final long lastUpdate) {
		return new File(name) {
			@SuppressWarnings("unused")
			public long lastUpdate() {
				return lastUpdate;
			}
		};
	}

	private Plugin createPlugin() {
		return new Plugin() {

			public String transform(String text) {
				return null;
			}

			public String getLabel() {
				return null;
			}
		};
	}
}
