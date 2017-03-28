package com.fanfandou.admin.system.controller;


import com.fanfandou.admin.api.system.entity.DataDictionary;
import com.fanfandou.admin.api.system.service.DataDictionaryService;
import com.fanfandou.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * author zhongliang.
 * Description:数据字典操作.
 */
@RestController
@RequestMapping(value = "/unify/uniform")
public class DataDictionaryController {
    @Autowired
    private DataDictionaryService dataDictionaryService;

    /**
     * 跳转到分类页面
     *
     * @return 分类页面
     */
    @RequestMapping(value = "/uniformInit")
    public ModelAndView toListMenu() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("system/UniformList");
        return mav;
    }

    /**
     * 数据字典添加
     *
     * @param dataDictionary 数据字典集合
     * @throws Exception
     */
    @RequestMapping(value = "/insert")
    public String insert(DataDictionary dataDictionary) throws Exception {
        try {
            dataDictionaryService.insertUniform(dataDictionary);
        } catch (ServiceException e) {
            e.printStackTrace();
            return "load.uni" + e.getId();
        }
        return null;

    }

    /**
     * 数据字典修改
     *
     * @param dataDictionary 数据字典集合
     * @throws Exception
     */
    @RequestMapping(value = "/update")
    public String update(DataDictionary dataDictionary) throws Exception {
        try {
            dataDictionaryService.updateUniform(dataDictionary);
        } catch (ServiceException e) {
            e.printStackTrace();
            return "load.uni" + e.getId();
        }
        return null;
    }


    /**
     * 查询所有
     *
     * @return 数据集合
     * @throws Exception
     */
    @RequestMapping(value = "/query")
    public List<DataDictionary> query(int gameId, int siteId) throws Exception {
        return dataDictionaryService.getUniform(gameId, siteId);
    }

    /**
     * 根据key查询
     *
     * @param key key值
     * @return 数据集合
     * @throws Exception
     */
    @RequestMapping(value = "/getKey")
    public DataDictionary getKey(String key, int gameId, int siteId) throws Exception {
        return dataDictionaryService.getValueByUniform(key, gameId, siteId);
    }

    /**
     * 根据code删除
     *
     * @param key code
     * @throws Exception
     */
    @RequestMapping(value = "/deleteKey")
    public void deleteKey(String key, int gameId, int siteId) throws Exception {
        /*String[] uniform = uniforms.split(",");
        List<String> uniformsList = new ArrayList<String>();
        for (int i = 0; i < uniform.length; i++) {
            uniformsList.add(uniform[i]);
        }*/
        dataDictionaryService.delResList(key, gameId, siteId);
    }

    /**
     * 数据字典缓存刷新
     *
     * @throws Exception
     */
    @RequestMapping(value = "/refreshCache")
    public void refreshCache() throws Exception {
        this.dataDictionaryService.updateUniformCache();
    }

}
