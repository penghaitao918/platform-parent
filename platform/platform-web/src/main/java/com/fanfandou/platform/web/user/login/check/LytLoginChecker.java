package com.fanfandou.platform.web.user.login.check;


import com.alibaba.fastjson.JSONObject;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.crypto.Md5Cipher;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.util.HttpUtils;
import com.fanfandou.platform.api.user.exception.UserException;
import com.fanfandou.platform.web.user.login.SiteNote;
import com.fanfandou.platform.web.user.vo.AccountVo;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by zhongliang.
 * Descreption:乐视登录接口验证.
 * Date:2016/5/16
 */
@SiteNote(name = "")
public class LytLoginChecker extends BaseLogger {


    /**
     * 三方方登录入口.
     */
    public AccountVo login(AccountVo accountVo) throws ServiceException {
        logger.debug("LytLoginChecker>>>>>>>>>>>>>>>>>");
        String checkUrl = "http://ip:port/check_token";
        String clientId = accountVo.getAppId();

        String[] thirdOauth = new String(Base64.decodeBase64(accountVo.getThirdOauth())).split("&");

        String token = thirdOauth[0];

        String param = clientId + "#" + token + "#" + "appKey";
        Md5Cipher md5Cipher = new Md5Cipher();
        String sign = md5Cipher.encrypt(param);

        logger.debug("LeTvLoginChecker>>>>>>>>>>>>>>>>>decode thirdOauth= token = " + token);

        StringBuilder sb = new StringBuilder(checkUrl).append("?appId=" + clientId).append("&sign=" + sign)
                .append
                        ("&token=" + token);
        logger.debug("LeTvLoginChecker >>>>" + sb.toString());
        String resultParams = null;
        try {
            resultParams = HttpUtils.ignoreGetForHttps(sb.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("LeTvLoginChecker>>>>>>>>>>>>>>>>>resultParams = " + resultParams);

        JSONObject paramObject = JSONObject.parseObject(resultParams);

        if (paramObject.getIntValue("code") != 100) {
            throw new UserException(UserException.USER_LOGIN_INVALIED, "登录验证无效");
        }

        accountVo.setAccountId(paramObject.getLongValue("account"));
        accountVo.setChannel(paramObject.getString("partner"));
        accountVo.setSignCode(paramObject.getString("sign"));
        return accountVo;
    }
}
