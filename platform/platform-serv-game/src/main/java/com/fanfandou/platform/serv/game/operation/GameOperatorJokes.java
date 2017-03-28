package com.fanfandou.platform.serv.game.operation;

import com.alibaba.fastjson.JSONObject;
import com.fanfandou.common.entity.resource.DicItem;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.util.CipherUtils;
import com.fanfandou.platform.api.billing.entity.GoodsItem;
import com.fanfandou.platform.api.billing.entity.GoodsItemPackage;
import com.fanfandou.platform.api.game.entity.EnterAddress;
import com.fanfandou.platform.api.game.entity.Message;
import com.fanfandou.platform.api.game.exception.GameException;
import com.fanfandou.platform.serv.game.connection.codec.JokesConnectionCodec;
import com.fanfandou.platform.serv.game.connection.netty.NettyGameConnector;
import com.fanfandou.platform.serv.game.entity.jokes.base.JokesMsgPing;
import com.fanfandou.platform.serv.game.entity.jokes.base.JokesParams;
import com.fanfandou.platform.serv.game.entity.jokes.gm.JokesMsgGmReq;
import com.fanfandou.platform.serv.game.entity.tol.CommonMsgType;
import com.fanfandou.platform.serv.game.entity.tol.gm.GmKeyValueList;
import com.fanfandou.platform.serv.game.service.GameWorker;
import org.apache.commons.collections.CollectionUtils;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wudi.
 * Descreption:十冷.
 * Date:2017/3/9
 */
public class GameOperatorJokes extends AbstractGameOperator {


    //private GameConnector logicConnector;
    private static final long LOGIC_PINT_PERIOD = 30L;

    @Override
    protected void initConnector() {
        EnterAddress areaServerAddr = gameArea.getServerEnterAddrObj();
        connector = new NettyGameConnector(new JokesConnectionCodec(), messageHandler,
                new InetSocketAddress(areaServerAddr.getIpAddress(), areaServerAddr.getPort()));
        connector.connect();
    }

    @Override
    protected void ping() {
        JokesMsgPing pingReq = new JokesMsgPing();
        pingReq.setAtion(JokesParams.ACTION_BROADCAST_SERVERID);
        pingReq.setMsgId((short) 3000);
        pingReq.setServerid(String.valueOf(gameArea.getId()));
        pingReq.setSign(sign(JokesParams.ACTION_BROADCAST_SERVERID));

        Message<JokesMsgPing> pingMsg = new Message<JokesMsgPing>("3000", pingReq,
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
    }

    private String sign(int action) {
        return CipherUtils.initMd5().encrypt("action=" + action + "key=" + "05A9C0A6CD768B798C186814243EEAC5");
    }


    @Override
    protected GameWorker getGameWorker() {
        return null;
    }

    @Override
    public void sendGMCmd(long dispatchId, String cmd, int type) {

    }

    @Override
    public void sendScrollNotice(long dispatchId, long startTime, long endTime, String noticeContent, int publishCount)
            throws GameException {

    }

    @Override
    public void pushMsgToClient(long userId, DicItem msgType, long msgLongVal, String msgStrVal)
            throws ServiceException {

    }

    @Override
    public void routePurchaseByGem(long userId, GoodsItemPackage itemPackage) throws ServiceException {

    }

    @Override
    public void sendItem(long dispatchId, long roleId, GoodsItemPackage itemPackage) throws GameException {


    }

    @Override
    public void sendMail(int dispatchId, List<String> roleIds, GoodsItemPackage itemPackage) throws ServiceException {
        int [] roleIdInts = new int[roleIds.size()];
        for (int i = 0; i < roleIds.size(); i++) {
            roleIdInts[i] = Integer.parseInt(roleIds.get(i));
        }
        JokesMsgGmReq mailReq = new JokesMsgGmReq();
        mailReq.setAction(JokesParams.ACTION_MAIL);
        mailReq.setContent(itemPackage.getPackageDesc());
        mailReq.setMailid(dispatchId + "");
        mailReq.setMsgId((short) 3000);
        mailReq.setRoleid(roleIdInts);
        mailReq.setOp(1);
        mailReq.setMailtarget(1);
        mailReq.setTransId(dispatchId + "");
        mailReq.setSign(sign(JokesParams.ACTION_MAIL));

        //拼接appendix
        List<GoodsItem> goodsItems = itemPackage.getGoodsItems();
        List<JSONObject> appendix = new ArrayList<>();
        if (!CollectionUtils.isEmpty(goodsItems) && goodsItems.size() == 3) {
            for (GoodsItem goodsItem : goodsItems) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("1", goodsItem.getItemType().getValue());//type
                jsonObject.put("2", goodsItem.getItemId());//itemid
                jsonObject.put("3", goodsItem.getValue());//num
                appendix.add(jsonObject);
            }
        }
        mailReq.setAppendix(appendix);
        Message<JokesMsgGmReq> message = new Message<JokesMsgGmReq>(dispatchId + "", mailReq,
                CommonMsgType.MSG_HILINK_GM2LOGIC.getId());
        connector.send(message);

    }

    @Override
    public void deliverPayPackage(long dispatchId, long userId, GoodsItemPackage itemPackage) throws ServiceException {

    }

    @Override
    public String getLoginKey(long dispatchId, long roleId) throws GameException {
        return null;
    }

    @Override
    public void sendMsg(Message msg) throws GameException {

    }

    @Override
    public void sendAntiAddiction(long userId, int onlineSeconds) throws GameException {

    }

    @Override
    public void sendActivityTask(long userId, int areaId, int activityId, long startTime) throws ServiceException {

    }

    @Override
    public void settleActivityTask(long userId, int areaId, int activityId, int taskId) throws ServiceException {

    }

    @Override
    public Map<Integer, Object> sendGmCommand(int gmMsgType, long operateId, long userId, int areaId,
                                              GmKeyValueList keyValueList) throws ServiceException {
        return null;
    }
}
