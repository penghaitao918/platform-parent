package com.fanfandou.admin.system.dao;

import com.fanfandou.admin.system.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzhenwei on 2016/3/19.
 * Description UserMapper
 */
public interface UserMapper {

    List<User> selectAll();

    void insert(User user);

    void delete(int id);

    void update(User user);

    User selectById(int id);

    User selectByLoginName(String loginName);

    List<User> pageList(Map paramMap);

    int totalCount();

}
