package com.fanfandou.platform.api.user.service;

import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.user.entity.AccountType;
import com.fanfandou.platform.api.user.entity.UserAccount;

import java.util.List;

/**
 * Description: 账号相关接口.
 * <p/>
 * Date: 2016-02-23 16:34.
 * author: Arvin.
 */
public interface AccountService {

    void createAccount(UserAccount userAccount) throws ServiceException;

    UserAccount getAccount(AccountType accountType, String accountName);

    //UserAccount getAccountByUid(long userId) throws ServiceException;

    List<UserAccount> getAccountsByUid(long userId) throws ServiceException;

    int getAccountById(String accountId) throws ServiceException;

    void modifyPassword(UserAccount userAccount, String newPassWord, String oldPassword, String ip) throws
            ServiceException;

    void findPassword(long accountId, String password,  String ip) throws ServiceException;

    UserAccount getAccountByAccountId(long accountId) throws ServiceException;

    void activeAccount(UserAccount userAccount) throws ServiceException;
}
