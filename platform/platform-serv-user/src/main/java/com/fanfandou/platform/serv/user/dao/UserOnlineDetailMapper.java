package com.fanfandou.platform.serv.user.dao;

import com.fanfandou.platform.api.user.entity.UserOnlineDetail;
import com.fanfandou.platform.api.user.entity.UserOnlineDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("userOnlineDetailMapper")
public interface UserOnlineDetailMapper {
    int countByExample(UserOnlineDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserOnlineDetail record);

    int insertSelective(UserOnlineDetail record);

    List<UserOnlineDetail> selectByExample(UserOnlineDetailExample example);

    UserOnlineDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserOnlineDetail record, @Param("example") UserOnlineDetailExample example);

    int updateByExample(@Param("record") UserOnlineDetail record, @Param("example") UserOnlineDetailExample example);

    int updateByPrimaryKeySelective(UserOnlineDetail record);

    int updateByPrimaryKey(UserOnlineDetail record);
}