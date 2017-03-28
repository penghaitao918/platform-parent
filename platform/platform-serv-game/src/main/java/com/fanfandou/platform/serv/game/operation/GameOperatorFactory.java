package com.fanfandou.platform.serv.game.operation;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.platform.api.game.exception.GameException;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Description: 所有游戏、所有区服的operator都放此工厂内，供外部接口调用.
 * <p/>
 * Date: 2016-05-06 14:26.
 * author: Arvin.
 */
@Service("gameOperatorFactory")
public class GameOperatorFactory extends BaseLogger {
    private static final String KEY_SEPARATOR = ".";
    private static final String OPERATOR_KEY_PREFIX = "operator.key";
    private static final String OPERATOR_CLASS_KEY_PREFIX = "operator.class.game";

    private static ConcurrentMap<String, Class<? extends GameOperator>> gameOperatorClass = new ConcurrentHashMap<String, Class<? extends GameOperator>>();
    private static ConcurrentMap<String, GameOperator> gameOperatorAll = new ConcurrentHashMap<String, GameOperator>();

    public GameOperatorFactory() {
        //TODO 之后做到配置中
        gameOperatorClass.put("operator.class.game.1", GameOperatorTol.class);
        gameOperatorClass.put("operator.class.game.22", GameOperatorJokes.class);
    }

    /**
     * 取得game operator.
     * @param gameCode gameCode
     * @param areaId 区服id
     * @return GameOperator
     * @throws GameException 异常
     */
    public GameOperator getOperator(GameCode gameCode, int areaId) throws GameException {
        if (gameCode == null || areaId < 1) {
            throw new GameException(GameException.GAME_OPERATOR_INIT_ILLEGAL_ARGUMENT);
        }
        String operatorKey = OPERATOR_KEY_PREFIX + KEY_SEPARATOR + gameCode.getId() + KEY_SEPARATOR + areaId;
        GameOperator operator = gameOperatorAll.get(operatorKey);

        //TODO 初始化的时候可以使用乐观锁，锁掉operatorKey
        if (operator == null) {
            String classKey = OPERATOR_CLASS_KEY_PREFIX + KEY_SEPARATOR + gameCode.getId();
            if (!gameOperatorClass.containsKey(classKey)) {
                throw new GameException(GameException.GAME_OPERATOR_INIT_CONFIG_ERROR);
            }
            try {
                logger.info("GameOperatorFactory.getOperator -> game operator 初始化：gameId={},areaId={}",gameCode.getId(),areaId);
                operator = gameOperatorClass.get(classKey).newInstance();
            } catch (Exception e) {
                logger.error("GameOperatorFactory.getOperator -> game operator 初始化失败！", e);
                throw new GameException(GameException.GAME_OPERATOR_INSTANTIATION_FAILED);
            }
            //operator init
            operator.init(gameCode,areaId);
            //放入本地缓存
            gameOperatorAll.put(operatorKey, operator);
        }

        return operator;
    }

}
