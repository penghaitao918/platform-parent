package com.fanfandou.admin.operation.service;

import com.fanfandou.admin.operation.entity.Approve;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;

import java.util.List;

/**
 * Created by wangzhenwei on 2016/6/30.
 * Description 游戏物品类型service接口
 */
public interface ApproveService {

    /**
     * 添加.
     */
    void addApprove(Approve approve);

    /**
     * 删除 by id.
     */
    void delApprove(List<Integer> idList);

    /**
     * 删除 by orderId.
     */
    void delByOrderId(int orderId);

    /**
     * 更新.
     */
    void updApprove(Approve stu);

    /**
     * 更新ByOrderId.
     */
    void updByOrderId(Approve stu);

    /**
     * 查询所有.
     */
    List<Approve> selectAll();

    /**
     * 查询订单列表.
     *
     * @param mailTitleStr 邮件标题字符串
     * @param page         page对象
     * @param from         创建时间起始
     * @param to           创建时间结束
     * @param typeCode     1订单 2审核
     * @param gameId       游戏id
     * @return 订单列表
     */
    PageResult<Approve> getMailOrderPageList(String mailTitleStr, Page page, String from, String to, int typeCode,
                                             Integer sendType, Integer mailType,Integer sendStatus,
                                             Integer approvalStatus,Integer gameId) throws Exception;

    /**
     * 通过id查找.
     */
    Approve selApproveById(int id);

    /**
     * 通过OrderId查找.
     */
    Approve selByOrderId(int id);


    /**
     * 根据邮件状态，审批状态查询订单数
     * @param
     * @return
     */
    int selectCountByMid(Integer sendStatus,Integer approvalStatus);
}
