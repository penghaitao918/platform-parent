package com.fanfandou.admin.api.system.service;

import com.fanfandou.admin.api.system.entity.DataDictionary;
import com.fanfandou.common.entity.resource.DicItem;
import com.fanfandou.common.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author zhongliang.
 * Desciption: 资源列表Service.
 */
@Service
public interface DataDictionaryService {

    void insertUniform(DataDictionary dataDictionary) throws ServiceException;

    void uniform(String uniform) throws ServiceException;

    void updateUniformCache() throws ServiceException;

    /**
     * 根据key查value.
     *
     * @param uniform 缓存中的key.
     */
    DataDictionary getValueByUniform(String uniform, int gameId, int siteId) throws ServiceException;

    void updateUniform(DataDictionary dataDictionary) throws ServiceException;


    /**
     * 批量删除.
     *
     * @param uniforms
     */
    void delResList(String uniforms, int gameId, int siteId) throws ServiceException;


    /**
     * 获取所有下拉框key值.
     *
     * @return 下拉框key值.
     */
    List<DataDictionary> getUniform(int gameId, int siteId) throws ServiceException;

    /**
     * 通过字典常量直接获取字典条目.
     *
     * @param keyConstant  字典类型.
     * @param itemConstant 字典条目类型.
     */
    DicItem getDicItemByConstant(String keyConstant, String itemConstant) throws ServiceException;
}
