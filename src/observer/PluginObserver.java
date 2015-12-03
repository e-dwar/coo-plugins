package observer;

import execution.PluginUI;

public abstract class PluginObserver {
	
	
	protected PluginUI mainPluginUI;

	
	public PluginObserver(PluginUI mainPluginUI) {
		super();
		this.mainPluginUI = mainPluginUI;
	}
	
	
	public PluginUI getMainPluginUI(){
		
	}
	
	
	public void update(Plugin plugin){
		
	}
	
	

}
