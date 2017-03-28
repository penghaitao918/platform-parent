package com.fanfandou.admin.system.dao;

import com.fanfandou.admin.system.entity.Relation;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzhenwei on 2016/3/22.
 * Description 关联表dao
 */
public interface RelationMapper {

    List<Relation> selectAll();

    int selectId(Relation relation);

    /**
     * 通过id1和idType查找id2.
     */
    List<Integer> selectIdBy(Map map);

    void insert(Relation relation);

    void delete(int id);

    void update(Relation relation);

    Relation selectById(int id);
}
