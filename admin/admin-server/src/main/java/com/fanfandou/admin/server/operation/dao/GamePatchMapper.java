package com.fanfandou.admin.server.operation.dao;

import java.util.List;
import java.util.Map;

import com.fanfandou.admin.api.operation.entity.GamePatch;
import com.fanfandou.admin.api.operation.entity.GamePatchExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository(value = "gamePatchMapper")
public interface GamePatchMapper {
    int countByExample(GamePatchExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GamePatch record);

    int insertSelective(GamePatch record);

    List<GamePatch> selectByExample(GamePatchExample example);

    GamePatch selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GamePatch record, @Param("example") GamePatchExample example);

    int updateByExample(@Param("record") GamePatch record, @Param("example") GamePatchExample example);

    int updateByPrimaryKeySelective(GamePatch record);

    int updateByPrimaryKey(GamePatch record);

    List<GamePatch> pageList(Map paramMap);

    int totalCount(Map map);

    void delete(int id);

    GamePatch selectById(int id);
}