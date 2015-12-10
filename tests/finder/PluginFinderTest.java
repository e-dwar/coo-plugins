package finder;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FilenameFilter;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import doubles.PluginFinder;
import event.PluginAddedEvent;
import event.PluginEvent;
import execution.PluginObserver;

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
				File[] files = new File[1];
				files[0] = createFile("name", 1);
				return files;
			}
		});
	}

	@Test
	public void fstTest() {
		ActionEvent ev = new ActionEvent(finder, 0, null);
		final PluginObserver observer = context.mock((new PluginObserver() {
			public void update(PluginEvent ev) {}
		}).getClass());
		context.checking(new Expectations() {
			{
				oneOf(observer).update(new PluginAddedEvent(createPlugin()));
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