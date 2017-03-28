package com.platform.serv.game.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fanfandou.platform.api.game.entity.AreaEnterType;
import com.fanfandou.platform.api.game.entity.ConnectType;
import com.fanfandou.platform.api.game.entity.EnterAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description: TODO.
 * <p/>
 * Date: 2016-08-09 11:01.
 * author: Arvin.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/*.xml")
public class FastJsonEnumTest {

    @Test
    public void testEnum(){

        EnterAddress enterAddress = new EnterAddress();
        enterAddress.setConnectType(ConnectType.DATABASE);
        enterAddress.setIpAddress("192.168.0.1");
        enterAddress.setPort(8888);
        String jsonStr = JSON.toJSONString(enterAddress, SerializerFeature.WriteEnumUsingToString);
        System.out.println(jsonStr);
        EnterAddress parseEnterAddr = JSON.parseObject(jsonStr, EnterAddress.class);
        System.out.println(parseEnterAddr);
    }
}
