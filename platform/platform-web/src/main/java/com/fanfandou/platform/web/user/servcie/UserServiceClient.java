package com.fanfandou.platform.web.user.servcie;

import com.alibaba.fastjson.JSON;
import com.fanfandou.admin.api.operation.entity.DeviceType;
import com.fanfandou.admin.api.operation.entity.GameVersionCheck;
import com.fanfandou.admin.api.operation.service.PatchService;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.ActStatus;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.sequence.TableSequenceGenerator;
import com.fanfandou.common.service.cache.CacheService;
import com.fanfandou.common.util.CipherUtils;
import com.fanfandou.platform.api.constant.IcachedConstant;
import com.fanfandou.platform.api.constant.TableSequenceConstant;
import com.fanfandou.platform.api.game.entity.GameArea;
import com.fanfandou.platform.api.game.service.GameAreaService;
import com.fanfandou.platform.api.game.service.OperationDispatchService;
import com.fanfandou.platform.api.user.entity.AccountStatus;
import com.fanfandou.platform.api.user.entity.AccountType;
import com.fanfandou.platform.api.user.entity.AuthToken;
import com.fanfandou.platform.api.user.entity.AuthTokenType;
import com.fanfandou.platform.api.user.entity.PlatformUser;
import com.fanfandou.platform.api.user.entity.ProfileDomain;
import com.fanfandou.platform.api.user.entity.UserAccount;
import com.fanfandou.platform.api.user.entity.UserProfile;
import com.fanfandou.platform.api.user.exception.UserException;
import com.fanfandou.platform.api.user.service.AccountService;
import com.fanfandou.platform.api.user.service.TokenService;
import com.fanfandou.platform.api.user.service.UserService;
import com.fanfandou.platform.web.game.service.GameServiceClient;
import com.fanfandou.platform.web.game.vo.GameAreaRoleVo;
import com.fanfandou.platform.web.game.vo.GameAreaVo;
import com.fanfandou.platform.web.game.vo.RoleVo;
import com.fanfandou.platform.web.user.vo.AccountVo;
import com.fanfandou.platform.web.user.vo.UserTokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Description: 平台web层用户相关业务方法调用
 * <p/>
 * Date: 2016/3/7.
 * author: wudi.
 */
@Service
public class UserServiceClient extends BaseLogger {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private GameServiceClient gameServiceClient;

    @Autowired
    private GameAreaService gameAreaService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TableSequenceGenerator tableSequenceGenerator;

    @Autowired
    private OperationDispatchService operationDispatchService;

    /*@Autowired
    private ThreadPoolTaskExecutor operationExecutor;*/

    @Autowired
    private PatchService patchService;


    @Autowired
    private CacheService cacheService;

    /**
     * 登录.
     *
     * @param accountName 用户名/第三方uid/token
     * @param passwd      密码
     * @param loginType   见LoginType
     * @see LoginType
     */
    public UserTokenVo login(String accountName, String passwd,
                             LoginType loginType, int accountTypeId, String ipAddress, String serial, int siteId,
                             int deviceId, int resourceVersion) throws ServiceException {

        PlatformUser user = userService.loginByAccount(accountName, passwd, accountTypeId, ipAddress, serial);
        String whiteList = cacheService.get(IcachedConstant.USER_WHITE_LIST, String.class);
        if (siteId == 9) {
            if (whiteList != null && !whiteList.contains(user.getCurrentAccount().getAccountName())) {
                throw new UserException(UserException.USER_EXCEPT_WHITE_LIST, "服务器正在维护");
            }
        }
        UserTokenVo userTokenVo = new UserTokenVo(user);
        AuthToken refreshToken = tokenService.getToken(userTokenVo.getRefreshToken());
        AuthToken accessToken = tokenService.createAccessToken(refreshToken);
        userTokenVo.setAccessToken(accessToken.getToken());
        int gameId = user.getCurrentAccount().getGameId();
        List<GameAreaVo> gameAreaList = gameServiceClient.queryGameArea(gameId, siteId, resourceVersion);
        List<RoleVo> gameRoleList = gameServiceClient.getRoleList(gameId, user.getUserId());
        GameAreaRoleVo gameAreaRoleVo = new GameAreaRoleVo();
        gameAreaRoleVo.setGameAreaVoList(gameAreaList);
        gameAreaRoleVo.setRoleVoList(gameRoleList);
        userTokenVo.setAreaRoleProps(gameAreaRoleVo);

        //插入补丁更新信息
        GameVersionCheck gameVersionCheck = patchService.checkGameVersion(DeviceType.valueOf(deviceId),
                GameCode.getById(gameId), siteId, resourceVersion, ipAddress);

        userTokenVo.setGameVersionInfos(JSON.toJSONString(gameVersionCheck));
        return userTokenVo;
    }

