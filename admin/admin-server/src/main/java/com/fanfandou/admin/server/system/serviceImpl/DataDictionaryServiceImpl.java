package com.fanfandou.admin.server.system.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.fanfandou.admin.api.exception.AdminException;
import com.fanfandou.admin.server.system.dao.DataDictionaryMapper;
import com.fanfandou.admin.api.system.entity.DataDictionary;
import com.fanfandou.admin.api.system.service.DataDictionaryService;
import com.fanfandou.common.constant.DicKeyConstant;
import com.fanfandou.common.constant.PublicCachedKeyConstant;
import com.fanfandou.common.entity.resource.DicItem;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.service.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by zhongliang on 2016/3/15.
 * Description service实现类
 */
@Service("dataDictionaryService")
public class DataDictionaryServiceImpl implements DataDictionaryService {
    @Autowired(required = true)
    private CacheService cacheService;
    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;


    /**
     * 更新数据字典缓存
     *
     * @throws Exception
     */
    public void updateUniformCache() throws ServiceException {
        List<DataDictionary> dataDictionaryList = dataDictionaryMapper.getAll();
        for (DataDictionary dictionary : dataDictionaryList) {
            String charData = "";
            if (dictionary.getGameId() != -1) {
                charData = charData + "." + dictionary.getGameId();
            }
            if (dictionary.getSiteId() != -1) {
                charData = charData + "." + dictionary.getSiteId();
            }
            cacheService.hPut(PublicCachedKeyConstant.UNIFORM_LIST + charData, dictionary.getDictionaryName(), dictionary);
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = ServiceException.class)
    @Override
    public void insertUniform(DataDictionary dataDictionary) throws ServiceException {
        DataDictionary data = dataDictionaryMapper.selById(dataDictionary);
        String charData = "";
        if (dataDictionary.getGameId() != -1) {
            charData = charData + "." + dataDictionary.getGameId();
        }
        if (dataDictionary.getSiteId() != -1) {
            charData = charData + "." + dataDictionary.getSiteId();
        }
        DataDictionary dataDictionarys = cacheService.hGet(PublicCachedKeyConstant.UNIFORM_LIST + charData, dataDictionary.getDictionaryName(), DataDictionary.class);

        if (data != null || dataDictionarys != null) {
            updateUniformCache();
            throw new ServiceException(AdminException.ADMIN_WRONG_DATADICKEY);
        } else {
            dataDictionary.setCreateTime(new Date());
            //更新数据字典
            cacheService.hPut(PublicCachedKeyConstant.UNIFORM_LIST + charData, dataDictionary.getDictionaryName(), dataDictionary);
        }


    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public void updateUniform(DataDictionary dataDictionary) throws ServiceException {
        String charData = "";
        if (dataDictionary.getGameId() != -1) {
            charData = charData + "." + dataDictionary.getGameId();
        }
        if (dataDictionary.getSiteId() != -1) {
            charData = charData + "." + dataDictionary.getSiteId();
        }
        //更新数据库
        dataDictionaryMapper.update(dataDictionary);
        //更新数据字典
        cacheService.hPut(PublicCachedKeyConstant.UNIFORM_LIST + charData, dataDictionary.getDictionaryName(), dataDictionary);
        updateUniformCache();
    }

    @Override
    public void uniform(String id) {
        cacheService.del(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public DataDictionary getValueByUniform(String uniform, int gameId, int siteId) throws ServiceException {
        DataDictionary dictionary = new DataDictionary();
        dictionary.setDictionaryName(uniform);
        dictionary.setGameId(gameId);
        dictionary.setSiteId(siteId);

        String charData = "";
        if (gameId != -1) {
            charData = charData + "." + gameId;
        }
        if (siteId != -1) {
            charData = charData + "." + siteId;
        }
        DataDictionary dataDictionary = cacheService.hGet(PublicCachedKeyConstant.UNIFORM_LIST + charData, uniform, DataDictionary.class);
        if (dataDictionary == null || dataDictionary.getItemJson() == null || dataDictionary.getItemJson().equals("") || dataDictionary.getItemJson().equals("[]")) {
            DataDictionary general = cacheService.hGet(PublicCachedKeyConstant.UNIFORM_LIST, uniform, DataDictionary.class);
            if (general == null || general.getItemJson() == null || general.getItemJson().equals("")) {
                general = dataDictionaryMapper.selById(dictionary);
                updateUniformCache();
                return general;
            } else {
                return general;
            }
        } else {
            return dataDictionary;
        }
    }


    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public void delResList(String uniforms, int gameId, int siteId) throws ServiceException {
        String charData = "";
        if (gameId != -1) {
            charData = charData + "." + gameId;
        }
        if (siteId != -1) {
            charData = charData + "." + siteId;
        }
        cacheService.hDel(PublicCachedKeyConstant.UNIFORM_LIST + charData, uniforms);
        //更新数据库
        dataDictionaryMapper.deleteById(uniforms);
        //更新缓存
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DataDictionary> getUniform(int gameId, int siteId) throws ServiceException {

        String charData = "";
        if (gameId != -1) {
            charData = charData + "." + gameId;
        }
        if (siteId != -1) {
            charData = charData + "." + siteId;
        }
        List<DataDictionary> uniformList = cacheService.hGetValues(PublicCachedKeyConstant.UNIFORM_LIST + charData,
                DataDictionary.class);

        //如果缓存中没有数据，查询数据库
        if (uniformList == null || uniformList.size() == 0) {
            updateUniformCache();
            DataDictionary dataDictionary = new DataDictionary();
            dataDictionary.setGameId(gameId);
            dataDictionary.setSiteId(siteId);
            return dataDictionaryMapper.getByRes(dataDictionary);
        } else {
            return uniformList;
        }

    }

    @Override
    public DicItem getDicItemByConstant(String keyConstant, String itemConstant) throws ServiceException {
        DataDictionary dataDictionary = getValueByUniform(keyConstant, -1, -1);
        List<DicItem> dicItems = JSON.parseArray(dataDictionary.getItemJson(), DicItem.class);
        DicItem dkItem = null;
        for (DicItem dicItem : dicItems) {
            if (dicItem.getKey().equals(itemConstant)) {
                dkItem = dicItem;
            }
        }
        return dkItem;
    }
}
