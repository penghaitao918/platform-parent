package com.fanfandou.common.entity.result;

import java.io.Serializable;

/**
 * Description: 分页对象.
 * <p/>
 * Date: 2016-04-13 15:02.
 * author: Arvin.
 */
public class Page implements Serializable {
    public static final int DEFAULT_PAGE_SIZE = 20;
    // 页码,从1开始
    private int pageIndex;
    // 每页多少行
    private int pageSize = DEFAULT_PAGE_SIZE;
    // 数据总行数
    private int totalCount = 0;
    // 总共可以分多少页
    private int pageCount;
    //排序方式 desc asc
    private String sort;
    //排序字段
    private String order;

    /**
     * 分页信息,默认每页20行数据.
     *
     * @param pageIndex 页码,从1开始
     */
    public Page(int pageIndex) {
        this(pageIndex, DEFAULT_PAGE_SIZE);
    }

    public Page() {

    }

    /**
     * 分页信息.
     *
     * @param pageIndex 页码,从1开始
     * @param pageSize  每页多少行,默认为 20
     */
    public Page(int pageIndex, int pageSize) {
        if (pageIndex < 1)
            pageIndex = 1;
        if (pageSize < 1)
            pageSize = DEFAULT_PAGE_SIZE;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }


    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * 获取当前页页码.
     *
     */
    public int getPageIndex() {
        if (pageIndex < 1)
            pageIndex = 1;
        if (pageSize < 1)
            pageSize = DEFAULT_PAGE_SIZE;
        return pageIndex;
    }

    /**
     * 获取每页多少行.
     *
     */
    public int getPageSize() {
        if (pageSize == 0)
            pageSize = DEFAULT_PAGE_SIZE;
        return pageSize;
    }

    /**
     * 获取总共有多少页.
     *
     */
    public int getPageCount() {

        return pageCount;
    }

    /**
     * 获取起始行数.
     *
     */
    public int getFirstResult() {
        return (pageIndex - 1) * pageSize;
    }

    /**
     * 获取总记录数.
     *
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 设置总记录数.
     *
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        pageCount = totalCount / pageSize
                + (totalCount % pageSize == 0 ? 0 : 1);
        /*
		// 调整页码信息,防止出界
		if (totalCount == 0) {
			if (pageIndex != 1)
				pageIndex = 1;
		} else {
			if (pageIndex > pageCount)
				pageIndex = pageCount;
		}
		*/
    }


    /**
     * 是否有数据.
     *
     */
    public boolean isEmpty() {
        return totalCount == 0;
    }

    /**
     * 设置页面大小.
     *
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 排序方式 desc asc.
     *
     * @return String
     */
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * 排序字段  例如 id.
     *
     * @return String
     */
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }


    /**
     * 是否有上一页.
     *
     * @return boolean
     */
    public boolean getHasPrev() {
        return getPageIndex() > 1;

    }


    /**
     * 是否有下一页.
     *
     * @return boolean
     */
    public boolean getHasNext() {
        return pageIndex <= (pageCount - 1);
    }


}
