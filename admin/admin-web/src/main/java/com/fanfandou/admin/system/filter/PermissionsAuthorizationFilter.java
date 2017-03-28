package com.fanfandou.admin.system.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Set;

/**
 * Created by wangzhenwei on 2016/6/12.
 * Description 自定义的过滤器，来实现自己需要的授权过滤方式。
 */
public class PermissionsAuthorizationFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        //自定义过滤器逻辑
        Subject subject = this.getSubject(request, response);
        String[] permsArray = (String[]) mappedValue;
        if (permsArray == null || permsArray.length == 0) {
            return true;
        }
        Set<String> perms = CollectionUtils.asSet(permsArray);
        for (String perm : perms) {
            if (subject.isPermitted(perm)) {
                return true;
            }
        }
        return false;
    }

}

