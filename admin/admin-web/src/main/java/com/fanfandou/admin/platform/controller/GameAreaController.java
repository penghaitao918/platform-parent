package com.fanfandou.admin.platform.controller;


import com.fanfandou.common.entity.ActStatus;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.game.entity.AreaGroup;
import com.fanfandou.platform.api.game.entity.GameArea;

import com.fanfandou.platform.api.game.entity.MaintenanceStatus;
import com.fanfandou.platform.api.game.service.GameAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * author zhongliang.
 * Description:区服管理操作.
 */
@RestController
@RequestMapping(value = "/platform/gameArea")
public class GameAreaController {
    @Autowired
    private GameAreaService gameAreaService;

    /**
     * * 跳转到分类页面
     *
     * @return 分类页面
     */
    @RequestMapping(value = "/gameAreaInit")
    public ModelAndView toListMenu() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/platform/GameAreaList");
        return mav;
    }

    /**
     * 跳转到添加页面
     *
     * @return 添加页面
     */
    @RequestMapping(value = "/gameAreaAddInit")
    public ModelAndView togameAreaAdd() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/platform/GameAreaAdd");
        return mav;
    }


    /**
     * 跳转到添加页面
     *
     * @return 添加页面
     */
    @RequestMapping(value = "/gameAreaEditInit")
    public ModelAndView toGameAreaEdit() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/platform/GameAreaEdit");
        return mav;
    }

    /**
     * 跳转到copy页面
     *
     * @return 添加页面
     */
    @RequestMapping(value = "/gameAreaCopyInit")
    public ModelAndView togameAreaCopy() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/platform/GameAreaCopy");
        return mav;
    }

    /**
     * 跳转到修改页面
     *
     * @return 添加页面
     */
    @RequestMapping(value = "/gameAreaUpdateInit")
    public ModelAndView togameAreaUpdate() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/platform/GameAreaUpdate");
        return mav;
    }

    /**
     * 分页查询
     *
     * @param gameArea 区服对象
     * @param siteId   渠道id
     * @param page     page对象
     * @return 分页对象集合
     * @throws ServiceException
     */
    @ResponseBody
    @RequestMapping("/pageList")
    public PageResult<GameArea> getPageList(GameArea gameArea, Integer siteId, Page page) throws ServiceException {
        return this.gameAreaService.getGameAreasForPage(gameArea.getGameId(), siteId, page);
    }

    /**
     * 区服添加
     *
     * @param gameArea    区服对象
     * @param maintenance 服务器状态
     * @param areaGroupId 大区id
     * @throws ServiceException
     */
    @RequestMapping(value = "/insert")
    public String insert(GameArea gameArea, int maintenance, int areaGroupId) throws ServiceException {
        gameArea.setMaintenanceStatus(MaintenanceStatus.valueOf(maintenance));
        AreaGroup areaGroup = new AreaGroup();
        areaGroup.setId(areaGroupId);
        gameArea.setAreaGroup(areaGroup);
        try {
            this.gameAreaService.createGameArea(gameArea);
        } catch (ServiceException e) {
            e.printStackTrace();
            return "load.region" + e.getId();
        }
        return null;
    }

    /**
     * 批量修改
     *
     * @param batchMaintenanceStatus 服务器状态
     * @param loadStatus             负载状态
     * @param supportVersionMin      游戏版本号下限
     * @param supportVersionMax      游戏版本号上限
     * @param ids                    id集合
     * @throws ServiceException
     */
    @RequestMapping(value = "/batchUpdate")
    public void batchUpdate(int batchMaintenanceStatus, Integer loadStatus, String supportVersionMin, String supportVersionMax, String ids) throws ServiceException {
        GameArea gameArea = new GameArea();
        gameArea.setMaintenanceStatus(MaintenanceStatus.valueOf(batchMaintenanceStatus));
        gameArea.setLoadStatus(loadStatus);
        gameArea.setSupportVersionMin(supportVersionMin);
        gameArea.setSupportVersionMax(supportVersionMax);


        String[] idsList = ids.split(",");
        for (int i = 0; i < idsList.length; i++) {
            gameArea.setId(Integer.parseInt(idsList[i]));
            this.gameAreaService.updateGameAreaById(gameArea);
        }

    }

    /**
     * 区服修改
     *
     * @param gameArea    区服对象
     * @param maintenance 服务器状态
     * @param valueChange 是否有更新
     * @throws ServiceException
     */
    @RequestMapping(value = "/update")
    public String update(GameArea gameArea, int maintenance, Boolean valueChange) throws ServiceException {
        gameArea.setMaintenanceStatus(MaintenanceStatus.valueOf(maintenance));
        if (valueChange) {
            try {
                this.gameAreaService.updateGameAreaById(gameArea);
            } catch (ServiceException e) {
                e.printStackTrace();
                return "load.region" + e.getId();
            }
        }
        return null;
    }

    /**
     * 根据id查.
     *
     * @param id id
     */
    @ResponseBody
    @RequestMapping(value = "/getGameArea/{id}")
    public GameArea getGameAreaById(@PathVariable(value = "id") int id) throws ServiceException {
        GameArea gameArea = gameAreaService.getGameAreaById(id);
        return gameArea;
    }

    /**
     * 根据游戏id查.
     *
     * @param gameId 游戏id
     */
    @ResponseBody
    @RequestMapping(value = "/getGameAreasById")
    public List<GameArea> getGameAreasByGameId(int gameId) throws ServiceException {
        List<GameArea> gameAreas = gameAreaService.getAreasByGameId(gameId);
        return gameAreas;
    }

    /**
     * 根据id删除.
     *
     * @param areaIds
     */
    @ResponseBody
    @RequestMapping(value = "/areaDelete")
    public void areaDelete(String areaIds) throws ServiceException {
        String[] idsList = areaIds.split(",");
        for (int i = 0; i < idsList.length; i++) {
            this.gameAreaService.deleteGameAreaById(Integer.parseInt(idsList[i]));
        }

    }

    /**
     * 根据id集合修改状态
     *
     * @param ids   id集合
     * @param state 状态码
     * @throws ServiceException
     */
    @ResponseBody
    @RequestMapping(value = "/updateState")
    public void itemState(String ids, int state) throws ServiceException {
        String[] idsList = ids.split(",");
        for (int i = 0; i < idsList.length; i++) {
            GameArea gameArea = new GameArea();
            gameArea.setId(Integer.parseInt(idsList[i]));
            gameArea.setValidStatus(ActStatus.valueOf(state));
            this.gameAreaService.updateGameAreaById(gameArea);

        }
    }

    /**
     * 刷新区服缓存
     *
     * @throws Exception
     */
    @RequestMapping(value = "/refreshGameAreaCache")
    public void refreshGameAreaCache() throws Exception {
        this.gameAreaService.refreshGameAreaCache();
    }
}
