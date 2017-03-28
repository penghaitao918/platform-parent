package com.fanfandou.common.entity.resource;

import com.google.common.base.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 渠道.
 * <p/>
 * Date: 2016-04-07 13:55.
 * author: Arvin.
 */
public class SiteCode implements Serializable {
    private int id;
    private String code;
    private List<SiteCode> children = new ArrayList<SiteCode>();
    private List<SiteCode> parents = new ArrayList<SiteCode>();


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

    public List<SiteCode> getChildren() {
        return children;
    }

    public void setChildren(List<SiteCode> children) {
        this.children = children;
    }

    public List<SiteCode> getParents() {
        return parents;
    }

    public void setParents(List<SiteCode> parents) {
        this.parents = parents;
    }

    public static SiteCode getById(int id) {
        return SourceCodeFactory.getSiteById(id);
    }

    public static SiteCode getByCode(String code) {
        return SourceCodeFactory.getSiteByCode(code);
    }

    @Override
    public String toString() {
        return "id = " + this.id + ",code = " + this.code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SiteCode siteCode = (SiteCode) o;
        return id == siteCode.id &&
                Objects.equal(code, siteCode.code);
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
    public boolean isParent(SiteCode parent) {
        if (parent == null) {
            return false;
        }
        String parentCode = parent.getCode();
        //此处对以group打头的site做特殊处理，约定group打头专为分组使用，以满足一些特殊分组需求
        if (parentCode.startsWith("group.")) {
            parentCode = parentCode.substring(6);
        }
        return this.code.contains(parentCode);
    }


}

