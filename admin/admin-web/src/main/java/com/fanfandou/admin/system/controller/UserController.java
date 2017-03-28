package com.fanfandou.admin.system.controller;

import com.fanfandou.admin.system.entity.User;
import com.fanfandou.admin.system.service.SysLogService;
import com.fanfandou.admin.system.service.UserService;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.constant.PublicCachedKeyConstant;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangzhenwei on 2016/3/15.
 * Description 用户controller.
 */
@RestController
@RequestMapping("/system")
public class UserController extends BaseLogger {
    @Autowired
    private UserService userService;

    @Autowired
    private SysLogService sysLogService;

    /**
     * 显示角色列表.
     */
    @RequestMapping(value = "/user/userInit")
    public ModelAndView userList() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/UserList");
        return mv;
    }

    /**
     * 首页
     */
    @RequestMapping(value = "/HomePage")
    public ModelAndView homePage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("HomePage");
        HttpSession session = request.getSession();
        session.setAttribute("login", "homePage");
        return mv;
    }


    /**
     * 显示角色列表.
     */
    @RequestMapping(value = "/user/accreditInit")
    public ModelAndView accredit() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/Accredit");
        return mv;
    }

    /**
     * 分页查询.
     *
     * @param loginName 用户名
     * @param page      page对象
     * @return 分页对象集合
     */
    @ResponseBody
    @RequestMapping("/user/pageList")
    public PageResult<User> getPageList(String loginName, Page page) {
        return this.userService.getPageList(loginName, page);
    }


    /**
     * 添加.
     */
    @ResponseBody
    @RequestMapping("/user/addUser")
    public String addUser(User user) {
        try {
            this.userService.addUser(user);
        } catch (ServiceException e) {
            e.printStackTrace();
            return "load.user" + e.getId();
        }
        return null;
    }

    /**
     * 根据id修改状态.
     *
     * @param ids state
     */
    @RequestMapping(value = "/user/updateState")
    public void menuState(String ids, int state) throws ServiceException {
        this.userService.invalid(ids, state);
    }

    /**
     * 删.
     *
     * @param userId id
     */
    @RequestMapping("/user/delUse/{userId}")
    @ResponseBody
    public List<User> delUser(@PathVariable(value = "userId") String userId) {
        String[] ids = userId.split(",");
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            int id = Integer.parseInt(ids[i]);
            idList.add(id);
        }
        this.userService.delUser(idList);
        return this.userService.selectAll();
    }

    /**
     * 修改.
     *
     * @param user 用户对象
     * @return 用户集合
     */
    @ResponseBody
    @RequestMapping(value = "/user/updateUser", method = RequestMethod.POST)
    public String editUser(User user) {
        try {
            this.userService.updUser(user);
        } catch (ServiceException e) {
            e.printStackTrace();
            return "load.user" + e.getId();
        }
        return null;

    }

    /**
     * 根据id查.
     *
     * @param id userId
     */
    @ResponseBody
    @RequestMapping(value = "/user/editUser/{id}")
    public User edit(@PathVariable(value = "id") int id) {
        return userService.selUserById(id);
    }

    /**
     * 用户的资料详情页，修改.
     */
    @RequestMapping(value = "/user/profileInit")
    public ModelAndView profile() {
        ModelAndView mav = new ModelAndView("system/Profile");
        return mav;
    }

    /**
     * 根据name查.
     */
    @ResponseBody
    @RequestMapping(value = "/user/profileShow")
    public User getUserByName() {
        Subject currentUser = SecurityUtils.getSubject();
        User user = userService.findUserByLoginName(currentUser.getPrincipal().toString());
        return user;
    }


    /**
     * 登录.
     *
     * @param loginName 登录名
     * @param password  密码
     */
    @RequestMapping(value = "/login")
    public ModelAndView isLogin(@RequestParam(value = "loginName") String loginName,
                                @RequestParam(value = "password") String password, HttpServletRequest request) throws ServiceException {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
        token.setRememberMe(true);
        try {
            subject.login(token);
            User user = userService.findUserByLoginName(subject.getPrincipal().toString());
            if (user != null) {
                user.setCount(user.getCount() + 1);
                /*user.setLoginIp();*/
                user.setLoginTime(new Date());
                this.userService.updUser(user);
            }
            HttpSession session = request.getSession();
            session.setAttribute("login", "login");
            session.setAttribute("habit", this.userHabitQuery());
            //获取modelandview对象
            ModelAndView view = new ModelAndView();
            view.setViewName("redirect:/index");
            return view;
        } catch (AuthenticationException e) {
            logger.debug("登录失败错误信息:" + e);
            token.clear();
            //获取modelandview对象
            ModelAndView view = new ModelAndView();
            view.setViewName("/system/Login");
            view.addObject("error", "error");
            return view;
        }
    }

    /**
     * 登出.
     */
    @RequestMapping(value = "/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            if (logger.isDebugEnabled()) {
                logger.debug("用户退出登录");
            }
        }
        return "redirect:/index";
    }

    /**
     * 验证密码
     *
     * @param userId      用户id
     * @param newPassword 用户密码
     * @return 用户集合
     */
    @ResponseBody
    @RequestMapping(value = "/user/pwdValidate", method = RequestMethod.POST)
    public boolean pwdValidate(int userId, String newPassword) {
        return userService.pwdValidate(userId, newPassword);
    }

    /**
     * 修改.
     *
     * @param userId      用户id
     * @param newPassword 用户密码
     * @return 用户集合
     */
    @ResponseBody
    @RequestMapping(value = "/user/updUserPwd", method = RequestMethod.POST)
    public void updUserPwd(int userId, String newPassword) throws ServiceException {
        userService.updUserPwd(userId, newPassword);
    }

    /**
     * 更新用户习惯
     *
     * @return 用户集合
     */
    @ResponseBody
    @RequestMapping(value = "/user/habitEdit", method = RequestMethod.POST)
    public void userHabitEdit(String value) throws ServiceException {
        userService.userHabitAdd(PublicCachedKeyConstant.USER_HABIT+sysLogService.getUserId(), value);
    }

    /**
     * 获取用户习惯
     *
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/user/habitQuery", method = RequestMethod.POST)
    public String userHabitQuery() throws ServiceException {
        return userService.userHabitQuery(PublicCachedKeyConstant.USER_HABIT + sysLogService.getUserId());
    }

    /**
     * 更新用户习惯
     *
     * @return 用户集合
     */
    @ResponseBody
    @RequestMapping(value = "/user/habitUrlEdit", method = RequestMethod.POST)
    public void userUrlHabitEdit(String value) throws ServiceException {
        userService.userHabitAdd(PublicCachedKeyConstant.USER_HABIT_URL+sysLogService.getUserId(), value);
    }

    /**
     * 获取用户习惯
     *
     * @return
     * @throws ServiceException
     */
    @ResponseBody
    @RequestMapping(value = "/user/habitUrlQuery", method = RequestMethod.POST)
    public String userUrlHabitQuery() throws ServiceException {
        return userService.userHabitQuery(PublicCachedKeyConstant.USER_HABIT_URL + sysLogService.getUserId());
    }

}