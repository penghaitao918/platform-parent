package com.fanfandou.platform.web.user.login.check;

import com.alibaba.fastjson.JSONObject;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.util.HttpUtils;
import com.fanfandou.platform.api.user.exception.UserException;
import com.fanfandou.platform.web.user.login.SiteNote;
import com.fanfandou.platform.web.user.vo.AccountVo;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wudi.
 * Descreption:机锋登录实现.
 * Date:2016/3/29
 */
@SiteNote(name = "mobile_android_gfan")
public class GfanLoginChecker extends BaseLogger {

    private static final String CHECK_URL_PATTEN = "http://api.gfan.com/uc1/common/verify_token";

    /**
     * 单方登录入口.
     */
    public AccountVo login(AccountVo accountVo) throws ServiceException {

        long userId = accountVo.getUserId();
        String token = accountVo.getToken();

        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("token", token));

            String response = HttpUtils.doGet(CHECK_URL_PATTEN,params);

            JSONObject jsonObject = new JSONObject();

            String uid = jsonObject.getJSONObject(response).getString("uid");
            int resultCode = jsonObject.getJSONObject(response).getIntValue("resultCode");
            if (resultCode != 1 || !uid.equalsIgnoreCase(uid)) {
                throw new UserException(UserException.USER_TOKEN_INVALID,"token验证无效");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountVo;
    }
}
