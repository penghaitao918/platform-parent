package com.fanfandou.platform.web.billing.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.result.JsonResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.util.CipherUtils;
import com.fanfandou.common.util.ErrorValidate;
import com.fanfandou.common.util.HttpUtils;
import com.fanfandou.platform.api.billing.entity.Currency;
import com.fanfandou.platform.web.billing.callback.Utils.GooglePlay.Security;
import com.fanfandou.platform.web.billing.callback.Utils.ReorderParams;
import com.fanfandou.platform.web.billing.service.BillingServiceClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wudi.
 * Descreption:渠道支付回调地址.
 * Date:2016/8/1
 */
@RequestMapping("/billing")
@RestController
@ErrorValidate
public class BillingCallback extends BaseLogger {

    @Autowired
    private BillingServiceClient billingServiceClient;

    /**
     * 乐视支付回调.
     */
    @RequestMapping("/callback/letvPayCallback")
    public String letvPayCallback() {
        String result = "fail";
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info("letvPayCallback >> " + httpServletRequest.getRequestURI() + ",params = "
                + JSON.toJSONString(httpServletRequest.getParameterMap()));
        try {
            //String prodCallback = "http://api.platform.fanfandou.com:8888/billing/callback/letvPayCallback";
            String urlCallback = "http://106.75.33.45:8888/platform/billing/callback/letvPayCallback";
            String secretKey = "82f4d3ae4ced4178bff0cf711402a27e";
            String toyType = "ttl-toys";
            String appKey = httpServletRequest.getParameter("appKey");
            if (appKey.equals("276242")) {
                secretKey = "346a5556cc1648d4a0db92d066c93bbb";
                toyType = "mouse";
            }
            //beta参数
            // String codeAppkey = "234dd0f1782f4cf0a85881bcb4c54d77";
            //String codeSecret = "346a5556cc1648d4a0db92d066c93bbb";

            String params = httpServletRequest.getParameter("params");
            String pxNumber = httpServletRequest.getParameter("pxNumber");
            String price = httpServletRequest.getParameter("price");
            String userName = httpServletRequest.getParameter("userName");
            String currencyCode = httpServletRequest.getParameter("currencyCode");
            String products = httpServletRequest.getParameter("products");
            String sign = httpServletRequest.getParameter("sign");
            String extraData[] = params.split("\\*");
            String orderNo = extraData[0];
            String codeType = extraData[1];
            int gameId = Integer.parseInt(extraData[2]);
            String areaCode = extraData[3];
            int goodsId = Integer.parseInt(extraData[4]);

            logger.info("letvPayCallback >> orderNo = " + orderNo);

            //重新排序，生成新的字符串
            String strParams = urlCallback + ReorderParams.getOrderParams(httpServletRequest, secretKey);
            logger.info("letvPayCallback >> strParams = " + strParams);
            strParams = URLEncoder.encode(strParams, "utf-8");
            logger.info("letvPayCallback >> strParamsEncoder = " + strParams);
            //加密
            String msign = CipherUtils.initMd5().encrypt(strParams);
            logger.info("letvPayCallback >> msign = " + msign);
            if (msign.equals(sign)) {

                String timeStamp = new Date().getTime() + "";//
                if (codeType.contains("ttl-toys")) {
                    //如果有类型，则走乐视0元购流程
                    String reqUrl = "http://hades.hdtv.letv.com/hades/proxy/api/cdkey/";

                    String reqStrParams = "accType=" + toyType + "appKey=" + appKey + "appPackage=" + "com.fanfandou.ttol.letv"
                            + "pxNumber=" + pxNumber + "timestamp=" + timeStamp + secretKey;
                    String reqStrAll = "POST" + reqUrl + reqStrParams;
                    logger.info("0gou >> reqStrAll = " + reqStrAll);
                    reqStrAll = URLEncoder.encode(reqStrAll, "utf-8");
                    String codeSign = CipherUtils.initMd5().encrypt(reqStrAll);
                    List<NameValuePair> paramsPair = new ArrayList<NameValuePair>();
                    paramsPair.add(new BasicNameValuePair("pxNumber", pxNumber));
                    paramsPair.add(new BasicNameValuePair("appPackage", "com.fanfandou.ttol.letv"));
                    paramsPair.add(new BasicNameValuePair("accType", toyType));
                    paramsPair.add(new BasicNameValuePair("sign", codeSign));
                    paramsPair.add(new BasicNameValuePair("timestamp", timeStamp));
                    paramsPair.add(new BasicNameValuePair("appKey", appKey));
                    logger.info("0gou >> codeSign = " + codeSign);
                    JSONObject jsonObject = JSONObject.parseObject(HttpUtils.doPost(reqUrl, paramsPair, "utf-8"));
                    logger.info("0gou >> jsonObject = " + jsonObject);
                    if (!StringUtils.isEmpty(jsonObject.getString("cdkey"))) {
                        logger.info("开始零元购发货");
                        String retCode = jsonObject.getString("cdkey");
                        billingServiceClient.sendToycode(gameId, areaCode, userName, goodsId, retCode);
                    }
                }
                logger.info("正常充值发货");
                billingServiceClient.thirdCharge(orderNo, pxNumber, Currency.CNY, (int) (Double.parseDouble(price) * 100));
                result = "SUCCESS";
            }
        } catch (Exception e) {
            logger.error("letvPayCallback", e);
            result = "FAIL";
        }
        logger.info("正常返回状态 result = " + result);
        return result;
    }

