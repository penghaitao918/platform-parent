package com.fanfandou.platform.serv.user.service;

import com.fanfandou.platform.api.user.entity.AuthToken;
import com.fanfandou.platform.api.user.service.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description: token相关操作单元测试.
 * <p/>
 * Date: 2016-03-17 10:28.
 * author: Arvin.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/spring-*.xml")
public class TokenServiceTest {

    @Autowired
    private TokenService tokenService;

    @Test
    public void testCreate(){
        AuthToken refreshToken = tokenService.createRefreshToken(123456L, 1);
        AuthToken accessToken = tokenService.createAccessToken(refreshToken);
        System.out.println("refreshToken="+refreshToken);
        System.out.println("accessToken="+accessToken);
    }

    @Test
    public void test(){
        byte  b1[] = {0x02};
        byte  b2[] = {0x01};
        System.out.println("0x01 = " + new String(b2));
    }
}
