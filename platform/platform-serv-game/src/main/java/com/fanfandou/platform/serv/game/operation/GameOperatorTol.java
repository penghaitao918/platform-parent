package com.fanfandou.platform.serv.game.operation;

import com.fanfandou.admin.api.operation.entity.Notice;
import com.fanfandou.common.constant.DicItemKeyConstant;
import com.fanfandou.common.entity.resource.DicItem;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.exception.ValidateException;
import com.fanfandou.platform.api.billing.entity.BillingOrder;
import com.fanfandou.platform.api.billing.entity.GoodsItem;
import com.fanfandou.platform.api.billing.entity.GoodsItemPackage;
import com.fanfandou.platform.api.billing.exception.BillingException;
import com.fanfandou.platform.api.game.entity.EnterAddress;
import com.fanfandou.platform.api.game.entity.Message;
import com.fanfandou.platform.api.game.exception.GameException;
import com.fanfandou.platform.serv.game.connection.GameConnector;
import com.fanfandou.platform.serv.game.connection.codec.TolConnectionCodec;
import com.fanfandou.platform.serv.game.connection.netty.NettyGameConnector;
import com.fanfandou.platform.serv.game.entity.tol.CommonMsgType;
import com.fanfandou.platform.serv.game.entity.tol.account.Msg_Account2Center_AntiAddiction_Req;
import com.fanfandou.platform.serv.game.entity.tol.account.Msg_Account2Center_Reg_Login_Key_Req;
import com.fanfandou.platform.serv.game.entity.tol.account.Msg_Center2Account_Reg_Login_Key_Res;
import com.fanfandou.platform.serv.game.entity.tol.base.Msg_Connect;
import com.fanfandou.platform.serv.game.entity.tol.base.Msg_Ping_Req;
import com.fanfandou.platform.serv.game.entity.tol.base.ServerType;
import com.fanfandou.platform.serv.game.entity.tol.gm.GMErrorCode;
import com.fanfandou.platform.serv.game.entity.tol.gm.GmKeyValue;
import com.fanfandou.platform.serv.game.entity.tol.gm.GmKeyValueList;
import com.fanfandou.platform.serv.game.entity.tol.gm.GmNotice;
import com.fanfandou.platform.serv.game.entity.tol.gm.GmRouteMsgType;
import com.fanfandou.platform.serv.game.entity.tol.gm.Msg_Gm2Logic_Activity_Finsh_Req;
import com.fanfandou.platform.serv.game.entity.tol.gm.Msg_Gm2Logic_Activity_Get_Req;
import com.fanfandou.platform.serv.game.entity.tol.gm.Msg_Gm2Logic_Deposit_Req;
import com.fanfandou.platform.serv.game.entity.tol.gm.Msg_Gm2Logic_GM_Req;
import com.fanfandou.platform.serv.game.entity.tol.gm.Msg_Gm2Logic_Mail_Req;
import com.fanfandou.platform.serv.game.entity.tol.gm.Msg_Gm2Logic_Notice_Req;
import com.fanfandou.platform.serv.game.entity.tol.gm.Msg_Gm2Logic_Route_Req;
import com.fanfandou.platform.serv.game.entity.tol.gm.Msg_Gm2Logic_Shop_Req;
import com.fanfandou.platform.serv.game.entity.tol.gm.Msg_Logic2Gm_Deposit_Res;
import com.fanfandou.platform.serv.game.entity.tol.gm.Msg_Logic2Gm_GM_Res;
import com.fanfandou.platform.serv.game.entity.tol.gm.Msg_Logic2Gm_Notice_Res;
import com.fanfandou.platform.serv.game.service.GameWorker;
import com.fanfandou.platform.serv.game.service.GameWorkerTol;
import org.apache.commons.collections.CollectionUtils;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Description: 无限幻想operator.
 * <p/>
 * 无限幻想游戏方架构不同，此处connector代表中心服，logicConnector代表游戏逻辑服
 * <p/>
 * Date: 2016-05-10 10:37.
 * author: Arvin.
 */
public class GameOperatorTol extends AbstractGameOperator {
    private GameConnector logicConnector;
    private static final long LOGIC_PINT_PERIOD = 30L;

    @Override
    protected void initConnector() {
        //确认要使用的connector，并传入基础协议以及回调msg处理接口
        logger.info("GameOperatorTol.initConnector -> the connector is initializing ... ");
        EnterAddress centerServerAddr = gameArea.getAreaGroup().getServerEnterAddrObj();
        connector = new NettyGameConnector(new TolConnectionCodec(), messageHandler,
                new InetSocketAddress(centerServerAddr.getIpAddress(), centerServerAddr.getPort()));
        connector.connect();
        Message<Msg_Connect> connectMsg = new Message<>("",
                new Msg_Connect(ServerType.ServerType_AccountDB.getNumber()),
                CommonMsgType.MSG_CONNECT.getId());
        try {
            connector.send(connectMsg);
            //connector.send(connectMsg);
        } catch (GameException e) {
            logger.error("send connect msg failed !", e);
        }

    }

