package finder;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

import execution.*;

public class PluginFinder implements ActionListener {

	static protected ArrayList<File> pastList;
	
	protected File file;
	protected PluginFilter filter;
	protected ArrayList<File> newList;
	protected Map<Event, ArrayList<PluginListener>> map;
	protected ArrayList<PluginListener> pluginListeners;
	protected ArrayList<PluginListener> addList;
	protected ArrayList<PluginListener> deleteList;
	protected ArrayList<PluginListener> updateList;

    public PluginFinder (String dir) {
    	//if(!new File(dir).exists()) throw new NotAFileException();
        file = new File(dir);
        filter = new PluginFilter();
        newList = new ArrayList<File>();
        pastList = new ArrayList<File>();
        pluginListeners = new ArrayList<PluginListener>();
        map = new HashMap<Event,ArrayList<PluginListener>>();
        addList = new ArrayList<PluginListener>();
        deleteList = new ArrayList<PluginListener>();
        updateList = new ArrayList<PluginListener>();
    }
    
    /**
     * Set the files list of <code>this<code> file directory.
     * RETURN A SUPPRIMER
     * @return 
     * @return 
     */

    public void setNewList() {
    	File[] files = file.listFiles(filter);
    	for(int i = 0; i < files.length; i++) {
    		newList.add(files[i]);
    	}
    }
    
    /**
     * Add a listener to <code>this<code> pluginFinder.
     */
    public void addListeners(PluginListener plugin, Event typeEvent) {
    	pluginListeners.add(plugin);
    	if (typeEvent == Event.add) {
    		addList.add(plugin);
    		map.put(typeEvent,addList);
    	}
    	if (typeEvent == Event.delete) {
    		map.put(typeEvent,deleteList);
    		deleteList.add(plugin);
    		map.put(typeEvent,deleteList);
    	}
    	if (typeEvent == Event.update) {
    		map.put(typeEvent,updateList);
    		updateList.add(plugin);
    		map.put(typeEvent,updateList);
    	}
    }

    /**
     * Check if files were added between 2 consecutive calls
     * @return true a file has been added
     */
    public boolean hasBeenAdded() {
    	for(File file: newList) {
    		if(!pastList.contains(file)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Check if files were deleted between 2 consecutive calls
     * @return 
     * @return true file has been deleted
     */
    public boolean hasBeenDeleted() {
    	for(File file: pastList) {
    		if (!newList.contains(file)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Check if a file has been updated between 2 consecutive calls
     * @return true if a file was updated between 2 calls
     */
    public boolean hasBeenUpdated() {
    	for(File file: newList) {
        	for(File pfile: pastList) {
        		if(file.equals(pfile)) {
        			if(System.currentTimeMillis() - file.lastModified() <= 1000) {
        				return true;
        			}
        		}
        	}
        }
    	return false;
    }

	@Override
	/**
	 * Execute the action performed of <code>this<code>registered PluginListeners.
	 * @param the action event to be executed.
	 */
	public void actionPerformed(ActionEvent e) {
		setNewList();
		if(hasBeenAdded()) {
			for(PluginListener l : map.get(Event.add)) {
				l.actionPerformed(new ActionEvent(this,0,null));
			}
		}
		if(hasBeenDeleted()) {
			for(PluginListener l : map.get(Event.delete)) {
				l.actionPerformed(new ActionEvent(this,0,null));
			}
		}		
		if(hasBeenUpdated()) {
			for(PluginListener l : map.get(Event.update)) {
				l.actionPerformed(new ActionEvent(this,0,null));
			}
		}
		pastList.clear();
		pastList.addAll(newList);
		newList.clear();
	}
        		
}