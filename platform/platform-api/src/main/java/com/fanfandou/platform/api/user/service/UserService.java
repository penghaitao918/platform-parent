package com.fanfandou.platform.api.user.service;

import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.user.entity.PlatformUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Description: 平台用户相关业务.
 *
 * @author Arvin.
 */
public interface UserService {

    /**
     * 账号登录.
     * @param accountName 登录用户名
     * @param passwd 密码
     * @return user
     * @throws ServiceException ServiceException
     */
    PlatformUser loginByAccount(String accountName, String passwd, int accountType, String ipAddress, String serial)
            throws ServiceException;


    boolean isExistAccount(String account, int accountTypeId) throws ServiceException;

    boolean checkDuplicateAccount(String userAccount) throws ServiceException;

    /**
     * token登录.
     * @param token token
     * @return user
     * @throws ServiceException ServiceException
     */
    PlatformUser loginByToken(String token) throws ServiceException;

    /**
     * 给用户发送短信.
     */
    boolean sendSmsToUser(String phoneNo , String smsContent) throws ServiceException;

    /**
     * 给用户发送邮件.
     */
    boolean sendEmailToUser(String email, String title, String content)throws ServiceException;

    /**
     * 注册账号.
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void registerAccount(PlatformUser platformUser)throws ServiceException;

    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void activeUser(long accountId, long userId) throws ServiceException;

    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    boolean registerByEmail(PlatformUser platformUser, String title, String content) throws ServiceException;

    /**
     * 白名单功能.
     */
    boolean whiteList(String accountName) throws ServiceException;


    /**
     * 本接口主要用于防沉迷，记录用户上线，并结算离线时间（非防沉迷用户不计算）.
     * @param userId userId
     * @param onlineTime 上线时间
     * @param areaId 区服id
     */
    void userOnline(long userId, Date onlineTime, int areaId) throws ServiceException;

    /**
     * 本接口主要用于防沉迷，记录用户下线，并结算在线时间（非防沉迷用户不计算）.
     * @param userId userId
     * @param offlineTime 下线时间
     * @param areaId 区服id
     */
    void userOffline(long userId, Date offlineTime, int areaId);

    /**
     * 检查某个身份证是否沉迷，如果已达到沉迷条件，发出防沉迷通知.
     * @param idcardId 身份证id.
     * @param checkSeconds 校验的秒数，如果为0则按默认规则校验.
     *  @param userId userId
     */
    void checkAddiction(int idcardId, int checkSeconds, long userId) throws ServiceException;

}
