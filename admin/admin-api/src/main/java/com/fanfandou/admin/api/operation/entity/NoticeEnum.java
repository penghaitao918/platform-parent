package com.fanfandou.admin.api.operation.entity;

import com.fanfandou.common.entity.EnumStatus;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * author wangzhenwei
 * Desciption: NoticeType的枚举.
 */
public enum NoticeEnum implements EnumStatus<NoticeEnum>{

    NOTICE_POPUP(1,"NOTICE_POPUP"),
    NOTICE_ROLE(2,"NOTICE_ROLE"),
    NOTICE_SYSTEM(3,"NOTICE_SYSTEM");

    //公告类型 1进入游戏弹出公告 2游戏内滚动公告 3聊天窗口系统公告


    private  int id;
    private  String code;

    public String getCode() {
        return code;
    }

    NoticeEnum(int id, String code) {
        this.id = id;

        this.code = code;
    }

    @JsonValue
    @Override
    public int getId() {
        return id;
    }

    @Override
    public NoticeEnum getById(int id) {
        return ValueOf(id);
    }

    public static NoticeEnum ValueOf(int id){
        NoticeEnum[] res =values();
        for(NoticeEnum re :res){
            if (re.getId()==id){
                return re;
            }
        }
        return null;
    }
}
