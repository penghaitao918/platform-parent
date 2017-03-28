package com.fanfandou.platform.web.billing.callback.Utils;


import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Enumeration;
import java.util.SortedSet;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by wudi.
 * Descreption:为参数重新排序.
 * Date:2016/8/1
 */
public class ReorderParams {

    private static Logger logger = LoggerFactory.getLogger(ReorderParams.class);

    /**
     * 为请求参数重新排序
     * @param request 请求参数.
     * @param key 验证秘钥.
     * @return 排序后的值.
     */
    public static String getOrderParams(HttpServletRequest request, String key) {

        Enumeration names = request.getParameterNames();

        SortedSet<String> allParams = Sets.newTreeSet();

        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            if (name.equals("sign")) {
                continue;
            }
            try {
                logger.debug(name + "=" + request.getParameter(name));
                allParams.add(name + "=" + request.getParameter(name));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return Joiner.on("").join(allParams) + key;
    }
}
