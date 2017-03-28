package com.fanfandou.admin.operation.service;

import com.fanfandou.admin.operation.entity.ItemType;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;

import java.util.List;

/**
 * Created by wangzhenwei on 2016/6/30.
 * Description 游戏物品类型service接口
 */
public interface ItemTypeService {


    /**
     * 添加.
     */
    void addItemType(ItemType itemType) throws ServiceException;

    /**
     * 删除 by id.
     */
    void delItemType(List<Integer> idList);

    /**
     * 更新角色.
     */
    void updItemType(ItemType stu) throws ServiceException;

    /**
     * 查询所有.
     */
    List<ItemType> selectAll();

    /**
     * 从数据库模糊查询出本页数据并排序.
     */
    PageResult<ItemType> getPageList(String itemTypeName,int gameId, Page page);

    /**
     * 加入了按照类型查询.
     * @param itemTypeName 模糊查询的str
     * @param gameId 游戏id
     * @param itemType 物品类型
     * @param page page对象
     */
    PageResult<ItemType> pageList(String itemTypeName,int gameId,int itemType, Page page);

    /**
     * 通过id查找.
     */
    ItemType selItemTypeById(int id);

    /**
     * 通过游戏id查询.
     */
    List<ItemType> selByGameId(int gameId);

    /**
     * 一键开启和关闭 0关闭 1开启.
     */
    void optAvailable(String itemIds,int available) throws ServiceException;

    /**
     * 根据类型查询物品.
     * @param itemType 类型
     * @return 物品集合
     */
    List<ItemType> selByType(int itemType);

    /**
     * 根据code查询.
     * @param itemCode code
     */
    ItemType selByCode(int gameId,int itemCode);

    /**
     * itemCode == 0时，模糊。itemCode ！= 0,精确.
     */
    PageResult<ItemType> twoFind(String itemTypeName,int gameId,int itemType, int itemCode,Page page);

    /**
     * selByCodeAndType.
     * @param gameId 游戏id
     */
    ItemType selByCodeAndType(int gameId,int itemCode,int itemType);

    /**
     * 物品excel导入.
     * @param url 路径
     * @param fileName 文件名
     * @return 错误原因集合
     */
    List<String> importExcel(String url, String fileName) throws Exception;
}
