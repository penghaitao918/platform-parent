package com.fanfandou.admin.system.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * Created by wangzhenwei on 2016/6/12.
 * Description 自定义的过滤器，来实现自己需要的授权过滤方式。
 */
public class PermissionsFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return true;
    }

    protected List havePerm(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = this.getSubject(request, response);
        String[] perms = (String[]) mappedValue;
        List permArray = new ArrayList();
        for (String perm : perms) {
            if (subject.isPermitted(perm)) {
                permArray.add(perm);
            }
        }
        return permArray;
    }
}

