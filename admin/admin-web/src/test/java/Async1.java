import org.springframework.scheduling.annotation.Async;

/**
 * Created by wangzhenwei on 2016/8/1.
 * Description test.
 */
public class Async1 {

    @Async("myExecutor")
    public static void testMethod(){
        try {
            //让程序暂停100秒，相当于执行一个很耗时的任务
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
