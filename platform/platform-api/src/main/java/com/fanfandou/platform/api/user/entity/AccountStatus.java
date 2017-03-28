/**
 * CopyRight Hilink.com 2013.
 */

package com.fanfandou.platform.api.user.entity;

import com.fanfandou.common.entity.EnumStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Description: 账号状态.
 *
 * @author Arvin.
 */
public enum AccountStatus implements EnumStatus<AccountStatus> {
    //
    DEACTIVATED(0, "deactivated"),
    ACTIVE(1, "active"),
    BANNED(2, "banned");

    //
    private int id;
    private String code;

    //
    AccountStatus(int id, String code) {
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
    public AccountStatus getById(int id) {
        return valueOf(id);
    }

    /**
     * 根据id获取AccountStatus
     * @param id id.
     * @return AccountStatus.
     */
    public static AccountStatus valueOf(int id) {
        AccountStatus[] types = values();
        for (AccountStatus type : types) {
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
