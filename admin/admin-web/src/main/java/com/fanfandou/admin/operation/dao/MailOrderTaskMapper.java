package com.fanfandou.admin.operation.dao;

import com.fanfandou.admin.operation.entity.MailOrderTask;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangzhenwei on 2016/6/30.
 * Description 游戏物品类型dao
 */
@Repository(value = "mailOrderTaskMapper")
public interface MailOrderTaskMapper {

    /**
     * 查询.
     */
    List<MailOrderTask> selectAll();

    /**
     * 增.
     */
    void insert(MailOrderTask mailOrder);

    /**
     * 删.
     */
    void delete(int id);

    /**
     * 改.
     */
    void update(MailOrderTask mailOrder);

    /**
     * selectById.
     */
    MailOrderTask selectById(int id);

    /**
     * 查询没有发送的任务.
     */
    List<MailOrderTask> selectNotSend();

    /**
     * 查询错误.
     */
    String selFailedReason();
}
