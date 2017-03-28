package com.fanfandou.admin.operation.service;

import com.fanfandou.admin.operation.entity.MailOrderTask;

import java.util.List;

/**
 * Created by wangzhenwei on 2016/6/30.
 * Description 游戏物品类型service接口
 */
public interface MailOrderTaskService {

    /**
     * 添加.
     */
    void addMailOrderTask(MailOrderTask mailOrderTask);

    /**
     * 删除 by id.
     */
    void delMailOrderTask(List<Integer> idList);

    /**
     * 更新.
     */
    void updMailOrderTask(MailOrderTask mailOrderTask);

    /**
     * 查询所有.
     */
    List<MailOrderTask> selectAll();

    /**
     * 通过id查找.
     */
    MailOrderTask selMailOrderTaskById(int id);

    /**
     * 查询未发送的邮件任务.
     */
    List<MailOrderTask> selectNotSend();

    /**
     * 查找错误详情.
     * @param mailOrderId 订单id
     * @return 错误信息
     */
    String selFailedReason(int mailOrderId);

}
