package com.fanfandou.platform.web.activity.service;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.ValidStatus;
import com.fanfandou.common.entity.resource.SiteCode;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.activity.entity.GameActivity;
import com.fanfandou.platform.api.activity.service.GameActivityService;
import com.fanfandou.platform.api.activity.service.PromoteCodeBatchService;
import com.fanfandou.platform.api.game.entity.GameArea;
import com.fanfandou.platform.api.game.service.GameAreaService;
import com.fanfandou.platform.web.activity.vo.GameActivityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wudi.
 * Descreption:游戏活动.
 * Date:2016/11/23
 */
@Service
public class GameActivityServiceClient extends BaseLogger {

    @Autowired
    private GameActivityService gameActivityService;

    @Autowired
    private PromoteCodeBatchService promoteCodeBatchService;

    @Autowired
    private GameAreaService gameAreaService;

    /**
     * 获取活动列表.
     */
    public List<GameActivityVo> getGameActivityList(int gameId, int siteId, String areaCode) throws ServiceException {
        GameArea gameArea = gameAreaService.getGameAreaByCode(gameId, areaCode);
        List<GameActivity> gameActivities = gameActivityService.getGameActivityList(gameId);
        List<GameActivityVo> gameActivityVos = new ArrayList<>();
        for (GameActivity gameActivity : gameActivities) {
                if (gameActivity.getValidStatus().getId() == ValidStatus.REMOVED.getId()) {
                    continue;
                }
            if ((gameActivity.getSiteIds().contains("," + siteId + ",") || SiteCode.getById(siteId)
                    .isParent(SiteCode.getById(siteId)) || StringUtils.isEmpty(gameActivity.getSiteIds()))
                    && (gameActivity.getAreaIds().contains("," + gameArea.getId() + ","))) {
                GameActivityVo gameActivityVo = new GameActivityVo();
                gameActivityVo.setActivityContent(gameActivity.getActivityContent());
                gameActivityVo.setActivityId(gameActivity.getActivityId());
                gameActivityVo.setStartTime(transDate(gameActivity.getStartTime()));
                gameActivityVo.setEndTime(transDate(gameActivity.getEndTime()));
                gameActivityVo.setId(gameActivity.getId().intValue());
                gameActivityVo.setValidStatus(gameActivity.getValidStatus().getId());
                gameActivityVos.add(gameActivityVo);
            }
        }
        return gameActivityVos;
    }

    public String transDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取单个活动内容.
     */
    public GameActivityVo getGameActivity(int gameId, int id) throws ServiceException {
        GameActivity gameActivity = gameActivityService.getGameActivityContent(gameId, id);
        GameActivityVo gameActivityVo = new GameActivityVo();
        gameActivityVo.setActivityContent(gameActivity.getActivityContent());
        gameActivityVo.setActivityId(gameActivity.getActivityId());
        gameActivityVo.setEndTime(transDate(gameActivity.getEndTime()));
        gameActivityVo.setStartTime(transDate(gameActivity.getStartTime()));
        gameActivityVo.setId(gameActivity.getId().intValue());
        gameActivityVo.setValidStatus(gameActivity.getValidStatus().getId());
        return gameActivityVo;
    }

    /**
     * 通知游戏服活动信息.
     */
    public void sendActivityTask(int gameId, int siteId, long userId, String areaCode, int id)
            throws ServiceException {
        GameArea gameArea = gameAreaService.getGameAreaByCode(gameId, areaCode);
        gameActivityService.sendActivityTask(gameId, siteId, userId, gameArea.getId(),Integer.parseInt(areaCode)
                , id);
    }

    /**
     * 通知游戏服活动结算.
     */
    public void settleActivityTask(int gameId, int siteId, long userId, String areaCode, int id, int taskId)
            throws ServiceException {
        GameArea gameArea = gameAreaService.getGameAreaByCode(gameId, areaCode);
        gameActivityService.settleActivityTask(gameId, siteId, userId, gameArea.getId(),Integer.parseInt(areaCode), id, taskId);
    }

    /**
     * 激活码激活.
     */
    public String checkActiveCode(int gameId, int siteId, int roleId, String code) throws ServiceException {
        return promoteCodeBatchService.checkPromoteCode(gameId, siteId, roleId, code);
    }
}
