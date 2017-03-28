import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wangzhenwei on 2016/8/2.
 * Description test.
 */
public class Reminder {
    Timer timer;

    public Reminder(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), 1000, seconds * 1000);
    }

    class RemindTask extends TimerTask {
        public void run() {
            System.out.println("Time's up!");
           // timer.cancel(); //Terminate the timer thread
        }
    }

    public static void main(String args[]) {
        System.out.println("About to schedule task.");
        new Reminder(5);
        System.out.println("Task scheduled.");
    }
}
