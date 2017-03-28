package com.fanfandou.admin.platform.controller;


import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.game.entity.AreaGroup;
import com.fanfandou.platform.api.game.entity.EnterAddress;

import com.fanfandou.platform.api.game.service.GameAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


/**
 * author zhongliang.
 * Description:大区区服管理操作.
 */
@RestController
@RequestMapping(value = "/platform/gameRegion")
public class GameRegionController {
    @Autowired
    private GameAreaService gameAreaService;

    /**
     * * 跳转到分类页面
     *
     * @return 分类页面
     */
    @RequestMapping(value = "/gameRegionInit")
    public ModelAndView toList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/platform/GameRegionList");
        return mav;
    }

    /**
     * 跳转到编辑页面
     *
     * @return 添加页面
     */
    @RequestMapping(value = "/gameRegionEditInit")
    public ModelAndView toGameRegionEdit() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/platform/GameRegionEdit");
        return mav;
    }

    /**
     * 分页查询
     *
     * @param areaGroup 大区对象
     * @param page      page对象
     * @return 分页对象集合
     * @throws ServiceException
     */
    @ResponseBody
    @RequestMapping("/pageList")
    public PageResult<AreaGroup> getPageList(AreaGroup areaGroup, Page page) throws ServiceException {
        return this.gameAreaService.getAreaGroupPage(areaGroup.getGameId(), page);
    }

    /**
     * 添加
     *
     * @param areaGroup 大区对象
     * @throws ServiceException
     */
    @RequestMapping(value = "/insert")
    public String insert(AreaGroup areaGroup)  {
        try {
            this.gameAreaService.createAreaGroup(areaGroup);
        } catch (ServiceException e) {
            e.printStackTrace();
            return "load.region"+e.getId();
        }
        return null;
    }

    /**
     * 修改
     *
     * @param areaGroup 大区对象
     * @throws ServiceException
     */
    @RequestMapping(value = "/update")
    public String update(AreaGroup areaGroup) {
        try {
            this.gameAreaService.updateAreaGroupById(areaGroup);
        } catch (ServiceException e) {
            e.printStackTrace();
            return "load.region"+e.getId();
        }
        return null;
    }

    /**
     * 根据id查.
     *
     * @param id id
     */
    @ResponseBody
    @RequestMapping(value = "/getGameRegion/{id}")
    public AreaGroup getGameRegionById(@PathVariable(value = "id") int id) throws ServiceException {
        AreaGroup areaGroup = gameAreaService.getAreaGroupById(id);
        return areaGroup;
    }

    /**
     * 根据id查.
     *
     * @param gameId 游戏id
     */
    @ResponseBody
    @RequestMapping(value = "/getGameRegionsByGameId")
    public List<AreaGroup> getGameAreasByGameId(int gameId) throws ServiceException {
        List<AreaGroup> areaGroups = gameAreaService.getAreaGroupByGameId(gameId);
        return areaGroups;
    }

    /**
     * 根据id删除.
     *
     * @param regionIds 大区id集合
     */
    @ResponseBody
    @RequestMapping(value = "/regionDelete")
    public String areaDelete(String regionIds) {
        try {
            this.gameAreaService.deleteAreaGroupById(regionIds);
        } catch (ServiceException e) {
            e.printStackTrace();
          return "load.region"+e.getId();
        }
        return null;
    }

}
