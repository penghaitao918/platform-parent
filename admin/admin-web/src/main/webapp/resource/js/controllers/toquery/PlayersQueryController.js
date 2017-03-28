adminApp.config(function ($routeProvider) {
    $routeProvider.when('/toyQuery/view', {
        templateUrl: ctx + '/toyQuery/playersQueryInit',
        controller: 'playersController'
    });
});

adminApp.controller('playersController', function ($scope, gameAreaFnService, roleQueryService, $rootScope, playersQueryService, $translate) {
    $scope.playersSearchBox = 0;//0代表搜素框显示，结果信息隐藏，反之。
    $scope.playersSearchData = {};//搜索条件
    $scope.playersSearchData.gameId = $scope.gameTransfer;//初始化gameId，与全局game同步
    $scope.playersSearchData.serverIdKey = "513";
    $scope.playersSearchData.accountKey = "512";
    $scope.playersSearchData.gmMsgType = "Gm2LogicPlayerLoadReq";
    $scope.areaSearch = {};//区服查询条件
    $scope.areaSearch.gameId = $scope.gameTransfer;
    $scope.areaSearch.siteId = $scope.siteTransfer;
    $scope.condition = -1;//状态 1代表修改，2代表确定
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "PlayersQueryManage";
    $scope.playersItems = [];//查询结果转换
    $scope.updateListJson = [{
        itemName: "BlockReqAccountId",
        itemUpdateName: "BaseInfoUpdateReqAccountId"
    }, {itemName: "BlockReqBlockSecond", itemUpdateName: "BaseInfoGetResBlockSecond"}];//修改名单
    $scope.parentMenu = [{active: "active", menuName: "PlayersQueryManage"}, {
        active: "",
        menuName: "HeroQueryManage"
    }, {active: "", menuName: "ItemQueryManage"}, {active: "", menuName: "MailQueryManage"}, {
        active: "",
        menuName: "RuneQueryManage"
    }, {
        active: "",
        menuName: "FriendQueryManage"
    }];//菜单栏
    $scope.tabPaneState = "";//菜单选定项
    $scope.heroUpdateData = {};//英雄修改数据
    $scope.playerMailIds = "";//玩家邮件id集合
    $scope.playerFriendIds = "";//玩家好友id集合
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.messagesConfirm = {};//设置确认框model数据集合
    $scope.itemUpdateData = {}//玩家物品集合（修改）
    $scope.runeUpdateData = {};//玩家符文集合
    /**
     *gameId改变时，查询自定义表单
     */
    $scope.gameIdChange = function (gameId) {
        $scope.areaSearch.gameId = gameId;
        /*
         根据全局game，site查询区服
         */
        gameAreaFnService.gameAreaFindByGameId($scope.areaSearch).success(function (response) {
            $scope.areaLists = response;
            $scope.$apply();
        });
    };

    /**
     * 菜单单击
     * @param name 菜单名称
     */
    $scope.menuClick = function (name) {
        $scope.tabPaneState = name;
        if (name == "HeroQueryManage") {
            $scope.playersSearchData.gmMsgType = "Gm2LogicPlayerHeroInfoGetReq";//获取玩家英雄信息指令
            playersQueryService.getHeroInfo($scope.playersSearchData).success(function (data) {
                //$scope.heroData = JSON.parse(data);
                $scope.heroData = [{
                    GetHeroResId: 1,
                    GetHeroResName: "齐天大圣",
                    GetHeroResLv: 27,
                    GetHeroResExp: 23,
                    GetHeroResQuality: "紫色",
                    GetHeroResSkillCount: 2,
                    GetHeroResEquipCount: 1,
                    heroSkillJson: [{UpdateHeroReqSkilliD: "天崩地裂"}, {UpdateHeroReqSkilliD: "天旋地转"}],
                    heroEquipJson: [{GetHeroResEquipId: "小刀"}]
                }, {
                    GetHeroResId: 2,
                    GetHeroResName: "提莫",
                    GetHeroResLv: 27,
                    GetHeroResExp: 23,
                    GetHeroResQuality: "橙色",
                    GetHeroResSkillCount: 2,
                    GetHeroResEquipCount: 1,
                    heroSkillJson: [{UpdateHeroReqSkilliD: "天崩地裂"}, {UpdateHeroReqSkilliD: "天旋地转"}],
                    heroEquipJson: [{GetHeroResEquipId: "小刀"}]
                }, {
                    GetHeroResId: 1,
                    GetHeroResName: "德玛西亚",
                    GetHeroResLv: 27,
                    GetHeroResExp: 23,
                    GetHeroResQuality: "紫色",
                    GetHeroResSkillCount: 2,
                    GetHeroResEquipCount: 1,
                    heroSkillJson: [{UpdateHeroReqSkilliD: "天崩地裂"}, {UpdateHeroReqSkilliD: "天旋地转"}],
                    heroEquipJson: [{GetHeroResEquipId: "小刀"}]
                }];
                $scope.$apply();
            });


        } else if (name == "ItemQueryManage") {
            $scope.playersSearchData.gmMsgType = "Gm2LogicPlayerItemInfoGetReq";//获取玩家物品信息指令
            playersQueryService.getGoodsInfo($scope.playersSearchData).success(function (data) {
                $scope.itemData = JSON.parse(data);
                $scope.$apply();
            });
            //$scope.itemData = [{itemId: 1, itemCount: 23}, {itemId: 2, itemCount: 12}, {itemId: 1, itemCount: 17}];
        } else if (name == "MailQueryManage") {
            $scope.playersSearchData.gmMsgType = "Gm2LogicPlayerMailInfoGetReq";//获取玩家物品信息指令
            playersQueryService.getMailInfo($scope.playersSearchData).success(function (data) {
                $scope.mailData = [{
                    GetMailResMailId: 1,
                    GetMailResTitle: "游戏补偿",
                    GetMailResGetTime: "2017/1/17",
                    GetMailResAccessoryCount: 1,
                    GetMailResAccessoryIsGet: "是",
                    accessoryJson: [{GetMailResAccessoryItemId: "菜刀"}]
                }, {
                    GetMailResMailId: 2,
                    GetMailResTitle: "bug补偿",
                    GetMailResGetTime: "2017/1/18",
                    GetMailResAccessoryCount: 1,
                    GetMailResAccessoryIsGet: "是",
                    accessoryJson: [{GetMailResAccessoryItemId: "金箍棒"}]
                }, {
                    GetMailResMailId: 3,
                    GetMailResTitle: "全服补偿",
                    GetMailResGetTime: "2017/1/19",
                    GetMailResAccessoryCount: 1,
                    GetMailResAccessoryIsGet: "否",
                    accessoryJson: [{GetMailResAccessoryItemId: "屠龙刀"}]
                }];
                //$scope.itemData = JSON.parse(data);
                //$scope.itemData = $scope.mailData;
                $scope.$apply();
            });


        } else if (name == "RuneQueryManage") {
            $scope.playersSearchData.gmMsgType = "Gm2LogicPlayerRuneInfoGetReq";//获取玩家物品信息指令
            playersQueryService.getRuneInfo($scope.playersSearchData).success(function (data) {
                $scope.runeData = [{GetRuneResId: 1, GetRuneResType: "攻击符文", GetRuneResLv: 6}, {
                    GetRuneResId: 2,
                    GetRuneResType: "防御符文",
                    GetRuneResLv: 6
                }, {GetRuneResId: 3, GetRuneResType: "生命符文", GetRuneResLv: 6}];
                //$scope.itemData = JSON.parse(data);
                //$scope.itemData = $scope.runeData;
                $scope.$apply();
            });

        } else if (name == "FriendQueryManage") {
            $scope.playersSearchData.gmMsgType = "Gm2LogicPlayerFriendInfoGetReq";//获取玩家物品信息指令
            playersQueryService.getFriendInfo($scope.playersSearchData).success(function (data) {
                $scope.friendData = [{
                    GetFriendResID: 1,
                    GetFriendResName: "奔跑的犀牛",
                    GetFriendResLv: 60,
                    GetFriendResIsOnline: "在线"
                }, {
                    GetFriendResID: 2,
                    GetFriendResName: "小ka",
                    GetFriendResLv: 58,
                    GetFriendResIsOnline: "在线"
                }, {GetFriendResID: 3, GetFriendResName: "轩辕淼", GetFriendResLv: 60, GetFriendResIsOnline: "在线"}];
                //$scope.itemData = JSON.parse(data);
                //$scope.itemData = $scope.runeData;
                $scope.$apply();
            });

        }
    }

    /**
     * 修改英雄信息
     * @param heroData
     */
    $scope.heroUpdateClicked = function (heroData) {
        $scope.heroUpdateData = heroData;
        $scope.heroUpdateData.UpdateHeroReqHeroId = heroData.GetHeroResId;
        $scope.heroUpdateData.GetHeroResName = heroData.GetHeroResName;
        $scope.heroUpdateData.UpdateHeroReqExp = heroData.GetHeroResExp;
        $scope.heroUpdateData.UpdateHeroReqQuality = heroData.GetHeroResQuality;
        $("#heroUpdateViewModal").modal('show');
    }

    ///**
    // 监听英雄修改表单
    // */
    //$scope.heroUpdateForm = function () {
    //    console.log($scope.heroUpdateData);
    //    $("#heroUpdateViewModal").modal('hide');
    //}

    /**
     * 修改物品信息
     * @param heroData
     */
    $scope.itemUpdateClicked = function (itemData) {
        $scope.itemUpdateData.UpdateItemReqItemCount = itemData.GetItemResItemCount;
        $("#itemUpdateViewModal").modal('show');
    }

    /**
     监听物品修改表单
     */
    $scope.itemUpdateForm = function () {
        var itemUpdate = [];

        var dicItem = {};
        dicItem.key = "UpdateItemReqItemCount";
        dicItem.value = $scope.itemUpdateData.UpdateItemReqItemCount;
        itemUpdate.push(dicItem);
        $scope.playersSearchData.gmMsgType = "Gm2LogicPlayerItemInfoUpdateReq";
        $scope.playersSearchData.itemData = JSON.stringify(itemUpdate);
        playersQueryService.setGoodsInfo($scope.playersSearchData).success(function () {
            $scope.menuClick("ItemQueryManage");//刷新
        });
        console.log($scope.playersSearchData);
        $("#itemUpdateViewModal").modal('hide');
    }

    /**
     监听英雄修改表单
     */
    $scope.heroUpdateForm = function () {
        var heroUpdate = [];

        var dicItem = {};
        dicItem.key = "UpdateHeroReqHeroId";
        dicItem.value = $scope.heroUpdateData.UpdateHeroReqHeroId;
        heroUpdate.push(dicItem);

        dicItem.key = "UpdateHeroReqExp";
        dicItem.value = $scope.heroUpdateData.UpdateHeroReqExp;
        heroUpdate.push(dicItem);

        dicItem.key = "UpdateHeroReqQuality";
        dicItem.value = $scope.heroUpdateData.UpdateHeroReqQuality;
        heroUpdate.push(dicItem);

        dicItem.key = "UpdateHeroReqSkilliD";
        dicItem.value = $scope.heroUpdateData.UpdateHeroReqSkilliD;
        heroUpdate.push(dicItem);

        dicItem.key = "UpdateHeroReqSkillIndex";
        dicItem.value = $scope.heroUpdateData.UpdateHeroReqSkillIndex;
        heroUpdate.push(dicItem);

        $scope.playersSearchData.gmMsgType = "Gm2LogicPlayerHeroInfoUpdateReq";
        $scope.playersSearchData.itemData = JSON.stringify(heroUpdate);
        playersQueryService.setHeroInfo($scope.playersSearchData).success(function () {
            $scope.menuClick("HeroQueryManage");//刷新
        });
        console.log($scope.playersSearchData);
        $("#heroUpdateViewModal").modal('hide');
    }

    /**
     * 装备查询
     * @param heroData
     */
    $scope.equipViewClicked = function (heroData) {
        $scope.heroEquipCode = heroData.heroEquipJson;
        $("#equipViewModal").modal("show");
    }

    /**
     * 技能查询
     * @param heroData
     */
    $scope.skillViewClicked = function (heroData) {
        $scope.heroSkillCode = heroData.heroSkillJson;
        $("#skillViewModal").modal("show");
    }

    /**
     * 邮件附件物品查询
     * @param heroData
     */
    $scope.accessoryViewClicked = function (heroData) {
        $scope.mailItemCode = heroData.accessoryJson;
        $("#mailViewModal").modal("show");
    }

    /**
     * 修改符文等级
     * @param runeData
     */
    $scope.runeUpdateClicked = function (runeData) {
        $scope.runeUpdateData.UpdateRuneReqLv = runeData.GetRuneResLv;
        $("#runeUpdateViewModal").modal('show');
    }

    /**
     监听符文修改表单
     */
    $scope.runeUpdateForm = function () {
        console.log($scope.runeUpdateData);
        var itemUpdate = [];

        var dicItem = {};
        dicItem.key = "UpdateRuneReqLv";
        dicItem.value = $scope.runeUpdateData.UpdateRuneReqLv;
        itemUpdate.push(dicItem);
        $scope.playersSearchData.gmMsgType = "Gm2LogicPlayerRuneInfoUpdateReq";
        $scope.playersSearchData.itemData = JSON.stringify(itemUpdate);
        playersQueryService.setRuneInfo($scope.playersSearchData).success(function () {
            $scope.menuClick("RuneQueryManage");//刷新
        });
        console.log($scope.runeUpdateData);
        $("#runeUpdateViewModal").modal('hide');
    }

    /**
     删除邮件confirm
     */
    $scope.mailDelete = function () {

        if ($scope.playerMailIds != "") {
            $scope.messagesConfirm.title = "load.messagesConfirmTitleByDelete";
            $scope.messagesConfirm.body = "load.messagesConfirmBodyByDelete";
            $("#mailMessagesConfirm").modal('show');
        } else {
            $scope.messagesData.messagesTitle = "load.promptTitle";
            $scope.messagesData.messagesBody = "load.promptBody";
            $("#messagesModal").modal('show');
        }


    };

    /**
     删除邮件确认
     */
    $scope.mailConfirm = function () {
        var itemUpdate = [];

        var dicItem = {};
        dicItem.key = "UpdateMailReqDeleteMailId";
        dicItem.value = $scope.playerMailIds;
        itemUpdate.push(dicItem);
        $scope.playersSearchData.gmMsgType = "Gm2LogicPlayerMailInfoUpdateReq";
        $scope.playersSearchData.itemData = JSON.stringify(itemUpdate);
        playersQueryService.setMailInfo($scope.playersSearchData).success(function () {
            $scope.menuClick("MailQueryManage");//刷新
        });
        console.log($scope.playerMailIds);
        $("#mailMessagesConfirm").modal('hide');
    };

    /**
     更改邮件的选择状态，并保存相应的id
     */
    $scope.friendCheck = function (val, chk) {
        if (chk == true) {
            $scope.playerFriendIds += val + ",";
        } else {
            $scope.playerFriendIds = $scope.playerFriendIds.replace(val + ",", "");
        }

    };


    /**
     删除好友confirm
     */
    $scope.friendDelete = function () {

        if ($scope.playerFriendIds != "") {
            $scope.messagesConfirm.title = "load.messagesConfirmTitleByDelete";
            $scope.messagesConfirm.body = "load.messagesConfirmBodyByDelete";
            $("#friendMessagesConfirm").modal('show');
        } else {
            $scope.messagesData.messagesTitle = "load.promptTitle";
            $scope.messagesData.messagesBody = "load.promptBody";
            $("#messagesModal").modal('show');
        }


    };

    /**
     删除好友确认
     */
    $scope.friendConfirm = function () {
        var itemUpdate = [];

        var dicItem = {};
        dicItem.key = "UpdateFriendReqDeleteFriendID";
        dicItem.value = $scope.playerFriendIds;
        itemUpdate.push(dicItem);
        $scope.playersSearchData.gmMsgType = "Gm2LogicPlayerFriendInfoUpdateReq";
        $scope.playersSearchData.itemData = JSON.stringify(itemUpdate);
        playersQueryService.setFriendInfo($scope.playersSearchData).success(function () {
            $scope.menuClick("FriendQueryManage");//刷新
        });
        console.log($scope.playerFriendIds);
        $("#friendMessagesConfirm").modal('hide');
        console.log($scope.playerFriendIds);//删除方法
    };

    /**
     更改好友的选择状态，并保存相应的id
     */
    $scope.mailCheck = function (val, chk) {
        if (chk == true) {
            $scope.playerMailIds += val + ",";
        } else {
            $scope.playerMailIds = $scope.playerMailIds.replace(val + ",", "");
        }

    };

    /**
     * 监听玩家查询表单
     */
    $scope.playersSearchForm = function () {
        $scope.playersSearchBox = 1;
        $scope.condition = 1;
        $scope.playersItems = [];
        playersQueryService.getPlayersInfo($scope.playersSearchData).success(function (data) {
            data = '{"LoadReqAccountId":"2323d 23","LoadReqServerId":"多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多的反馈老妇女失控裸女的咖啡但是可能的服了你上岛咖啡的烦恼斯柯达你发","LoadResRes":"大幅度发大幅度发大幅度发的冯绍","BlockReqAccountId":"大幅度发的发带你看发到付呢的可能反打是啊的狂蜂浪蝶你分段放得开发你",' +
                '"BlockReqServerId":"大幅度发的发带你看发到付呢的可能反打是啊的狂蜂浪蝶你分段放得开发你","BlockReqBlockSecond":"大幅度发你","BlockReqBlockRes":"2323d 23","BlockResRes":"金币","BaseInfoGetReqAccountId":"副本货币","BaseInfoGetReqServerId":"充值货币"}';
            var dataItem = [];
            if (data != "" && data != null) {
                dataItem = JSON.parse(data);
            }

            //拼装前端js数据
            for (var key in dataItem) {
                var item = {};
                item.dataTag = key;
                item.name = "load." + key;
                item.value = dataItem[key];
                item.identity = -1;
                $scope[key] = dataItem[key];
                $scope.playersItems.push(item);
            }
            $scope.tabPaneState = "PlayersQueryManage";
            $scope.$apply();
        });
    }


    /**
     * 更改修改状态
     */
    $scope.playersTransform = function () {
        $scope.condition = 2;
        for (var j = 0; j < $scope.playersItems.length; j++) {
            for (var i = 0; i < $scope.updateListJson.length; i++) {
                if ($scope.playersItems[j].dataTag == $scope.updateListJson[i].itemName) {
                    $scope.playersItems[j].identity = 0;
                }
            }
        }

    }

    /**
     * 监听修改表单
     */
    $scope.playersUpdateForm = function () {
        var playersUpdateData = [];
        for (var j = 0; j < $scope.playersItems.length; j++) {
            for (var i = 0; i < $scope.updateListJson.length; i++) {
                if ($scope.playersItems[j].dataTag == $scope.updateListJson[i].itemName) {
                    var dicItem = {};
                    dicItem.key = $scope.updateListJson[i].itemUpdateName;
                    dicItem.value = $scope[$scope.playersItems[j].dataTag];
                    playersUpdateData.push(dicItem);
                }
            }

        }
        console.log(playersUpdateData);
        $scope.playersSearchData.itemData = JSON.stringify(playersUpdateData);
        $scope.playersSearchData.gmMsgType = "Gm2LogicPlayerBaseInfoUpdateReq";
        playersQueryService.updatePlayersInfo($scope.playersSearchData).success(function (data) {
            $scope.condition = 1;
            $scope.playersSearchForm();//刷新
        });
    }

    /**
     * 返回搜索
     */
    $scope.returnSearchBox = function () {
        $scope.playersSearchBox = 0;
        $scope.playersSearchData.roleId = "";
        $scope.playersSearchData.userId = "";
        $scope.playersSearchData.roleName = "";
        $scope.playersItems = [];
    }

    /**
     * 清空
     */
    $scope.empty = function () {
        $scope.playersSearchData = {};
        $scope.playersItems = [];
    }


    /*
     根据全局game，site查询区服
     */
    gameAreaFnService.gameAreaFindByGameId($scope.areaSearch).success(function (response) {
        $scope.areaLists = response;
        $scope.$apply();
    });
});

