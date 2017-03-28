package com.fanfandou.platform.web.game.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fanfandou.admin.api.operation.entity.Notice;
import com.fanfandou.admin.api.operation.service.NoticeService;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.ActStatus;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.entity.resource.SiteCode;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.game.entity.GameArea;
import com.fanfandou.platform.api.game.entity.GameRole;
import com.fanfandou.platform.api.game.exception.GameException;
import com.fanfandou.platform.api.game.service.GameAreaService;
import com.fanfandou.platform.api.game.service.GameRoleService;
import com.fanfandou.platform.web.game.vo.GameAreaVo;
import com.fanfandou.platform.web.game.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wudi.
 * Descreption:游戏交互服务层.
 * Date:2016/6/8
 */
@Service("gameServiceClient")
public class GameServiceClient extends BaseLogger {

    @Autowired
    private GameAreaService gameAreaService;

    @Autowired
    private GameRoleService gameRoleService;

    @Autowired
    private NoticeService noticeService;

    /**
     * 客户端获取的区服信息.
     *
     * @param gameId      游戏ID
     * @param siteId      渠道ID
     * @param gameVersion 游戏当前版本号
     * @return 区服列表信息.
     */
    @SuppressWarnings("unchecked")
    public List<GameAreaVo> queryGameArea(int gameId, int siteId, int gameVersion) throws ServiceException {
        List<GameArea> gameAreaList = gameAreaService.getAreasByGameId(gameId);
        List<GameAreaVo> returnAreaList = new ArrayList<GameAreaVo>();
        for (GameArea gameArea : gameAreaList) {
            //校验区服有效性
            if (gameArea.getValidStatus() == ActStatus.ACTED) {
                int minVersion = Integer.parseInt(gameArea.getSupportVersionMin());
                int maxVersion = Integer.parseInt(gameArea.getSupportVersionMax());
                //校验是否在版本信息内
                if (gameVersion >= minVersion && gameVersion <= maxVersion) {
                    //校验是否在渠道范围内
                    boolean flag = false;
                    String sites[] = gameArea.getSiteIds().split(",");
                    for (String site : sites) {
                        int pSiteId = Integer.parseInt(site);
                        if (pSiteId == siteId || SiteCode.getById(siteId).isParent(SiteCode.getById(siteId))
                                || StringUtils.isEmpty(site)) {
                            flag = true;
                        }
                    }
                    if (flag) {
                        logger.debug("queryGameArea avalableTime = " + gameArea.getAvailableTime());
                        String availableTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                .format(gameArea.getAvailableTime());
                        GameAreaVo gameAreaVo = new GameAreaVo();
                        gameAreaVo.setAreaCode(gameArea.getAreaCode());
                        gameAreaVo.setAreaDesc(gameArea.getAreaDesc());
                        gameAreaVo.setAreaName(gameArea.getAreaName());
                        gameAreaVo.setAreaTag(gameArea.getAreaTag());
                        gameAreaVo.setAvailableTime(availableTime);
                        gameAreaVo.setDisplayOrder(gameArea.getDisplayOrder());

                        gameAreaVo.setClientEnterAddr(JSON.toJSONString(gameArea.getClientEnterAddrList(), SerializerFeature.WriteEnumUsingToString));
                        gameAreaVo.setGameId(gameArea.getGameId());
                        gameAreaVo.setLoadStatus(gameArea.getLoadStatus());
                        gameAreaVo.setMaintenanceStatus(gameArea.getMaintenanceStatus().getId());
                        returnAreaList.add(gameAreaVo);
                    }
                }
            }
        }
        return returnAreaList;
    }


    /**
     * 获取游戏公告.
     */
    public String getAnnounce(int gameId, int siteId, int areaCode, int noticType) throws ServiceException {
        GameArea gameArea = gameAreaService.getGameAreaByCode(gameId, areaCode + "");
        logger.info("GameServiceClient >>> getAnnounce areaId = " + gameArea.getId());
        Notice notice = noticeService.findByGameSiteAreaId(gameId, siteId, gameArea.getId(), noticType);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>\n<html>\n<head>\n");
        stringBuilder.append("    <meta charset=\"utf-8\">\n");
        stringBuilder.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        stringBuilder.append("    <title>游戏公告</title>\n");
        stringBuilder.append("</head>\n<body>");
        if (notice != null) {
            if (!StringUtils.isEmpty(notice.getNoticeTitle())) {
                stringBuilder.append(notice.getNoticeTitle());
                stringBuilder.append("<br>");
            }
            stringBuilder.append(notice.getNoticeContent());
        } else {
            stringBuilder.append("There are no announce");
        }
        stringBuilder.append("</body>\n</html>");
        return stringBuilder.toString();
    }

