package com.fanfandou.admin.operation.dao;

import com.fanfandou.admin.operation.entity.MailOrder;
import com.fanfandou.admin.operation.entity.MailOrderFailure;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongliang on 2016/9/26.
 * Description 邮件发送错误记录dao
 */
@Repository(value = "mailOrderFailureMapper")
public interface MailOrderFailureMapper {

    /**
     * 查询.
     */
    List<MailOrderFailure> selectAll();

    /**
     * 增.
     */
    void insert(MailOrderFailure mailOrderFailure);

    /**
     * 删.
     */
    void delete(int id);

    /**
     * 改.
     */
    void update(MailOrderFailure mailOrderFailure);

    /**
     * selectById.
     */
    MailOrderFailure selectById(int id);

    /**
     * 分页.
     */
    List<MailOrderFailure> pageList(Map paramMap);

    /**
     * 分页数量.
     */
    int totalCount(Map map);
}
