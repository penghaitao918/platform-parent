package com.fanfandou.platform.web.user.login.check;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.user.entity.AccountType;
import com.fanfandou.platform.web.user.login.SiteNote;
import com.fanfandou.platform.web.user.vo.AccountVo;
import org.apache.commons.codec.binary.Base64;

import java.io.Serializable;

/**
 * Created by wudi.
 * Descreption:压力测试登录校验.
 * Date:2017/2/22
 */
@SiteNote(name = "mobile.android.test")
public class PressTestLoginCheck extends BaseLogger {

    private static PressTestLoginCheck instance = new PressTestLoginCheck();

    private PressTestLoginCheck(){}

    public static PressTestLoginCheck getInstance() {
        return instance;
    }

    /**
     * 三方方登录入口.
     */
    public AccountVo login(AccountVo accountVo) throws ServiceException {
        if (accountVo.getUserId() != null) {
            accountVo.setAccountName(accountVo.getUserId() + "");
        }
        String arg = "";
        String accountName = accountVo.getAccountName();
        String token = accountVo.getToken();

        accountVo.setAccountName(accountName);
        accountVo.setToken(token);

        return accountVo;
    }

}
