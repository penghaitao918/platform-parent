package com.fanfandou.platform.serv.user.dao;

import com.fanfandou.platform.api.user.entity.UserProfile;
import com.fanfandou.platform.api.user.entity.UserProfileExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserProfileMapper {
    int countByExample(UserProfileExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(UserProfile record);

    int insertSelective(UserProfile record);

    List<UserProfile> selectByExample(UserProfileExample example);

    UserProfile selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") UserProfile record, @Param("example") UserProfileExample example);

    int updateByExample(@Param("record") UserProfile record, @Param("example") UserProfileExample example);

    int updateByPrimaryKeySelective(UserProfile record);

    int updateByPrimaryKey(UserProfile record);
}