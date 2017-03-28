package com.fanfandou.common.service.cache;

import com.fanfandou.common.util.ProtostuffSerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: redis缓存实现.
 * <p/>
 * Date: 2016-10-15 14:21.
 * author: Arvin.
 */
@Service("cacheService")
public class RedisCacheServiceImpl implements CacheService {
    @Autowired
    private RedisTemplate<String, byte[]> redisTemplate;

    @Override
    public <V> void put(String key, V value) {

        doPut(key, serialize(value), null);
    }

    @Override
    public <V> void put(String key, V value, long expireTime) {
        doPut(key, serialize(value), expireTime);
    }

    @Override
    public <V> void putList(String key, List<V> value) {
        doPut(key, ProtostuffSerializeUtil.serializeList(value), null);
    }

    private void doPut(final String key, final byte[] value, final Long expireSec) {
        final byte[] keyBytes = serializeString(key);
        redisTemplate.execute(new RedisCallback<Object>() {
            public String doInRedis(final RedisConnection connection)
                    throws DataAccessException {
                connection.set(keyBytes, value);
                if (expireSec != null) {
                    connection.expire(keyBytes, expireSec);
                }
                return null;
            }
        });

    }

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public <V> V get(String key, Class<V> clazz) {
        return deserialize(doGet(key), clazz);
    }

    @Override
    public <V> List<V> getList(String key, Class<V> clazz) {
        return ProtostuffSerializeUtil.deserializeList(doGet(key), clazz);
    }

    private byte[] doGet(final String key) {
        return redisTemplate.execute(new RedisCallback<byte[]>() {
            public byte[] doInRedis(RedisConnection connection)
                    throws DataAccessException {
                return connection.get(serializeString(key));
            }
        });

    }


    @Override
    public <V> void hPut(String key, String hKey, V value) {
        final byte[] keyBytes = serializeString(key);
        final byte[] hKeyBytes = serializeString(hKey);
        final byte[] valBytes = serialize(value);
        redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                return connection.hSet(keyBytes, hKeyBytes, valBytes);
            }
        });
    }

    @Override
    public void hDel(String key, String hKey) {
        redisTemplate.opsForHash().delete(key, hKey);
    }

    @Override
    public <V> V hGet(String key, String hKey, final Class<V> clazz) {
        final byte[] keyBytes = serializeString(key);
        final byte[] hkeyBytes = serializeString(hKey);
        return redisTemplate.execute(new RedisCallback<V>() {
            public V doInRedis(RedisConnection connection)
                    throws DataAccessException {
                byte[] data = connection.hGet(keyBytes, hkeyBytes);
                return deserialize(data, clazz);

            }
        });
    }

    @Override
    public <V> Map<String, V> hGetAll(String key, Class<V> clazz) {
        final byte[] keyBytes = serializeString(key);

        Map<byte[], byte[]> entries = redisTemplate.execute(new RedisCallback<Map<byte[], byte[]>>() {

            public Map<byte[], byte[]> doInRedis(RedisConnection connection) {
                return connection.hGetAll(keyBytes);
            }
        }, true);
        return deserializeMap(entries, clazz);
    }

    @Override
    public <V> List<V> hGetValues(String key, final Class<V> clazz) {
        final byte[] keyBytes = serializeString(key);
        return redisTemplate.execute(new RedisCallback<List<V>>() {

            public List<V> doInRedis(RedisConnection connection) {
                List<byte[]> valBytes = connection.hVals(keyBytes);
                List<V> val = new ArrayList<>();
                for (byte[] valByte : valBytes) {
                    val.add(ProtostuffSerializeUtil.deserialize(valByte, clazz));
                }
                return val;
            }
        }, true);
    }

    private <V> Map<String, V> deserializeMap(Map<byte[], byte[]> entries, Class<V> clazz) {
        if (entries == null) {
            return null;
        }

        Map<String, V> map = new LinkedHashMap<>(entries.size());

        for (Map.Entry<byte[], byte[]> entry : entries.entrySet()) {
            map.put(entry.getKey() == null ? null : new String(entry.getKey()), deserialize(entry.getValue(), clazz));
        }
        return map;
    }

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    private byte[] serializeString(String value) {
        return value == null ? null : value.getBytes(Charset.forName("UTF8"));
    }

    @SuppressWarnings("unchecked")
    private <V> byte[] serialize(V value) {
        if (value instanceof Map) {
            return ProtostuffSerializeUtil.serializeMap((Map<Object, Object>) value);
        } else {
            return ProtostuffSerializeUtil.serialize(value);
        }
    }

    @SuppressWarnings("unchecked")
    private <V> V deserialize(byte[] data, Class<V> clazz) {
        if (clazz.getName().equals(Map.class.getName())) {
            return (V) ProtostuffSerializeUtil.deserializeMap(data);
        } else {
            return ProtostuffSerializeUtil.deserialize(data, clazz);
        }
    }

}
