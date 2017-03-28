package com.fanfandou.admin.system.service;

import com.fanfandou.admin.system.entity.SysLog;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;

import java.util.List;


public interface SysLogService {

    void log(SysLog log);


    int getUserId();

    String getUserName();

    PageResult<SysLog> logList(String loginName,SysLog sysLog, Page page, String from, String to)throws Exception;

    SysLog getSyslogById(int id);
}
