package com.fanfandou.admin.operation.service.impl;

import com.fanfandou.admin.api.exception.AdminException;
import com.fanfandou.admin.operation.dao.ItemTypeMapper;
import com.fanfandou.admin.operation.entity.ItemType;
import com.fanfandou.admin.operation.service.InputExcelService;
import com.fanfandou.admin.operation.service.ItemTypeService;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzhenwei on 2016/6/30.
 * Description 游戏物品类型service实现类
 */
@Service("itemTypeService")
public class ItemTypeServiceImpl extends BaseLogger implements ItemTypeService {
    @Autowired
    private ItemTypeMapper itemTypeMapper;

    @Autowired
    private InputExcelService inputExcelService;

    /**
     * 查询所有.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<ItemType> selectAll() {
        return this.itemTypeMapper.selectAll();
    }

    /**
     * 获取分页数据
     * 模糊查询，分页，排序.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public PageResult<ItemType> getPageList(String itemTypeName, int gameId, Page page) {
        if (page.getSort() == null || page.getSort().equals("")) {
            page.setSort("desc");
        }
        if (page.getOrder() == null || page.getOrder().equals("")) {
            page.setOrder("id");
        }

        if (gameId == 0 || gameId==-1) {
            gameId = 1;
        }
        String str = '%' + itemTypeName + '%';
        int num1 = (page.getPageIndex() - 1) * page.getPageSize();
        Map paramMap = new HashMap();
        paramMap.put("itemName", str);
        paramMap.put("str1", page.getOrder());
        paramMap.put("str2", page.getSort());
        paramMap.put("num1", num1);
        paramMap.put("num2", page.getPageSize());
        paramMap.put("gameId", gameId);

        List<ItemType> list = this.itemTypeMapper.pageList(paramMap);

        Map map = new HashMap();
        map.put("itemName", str);
        map.put("gameId", gameId);
        int totalCount = this.itemTypeMapper.totalCount(map);

        page.setTotalCount(totalCount);
        PageResult<ItemType> pageResult = new PageResult<>();
        pageResult.setPage(page);
        pageResult.setRows(list);
        return pageResult;
    }

    /**
     * 获取分页数据
     * 模糊查询，分页，排序.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public PageResult<ItemType> pageList(String itemTypeName, int gameId, int itemType, Page page) {
        String str = '%' + itemTypeName + '%';

        if (page.getSort() == null || page.getSort().equals("")) {
            page.setSort("desc");
        }
        if (page.getOrder() == null || page.getOrder().equals("")) {
            page.setOrder("id");
        }
        int num1 = (page.getPageIndex() - 1) * page.getPageSize();
        Map paramMap = new HashMap();
        paramMap.put("itemName", str);
        paramMap.put("str1", page.getOrder());
        paramMap.put("str2", page.getSort());
        paramMap.put("itemType", itemType);
        paramMap.put("num1", num1);
        paramMap.put("num2", page.getPageSize());
        paramMap.put("gameId", gameId);

        Map map = new HashMap();
        map.put("itemName", str);
        map.put("gameId", gameId);
        map.put("itemType", itemType);

        int totalCount = this.itemTypeMapper.totalCountByType(map);
        List<ItemType> list = this.itemTypeMapper.pageListByType(paramMap);
        page.setTotalCount(totalCount);
        PageResult<ItemType> pageResult = new PageResult<>();
        pageResult.setPage(page);
        pageResult.setRows(list);
        return pageResult;
    }

    /**
     * 添加.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addItemType(ItemType itemType) throws ServiceException {
        int gameId = itemType.getGameId();
        int itemCode = itemType.getItemCode();
        int itemType1 = itemType.getItemType();
        ItemType item = selByCodeAndType(gameId, itemCode, itemType1);
        if (item != null) {
            throw new ServiceException(AdminException.ADMIN_WRONG_ITEMCODE);
        } else {
            Date now = new Date();
            itemType.setCreateTime(now);
            itemType.setAvailable(1);
            this.itemTypeMapper.insert(itemType);
        }
    }

    /**
     * 删除.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void delItemType(List<Integer> idList) {
        for (int i = 0; i < idList.size(); i++) {
            int id = idList.get(i);
            this.itemTypeMapper.delete(id);
        }
    }

    /**
     * 更新.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void updItemType(ItemType itemType) throws ServiceException {
        ItemType itemTypeOld = this.selItemTypeById(itemType.getId());
        itemType.setCreateTime(itemTypeOld.getCreateTime());
        itemType.setAvailable(1);
        int oldItemCode = itemTypeOld.getItemCode();
        int gameId = itemType.getGameId();
        int itemCode = itemType.getItemCode();
        int itemType1 = itemType.getItemType();
        ItemType item = selByCodeAndType(gameId, itemCode, itemType1);
        if (oldItemCode == itemCode) {
            this.itemTypeMapper.update(itemType);
        } else {
            if (item == null) {
                this.itemTypeMapper.update(itemType);
            } else {
                throw new ServiceException(AdminException.ADMIN_WRONG_ITEMCODE);
            }
        }
    }

    /**
     * 通过id查找.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public ItemType selItemTypeById(int id) {
        return this.itemTypeMapper.selectById(id);
    }

    /**
     * 通过gameId查找.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<ItemType> selByGameId(int gameId) {
        return this.itemTypeMapper.selByGameId(gameId);
    }

    /**
     * 一键开启和关闭.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void optAvailable(String itemIds, int available) throws ServiceException {
        String[] itemIdStr = itemIds.split(",");
        for (String itemId : itemIdStr) {
            int id = Integer.parseInt(itemId);
            ItemType itemType = this.selItemTypeById(id);
            if (available == 0 || available == 1) {
                itemType.setAvailable(available);
                this.updItemType(itemType);
            }
        }
    }

    /**
     * select By type.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<ItemType> selByType(int itemType) {
        return this.itemTypeMapper.selectByType(itemType);
    }

    /**
     * select By code.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public ItemType selByCode(int gameId, int itemCode) {
        Map map = new HashMap();
        map.put("gameId", gameId);
        map.put("itemCode", itemCode);

        return itemTypeMapper.selectByCode(map);
    }

    /**
     * 分页数据.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public PageResult<ItemType> twoFind(String itemTypeName, int gameId, int itemType, int itemCode, Page page) {
        PageResult<ItemType> pageResult = new PageResult<>();
        if (itemCode == 0) {
            return pageList(itemTypeName, gameId, itemType, page);
        } else {
            ItemType item = selByCode(gameId, itemCode);
            List<ItemType> list = new ArrayList<>();
            list.add(item);
            pageResult.setPage(page);
            pageResult.setRows(list);
        }
        return pageResult;
    }

    /**
     * selByCodeAndType.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public ItemType selByCodeAndType(int gameId, int itemCode, int itemType) {
        Map map = new HashMap();
        map.put("gameId", gameId);
        map.put("itemCode", itemCode);
        map.put("itemType", itemType);
        return itemTypeMapper.selByCodeAndType(map);
    }

    /**
     * excel导入
     *
     * @param url      路径
     * @param fileName 文件名.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<String> importExcel(String url, String fileName) throws Exception {
        FileInputStream is = new FileInputStream(new File(url));
        return inputExcelService.importItemExcel(is, fileName);
    }

}
