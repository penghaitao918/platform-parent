/**
 * CopyRight Hilink.com 2013.
 */

package com.fanfandou.platform.api.user.entity;

import com.fanfandou.common.entity.EnumStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 性别.
 *
 * @author Arvin.
 */
public enum Gender implements EnumStatus<Gender> {
    UNKNOWN(-1, "unknown"),
    FEMALE(1, "female"),
    MALE(2, "male");

    private int id;
    private String code;

    Gender(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
    @Override
    public Gender getById(int id) {
        return valueOf(id);
    }

    /**
     * 根据id获取Gender.
     * @param id id.
     * @return Gender.
     */
    public static Gender valueOf(int id) {
        Gender[] types = values();
        for (Gender type : types) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
