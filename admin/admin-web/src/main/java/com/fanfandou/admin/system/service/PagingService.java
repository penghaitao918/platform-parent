package com.fanfandou.admin.system.service;

import com.fanfandou.common.entity.result.PageResult;

import java.util.List;

/**
 * Created by wangzhenwei on 2016/4/25.
 * Description 分页
 */
public interface PagingService<T> {
    PageResult<T> paging(int pageIndex, int totalCount, List<T> list);
}