    /**
     * 谷歌支付回调.
     *
     * @return 支付结果.
     */
    @RequestMapping("/googlePlayCallback")
    public JsonResult googlePlayCallback() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setMessage(JsonResult.FAIL_MSG);
        jsonResult.setStatus(JsonResult.FAIL);
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info("letvPayCallback >> " + request.getRequestURI() + ",params = "
                + JSON.toJSONString(request.getParameterMap()));
        String signData = request.getParameter("signData");
        String signature = request.getParameter("signSignature");
        String orderNo = request.getParameter("orderNO");
        String numberNo = request.getParameter("numberNo");
        String money = request.getParameter("money");
        logger.info("googlePlayCallback >>>> signData = {}, signSignature = {}, orderNo = {}, money = {}",
                signData, signature, orderNo, money);
        if (!StringUtils.contains(numberNo, '.')) {  //订单号不符合规则

            logger.info("googlePlayCallback -> numberNO invalid. numberNO : " + numberNo);
            return jsonResult;
        }
        try {
            boolean flag = Security.verifyPurchase(signData, signature);
            if (flag) {
                billingServiceClient.thirdCharge(orderNo, numberNo, Currency.CNY, Integer.parseInt(money));
                jsonResult.setMessage(JsonResult.SUCCESS_MSG);
                jsonResult.setStatus(JsonResult.SUCCESS);
            }
        } catch (ServiceException e) {
            logger.error("googlePlayCallback >", e);
        }
        logger.info("result = " + jsonResult);

        return jsonResult;
    }

    /**
     * 乐赢互动支付回调.
     *
     * @return 支付结果.
     */
    @RequestMapping("/LytPlayCallback")
    public JsonResult LytPlayCallback() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setMessage(JsonResult.FAIL_MSG);
        jsonResult.setStatus(JsonResult.FAIL);
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info("letvPayCallback >> " + request.getRequestURI() + ",params = "
                + JSON.toJSONString(request.getParameterMap()));
        String signData = request.getParameter("signData");
        String signature = request.getParameter("signSignature");
        String orderNo = request.getParameter("cp_order_id");
        String numberNo = request.getParameter("trade_id");
        String money = request.getParameter("money");
        logger.info("LytPlayCallback >>>> signData = {}, signSignature = {}, orderNo = {}, money = {}",
                signData, signature, orderNo, money);
        if (!StringUtils.contains(numberNo, '.')) {  //订单号不符合规则

            logger.info("LytPlayCallback -> numberNO invalid. numberNO : " + numberNo);
            return jsonResult;
        }
        try {
            boolean flag = Security.verifyPurchase(signData, signature);
            if (flag) {
                billingServiceClient.thirdCharge(orderNo, numberNo, Currency.CNY, Integer.parseInt(money));
                jsonResult.setMessage(JsonResult.SUCCESS_MSG);
                jsonResult.setStatus(JsonResult.SUCCESS);
            }
        } catch (ServiceException e) {
            logger.error("LytPlayCallback >", e);
        }
        logger.info("result = " + jsonResult);

        return jsonResult;
    }

}
