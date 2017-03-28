package com.fanfandou.admin.operation.controller;


import com.fanfandou.common.entity.ValidStatus;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.activity.entity.GameActivity;
import com.fanfandou.platform.api.activity.service.GameActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * author zhongliang.
 * Description:活动开关管理操作.
 */
@RestController
@RequestMapping(value = "/operation/gameActivity")
public class GameActivityController {

    @Autowired
    private GameActivityService gameActivityService;

    /**
     * 跳转到公告List页面
     *
     * @return 分类页面
     */
    @RequestMapping(value = "/gameActivityInit")
    public ModelAndView toList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/operation/GameActivityList");
        return mav;
    }

    /**
     * 跳转到公告List页面
     *
     * @return 分类页面
     */
    @RequestMapping(value = "/gameActivityEdit")
    public ModelAndView toEdit() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/operation/GameActivityEdit");
        return mav;
    }


    /**
     * 分页
     *
     * @param siteId 渠道id
     * @param gameId 游戏id
     * @param page   分页对象
     * @param from   开始时间
     * @param to     结束时间
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/pageList")
    public PageResult getPageList(Integer siteId, Integer gameId, Page page, String from, String to) throws Exception {
        return this.gameActivityService.getGameActivityList(gameId, siteId, 0, page);
    }

    /**
     * 根据id查询公告
     *
     * @param activityId 活动id
     * @return 公告对象
     * @throws ServiceException
     */
    @RequestMapping(value = "/gameActivityGetById")
    public GameActivity getNoticeById(Integer activityId) throws ServiceException {
        return this.gameActivityService.queryGameActivity(activityId);
    }

    /**
     * 修改
     *
     * @param gameActivity 活动对象
     * @throws ServiceException
     */
    @RequestMapping(value = "/gameActivityUpdate")
    public String update(GameActivity gameActivity, int validStatusCheck) throws ServiceException {
        try {
            gameActivity.setValidStatus(ValidStatus.valueOf(validStatusCheck));
            this.gameActivityService.updateActivity(gameActivity);
        } catch (ServiceException e) {
            e.printStackTrace();
            return "load.gameActivity" + e.getId();
        }
        return null;

    }

    /**
     * 添加
     *
     * @param gameActivity 活动对象
     * @throws ServiceException
     */
    @RequestMapping(value = "/gameActivityAdd")
    public String add(GameActivity gameActivity, int validStatusCheck) throws ServiceException {
        try {
            gameActivity.setValidStatus(ValidStatus.valueOf(validStatusCheck));
            this.gameActivityService.createActivity(gameActivity);
        } catch (ServiceException e) {
            e.printStackTrace();
            return "load.gameActivity" + e.getId();
        }
        return null;

    }

    /**
     * 活动开关
     *
     * @param ids   id集合
     * @param state 1代表开，2代表关
     * @throws ServiceException
     */
    @RequestMapping(value = "/updateState")
    public void updateState(String ids, int state) throws ServiceException {
        String[] idData = ids.split(",");
        for (int i = 0; i < idData.length; i++) {
            GameActivity gameActivity = new GameActivity();
            gameActivity.setId(Long.parseLong(idData[i]));
            gameActivity.setValidStatus(ValidStatus.valueOf(state));
            this.gameActivityService.turnOffActivity(gameActivity);
        }

    }


}
