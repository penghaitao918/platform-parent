package com.fanfandou.platform.serv.game.dao;

import com.fanfandou.platform.api.game.entity.GameOperation;
import com.fanfandou.platform.api.game.entity.GameOperationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameOperationMapper {
    int countByExample(GameOperationExample example);

    int deleteByPrimaryKey(Long optId);

    int insert(GameOperation record);

    int insertSelective(GameOperation record);

    List<GameOperation> selectByExample(GameOperationExample example);

    GameOperation selectByPrimaryKey(Long optId);

    int updateByExampleSelective(@Param("record") GameOperation record, @Param("example") GameOperationExample example);

    int updateByExample(@Param("record") GameOperation record, @Param("example") GameOperationExample example);

    int updateByPrimaryKeySelective(GameOperation record);

    int updateByPrimaryKey(GameOperation record);
}