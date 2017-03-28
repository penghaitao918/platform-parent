package com.fanfandou.common.sequence;

import com.fanfandou.common.exception.DbException;
import com.fanfandou.common.exception.ServiceException;
import com.google.common.primitives.Longs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 数据库主键生成器.
 *
 * @author Arvin.
 */
@Component
public class TableSequenceGenerator {
    //
    private static final Map<String, SequenceValue> sequenceMap = new HashMap<String, SequenceValue>();

    //暂定10，上线再调整
    private static final int STEP = 10;

    private static final int TRY_TIMES = 3;

    @Autowired
    private RedisTemplate<String, Long> redisTemplate;


    private static final String SEQUENCE_NAME_PREFIX = "TABLE_SEQUENCE";

    /**
     * 获取数据库主键.
     *
     * @param sequenceName table name
     * @return id
     * @throws ServiceException ServiceException
     */
    public long generate(String sequenceName) throws ServiceException {
        SequenceValue sequenceValue = null;
        long returnValue = 0;
        String fullSequenceName = SEQUENCE_NAME_PREFIX + "_" + sequenceName.toUpperCase();

        // get the sequence from map or initialize it.
        synchronized (sequenceMap) {
            sequenceValue = sequenceMap.get(fullSequenceName);

            if (sequenceValue == null) {
                sequenceValue = new SequenceValue(fullSequenceName);

                sequenceMap.put(sequenceValue.getSequenceName(), sequenceValue);
            }
        }

        // get the next value from the cache.
        synchronized (sequenceValue) {
            // if the cache has next value, just get one from the cache.
            if (sequenceValue.hasNext()) {
                returnValue = sequenceValue.getNextValue();
            } else {
                // if the cache hasn't next value, get more from db.
                long dbValue = fetchNextValue(fullSequenceName, STEP);

                // set the db value to cache.
                sequenceValue.setCurValue(dbValue);
                sequenceValue.setMaxValue(dbValue + STEP - 1);

                returnValue = sequenceValue.getNextValue();
            }
        }

        return returnValue;
    }

    private long fetchNextValue(String sequenceName, final int step) throws ServiceException {

        long curValue = -1L;
        int tryTimes = 0;

        try {
            while (curValue < 0 && (tryTimes < TRY_TIMES)) {
                final byte[] redisKey = sequenceName.getBytes();

                curValue = redisTemplate.execute(new RedisCallback<Long>() {
                    @Override
                    public Long doInRedis(RedisConnection connection) throws DataAccessException {
                        long value = 1;
                        connection.watch(redisKey);
                        if (connection.exists(redisKey)) {
                            value = Longs.fromByteArray(connection.get(redisKey));
                        }
                        connection.multi();
                        connection.set(redisKey, Longs.toByteArray(value + step));
                        List<Object> result = connection.exec();
                        if (result == null) {
                            return -1L;
                        }
                        return value;
                    }
                });
                tryTimes++;
            }

        } catch (Exception e) {
            throw new DbException(DbException.DB_GENERIC, e);
        }

        if (curValue < 0) {
            throw new DbException(DbException.TABLE_SEQUENCE_ERROR, "生成数据库表主键失败：sequenceName=" + sequenceName);
        }
        return curValue;

    }
}
