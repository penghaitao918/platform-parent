package com.fanfandou.platform.api.user.entity;

import com.fanfandou.common.entity.EnumStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Description: 账号类型.
 *
 * @author Arvin.
 */
public enum AccountType implements EnumStatus<AccountType> {
    //
    DEVICE(1, "device", true, false, false),
    ACCOUNT_NAME(2, "account.name", true, true, false),
    EMAIL(3, "email", true, true, true),
    PHONE_NUMBER(4, "phone.number", true, true, true),

    //
    THIRD_OAUTH(11, "third.oauth", false, false, false);

    //
    private int id;
    private String code;

    //
    private boolean needPwd;
    private boolean needConfirm;
    private boolean selfAuth;

    //
    AccountType(int id, String code, boolean selfAuth, boolean needPwd, boolean needConfirm) {
        this.id = id;
        this.code = code;

        this.selfAuth = selfAuth;
        this.needPwd = needPwd;
        this.needConfirm = needConfirm;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public AccountType getById(int id) {
        return valueOf(id);
    }

    /**
     * 根据id获取AccountType.
     * @param id id.
     * @return AccountType.
     */
    public static AccountType valueOf(int id) {
        AccountType[] types = values();
        for (AccountType type : types) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public boolean isNeedPwd() {
        return needPwd;
    }

    public boolean isNeedConfirm() {
        return needConfirm;
    }

    public boolean isSelfAuth() {
        return selfAuth;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
