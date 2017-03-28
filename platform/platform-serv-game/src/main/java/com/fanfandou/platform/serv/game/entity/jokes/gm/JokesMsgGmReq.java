package com.fanfandou.platform.serv.game.entity.jokes.gm;


import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wudi.
 * Descreption:发送GM道具邮件.
 * Date:2017/3/13
 */
public class JokesMsgGmReq implements Serializable {

    private int action;

    private String transId;

    private int msgId;

    private int mailtarget;

    private int op;

    private String mailid;

    private int[] roleid;

    private String content;

    //1:type 2:value 3:num
    private List<JSONObject> appendix;

    private String sign;

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public int[] getRoleid() {
        return roleid;
    }

    public void setRoleid(int[] roleid) {
        this.roleid = roleid;
    }

    public int getMailtarget() {
        return mailtarget;
    }

    public void setMailtarget(int mailtarget) {
        this.mailtarget = mailtarget;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

    public String getMailid() {
        return mailid;
    }

    public void setMailid(String mailid) {
        this.mailid = mailid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<JSONObject> getAppendix() {
        return appendix;
    }

    public void setAppendix(List<JSONObject> appendix) {
        this.appendix = appendix;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
