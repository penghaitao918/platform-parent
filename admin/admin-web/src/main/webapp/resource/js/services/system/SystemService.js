/**
 * 系统管理Service文件
 * author：钟亮
 * @type {module|*}
 */
/**
 自定义表单Service
 */
adminApp.service('customFormService', function () {

    /*
     添加信息
     对象：gameArea
     */
    this.customFormAdd = function (custom) {
        return $.post(ctx + '/CustomForm/insert', custom);
    }

    /*
     添加信息
     对象：gameArea
     */
    this.customFormUpdate = function (custom) {
        return $.post(ctx + '/CustomForm/update', custom);
    }

    /*
     查询信息
     对象：gameArea
     */
    this.customFormAll = function (custom) {
        return $.post(ctx + '/CustomForm/query', custom);
    }

    this.customFormByKey = function (key) {
        return $.post(ctx + '/CustomForm/getByKey', key);
    }

    /*
     根据key删除
     对象：gameArea
     */
    this.customFormDeleteByKey = function (custom) {
        return $.post(ctx + '/CustomForm/deleteKey', custom);
    }
});

/**
 菜单管理Service
 */
adminApp.service('menuFnService', function () {

    /*
     根据id查询menu信息
     菜单id：menuId
     */
    this.menuGetById = function (menuId) {
        return $.get(ctx + '/system/menu/getById', {"id": menuId});
    }

    /*
     添加menu信息
     菜单对象：menu
     */
    this.menuAdd = function (menu) {
        return $.post(ctx + '/system/menu/insert', menu);
    }

    /*
     修改menu信息
     菜单对象：menu
     */
    this.menuUpdate = function (menu) {
        return $.post(ctx + '/system/menu/update', menu);
    }


    /*
     删除menu信息
     菜单id：menu
     */
    this.menuDelete = function (menuIds) {
        return $.get(ctx + '/system/menu/delete', {"menuIds": menuIds});
    }

    /*
     根据menuName查询menu信息（分页，menuName可传空值）
     菜单名称：menuName
     指定页：pageIndex
     每页条数：pageSize
     */
    this.menuFindByName = function (menuSearch, pageIndex, pageSize) {
        return $.post(ctx + '/system/menu/pageList', {
            "name": menuSearch.menuName,
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "sort": null,
            "order": null
        });
    }

    /*
     根据menuName查询menu信息（分页，menuName可传空值）查询菜单权限 调用relation接口
     菜单名称：menuName
     指定页：pageIndex
     每页条数：pageSize
     */
    this.menuPFindByName = function (pageIndex, pageSize, userId, type, parentId) {
        return $.post(ctx + '/system/relation/menuPermission', {
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "sort": null,
            "order": null,
            "parentId": parentId,
            userId: userId,
            type: type
        });
    }


    /*
     查询menu列表
     */
    this.menuList = function () {
        return $.get(ctx + '/system/menu/getMenu');
    }

    /*
     修改状态
     权限id：
     */
    this.menuStateUpdate = function (menu) {
        return $.post(ctx + '/system/menu/updateState', menu);
    }

    /*
     查询父menu列表
     */
    this.getParentMenu = function () {
        return $.get(ctx + '/system/menu/getParentMenu');
    }

    /*
     根据name查询menu
     */
    this.getMenuByName = function (name) {
        return $.get(ctx + '/system/menu/getMenuByName', {name: name});
    }
});

/**
 权限管理Service
 */
adminApp.service('permissionFnService', function () {

    /*
     根据id查询permission信息
     权限id：permissionId
     */
    this.permissionGetById = function (permissionId) {
        return $.get(ctx + '/system/permission/editPermission/' + permissionId);
    }

    /*
     添加permission信息
     权限对象：permission
     */
    this.permissionAdd = function (permission) {
        return $.post(ctx + '/system/permission/addPermission', permission);
    }

    /*
     修改permission信息
     权限对象：permission
     */
    this.permissionUpdate = function (permission) {
        return $.post(ctx + '/system/permission/updatePermission', permission);
    }


    /*
     删除permission信息
     权限id：permissionId
     */
    this.permissionDelete = function (permissionId) {
        return $.get(ctx + '/system/permission/delPermission/' + permissionId);
    }

    /*
     根据permissionValue查询permission信息（分页，permissionValue可传空值）
     权限名称：permissionValue
     指定页：pageIndex
     每页条数：pageSize
     */
    this.permissionFindByName = function (permissionValue, pageIndex, pageSize) {
        return $.post(ctx + '/system/permission/pageList', {
            "permissionValue": permissionValue,
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "sort": null,
            "order": null
        });
    }
});

/**
 获取当前用户信息Service
 */
