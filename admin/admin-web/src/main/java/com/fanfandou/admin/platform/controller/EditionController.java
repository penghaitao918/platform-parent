package com.fanfandou.admin.platform.controller;

import com.fanfandou.admin.api.operation.entity.DeviceType;
import com.fanfandou.admin.api.operation.entity.GamePatch;
import com.fanfandou.admin.api.operation.service.PatchService;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.ValidStatus;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * author zhongliang.
 * Description:版本补丁.
 */
@RestController
@RequestMapping(value = "/platform/edition")
public class EditionController extends BaseLogger {

    @Autowired
    private PatchService patchService;

    /**
     * 跳转到edition List页面
     *
     * @return list页面
     */
    @RequestMapping(value = "/editionInit")
    public ModelAndView toEditionList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/platform/EditionList");
        return mav;
    }

    /**
     * 跳转到edition List页面
     *
     * @return list页面
     */
    @RequestMapping(value = "/editionEditInit")
    public ModelAndView toArticleList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/platform/EditionEdit");
        return mav;
    }


    /**
     * 版本补丁添加
     *
     * @param gamePatch       补丁对象
     * @param deviceTypeView  设备
     * @param whiteStatusView 是否开启白名单
     * @param validStatusView 是否生效
     * @throws ServiceException
     */
    @RequestMapping(value = "/insert")
    public String insert(GamePatch gamePatch, int deviceTypeView, int whiteStatusView, int validStatusView) throws ServiceException {
        gamePatch.setValidStatus(ValidStatus.valueOf(validStatusView));
        gamePatch.setDeviceType(DeviceType.valueOf(deviceTypeView));
        gamePatch.setWhiteStatus(ValidStatus.valueOf(whiteStatusView));
        this.patchService.createGamePatch(gamePatch);
        return null;
    }

    /**
     * 分页查询
     *
     * @param gameId 游戏id
     * @param siteId 渠道id
     * @param page   page对象
     * @return 分页对象集合
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/pageList")
    public PageResult<GamePatch> getPageList(int gameId, int siteId, Page page) throws Exception {
        return this.patchService.getPageList(gameId, siteId, page);
    }


    /**
     * 根据id删除.
     *
     * @param editionIds id集合
     */
    @ResponseBody
    @RequestMapping(value = "/editionDelete")
    public void delete(String editionIds) throws ServiceException {
        this.patchService.delPatch(editionIds);
    }


    /**
     * 根据id查询版本补丁
     * @param id id.
     * @return 版本对象
     * @throws ServiceException
     */
    @ResponseBody
    @RequestMapping(value = "/editionSelectById")
    public GamePatch selectById(int id) throws ServiceException {
        return this.patchService.selectById(id);
    }

    /**
     * 版本补丁修改
     *
     * @param gamePatch       补丁对象
     * @param deviceTypeView  设备
     * @param whiteStatusView 是否开启白名单
     * @param validStatusView 是否生效
     * @throws ServiceException
     */
    @RequestMapping(value = "/update")
    public void update(GamePatch gamePatch, int deviceTypeView, int whiteStatusView, int validStatusView) throws ServiceException {
        gamePatch.setValidStatus(ValidStatus.valueOf(validStatusView));
        gamePatch.setDeviceType(DeviceType.valueOf(deviceTypeView));
        gamePatch.setWhiteStatus(ValidStatus.valueOf(whiteStatusView));
       this.patchService.updateGamePatch(gamePatch);
    }
}
