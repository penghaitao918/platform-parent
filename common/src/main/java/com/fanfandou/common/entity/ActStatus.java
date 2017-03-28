/**
 * CopyRight Hilink.com 2013.
 */

package com.fanfandou.common.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description: 状态.
 *
 * @author Arvin.
 */
public enum ActStatus implements EnumStatus<ActStatus> {

    //
    //the initialize status
    UNACT(0, "unact"),

    //the acting
    ACTING(2, "acting"),
    LOCKED(3, "locked"),

    //the act result. ok, reject error.
    ACTED(1, "acted"),
    REJECTED(-2, "reject"),
    ERROR(-1, "error");

    //
    private int id;
    private String code;

    ActStatus(int id, String code) {
        this.id = id;
        this.code = code;
    }
    @JsonValue
    @Override
    public int getId() {
        return id;
    }

    @Override
    public ActStatus getById(int id) {
        return valueOf(id);
    }

    public String getCode() {
        return code;
    }
    /**
     * 根据id获取ActStatus.
     * @param id id.
     * @return ActStatus.
     */
    public static ActStatus valueOf(int id) {
        ActStatus[] types = values();
        for (ActStatus type : types) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

}
