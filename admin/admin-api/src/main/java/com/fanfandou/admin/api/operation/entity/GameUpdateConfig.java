package com.fanfandou.admin.api.operation.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by wudi.
 * Descreption:游戏更新配置.
 * Date:2017/2/23
 */
public class GameUpdateConfig implements Serializable {

    /**
     * 补丁下载路径.
     */
    private String updatePackageUrl;

    /**
     * 更新文件名称.
     */
    private String updateFileName;

    /**
     * 文件大小,单位kb.
     */
    private int updatePackageSize;

    public String getUpdatePackageUrl() {
        return updatePackageUrl;
    }

    public void setUpdatePackageUrl(String updatePackageUrl) {
        this.updatePackageUrl = updatePackageUrl;
    }

    public String getUpdateFileName() {
        return updateFileName;
    }

    public void setUpdateFileName(String updateFileName) {
        this.updateFileName = updateFileName;
    }

    public int getUpdatePackageSize() {
        return updatePackageSize;
    }

    public void setUpdatePackageSize(int updatePackageSize) {
        this.updatePackageSize = updatePackageSize;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
