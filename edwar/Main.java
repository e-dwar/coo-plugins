import java.util.Timer;
import java.util.TimerTask;

public class Main 
{
    public static void main (String[] args)
    {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run () {
                System.out.println(
                    java.util.Calendar.getInstance().getTime()
                ); 
            }
        }, 0, 1000);
    }
}