    /**
     * 登录.
     *
     * @param accountName 用户名/第三方uid/token
     * @param passwd      密码
     * @param loginType   见LoginType
     * @see LoginType
     */
    public UserTokenVo login(String accountName, String passwd,
                             LoginType loginType, int accountTypeId, String ipAddress, String serial, int siteId) throws ServiceException {

        PlatformUser user = null;
        switch (loginType) {
            case ACCOUNT: {
                user = userService.loginByAccount(accountName, passwd, accountTypeId, ipAddress, serial);
                break;
            }
            case TOKEN: {
                user = userService.loginByToken(accountName);
                break;
            }
            case TICKET: {
                //TODO ticket login/sso
                throw new UserException(UserException.USER_LOGIN_TYPE_INVALID, "登录类型错误");
            }
            default: {
                throw new UserException(UserException.USER_LOGIN_TYPE_INVALID, "登录类型错误");
            }
        }
        String whiteList = cacheService.get(IcachedConstant.USER_WHITE_LIST, String.class);
        if (siteId == 9) {
            if (whiteList != null && !whiteList.contains(user.getCurrentAccount().getAccountName())) {
                throw new UserException(UserException.USER_EXCEPT_WHITE_LIST, "服务器正在维护");
            }
        }
        UserTokenVo userTokenVo = new UserTokenVo(user);
        AuthToken refreshToken = tokenService.getToken(userTokenVo.getRefreshToken());
        AuthToken accessToken = tokenService.createAccessToken(refreshToken);
        userTokenVo.setAccessToken(accessToken.getToken());
        return userTokenVo;
    }

    /**
     * 从中心服拿到serverKey.
     */
    public AccountVo getLoginKey(AccountVo accountVo) throws ServiceException {
        GameArea gameArea = gameAreaService.getGameAreaByCode(accountVo.getGameId(), accountVo.getAreaId() + "");
        logger.info("UserServiceClient >>> getLoginKey areaId = " + gameArea.getId());
        String loginKey = operationDispatchService.getLoginKey(GameCode.getById(accountVo.getGameId()),
                gameArea.getId(), accountVo.getUserId());
        logger.info("UserServiceClient >>> getLoginKey = " + loginKey);
        accountVo.setServerKey(loginKey);
        return accountVo;
    }

    public boolean isExistAccount(String account, int accountTypeId) throws ServiceException {
        return userService.isExistAccount(account, accountTypeId);
    }


    /**
     * 发送短信注册账号.
     */
    public boolean registerBySms(AccountVo accountVo) throws ServiceException {
        int code = new Random().nextInt(89999) + 10000;
        String smsContent = "您正在进行账号安全相关操作，为了您的账号安全请不要将验证码泄露，您的手机验证码为：";//TODO 配置
        String replyContent = smsContent + code;
        cacheService.put(IcachedConstant.SEND_SMS_TO_USER + accountVo.getAccountName(), code + "", 600);//三十分钟失效

        return userService.sendSmsToUser(accountVo.getAccountName(), replyContent);
    }

