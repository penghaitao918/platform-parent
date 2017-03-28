package com.fanfandou.platform.web.user.servcie;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.resource.SiteCode;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.service.cache.CacheService;
import com.fanfandou.platform.api.constant.IcachedConstant;
import com.fanfandou.platform.web.user.login.SiteNote;
import com.fanfandou.platform.web.user.login.check.LeTvLoginChecker;
import com.fanfandou.platform.web.user.login.check.PressTestLoginCheck;
import com.fanfandou.platform.web.user.vo.AccountVo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wudi.
 * Descreption:平台三方登录工厂.
 * Date:2016/3/29
 */
@Service
public class ThirdLoginServiceClient extends BaseLogger {


    public ThirdLoginServiceClient() {

    }

    @Autowired
    private CacheService cacheService;

    private Map<String, Object> checkerMap = new HashMap<>();

    private Map<String, String> classUrl = new HashMap<>();


    /**
     * 三方登录渠道处理方法.
     */
    /*public AccountVo factory(AccountVo accountVo) throws ServiceException {
        logger.debug("ThirdLoginServiceClientfactory");
        logger.debug("accountVo siteId = " + accountVo.getSiteId());
        List<Class> clazzs = new ArrayList<>();
        AccountVo platformLogin = null;
        //String className = cacheService.hGet(IcachedConstant.USER_PACKAGE_CLASSNAME, accountVo.getSiteId() + "", String.class);
        String className = classUrl.get(IcachedConstant.USER_PACKAGE_CLASSNAME + accountVo.getSiteId());
        if (className == null) {
            clazzs = getClasssFromPackage("com.fanfandou.platform.web.user.login.check");
            for (Class clazz : clazzs) {
                className = clazz.getName();
                Annotation annotations[] = clazz.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof SiteNote) {
                        SiteNote data = (SiteNote) annotation;
                        String siteCode = SiteCode.getById(accountVo.getSiteId()).getCode();
                        logger.info("SiteNote name = " + data.name());
                        if (data.name().equals(siteCode)) {
                            //cacheService.hPut(IcachedConstant.USER_PACKAGE_CLASSNAME, accountVo.getSiteId() + "", className);
                            classUrl.put(IcachedConstant.USER_PACKAGE_CLASSNAME + accountVo.getSiteId(), className);
                        }
                    }
                }
            }
        }
        try {
            Class clazz = Class.forName(className);
            Method method = clazz.getDeclaredMethod("login",
                    new Class[]{AccountVo.class});
            method.setAccessible(true);
            Object object = checkerMap.get(IcachedConstant.USER_SITE_LONGINCHECK_OBJECT + accountVo.getSiteId());
            if (object == null) {
                object = clazz.newInstance();
                checkerMap.put(IcachedConstant.USER_SITE_LONGINCHECK_OBJECT + accountVo.getSiteId(), object);
            }
            platformLogin = (AccountVo) method.invoke(object,
                    new Object[]{accountVo});

        } catch (Exception e) {
            e.printStackTrace();
        }
        return platformLogin;
    }*/

    public AccountVo factory(AccountVo accountVo) throws ServiceException {
        AccountVo platformLogin = null;
        if (accountVo.getSiteId() == 22 || accountVo.getSiteId() == 28) {
            platformLogin = PressTestLoginCheck.getInstance().login(accountVo);
        } else {
            platformLogin = LeTvLoginChecker.getInstance().login(accountVo);
        }
        return platformLogin;
    }


    /**
     * 获得包下面的所有的class.
     *
     * @param pack package完整名称
     * @return List包含所有class的实例
     */
    public static List<Class> getClasssFromPackage(String pack) {
        List<Class> clazzs = new ArrayList<Class>();
        // 包名字
        // 包名对应的路径名称
        String packageDirName = pack.replace('.', '/');

        Enumeration<URL> dirs;

        try {
            dirs = Thread.currentThread().getContextClassLoader()
                    .getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();

                String protocol = url.getProtocol();

                if ("file".equals(protocol)) {
                    System.out.println("file类型的扫描");
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findClassInPackageByFile(pack, filePath, true,
                            clazzs);
                } else if ("jar".equals(protocol)) {
                    System.out.println("jar类型的扫描");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clazzs;
    }

    /**
     * 通过包名，路径获取class.
     */
    public static void findClassInPackageByFile(String packageName,
                                                String filePath, final boolean recursive, List<Class> clazzs) {
        File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 在给定的目录下找到所有的文件，并且进行条件过滤
        File[] dirFiles = dir.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
                boolean acceptDir = recursive && file.isDirectory();// 接受dir目录
                boolean acceptClass = file.getName().endsWith("class");// 接受class文件
                return acceptDir || acceptClass;
            }
        });

        for (File file : dirFiles) {
            if (file.isDirectory()) {
                findClassInPackageByFile(packageName + "." + file.getName(),
                        file.getAbsolutePath(), recursive, clazzs);
            } else {
                String className = file.getName().substring(0,
                        file.getName().length() - 6);
                try {
                    clazzs.add(Thread.currentThread().getContextClassLoader()
                            .loadClass(packageName + "." + className));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
