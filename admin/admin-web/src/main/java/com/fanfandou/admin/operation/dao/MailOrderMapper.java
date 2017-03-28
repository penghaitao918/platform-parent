package com.fanfandou.admin.operation.dao;

import com.fanfandou.admin.operation.entity.MailOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangzhenwei on 2016/6/30.
 * Description 游戏物品类型dao
 */
@Repository(value = "mailOrderMapper")
public interface MailOrderMapper {

    /**
     * 查询.
     */
    List<MailOrder> selectAll();

    /**
     * 增.
     */
    void insert(MailOrder mailOrder);

    /**
     * 删.
     */
    void delete(int id);

    /**
     * 改.
     */
    void update(MailOrder mailOrder);

    /**
     * selectById.
     */
    MailOrder selectById(int id);


}