    @Override
    protected void ping() {
        Message<Msg_Ping_Req> pingMsg = new Message<Msg_Ping_Req>("1", new Msg_Ping_Req(),
                CommonMsgType.MSG_PING_REQ.getId());
        try {
            connector.send(pingMsg);
        } catch (GameException e) {
            logger.error("GameOperatorTol.ping -> ping faild and reconnected!", e);
            try {
                Thread.sleep(30 * 1000);
            } catch (InterruptedException e1) {
                logger.error("sleep error", e1);
            }
            if (connector != null && !connector.isConnected()) {
                connector.connect();
            }
        }
        //要不要等返回消息做处理
    }


    /**
     * 逻辑服链接初始化，心跳自己维护，不依赖于abstract.
     */
    private void initLogicConnector() {
        EnterAddress areaServerAddr = gameArea.getServerEnterAddrObj();
        logicConnector = new NettyGameConnector(new TolConnectionCodec(), messageHandler,
                new InetSocketAddress(areaServerAddr.getIpAddress(), areaServerAddr.getPort()));
        logicConnector.connect();
        Message<Msg_Connect> connectMsg = new Message<>("",
                new Msg_Connect(ServerType.ServerType_ClientGM.getNumber()),
                CommonMsgType.MSG_CONNECT.getId());
        try {
            logicConnector.send(connectMsg);
        } catch (GameException e) {
            logger.error("send connect msg failed !", e);
        }
        //心跳
        ScheduledExecutorService logicSchedule = Executors
                .newScheduledThreadPool(2);
        logicSchedule.scheduleAtFixedRate(new LogicPingTask(), LOGIC_PINT_PERIOD, LOGIC_PINT_PERIOD, TimeUnit.SECONDS);
    }

    /**
     * 逻辑服心跳task.
     */
    private class LogicPingTask extends TimerTask {

        @Override
        public void run() {
            try {
                logicPing();
            } catch (Throwable e) {
                logger.error("LogicPingTask.run:", e);
            }
        }

        private void logicPing() {
            Message<Msg_Ping_Req> pingMsg = new Message<Msg_Ping_Req>("2", new Msg_Ping_Req(),
                    CommonMsgType.MSG_PING_REQ.getId());
            try {
                logicConnector.send(pingMsg);
            } catch (GameException e) {
                logger.error("GameOperatorTol.logicPing -> ping faild and reconnected!", e);
                try {
                    Thread.sleep(30 * 1000);
                } catch (InterruptedException e1) {
                    logger.error("sleep error", e1);
                }
                if (logicConnector != null && !logicConnector.isConnected()) {
                    logicConnector.connect();
                }
            }
        }
    }


    @Override
    protected GameWorker getGameWorker() {
        return new GameWorkerTol(gameCode, gameArea.getId());
    }

    @Override
    public void sendGMCmd(long dispatchId, String cmd, int type) {

    }

    @Override
    public void sendItem(long dispatchId, long roleId, GoodsItemPackage itemPackage) throws GameException {
        Msg_Gm2Logic_Mail_Req mailReq = new Msg_Gm2Logic_Mail_Req(dispatchId + "", (int) roleId,
                Integer.valueOf(gameArea.getAreaCode()), itemPackage.getTitle(),
                itemPackage.getPackageDesc(), 7 * 24 * 60 * 60);
        List<Integer> itemIds = new ArrayList<>();
        List<Integer> itemCounts = new ArrayList<>();
        for (GoodsItem item : itemPackage.getGoodsItems()) {
            itemIds.add(item.getItemId());
            itemCounts.add(item.getValue());
        }
        mailReq.setMItemIdsList(itemIds);
        mailReq.setMItemCountsList(itemCounts);
        Message<Msg_Gm2Logic_Mail_Req> msg =
                new Message<>(dispatchId + "", mailReq,
                        CommonMsgType.MSG_GM2LOGIC_MAIL_REQ.getId());
        sendLogicMsg(msg);
    }

    @Override
    public void sendMail(int dispatchId, List<String> roleIds, GoodsItemPackage itemPackage) throws ServiceException {

    }

