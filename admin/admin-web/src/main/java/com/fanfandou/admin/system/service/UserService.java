package com.fanfandou.admin.system.service;

import com.fanfandou.admin.system.entity.User;
import com.fanfandou.admin.util.Log;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;

import java.util.List;

/**
 * Created by wangzhenwei on 2016/3/15.
 * Description 用户service接口
 */
public interface UserService {

    /**
     * 添加用户.
     */
    void addUser(User user) throws ServiceException;

    /**
     * 删除用户.
     */
    void delUser(List<Integer> idList);

    /**
     * 更新用户信息.
     */
    @Log
    void updUser(User stu) throws ServiceException;

    /**
     * 查询所有用户.
     */
    List<User> selectAll();

    /**
     * 通过id查找用户.
     */
    User selUserById(int id);

    /**
     * 通过loginName查找用户.
     */
    User findUserByLoginName(String loginName);

    /**
     * 从数据库模糊查询出本页数据并排序.
     */
    PageResult<User> getPageList(String roleName, Page page);

    /**
     * 一键解锁，锁定.
     * @param ids id集合
     * @param state 状态
     */
    void invalid(String ids,int state) throws ServiceException;

    /**
     * 密码验证.
     * @param userId 用户id
     * @param pwd 原密码
     */
    boolean pwdValidate(int userId, String pwd);

    /**
     * 修改密码.
     * @param pwd 新密码
     */
    void updUserPwd(int userId,String pwd) throws ServiceException;

    /**
     * 用户习惯
     * @param key
     * * @param value
     * @throws ServiceException
     */
    void userHabitAdd(String key,String value) throws ServiceException;

    /**
     * 获取用户习惯
     * @param key
     * @return
     * @throws ServiceException
     */
    String userHabitQuery(String key) throws ServiceException;
}
