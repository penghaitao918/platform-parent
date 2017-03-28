package com.fanfandou.admin.system.service.impl;

import com.fanfandou.admin.api.exception.AdminException;
import com.fanfandou.admin.system.dao.UserMapper;
import com.fanfandou.admin.system.entity.User;
import com.fanfandou.admin.system.service.UserService;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.service.cache.CacheService;
import com.fanfandou.common.util.CipherUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzhenwei on 2016/3/15.
 * Description service实现类
 */
@Service("userService")
public class UserServiceImpl extends BaseLogger implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired(required = true)
    private CacheService cacheService;

    /**
     * 查询所有用户.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<User> selectAll() {
        return this.userMapper.selectAll();
    }

    /**
     * 添加用户.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addUser(User user) throws ServiceException {
        Date now = new Date();
        user.setCreateTime(now);
        String passWord = user.getPassword();
        String salt = now.toString();
        user.setSalt(salt);
        String miPassWord = CipherUtils.getPasswd(passWord, salt);
        user.setPassword(miPassWord);
        user.setLocked(1);
        String userName = user.getLoginName();
        User user1 = this.findUserByLoginName(userName);
        if (user1 == null) {
            this.userMapper.insert(user);
        } else {
            throw new ServiceException(AdminException.ADMIN_WRONG_LOGINNAME, "该登录名已被注册");
        }
    }

    /**
     * 删除用户.
     *
     * @param idList 批量删除的id集合
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void delUser(List<Integer> idList) {
        for (int i = 0; i < idList.size(); i++) {
            int id = idList.get(i);
            this.userMapper.delete(id);
        }
    }

    /**
     * 更新用户信息.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void updUser(User user) throws ServiceException {
        User userOld = this.selUserById(user.getId());
        if (user.getPassword() == null) {
            user.setPassword(userOld.getPassword());
        }
        if (user.getPassword().length() < 30) {
            String miPwd = CipherUtils.getPasswd(user.getPassword(), userOld.getSalt());
            user.setPassword(miPwd);
        } else {
            user.setPassword(userOld.getPassword());
        }
        user.setCreateTime(userOld.getCreateTime());
        user.setCount(userOld.getCount());
        user.setLoginIp(userOld.getLoginIp());
        //判断登录名是否重复
        String userName = user.getLoginName();
        if (!userName.equals(userOld.getLoginName())) {
            User user1 = this.findUserByLoginName(userName);
            if (user1 != null) {
                throw new ServiceException(AdminException.ADMIN_WRONG_LOGINNAME, "该登录名已被注册");
            }
        }

        if (user.getLoginTime() == null) {
            user.setLoginTime(userOld.getLoginTime());
        }
        user.setSalt(userOld.getSalt());
        if (user.getLockReason() == null && userOld.getLockReason() != null) {
            user.setLockReason(userOld.getLockReason());
        }
        this.userMapper.update(user);
    }

    /**
     * 通过id查找用户.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public User selUserById(int id) {
        return this.userMapper.selectById(id);
    }

    /**
     * 通过loginName找User.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public User findUserByLoginName(String loginName) {
        return this.userMapper.selectByLoginName(loginName);
    }

    /**
     * 获取分页数据
     * 模糊查询，分页，排序.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public PageResult<User> getPageList(String userName, Page page) {
        if (page.getOrder() == null || page.getOrder().equals("")) {
            page.setOrder("id");
        }
        if (page.getSort() == null || page.getSort().equals("")) {
            page.setSort("desc");
        }
        String str = '%' + userName + '%';
        int num1 = (page.getPageIndex() - 1) * page.getPageSize();
        Map paramMap = new HashMap();
        paramMap.put("loginName", str);
        paramMap.put("str1", page.getOrder());
        paramMap.put("str2", page.getSort());
        paramMap.put("num1", num1);
        paramMap.put("num2", page.getPageSize());

        List<User> list = this.userMapper.pageList(paramMap);
        int totalCount = this.userMapper.totalCount();
        page.setTotalCount(totalCount);
        PageResult<User> pageResult = new PageResult<>();
        pageResult.setPage(page);
        pageResult.setRows(list);
        return pageResult;
    }


    /**
     * 一键失效和开启.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void invalid(String ids, int state) throws ServiceException {
        String[] userIds = ids.split(",");
        for (String id : userIds) {
            User user = this.selUserById(Integer.parseInt(id));
            if (state == 0 || state == 1) {
                user.setLocked(state);
            }
            this.updUser(user);
        }
    }

    /**
     * 密码验证.
     */
    public boolean pwdValidate(int userId, String pwd) {
        User user = selUserById(userId);
        String salt = user.getSalt();
        String miPwd = CipherUtils.getPasswd(pwd, salt);
        String passWord = user.getPassword();
        if (miPwd.equals(passWord)) {
            return true;
        }
        return false;
    }

    /**
     * 修改密码.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void updUserPwd(int userId, String pwd) throws ServiceException {
        User user = selUserById(userId);
        String salt = user.getSalt();
        String miPwd = CipherUtils.getPasswd(pwd, salt);
        user.setPassword(miPwd);
        updUser(user);
    }

    /**
     * 用户习惯
     *
     * @param key   * @param value
     * @param value
     * @throws ServiceException
     */
    @Override
    public void userHabitAdd(String key, String value) throws ServiceException {
        cacheService.put(key, value);
    }

    @Override
    public String userHabitQuery(String key) throws ServiceException {
        return cacheService.get(key, String.class);
    }

}
