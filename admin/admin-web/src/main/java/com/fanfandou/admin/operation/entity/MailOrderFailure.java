package com.fanfandou.admin.operation.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 钟亮 on 2016/9/26.
 * Description 邮件订单Failure记录.
 */
public class MailOrderFailure implements Serializable {

    private int id;
    //发送失败的原因
    private String failureReasons;
    //创建时间
    private Date createTime;
    //问题备注
    private String remark;
    //邮件订单id
    private int mailOrderId;
    //邮件标题
    private String mailTitle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFailureReasons() {
        return failureReasons;
    }

    public void setFailureReasons(String failureReasons) {
        this.failureReasons = failureReasons;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getMailOrderId() {
        return mailOrderId;
    }

    public void setMailOrderId(int mailOrderId) {
        this.mailOrderId = mailOrderId;
    }

    public String getMailTitle() {
        return mailTitle;
    }

    public void setMailTitle(String mailTitle) {
        this.mailTitle = mailTitle;
    }

    @Override
    public String toString() {
        return "MailOrderFailure{" +
                "id=" + id +
                ", failureReasons='" + failureReasons + '\'' +
                ", create_time=" + createTime +
                ", remark='" + remark + '\'' +
                ", mailOrderId=" + mailOrderId +
                ", mailTitle='" + mailTitle + '\'' +
                '}';
    }
}
