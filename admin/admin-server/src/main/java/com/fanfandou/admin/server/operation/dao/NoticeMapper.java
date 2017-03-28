package com.fanfandou.admin.server.operation.dao;

import com.fanfandou.admin.api.operation.entity.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzhenwei on 2016/6/16.
 * Description 公告dao
 */
@Repository(value = "noticeMapper")
public interface NoticeMapper {

    List<Notice> selectAll();

    List<Notice> pageList(Map paramMap);

    int totalCount(Map map);

    void insert(Notice notice);

    void delete(int id);

    void update(Notice notice);

    Notice selectById(int id);

    /*按照游戏id，渠道id，区服id进行查询*/
    Notice selByArea(Map idMap);
}