    @Override
    public void deliverPayPackage(long dispatchId, long userId, GoodsItemPackage itemPackage) throws ServiceException {
        if (CollectionUtils.isEmpty(itemPackage.getGoodsItems())) {
            throw new ValidateException(ValidateException.INVALID_ARGUMENT, "GoodsItem is null");
        }
        GoodsItem item = itemPackage.getGoodsItems().get(0);
        if (item.getItemType().getKey().equals(DicItemKeyConstant.GOODS_ITEM_TYPE_GEM)) {
            Message<Msg_Gm2Logic_Deposit_Req> msg =
                    new Message<>("",
                            new Msg_Gm2Logic_Deposit_Req(dispatchId + "", (int) userId, Integer.parseInt(gameArea.getAreaCode()),
                                    itemPackage.getValue(),
                                    item.getValue()),
                            CommonMsgType.MSG_GM2LOGIC_DEPOSIT_REQ.getId());
            sendLogicMsg(msg);
        } else {
            logger.info("start delever item");
            Msg_Gm2Logic_Shop_Req shopReq = new Msg_Gm2Logic_Shop_Req(dispatchId + "", (int) userId,
                    Integer.parseInt(gameArea.getAreaCode()), itemPackage.getValue(), item.getItemId(), 0);

            Message<Msg_Gm2Logic_Shop_Req> msg = new Message<>(dispatchId + "", shopReq,
                    CommonMsgType.MSG_GM2LOGIC_SHOP_REQ.getId());
            sendLogicMsg(msg);
        }
    }

    @Override
    public void sendScrollNotice(long dispatchId, long startTime, long endTime,
                                 String noticeContent, int publishCount) throws GameException {
        GmNotice gmNotice = new GmNotice();
        gmNotice.setMNoticeStr(noticeContent);
        gmNotice.setMBeginTime(startTime / 1000);
        gmNotice.setMEndTime(endTime / 1000);
        gmNotice.setMLoopCount(publishCount);

        Msg_Gm2Logic_Notice_Req noticeReq = new Msg_Gm2Logic_Notice_Req();
        noticeReq.setMSerialNumber(dispatchId + "");
        noticeReq.getMNoticeList().add(gmNotice);

        Message<Msg_Gm2Logic_Notice_Req> msg =
                new Message<>(dispatchId + "",
                        noticeReq, CommonMsgType.MSG_GM2LOGIC_NOTICE_REQ.getId());
        sendLogicMsg(msg);
    }

    @Override
    public void pushMsgToClient(long userId, DicItem msgType, long msgLongVal, String msgStrVal) throws ServiceException {
        GmRouteMsgType routeMsgType = GmRouteMsgType.valueOf(Integer.valueOf(msgType.getValue()));
        if (routeMsgType == null) {
            throw new ValidateException(ValidateException.INVALID_ARGUMENT, "GmRouteMsgType is null");
        }
        Msg_Gm2Logic_Route_Req pushMsg = new Msg_Gm2Logic_Route_Req("", (int) userId,
                Integer.parseInt(gameArea.getAreaCode()), routeMsgType.getNumber());
        List<Long> params = new ArrayList<>();
        params.add(msgLongVal);
        pushMsg.setMParmList(params);
        List<String> strParams = new ArrayList<>();
        strParams.add(msgStrVal);
        pushMsg.setMParmStrList(strParams);
        Message<Msg_Gm2Logic_Route_Req> reqMsg = new Message<>("", pushMsg, CommonMsgType.MSG_GM2LOGIC_ROUTE_REQ.getId());
        sendLogicMsg(reqMsg);

    }

    @Override
    public void routePurchaseByGem(long userId, GoodsItemPackage itemPackage) throws ServiceException {
        if (CollectionUtils.isEmpty(itemPackage.getGoodsItems())) {
            throw new ValidateException(ValidateException.INVALID_ARGUMENT, "GoodsItem is null");
        }
        GoodsItem item = itemPackage.getGoodsItems().get(0);
        Msg_Gm2Logic_Shop_Req shopReq = new Msg_Gm2Logic_Shop_Req("0", (int) userId,
                Integer.parseInt(gameArea.getAreaCode()), itemPackage.getValue(), item.getItemId(), 1);

        Message<Msg_Gm2Logic_Shop_Req> msg = new Message<>("", shopReq, CommonMsgType.MSG_GM2LOGIC_SHOP_REQ.getId());
        sendLogicMsg(msg);
    }

    @Override
    public String getLoginKey(long dispatchId, long roleId) throws GameException {
        Message<Msg_Account2Center_Reg_Login_Key_Req> msg =
                new Message<Msg_Account2Center_Reg_Login_Key_Req>(dispatchId + "",
                        new Msg_Account2Center_Reg_Login_Key_Req(dispatchId + "", (int) roleId, 0),
                        CommonMsgType.MSG_ACCOUNT2CENTER_REG_LOGIN_KEY_REQ.getId());
        connector.send(msg);
        Message response = messageHandler.getResponse(dispatchId + "");
        if (response != null && response.getProtocolType()
                == CommonMsgType.MSG_CENTER2ACCOUNT_REG_LOGIN_KEY_RES.getId()) {
            Msg_Center2Account_Reg_Login_Key_Res responseMsg
                    = (Msg_Center2Account_Reg_Login_Key_Res) response.getMsgBody();
            return responseMsg.getMKey() + "";
        }

        return null;
    }

