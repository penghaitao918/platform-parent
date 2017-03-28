package com.fanfandou.platform.serv.game.dao;

import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.game.entity.GameArea;
import com.fanfandou.platform.api.game.entity.GameAreaExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameAreaMapper {
    int countByExample(GameAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GameArea record);

    int insertSelective(GameArea record);

    List<GameArea> selectByExample(GameAreaExample example);

    List<GameArea> getAreasSelective(Map map);

    List<GameArea> getAreasByGameId(int gameId);

    List<GameArea> getAreasByGameCode(@Param("gameId")int gameId,@Param("areaCode") String gameCode);

    List<GameArea> getAreasAll();

    int checkCodeExist(@Param("gameId") int gameId, @Param("areaCode") String areaCode);

    int getAreasCountForGroup(int id);

    int getTotalArea(Map map);

    GameArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GameArea record, @Param("example") GameAreaExample example);

    int updateByExample(@Param("record") GameArea record, @Param("example") GameAreaExample example);

    int updateByPrimaryKeySelective(GameArea record);

    int updateByPrimaryKey(GameArea record);
}