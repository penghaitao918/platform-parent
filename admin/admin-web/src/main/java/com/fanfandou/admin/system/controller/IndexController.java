package com.fanfandou.admin.system.controller;

import com.fanfandou.admin.system.entity.User;
import com.fanfandou.admin.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description:跳转至index.
 * author:wangzhenwei.
 * time:2016/3/21 20:59
 */
@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    /**
     * 跳转index.
     */
    @RequestMapping("/index")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        /*Subject subject = SecurityUtils.getSubject();
        User user = userService.findUserByLoginName(subject.getPrincipal().toString());
        String languages = user.getLanguage();
        mv.addObject(user);*/
        return mv;
    }

    @RequestMapping("/welcome")
    public ModelAndView welcome() {
        return new ModelAndView("welcome");
    }
}
