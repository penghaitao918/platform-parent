/**
 * CopyRight Hilink.com 2013.
 */

package com.fanfandou.platform.api.user.entity;

import com.fanfandou.common.entity.EnumStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Description: 用户类型.
 *
 * @author Arvin.
 */
public enum ProfileDomain implements EnumStatus<ProfileDomain> {

    /**
     * the default user.
     */
    DEFAULT(1, "default"),

    /**
     * the developer.
     */
    DEVELOPER(6, "developer"),

    /**
     * the dev company.
     */
    COMPANY(11, "company");

    //
    private int id;
    private String code;

    //
    ProfileDomain(int id, String code) {
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
    public ProfileDomain getById(int id) {
        return valueOf(id);
    }

    /**
     * 根据id获取ProfileDomain.
     *
     * @param id id.
     * @return ProfileDomain.
     */
    public static ProfileDomain valueOf(int id) {
        ProfileDomain[] types = values();
        for (ProfileDomain type : types) {
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
