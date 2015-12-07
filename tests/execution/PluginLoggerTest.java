package execution;

public class PluginLoggerTest extends PluginObserverTest<PluginLogger> {

	@Override
	public PluginLogger createPluginObserver() {
		return new PluginLogger();
	}
	
}
