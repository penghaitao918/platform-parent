package com.fanfandou.admin.system.realm;

import com.fanfandou.admin.system.entity.Permission;
import com.fanfandou.admin.system.entity.Role;
import com.fanfandou.admin.system.entity.User;
import com.fanfandou.admin.system.service.PermissionService;
import com.fanfandou.admin.system.service.RoleService;
import com.fanfandou.admin.system.service.UserService;
import com.fanfandou.common.util.CipherUtils;
import com.fanfandou.common.util.PropertiesUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("myRealm")
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /*得到授权信息*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*用户的角色*/
        Set<String> roleNames = new HashSet<>();
        /*用户的权限*/
        Set<String> permissions = new HashSet<>();
        /*得到登陆用户的用户名*/
        String username = (String) principals.getPrimaryPrincipal();
        /*根据用户名得到用户*/
        User user = userService.findUserByLoginName(username);


	       /*根据用户id得到角色*/
        int userRoleType = 1;
        List<Role> role = roleService.selRoleByUId(user.getId(), userRoleType);
        for (int i = 0; i < role.size(); i++) {
            Role ro = role.get(i);
            if (ro.getAvailable() == 1) {
                roleNames.add(ro.getRole());
            }
        }
        /*根据用户id得到权限*/
        int userPermissionType = 3;
        List<Permission> permission = permissionService.selByRoleUserId(user.getId(), userPermissionType);
        for (Permission perm : permission) {
            //是否可用，1可用，0不可用
            if (perm.getAvailable() == 1) {
                permissions.add(Integer.toString(perm.getId()));
            }
        }
        /*根据角色id得到权限*/
        for (int i = 0; i < role.size(); i++) {
            Role ro = role.get(i);
            if (ro.getAvailable() == 1) {
                int rolePermissionType = 2;
                List<Permission> perList = permissionService.selByRoleUserId(ro.getId(), rolePermissionType);
                for (Permission per : perList) {
                    if (per.getAvailable() == 1) {
                        permissions.add(Integer.toString(per.getId()));
                    }
                }
            }
        }

	       /*授权信息，设置角色和权限*/
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        info.setStringPermissions(permissions);
        return info;
    }

    /*认证用户名和密码是否符合登陆条件*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        // 这里编写认证代码
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        if (token.getUsername().equals("admin")) {
            String salt = "a123";
            String pwd = new String(token.getPassword());
            String miPwd = CipherUtils.getPasswd(pwd,salt);
            token.setPassword(miPwd.toCharArray());
            String userPwd = PropertiesUtil.getProperty("admin.password");
            return new SimpleAuthenticationInfo("admin",userPwd,getName());
        }
        //根据用户名从数据库里查找用户
        User user = userService.findUserByLoginName(token.getUsername());
        if (user == null) {
            throw new UnknownAccountException("没有找到该账号");
        }
        if (user.getLocked() == 0) {
            //帐号锁定
            throw new LockedAccountException("账号锁定");
        }
        String pwd = new String(token.getPassword());
        String salt = user.getSalt();
        String miPwd = CipherUtils.getPasswd(pwd, salt);
        token.setPassword(miPwd.toCharArray());
        String userPwd = user.getPassword();
        //查找到的用户与Token里面的用户进行比较  匹配则登陆成功，不匹配则登陆失败
        return new SimpleAuthenticationInfo(user.getLoginName(), userPwd, getName());

    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection arg0) {
        super.clearCachedAuthorizationInfo(arg0);
    }

    /*退出登录即点击logout时清除登陆的所有信息*/
    @Override
    protected void doClearCache(PrincipalCollection principals) {
        Object principal = principals.getPrimaryPrincipal();
        getCacheManager().getCache(principal.toString());
        super.doClearCache(principals);
    }
}
