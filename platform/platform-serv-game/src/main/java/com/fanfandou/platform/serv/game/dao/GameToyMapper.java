package com.fanfandou.platform.serv.game.dao;

import com.fanfandou.common.entity.result.Page;
import com.fanfandou.platform.api.game.entity.GameToy;
import com.fanfandou.platform.api.game.entity.GameToyExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface GameToyMapper {
    int countByExample(GameToyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GameToy record);

    int insertSelective(GameToy record);

    List<GameToy> selectByExample(GameToyExample example);

    GameToy selectByPrimaryKey(Long id);

    GameToy selectByUser(long uid);

    List<GameToy> getMaxToyCode();

    GameToy selectByCode(String toyCode);

    GameToy selectByToyGuid(long toyGuid);

    long getBindUid(long toyGuid);

    List<GameToy> getGameToyBySelective(Map map);

    List<GameToy> getGameToyBySelective(@Param("gameToy") GameToy gameToy, @Param("gameToy") Page page, Date from, Date to);

    GameToy checkBindCode(long toyGuid);

    List<Integer> getActedToyType(long userId);

    int totalCount(Map map);

    List<GameToy> selectCountAll();

    int updateByExampleSelective(@Param("record") GameToy record, @Param("example") GameToyExample example);

    int updateByExample(@Param("record") GameToy record, @Param("example") GameToyExample example);

    int updateByPrimaryKeySelective(GameToy record);

    int updateByCodeSelective(GameToy gameToy);

    int updateByPrimaryKey(GameToy record);
}