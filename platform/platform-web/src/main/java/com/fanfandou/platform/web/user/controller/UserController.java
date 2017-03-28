package com.fanfandou.platform.web.user.controller;

import com.alibaba.fastjson.JSON;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.result.JsonResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.exception.ValidateException;
import com.fanfandou.common.util.ErrorValidate;
import com.fanfandou.common.util.HttpAddrUtil;
import com.fanfandou.platform.api.user.entity.AccountType;
import com.fanfandou.platform.api.user.exception.UserException;
import com.fanfandou.platform.web.user.servcie.LoginType;
import com.fanfandou.platform.web.user.servcie.ThirdLoginServiceClient;
import com.fanfandou.platform.web.user.servcie.UserServiceClient;
import com.fanfandou.platform.web.user.vo.AccountVo;
import com.fanfandou.platform.web.user.vo.UserTokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Description: 用户对外接口
 * <p/>.
 * Date: 2016-03-04 14:46.
 * author: wudi
 */
@RequestMapping("/user")
@RestController
@ErrorValidate
public class UserController extends BaseLogger {

    @Autowired
    private UserServiceClient userService;

    @Autowired
    private ThirdLoginServiceClient thirdLoginServiceClient;

    private Object respResult;

    /**
     * 创建平台账号.
     */
    @RequestMapping("/registerAccount")
    public JsonResult registerAccount(@Valid AccountVo accountVo, BindingResult bindingResult) throws
            ServiceException {
        if (bindingResult.hasErrors()) {
            throw new ValidateException(ValidateException.JSR_ERROR,"请求参数缺失");
        }
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddress = HttpAddrUtil.getRemoteAddr(httpServletRequest);
        accountVo.setIpAddress(ipAddress);
        accountVo.setAccountType(2);
        UserTokenVo userTokenVo = userService.registerAccount(accountVo);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(userTokenVo);
        jsonResult.setMessage(JsonResult.SUCCESS_MSG);
        jsonResult.setStatus(JsonResult.SUCCESS);
        return jsonResult;
    }

    /**
     * 登录.
     *
     * @return 结果
     */
    @RequestMapping("/login")
    public JsonResult login(@Valid AccountVo accountVo, BindingResult bindingResult)//String accountName, String password, Integer loginType, Integer accountType, String deviceSerial, int siteId, int gameVersion,int deviceType
            throws ServiceException {
        if (bindingResult.hasErrors()) {
            throw new ValidateException(ValidateException.JSR_ERROR,"请求参数缺失");
        }
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddress = HttpAddrUtil.getRemoteAddr(httpServletRequest);
        LoginType loginType1 = accountVo.getLoginType() == 0 ? LoginType.ACCOUNT : LoginType.valueOf(accountVo.getLoginType());
        Integer accountType = 2;//accountVo.getAccountType() == null ? 0 : accountVo.getAccountType().getId();
        UserTokenVo userTokenVo = null;
        if (accountVo.getGameVersion() == 0 || accountVo.getDeviceType() == 0) {
            //不返回区服信息和补丁信息
             userTokenVo = userService.login(accountVo.getAccountName(), accountVo.getPassword(), loginType1, accountType, ipAddress,
                     accountVo.getDeviceSerial(), accountVo.getSiteId());
        } else {
            userTokenVo = userService.login(accountVo.getAccountName(), accountVo.getPassword(), loginType1, accountType, ipAddress,
                    accountVo.getDeviceSerial(), accountVo.getSiteId(), accountVo.getDeviceType(), accountVo.getGameVersion());
        }

        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(userTokenVo);
        jsonResult.setMessage(JsonResult.SUCCESS_MSG);
        jsonResult.setStatus(JsonResult.SUCCESS);
        return jsonResult;
    }

    /**
     * token检验后直接Userid登录.
     */
    @RequestMapping("/tokenLogin")
    public JsonResult loginWithToken(@Valid AccountVo accountVo, BindingResult bindingResult) throws ServiceException {
        if (bindingResult.hasErrors()) {
            throw new ValidateException(ValidateException.JSR_ERROR,"请求参数缺失");
        }
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddress = HttpAddrUtil.getRemoteAddr(httpServletRequest);
        LoginType loginType = LoginType.ACCOUNT;
        UserTokenVo userTokenVo = userService.login(accountVo.getAccountName(), "", loginType,
                AccountType.THIRD_OAUTH.getId(), ipAddress,accountVo.getDeviceSerial(), accountVo.getSiteId(),
                accountVo.getDeviceType(), accountVo.getGameVersion());
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(userTokenVo);
        jsonResult.setMessage(JsonResult.SUCCESS_MSG);
        jsonResult.setStatus(JsonResult.SUCCESS);
        return jsonResult;
    }

    /**
     * 对外提供serverKey接口.
     */
    @RequestMapping("/getServerKey")
    public JsonResult getServerKey(@Valid AccountVo accountVo, BindingResult bindingResult) throws ServiceException {
        accountVo = userService.getLoginKey(accountVo);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(accountVo);
        jsonResult.setMessage(JsonResult.SUCCESS_MSG);
        jsonResult.setStatus(JsonResult.SUCCESS);
        return jsonResult;
    }

