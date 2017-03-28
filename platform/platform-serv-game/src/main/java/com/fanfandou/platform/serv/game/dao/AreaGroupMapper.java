package com.fanfandou.platform.serv.game.dao;

import java.util.List;
import java.util.Map;

import com.fanfandou.common.entity.result.Page;
import com.fanfandou.platform.api.game.entity.AreaGroup;
import com.fanfandou.platform.api.game.entity.AreaGroupExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaGroupMapper {
    int countByExample(AreaGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AreaGroup record);

    int insertSelective(AreaGroup record);

    List<AreaGroup> selectByExample(AreaGroupExample example);

    List<AreaGroup> getAreaGroupByGameId(int gameId);

    List<AreaGroup> getAreaGroupPage(Map map);

    int checkCodeExist(@Param("gameId") int gameId, @Param("areaGroupCode") String areaGroupCode);

    int getAreaGroupSize(Map map);

    AreaGroup getAreaGroup();

    AreaGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AreaGroup record, @Param("example") AreaGroupExample example);

    int updateByExample(@Param("record") AreaGroup record, @Param("example") AreaGroupExample example);

    int updateByPrimaryKeySelective(AreaGroup record);

    int updateByPrimaryKey(AreaGroup record);
}