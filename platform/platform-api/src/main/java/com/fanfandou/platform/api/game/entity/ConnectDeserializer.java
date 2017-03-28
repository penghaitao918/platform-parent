package com.fanfandou.platform.api.game.entity;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Type;

/**
 * Created by wudi.
 * Descreption:连接方式反序列化.
 * Date:2016/8/6
 */
public class ConnectDeserializer implements ObjectDeserializer {

    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
        Integer intValue = defaultJSONParser.parseObject(int.class);
        if (intValue == 0) {
            return (T) ConnectType.SOCKET;
        } else if (intValue == 1) {
            return (T) ConnectType.DATABASE;
        }
        throw new IllegalStateException();
    }

    @Override
    public int getFastMatchToken() {
        return JSONToken.LITERAL_INT;
    }
}
