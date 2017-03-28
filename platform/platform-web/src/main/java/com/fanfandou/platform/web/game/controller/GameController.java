package com.fanfandou.platform.web.game.controller;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.result.JsonResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.util.ErrorValidate;
import com.fanfandou.platform.api.game.entity.GameRole;
import com.fanfandou.platform.api.user.exception.UserException;
import com.fanfandou.platform.web.game.service.GameServiceClient;
import com.fanfandou.platform.web.game.vo.GameAreaVo;
import com.fanfandou.platform.web.game.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by wudi.
 * Descreption:游戏内容控制类.
 * Date:2016/6/8
 */
@RequestMapping("/game")
@RestController
@ErrorValidate
public class GameController extends BaseLogger {

    @Autowired
    private GameServiceClient gameServiceClient;

    /**
     * 获取区服信息.
     *
     * @param gameId      游戏ID
     * @param siteId      渠道ID
     * @param gameVersion 游戏版本号
     */
    @RequestMapping("/getGameAreas")
    public JsonResult queryGameAreas(int gameId, int siteId, int gameVersion)
            throws ServiceException {
        if (gameId == 0 || siteId == 0) {
            throw new UserException(UserException.USER_INVALID_SITE, "请求信息缺失");
        }
        List<GameAreaVo> gameAreaList = gameServiceClient.queryGameArea(gameId, siteId, gameVersion);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(gameAreaList);
        jsonResult.setMessage(JsonResult.SUCCESS_MSG);
        jsonResult.setStatus(JsonResult.SUCCESS);
        return jsonResult;
    }

    /**
     * 游戏公告,选择区服后，返回页面..
     */
    @RequestMapping("/getAnnounce")
    public void getAnnounce(int gameId, int siteId, int areaCode, int notifyType) throws Exception {
        String announce = gameServiceClient.getAnnounce(gameId, siteId, areaCode, notifyType);
        HttpServletResponse response = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getResponse();
        PrintWriter out = response.getWriter();
        out.print(announce);
    }

    /**
     * 游戏公告内容,选服前(只获取文字内容).
     */
    @RequestMapping("/getAnnounceContent")
    public JsonResult getAnnounceContent(int gameId, int siteId, int notifyType) throws ServiceException {
        String announce = gameServiceClient.getAnnounceContent(gameId, siteId, notifyType);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(announce);
        jsonResult.setMessage(JsonResult.SUCCESS_MSG);
        jsonResult.setStatus(JsonResult.SUCCESS);
        return jsonResult;
    }

    /**
     * 获取玩家所有区服角色列表.
     */
    @RequestMapping("/getRoleList")
    public JsonResult getRoleList(int gameId, long userId) throws ServiceException {
        List<RoleVo> roleVoList = gameServiceClient.getRoleList(gameId, userId);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(roleVoList);
        jsonResult.setMessage(JsonResult.SUCCESS_MSG);
        jsonResult.setStatus(JsonResult.SUCCESS);
        return jsonResult;
    }

    /**
     * 获取用户角色信息(包括角色分配和获取).
     */
    @RequestMapping("/getRole")
    public JsonResult getRole(int gameId, long userId, int areaCode, int siteId) throws ServiceException {
        logger.info("getRole gameId = {}, userId = {}, areaCode = {}, siteId = {}", gameId, userId, areaCode, siteId);
        GameRole gameRole = gameServiceClient.getRole(gameId, userId, areaCode, siteId);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(gameRole);
        jsonResult.setMessage(JsonResult.SUCCESS_MSG);
        jsonResult.setStatus(JsonResult.SUCCESS);
        return jsonResult;
    }

    /**
     * 检查和创建角色名.
     */
    @RequestMapping("/checkRoleName")
    public JsonResult checkRoleName(int gameId, int areaCode, int roleId, String roleName) throws ServiceException {
        logger.info("getRole gameId = {}, areaCode = {}", gameId, areaCode);
        try {
            roleName = URLDecoder.decode(roleName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        gameServiceClient.checkRoleName(gameId, areaCode, roleId, roleName);
        return JsonResult.RESULT_SUCCESS;
    }

    /**
     * 更换角色头像.
     */
    @RequestMapping("/changeRoleHead")
    public JsonResult changeRoleHead(int gameId, int roleId, String roleHead) throws ServiceException {
        gameServiceClient.changeRoleHead(gameId, roleId, roleHead);
        return JsonResult.RESULT_SUCCESS;
    }

    /**
     * 记录玩具角色等级.
     */
    @RequestMapping("/recordRoleLevel")
    public JsonResult recordRoleLevel(int gameId, int roleId, int roleLevel) throws ServiceException {
        gameServiceClient.recordRoleLevel(gameId, roleId, roleLevel);
        return JsonResult.RESULT_SUCCESS;
    }

}
