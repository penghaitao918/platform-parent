package com.fanfandou.admin.api.system.entity;

import com.fanfandou.common.entity.EnumStatus;

/**
 * author shengbo
 * Desciption: type的枚举.
 */
public enum ResEnum implements EnumStatus<ResEnum>{

    game(1,"game"),
    site(2,"site");

    private  int id;
    private  String code;

    public String getCode() {
        return code;
    }

    ResEnum(int id, String code) {
        this.id = id;

        this.code = code;
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public ResEnum getById(int id) {
        return ValueOf(id);
    }

    public static  ResEnum ValueOf(int id){
        ResEnum [] res =values();
        for(ResEnum re :res){
            if (re.getId()==id){
                return re;
            }
        }
        return null;
    }
}
