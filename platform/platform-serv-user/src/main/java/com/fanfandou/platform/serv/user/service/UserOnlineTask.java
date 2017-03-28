package com.fanfandou.platform.serv.user.service;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.user.entity.UserOnlineDetail;
import com.fanfandou.platform.api.user.entity.UserOnlineDetailExample;
import com.fanfandou.platform.api.user.service.UserService;
import com.fanfandou.platform.serv.user.dao.UserOnlineDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Description: 统计用户在线时长，用于防沉迷.
 * <p/>
 * Date: 2016-09-05 20:12.
 * author: Arvin.
 */
@Component
public class UserOnlineTask extends BaseLogger {

    @Autowired
    private UserOnlineDetailMapper userOnlineDetailMapper;

    @Autowired
    private UserService userService;

    @Scheduled(cron = "0 0/5 * * * ?")
    void execute() {
        logger.info("UserOnlineTask >>>>> execute");
        UserOnlineDetailExample userOnlineDetailExample = new UserOnlineDetailExample();
        userOnlineDetailExample.createCriteria().andIsOnlineEqualTo(true);
        List<UserOnlineDetail> onlineUsers = userOnlineDetailMapper.selectByExample(userOnlineDetailExample);
        Date now = new Date();
        for (UserOnlineDetail userOnlineDetail : onlineUsers) {
            logger.info("UserOnlineTask >>>> updateByPrimaryKeySelective before : " + userOnlineDetail.getUserId());
            userOnlineDetail.setOnlineSeconds(userOnlineDetail.getOnlineSeconds()
                    + (int) (now.getTime() - userOnlineDetail.getLastUpdateTime().getTime()) / 1000);
            userOnlineDetail.setLastUpdateTime(now);
            userOnlineDetailMapper.updateByPrimaryKeySelective(userOnlineDetail);
            logger.info("UserOnlineTask >>>> updateByPrimaryKeySelective after : " + userOnlineDetail.getUserId());
            try {
                logger.info("UserOnlineTask >>>>> start");
                userService.checkAddiction(userOnlineDetail.getIdcardId(), 0, userOnlineDetail.getUserId());
            } catch (ServiceException e) {
                logger.error("UserOnlineTask -> check addiction failed !", e);
            }
        }
    }
}
