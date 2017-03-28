package com.fanfandou.admin.operation.dao;

import com.fanfandou.admin.operation.entity.Approve;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzhenwei on 2016/6/30.
 * Description 游戏物品类型dao
 */
@Repository(value = "approveMapper")
public interface ApproveMapper {

    /**
     * 查询.
     */
    List<Approve> selectAll();

    /**
     * 订单分页.
     */
    List<Approve> orderPageList(Map paramMap);

    /**
     * 审核分页.
     */
    List<Approve> approvePageList(Map paramMap);

    /**
     * 分页总数.
     */
    int orderCount(Map map);

    /**
     * 审批总数.
     */
    int approveCount(Map map);

    /**
     * 增.
     */
    void insert(Approve approve);

    /**
     * 删.
     */
    void delete(int id);

    /**
     * 改.
     */
    void update(Approve approve);

    /**
     * select by id.
     */
    Approve selectById(int id);

    /**
     * 根据订单id删除.
     */
    void deleteByOrderId(int mailOrderId);

    /**
     * 根据订单id修改.
     */
    void updateByOrderId(Approve approve);

    /**
     * selectByOrderId.
     */
    Approve selectByOrderId(int id);

    /**
     * 根据邮件状态，审批状态查询订单数
     * @param paramMap
     * @return
     */
    int selectCountByMid(Map paramMap);
}
