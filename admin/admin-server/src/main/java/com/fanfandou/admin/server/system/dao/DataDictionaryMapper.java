package com.fanfandou.admin.server.system.dao;

import com.fanfandou.admin.api.system.entity.DataDictionary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author zhongliang.
 * Description:数据字典keymapper.
 */

@Repository
public interface DataDictionaryMapper {

    /**
     * 查询所有.
     */
    List<DataDictionary> getAll();

    /**
     * 根据资源查询
     *
     * @return
     */
    List<DataDictionary> getByRes(DataDictionary dataDictionary);

    /**
     * selById.
     */
    DataDictionary selById(DataDictionary dataDictionary);

    /**
     * 改.
     */
    void update(DataDictionary dataDictionary);

    /**
     * 增.
     */
    void insert(DataDictionary dataDictionary);

    /**
     * deleteById.
     */
    void deleteById(String dictionaryName);


}
