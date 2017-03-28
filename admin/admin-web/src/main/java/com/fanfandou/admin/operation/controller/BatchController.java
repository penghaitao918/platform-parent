package com.fanfandou.admin.operation.controller;


import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.game.entity.GameToy;
import com.fanfandou.platform.api.game.entity.GameToyBatch;
import com.fanfandou.platform.api.game.service.GameToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * author zhongliang.
 * Description:玩具管理操作.
 */
@RestController
@RequestMapping(value = "/operation/batch")
public class BatchController {

    @Autowired
    private GameToyService gameToyService;


    /**
     * 分页查询.Todo
     * @param gameToyBatch 玩具批次对象
     * @param page 分页对象
     * @param from 开始时间
     * @param to 结束时间
     * @return page对象
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/pageList")
    public PageResult<GameToyBatch> getPageList(GameToyBatch gameToyBatch, Page page, String from, String to) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date fromDate = formatter.parse(from);
        Date toDate = formatter.parse(to);
        return this.gameToyService.getToyBatches(page, gameToyBatch, fromDate, toDate);
    }

    /**
     * 玩具批量添加
     * @param maxCode 允许最大的玩具code
     * @param gameId 游戏id
     * @param siteId 渠道id
     * @param batchCode 批次号
     * @param itemType 物品类型
     * @param proTime 生产时间
     * @throws ServiceException
     */
    @RequestMapping(value = "/insert")
    public void insert(int maxCode, int gameId, int siteId, String batchCode, int itemType, Date proTime) throws ServiceException {
        this.gameToyService.createBatch(batchCode, maxCode, proTime, itemType, siteId, gameId);
    }


    /**
     * 跳转到分类页面
     *
     * @return 分类页面
     */
    @RequestMapping(value = "/batchInit")
    public ModelAndView toListMenu() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/operation/BatchList");
        return mav;
    }
}
