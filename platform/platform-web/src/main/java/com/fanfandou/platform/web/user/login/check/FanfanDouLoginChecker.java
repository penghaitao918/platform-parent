package com.fanfandou.platform.web.user.login.check;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.web.user.login.SiteNote;
import com.fanfandou.platform.web.user.vo.AccountVo;
import org.apache.commons.codec.binary.Base64;

import java.io.Serializable;

/**
 * Created by wudi.
 * Descreption:翻翻豆内部测试.
 * Date:2016/5/23
 */
@SiteNote(name = "pc.test")
public class FanfanDouLoginChecker extends BaseLogger {


    /**
     * 三方方登录入口.
     */
    public AccountVo login(AccountVo accountVo) throws ServiceException {

        logger.debug("FanfanDouLoginChecker>>>>>>>>>>>>>>>>>");
        String checkUrl = "https://sso.letv.com/oauthopen/userbasic";
        String clientId = accountVo.getAppId();

        String[] thirdOauth = new String(Base64.decodeBase64(accountVo.getThirdOauth())).split("&");

        String token = thirdOauth[0];
        String userId = thirdOauth[1];

        logger.debug("FanfanDouLoginChecker>>>>>>>>>>>>>>>>>decode thirdOauth= token = " + token + ",userId = " + userId);

        accountVo.setAccountName(userId);//"tetv_uid"
        accountVo.setToken(token);

        return accountVo;
    }
}
