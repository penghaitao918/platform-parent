
package com.fanfandou.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*******************************************************************************
 * #(c) itentek
 * <p/>
 * 功能说明:db.properties文件辅助类
 * <p/>
 * 2012-5-29 下午09:08:29 awcui 创建文件
 * <p/>
 * 修改说明: 创建文件.
 * <p/>
 * 2012-5-29 下午09:08:29 awcui 修改文件
 ******************************************************************************/

public class PropertiesUtil {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
    /**
     * Properties.
     */
    private static Properties props;

    /**
     * 功能 :从database.properties文件中得到某个键所对应的值.
     * <p/>
     * 开发：awcui 2012-5-29
     *
     * @param key 键
     * @return 值
     */
    public static String getProperty(String key) {
        synchronized (PropertiesUtil.class) {
            if (props == null) {
                InputStream inputStream = Thread.currentThread()
                        .getContextClassLoader().getResourceAsStream(
                                "db.properties");
                props = new Properties();
                try {
                    props.load(inputStream);
                } catch (IOException e) {
                    logger.error("读取db.properties文件失败", e);
                }
            }

            return props.getProperty(key);
        }
    }

}
