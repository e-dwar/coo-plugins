package finder;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

import execution.*;

public class PluginFinder implements ActionListener {

	static protected ArrayList<File> previousCallList;
	
	protected File file;
	protected PluginFilter filter;
	protected ArrayList<File> upToDateFileList;
	protected File[] upToDateFileTab;
	protected File[] upToDateFileTemp;
	protected Map<String,PluginListener> map;
	protected List<PluginListener> pluginListeners;

    public PluginFinder (String dir) {
    	//if(!new File(dir).exists()) throw new NotAFileException();
        file = new File(dir);
        filter = new PluginFilter();
        upToDateFileList = new ArrayList<File>();
        previousCallList = new ArrayList<File>();
        pluginListeners = new ArrayList<PluginListener>();
        map = new HashMap<String,PluginListener>();
    }
    
    /**
     * Set the files list of <code>this<code> file directory.
     * RETURN A SUPPRIMER
     * @return 
     */
    public void initToDateListFiles() {
    	upToDateFileTab = file.listFiles(filter);
    	upToDateFileTemp = upToDateFileTab; 
    	for(int i = 0; i < upToDateFileTemp.length; i++) {
    		System.out.println(upToDateFileTemp[0]);
    	}
    	//return upToDateFileList;
    	 
    }
    
   /* A SUPPRIMER*/
   public ArrayList<File> getPrevList() {
	   return previousCallList;
   }
    
    /**
     * Add (ie: register) a listener to <code>this<code> pluginFinder.
     */
    public void addListeners(PluginListener plugin) {
    	pluginListeners.add(plugin);
    }
    
    /**
     * Check if the file is in <code>this</code> toDateList
     * @return true if the file is in the toDateList
     */
    public boolean isInToDateList(File file) {
    		return upToDateFileList.contains(file);
    }

    /**
     * Check if files were added between 2 consecutive calls
     * @return true file has been deleted
     */
    public boolean hasBeenAdded() {
    	for(File file: upToDateFileList) {
    		if(!previousCallList.contains(file)) {
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
    	for(File file: previousCallList) {
    		if (!upToDateFileList.contains(file)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Check if a file has been modified between 2 consecutive calls
     * @return true if a file was modified between 2 calls
     */
    public boolean hasBeenModified() {
    	for(File file: upToDateFileList) {
        	for(File pfile: previousCallList) {
        		if(file.equals(pfile)) {
        			if(file.lastModified() - pfile.lastModified() <= 1) {
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
		System.out.println(previousCallList);
		initToDateListFiles();
		System.out.println("up" + upToDateFileList);
		/*for(PluginListener l : pluginListeners) {
			System.out.println(upToDateFileList);
			if(!this.hasBeenAdded()) {
				l.actionPerformed(new ActionEvent(this,0,null));
			}
		}*/
		previousCallList.clear();
		//previousCallList.addAll(upToDateFileList);
		//upToDateFileList.clear();
	}
}
