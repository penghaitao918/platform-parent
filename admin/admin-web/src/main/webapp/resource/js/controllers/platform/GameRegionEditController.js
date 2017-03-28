/**
 * 区服管理controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/gameRegionEdit/view', {
        templateUrl: ctx + '/platform/gameRegion/gameRegionEditInit',
        controller: 'gameRegionEditController'
    }).when("/gameRegionEdit/view/:id/:judge", {
        templateUrl: ctx + '/platform/gameRegion/gameRegionEditInit',
        controller: 'gameRegionEditController'
    });
});


/*
 区服管理controller
 全局：$scope
 Service:gameAreaFnService
 */
adminApp.controller('gameRegionEditController', function ($scope, gameRegionFnService, $location, $routeParams,$rootScope) {
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "GameRegionEditManage";
    $rootScope.menuBarData.menuBarTitle = "GameRegionEditManage";
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.gameRegionData = {};//区服对象集合
    $scope.gameRegionData.gameId = $scope.gameTransfer;//初始化gameId，与全局game同步
    $scope.gameSelected = $scope.gameTransfer;//更改gameId选择项
    $scope.gameRegionData.valueChange = false;
    $scope.connectType = "";
    $scope.EnterDressDataBase = {};
    $scope.EnterDressSocket = {};
    $scope.serverEnterAddr = {};
    var check = -1;//判断是否成功，url参数 1，是修改成功，0是修改失败，2是添加成功，3是添加失败，-1是失效
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

    if ($scope.gameRegionData.gameId == 1) {
        $(".clientConnectionEntry").hide();
    } else {
        $(".clientConnectionEntry").show();
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
        if ($scope.gameRegionData.gameId == 1) {
            $(".clientConnectionEntry").hide();
        } else {
            $(".clientConnectionEntry").show();
        }
        $scope.gameRegionData.valueChange = true;
    }

    /**
     连接方式转换
     */
    $scope.mannerChange = function (Manner) {
        if (Manner == "DATABASE") {
            $(".dataBase").show();
            $(".socket").hide();
        } else if (Manner == "SOCKET") {
            $(".dataBase").hide();
            $(".socket").show();
        } else {
            $(".dataBase").hide();
            $(".socket").hide();
        }
    }


    /**
     监听addForm表单
     */
    $scope.gameRegionEditForm = function () {

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

        $scope.gameRegionData.serverEnterAddr = JSON.stringify($scope.serverEnterAddr);//平台连接转成json


        $scope.gameRegionData.clientEnterAddr = JSON.stringify($scope.clientContent.clientData);//客户端连接转成json


        /*
         judge=update调用修改方法，judge=null或者copy时，调用添加方法
         */
        if ($routeParams.judge == "update") {
            /*
             调用修改方法
             */
            $scope.gameRegionData.id = $routeParams.id;
            gameRegionFnService.gameRegionUpdate($scope.gameRegionData).success(function (data) {
                check = 1;
                if (data != null && data !="") {
                    check = data;
                }
                $location.path("/gameRegion/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            }).error(function () {
                check = 0;
                $location.path("/gameRegion/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            });
        } else {
            /*
             调用添加方法
             */
            gameRegionFnService.gameRegionAdd($scope.gameRegionData).success(function (data) {
                check = 2;
                if (data != null && data !="") {
                    check = data;
                }
                $location.path("/gameRegion/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            }).error(function () {
                check = 3;
                $location.path("/gameRegion/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            });
        }

    };

    /**
     * 如果是区服修改，或者copy，根据id查询区服信息
     */
    if ($routeParams.judge == "update" || $routeParams.judge == "copy") {
        gameRegionFnService.gameRegionGetById($routeParams.id).success(function (data) {
            $scope.gameRegionData.areaGroupCode = Number(data.areaGroupCode);
            $scope.gameRegionData.areaGroupName = data.areaGroupName;
            $scope.gameRegionData.displayOrder = data.displayOrder;
            $scope.gameRegionData.areaGroupDesc = data.areaGroupDesc;

            $scope.clientContent.clientData = JSON.parse(data.clientEnterAddr);
            $scope.serverEnterAddr = JSON.parse(data.serverEnterAddr);

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
            /*  $scope.serverEnterAddr.connectType =  $scope.serverEnterAddr.connectType.toString();*/
            if ($scope.connectType == "DATABASE") {
                $(".dataBase").show();
                $(".socket").hide();
            } else {
                $(".dataBase").hide();
                $(".socket").show();
            }

            $scope.gameRegionData.gameId = data.gameId;
            $scope.$apply();
        });
    }


});