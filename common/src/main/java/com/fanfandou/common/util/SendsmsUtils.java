package com.fanfandou.common.util;

import com.fanfandou.common.base.BaseLogger;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wudi.
 * Descreption:发送短信工具类.
 * Date:2016/3/14
 */
public class SendsmsUtils extends BaseLogger {

    private static String reqUrl = "http://gbk.sms.webchinese.cn";

    /**
     * 发送短信.
     */
    public static int sendSms(String phoneNo, String smsContent) {
        //查询邮箱是否已注册
        int resp = 0;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Uid", "baomax"));
        params.add(new BasicNameValuePair("Key", "1b66c7e72c4455adc3dd"));
        params.add(new BasicNameValuePair("smsMob", phoneNo));
        params.add(new BasicNameValuePair("smsText", smsContent));

        /*params.add(new BasicNameValuePair("Uid", URLEncoder.encode("桃衫倚醉","utf-8")));
        params.add(new BasicNameValuePair("Key", "kkwty01"));
        params.add(new BasicNameValuePair("smsMob", phoneNo));
        params.add(new BasicNameValuePair("smsText", URLEncoder.encode(smsContent,"utf-8")));*/
        try {
            resp = Integer.parseInt(HttpUtils.doPost(reqUrl, params, "gbk"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }


}
