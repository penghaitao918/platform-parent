package com.fanfandou.platform.web.user.login.check;


import com.alibaba.fastjson.JSONObject;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.util.HttpUtils;
import com.fanfandou.platform.api.user.exception.UserException;
import com.fanfandou.platform.web.user.login.SiteNote;
import com.fanfandou.platform.web.user.vo.AccountVo;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wudi.
 * Descreption:乐视登录接口验证.
 * Date:2016/5/16
 */
@SiteNote(name = "tv.letv")
public class LeTvLoginChecker extends BaseLogger {


    private static LeTvLoginChecker instance = new LeTvLoginChecker();

    private LeTvLoginChecker(){}

    public static LeTvLoginChecker getInstance() {
        return instance;
    }

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

        /*StringBuilder sb = new StringBuilder(checkUrl).append("?client_id=" + clientId).append("&uid=" + userId)
        .append
                ("&access_token=" + token);
        logger.debug("LeTvLoginChecker >>>>" + sb.toString());*/
            /*String resultParams = HttpUtils.ignoreGetForHttps(sb.toString(), true);
            logger.debug("LeTvLoginChecker>>>>>>>>>>>>>>>>>resultParams = " + resultParams);

            JSONObject paramObject = JSONObject.parseObject(resultParams);

            if (paramObject.getIntValue("status") != 1) {
                throw new UserException(UserException.USER_LOGIN_INVALIED, "登录验证无效");
            }
            */
            accountVo.setAccountName(userId);//"tetv_uid"
            accountVo.setToken(token);

        return accountVo;
    }
}
