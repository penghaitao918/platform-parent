package com.fanfandou.platform.serv.activity.dao;

import java.util.List;
import java.util.Map;

import com.fanfandou.platform.api.activity.entity.GameActivity;
import com.fanfandou.platform.api.activity.entity.GameActivityExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameActivityMapper {
    int countByExample(GameActivityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GameActivity record);

    int insertSelective(GameActivity record);

    List<GameActivity> selectByExample(GameActivityExample example);

    List<GameActivity> selectBySelective(Map map);

    int getAllCount(Map map);

    GameActivity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GameActivity record, @Param("example") GameActivityExample example);

    int updateByExample(@Param("record") GameActivity record, @Param("example") GameActivityExample example);

    int updateByPrimaryKeySelective(GameActivity record);

}