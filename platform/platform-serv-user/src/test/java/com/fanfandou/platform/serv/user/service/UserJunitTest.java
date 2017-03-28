package com.fanfandou.platform.serv.user.service;

import com.alibaba.fastjson.JSON;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.ActStatus;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.sequence.TableSequenceGenerator;
import com.fanfandou.common.service.cache.CacheService;
import com.fanfandou.common.util.CipherUtils;
import com.fanfandou.platform.api.constant.IcachedConstant;
import com.fanfandou.platform.api.user.entity.AccountStatus;
import com.fanfandou.platform.api.user.entity.AccountType;
import com.fanfandou.platform.api.user.entity.AuthToken;
import com.fanfandou.platform.api.user.entity.PlatformUser;
import com.fanfandou.platform.api.user.entity.ProfileDomain;
import com.fanfandou.platform.api.user.entity.UserAccount;
import com.fanfandou.platform.api.user.entity.UserProfile;
import com.fanfandou.platform.api.user.service.AccountService;
import com.fanfandou.platform.api.user.service.TokenService;
import com.fanfandou.platform.api.user.service.UserService;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


/**
 * Created by wudi.
 * Descreption:单元测试
 * Date:2016/3/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/*.xml")
public class UserJunitTest extends BaseLogger{

    @Autowired
    private UserService userService;

    @Autowired
    private TableSequenceGenerator tableSequenceGenerator;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CacheService cacheService;


    @Test
    public void createUserTest() {
        int site = 1;
        String channle = "mobile_hilink";
        int gameId = 1;
        String acountName = "a291318254";
        String password = "pokemon";
        String seed = "abcd";
        String createIp = "127.0.0.1";

        UserAccount userAccount = new UserAccount();
        userAccount.setSiteId(site);
        userAccount.setGameId(gameId);
        userAccount.setChannel(channle);
        userAccount.setAccountName(acountName);
        userAccount.setPassword(CipherUtils.getPasswd(password,seed));
        userAccount.setCreateIp(createIp);
        userAccount.setEncryptSeed(seed);
        userAccount.setAccountType(AccountType.DEVICE);
        userAccount.setCreateDeviceSerial("asda-asdasd-asd");
        userAccount.setCreateTime(new Date());
        try {
            //生成userId
            long userId = tableSequenceGenerator.generate("user_account_user_account");
            long accountId = tableSequenceGenerator.generate("platform_user_user_profile");
            //userVo.setUserId(userId);
            //userVo.setAccountId(accountId);

            //生成token
            AuthToken refreshToken = tokenService.createRefreshToken(userId, gameId);
            AuthToken accessToken = tokenService.createAccessToken(refreshToken);

            //userVo.setRefreshToken(refreshToken.getToken());
            //userVo.setAccessToken(accessToken.getToken());
            userAccount.setUserId(userId);
            userAccount.setAccountId(accountId);

            //创建userProfile
            UserProfile profile = new UserProfile();
            profile.setUserId(userId);
            profile.setSiteId(site);
            profile.setGameId(gameId);
            profile.setChannel(channle);
            profile.setProfileDomain(ProfileDomain.DEFAULT);
            profile.setProfileStatus(AccountStatus.ACTIVE);
            profile.setAuditStatus(ActStatus.ACTED);
            profile.setCreateTime(new Date());
            profile.setCreateIp(createIp);

            //创建整体账号信息（账号和用户信息）
            PlatformUser puser = new PlatformUser();
            puser.setCurrentAccount(userAccount);
            puser.setUserProfile(profile);
            //注册操作
            //userService.registerAccount(userAccount,profile);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据旧密码修密码
     */
    @Test
    public void modifyPasswordTest() {
        long accountId = 21L;
        String newPassword = "kkwty98";
        String oldPassword = "pokemon"; //a9292f36eff2121930523c69b4661a9f
        String ip = "127.0.0.1";
        /*try {
            accountService.modifyPassword(21L, newPassword, oldPassword, ip);//newPassword:a7ae4b87768ed751f97b53d0422ba453
        } catch (ServiceException e) {
            e.printStackTrace();
        }*/
    }

    @Test
    public void jsonTest(){
        String json = "{\"ask\" : 200, \"msg\" : \"OK\"}";
        System.out.println("json = " + JSON.parseObject(json).getString("msg"));
    }

    @Test
    public void Base64Test(){
        String ba64 = "cXdldWlxd2V1aXF3MTIzJjk4Nzg1NA==";
        String decodeBa64 = new String(Base64.decodeBase64(ba64));
        System.out.println("Base64Test = " + decodeBa64);

    }

    @Test
    public void checkPasswordTest() {
        String password = "pokemon98";
        String seed = "nuwff";
        System.out.println("密码为：" + CipherUtils.getPasswd(password,seed));
    }

    /**
     * 直接修改密码
     */
    @Test
    public void modifyPasswordTest2() {
        long accountId = 21L;
        String newPassword = "kkwty97";
        String ip = "101.69.176.140";
      /*  try {
            accountService.modifyPassword(21L, newPassword, null, ip);//newPassword:7572e784ad73ec507cde06f8680e9696
        } catch (ServiceException e) {
            e.printStackTrace();
        }*/
    }



    @Test
    public void sendEmailTest() {
        try {
            System.out.print(new Date().getTime());
            userService.sendEmailToUser("291318254@qq.com", "密码修改", "请赶紧修改密码，谢谢");
            System.out.print(new Date().getTime());
        }catch (ServiceException e){
            logger.error(e.getMessage());
        }
    }


    @Test
    public void sendSmsTest() {
        try {
            userService.sendSmsToUser("13671886406","验证码为：123456");
        }catch (ServiceException e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void addWhiteList() {
        cacheService.del(IcachedConstant.USER_WHITE_LIST);//put(IcachedConstant.USER_WHITE_LIST,"987854");
        //iCached.put(IcachedConstant.USER_WHITE_LIST,"");
    }

    @Test
    public void userOffline() {
        userService.userOffline(53,new Date(),1);
    }

    @Test
    public void userOnline() throws ServiceException {
        userService.userOnline(53,new Date(),1);
    }


    @Test
    public void testORder() {
        String param = "2016117405*0";
        String orderNo = param.split("\\*")[0];
        System.out.println("orderNo : " + orderNo);
    }

}
