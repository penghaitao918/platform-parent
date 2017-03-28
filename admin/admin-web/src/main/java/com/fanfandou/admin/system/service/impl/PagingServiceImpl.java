package com.fanfandou.admin.system.service.impl;

import com.fanfandou.admin.system.service.PagingService;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzhenwei on 2016/4/25.
 * Description 分页接口实现类
 */
@Service("pagingService")
public class PagingServiceImpl<T> implements PagingService<T> {

    private PageResult pageResult = new PageResult<T>();

    private Page page = new Page();

    /**
     * 分页方法，返回pageResult.
     *
     * @param pageIndex 当前页
     * @param pageSize  每页显示的信息条数
     * @param list      所有信息的集合
     */
    public PageResult<T> paging(int pageIndex, int pageSize, List<T> list) {

        int totalCount = list.size();
        pageResult.setTotal(totalCount);
        int pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
        page.setPageIndex(this.getPageIndex(pageIndex, pageCount));
        page.setPageSize(this.getPageSize(pageSize));
        page.setTotalCount(totalCount);
        pageResult.setPage(page);


        pageResult.setRows(this.getList(pageIndex, pageSize, list));
        return pageResult;
    }

    /**
     * getPageIndex.
     */
    public int getPageIndex(int pageIndex, int pageCount) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        if (pageIndex > pageCount) {
            pageIndex = pageCount;
        }
        return pageIndex;
    }

    /**
     * getPageSize.
     */
    public int getPageSize(int pageSize) {
        if (pageSize < 1) {
            pageSize = 20;
        }
        return pageSize;
    }

    /**
     * 获取当前页数据.
     */
    public List<T> getList(int pageIndex, int pageSize, List<T> list) {
        List<T> pageList = new ArrayList<T>();
        for (int i = pageSize * (pageIndex - 1); i < list.size(); i++) {
            for (int t = 0; t < pageSize; t++) {
                int m = i + t;
                if (m < list.size()) {
                    pageList.add(list.get(m));
                } else {
                    break;
                }
            }
            break;
        }
        return pageList;
    }
}
