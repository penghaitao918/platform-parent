package com.fanfandou.platform.serv.user.service;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.ActStatus;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.sequence.TableSequenceGenerator;
import com.fanfandou.common.service.cache.CacheService;
import com.fanfandou.common.util.CipherUtils;
import com.fanfandou.common.util.EmailUtils;
import com.fanfandou.common.util.SendsmsUtils;
import com.fanfandou.common.util.ValidateUtils;
import com.fanfandou.platform.api.constant.TableSequenceConstant;
import com.fanfandou.platform.api.game.service.OperationDispatchService;
import com.fanfandou.platform.api.user.entity.AccountStatus;
import com.fanfandou.platform.api.user.entity.AccountType;
import com.fanfandou.platform.api.user.entity.AuthToken;
import com.fanfandou.platform.api.user.entity.PlatformUser;
import com.fanfandou.platform.api.user.entity.UserAccount;
import com.fanfandou.platform.api.user.entity.UserIdcard;
import com.fanfandou.platform.api.user.entity.UserOnlineDetail;
import com.fanfandou.platform.api.user.entity.UserOnlineDetailExample;
import com.fanfandou.platform.api.user.entity.UserProfile;
import com.fanfandou.platform.api.user.exception.UserException;
import com.fanfandou.platform.api.user.service.AccountService;
import com.fanfandou.platform.api.user.service.ProfileService;
import com.fanfandou.platform.api.user.service.TokenService;
import com.fanfandou.platform.api.user.service.UserService;
import com.fanfandou.platform.serv.user.SmsResChecker;
import com.fanfandou.platform.serv.user.dao.UserAccountMapper;
import com.fanfandou.platform.serv.user.dao.UserIdcardMapper;
import com.fanfandou.platform.serv.user.dao.UserOnlineDetailMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Description: 平台用户相关业务实现.
 * <p/>
 * Date: 2016-02-24 16:44.
 * author: Arvin.
 */
