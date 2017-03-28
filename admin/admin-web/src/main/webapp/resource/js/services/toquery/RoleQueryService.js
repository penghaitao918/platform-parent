/**
 * 玩家角色查询Service文件
 * author：钟亮
 * @type {module|*}
 */
/**
 角色查询Service
 */
adminApp.service('roleQueryService', function () {
    //获取角色信息
    this.getRoleInfo = function (roleSearchData) {
        if (roleSearchData.userId == undefined || roleSearchData.userId == null || roleSearchData.userId == "") {
            roleSearchData.userId = -1;
        }
        if (roleSearchData.roleId == undefined || roleSearchData.roleId == null || roleSearchData.roleId == "") {
            roleSearchData.roleId = -1;
        }
        if (roleSearchData.areaId == undefined || roleSearchData.areaId == null || roleSearchData.areaId == "") {
            roleSearchData.areaId = -1;
        }

        return $.post(ctx + '/roleQuery/getRoleInfo', {
            "gameId": roleSearchData.gameId,
            "areaId": roleSearchData.areaId,
            "userId": roleSearchData.userId,
            "roleId": roleSearchData.roleId,
            "roleName": roleSearchData.roleName
        });
    };
});

/**
 * 玩家查询
 */
adminApp.service('playersQueryService', function () {
    //获取玩家基本信息
    this.getPlayersInfo = function (roleSearchData) {
        if (roleSearchData.userId == undefined || roleSearchData.userId == null || roleSearchData.userId == "") {
            roleSearchData.userId = -1;
        }
        if (roleSearchData.roleId == undefined || roleSearchData.roleId == null || roleSearchData.roleId == "") {
            roleSearchData.roleId = -1;
        }
        if (roleSearchData.serverId == undefined || roleSearchData.serverId == null || roleSearchData.serverId == "") {
            roleSearchData.serverId = -1;
        }
        if (roleSearchData.roleName == null) {
            roleSearchData.roleName = "";
        }
        return $.post(ctx + '/toyQuery/getPlayersInfo', {
            "gameId": roleSearchData.gameId,
            "userId": roleSearchData.userId,
            "serverId": roleSearchData.serverId,
            "roleId": roleSearchData.roleId,
            "serverIdKey": roleSearchData.serverIdKey,
            "accountKey": roleSearchData.accountKey,
            "roleName": roleSearchData.roleName,
            "gmMsgType": roleSearchData.gmMsgType
        });
    };

    //修改玩家基本信息
    this.updatePlayersInfo = function (updateData) {
        return $.post(ctx + '/toyQuery/updatePlayersInfo', {
            "gameId": updateData.gameId,
            "userId": updateData.userId,
            "serverId": updateData.serverId,
            "roleId": updateData.roleId,
            "roleName": updateData.roleName,
            "gmMsgType": updateData.gmMsgType,
            "itemData": updateData.itemData
        });
    };

    //获取物品信息
    this.getGoodsInfo = function (goodsSearchData) {
        if (goodsSearchData.userId == undefined || goodsSearchData.userId == null || goodsSearchData.userId == "") {
            goodsSearchData.userId = -1;
        }
        if (goodsSearchData.roleId == undefined || goodsSearchData.roleId == null || goodsSearchData.roleId == "") {
            goodsSearchData.roleId = -1;
        }
        if (goodsSearchData.serverId == undefined || goodsSearchData.serverId == null || goodsSearchData.serverId == "") {
            goodsSearchData.serverId = -1;
        }
        if (goodsSearchData.roleName == null) {
            goodsSearchData.roleName = "";
        }
        return $.post(ctx + '/toyQuery/getGoodsInfo', {
            "gameId": goodsSearchData.gameId,
            "userId": goodsSearchData.userId,
            "serverId": goodsSearchData.serverId,
            "roleId": goodsSearchData.roleId,
            "serverIdKey": goodsSearchData.serverIdKey,
            "accountKey": goodsSearchData.accountKey,
            "roleName": goodsSearchData.roleName,
            "gmMsgType": goodsSearchData.gmMsgType
        });

    };

    //修改物品信息
    this.setGoodsInfo = function (goodsUpdateData) {
        return $.post(ctx + '/toyQuery/setGoodsInfo', {
            "gameId": goodsUpdateData.gameId,
            "userId": goodsUpdateData.userId,
            "serverId": goodsUpdateData.serverId,
            "roleId": goodsUpdateData.roleId,
            "roleName": goodsUpdateData.roleName,
            "gmMsgType": goodsUpdateData.gmMsgType,
            "itemData": goodsUpdateData.itemData
        });
    }


    //获取玩家英雄信息
    this.getHeroInfo = function (heroSearchData) {
        if (heroSearchData.userId == undefined || heroSearchData.userId == null || heroSearchData.userId == "") {
            heroSearchData.userId = -1;
        }
        if (heroSearchData.roleId == undefined || heroSearchData.roleId == null || heroSearchData.roleId == "") {
            heroSearchData.roleId = -1;
        }
        if (heroSearchData.serverId == undefined || heroSearchData.serverId == null || heroSearchData.serverId == "") {
            heroSearchData.serverId = -1;
        }
        if (heroSearchData.roleName == null) {
            heroSearchData.roleName = "";
        }
        return $.post(ctx + '/toyQuery/getHeroInfo', {
            "gameId": heroSearchData.gameId,
            "userId": heroSearchData.userId,
            "serverId": heroSearchData.serverId,
            "roleId": heroSearchData.roleId,
            "serverIdKey": heroSearchData.serverIdKey,
            "accountKey": heroSearchData.accountKey,
            "roleName": heroSearchData.roleName,
            "gmMsgType": heroSearchData.gmMsgType
        });
    };

    //修改玩家英雄信息
    this.setHeroInfo = function (heroUpdateData) {
        return $.post(ctx + '/toyQuery/setHeroInfo', {
            "gameId": heroUpdateData.gameId,
            "userId": heroUpdateData.userId,
            "serverId": heroUpdateData.serverId,
            "roleId": heroUpdateData.roleId,
            "roleName": heroUpdateData.roleName,
            "gmMsgType": heroUpdateData.gmMsgType,
            "itemData": heroUpdateData.itemData
        });
    };

    //获取邮件信息
    this.getMailInfo = function (mailSearchData) {
        if (mailSearchData.userId == undefined || mailSearchData.userId == null || mailSearchData.userId == "") {
            mailSearchData.userId = -1;
        }
        if (mailSearchData.roleId == undefined || mailSearchData.roleId == null || mailSearchData.roleId == "") {
            mailSearchData.roleId = -1;
        }
        if (mailSearchData.serverId == undefined || mailSearchData.serverId == null || mailSearchData.serverId == "") {
            mailSearchData.serverId = -1;
        }
        if (mailSearchData.roleName == null) {
            mailSearchData.roleName = "";
        }
        return $.post(ctx + '/toyQuery/getMailInfo', {
            "gameId": mailSearchData.gameId,
            "userId": mailSearchData.userId,
            "serverId": mailSearchData.serverId,
            "roleId": mailSearchData.roleId,
            "serverIdKey": mailSearchData.serverIdKey,
            "accountKey": mailSearchData.accountKey,
            "roleName": mailSearchData.roleName,
            "gmMsgType": mailSearchData.gmMsgType
        });

    };

    //修改玩家邮件信息
    this.setMailInfo = function (mailUpdateData) {
        return $.post(ctx + '/toyQuery/setMailInfo', {
            "gameId": mailUpdateData.gameId,
            "userId": mailUpdateData.userId,
            "serverId": mailUpdateData.serverId,
            "roleId": mailUpdateData.roleId,
            "roleName": mailUpdateData.roleName,
            "gmMsgType": mailUpdateData.gmMsgType,
            "itemData": mailUpdateData.itemData
        });
    };

    //获取符文信息
    this.getRuneInfo = function (runeSearchData) {
        if (runeSearchData.userId == undefined || runeSearchData.userId == null || runeSearchData.userId == "") {
            runeSearchData.userId = -1;
        }
        if (runeSearchData.roleId == undefined || runeSearchData.roleId == null || runeSearchData.roleId == "") {
            runeSearchData.roleId = -1;
        }
        if (runeSearchData.serverId == undefined || runeSearchData.serverId == null || runeSearchData.serverId == "") {
            runeSearchData.serverId = -1;
        }
        if (runeSearchData.roleName == null) {
            runeSearchData.roleName = "";
        }
        return $.post(ctx + '/toyQuery/getRuneInfo', {
            "gameId": runeSearchData.gameId,
            "userId": runeSearchData.userId,
            "serverId": runeSearchData.serverId,
            "roleId": runeSearchData.roleId,
            "serverIdKey": runeSearchData.serverIdKey,
            "accountKey": runeSearchData.accountKey,
            "roleName": runeSearchData.roleName,
            "gmMsgType": runeSearchData.gmMsgType
        });

    };

    //修改玩家符文信息
    this.setRuneInfo = function (runeUpdateData) {
        return $.post(ctx + '/toyQuery/setRuneInfo', {
            "gameId": runeUpdateData.gameId,
            "userId": runeUpdateData.userId,
            "serverId": runeUpdateData.serverId,
            "roleId": runeUpdateData.roleId,
            "roleName": runeUpdateData.roleName,
            "gmMsgType": runeUpdateData.gmMsgType,
            "itemData": runeUpdateData.itemData
        });
    };


    //获取玩家好友信息
    this.getFriendInfo = function (friendSearchData) {
        if (friendSearchData.userId == undefined || friendSearchData.userId == null || friendSearchData.userId == "") {
            friendSearchData.userId = -1;
        }
        if (friendSearchData.roleId == undefined || friendSearchData.roleId == null || friendSearchData.roleId == "") {
            friendSearchData.roleId = -1;
        }
        if (friendSearchData.serverId == undefined || friendSearchData.serverId == null || friendSearchData.serverId == "") {
            friendSearchData.serverId = -1;
        }
        if (friendSearchData.roleName == null) {
            friendSearchData.roleName = "";
        }
        return $.post(ctx + '/toyQuery/getFriendInfo', {
            "gameId": friendSearchData.gameId,
            "userId": friendSearchData.userId,
            "serverId": friendSearchData.serverId,
            "roleId": friendSearchData.roleId,
            "serverIdKey": friendSearchData.serverIdKey,
            "accountKey": friendSearchData.accountKey,
            "roleName": friendSearchData.roleName,
            "gmMsgType": friendSearchData.gmMsgType
        });

    };

    //修改玩家符文信息
    this.setFriendInfo = function (friendUpdateData) {
        return $.post(ctx + '/toyQuery/setFriendInfo', {
            "gameId": friendUpdateData.gameId,
            "userId": friendUpdateData.userId,
            "serverId": friendUpdateData.serverId,
            "roleId": friendUpdateData.roleId,
            "roleName": friendUpdateData.roleName,
            "gmMsgType": friendUpdateData.gmMsgType,
            "itemData": friendUpdateData.itemData
        });
    };
});

