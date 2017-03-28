package com.fanfandou.common;

import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.sequence.TableSequenceGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description: 测试数据库表id生成器.
 * <p/>
 * Date: 2016-03-24 16:41.
 * author: Arvin.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/spring-*.xml")
public class TableSequenceTest {
    @Autowired
    private TableSequenceGenerator tableSequenceGenerator;
    @Test
    public void testSequence(){
        try {
          // for(int i=0;i<1060;i++) {
                long accountId = tableSequenceGenerator.generate("platform.user.account");
                long userId = tableSequenceGenerator.generate("platform.user.profile");
                System.out.println(accountId + ",,,,,," + userId);
           // }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
