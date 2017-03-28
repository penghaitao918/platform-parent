package com.platform.serv.game.service;

import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.util.ProtostuffSerializeUtil;
import com.fanfandou.platform.api.game.exception.GameException;
import com.fanfandou.platform.api.game.service.OperationDispatchService;
import com.fanfandou.platform.serv.game.entity.tol.gm.GmNotice;
import com.fanfandou.platform.serv.game.entity.tol.gm.Msg_Gm2Logic_Notice_Req;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Description: 平台与游戏通讯测试.
 * <p/>
 * Date: 2016-05-27 17:16.
 * author: Arvin.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/*.xml")
public class GameConnectionTest {

    @Autowired
    private OperationDispatchService operationDispatchService;

    @Test
    public void testGetLoginKey() throws GameException {

        String loginKey = operationDispatchService.getLoginKey(GameCode.getById(1), 381, 1);
        System.out.println(loginKey);

    }

    @Test
    public void testSerialize(){
        GmNotice gmNotice = new GmNotice();
        gmNotice.setMNoticeStr("测试");
        gmNotice.setMBeginTime(new Date().getTime());
        gmNotice.setMEndTime(new Date().getTime());
        gmNotice.setMLoopCount(2);

        byte[] data1 = ProtostuffSerializeUtil.serialize(gmNotice);
        byte[] data2 = ProtostuffSerializeUtil.serializeProtoBuf(gmNotice);

        Msg_Gm2Logic_Notice_Req noticeReq = new Msg_Gm2Logic_Notice_Req();
        noticeReq.setMSerialNumber(21 + "");
        noticeReq.getMNoticeList().add(gmNotice);

        byte[] seriData = ProtostuffSerializeUtil.serialize(noticeReq);
        Msg_Gm2Logic_Notice_Req notice_req = ProtostuffSerializeUtil.deserialize(seriData, Msg_Gm2Logic_Notice_Req.class);
        System.out.println(notice_req);

        /*CommonGMMsg.Msg_Gm2Logic_Notice_Req.Builder builder = CommonGMMsg.Msg_Gm2Logic_Notice_Req.newBuilder();
        builder.setMSerialNumber(21+"");
        builder.addMNotice(CommonGMStruct.GmNotice.newBuilder()
                .setMBeginTime(new Date().getTime())
        .setMEndTime(new Date().getTime())
        .setMLoopCount(2l)
        .setMNoticeStr("测试"));
        byte[] seriData  = builder.build().toByteArray();
        System.out.println(11);
*/
    }

}