    @Override
    public void sendMsg(Message msg) throws GameException {
        connector.send(msg);
    }

    private void sendLogicMsg(Message msg) throws GameException {
        if (logicConnector == null) {
            initLogicConnector();
        }
        logicConnector.send(msg);
    }

    @Override
    public void sendAntiAddiction(long userId, int onlineSeconds) throws GameException {
        Msg_Account2Center_AntiAddiction_Req antiAddictionReq = new Msg_Account2Center_AntiAddiction_Req();
        antiAddictionReq.setMAccountId((int) userId);
        antiAddictionReq.setMServerId(Integer.valueOf(gameArea.getAreaCode()));
        antiAddictionReq.setMTimeUsed(onlineSeconds);
        Message<Msg_Account2Center_AntiAddiction_Req> msg = new Message<>("",
                antiAddictionReq, CommonMsgType.MSG_ACCOUNT2CENTER_ANTIADDICTION_REQ.getId());
        sendMsg(msg);
    }

    @Override
    public void sendActivityTask(long userId, int areaId, int activityId, long startTime) throws ServiceException {
        Msg_Gm2Logic_Activity_Get_Req activityGetReq = new Msg_Gm2Logic_Activity_Get_Req();
        activityGetReq.setMAccountId((int) userId);
        activityGetReq.setMActivityId(activityId);
        activityGetReq.setMBeginTime(startTime / 1000);
        activityGetReq.setMSerialNumber("");
        activityGetReq.setMServerId(areaId);
        Message<Msg_Gm2Logic_Activity_Get_Req> msg = new Message<>("",
                activityGetReq, CommonMsgType.MSG_GM2LOGIC_ACTIVITY_GET_REQ.getId());
        sendLogicMsg(msg);
    }

    @Override
    public void settleActivityTask(long userId, int areaId, int activityId, int taskId) throws ServiceException {
        Msg_Gm2Logic_Activity_Finsh_Req activityFinshReq = new Msg_Gm2Logic_Activity_Finsh_Req();
        activityFinshReq.setMAccountId((int) userId);
        activityFinshReq.setMActivityId(activityId);
        activityFinshReq.setMServerId(areaId);
        activityFinshReq.setMTaskId(taskId);
        activityFinshReq.setMSerialNumber("");
        Message<Msg_Gm2Logic_Activity_Finsh_Req> msg = new Message<>("",
                activityFinshReq, CommonMsgType.MSG_GM2LOGIC_ACTIVITY_FINSH_REQ.getId());
        sendLogicMsg(msg);
    }

    @Override
    public Map<Integer, Object> sendGmCommand(int gmMsgType, long operateId, long userId, int areaId, GmKeyValueList keyValueList)
            throws ServiceException {
        Msg_Gm2Logic_GM_Req gm2LogicGmReq = new Msg_Gm2Logic_GM_Req();
        gm2LogicGmReq.setMAccountId((int) userId);
        gm2LogicGmReq.setMParamList(keyValueList);
        gm2LogicGmReq.setMServerId(areaId);
        gm2LogicGmReq.setMType(gmMsgType);
        logger.debug("sendGmCommand keyValueList size = " + gm2LogicGmReq.getMParamList().getMValuesList().size());
        Message<Msg_Gm2Logic_GM_Req> msg = new Message<Msg_Gm2Logic_GM_Req>(operateId + "",
                gm2LogicGmReq, CommonMsgType.MSG_GM2LOGIC_GM_REQ.getId());
        sendLogicMsg(msg);
        Message response = messageHandler.getResponse(operateId + "");
        Map<Integer, Object> valueMap = new HashMap<>();
        if (response != null && response.getProtocolType()
                == CommonMsgType.MSG_LOGIC2GM_GM_RES.getId()) {
            Msg_Logic2Gm_GM_Res gmGmRes = (Msg_Logic2Gm_GM_Res) response.getMsgBody();
            logger.debug("sendGmCommand return :" + gmGmRes.getMRetInfo());
            GmKeyValueList resGmKeyValue = gmGmRes.getMRetInfo();

            List<GmKeyValue> gmCommandList = resGmKeyValue.getMValuesList();
            for (GmKeyValue keyValue : gmCommandList) {
                if (keyValue.getMValueUint32() != null) {
                    valueMap.put(keyValue.getMParam(), keyValue.getMValueUint32());
                }
                if (keyValue.getMValueString() != null) {
                    valueMap.put(keyValue.getMParam(), keyValue.getMValueString());
                }
                if (keyValue.getMValueBool() != null) {
                    valueMap.put(keyValue.getMParam(), keyValue.getMValueBool());
                }
            }
        }
        return valueMap;
    }

}
