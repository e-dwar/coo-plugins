package plugins;
import java.swing.Timer;
import java.swing.TimerTask;

public class TimePrinter
{
    public static void main (String[] args)
    {
        Timer timer = new Timer();
        System.out.println("---- timer");
        timer.schedule(new TimerTask() {
            public void run () {
                System.out.println(
                    java.util.Calendar.getInstance().getTime()
                ); 
            }
        }, 0, 1000);
    }
}
