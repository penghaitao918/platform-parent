package com.fanfandou.platform.web.user.controller;

import com.fanfandou.admin.api.operation.entity.DeviceType;
import com.fanfandou.admin.api.operation.entity.GameVersionCheck;
import com.fanfandou.admin.api.operation.entity.Notice;
import com.fanfandou.admin.api.operation.service.PatchService;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.game.service.OperationDispatchService;
import com.fanfandou.platform.web.game.service.GameServiceClient;
import com.fanfandou.platform.web.game.vo.GameAreaVo;
import com.fanfandou.platform.web.user.servcie.UserServiceClient;
import com.fanfandou.platform.web.user.vo.AccountVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by wudi.
 * Descreption:单元测试
 * Date:2016/3/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/*.xml")
public class UserJunitTest extends BaseLogger{

    @Autowired
    private GameServiceClient gameServiceClient;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private OperationDispatchService operationDispatchService;

    @Autowired
    private PatchService patchService;

    @Test
    public void areaTest() throws ServiceException {
       List<GameAreaVo> areaList =  gameServiceClient.queryGameArea(1,9,1000);
        for (GameAreaVo gameArea : areaList){
            logger.debug("areaList address = " + gameArea.getClientEnterAddr());
        }
    }

    @Test
    public void sendNotice() throws ServiceException {
        Notice notice = new Notice();
        notice.setBeginTime(new Date());
        notice.setEndTime(new Date(1477015920000L));
        notice.setNoticeContent("开始轮换滚动公告,第四次");
        notice.setPublishCount(1);
        operationDispatchService.sendScrollNotice(GameCode.getById(1), 1, 1, new Date().getTime(), new Date(1477015920000L).getTime(),"开始轮换滚动公告,第四次", 1 );
    }

    @Test
    public void testDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd");
        Date date = new Date();
        System.out.println(simpleDateFormat.format(date));
    }

    @Test
    public void getloginKey () throws ServiceException {
        AccountVo accountVo = new AccountVo();
        accountVo.setAreaId(1);
        accountVo.setGameId(1);
        accountVo.setUserId(52L);
        userServiceClient.getLoginKey(accountVo);
    }

    @Test
    public void testPatch() throws ServiceException {
        GameVersionCheck gameVersionCheck = patchService.checkGameVersion(DeviceType.ANDROID, GameCode.getById(1), 9,500,"");
        logger.info("gameVersionCheck : " + gameVersionCheck.toString());
    }

}
