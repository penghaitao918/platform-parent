package com.fanfandou.platform.api.game.exception;

import com.fanfandou.common.exception.ServiceException;

/**
 * Description: 游戏相关异常.
 * <p/>
 * Date: 2016-05-09 19:08.
 * author: Arvin.
 */
public class GameException extends ServiceException {

    /* game operator 1-20 */
    //game operator 初始化参数错误
    public static final int GAME_OPERATOR_INIT_ILLEGAL_ARGUMENT = BASE_GAME + 1;

    //game operator 配置错误
    public static final int GAME_OPERATOR_INIT_CONFIG_ERROR = BASE_GAME + 2;

    //game operator 类实例化失败
    public static final int GAME_OPERATOR_INSTANTIATION_FAILED = BASE_GAME + 3;

    //game operator 未连接
    public static final int GAME_OPERATOR_NOT_CONNECTED = BASE_GAME + 4;

    /* toy 21-40 */
    //绑定玩具参数残缺
    public static final int GAME_LACK_BIND_TOY_PARAMS = BASE_GAME + 21;

    //code跟玩家信息不一致
    public static final int GAME_TOY_CODE_NOT_AGREED = BASE_GAME + 22;

    //code不存在
    public static final int GAME_TOY_CODE_NOT_EXIST = BASE_GAME + 23;

    //code创建重复
    public static final int GAME_TOY_CODE_ALREADY_EXIST = BASE_GAME + 24;

    //code无效
    public static final int GAME_TOY_CODE_INVALIED = BASE_GAME + 25;

    //玩具已被其它账号绑定
    public static final int GAME_TOY_CODE_BOUND = BASE_GAME + 26;

    //玩具已被本账号绑定
    public static final int GAME_TOY_CODE_BOUND_FOR_SELF = BASE_GAME + 27;

    //玩具已经被其他账号绑定
    public static final int GAME_TOY_CHECK_OTHER_BOUND = BASE_GAME + 28;

    //玩具类型不对应
    public static final int GAME_TOY_TYPE_ERROR = BASE_GAME + 29;

    //玩具批次号重复
    public static final int GAME_TOY_BATCH_CODE_REPEAT = BASE_GAME + 30;

    //玩具序列号非法比如序列号最大到10000，我传给你的是20000
    public static final int GAME_TOY_GUID_ERROR = BASE_GAME + 31;
    //
    public static final int GAME_TOYBATCH_ISNULL = BASE_GAME + 32;


    /* game area 41-60 */
    //区服信息缺失
    public static final int GAME_AREA_LACK_PARAMS = BASE_GAME + 41;

    //区服连接地址能为空
    public static final int GAME_AREA_ADDRESS_ISNULL = BASE_GAME + 42;

    //区服无法删除
    public static final int GAME_AREA_CANNOT_DEL = BASE_GAME + 43;

    //区服CODE不能重复.
    public static final int GAME_AREA_DULPLICATE = BASE_GAME + 44;

    public static final int GAME_AREA_NULL = BASE_GAME + 45;


    public static final int GAME_NOTICE_ERROR = BASE_GAME + 51;
    /* game operation 61-80 */
    //operation type 不存在
    public static final int GAME_OPERATION_TYPE_NOTE_EXIST = BASE_GAME + 61;
    //充值发货失败
    public static final int PAY_DILEVER_FAIL = BASE_GAME + 62;
    //邮件发送失败
    public static final int MAIL_DELEBER_FAIL = BASE_GAME + 63;
    //道具购买
    public static final int PAY_SHOP_GEM_FAIL = BASE_GAME + 64;

    //81 - 120 GameRole
    //角色名已存在
    public static final int GAMEROLE_EXIST = BASE_GAME + 81;


    protected GameException(ServiceException e) {
        super(e);
    }

    public GameException(ServiceException e, String detail) {
        super(e, detail);
    }

    public GameException(int id) {
        super(id);
    }

    public GameException(int id, Throwable cause) {
        super(id, cause);
    }

    public GameException(int id, String detail) {
        super(id, detail);
    }
}
