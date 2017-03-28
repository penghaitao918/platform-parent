package com.fanfandou.platform.api.game.entity;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Type;

/**
 * Created by wudi.
 * Descreption:区服类型反序列化.
 * Date:2016/8/6
 */
public class AreaEnterDeserializer implements ObjectDeserializer {

    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
        Integer intValue = defaultJSONParser.parseObject(int.class);
        if (intValue == 0) {
            return (T) AreaEnterType.CLIENTENTER;
        } else if (intValue == 1) {
            return (T) AreaEnterType.SERVERENTER;
        }
        throw new IllegalStateException();
    }

    @Override
    public int getFastMatchToken() {
        return JSONToken.LITERAL_INT;
    }
}