    /**
     * 校验手机短信验证码激活账号.
     */
    public UserTokenVo activeSmsAccount(AccountVo accountVo) throws ServiceException {
        if (accountVo.getSignCode() != null) {
            String code = cacheService.get(IcachedConstant.SEND_SMS_TO_USER + accountVo.getAccountName(), String.class);
            if (code.equals(accountVo.getSignCode())) {
                cacheService.del(IcachedConstant.SEND_SMS_TO_USER + accountVo.getAccountName());
                return registerAccount(accountVo);
            } else {
                throw new UserException(UserException.PHONENO_SIGN_CODE_ERROR, "验证码错误");
            }
        } else {
            throw new UserException(UserException.SIGN_CODE_EMPTY, "验证码为空");
        }

    }

    /**
     * 根据短信找回修改密码.
     */
    public void updatePasswordBySms(AccountVo accountVo) throws ServiceException {
        if (accountVo.getSignCode() != null) {
            String code = cacheService.get(IcachedConstant.SEND_SMS_TO_USER + accountVo.getAccountName(), String.class);
            if (code.equals(accountVo.getSignCode())) {
                modifyPassword(accountVo);
            } else {
                throw new UserException(UserException.PHONENO_SIGN_CODE_ERROR, "验证码错误");
            }
        } else {
            throw new UserException(UserException.SIGN_CODE_EMPTY, "验证码为空");
        }
    }

    /**
     * 密码找回.
     */
    public boolean retrievePasswordByEmail(AccountVo accountVo) throws ServiceException {
        int code = new Random().nextInt(89999) + 10000;
        String title = "密码找回";
        String emailContent = "您正在进行账号安全相关操作，为了您的账号安全请不要将验证码泄露，您的邮箱账号验证码为：";//TODO 配置
        String replyContent = emailContent + code;
        cacheService.put(IcachedConstant.SEND_EMAIL_TO_USER + accountVo.getAccountName(), code + "", 600);//三十分钟失效

        return userService.sendEmailToUser(accountVo.getAccountName(), title, replyContent);
    }

    /**
     * 邮箱密码修改.
     */
    public void updatePasswordByEmail(AccountVo accountVo) throws ServiceException {
        if (accountVo.getSignCode() != null) {
            String code = cacheService.get(IcachedConstant.SEND_EMAIL_TO_USER + accountVo.getAccountName(), String.class);
            if (code.equals(accountVo.getSignCode())) {
                modifyPassword(accountVo);
                cacheService.del(IcachedConstant.SEND_EMAIL_TO_USER + accountVo.getAccountName());
            } else {
                throw new UserException(UserException.PHONENO_SIGN_CODE_ERROR, "验证码错误");
            }
        } else {
            throw new UserException(UserException.SIGN_CODE_EMPTY, "验证码为空");
        }
    }

    /**
     * 发送邮件.
     */
    public boolean registerByEmail(AccountVo accountVo) throws ServiceException {
        String title = "账号注册";//TODO 配置.
        String emailCotent = "您正在注册我们账号，请在十分钟之内激活该链接";//Todo
        String checkUrl = "http://localhost:8080/platform/user/activeEmailAccount";
        long userId = tableSequenceGenerator.generate(TableSequenceConstant.PLATFORM_USER_PROFILE);
        long accountId = tableSequenceGenerator.generate(TableSequenceConstant.PLATFORM_USER_ACCOUNT);

        String token = tokenService.createToken(userId, accountVo.getGameId(), AuthTokenType.VERIFY).getToken();

        UserAccount userAccount = AccountVo.convertAccount(accountVo);
        String seed = getSeed();
        userAccount.setEncryptSeed(seed);
        userAccount.setPassword(CipherUtils.getPasswd(accountVo.getPassword(), seed));
        userAccount.setUserId(userId);
        userAccount.setAccountId(accountId);
        userAccount.setConfirmStatus(ActStatus.ACTING);
        userAccount.setConfirmTime(new Date());
        userAccount.setAccountType(AccountType.EMAIL);

        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userId);
        userProfile.setChannel(accountVo.getChannel());
        userProfile.setCreateDeviceSerial(accountVo.getDeviceSerial());
        userProfile.setCreateIp(accountVo.getIpAddress());
        userProfile.setCreateTime(new Date());
        userProfile.setGameId(accountVo.getGameId());
        userProfile.setSiteId(accountVo.getSiteId());
        userProfile.setProfileStatus(AccountStatus.ACTIVE);
        userProfile.setAuditStatus(ActStatus.ACTING);

