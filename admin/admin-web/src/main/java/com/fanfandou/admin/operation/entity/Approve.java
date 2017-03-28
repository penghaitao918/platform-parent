package com.fanfandou.admin.operation.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * Created by wangzhenwei on 2016/7/13.
 * Description 审核表
 */
public class Approve {
    //id
    private int id;
    //申请人
    private String applyUser;
    //申请时间
    private Date applyTime;
    //审批人
    private String approvalUser;
    //审批时间
    private Date approvalTime;
    //审批状态 1未提交 2提交审批中 3审批通过 4审批打回
    private int approvalStatus;
    //审批正文
    private String approvalContent;
    //邮件订单id
    private MailOrder mailOrder;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApprovalUser() {
        return approvalUser;
    }

    public void setApprovalUser(String approvalUser) {
        this.approvalUser = approvalUser;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalContent() {
        return approvalContent;
    }

    public void setApprovalContent(String approvalContent) {
        this.approvalContent = approvalContent;
    }

    public MailOrder getMailOrder() {
        return mailOrder;
    }

    public void setMailOrder(MailOrder mailOrder) {
        this.mailOrder = mailOrder;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("applyUser", applyUser)
                .append("applyTime", applyTime)
                .append("approvalUser", approvalUser)
                .append("approvalTime", approvalTime)
                .append("approvalStatus", approvalStatus)
                .append("approvalContent", approvalContent)
                .append("mailOrder", mailOrder)
                .toString();
    }
}
