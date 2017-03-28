package com.fanfandou.admin.operation.dao;

import com.fanfandou.admin.operation.entity.ItemType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzhenwei on 2016/6/30.
 * Description 游戏物品类型dao
 */
@Repository(value = "itemTypeMapper")
public interface ItemTypeMapper {

    /**
     * 查询.
     */
    List<ItemType> selectAll();

    /**
     * selectByType.
     */
    List<ItemType> selectByType(int itemType);

    /**
     * selByGameId.
     */
    List<ItemType> selByGameId(int gameId);

    /**
     * 分页.
     */
    List<ItemType> pageList(Map paramMap);

    /**
     * pageListByType.
     */
    List<ItemType> pageListByType(Map paramMap);

    /**
     * 分页数量.
     */
    int totalCount(Map map);

    /**
     * 分页数量.
     */
    int totalCountByType(Map map);

    /**
     * 增.
     */
    void insert(ItemType itemType);

    /**
     * 删.
     */
    void delete(int id);

    /**
     * 改.
     */
    void update(ItemType itemType);

    /**
     * selectById.
     */
    ItemType selectById(int id);

    /**
     * selectByCode.
     */
    ItemType selectByCode(Map map);

    /**
     * selByCodeAndType.
     */
    ItemType selByCodeAndType(Map map);
}
