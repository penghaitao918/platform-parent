package com.fanfandou.platform.web.user.login.check;


import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.web.user.login.SiteNote;
import com.fanfandou.platform.web.user.vo.AccountVo;
import org.apache.commons.codec.binary.Base64;

import java.io.Serializable;

/**
 * Created by wudi.
 * Descreption:乐视登录接口验证.
 * Date:2016/5/16
 */
@SiteNote(name = "tv.osletv")
public class OsLeTvLoginChecker extends BaseLogger {


    /**
     * 三方方登录入口.
     */
    public AccountVo login(AccountVo accountVo) throws ServiceException {
        logger.debug("LeTvLoginChecker>>>>>>>>>>>>>>>>>");
        String checkUrl = "https://sso.letv.com/oauthopen/userbasic";
        String clientId = accountVo.getAppId();

        String[] thirdOauth = new String(Base64.decodeBase64(accountVo.getThirdOauth())).split("&");

        String token = thirdOauth[0];
        String userId = thirdOauth[1];

        logger.debug("LeTvLoginChecker>>>>>>>>>>>>>>>>>decode thirdOauth= token = " + token + ",userId = " + userId);

            accountVo.setAccountName(userId);//"tetv_uid"
            accountVo.setToken(token);

        return accountVo;
    }
}
