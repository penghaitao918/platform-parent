package com.fanfandou.admin.system.controller;


import com.fanfandou.admin.system.entity.DiyForm;
import com.fanfandou.admin.system.service.DiyFormService;
import com.fanfandou.common.constant.PublicCachedKeyConstant;
import com.fanfandou.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * author zhongliang.
 * Description:自定义表单.
 */
@RestController
@RequestMapping(value = "/CustomForm")
public class DiyFormController {

    @Autowired
    private DiyFormService diyFormService;

    /**
     * 跳转到自定义表单功能页
     *
     * @return 功能页面
     */
    @RequestMapping(value = "/CustomFormInit")
    public ModelAndView toListCustom() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/system/CustomForm");
        return mav;
    }

    /**
     * 跳转到自定义表单编辑页
     *
     * @return 功能页面
     */
    @RequestMapping(value = "/CustomFormEditInit")
    public ModelAndView toEditCustom() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/system/CustomFormEdit");
        return mav;
    }

    /**
     * 添加
     *
     * @param diyForm 自定义表单.
     * @throws Exception
     */
    @RequestMapping(value = "/insert")
    public String addCustom(DiyForm diyForm) throws Exception {
        String key = PublicCachedKeyConstant.FORMOBJECT_LIST + "." + diyForm.getGameId() + "." + diyForm.getItemType();
        diyForm.setFormCode(key);
        try {
            diyFormService.insertCustomForm(diyForm);
        } catch (ServiceException e) {
            e.printStackTrace();
            return "load.diyForm" + e.getId();
        }
        return null;


    }

    /**
     * 修改
     *
     * @param diyForm 自定义表单.
     * @throws Exception
     */
    @RequestMapping(value = "/update")
    public String updateCustom(DiyForm diyForm) throws Exception {
        String key = PublicCachedKeyConstant.FORMOBJECT_LIST + "." + diyForm.getGameId() + "." + diyForm.getItemType();
        diyForm.setFormCode(key);
        try {
            diyFormService.updateDiyForm(diyForm);
        } catch (ServiceException e) {
            e.printStackTrace();
            return "load.diyForm" + e.getId();
    }
        return null;

    }

    /**
     * 查询所有
     *
     * @return 自定义表单对象
     * @throws Exception
     */
    @RequestMapping(value = "/query")
    public List<DiyForm> query() throws Exception {
        return diyFormService.getCustomForm();
    }

    /**
     * 根据key查询
     *
     * @param key key值
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getByKey")
    public DiyForm getKey(String key) throws Exception {
        return diyFormService.getCustomFormByKey(key);
    }

    /**
     * 根据code集合删除
     *
     * @param customs code集合
     * @throws Exception
     */
    @RequestMapping(value = "/deleteKey")
    public void deleteKey(String customs) throws Exception {
        diyFormService.delCustomList(customs);
    }
}
