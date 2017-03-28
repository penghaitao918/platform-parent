package com.fanfandou.admin.system.dao;

import com.fanfandou.admin.system.entity.DiyForm;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author zhongliang.
 * Description:数据字典keymapper.
 */

@Repository
public interface DiyFormMapper {

    /**
     * 查询所有.
     */
    List<DiyForm> getAll();


    /**
     * selById.
     */
    DiyForm selById(String formCode);

    /**
     * 改.
     */
    void update(DiyForm diyForm);

    /**
     * 增.
     */
    void insert(DiyForm diyForm);

    /**
     * deleteById.
     */
    void deleteById(String formCode);



}
