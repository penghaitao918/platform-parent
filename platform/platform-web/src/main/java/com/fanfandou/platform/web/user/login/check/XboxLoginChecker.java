package com.fanfandou.platform.web.user.login.check;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.web.user.login.SiteNote;
import com.fanfandou.platform.web.user.vo.AccountVo;
import org.apache.commons.codec.binary.Base64;

import java.io.Serializable;

/**
 * Created by wudi.
 * Descreption:TODO..
 * Date:2016/7/1
 */
@SiteNote(name = "pc.xbox")
public class XboxLoginChecker extends BaseLogger {

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
