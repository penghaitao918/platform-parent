package com.fanfandou.admin.system.service;

import com.fanfandou.admin.operation.entity.CustomForm;
import com.fanfandou.admin.operation.entity.FormObject;
import com.fanfandou.admin.operation.entity.FormView;
import com.fanfandou.admin.system.entity.DiyForm;
import com.fanfandou.admin.system.entity.User;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;

import java.util.List;

/**
 * Created by zhongliang on 2016/3/15.
 * Description 自定义表单service接口
 */
public interface DiyFormService {

    void insertCustomForm(DiyForm diyForm) throws Exception;

    void updateDiyForm(DiyForm diyForm) throws Exception;

    /**
     * 获取所有下拉框key值.
     * @return 下拉框key值.
     */
    List<DiyForm> getCustomForm() throws Exception ;

    /**
     * 根据key获取custom对象
     * @param key
     * @return
     */
    DiyForm getCustomFormByKey(String key) throws Exception;

    /**
     * 批量删除.
     * @param key uniforms
     */
    void delCustomList(String key) throws Exception;
}
