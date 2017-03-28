package com.fanfandou.common.service.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description: 缓存接口.
 * <p/>
 * Date: 2016-10-13 15:53.
 * author: Arvin.
 */
public interface CacheService {

    /**
     * 新增或更新缓存(非list).
     *
     * @param key   k
     * @param value v
     */
    <V> void put(String key, V value);

    /**
     * 新增或更新缓存.
     *
     * @param key        k
     * @param value      v
     * @param expireTime 有效时间
     */
    <V> void put(String key, V value, long expireTime);

    /**
     * 新增或更新list缓存
     *
     * @param key   k
     * @param value v
     * @param <V>   类型
     */
    <V> void putList(String key, List<V> value);

    /**
     * 删除缓存.
     *
     * @param key k
     */
    void del(String key);

    /**
     * 查询缓存.
     *
     * @param key k
     * @return obj
     */

    /**
     * 查询缓存(非list).
     *
     * @param key   k
     * @param clazz 返回值的class
     * @param <V>   返回值的类型
     * @return 结果
     */
    <V> V get(String key, Class<V> clazz);

    /**
     * 查询list缓存
     *
     * @param key   k
     * @param clazz 返回值的class
     * @param <V>   返回值的类型
     * @return list
     */
    <V> List<V> getList(String key, Class<V> clazz);

    /**
     * 新增或更新hash缓存（map，hKey就是map中的key）.
     *
     * @param key   k
     * @param hKey  hash key
     * @param value v
     */
    <V> void hPut(String key, String hKey, V value);

    /**
     * 删除hash缓存.
     *
     * @param key  k
     * @param hKey hash key
     */
    void hDel(String key, String hKey);

    /**
     * 查询hash缓存.
     *
     * @param key  k
     * @param hKey hash key
     * @return v
     */
    <V> V hGet(String key, String hKey, Class<V> clazz);

    /**
     * 查询同一个key下所有hash缓存
     *
     * @param key   k
     * @param clazz 返回列表中的class
     * @param <V>   返回列表中的类型
     * @return Map
     */
    <V> Map<String, V> hGetAll(String key, Class<V> clazz);

    /**
     * 查询同一个key下所有hash缓存的value
     * @param key k
     * @param clazz 返回列表中的class
     * @param <V> 返回列表中的类型
     * @return list
     */
    <V> List<V> hGetValues(String key, Class<V> clazz);

    /**
     * 查看是否存在key.
     *
     * @param key k
     * @return boolean
     */
    boolean hasKey(String key);


}
