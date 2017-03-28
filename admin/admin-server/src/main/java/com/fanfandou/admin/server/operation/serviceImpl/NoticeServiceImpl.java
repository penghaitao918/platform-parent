package com.fanfandou.admin.server.operation.serviceImpl;

import com.fanfandou.admin.api.operation.entity.Notice;
import com.fanfandou.admin.api.operation.service.NoticeService;
import com.fanfandou.admin.server.operation.dao.NoticeMapper;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzhenwei on 2016/6/16.
 * Description service实现类
 */
@Service("noticeService")
public class NoticeServiceImpl extends BaseLogger implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;


    /**
     * 查询所有公告
     */
    public List<Notice> selectAll() {
        return this.noticeMapper.selectAll();
    }

    /**
     * 获取分页数据
     * 模糊查询，分页，排序
     */
    public PageResult<Notice> getPageList(String noticeTitleStr, String gameIds, String siteIdss, Page page, String from, String to) throws Exception {
        if (page.getOrder() == null|| page.getOrder().equals("")) {
            page.setOrder("id");
        }
        if (page.getSort() == null|| page.getSort().equals("")) {
            page.setSort("desc");
        }
        if (gameIds .equals("")) {
            gameIds = "1";
        }
        if (siteIdss .equals("")) {
            siteIdss = "%%";
        }
        if (noticeTitleStr == null || noticeTitleStr.equals("")) {
            noticeTitleStr = "%%";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = null;
        Date toDate = null;
        if (from == null) {
            from = "1900-01-01 00:00:00";
            fromDate = sdf.parse(from);
        } else {
            fromDate = sdf.parse(from);
        }

        if (to == null) {
            toDate = new Date();
        } else {
            toDate = sdf.parse(to);
        }

        String noticeTitle = '%' + noticeTitleStr + '%';
        String siteIds = "%" + siteIdss + ",%";
        int num1 = (page.getPageIndex() - 1) * page.getPageSize();

        Map paramMap = new HashMap();
        paramMap.put("noticeTitle", noticeTitle);
        paramMap.put("gameIds", gameIds);
        paramMap.put("siteIds", siteIds);
        paramMap.put("str1", page.getOrder());
        paramMap.put("str2", page.getSort());
        paramMap.put("num1", num1);
        paramMap.put("num2", page.getPageSize());
        paramMap.put("from", fromDate);
        paramMap.put("to", toDate);
        List<Notice> noticeList = this.noticeMapper.pageList(paramMap);

        Map map = new HashMap();
        map.put("noticeTitle", noticeTitle);
        map.put("gameIds", gameIds);
        map.put("siteIds", siteIds);
        map.put("from", fromDate);
        map.put("to", toDate);
        int totalCount = this.noticeMapper.totalCount(map);

        page.setTotalCount(totalCount);
        PageResult<Notice> pageResult = new PageResult<>();
        pageResult.setPage(page);
        pageResult.setRows(noticeList);
        return pageResult;
    }

    /**
     * 添加公告.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addNotice(Notice notice) throws Exception{
        String str = notice.getSiteIds();
        String siteIds = ',' + str;
        notice.setSiteIds(siteIds);
        String str2 = notice.getAreaIds();
        String areaIds = ',' + str2;
        notice.setAreaIds(areaIds);
        notice.setCreateTime(new Date());
//        Subject subject = SecurityUtils.getSubject();
//        String publisher = subject.getPrincipal().toString();
//        notice.setPublisher(publisher);
        notice.setNoticeState(1);


        this.noticeMapper.insert(notice);
    }

    /**
     * 删除公告
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void delNotice(List<Integer> idList) {
        for (int i = 0; i < idList.size(); i++) {
            int id = idList.get(i);
            this.noticeMapper.delete(id);
        }
    }

    /**
     * 更新公告.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void updNotice(Notice notice) {
        String str = notice.getSiteIds();
        String siteIds = ',' + str;
        String str2 = notice.getAreaIds();
        String areaIds = ',' + str2;
        notice.setSiteIds(siteIds);
        notice.setAreaIds(areaIds);
        this.noticeMapper.update(notice);
    }

    /**
     * 通过id查找公告
     */
    public Notice selNoticeById(int id) {
        return this.noticeMapper.selectById(id);
    }

    /**
     * 开启或关闭
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void invalid(String ids, int state) {
        String noticeIds[] = ids.split(",");
        for (String id : noticeIds) {
            Notice notice = this.selNoticeById(Integer.parseInt(id));
            if (state == 0 || state == 1) {
                notice.setNoticeState(state);
            }
            this.updNotice(notice);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Notice findByGameSiteAreaId(int gameId, int siteId, int areaId, int noticeType) {
        Map idMap = new HashMap();
        idMap.put("gameIds", gameId);
        String siteIds = "%," + siteId + ",%";
        idMap.put("siteIds", siteIds);
        String areaIds="";
        if(areaId==0){
            areaIds=null;
        }else {
             areaIds = "%," + areaId + ",%";
        }

        idMap.put("areaIds", areaIds);
        /*if(noticeType==1|noticeType==2){
         idMap.put("noticeType",noticeType);
        }*/
        idMap.put("noticeType", noticeType);
        return noticeMapper.selByArea(idMap);
    }
}
