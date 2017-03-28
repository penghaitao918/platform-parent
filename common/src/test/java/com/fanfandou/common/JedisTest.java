package com.fanfandou.common;

import com.google.common.primitives.Longs;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * Description: jedis用法测试.
 * <p/>
 * Date: 2016-03-25 11:35.
 * author: Arvin.
 */

public class JedisTest {


    /**
     * 测试结果：当watch key的时候，一个未执行完毕，另一个又开始执行，那么result就会为null
     * 可以使用watch解决分布式存取的问题。
     * @throws InterruptedException
     */
    @Test
    public void testJedis() throws InterruptedException {
        Jedis jedis = new Jedis("192.168.1.211",6379);
        byte[] key = "test002".getBytes();
        long value = 1;
        jedis.watch(key);

        if(jedis.exists(key)){
            value = Longs.fromByteArray(jedis.get(key));
            System.out.println(value);
        }
        Thread.sleep(10000);
        Transaction tx = jedis.multi();
        tx.set(key, Longs.toByteArray(value+10));
        List<Object> result = tx.exec();
        if(result==null){
            System.out.println("failure...");
        }
        jedis.unwatch();
        System.out.println(Longs.fromByteArray(jedis.get(key)));
    }
}
