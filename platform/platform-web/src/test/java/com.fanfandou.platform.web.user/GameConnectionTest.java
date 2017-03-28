package com.fanfandou.platform.web.user;

import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.platform.api.game.exception.GameException;
import com.fanfandou.platform.api.game.service.OperationDispatchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description: 平台与游戏通讯测试.
 * <p/>
 * Date: 2016-05-27 17:16.
 * author: Arvin.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/*.xml")
public class GameConnectionTest {

    @Autowired
    private OperationDispatchService operationDispatchService;

    @Test
    public void testGetLoginKey() throws Exception {

        for (int i = 0; i < 100; i++) {
            //381测试服，391本地服
            String loginKey = operationDispatchService.getLoginKey(GameCode.getById(1), 391, 1881);
            System.out.println(loginKey);
            Thread.sleep(5000);
        }

    }





}
