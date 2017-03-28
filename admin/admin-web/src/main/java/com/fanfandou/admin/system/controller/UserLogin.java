package com.fanfandou.admin.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wangzhenwei on 2016/3/28.
 * Description 登录页面
 */
@RestController
@RequestMapping("/system")
public class UserLogin {
    /**
     * 跳转登录页.
     */
    @RequestMapping(value = "/userLogin")
    public ModelAndView permissionList() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/Login");
        mv.addObject("error","login");
        return mv;
    }
}
