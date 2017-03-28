package com.fanfandou.platform.api.activity.service;

import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.activity.entity.PromoteAwardPackage;
import com.fanfandou.platform.api.activity.entity.PromoteCodeBatch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by zhongliang.
 * Descreption:礼包码-批次管理.
 * Date:2016/11/22
 */
public interface PromoteCodeBatchService {

    /**
     * 新增批次.
     *
     * @param promoteCodeBatch 礼包对象.
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void createCodeBatch(PromoteCodeBatch promoteCodeBatch) throws ServiceException;

    /**
     * 修改批次.
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void updateCodeBatch(PromoteCodeBatch promoteCodeBatch) throws ServiceException;

    /**
     * 批次分页查询.
     */
    PageResult getCodeBatch(Integer gameId, String batchName, Page page, String from, String to) throws Exception;

    /**
     * 查询单个批次信息.
     *
     * @param id 礼包ID
     */
    PromoteCodeBatch getCodeBatchById(Integer id) throws ServiceException;

    /**
     * 删除批次信息.
     *
     * @param idList 礼包ID集合
     */
    void deleteCodeBatchById(List<Integer> idList) throws ServiceException;

    /**
     * 生成礼包码.
     *
     * @param promoteCodeBatch 批次信息.
     */
    List<String> generatePromoteCode(PromoteCodeBatch promoteCodeBatch) throws ServiceException;


    /**
     * 礼包/激活码校验.
     */
    String checkPromoteCode(int gameId, int siteId, int roleId, String code) throws ServiceException;

    /**
     * 获取礼包码集合（缓存）
     *
     * @throws Exception
     */
    List getPromoteCodeList() throws Exception;

}
