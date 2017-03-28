package com.fanfandou.common.entity.resource;

import com.google.common.base.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 游戏.
 * <p/>
 * Date: 2016-04-07 13:55.
 * author: Arvin.
 */
public class GameCode implements Serializable {
    private int id;
    private String code;
    //private List<GameCode> children = new ArrayList<GameCode>();
    //private GameCode parent;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public static GameCode getById(int id) {
        return SourceCodeFactory.getGameById(id);
    }

    public static GameCode getByCode(String code) {
        return SourceCodeFactory.gatGameByCode(code);
    }


    @Override
    public String toString() {
        return "id = " + this.id + ",code = " + this.code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCode gameCode = (GameCode) o;
        return id == gameCode.id &&
                Objects.equal(code, gameCode.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, code);
    }

    /**
     * 判断是否为父子关系.
     *
     * @param parent 父
     * @return boolean
     */
    public boolean isParent(GameCode parent) {
        return parent != null && this.code.contains(parent.getCode());
    }
}
