/**
 * 公告添加controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/gameActivityEdit/view', {
        templateUrl: ctx + '/operation/gameActivity/gameActivityEdit',
        controller: 'gameActivityEditController'
    }).when('/gameActivityEdit/view/:id/:judge', {
        templateUrl: ctx + '/operation/gameActivity/gameActivityEdit',
        controller: 'gameActivityEditController'
    });
});


/**
 公告编辑controller
 全局：$scope
 */
adminApp.controller('gameActivityEditController', function ($scope, gameActivityFnService, gameAreaFnService, $location, $routeParams,$rootScope) {

    $scope.selectedId = [];//渠道id集合
    $scope.selectedCode = [];//渠道code集合
    $scope.areaLists = [];//区服集合
    $scope.areaId = [];//区服id集合
    $scope.areaCode = [];//区服name集合
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.channelCode = [];//渠道code集合（显示项）
    $scope.activityGameList = $scope.gameTransfer;//初始化gameId，与全局game同步
    $scope.gameAreasData = [];//区服查询条件集合
    $scope.gameAreasData.gameId = $scope.gameTransfer;//初始化gameId，与全局game同步
    $scope.activity={};//活动对象
    $scope.activity.validStatusCheck=1;//默认开启
    var check = -1;//判断是否成功，url参数 1，是修改成功，0是修改失败，2是添加成功，3是添加失败，-1是失效
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "GameActivityEdit";
    $rootScope.menuBarData.menuBarTitle = "GameActivityEdit";
    /**
     绑定game,site select，change事件
     */
    $scope.resourceGameListChange = function (id) {
        $scope.gameAreasData.gameId = id;
        gameAreaFnService.gameAreaFindByGameId($scope.gameAreasData).success(function (response) {
            $scope.areaLists = response;
            $scope.$apply();
        });
    };

    /**
     根据全局game，site查询区服
     */
    if ($scope.gameAreasData.gameId == undefined) {
        $scope.gameAreasData.gameId = -1;
    }

    $.ajax({
        url: ctx + '/platform/gameArea/getGameAreasById',
        data:{"gameId": $scope.gameAreasData.gameId},
        success: function (data) {
            $scope.areaLists = data;
        },
        async: false
    });
    //gameAreaFnService.gameAreaFindByGameId($scope.gameAreasData).success(function (response) {
    //    $scope.areaLists = response;
    //    $scope.$apply();
    //});

    /*
     监听addForm表单
     */
    $scope.gameActivityEditForm = function () {
        var site = "";
        for (var i = 0; i < $scope.selectedId.length; i++) {
            site += $scope.selectedId[i] + ",";
        }
        var areaids = "";
        for (var i = 0; i < $scope.areaId.length; i++) {
            areaids += $scope.areaId[i] + ",";
        }
        $scope.gameAreasData.siteIds = "," + site;
        $scope.activity.gameId = $scope.gameAreasData.gameId;
        $scope.activity.siteIds = "," + site;
        $scope.activity.areaIds = "," + areaids;
        if ($scope.activity.startTime != undefined) {
            $scope.activity.startTime = new Date($scope.activity.startTime);
        }
        if ($scope.activity.endTime != undefined) {
            $scope.activity.endTime = new Date($scope.activity.endTime);
        }
        delete $scope.activity.validStatus;
        if ($routeParams.judge == "update") {
            gameActivityFnService.gameActivityUpdate($scope.activity).success(function (data) {
                check = 1;
                if (data != null && data !="") {
                    check = data;
                }
                $location.path("/gameActivity/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            }).error(function () {
                check = 0;
                $location.path("/gameActivity/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');
            });
        } else {
            gameActivityFnService.gameActivityAdd($scope.activity).success(function (data) {
                check = 2;
                if (data != null && data !="") {
                    check = data;
                }
                $location.path("/gameActivity/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            }).error(function () {
                check = 3;
                $location.path("/gameActivity/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            });
        }


    };

    /**
     * 如果跳转参数是update，根据id查询活动对象
     */
    if ($routeParams.judge == "update") {
        /*
         根据id查询公告
         */
        gameActivityFnService.gameActivityGetById($routeParams.id).success(function (data) {
            $(".activityId").attr("readonly",true);
            $(".activityTaskId").attr("readonly",true);
            $(".activityGameList").attr("disabled",false);

            $scope.activityGameList = Number(data.gameId);
            data.startTime = $scope.format(data.startTime, "yyyy-MM-dd HH:mm:ss");
            data.endTime = $scope.format(data.endTime, "yyyy-MM-dd HH:mm:ss");
            $scope.activity = data;
            $scope.activity.gameId = Number(data.gameId);
            $scope.activity.validStatusCheck = $scope.activity.validStatus;

            /*
             区服 初始化
             */
            var areaCheckbox = $(".areaChildren");
            if (data.areaIds.substring(0, 1) == ",") {
                //字符串以,开头，去掉"
                data.areaIds = data.areaIds.substr(1, data.areaIds.length - 1)
            }
            if (data.areaIds.substring(data.areaIds.length - 1, data.areaIds.length) == ",") {
                //字符串以,结尾，去掉"
                data.areaIds = data.areaIds.substr(0, data.areaIds.length - 1)
            }
            var areaIds = data.areaIds.split(",");
            for (var i = 0; i < areaIds.length; i++) {
                for (var j = 0; j < $scope.areaLists.length; j++) {
                    if (areaIds[i] == $scope.areaLists[j].id) {
                        $scope.areaCode.push($scope.areaLists[j].areaName);
                        $scope.areaId.push($scope.areaLists[j].id);
                    }
                }

                /*
                 遍历checkbox，并初始化
                 */
                for (var k = 0; k < areaCheckbox.length; k++) {
                    if (areaCheckbox[k].name == areaIds[i]) {
                        areaCheckbox[k].checked = true;
                    }
                }
            }
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
            $scope.areaData = $scope.areaCode;
            $scope.channelCode = $scope.selectedCode;
            $scope.$apply();
        });
    }

    /*
     选择渠道（弹框）
     */
    $scope.channelClick = function () {
        $("#channelAddModal").modal('show');
    }


    /*
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


    /*
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

    /*
     checkbox单击操作
     */
    $scope.updateSelection = function ($event, id, code) {
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');//判断是否选中
        updateSelected(action, id, code);//选中判断逻辑方法
    }

    /*
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

    /*
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

    /*
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
    /*
     选择区服（弹框）
     */
    $scope.areaClick = function () {
        $("#areaAddModal").modal('show');
    }

    /*
     监听areaAdd表单
     */
    $scope.areaAdd = function () {

        //$scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
        //$scope.messagesData.messagesBody = "load.messagesBodyByAreaToSuccess";
        //$("#messagesModal").modal('show');
        $("#areaAddModal").modal('hide');
        $scope.areaData = $scope.areaCode;

    };

    /*
     区服 checkbox单击操作
     */
    $scope.updateAreaSelection = function ($event, id, code) {
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');//判断是否选中
        updateAreaSelected(action, id, code);//选中判断逻辑方法
    }


    /**
     选中判断逻辑方法（区服）
     */
    var updateAreaSelected = function (action, id, code) {
        /*
         true添加集合元素
         */
        if (action == 'add' && $scope.areaId.indexOf(id) == -1) {
            $scope.areaId.push(id);
            $scope.areaCode.push(code);
        }
        /*
         false移除集合元素
         */
        if (action == 'remove' && $scope.areaId.indexOf(id) != -1) {
            var idx = $scope.areaId.indexOf(id);
            $scope.areaId.splice(idx, 1);
            $scope.areaCode.splice(idx, 1);
        }
    }


    /**
     全选，遍历checkboxModel，正向操作
     */
    $scope.areaAll = function (v) {
        var checkbox = $(".areaChildren");
        for (var i = 0; i < checkbox.length; i++) {
            checkbox[i].checked = true;
        }
        var idData = [];
        var codeData = [];
        $scope.areaModel = true;
        for (var i = 0; i < v.length; i++) {
            idData.push(v[i].id);
            codeData.push(v[i].areaName);
        }
        $scope.areaId = idData;
        $scope.areaCode = codeData;
    };

    /**
     全不选
     */
    $scope.deselectAllArea = function () {
        var checkbox = $(".areaChildren");
        for (var i = 0; i < checkbox.length; i++) {
            checkbox[i].checked = false;
        }
        $scope.areaId = [];
        $scope.areaCode = [];
    }

    /**
     反选
     */
    $scope.areaVersa = function () {
        var checkbox = $(".areaChildren");
        /*
         遍历checkbox，并反向操作
         */
        for (var i = 0; i < checkbox.length; i++) {
            if (checkbox[i].checked) {

                checkbox[i].checked = false;
                var idx = $scope.areaId.indexOf(Number(checkbox[i].name));
                $scope.areaId.splice(idx, 1);
                $scope.areaCode.splice(idx, 1);

            } else {
                $scope.areaId.push(Number(checkbox[i].name));
                $scope.areaCode.push(checkbox[i].id);
                checkbox[i].checked = true;

            }

        }
    }


});