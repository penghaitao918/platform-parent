package com.fanfandou.platform.serv.game.dao;

import com.fanfandou.platform.api.game.entity.GameRole;
import com.fanfandou.platform.api.game.entity.GameRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GameRoleMapper {
    int countByExample(GameRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GameRole record);

    int insertSelective(GameRole record);

    List<GameRole> selectByExample(GameRoleExample example);

    GameRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GameRole record, @Param("example") GameRoleExample example);

    int updateByExample(@Param("record") GameRole record, @Param("example") GameRoleExample example);

    int updateByPrimaryKeySelective(GameRole record);

    int updateByPrimaryKey(GameRole record);
}