@Service("userService")
public class UserServiceImpl extends BaseLogger implements UserService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private UserIdcardMapper userIdcardMapper;

    @Autowired
    private UserOnlineDetailMapper userOnlineDetailMapper;

    @Autowired
    private TableSequenceGenerator tableSequenceGenerator;

    @Autowired
    private OperationDispatchService operationDispatchService;
    private static final long USER_CACHE_EXPIRE = 24 * 60 * 60L;
    private static final String USER_CACHE_PREFIX = "platform_user_";


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = ServiceException.class)
    public PlatformUser loginByAccount(String accountName, String passwd, int accountTypeId, String ipAddress, String serial)
            throws ServiceException {
        AccountType accountType = accountTypeId > 0 ? AccountType.valueOf(accountTypeId) : getAccountType(accountName);
        UserAccount userAccount = accountService.getAccount(accountType, accountName);

        if (userAccount == null) {
            throw new ServiceException(UserException.USER_NOT_FOUND, "用户不存在");
        }

        if (userAccount.getAccountType().isNeedPwd()
                && (!CipherUtils.getPasswd(passwd, userAccount.getEncryptSeed()).equals(userAccount.getPassword()))) {
            throw new ServiceException(UserException.USER_PASSWD_INVALID, "密码不正确");
        }

        UserProfile userProfile = profileService.getProfileByUid(userAccount.getUserId());
        checkUser(userProfile);
        //创建refreshToken，并放入user中
        AuthToken refreshToken = tokenService.createRefreshToken(userProfile.getUserId(), userProfile.getGameId());
        PlatformUser user = new PlatformUser(userAccount, userProfile);
        user.setRefreshToken(refreshToken.getToken());
        userAccount.setLastLoginTime(new Date());
        userAccount.setLastLoginIp(ipAddress);
        userAccount.setLastLoginDeviceSerial(serial);
        userAccountMapper.upadteInfosByAccountId(userAccount);
        return user;
    }

    @Override
    public boolean isExistAccount(String accountName, int accountTypeId) throws ServiceException {
        AccountType accountType = accountTypeId > 0 ? AccountType.valueOf(accountTypeId) : getAccountType(accountName);
        return accountService.getAccount(accountType, accountName) != null;
    }

    @Override
    public boolean checkDuplicateAccount(String userAccount) throws ServiceException {
        return userAccountMapper.selectCountByAccountName(userAccount) == 0;
    }

    /**
     * 校验用户状态.
     *
     * @param userProfile 用户信息
     * @throws UserException 用户校验不通过直接抛出异常
     */
    private void checkUser(UserProfile userProfile) throws ServiceException {
        if (userProfile == null) {
            throw new ServiceException(UserException.USER_NOT_FOUND, "用户不存在");
        }

        if (userProfile.getAuditStatus() != ActStatus.ACTED) {
            throw new ServiceException(UserException.USER_NOT_ACTIVATE, "用户未激活");
        }
        if (userProfile.getProfileStatus() == AccountStatus.BANNED) {
            throw new ServiceException(UserException.USER_BANNED, "用户被封号");
        }
        if (userProfile.getProfileStatus() == AccountStatus.DEACTIVATED) {
            throw new ServiceException(UserException.USER_DEACTIVATED, "用户被停用");
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = ServiceException.class)
    public PlatformUser loginByToken(String token) throws ServiceException {
        AuthToken refreshToken = tokenService.getToken(token);
        if (refreshToken == null) {
            throw new ServiceException(UserException.USER_TOKEN_NOT_FOUND, "token无效或过期");
        }
        //通过token中用户id获取用户
        UserProfile userProfile = profileService.getProfileByUid(refreshToken.getUserId());
        checkUser(userProfile);
        //返回获取到的用户
        PlatformUser user = new PlatformUser(null, userProfile);
        user.setRefreshToken(refreshToken.getToken());
        //如果需要此处可以取用户allAccount
        return user;
    }

    /**
     * 向用户发送短信验证码
     *
     * @throws ServiceException .
     */
    @Override
    public boolean sendSmsToUser(String phoneNo, String smsContent) throws ServiceException {

        if (!ValidateUtils.isMobile(phoneNo)) {
            throw new UserException(UserException.PHONENO_FORMAT_INVALID);//号码格式错误
        }

        int resp = SendsmsUtils.sendSms(phoneNo, smsContent);

        if (resp < 0) {
            throw SmsResChecker.checkCode(resp);
        }

        return true;
    }

    /**
     * 发送邮箱激活.
     *
     * @throws ServiceException .
     */
    @Override
    public boolean sendEmailToUser(String email, String title, String content) throws ServiceException {

        if (!EmailUtils.checkEmail(email)) {
            throw new UserException(UserException.EMAIL_FORMAT_INVALID, "邮箱格式错误");//邮箱格式错误
        }

        return EmailUtils.sendEmail(title, email, content);
    }


    /**
     * 注册账号.
     */
    @Override
    @Transactional
    public void registerAccount(PlatformUser platformUser) throws ServiceException {
        accountService.createAccount(platformUser.getCurrentAccount());
        profileService.createProfile(platformUser.getUserProfile());
    }

    /**
     * 激活账号.
     */
    @Override
    @Transactional
    public void activeUser(long accountId, long userId) throws ServiceException {
        UserAccount userAccount = new UserAccount();
        userAccount.setConfirmStatus(ActStatus.ACTED);
        userAccount.setConfirmTime(new Date());
        userAccount.setAccountId(accountId);
        UserProfile userProfile = new UserProfile();
        userProfile.setAuditStatus(ActStatus.ACTED);
        userProfile.setUserId(userId);
        accountService.activeAccount(userAccount);
        profileService.acticeProfile(userProfile);
    }

    @Override
    @Transactional
    public boolean registerByEmail(PlatformUser platformUser, String title, String content) throws ServiceException {
        //注册账号
        registerAccount(platformUser);
        //发送邮箱
        return sendEmailToUser(platformUser.getCurrentAccount().getAccountName(), title, content);

    }

    @Override
    public boolean whiteList(String accountName) throws ServiceException {
        return false;
    }

    @Override
    public void userOnline(long userId, Date onlineTime, int areaId) throws ServiceException {
        //TODO 设置异步不需要返回结果的调用，注意配置线程池大小
        logger.info("UserServiceImpl >>> userOnline userId = {}",userId);
        UserProfile userProfile = profileService.getProfileByUid(userId);
        if (!StringUtils.isEmpty(userProfile.getIdcard())) {
            UserIdcard idcard = userIdcardMapper.selectByPrimaryKey(Integer.parseInt(userProfile.getIdcard()));
            if (idcard != null && idcard.getIsChecked()) {
                logger.info("UserServiceImpl >>> userOnline userId = {},创建用户详情",userId);
                UserOnlineDetailExample onlineDetailExample = new UserOnlineDetailExample();
                onlineDetailExample.createCriteria().andIdcardIdEqualTo(idcard.getId()).andUserIdEqualTo(userId);
                List<UserOnlineDetail> userOnlineDetails = userOnlineDetailMapper.selectByExample(onlineDetailExample);
                if (CollectionUtils.isEmpty(userOnlineDetails)) {
                    UserOnlineDetail userOnlineDetail = new UserOnlineDetail();
                    userOnlineDetail.setUserId(userId);
                    userOnlineDetail.setCreateTime(new Date());
                    userOnlineDetail.setLastUpdateTime(new Date());
                    userOnlineDetail.setIdcardId(idcard.getId());
                    userOnlineDetail.setIsOnline(true);
                    userOnlineDetail.setOnlineTime(onlineTime);
                    userOnlineDetail.setAreaId(areaId);
                    userOnlineDetail.setGameId(userProfile.getGameId());
                    try {
                        userOnlineDetail.setId(tableSequenceGenerator
                                .generate(TableSequenceConstant.PLATFORM_USER_ONLINE_DETAIL));
                        logger.info("UserServiceImpl userOnline>>> 准备插入数据库");
                        userOnlineDetailMapper.insert(userOnlineDetail);
                    } catch (ServiceException e) {
                        logger.error("get user_online_detail sequence id failed !", e);
                    }
                } else {
                    logger.info("UserServiceImpl >>> userOnline userId = {},用户详情已存在，更新",userId);
                    UserOnlineDetail userOnlineDetail = userOnlineDetails.get(0);
                    userOnlineDetail.setIsOnline(true);
                    int offlineSeconds = (int) ((onlineTime.getTime() - userOnlineDetail.getOfflineTime().getTime()) / 1000);
                    //计算离线时长
                    logger.info("UserServiceImpl userOnline>>> 计算离线时长");
                    userOnlineDetail.setOfflineSeconds(offlineSeconds + userOnlineDetail.getOfflineSeconds());
                    //离线时长超过5小时，防沉迷归零
                    if (userOnlineDetail.getOfflineSeconds() > 5 * 60 * 60) {
                        logger.info("UserServiceImpl userOnline>>> 离线时长超过5小时，防沉迷归零");
                        userOnlineDetail.setOnlineSeconds(0);
                        userOnlineDetail.setOfflineSeconds(0);
                    }
                    logger.info("UserServiceImpl userOnline>>> 准备修改数据库");
                    userOnlineDetail.setOnlineTime(onlineTime);
                    userOnlineDetailMapper.updateByPrimaryKey(userOnlineDetail);

                    //防沉迷检查
                    checkAddiction(idcard.getId(), 3 * 60 * 60, userId);
                }
            }
        }
    }

    @Override
    public void userOffline(long userId, Date offlineTime, int areaId) {
        logger.info("UserServiceImpl userOffline>>> userId = {}",userId);
        UserProfile userProfile = profileService.getProfileByUid(userId);
        if (!StringUtils.isEmpty(userProfile.getIdcard())) {
            UserIdcard idcard = userIdcardMapper.selectByPrimaryKey(Integer.parseInt(userProfile.getIdcard()));
            if (idcard != null && idcard.getIsChecked()) {
                UserOnlineDetailExample onlineDetailExample = new UserOnlineDetailExample();
                onlineDetailExample.createCriteria().andIdcardIdEqualTo(idcard.getId()).andUserIdEqualTo(userId);
                List<UserOnlineDetail> userOnlineDetails = userOnlineDetailMapper.selectByExample(onlineDetailExample);
                if (CollectionUtils.isNotEmpty(userOnlineDetails)) {
                    UserOnlineDetail userOnlineDetail = userOnlineDetails.get(0);
                    //计算在线时长
                    logger.info("UserServiceImpl userOffline>>> 计算在线时长");
                    int onlineSeconds = (int) ((offlineTime.getTime() - userOnlineDetail.getLastUpdateTime().getTime()) / 1000);
                    logger.info("UserServiceImpl userOffline>>> 计算在线时长 : {}",onlineSeconds);
                    userOnlineDetail.setOnlineSeconds(userOnlineDetail.getOnlineSeconds() + onlineSeconds);
                    logger.info("UserServiceImpl userOffline>>> setOnlineSeconds: {}",userOnlineDetail.getOnlineSeconds() + onlineSeconds);
                    userOnlineDetail.setIsOnline(false);
                    userOnlineDetail.setOfflineTime(offlineTime);
                    userOnlineDetail.setLastUpdateTime(offlineTime);
                    logger.info("UserServiceImpl userOffline>>> 准备修改数据库");
                    userOnlineDetailMapper.updateByPrimaryKey(userOnlineDetail);
                }
            }
        }
    }

    @Override
    public void checkAddiction(int idcardId, int checkSeconds, long userId) throws ServiceException {
        logger.info("UserServiceImpl checkAddiction>>> userId = {}",userId);
        UserOnlineDetailExample onlineDetailExample = new UserOnlineDetailExample();
        onlineDetailExample.createCriteria().andIdcardIdEqualTo(idcardId);
        List<UserOnlineDetail> userOnlineDetails = userOnlineDetailMapper.selectByExample(onlineDetailExample);
        int totalOlineTime = 0;
        UserOnlineDetail currentUser = null;
        for (UserOnlineDetail userOnlineDetail : userOnlineDetails) {
            totalOlineTime += userOnlineDetail.getOnlineSeconds();
            if (userOnlineDetail.getUserId() == userId) {
                currentUser = userOnlineDetail;
            }
        }
        boolean isSendNotice = false;
        if (checkSeconds > 0) {
            logger.info("UserServiceImpl checkAddiction checkSeconds>0");
            isSendNotice = totalOlineTime > checkSeconds;
        } else {
            // 累计在线时间”满1小时/2小时/3小时/4小时/4小时30分/5小时+15分*X时间点通知
            //统计周期为5分钟，所以满1小时，未满1小时零5分,满两小时，未满2小时零5分,以此类推
            int hourSeconds = 60 * 60;
            int intervalSeconds = 5 * 60;
            //1-4小时，每隔1小时
            if (totalOlineTime > 0 && totalOlineTime <= (4 * hourSeconds + intervalSeconds)) {
                logger.info("UserServiceImpl checkAddiction 1-4小时，每隔1小时");
                isSendNotice = checkPeriodSeconds(totalOlineTime, hourSeconds, intervalSeconds);
                //4-5小时，每隔半小时
            } else if (totalOlineTime > (4 * hourSeconds + intervalSeconds)
                    && totalOlineTime <= (5 * hourSeconds + intervalSeconds)) {
                logger.info("UserServiceImpl checkAddiction 4-5小时，每隔半小时");
                isSendNotice = checkPeriodSeconds(totalOlineTime, 30 * 60, intervalSeconds);
                //超过5小时，每隔15分钟
            } else if (totalOlineTime > (5 * hourSeconds + intervalSeconds)) {
                logger.info("UserServiceImpl checkAddiction 超过5小时，每隔15分钟");
                isSendNotice = checkPeriodSeconds(totalOlineTime, 15 * 60, intervalSeconds);
            }


        }

        if (isSendNotice) {
            //发送防沉迷通知.
            if (currentUser != null) {
                operationDispatchService.sendAntiAddiction(GameCode.getById(currentUser.getGameId()),
                        currentUser.getAreaId(), userId, totalOlineTime * 1000);
                logger.info("userSerivceImple >>>> sendAntiAddiction");
            }
        }
    }

    /**
     * 判定当前时长是否满足某个周期时间（period），并且在一个区间内(interval).
     * </p>列：periodSeconds为1小时，interval为5分钟，那么满足条件的就是1小时~1小时零5分，2小时~2小时零5分...n小时~n小时零5分.
     *
     * @param currentSeconds  当前要判定的时长
     * @param periodSeconds   周期时间
     * @param intervalSeconds 时间间隔
     * @return boolean
     */
    private boolean checkPeriodSeconds(int currentSeconds, int periodSeconds, int intervalSeconds) {
        return currentSeconds > periodSeconds && ((currentSeconds % periodSeconds) < (5 * 60));
    }

    /**
     * 根据账户名称判断账户类型
     *
     * @param accountName 账户名称.
     * @return 账户类型
     */
    private AccountType getAccountType(String accountName) {
        if (ValidateUtils.isEmail(accountName)) {
            return AccountType.EMAIL;
        } else if (ValidateUtils.isMobile(accountName)) {
            return AccountType.PHONE_NUMBER;
        } else {
            return AccountType.ACCOUNT_NAME;
        }
    }


}