adminApp.service('profileService', function () {

    /*
     获取当前用户信息
     */
    this.getUserByName = function () {
        return $.post(ctx + '/system/user/profileShow');
    }

});

/**
 资源管理Service
 */
adminApp.service('resourceFnService', function () {
    /*
     根据name查询resource信息（分页，name可传空值）
     资源名称：name
     指定页：pageIndex
     每页条数：pageSize
     */
    this.resourceFindByName = function (name, pageIndex, pageSize) {
        return $.post(ctx + '/system/res/pageList', {
            "resCode": name,
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "sort": null,
            "order": null
        });
    }

    /*
     添加game资源
     资源对象：game
     */
    this.resAddGanme = function (game) {
        return $.post(ctx + '/system/res/insertGame', game);
    }

    /*
     添加Site资源
     资源对象：Site
     */
    this.resAddSite = function (site) {
        return $.post(ctx + '/system/res/insertSite', site);
    }

    /*
     删除资源信息
     资源id：resId
     */
    this.resourceDelete = function (resIds) {
        return $.get(ctx + '/system/res/delResource/' + resIds);
    }

    /*
     根据id查询信息
     权限id：resId
     */
    this.resourceGetById = function (resId) {
        return $.get(ctx + '/system/res/editRes/' + resId);
    }

    /*
     修改信息
     权限对象：res
     */
    this.resourceUpdate = function (res) {
        return $.post(ctx + '/system/res/updateGame', res);
    }

    /*
     获取Game集合
     */
    this.resourceGetGame = function () {
        return $.get(ctx + '/system/res/gameMap');
    }

    /*
     获取Site集合
     */
    this.resourceGetSite = function () {
        return $.get(ctx + '/system/res/siteMap');
    }

    /*
     修改状态
     */
    this.resStateUpdate = function (res) {
        return $.post(ctx + '/system/res/updateState', res);
    }

    /*
     更新资源缓存
     */
    this.updateResCache = function (res) {
        return $.post(ctx + '/system/res/updateResCache');
    }
});

/**
 角色管理Service
 */
adminApp.service('roleFnService', function () {
    /*
     根据id查询role信息
     角色id：roleId
     */
    this.roleGetById = function (roleId) {
        return $.get(ctx + '/system/role/editRole/' + roleId);
    }

    /*
     添加role信息
     角色对象：role
     */
    this.roleAdd = function (role) {
        return $.post(ctx + '/system/role/addRole', role);

        /*return $http({
         method  : 'POST',
         url     : '/admin-web/system/role/addRole',
         data    : $.param(role),  // pass in data as strings
         headers : { 'Content-Type': 'application/x-www-form-urlencoded' }  // set the headers so angular passing info as form data (not request payload)
         });*/
    }

    /*
     修改role信息
     角色对象：role
     */
    this.roleUpdate = function (role) {
        return $.post(ctx + '/system/role/updateRole', role);
    }


    /*
     删除role信息
     角色id：roleId
     */
    this.roleDelete = function (roleId) {
        return $.get(ctx + '/system/role/delRole/' + roleId);
    }

    /*
     根据roleName查询role信息（分页，roleName可传空值）
     角色名称：roleName
     指定页：pageIndex
     每页条数：pageSize
     */
    this.roleFindByName = function (roleName, pageIndex, pageSize) {
        return $.post(ctx + '/system/role/pageList', {
            "roleName": roleName,
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "sort": null,
            "order": null
        });
    }
    this.allRole = function () {
        return $.post(ctx + '/system/role/list');
    }

    /*
     修改状态
     权限id：
     */
    this.roleStateUpdate = function (role) {
        return $.post(ctx + '/system/role/updateState', role);
    }

    /*
     添加权限
     */
    this.appendRelation = function (userId, type, menuId, opId, judge) {
        return $.post(ctx + '/system/relation/appendRelation', {
            userId: userId,
            type: type,
            menuId: menuId,
            opId: opId,
            judge: judge
        });
    }

});

/**
 日志管理Service
 */
adminApp.service('syslogFnService', function () {
    /*
     查询信息（分页）
     指定页：pageIndex
     每页条数：pageSize
     */
    this.syslogPageList = function (sysLog, pageIndex, pageSize) {
        return $.post(ctx + '/syslog/pageList', {
            "loginName": sysLog.loginName,
            "to": sysLog.to,
            "from": sysLog.from,
            "operation": sysLog.operation,
            userId: sysLog.userId,
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "sort": null,
            "order": null
        });
    };

    this.getSyslogById = function (id) {
        return $.post(ctx + '/syslog/getSyslog/' + id);
    }
});

/**
 数据字典管理Service
 */
