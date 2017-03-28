package com.fanfandou.platform.serv.game.entity.jokes.base;

import java.io.Serializable;

/**
 * Created by wudi.
 * Descreption:TODO..
 * Date:2017/3/13
 */
public class JokesParams implements Serializable {

    public final static int ACTION_SHUTDOWN_SERVER = 1;                //停服
    public final static int ACTION_MAIL = 2;                //邮件
    public final static int ACTION_ANNOUNCE_TO_ALL = 3;                //向所有玩家发公告
    public final static int ACTION_RECHARGE = 4;                //充值
    public final static int ACTION_PRIVATE_ANNOUNCE = 5;                //向个别玩家发公告
    public final static int ACTION_BAN = 7;                //封停账号
    public final static int ACTION_COMMAND = 8;                //GM指令
    public final static int ACTION_KICK_PLAYER = 6;                //在线踢人
    public final static int ACTION_MODIFY_PLAYER_PROPS = 9;                //修改信息
    public final static int ACTION_BROADCAST_SERVERID = 10;                //
    public final static int ACTION_INVITEE_INFO = 11;                //向个别玩家发公告
    public final static int ACTION_GET_ROLE_INFO = 12;

    public final static String ID = "id";
    public final static String OP = "op";
    public final static String TRANSID = "transId";
    public final static String INFOTYPE = "infoType";
    public final static String INTERVAL = "interval";
    public final static String BEGINTIME = "begintime";
    public final static String ENDTIME = "endtime";
    public final static String MAIL_TARGET = "mailtarget";
    public final static String APPENDIX = "appendix";
    public final static String ACTION = "action";         //action
    public final static String ROLEID = "roleid";         //玩家角色id
    public final static String CONTENT = "content";        //消息文本
    public final static String MONEY = "money";            //money
    public final static String GEM = "gem";            //宝石
    public final static String SIGN = "sign";           //验证码
    public final static String EVENT_ID = "eventId";        //eventID
    public final static String TIME = "time";           //时间，如停机倒计时
    public final static String STRTIME = "strtime";           //时间，如停机倒计时
    public final static String SERVERID = "serverid";           //
    public final static String FIRST_CREDIT = "first";          //是否是首次充值
    public final static String REASON = "reason";         //封停原因
    public final static String TYPE = "type";           //封停类型
    public final static String MSG_ID = "msgId";          //发送给服务器的消息ID
    public final static String CMD = "cmd";            //发送给服务器的消息ID
    public final static String VALUE = "value";           //封停类型
    public final static String NAME = "name";           //名字
    public final static String MAILID = "mailid";           //名字
}
