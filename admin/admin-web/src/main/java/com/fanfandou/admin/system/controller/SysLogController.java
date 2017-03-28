package com.fanfandou.admin.system.controller;
import com.fanfandou.admin.system.entity.SysLog;
import com.fanfandou.admin.system.service.SysLogService;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * author zhongliang.
 * Description:自定义表单.
 */
@RestController
@RequestMapping(value = "/syslog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 跳转到自定义表单功能页
     *
     * @return 功能页面
     */
    @RequestMapping(value = "/syslogInit")
    public ModelAndView toListCustom() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/system/SysLog");
        return mav;
    }


    /**
     * 分页查询
     *
     * @param page     分页对象
     * @return  page对象
     */
    @ResponseBody
    @RequestMapping("/pageList")
    public PageResult<SysLog> getPageList(String loginName,SysLog sysLog,Page page, String from, String to) throws Exception {
        return this.sysLogService.logList(loginName,sysLog, page,from,to);
    }

    /**
     * 根据id查.
     *
     * @param id userId
     */
    @ResponseBody
    @RequestMapping(value = "/getSyslog/{id}")
    public SysLog edit(@PathVariable(value = "id") int id) {
        return sysLogService.getSyslogById(id);
    }



}
