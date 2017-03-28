package com.fanfandou.common.entity;

/**
 * Description: 所有需要存数据库的枚举类都要实现此接口.
 * <p/>
 * Date: 2016-02-20 11:23.
 * author: Arvin.
 */
public interface EnumStatus<E extends Enum<E>> {
    int getId();
    E getById(int id);
}
