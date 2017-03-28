package com.fanfandou.common.entity.result;


import java.io.Serializable;
import java.util.List;

/**
 * Description: 分页列表返回结果.
 * <p/>
 * Date: 2016-04-13 11:34.
 * author: Arvin.
 */
public class PageResult<T> implements Serializable {

    //总数
    private int total;
    //本页数据
    private List<T> rows;
    //分页信息
    private Page page;

    /**
     * 构造方法
     * @param total 总数.
     * @param rows 本页数据
     * @param page 分页信息
     */
    public PageResult(int total, List<T> rows, Page page) {
        this.total = total;
        this.rows = rows;
        this.page = page;
    }

    public PageResult() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
