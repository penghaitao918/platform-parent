package com.fanfandou.platform.serv.user.dao;

import com.fanfandou.platform.api.user.entity.UserIdcard;
import com.fanfandou.platform.api.user.entity.UserIdcardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("userIdcardMapper")
public interface UserIdcardMapper {
    int countByExample(UserIdcardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserIdcard record);

    int insertSelective(UserIdcard record);

    List<UserIdcard> selectByExample(UserIdcardExample example);

    UserIdcard selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserIdcard record, @Param("example") UserIdcardExample example);

    int updateByExample(@Param("record") UserIdcard record, @Param("example") UserIdcardExample example);

    int updateByPrimaryKeySelective(UserIdcard record);

    int updateByPrimaryKey(UserIdcard record);
}