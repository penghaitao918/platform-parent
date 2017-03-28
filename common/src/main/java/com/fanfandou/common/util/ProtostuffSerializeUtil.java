package com.fanfandou.common.util;

import com.fanfandou.common.entity.MapWrapper;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: protostuff 序列化工具.
 * <p/>
 * Date: 2016-04-28 16:25.
 * author: Arvin.
 */
public class ProtostuffSerializeUtil {
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

    private static <T> Schema<T> getSchema(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        Schema<T> schema = (Schema<T>) cachedSchema.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.getSchema(clazz);
            if (schema != null) {
                cachedSchema.put(clazz, schema);
            }
        }
        return schema;
    }

    /**
     * 序列化.
     *
     * @param obj 需要序列化的obj
     */
    public static <T> byte[] serialize(T obj) {
        if (obj == null) {
            logger.error("ProtostuffSerializeUtil.serialize --> 序列化对象为空！");
            return null;
        }
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(clazz);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化.
     *
     * @param data  需要反序列化的数据
     * @param clazz 结果class
     */
    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        if (data == null || data.length < 1) {
            logger.error("ProtostuffSerializeUtil.deserialize --> 反序列化对象，byte序列为空");
            return null;
        }
        try {
            T obj = clazz.newInstance();
            Schema<T> schema = getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(data, obj, schema);
            return obj;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 原生protobuf序列化.
     *
     * @param obj 需要序列化的obj
     */
    public static <T> byte[] serializeProtoBuf(T obj) {
        if (obj == null) {
            logger.error("ProtostuffSerializeUtil.serializeProtoBuf --> 序列化对象为空！");
            return null;
        }
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(clazz);
            return ProtobufIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            buffer.clear();
        }
    }

    /**
     * 原生protobuf反序列化.
     *
     * @param data  需要反序列化的数据
     * @param clazz 结果class
     */
    public static <T> T deserializeProtoBuf(byte[] data, Class<T> clazz) {
        if (data == null || data.length < 1) {
            logger.error("ProtostuffSerializeUtil.deserializeProtoBuf --> 反序列化对象，byte序列为空");
            return null;
        }
        try {
            T obj = clazz.newInstance();
            Schema<T> schema = getSchema(clazz);
            ProtobufIOUtil.mergeFrom(data, obj, schema);
            return obj;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 序列化map.
     *
     * @param objMap 需要序列化的map
     * @return byte[]
     */
    public static byte[] serializeMap(Map<Object, Object> objMap) {
        if (objMap == null || objMap.isEmpty()) {
            logger.error("ProtostuffSerializeUtil.serializeMap --> 序列化map为空");
            return null;
        }
        MapWrapper wrapper = new MapWrapper(objMap);
        return serialize(wrapper);
    }

    /**
     * 反序列化map.
     *
     * @param data 需要反序列化的数据
     * @return map
     */
    public static Map<Object, Object> deserializeMap(byte[] data) {
        if (data == null || data.length < 1) {
            logger.error("ProtostuffSerializeUtil.deserialize --> 反序列化map，byte序列为空");
            return null;
        }
        MapWrapper wrapper = deserialize(data, MapWrapper.class);
        if (wrapper != null) {
            return wrapper.getMap();
        }
        return null;
    }

    /**
     * 序列化列表.
     *
     * @param objList 需要序列化的列表
     * @return 返回结果
     */
    public static <T> byte[] serializeList(List<T> objList) {
        if (objList == null || objList.isEmpty()) {
            logger.error("ProtostuffSerializeUtil.serializeList --> 序列化列表为空");
            return null;
        }
        @SuppressWarnings("unchecked")
        Schema<T> schema = (Schema<T>) RuntimeSchema.getSchema(objList.get(0).getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(1024 * 1024);
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            ProtostuffIOUtil.writeListTo(bos, objList, schema, buffer);
            return bos.toByteArray();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            buffer.clear();
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 反序列化列表.
     *
     * @param data        需要反序列化的数据
     * @param targetClass Class
     * @return targetClass的列表
     */
    public static <T> List<T> deserializeList(byte[] data, Class<T> targetClass) {
        if (data == null || data.length == 0) {
            logger.error("ProtostuffSerializeUtil.deserializeList --> 序列化列表，byte序列为空");
            return null;
        }

        Schema<T> schema = RuntimeSchema.getSchema(targetClass);
        try {
            return ProtostuffIOUtil.parseListFrom(new ByteArrayInputStream(data), schema);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

}
