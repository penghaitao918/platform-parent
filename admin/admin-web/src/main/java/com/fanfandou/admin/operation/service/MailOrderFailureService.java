package com.fanfandou.admin.operation.service;

import com.fanfandou.admin.operation.entity.MailOrder;
import com.fanfandou.admin.operation.entity.MailOrderFailure;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.billing.entity.GoodsItem;

import java.util.List;

/**
 * Created by zhongliang on 2016/9/26.
 * Description 物品发送问题记录service接口
 */
public interface MailOrderFailureService {

    /**
     * 添加.
     */
    void addMailOrderFailure(MailOrderFailure mailOrderFailure);

    /**
     * 删除 by id.
     */
    void delMailOrderFailure(List<Integer> idList) throws ServiceException;

    /**
     * 更新.
     */
    void updMailOrderFailure(MailOrderFailure mailOrderFailure);

    /**
     * 查询所有.
     */
    List<MailOrderFailure> selectAll();

    /**
     * 通过id查找.
     */
    MailOrderFailure selMailOrderFailureById(int id);

    /**
     * 从数据库模糊查询出本页数据并排序.
     */
    PageResult<MailOrderFailure> getPageList(String name, Page page,String from,String to) throws Exception;
}
