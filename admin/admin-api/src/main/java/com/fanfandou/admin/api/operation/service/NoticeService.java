package com.fanfandou.admin.api.operation.service;

import com.fanfandou.admin.api.operation.entity.Notice;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;

import java.util.List;

/**
 * Created by wangzhenwei on 2016/6/16.
 * Description 角色service接口
 */
public interface NoticeService {


    /**
     * 添加公告.
     */
    void addNotice(Notice notice) throws Exception;

    /**
     * 删除公告.
     */
    void delNotice(List<Integer> idList);

    /**
     * 更新公告.
     */
    void updNotice(Notice stu);

    /**
     * 查询所有公告.
     */
    List<Notice> selectAll();

    /**
     * 从数据库模糊查询出本页数据并排序.
     */
    PageResult<Notice> getPageList(String noticeTitle, String gameId, String siteId, Page page,
                                   String from, String to) throws Exception;

    /**
     * 通过id查找公告.
     */
    Notice selNoticeById(int id);

    /**
     * 失效和开启.
     */
    void invalid(String ids, int state);

    /**
     * 根据gameId,siteId,areaId查找公告.
     *
     * @param gameId     游戏id
     * @param siteId     渠道id
     * @param areaId     区服id
     * @param noticeType 1系统公告 2弹出公告
     * @return 按时间查询出的最新可用的公告
     */
    Notice findByGameSiteAreaId(int gameId, int siteId, int areaId, int noticeType);
}
