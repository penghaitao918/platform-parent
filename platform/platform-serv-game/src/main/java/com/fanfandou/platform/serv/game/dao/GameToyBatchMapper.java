package com.fanfandou.platform.serv.game.dao;

import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.platform.api.game.entity.GameToyBatch;
import com.fanfandou.platform.api.game.entity.GameToyBatchExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GameToyBatchMapper {
    int countByExample(GameToyBatchExample example);

    int deleteByPrimaryKey(Integer batchId);

    int insert(GameToyBatch record);

    int insertSelective(GameToyBatch record);

    List<GameToyBatch> selectByExample(GameToyBatchExample example);

    int getMaxCode();

    List<GameToyBatch> activeCodeBatch();

    List<GameToyBatch> selectBatchByType(int toyType);

    List<GameToyBatch> selectGameToyBatchForPage(Map map);

    List<GameToyBatch> selectGameToyBatchForPageTest(@Param("gameToyBatch") GameToyBatch gameToyBatch, @Param("page")Page page);

    int totalCount(Map map);

    GameToyBatch selectByPrimaryKey(Integer batchId);

    int updateByExampleSelective(@Param("record") GameToyBatch record, @Param("example") GameToyBatchExample example);

    int updateByExample(@Param("record") GameToyBatch record, @Param("example") GameToyBatchExample example);

    int updateByPrimaryKeySelective(GameToyBatch record);

    int updateByPrimaryKey(GameToyBatch record);
}