package com.fanfandou.common;

import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.service.cache.CacheService;
import com.fanfandou.common.util.ProtostuffSerializeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 测试缓存相关api.
 * <p/>
 * Date: 2016-10-11 14:14.
 * author: Arvin.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/spring-*.xml")
public class CacheTest {

    @Autowired
    private CacheService cacheService;

    @Test
    public void test() {
        String keyStr = "key str";
        String valStr = "test cache a";
        //cacheService.put(keyStr, valStr);
        //System.out.println("test cache String --------------->" + cacheService.get(keyStr, String.class));

        String keyObj = "key obj";
        GameCode gameCode = new GameCode();
        gameCode.setCode("wxhx");
        gameCode.setId(1);
        //cache.put(keyObj, gameCode);
        //System.out.println("test cache Object --------------->" + cacheService.get(keyObj, GameCode.class));

        /*String keyMap = "key map";
        Map<String, Object> valMap = new HashMap<>();
        valMap.put("String", "String Val");
        valMap.put("int", 1);
        valMap.put("boolean", true);
        valMap.put("obj", gameCode);
        cacheService.put(keyMap, valMap);
        System.out.println("test cache Map --------------->"+cacheService.get(keyMap, HashMap.class));*/

       /* String keyList = "key list";
        List<GameCode> valList = new ArrayList<>();
        GameCode gameCode1 = new GameCode();
        gameCode1.setId(2);
        gameCode1.setCode("wxhx2");
        gameCode1.setParent(gameCode);
        valList.add(gameCode);
        valList.add(gameCode1);
        cacheService.put(keyList, valList);
        System.out.println("test cache List --------------->" + cacheService.get(keyList, ArrayList.class));*/

        String keyHash = "key.hash";
        GameCode gameCode1 = new GameCode();
        gameCode1.setId(2);
        gameCode1.setCode("wxhx2");
        //gameCode1.setParent(gameCode);
/*        cacheService.hPut(keyHash, gameCode.getCode(), gameCode);
        cacheService.hPut(keyHash, gameCode1.getCode(), gameCode1);
        System.out.println("test cache hash ---------->hget:" + cacheService.hGet(keyHash, gameCode.getCode(),GameCode.class));
        System.out.println("test cache hash ---------->hgetall:" + cacheService.hGetAll(keyHash,GameCode.class));
        System.out.println("test cache hash ---------->hgetvals:" + cacheService.hGetValues(keyHash,GameCode.class));

        cacheService.hDel(keyHash, gameCode.getCode());
        System.out.println("test cache hash ---------->hget after del:" + cacheService.hGet(keyHash, gameCode.getCode(),GameCode.class));*/

        String keyMap = "key.map";
        Map<String, GameCode> gameCodeMap = new HashMap<>();
        gameCodeMap.put(gameCode.getCode(), gameCode);
        gameCodeMap.put(gameCode1.getCode(), gameCode1);
        cacheService.put(keyMap,gameCodeMap);
        System.out.println("test cache map -------------->"+cacheService.get("source.game.code.map", Map.class));

    }

    @Test
    public void testSerialize() {
        GameCode gameCode = new GameCode();
        gameCode.setCode("wxhx");
        gameCode.setId(1);
        Map<Object, Object> valMap = new HashMap<>();
        Map<Object, Object> valMap1 = new HashMap<>();
        valMap.put("String", "String Val");
        valMap.put("int", 1);
        valMap.put("boolean", true);
        valMap.put("obj", gameCode);
        byte[] seriData = ProtostuffSerializeUtil.serializeMap(valMap);
        System.out.println("testSerialize---------" + seriData);
        valMap1 = ProtostuffSerializeUtil.deserializeMap(seriData);
        System.out.println("testSerialize---------" + valMap1);
    }

}
