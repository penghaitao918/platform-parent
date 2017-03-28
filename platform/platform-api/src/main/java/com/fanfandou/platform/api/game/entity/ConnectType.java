package com.fanfandou.platform.api.game.entity;


import com.fanfandou.common.entity.EnumStatus;

/**
 * Created by wudi.
 * Descreption:平台连接方式.
 * Date:2016/8/5
 */
public enum ConnectType  implements EnumStatus {
    //socket连接方式
    SOCKET(0,"socket"),
    //数据库连接方式
    DATABASE(1,"database")
    ;

    private int id;
    private String code;

    ConnectType(int id, String code) {
        this.id = id;
        this.code = code;
    }

    /**
     * 根据id获取ToyBindStatus.
     * @param id id.
     * @return ToyBindStatus.
     */
    public static ConnectType valueOf(int id) {
        ConnectType[] types = values();
        for (ConnectType type : types) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Enum getById(int id) {
        return valueOf(id);
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
