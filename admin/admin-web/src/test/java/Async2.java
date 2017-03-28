//import com.fanfandou.admin.operation.service.SyncTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wangzhenwei on 2016/8/1.
 * Description test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/*.xml")
public class Async2 {

    @Test
    public void test(){
        System.out.println("~~~~~~~~~~~~~开始了~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      //  SyncTest.testMethod();
        System.out.println("~~~~~~~~~~~~~执行了~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
