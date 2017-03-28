package com.fanfandou.common.util;


import com.fanfandou.common.base.BaseLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {

    private static final Logger logger = LoggerFactory.getLogger(SerializeUtil.class);

    /**
     * 序列化.
     * @param object obj
     * @return byte[]
     */
    public static byte[] serialize(Object object) {
        if (object == null) {
            return null;
        }

        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            logger.error("serialize error",e);
        }
        return null;
    }

    /**
     * 反序列化.
     * @param bytes byte[]
     * @return obj
     */
    public static Object unserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            logger.error("unserialize error",e);
        }
        return null;
    }
}
