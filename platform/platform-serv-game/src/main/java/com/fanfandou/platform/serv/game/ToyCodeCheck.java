package com.fanfandou.platform.serv.game;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.game.exception.GameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wudi.
 * Descreption:玩具激活码校验.
 * Date:2016/6/7
 */
public class ToyCodeCheck extends BaseLogger {
    protected static Logger logger = LoggerFactory.getLogger(ToyCodeCheck.class);
    static int key[] = {
            237, 139, 69, 77,
            213, 217, 95, 173,
            222, 55, 129, 245,
            32, 97, 61, 101,
            37, 39, 169, 177,
            23, 27, 195, 73,
            122, 155, 229, 145,
            232, 197, 161, 201,
    };

    /**
     * 玩具激活码校验方法.
     * @param dataToCheck 要校验的激活码.
     * @param toyType 玩具类型
     * @param howManyToysOfThisTypeHaveBeenProduced 目前生产了多少.
     * @return 是否为正规激活码.
     */
    public static boolean check(long dataToCheck, int toyType, int howManyToysOfThisTypeHaveBeenProduced) throws ServiceException {
        int id = (int) dataToCheck;

        int byte4 = (id << 24 >> 24) & 0xFF;
        int byte3 = (id << 16 >> 24) & 0xFF;
        int byte2 = (id << 8 >> 24) & 0xFF;
        int byte1 = (id >> 24) & 0xFF;

        int key_idx = byte2 % 28;

        byte1 ^= key[key_idx];
        byte3 ^= key[key_idx + 1];
        byte4 ^= key[key_idx + 2];

        id = byte1 | byte2 << 8 | byte3 << 16 | byte4 << 24;
        long decData = id & 0xFFFFFFFFL;
        if (decData % 1024 != toyType % 1000) {
            //System.out.println("" + decData%1024 + " should be " + toyType%1000);
            logger.info("玩具类型错误");
            throw new GameException(GameException.GAME_TOY_TYPE_ERROR,"玩具类型错误");
        }

        if (decData / 1024 > howManyToysOfThisTypeHaveBeenProduced) {
            logger.info("玩具类型正确，超过批次号最大值");
            throw new GameException(GameException.GAME_TOY_GUID_ERROR,"玩具序列号错误");
        }

        return true;
    }

    public static void main(String[] args) throws ServiceException {
        //real value: count=9999,type=1011  684991913
        boolean b1 = check(713043881, 1015, 100000);

        System.out.println(String.format("b1:%s", Boolean.toString(b1)));

    }
}