    /**
     * 获取游戏公告.
     */
    public String getAnnounceContent(int gameId, int siteId, int noticType) throws ServiceException {
        Notice notice = noticeService.findByGameSiteAreaId(gameId, siteId, 0, noticType);
        String noticeContent = notice.getNoticeContent();
        String regExhtml = "<[^>]+>"; // 定义HTML标签的正则表达式
        Pattern phtml = Pattern.compile(regExhtml, Pattern.CASE_INSENSITIVE);
        Matcher mhtml = phtml.matcher(noticeContent);
        noticeContent = mhtml.replaceAll(""); // 过滤html标签
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("noticeTitle", notice.getNoticeTitle());
        jsonObject.put("noticeContent", noticeContent);
        return jsonObject.toString();
    }

    /**
     * 获取所有区服玩家角色列表.
     */
    public List<RoleVo> getRoleList(int gameId, long userId) throws ServiceException {
        List<GameRole> gameRoleList = gameRoleService.getRoleList(GameCode.getById(gameId), userId);
        logger.info("getRoleList >>> gameId = {}, userId = {}", gameId, userId);
        logger.info("getRoleList >>> gameRoleList size = {}", gameRoleList.size());
        List<RoleVo> roleVoList = new ArrayList<>();
        for (GameRole gameRole : gameRoleList) {
            RoleVo roleVo = new RoleVo();
            String areaCode = gameAreaService.getGameAreaById(gameRole.getAreaId()).getAreaCode();
            roleVo.setAreaCode(areaCode);
            roleVo.setRoleHead(gameRole.getRoleHeadIcon());
            roleVo.setRoleId(gameRole.getRoleId());
            roleVo.setRoleLevel(gameRole.getRoleLevel());
            roleVo.setRoleName(gameRole.getRoleName());
            roleVoList.add(roleVo);
        }
        return roleVoList;
    }

    /**
     * 获取角色信息(如果不存在则临时创建).
     */
    public GameRole getRole(int gameId, long userId, int areaCode, int siteId) throws ServiceException {
        GameArea gameArea = gameAreaService.getGameAreaByCode(gameId, areaCode + "");
        GameRole gameRole = gameRoleService.getRoleByUserId(GameCode.getById(gameId), gameArea.getId(), userId);
        String roleName = "";
        try {
            if (gameRole == null) {
                gameRole = new GameRole();
                if (gameRole.getRoleName() != null) {
                    roleName = URLEncoder.encode(gameRole.getRoleName(), "utf-8");
                }
                gameRole.setAreaCode(areaCode);
                gameRole.setGameId(gameId);
                gameRole.setUserId(userId);
                gameRole.setSiteId(siteId);
                gameRole.setRoleName(roleName);
                gameRole.setAreaId(gameArea.getId());
                gameRole = gameRoleService.createRole(gameRole, GameCode.getById(gameId));
            }
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        return gameRole;
    }

    /**
     * 检查角色名(没有有临时创建)，这里包括角色名修改记录.
     */
    public void checkRoleName(int gameId, int areaCode, int roleId, String roleName) throws ServiceException {
        GameArea gameArea = gameAreaService.getGameAreaByCode(gameId, areaCode + "");
        logger.debug("roleName = " + roleName);
        //先检查角色名是否有已存在.
        GameRole gameRole = gameRoleService.getRoleByName(GameCode.getById(gameId), gameArea.getId(), roleName);
        if (gameRole == null) {
            //角色名可用，开始添加角色名称
            gameRole = gameRoleService.getRoleById(GameCode.getById(gameId), roleId);
            gameRole.setRoleName(roleName);
            gameRoleService.updateRole(gameRole);
        } else {
            throw new GameException(GameException.GAMEROLE_EXIST, "角色名已存在");
        }
    }

    /**
     * 修改角色头像.
     */
    public void changeRoleHead(int gameId, int roleId, String roleHead) throws ServiceException {
        logger.debug("roleHead = " + roleHead);
        GameRole gameRole = gameRoleService.getRoleById(GameCode.getById(gameId), roleId);
        gameRole.setRoleHeadIcon(roleHead);
        gameRoleService.updateRole(gameRole);
    }

    /**
     * 修改角色头像.
     */
    public void recordRoleLevel(int gameId, int roleId, int roleLevel) throws ServiceException {
        logger.debug("roleLevel = " + roleLevel);
        GameRole gameRole = gameRoleService.getRoleById(GameCode.getById(gameId), roleId);
        gameRole.setRoleLevel(roleLevel);
        gameRoleService.updateRole(gameRole);
    }
}
