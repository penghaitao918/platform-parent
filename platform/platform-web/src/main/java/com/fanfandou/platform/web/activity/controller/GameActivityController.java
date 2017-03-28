package com.fanfandou.platform.web.activity.controller;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.result.JsonResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.util.ErrorValidate;
import com.fanfandou.platform.web.activity.service.GameActivityServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wudi.
 * Descreption:游戏活动.
 * Date:2016/11/23
 */
@RequestMapping("/activity")
@RestController
@ErrorValidate
public class GameActivityController extends BaseLogger {

    @Autowired
    private GameActivityServiceClient gameActivityServiceClient;

    @RequestMapping("/getGameActivityList")
    public JsonResult getGameActivityList(int gameId, int siteId, String areaCode) throws ServiceException {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(gameActivityServiceClient.getGameActivityList(gameId, siteId, areaCode));
        jsonResult.setStatus(JsonResult.SUCCESS);
        jsonResult.setMessage(JsonResult.SUCCESS_MSG);
        return jsonResult;
    }

    @RequestMapping("/getGameActivity")
    public JsonResult getGameActivity(int gameId, int id) throws ServiceException {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(gameActivityServiceClient.getGameActivity(gameId, id));
        jsonResult.setStatus(JsonResult.SUCCESS);
        jsonResult.setMessage(JsonResult.SUCCESS_MSG);
        return jsonResult;
    }

    @RequestMapping("/sendActivityTask")
    public JsonResult sendActivityTask(int gameId, int siteId, long userId, String areaCode, int id)
        throws ServiceException {
        gameActivityServiceClient.sendActivityTask(gameId, siteId, userId, areaCode, id);
        return JsonResult.RESULT_SUCCESS;
    }

    @RequestMapping("/settleActivityTask")
    public JsonResult settleActivityTask(int gameId, int siteId, long userId, String areaCode, int id, int taskId)
            throws ServiceException {
        gameActivityServiceClient.settleActivityTask(gameId, siteId, userId, areaCode, id, taskId);
        return JsonResult.RESULT_SUCCESS;
    }

    @RequestMapping("/checkPromoteCode")
    public JsonResult checkPromoteCode(int gameId, int siteId, int roleId, String code) throws ServiceException {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(gameActivityServiceClient.checkActiveCode(gameId, siteId, roleId, code));
        jsonResult.setStatus(JsonResult.SUCCESS);
        jsonResult.setMessage(JsonResult.SUCCESS_MSG);
        return jsonResult;
    }
}
