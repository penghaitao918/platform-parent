package com.platform.serv.activity.service;

import com.fanfandou.common.entity.ValidStatus;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.util.GenCodeUtil;
import com.fanfandou.platform.api.activity.entity.GameActivity;
import com.fanfandou.platform.api.activity.entity.PromoteCode;
import com.fanfandou.platform.api.activity.entity.PromoteCodeBatch;
import com.fanfandou.platform.api.activity.service.GameActivityService;
import com.fanfandou.platform.api.activity.service.PromoteAwardPackageService;
import com.fanfandou.platform.api.activity.service.PromoteCodeBatchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by wudi.
 * Descreption:活动单元测试.
 * Date:2016/11/23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/*.xml")
public class GameActivityTest {

    @Autowired
    private GameActivityService gameActivityService;

    @Autowired
    private PromoteAwardPackageService promoteAwardPackageService;

    @Autowired
    private PromoteCodeBatchService promoteCodeBatchService;

    @Test
    public void testCreateGameAct() throws ServiceException {
        GameActivity gameActivity = new GameActivity();
        gameActivity.setActivityContent("啦啦啦啦啦");
        gameActivity.setActivityId(1);
        gameActivity.setAreaIds(",1,2,3,4");
        gameActivity.setEndTime(new Date());
        gameActivity.setGameId(1);
        gameActivity.setSiteIds(",1,2,3,4");
        gameActivity.setStartTime(new Date());
        gameActivity.setTaskId("1,2,3,4");
        gameActivity.setValidStatus(ValidStatus.VALID);
        gameActivityService.createActivity(gameActivity);
    }

    @Test
    public void testqueryAll() throws ServiceException {
        List<GameActivity> gameActivities = gameActivityService.getGameActivityList(1);
        for (GameActivity gameActivity: gameActivities) {
            System.out.println(gameActivity.toString());
        }
    }

    @Test
    public void testPromoteCode() {
        List<String> codes = new ArrayList<>();
        HashSet hashSet = new HashSet();
        for (int i = 0; i < 1000; i++) {
            String code = GenCodeUtil.generateCode(String.format("%07d", i));//04:8, 07:14
            System.out.println(code);
            //codes.add(code);
            //hashSet.add(code);
        }
        //System.out.println("list = " + codes.size() + "hashSet = " + hashSet.size());
    }

    @Test
    public void testCreateCode() throws ServiceException {
        PromoteCodeBatch promoteCodeBatch = new PromoteCodeBatch();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date from = sdf.parse("1898-06-14 20:00:13");
            Date to = sdf.parse("2017-09-15 20:49:03");
            promoteCodeBatch.setAmount(10);
            promoteCodeBatch.setGameId(1);
            promoteCodeBatch.setAvailableEndDate(to);
            promoteCodeBatch.setAvailableStartDate(from);
            promoteCodeBatch.setAwardGreet("啦啦啦啦");
            promoteCodeBatch.setAwardRole("10086");
            promoteCodeBatch.setBatchDesc("啦啦啦啦");
            promoteCodeBatch.setBatchId(1);
            promoteCodeBatch.setBatchName("激活码");
            promoteCodeBatch.setCreateDate(new Date());
            promoteCodeBatch.setPackageId(3);
            promoteCodeBatch.setSiteId(22);

            List<String> codeList = promoteCodeBatchService.generatePromoteCode(promoteCodeBatch);
            for (String code : codeList) {
                System.out.println(code);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkCode() throws ServiceException {
        String tips = promoteCodeBatchService.checkPromoteCode(1, 22, 10086, "4ECBBB88B2043C");
        System.out.println(tips);
    }
}
