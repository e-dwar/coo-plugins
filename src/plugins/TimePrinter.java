package plugins;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
public class TimePrinter
{
    public static void main (String[] args)
    {
    	int delay = 1000; //milliseconds
    	  ActionListener taskPerformer = new ActionListener() {
    	      public void actionPerformed(ActionEvent evt) {
    	         System.out.println("debug "+evt.getActionCommand());
    	      }
    	  };
    	  new Timer(delay, taskPerformer).start();
    	  while(true);
    }
  
}