    /**
     * 自动登录（三方登录）.
     */
    @RequestMapping("/thirdOauthLogin")
    public JsonResult thirdOauthLogin(@Valid AccountVo accountVo, BindingResult bindingResult) throws
            ServiceException {
        if (bindingResult.hasErrors()) {
            throw new ValidateException(ValidateException.JSR_ERROR,"请求参数缺失");
        }
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
        //获取IP地址
        String ipAddress = HttpAddrUtil.getRemoteAddr(httpServletRequest);
        accountVo.setIpAddress(ipAddress);
        accountVo = thirdLoginServiceClient.factory(accountVo);

        if (accountVo == null) {
            throw new UserException(UserException.USER_LOGIN_INVALIED, "登录验证失败");
        }

        //三方登录
        accountVo.setAccountType(AccountType.THIRD_OAUTH.getId());

        UserTokenVo userTokenVo = userService.thirdOauthLogin(accountVo);

        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(userTokenVo);
        jsonResult.setMessage(JsonResult.SUCCESS_MSG);
        jsonResult.setStatus(JsonResult.SUCCESS);
        return jsonResult;

    }

    /**
     * 带上区服返回的登录.
     */
    @RequestMapping("/autoLogin")
    public JsonResult autoLogin(@Valid AccountVo accountVo, BindingResult bindingResult) throws ServiceException {
        if (bindingResult.hasErrors()) {
            throw new ValidateException(ValidateException.JSR_ERROR,"请求参数缺失");
        }
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();

        logger.info("autoLogin : " + JSON.toJSONString(httpServletRequest.getParameterMap()));

        //获取IP地址
        String ipAddress = HttpAddrUtil.getRemoteAddr(httpServletRequest);

        accountVo.setIpAddress(ipAddress);
        accountVo = thirdLoginServiceClient.factory(accountVo);

        if (accountVo == null) {
            throw new UserException(UserException.USER_LOGIN_INVALIED, "登录验证失败");
        }

        //三方登录
        accountVo.setAccountType(AccountType.THIRD_OAUTH.getId());

        UserTokenVo userTokenVo = userService.autoLogin(accountVo);

        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(userTokenVo);
        jsonResult.setMessage(JsonResult.SUCCESS_MSG);
        jsonResult.setStatus(JsonResult.SUCCESS);
        return jsonResult;
    }

    /**
     * 退出.
     */
    @RequestMapping("/public/logout")
    public JsonResult logout() {
       /* Subject subject = SecurityUtils.getSubject();
        subject.logout();*/
        //TODO 清理token，清理缓存
        return JsonResult.RESULT_SUCCESS;
    }

    /**
     * 密码修改.
     */
    @RequestMapping("/public/modifyPassword")
    public JsonResult modifyPassword(@Valid AccountVo accountVo, BindingResult bindingResult) throws ServiceException {
        userService.modifyPassword(accountVo);
        return JsonResult.RESULT_SUCCESS;
    }

    /**
     * 短信找回修改密码.
     */
    @RequestMapping("/public/updatePasswordBySms")
    public JsonResult updatePasswordBySms(@Valid AccountVo accountVo, BindingResult bindingResult)
            throws ServiceException {
        userService.updatePasswordBySms(accountVo);
        return JsonResult.RESULT_SUCCESS;
    }


    /**
     * 给用户发送短信验证码.
     */
    @RequestMapping("/public/sendSmsToUser")
    public JsonResult registerBySms(@Valid AccountVo accountVo) throws ServiceException {
        return userService.registerBySms(accountVo) ? JsonResult.RESULT_SUCCESS : JsonResult.RESULT_FAIL;
    }


    /**
     * 检查用户短信验证码.
     */
    @RequestMapping("/public/checkSmsContent")
    public UserTokenVo checkSmsContent(@Valid AccountVo accountVo, BindingResult bindingResult) throws
            ServiceException {
        return userService.activeSmsAccount(accountVo);
    }

    /**
     * 发送邮箱验证码.
     */
    @RequestMapping("/public/sendEmailSignCode")
    public JsonResult sendEmailSignCode(@Valid AccountVo accountVo, BindingResult bindingResult)
            throws ServiceException {
        return userService.retrievePasswordByEmail(accountVo) ? JsonResult.RESULT_SUCCESS :
                JsonResult.RESULT_FAIL;
    }

    /**
     * 通过邮箱验证码修改密码.
     */
    @RequestMapping("/public/modifyPasswordBySignCode")
    public JsonResult modifyPasswordBySignCode(@Valid AccountVo accountVo, BindingResult bindingResult)
            throws ServiceException {
        userService.updatePasswordByEmail(accountVo);
        return JsonResult.RESULT_SUCCESS;
    }


    /**
     * 激活邮箱账号.
     */
    @RequestMapping("/public/activeEmailAccount")
    public String activeEmailAccount(AccountVo accountVo) throws ServiceException {
        if (userService.tokenCheck(accountVo.getToken())) {
            userService.activeAccount(accountVo);
            return "actice account success";
        } else {
            return "error";
        }
    }

    /**
     * 给用户发送邮件.
     */
    @RequestMapping("/public/registerByEmail")
    public JsonResult registerByEmail(@Valid AccountVo accountVo, BindingResult bindingResult) throws ServiceException {
        return userService.registerByEmail(accountVo) ? JsonResult.RESULT_SUCCESS :
                JsonResult.RESULT_FAIL;
    }


}
