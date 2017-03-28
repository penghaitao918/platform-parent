package com.fanfandou.common;

import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.entity.resource.SiteCode;
import com.fanfandou.common.util.HttpUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: source获取测试.
 * <p/>
 * Date: 2016-05-17 20:51.
 * author: Arvin.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/spring-*.xml")
public class SourceTest {

    @Test
    public void testGameCode(){
        /*GameCode gameCode = GameCode.getById(1);
        System.out.println(gameCode);*/
    }

    @Test
    public void testSiteCode(){
       /* SiteCode siteCode = SiteCode.getById(2);
        System.out.println(siteCode);*/
    }

}
