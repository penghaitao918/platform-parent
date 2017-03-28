package com.fanfandou.common.util;

import com.fanfandou.common.base.BaseLogger;

import javax.servlet.http.HttpServletRequest;
import java.util.StringTokenizer;

/**
 * Created by wudi.
 * Descreption:获取IP地址.
 * Date:2016/7/9
 */
public class HttpAddrUtil extends BaseLogger {

    /**
     * 获得ip地址.
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteIp = request.getHeader("HTTP_X_FORWARDED_FOR");

        if (remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = request.getHeader("x-forwarded-for");
        }

        if (remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = request.getHeader("Proxy-Client-IP");
        }

        if (remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = request.getHeader("WL-Proxy-Client-IP");
        }

        if (remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = request.getRemoteAddr();
        }

        // 多级反向代理
        if (null != remoteIp && !"".equals(remoteIp.trim())) {
            StringTokenizer st = new StringTokenizer(remoteIp, ",");
            String ipTmp = "";

            if (st.countTokens() > 1) {
                while (st.hasMoreTokens()) {
                    ipTmp = st.nextToken();
                    if (ipTmp != null && ipTmp.length() != 0 && !"unknown".equalsIgnoreCase(ipTmp)) {
                        remoteIp = ipTmp;
                        break;
                    }
                }
            }
        }
        return remoteIp;
    }
}
