package com.fanfandou.platform.serv.user.dao;

import com.fanfandou.platform.api.user.entity.UserAccount;
import com.fanfandou.platform.api.user.entity.UserAccountExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserAccountMapper {
    int countByExample(UserAccountExample example);

    int deleteByPrimaryKey(Long accountId);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    List<UserAccount> selectByExample(UserAccountExample example);

    UserAccount selectByPrimaryKey(Long accountId);

    List<UserAccount> selectAccountsByUid(Long userId);

    int upadteInfosByAccountId(UserAccount record);

    int updateByExampleSelective(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    int updateByExample(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);

    int selectCountByAccountName(String accountName);

    int modifyPasswordByAccountId(UserAccount userAccount);

    UserAccount getAccountByName(UserAccount record);

}