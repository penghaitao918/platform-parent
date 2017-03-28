package com.fanfandou.platform.api.game.service;

import com.fanfandou.common.entity.resource.DicItem;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.billing.entity.GoodsItemPackage;
import com.fanfandou.platform.api.game.entity.GameOperation;
import com.fanfandou.platform.api.game.entity.Message;
import com.fanfandou.platform.api.game.entity.OperationType;
import com.fanfandou.platform.api.game.exception.GameException;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Description: 向游戏发送指令、物品等跟游戏通讯的操作接口.
 * <p/>
 * Date: 2016-05-05 20:21.
 * author: Arvin.
 */
public interface OperationDispatchService {

    /**
     * 拿到游戏服允许登录的key.
     *
     * @param gameCode gameCode
     * @param areaId   区服id
     * @param roleId   角色id
     * @return key
     */
    String getLoginKey(GameCode gameCode, int areaId, long roleId) throws GameException;

    /**
     * 给游戏发送充值奖励.
     *
     * @param gameCode         游戏
     * @param userId           用户ID
     * @param areaId           区服
     * @param goodsItemPackage 奖励发送内容.
     */
    void deliverPayPackage(GameCode gameCode, long userId, int areaId, GoodsItemPackage goodsItemPackage)
            throws ServiceException;

    /**
     * 游戏内宝石购买道具消息转发.
     *
     * @param gameCode         gameCode
     * @param areaId           区服id
     * @param userId           用户id
     * @param goodsItemPackage 具体购买的东西
     * @throws ServiceException ServiceException
     */
    void routePurchaseByGem(GameCode gameCode, int areaId, long userId, GoodsItemPackage goodsItemPackage)
            throws ServiceException;

    /**
     * 推送消息到客户端.
     *
     * @param gameCode   gameCode
     * @param areaId     区服id
     * @param userId     用户id 全服通知的话userId填0
     * @param msgType    字典项配置：商品配方更新：1，商品配方上架：2，商品配方下架：3，玩具兑换码：4
     * @param msgLongVal long型参数，目前填商品id
     * @param msgStrVal  string型参数，目前填玩具兑换码
     * @throws ServiceException ServiceException
     */
    void pushMsgToClient(GameCode gameCode, int areaId, long userId, DicItem msgType,
                         long msgLongVal, String msgStrVal) throws ServiceException;

    void sendMsg(GameCode gameCode, int areaId, Message msg) throws GameException;

    /**
     * 单个玩家奖励发送.
     */
    void sendPackage(GameCode gameCode, int areaId, GoodsItemPackage goodsItemPackage,
                     long userId, long roleId, List<String> roleIds, OperationType optType) throws ServiceException;

    void sendAntiAddiction(GameCode gameCode, int areaId, long userId, int onlineSeconds) throws ServiceException;

    boolean completeOperation(GameOperation gameOperation);

    /**
     * 滚动公告.
     *
     * @param gameCode      游戏
     * @param noticeId      公告ID
     * @param areaId        区服ID
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param noticeContent 公告内容
     * @param publishCount  轮回次数
     */
    void sendScrollNotice(GameCode gameCode, long noticeId, int areaId, long startTime, long endTime,
                          String noticeContent, int publishCount) throws ServiceException;

    /**
     * 平台通知服务器，服务器给客户端发活动任务.
     */
    void sendActivityTask(GameCode gameCode, long userId, int areaId, int areaCode, int activityId, long startTime)
            throws ServiceException;

    /**
     * 平台通知服务器，服务器返给客户端结算活动任务.
     */
    void settleActivityTask(GameCode gameCode, long userId, int areaId, int areaCode, int activityId, int taskId)
            throws ServiceException;

    /**
     * 发送GM指令.
     *
     * @param gameCode  游戏
     * @param areaId  区服
     * @param userId    用户ID
     * @param dicItem   协议编号
     * @param gmContent GM指令相关内容
     */
    Map<Integer, Object> sendGmCommand(GameCode gameCode, int areaId, int areaCode, int userId, DicItem dicItem,
                                       Map<Integer, Object> gmContent) throws ServiceException;
}
