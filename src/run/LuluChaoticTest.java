package run;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import execution.PluginAddedLogger;
import finder.PluginFilter;
import finder.PluginFinder;

public class LuluChaoticTest {

	protected File curFile;
	protected File dropinsFile;
	protected File inDropinsFile;
	protected File[] list;
	protected ArrayList<File> alist;
	protected PluginFinder finder;
	protected PluginFilter filter;
	
	@Before
	public void before() {
		curFile = new File(".");
		dropinsFile = new File("./dropins");
		inDropinsFile = new File("./dropins/ToUppercasePlugin.class");
		filter = new PluginFilter();
		finder = new PluginFinder("./dropins");
		list  = dropinsFile.listFiles(filter);
		alist = new ArrayList<File>(Arrays.asList(list));
	}
	
	@Test
	public void thisFileExist() {
		assertTrue(curFile.exists());
	}

	@Test
	public void dropinsFileExist() {
		assertTrue(dropinsFile.exists());
	}
	@Test
	public void inDropinsFileExist() {
		assertTrue(inDropinsFile.exists());
	}

	@Test
	public void pluginNotNull() {
		assertNotNull(new PluginAddedLogger());
	}
	
	@Test
	public void finderNotNull() {
		assertNotNull(new PluginFinder("./Dropins"));
	}

	/*@Test
	public void arrayToListTest() {
		assertNotNull(finder.initToDateListFiles());
		assertEquals(1,finder.initToDateListFiles().size());
		assertTrue(finder.initToDateListFiles().contains(inDropinsFile));
	}*/

	@Test
	public void prevNextListExchangeTest() {
		assertNotNull(finder.getPrevList());
		assertEquals(0,finder.getPrevList().size());
	}
	
	@Test
	public void listFilesTest() {
		assertNotNull(list);
		assertNotNull(alist);
		assertEquals(1,list.length);
		assertEquals(1,alist.size());
	}
	
}