        PlatformUser platformUser = new PlatformUser(userAccount, userProfile);

        String content = getEmailContent(token, checkUrl, accountId, userId, accountVo.getAccountName(),
                emailCotent, accountVo.getPassword());

        return userService.registerByEmail(platformUser, title, content);
    }

    /**
     * 验证码校验.
     */
    public boolean tokenCheck(String token) throws ServiceException {
        AuthToken authToken = tokenService.getToken(token);
        if (authToken != null) {
            if (authToken.getToken().equals(token)) {
                return true;
            } else {
                throw new UserException(UserException.SIGN_CODE_INVALID, "验证码无效");
            }
        } else {
            throw new UserException(UserException.SIGN_CODE_EMPTY, "验证码为空");
        }
    }


    /**
     * 邮箱激活账号.
     */
    public void activeAccount(AccountVo accountVo) throws ServiceException {
        userService.activeUser(accountVo.getAccountId(), accountVo.getUserId());
    }

    /**
     * 获取到密码加密字符创.
     */
    public String getSeed() {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        String encryptSeed = "";
        for (int i = 0; i < 5; i++) {
            encryptSeed = encryptSeed + chars.charAt((int) (Math.random() * 26));
        }
        return encryptSeed;
    }

    /**
     * 账号注册.
     */
    public UserTokenVo registerAccount(AccountVo accountVo) throws ServiceException {

        if (accountVo.getAccountType().getId() != AccountType.THIRD_OAUTH.getId()) {
            if (!userService.checkDuplicateAccount(accountVo.getAccountName())) {
                throw new UserException(UserException.ACCNOUNT_DUPLICATE, "账号重复注册");
            }
        }
        //创建userAccount
        UserAccount userAccount = AccountVo.convertAccount(accountVo);//生成公共参数

        String seed = getSeed();
        userAccount.setEncryptSeed(seed);
        userAccount.setPassword(CipherUtils.getPasswd(accountVo.getPassword(), seed));

        //生成userId
        long userId = tableSequenceGenerator.generate(TableSequenceConstant.PLATFORM_USER_PROFILE);
        long accountId = tableSequenceGenerator.generate(TableSequenceConstant.PLATFORM_USER_ACCOUNT);
        userAccount.setUserId(userId); //生成userId
        userAccount.setAccountId(accountId);//生成accountId
        userAccount.setCreateDeviceSerial(accountVo.getDeviceSerial());

        //创建userProfile
        UserProfile profile = new UserProfile();
        profile.setUserId(userAccount.getUserId());
        profile.setSiteId(userAccount.getSiteId());
        profile.setGameId(userAccount.getGameId());
        profile.setProfileDomain(ProfileDomain.DEFAULT);

        profile.setCreateTime(new Date());
        profile.setCreateIp(userAccount.getCreateIp());

        //账号激活设定
        userAccount.setConfirmStatus(ActStatus.ACTED);//激活该账号
        profile.setAuditStatus(ActStatus.ACTED);


        PlatformUser platformUser = new PlatformUser(userAccount, profile);
        //注册操作
        userService.registerAccount(platformUser);

        LoginType loginType = LoginType.ACCOUNT;
        UserTokenVo userTokenVo = null;
        if (accountVo.getDeviceType() != 0) {
            userTokenVo = login(userAccount.getAccountName(), accountVo.getPassword(),
                    loginType, accountVo.getAccountType().getId(), userAccount.getCreateIp(),
                    accountVo.getDeviceSerial(), accountVo.getSiteId(), accountVo.getDeviceType(),
                    accountVo.getGameVersion());
        } else {
            userTokenVo = login(userAccount.getAccountName(), accountVo.getPassword(),
                    loginType, accountVo.getAccountType().getId(), userAccount.getCreateIp(),
                    accountVo.getDeviceSerial(), accountVo.getSiteId());
        }

        //登录
        return userTokenVo;
    }

    /**
     * 三方登录.
     */
    public UserTokenVo thirdOauthLogin(AccountVo accountVo) throws ServiceException {
        UserTokenVo userTokenVo = null;
        if (isExistAccount(accountVo.getAccountName(), AccountType
                .THIRD_OAUTH.getId())) {
            userTokenVo = login(accountVo.getAccountName(), "", LoginType.ACCOUNT, AccountType
                            .THIRD_OAUTH.getId(), accountVo.getIpAddress(), accountVo.getDeviceSerial(), accountVo.getSiteId());
        } else {
            userTokenVo = registerAccount(accountVo);
        }
        return userTokenVo;
    }

    /**
     * token和userId登录.
     */
    public UserTokenVo loginWithToken(AccountVo accountVo) throws ServiceException {
        UserTokenVo userTokenVo = null;
        if (isExistAccount(accountVo.getAccountName(), AccountType
                .THIRD_OAUTH.getId())) {
            userTokenVo = login(accountVo.getAccountName(), "", LoginType.ACCOUNT, AccountType
                    .THIRD_OAUTH.getId(), accountVo.getIpAddress(), accountVo.getDeviceSerial(), accountVo.getSiteId());
        } else {
            userTokenVo = registerAccount(accountVo);
        }
        return userTokenVo;
    }

    /**
     * 哈邻三方登录.
     */
    public UserTokenVo autoLogin(AccountVo accountVo) throws ServiceException {
        UserTokenVo userTokenVo = null;
        if (isExistAccount(accountVo.getAccountName(), AccountType
                .THIRD_OAUTH.getId())) {
            userTokenVo = login(accountVo.getAccountName(), "", LoginType.ACCOUNT, AccountType
                            .THIRD_OAUTH.getId(), accountVo.getIpAddress(), accountVo.getDeviceSerial(), accountVo.getSiteId(),
                    accountVo.getDeviceType(), accountVo.getGameVersion());
        } else {
            userTokenVo = registerAccount(accountVo);
        }
        return userTokenVo;
    }

    /**
     * 根据账号修改密码.
     */
    public void modifyPassword(AccountVo accountVo) throws ServiceException {
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountType(accountVo.getAccountType());
        userAccount.setAccountName(accountVo.getAccountName());
        accountService.modifyPassword(userAccount, accountVo.getNewPassword(),
                accountVo.getPassword(), accountVo.getIpAddress());
    }


    /**
     * .
     * 邮件内容
     *
     * @param ownerSign 邮箱验证秘钥
     * @param checkUrl  验证接口路径
     * @param address   邮箱地址
     * @param content   邮箱内容
     * @param pwd       用户登录密码
     */
    private static String getEmailContent(String ownerSign, String checkUrl, long accountId, long userId, String
            address, String content, String pwd) {
        StringBuilder sb = new StringBuilder();

        sb.append(content + "</br>");
        sb.append("<a href=\"" + checkUrl + "?");
        sb.append("userId=");
        sb.append(userId);
        sb.append("&accountId=");
        sb.append(accountId);
        sb.append("&accountName=");
        sb.append(address);
        sb.append("&token=");
        sb.append(ownerSign);
        sb.append("&password=");
        sb.append(pwd);
        sb.append("&createTime=");
        sb.append(new Date());
        sb.append("\" >");
        sb.append("点击此处验证");
        sb.append("</a>");

        return sb.toString();
    }
}
