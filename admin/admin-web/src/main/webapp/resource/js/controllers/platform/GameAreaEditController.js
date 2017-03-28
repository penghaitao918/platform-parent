/**
 * 区服添加controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/gameAreaEdit/view', {
        templateUrl: ctx + '/platform/gameArea/gameAreaEditInit',
        controller: 'gameAreaEditController'
    }).when("/gameAreaEdit/view/:id/:judge", {
        templateUrl: ctx + '/platform/gameArea/gameAreaEditInit',
        controller: 'gameAreaEditController'
    });
    ;
});


/**
 区服编辑controller
 全局：$scope
 */
adminApp.controller('gameAreaEditController', function ($scope, gameAreaFnService, $location, uniformFnService, gameRegionFnService, $routeParams,$rootScope) {
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "GameAreaEditManage";
    $rootScope.menuBarData.menuBarTitle = "GameAreaEditManage";

    $scope.selectedId = [];//渠道id集合
    $scope.selectedCode = [];//渠道code集合
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.channelCode = [];//渠道code集合（显示项）
    $scope.gameAreasData = {};//区服对象集合
    $scope.gameAreasData.gameId = $scope.gameTransfer;//初始化gameId，与全局game同步
    $scope.gameToyGameList = $scope.gameTransfer;//gameToyGameList，与全局game同步
    $scope.gameSelected = $scope.gameTransfer;//更改gameId选择项
    $scope.uniFormData = {};//下拉框选项集合key对象初始化
    $scope.areaTagList = [];//动态下拉框初始化
    $scope.regionList = [];//大区初始化
    $scope.gameAreasData.valueChange = false;//是否修改过值
    $scope.EnterDressDataBase = {};
    $scope.EnterDressSocket = {};
    $scope.serverEnterAddr = {};
    var check = -1;//判断是否成功，url参数 1，是修改成功，0是修改失败，2是添加成功，3是添加失败，-1是失效


    /**
     加载大区
     */
    function gameRegionFindByGameIdPublic(gameId) {
        gameRegionFnService.gameRegionFindByGameId(gameId).success(function (data) {
            $scope.regionList = data;
        });
    }

    /**
     加载大区
     */
    gameRegionFindByGameIdPublic($scope.gameAreasData.gameId);


    /**
     绑定game,site select，change事件
     */
    $scope.resourceGameListChange = function (id) {
        $scope.gameAreasData.gameId = id;
        $scope.gameAreasData.valueChange = true;
        if ($scope.gameAreasData.gameId != 1) {
            $(".jointAccess").hide();
            $(".clientConnectionEntry").hide();
        } else {
            $(".clientConnectionEntry").show();
            $(".jointAccess").show();
        }
        /*
         加载大区
         */
        gameRegionFindByGameIdPublic($scope.gameAreasData.gameId);
    };


    $scope.uniFormData.key = "platform.gameArea.areaTag";//初始化动态下拉框的key
    /**
     加载下拉框
     */
    uniformFnService.uniGetKey($scope.uniFormData).success(function (response) {
        $scope.areaTagList = JSON.parse(response.itemJson);
        /**
         * 如果是区服修改，或者copy，根据id查询区服信息
         */
        if ($routeParams.judge == "update" || $routeParams.judge == "copy") {

            //根据id获取区服信息
            gameAreaFnService.gameAreaGetById($routeParams.id).success(function (data) {
                if ($routeParams.judge == "update") {
                    $(".areaAreaCode").attr("readonly", true);
                }
                 $scope.gameAreasData.areaCode=Number(data.areaCode);
                //$scope.gameAreasData.areaCode = data.areaCode;
                $scope.gameAreasData.areaName = data.areaName;
                $scope.gameAreasData.areaGroupId = data.areaGroupId;
                $scope.gameAreasData.availableTime = $scope.format(data.availableTime, "yyyy-MM-dd HH:mm:ss");
                /*   $scope.gameAreasData.supportVersionMin=Number(data.supportVersionMin);
                 $scope.gameAreasData.supportVersionMax=Number(data.supportVersionMax);*/
                $scope.gameAreasData.supportVersionMin = Number(data.supportVersionMin);
                $scope.gameAreasData.supportVersionMax = Number(data.supportVersionMax);
                $scope.gameAreasData.displayOrder = data.displayOrder;
                $scope.gameAreasData.areaDesc = data.areaDesc;
                $scope.gameAreasData.areaTag = data.areaTag.toString();
                $scope.gameAreasData.loadStatus = data.loadStatus.toString();
                $scope.gameAreasData.areaGroupId = data.areaGroup.id;
                $scope.clientContent.clientData = JSON.parse(data.clientEnterAddr);
                $scope.serverEnterAddr = JSON.parse(data.serverEnterAddr);
                $scope.gameAreasData.gameId = $scope.gameToyGameList;
                if ($scope.serverEnterAddr.connectType == "DATABASE") {
                    $scope.connectType = $scope.serverEnterAddr.connectType;
                    $scope.EnterDressDataBase.ipAddress = $scope.serverEnterAddr.ipAddress;
                    $scope.EnterDressDataBase.port = $scope.serverEnterAddr.port;
                    $scope.EnterDressDataBase.sqlName = $scope.serverEnterAddr.sqlName;
                    $scope.EnterDressDataBase.sqlAccount = $scope.serverEnterAddr.sqlAccount;
                    $scope.EnterDressDataBase.sqlPassword = $scope.serverEnterAddr.sqlPassword;
                } else if ($scope.serverEnterAddr.connectType == "SOCKET") {
                    $scope.connectType = $scope.serverEnterAddr.connectType;
                    $scope.EnterDressSocket.socketKey = $scope.serverEnterAddr.socketKey;
                    $scope.EnterDressSocket.ipAddress = $scope.serverEnterAddr.ipAddress;
                    $scope.EnterDressSocket.port = $scope.serverEnterAddr.port;
                }


                if ($scope.serverEnterAddr.connectType == "DATABASE") {
                    $(".dataBase").show();
                    $(".socket").hide();
                } else {
                    $(".dataBase").hide();
                    $(".socket").show();
                }


                $scope.validStatus = data.validStatus.toString();
                $scope.maintenanceStatus = data.maintenanceStatus.toString();
                $scope.gameToyGameList = data.gameId;
                $scope.gameAreasData.gameId = data.gameId;
                /*
                 渠道初始化
                 */
                var checkbox = $(".children");
                var siteIds = data.siteIds.split(",");
                for (var i = 0; i < siteIds.length; i++) {
                    for (var j = 0; j < $scope.siteList.length; j++) {
                        if (siteIds[i] == $scope.siteList[j].id) {
                            $scope.selectedCode.push($scope.siteList[j].code);
                            $scope.selectedId.push($scope.siteList[j].id);
                        }

                        /*
                         遍历checkbox，并初始化
                         */
                        for (var k = 0; k < checkbox.length; k++) {
                            if (checkbox[k].id == siteIds[i]) {
                                checkbox[k].checked = true;
                            }
                        }
                    }
                }
                $scope.channelCode = $scope.selectedCode;
                $scope.$apply();
            });
        }
    });

    $scope.clientCount = 0;
    $scope.clientContent = new Object();
    $scope.clientContent.clientData = [{ipAddress: "", port: "", serverName: ""}];
    // 初始化时由于只有1条回复，所以不允许删除
    $scope.clientContent.canDescReply = false;
    // 增加
    $scope.clientContent.clientAdd = function ($index) {
        if ($scope.clientCount < 4) {
            $scope.clientContent.clientData.splice($index + 1, 0, {ipAddress: "", port: "", serverName: ""});
            // 增加后允许删除
            $scope.clientContent.canDescReply = true;
            $scope.clientCount++;
        } else {
            $scope.messagesData.messagesTitle = "load.messagesTitleByForm";
            $scope.messagesData.messagesBody = "load.messagesBodyByForm";
            $("#messagesModal").modal('show');
        }

    }

    // 减少
    $scope.clientContent.clientDelete = function ($index) {
        // 如果回复数大于1，删除被点击回复
        if ($scope.clientContent.clientData.length > 1) {
            $scope.clientContent.clientData.splice($index, 1);
            $scope.clientCount--;
        }
        // 如果回复数为1，不允许删除
        if ($scope.clientContent.clientData.length == 1) {
            $scope.clientContent.canDescReply = false;
        }
    }

    /**
     绑定change事件，判断输入框是否改变过值
     */
    $scope.valueChange = function (data) {
        $scope.gameAreasData.valueChange = true;
        console.log($scope.gameAreasData.valueChange);
    }

    if ($scope.gameAreasData.gameId != 1) {
        $(".jointAccess").hide();
        $(".clientConnectionEntry").hide();
    } else {
        $(".clientConnectionEntry").show();
        $(".jointAccess").show();
    }

    /**
     监听addForm表单
     */
    $scope.gameAreaEditForm = function () {
        console.log($scope.clientContent);
        var site = "";
        for (var i = 0; i < $scope.selectedId.length; i++) {
            site += $scope.selectedId[i] + ",";
        }
        $scope.gameAreasData.siteIds = site;
        console.log($scope.gameAreasData);

        /*
         删除$$hashKey
         */
        for (var i = 0; i < $scope.clientContent.clientData.length; i++) {
            delete $scope.clientContent.clientData[i].$$hashKey;
        }

        if ($scope.connectType == "DATABASE") {
            $scope.serverEnterAddr.connectType = $scope.connectType;
            $scope.serverEnterAddr.ipAddress = $scope.EnterDressDataBase.ipAddress;
            $scope.serverEnterAddr.port = $scope.EnterDressDataBase.port;
            $scope.serverEnterAddr.sqlName = $scope.EnterDressDataBase.sqlName;
            $scope.serverEnterAddr.sqlAccount = $scope.EnterDressDataBase.sqlAccount;
            $scope.serverEnterAddr.sqlPassword = $scope.EnterDressDataBase.sqlPassword;
        } else if ($scope.connectType == "SOCKET") {
            $scope.serverEnterAddr.connectType = $scope.connectType;
            $scope.serverEnterAddr.socketKey = $scope.EnterDressSocket.socketKey;
            $scope.serverEnterAddr.ipAddress = $scope.EnterDressSocket.ipAddress;
            $scope.serverEnterAddr.port = $scope.EnterDressSocket.port;
        }

        $scope.gameAreasData.serverEnterAddr = JSON.stringify($scope.serverEnterAddr);//平台连接转成json

        $scope.gameAreasData.availableTime = new Date($scope.gameAreasData.availableTime);
        $scope.gameAreasData.valid = $scope.validStatus;
        $scope.gameAreasData.maintenance = $scope.maintenanceStatus;
        $scope.gameAreasData.clientEnterAddr = JSON.stringify($scope.clientContent.clientData);//客户端连接转成json


        /**
         judge=update调用修改方法，judge=null或者copy时，调用添加方法
         */
        if ($routeParams.judge == "update") {
            /*
             调用修改方法
             */
            $scope.gameAreasData.id = $routeParams.id;
            gameAreaFnService.gameAreaUpdate($scope.gameAreasData).success(function (data) {
                check = 1;
                if (data != null && data != "") {
                    check = data;
                }
                $location.path("/gameArea/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            }).error(function () {
                check = 0;
                $location.path("/gameArea/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            });
        } else {
            /*
             调用添加方法
             */
            gameAreaFnService.gameAreaAdd($scope.gameAreasData).success(function (data) {
                check = 2;
                if (data != null && data != "") {
                    check = data;
                }
                $location.path("/gameArea/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            }).error(function () {
                check = 3;
                $location.path("/gameArea/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            });
        }
    };

    /**
     选择渠道（弹框）
     */
    $scope.channelClick = function () {
        $scope.gameAreasData.valueChange = true;
        $("#channelAddModal").modal('show');
    }


    /**
     监听addForm表单
     */
    $scope.channelAdd = function () {
        console.log($scope.selectedId);
        console.log($scope.selectedCode);
        //$scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
        //$scope.messagesData.messagesBody = "load.messagesBodyByChannelToSuccess";
        //$("#messagesModal").modal('show');
        $("#channelAddModal").modal('hide');
        $scope.channelCode = $scope.selectedCode;

    };


    /**
     选中判断逻辑方法
     */
    var updateSelected = function (action, id, code) {
        /*
         true添加集合元素
         */
        if (action == 'add' && $scope.selectedId.indexOf(id) == -1) {
            $scope.selectedId.push(id);
            $scope.selectedCode.push(code);
        }
        /*
         false移除集合元素
         */
        if (action == 'remove' && $scope.selectedId.indexOf(id) != -1) {
            var idx = $scope.selectedId.indexOf(id);
            $scope.selectedId.splice(idx, 1);
            $scope.selectedCode.splice(idx, 1);
        }
    }

    /**
     checkbox单击操作
     */
    $scope.updateSelection = function ($event, id, code) {
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');//判断是否选中
        updateSelected(action, id, code);//选中判断逻辑方法
    }

    /**
     全选，遍历checkboxModel，正向操作
     */
    $scope.all = function (v) {
        var checkbox = $(".children");
        for (var i = 0; i < checkbox.length; i++) {
            checkbox[i].checked = true;
        }
        var idData = [];
        var codeData = [];
        $scope.siteModel = true;
        for (var i = 0; i < v.length; i++) {
            idData.push(v[i].id);
            codeData.push(v[i].code);
        }
        $scope.selectedId = idData;
        $scope.selectedCode = codeData;
    };

    /**
     全不选
     */
    $scope.deselectAll = function () {
        var checkbox = $(".children");
        for (var i = 0; i < checkbox.length; i++) {
            checkbox[i].checked = false;
        }
        $scope.selectedId = [];
        $scope.selectedCode = [];
        $scope.master = false;
    }

    /**
     反选
     */
    $scope.versa = function (c) {
        var checkbox = $(".children");
        /*
         遍历checkbox，并反向操作
         */
        for (var i = 0; i < checkbox.length; i++) {
            if (checkbox[i].checked) {
                checkbox[i].checked = false;
                var idx = $scope.selectedId.indexOf(Number(checkbox[i].id));
                $scope.selectedId.splice(idx, 1);
                $scope.selectedCode.splice(idx, 1);

            } else {
                $scope.selectedId.push(Number(checkbox[i].id));
                $scope.selectedCode.push(checkbox[i].name);
                checkbox[i].checked = true;
            }

        }
    }

    /**
     连接方式转换
     */
    $scope.mannerChange = function (Manner) {
        if (Manner == "DATABASE") {
            $(".dataBase").show();
            $(".socket").hide();
        } else {
            $(".dataBase").hide();
            $(".socket").show();
        }
    }


});