adminApp.service('uniformFnService', function () {


    /*
     添加信息
     对象：gameArea
     */
    this.uniAdd = function (uniform) {
        if (uniform.gameId == "" || uniform.gameId == undefined) {
            uniform.gameId = -1;
        }
        if (uniform.siteId == "" || uniform.siteId == undefined) {
            uniform.siteId = -1;
        }
        return $.post(ctx + '/unify/uniform/insert', uniform);
    }

    /*
     查询信息
     对象：gameArea
     */
    this.uniAll = function (gameId, siteId) {
        if (gameId == "" || gameId == undefined) {
            gameId = -1;
        }
        if (siteId == "" || siteId == undefined) {
            siteId = -1;
        }
        return $.post(ctx + '/unify/uniform/query', {gameId: gameId, siteId: siteId});
    }

    /*
     根据key查询详细信息
     对象：gameArea
     */
    this.uniGetKey = function (uniform) {
        if (uniform.gameId == "" || uniform.gameId == undefined) {
            uniform.gameId = -1;
        }
        if (uniform.siteId == "" || uniform.siteId == undefined) {
            uniform.siteId = -1;
        }
        return $.post(ctx + '/unify/uniform/getKey', uniform);
    }

    /*
     根据key删除
     对象：gameArea
     */
    this.uniDeleteByKey = function (uniform) {
        if (uniform.gameId == "" || uniform.gameId == undefined) {
            uniform.gameId = -1;
        }
        if (uniform.siteId == "" || uniform.siteId == undefined) {
            uniform.siteId = -1;
        }
        return $.post(ctx + '/unify/uniform/deleteKey', uniform);
    }

    /*
    修改
     */
    this.uniUpdate = function (uniform) {
        if (uniform.gameId == "" || uniform.gameId == undefined) {
            uniform.gameId = -1;
        }
        if (uniform.siteId == "" || uniform.siteId == undefined) {
            uniform.siteId = -1;
        }
        return $.post(ctx + '/unify/uniform/update', uniform);
    }


    /*
     修改
     */
    this.refreshCache = function (uniform) {
        return $.post(ctx + '/unify/uniform/refreshCache');
    }



});

/**
 用户管理Service
 */
adminApp.service('userFnService', function () {
    /*
     根据userName查询user信息（分页，userName可传空值）
     角色名称：userName
     指定页：pageIndex
     每页条数：pageSize
     */
    this.userFindByName = function (loginName, pageIndex, pageSize) {
        return $.post(ctx + '/system/user/pageList', {
            "loginName": loginName,
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "sort": null,
            "order": null
        });
    };

    /*
     添加user信息
     用户对象：user
     */
    this.userAdd = function (user) {
        return $.post(ctx + '/system/user/addUser', user);
    }

    /*
     修改user信息
     用户对象：user
     */
    this.userUpdate = function (user) {
        return $.post(ctx + '/system/user/updateUser', user);
    }

    /*
     根据id查询user信息
     角色id：userId
     */
    this.userGetById = function (userId) {
        return $.get(ctx + '/system/user/editUser/' + userId);
    }

    /*
     删除user信息
     角色id：userId
     */
    this.userDelete = function (userId) {
        return $.get(ctx + '/system/user/delUse/' + userId);
    }

    /*
     获取权限信息
     */
    this.getPermission = function (accredit) {
        return $.get(ctx + '/system/relation/getPermission', accredit);
    }

    /*
     获取权限信息
     */
    this.setPermission = function (accredit) {
        return $.post(ctx + '/system/relation/setPermission', accredit);
    }

    /*
     根据父id改权限
     */
    this.appendAllRelation = function (accredit) {
        return $.post(ctx + '/system/relation/appendAllRelation', accredit);
    }

    /*
     用户添加角色
     */
    this.setRole = function (role) {
        return $.post(ctx + '/system/relation/setRole', role);
    }

    /*
     获取权限信息
     */
    this.getRole = function (role) {
        return $.get(ctx + '/system/relation/getRole', role);
    }

    /*
     修改状态
     */
    this.userStateUpdate = function (user) {
        return $.post(ctx + '/system/user/updateState', user);
    }

    /*
     修改状态
     */
    this.updUserPwd = function (user) {
        return $.post(ctx + '/system/user/updUserPwd', user);
    }

    /*
     登出
     */
    this.logout = function () {
        return $.ajax({
            type: "post",
            url: ctx + '/system/logout',
            async: false
        });
    }

    /*
     用户习惯编辑/site,game
     */
    this.habitEdit = function (value) {
        return $.post(ctx + '/system/user/habitEdit', {value: value});
    }

    /*
     用户习惯编辑 url
     */
    this.habitUrlEdit = function (value) {
        return $.post(ctx + '/system/user/habitUrlEdit', {value: value});
    }
});