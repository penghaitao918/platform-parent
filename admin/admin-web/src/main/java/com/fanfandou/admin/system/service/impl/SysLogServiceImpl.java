package com.fanfandou.admin.system.service.impl;

import com.fanfandou.admin.system.dao.SysLogMapper;
import com.fanfandou.admin.system.entity.SysLog;
import com.fanfandou.admin.system.entity.User;
import com.fanfandou.admin.system.service.SysLogService;
import com.fanfandou.admin.system.service.UserService;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
 * Created by wangzhenwei on 2016/3/15.
 * Description service实现类
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Autowired
    private UserService userService;

    public void log(SysLog log) {
        sysLogMapper.insert(log);
    }

    public int getUserId() {
        try {
            Subject subject = SecurityUtils.getSubject();
            String loginName = subject.getPrincipal().toString();
            if (loginName.equals("admin")) {
                return 0;
            }
            User user = userService.findUserByLoginName(loginName);
            return user.getId();
        } catch (Exception e) {
            return -1;
        }

    }

    public String getUserName() {
        try {
            Subject subject = SecurityUtils.getSubject();
            String loginName = subject.getPrincipal().toString();
           return loginName;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 获取分页数据
     * 模糊查询，分页，排序.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public PageResult<SysLog> logList(String loginName,SysLog sysLog, Page page, String from, String to) throws Exception{
        if (page.getOrder() == null || page.getOrder().equals("")) {
            page.setOrder("id");
        }
        if (page.getSort() == null || page.getSort().equals("")) {
            page.setSort("desc");
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

        String str = '%' + sysLog.getOperation() + '%';
        String name = '%' + loginName+ '%';
        int num1 = (page.getPageIndex() - 1) * page.getPageSize();
        Map paramMap = new HashMap();
        paramMap.put("operation", str);
        paramMap.put("str1", page.getOrder());
        paramMap.put("str2", page.getSort());
        paramMap.put("num1", num1);
        paramMap.put("num2", page.getPageSize());
        paramMap.put("from", fromDate);
        paramMap.put("to", toDate);
        paramMap.put("loginName",name);
        Map map = new HashMap();
        map.put("operation", str);
        map.put("from", fromDate);
        map.put("to", toDate);
        map.put("loginName",name);
        int totalCount = this.sysLogMapper.totalCount(map);
        page.setTotalCount(totalCount);
        PageResult<SysLog> pageResult = new PageResult<>();
        List<SysLog> list = this.sysLogMapper.pageList(paramMap);
        pageResult.setPage(page);
        pageResult.setRows(list);
        return pageResult;
    }

    public SysLog getSyslogById(int id){
       return this.sysLogMapper.selectById(id);
    }
}
