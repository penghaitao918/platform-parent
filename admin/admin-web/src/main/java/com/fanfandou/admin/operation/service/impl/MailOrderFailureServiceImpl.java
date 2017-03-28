package com.fanfandou.admin.operation.service.impl;


import com.fanfandou.admin.operation.dao.MailOrderFailureMapper;
import com.fanfandou.admin.operation.entity.MailOrder;
import com.fanfandou.admin.operation.entity.MailOrderFailure;
import com.fanfandou.admin.operation.service.MailOrderFailureService;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhongliang on 2016/9/27.
 * Description 游戏物品类型service实现类
 */
@Service("mailOrderFailureService")
public class MailOrderFailureServiceImpl extends BaseLogger implements MailOrderFailureService {

    @Autowired
    private MailOrderFailureMapper mailOrderFailureMapper;

    /**
     * 新增
     *
     * @param mailOrderFailure 对象
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addMailOrderFailure(MailOrderFailure mailOrderFailure) {
        mailOrderFailure.setCreateTime(new Date());
        this.mailOrderFailureMapper.insert(mailOrderFailure);
    }

    /**
     * 删除
     *
     * @param idList id集合
     * @throws ServiceException
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void delMailOrderFailure(List<Integer> idList) throws ServiceException {
        for (int i = 0; i < idList.size(); i++) {
            int id = idList.get(i);
            this.mailOrderFailureMapper.delete(id);
        }
    }

    /**
     * 修改
     *
     * @param mailOrderFailure 对象
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void updMailOrderFailure(MailOrderFailure mailOrderFailure) {
        this.mailOrderFailureMapper.update(mailOrderFailure);
    }

    @Override
    public List<MailOrderFailure> selectAll() {
        return this.mailOrderFailureMapper.selectAll();
    }

    @Override
    public MailOrderFailure selMailOrderFailureById(int id) {
        return this.mailOrderFailureMapper.selectById(id);
    }

    /**
     * 获取分页数据
     * 模糊查询，分页，排序.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public PageResult<MailOrderFailure> getPageList(String name, Page page, String from, String to) throws Exception {
        if (page.getOrder() == null || page.getOrder().equals("")) {
            page.setOrder("id");
        }
        if (page.getSort() == null || page.getSort().equals("")) {
            page.setSort("desc");
        }
        if (name == null || name.equals("")) {
            name = "%%";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate;
        Date toDate;
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
        String str = '%' + name + '%';
        int num1 = (page.getPageIndex() - 1) * page.getPageSize();
        Map paramMap = new HashMap();
        paramMap.put("mailTitle", str);
        paramMap.put("str1", page.getOrder());
        paramMap.put("str2", page.getSort());
        paramMap.put("from", fromDate);
        paramMap.put("to", toDate);
        paramMap.put("num1", num1);
        paramMap.put("num2", page.getPageSize());

        Map map = new HashMap();
        map.put("menuName", str);
        map.put("from", fromDate);
        map.put("to", toDate);
        int totalCount = this.mailOrderFailureMapper.totalCount(map);
        page.setTotalCount(totalCount);
        PageResult<MailOrderFailure> pageResult = new PageResult<>();
        List<MailOrderFailure> list = this.mailOrderFailureMapper.pageList(paramMap);
        pageResult.setPage(page);
        pageResult.setRows(list);
        return pageResult;
    }

}
