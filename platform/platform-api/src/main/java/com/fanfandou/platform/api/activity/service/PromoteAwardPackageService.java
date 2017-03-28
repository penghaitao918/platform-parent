package com.fanfandou.platform.api.activity.service;

import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.activity.entity.GameActivity;
import com.fanfandou.platform.api.activity.entity.PromoteAwardPackage;
import com.fanfandou.platform.api.activity.entity.PromoteCodeBatch;
import com.fanfandou.platform.api.activity.exception.ActivityException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by zhongliang.
 * Descreption:礼包码-批次礼包管理.
 * Date:2016/11/22
 */
public interface PromoteAwardPackageService {

    /**
     * 新增礼包.
     *
     * @param promoteAwardPackage 礼包对象.
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void createAwardPackage(PromoteAwardPackage promoteAwardPackage) throws ServiceException;

    /**
     * 修改礼包.
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void updateAwardPackage(PromoteAwardPackage promoteAwardPackage) throws ServiceException;

    /**
     * 礼包分页查询.
     */
    PageResult getAwardPackage(Integer gameId, String packageName, Page page) throws ServiceException;

    /**
     * 查询单个礼包信息.
     *
     * @param id 礼包ID
     */
    PromoteAwardPackage getAwardPackageById(Integer id) throws ServiceException;

    /**
     * 删除礼包信息.
     *
     * @param idList 礼包ID集合
     */
    void deleteAwardPackageById(List<Integer> idList) throws ServiceException;



    /**
     * 获取礼包集合
     * @param gameId .
     * @return list
     * @throws ServiceException
     */
    List<PromoteAwardPackage> getList(Integer gameId) throws ServiceException;
}
