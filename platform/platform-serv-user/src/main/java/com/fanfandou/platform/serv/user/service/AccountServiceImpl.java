package com.fanfandou.platform.serv.user.service;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.util.CipherUtils;
import com.fanfandou.platform.api.user.entity.AccountType;
import com.fanfandou.platform.api.user.entity.UserAccount;
import com.fanfandou.platform.api.user.exception.UserException;
import com.fanfandou.platform.api.user.service.AccountService;
import com.fanfandou.platform.api.user.service.UserService;
import com.fanfandou.platform.serv.user.dao.UserAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description: 账号业务实现.
 * <p/>
 * Date: 2016-02-23 19:46.
 * author: Arvin.
 */
@Service("accountService")
public class AccountServiceImpl extends BaseLogger implements AccountService {

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private UserService userService;

    @Override
    public void createAccount(UserAccount userAccount) throws ServiceException {
        userAccountMapper.insert(userAccount);
    }

    @Override
    public UserAccount getAccount(AccountType accountType, String accountName) {
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountName(accountName);
        userAccount.setAccountType(accountType);
        return userAccountMapper.getAccountByName(userAccount);
    }

    @Override
    public List<UserAccount> getAccountsByUid(long userId) throws ServiceException {
        return userAccountMapper.selectAccountsByUid(userId);
    }

    @Override
    public int getAccountById(String accountName) throws ServiceException {
        return userAccountMapper.selectCountByAccountName(accountName);
    }

    @Override
    public void modifyPassword(UserAccount account, String newPassWord, String oldPassword, String ip)
            throws
            ServiceException {
        //根据主键查询UserAccount
        UserAccount userAccount = userAccountMapper.getAccountByName(account);
        if (oldPassword != null) {
            if (!userAccount.getPassword().equals(getPasswd(oldPassword,userAccount.getEncryptSeed()))) {
                throw new ServiceException(UserException.ORIGINAL_PWD_INVALID,"原先密码错误");
            }
        }
        userAccount.setLastUpdateTime(new Date());
        userAccount.setLastUpdateIp(ip);

        //重新随机生成encryptSeed，重新生成密码
        String chars = "abcdefghijklmnopqrstuvwxyz";
        String seed = "";
        for (int i = 0;i < 5;i++) {
            seed = seed + chars.charAt((int)(Math.random() * 26));
        }

        userAccount.setEncryptSeed(seed);
        userAccount.setPassword(getPasswd(newPassWord,seed));

        userAccountMapper.updateByPrimaryKeySelective(userAccount);
    }

    @Override
    public void findPassword(long accountId, String password, String ip) throws ServiceException {

    }

    @Override
    public UserAccount getAccountByAccountId(long accountId) throws ServiceException {

        return userAccountMapper.selectByPrimaryKey(accountId);
    }

    @Override
    public void activeAccount(UserAccount userAccount) throws ServiceException {
        userAccountMapper.updateByPrimaryKeySelective(userAccount);
    }

    /**
     * 密码加密.
     *
     * @param passwd      原密码
     * @param encryptSeed 加密种子
     * @return 加密后密码
     */
    private String getPasswd(String passwd, String encryptSeed) {
        try {
            return CipherUtils.initMd5().encrypt(encryptSeed + CipherUtils.initMd5().encrypt(passwd + encryptSeed));
        } catch (Exception e) {
            logger.error("加密失败",e);
        }
        return "";
    }


}
