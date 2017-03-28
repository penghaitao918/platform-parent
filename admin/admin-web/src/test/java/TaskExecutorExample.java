import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by wangzhenwei on 2016/8/1.
 * Description test.
 */

public class TaskExecutorExample {

    private class MessagePrinterTask implements Runnable {

        private String message;


        public MessagePrinterTask(String message) {
            this.message = message;
        }

        public void run() {
            System.out.println(message);
        }

    }

    private TaskExecutor taskExecutor;

    public TaskExecutorExample(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void printMessages() {
        for(int i = 0; i < 25; i++) {
            taskExecutor.execute(new MessagePrinterTask("Message" + i));
        }
    }
}