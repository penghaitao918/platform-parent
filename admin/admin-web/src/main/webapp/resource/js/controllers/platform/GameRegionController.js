/**
 * 区服管理controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/gameRegion/view', {
        templateUrl: ctx + '/platform/gameRegion/gameRegionInit',
        controller: 'gameRegionController'
    }).when("/gameRegion/view/:check", {
        templateUrl: ctx + '/platform/gameRegion/gameRegionInit',
        controller: 'gameRegionController'
    });
});


/**
 区服管理controller
 全局：$scope
 */
adminApp.controller('gameRegionController', function ($scope, gameRegionFnService, $location, $routeParams, $rootScope) {
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.messagesConfirm = {};//设置确认框model数据集合
    $scope.gameRegionSearch = {};//初始化查询条件对象
    $scope.gameRegionSearch.gameId = $scope.gameTransfer;//初始化查询gameId
    $scope.regionId = "";//区服id字符串
    var index = 0;//修改角色的下标
    $scope.gameRegions = {};//list集合
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "GameRegionManage";

    /**
     * 判断是否有操作权限
     */
    function checkAuthority(type, execute) {
        //判断是否有操作权限
        $.ajax({
            url: ctx + '/system/relation/checkAuthority',
            data: {menuName: $rootScope.menuBarData.menuBarTitle, type: type},//1：添，2：删，3：改，4：查
            success: function (data) {
                if (data) {
                    execute();
                } else {
                    $scope.messagesData.messagesTitle = "load.messagesTitleByAuthorityLimit";
                    $scope.messagesData.messagesBody = "load.messagesBodyByAuthorityLimit";
                    $("#messagesModal").modal('show');
                }
            }, async: false
        });

    }

    /**
     区服添加（跳转）
     */
    $scope.addButton = function () {
        var add = function () {
            $location.path("/gameRegionEdit/view");
        };
        //判断是否有权限
        checkAuthority(1, add);//1：添，2：删，3：改，4：查 执行函数

    };

    /**
     刷新查询页面，调用查询方法
     */
    function gameRegionFindByNamePublic(gameRegionSearch, pageIndex, pageSize) {
        gameRegionFnService.gameRegionFindByName(gameRegionSearch, pageIndex, pageSize).success(function (response) {
            console.log(response);
            $scope.gameRegions = response.rows;
            $scope.pageCount = response.page.pageCount;
            $scope.$apply();
        });
    }

    /**
     查询表单监听
     */
    $scope.searchButtonClicked = function () {
        /*
         刷新查询页面，调用查询方法
         */
        gameRegionFindByNamePublic($scope.gameRegionSearch, 1, 10);

    }
    /**
     copy区服
     */
    $scope.CopyRegion = function () {
        var CopyRegion = function () {
            if ($scope.regionId != "") {
                var dataId = $scope.regionId.split(",");
                $scope.COMMONALITY.regionId = dataId[0];
                $location.path("/gameRegionEdit/view/" + dataId[0] + "/copy");
            } else {
                $scope.messagesData.messagesTitle = "load.promptTitle";
                $scope.messagesData.messagesBody = "load.promptBody";
                $("#messagesModal").modal('show');
            }
        };
        //判断是否有权限
        checkAuthority(1, CopyRegion);//1：添，2：删，3：改，4：查 执行函数

    }

    /**
     修改（调转）
     */
    $scope.updateClicked = function () {
        var data = $(this);
        var update = function () {
            index = data[0].gameRegion.id;
            $scope.COMMONALITY.regionId = index;
            $location.path("/gameRegionEdit/view/" + index + "/update");
        };
        //判断是否有权限
        checkAuthority(3, update);//1：添，2：删，3：改，4：查 执行函数

    };

    /**
     更改区服的选择状态，并保存相应的id
     */
    $scope.check = function (val, chk) {
        var data = $(this);
        if (chk == true) {
            $scope.regionId += data[0].gameRegion.id + ",";
        } else {
            $scope.regionId = $scope.regionId.replace(data[0].gameRegion.id + ",", "");
        }

    };
    if ($routeParams.check == 1) {
        $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
        $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToSuccess";
    } else if ($routeParams.check == 0) {
        $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
        $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToFailure";
    } else if ($routeParams.check == 2) {
        $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
        $scope.messagesData.messagesBody = "load.messagesBodyByaddToSuccess";
    } else if ($routeParams.check == 3) {
        $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
        $scope.messagesData.messagesBody = "load.messagesBodyByaddToFailure";
    } else if ($routeParams.check == "load.region70044") {
        $scope.messagesData.messagesTitle = "load.messagesTitleByUnusual";
        $scope.messagesData.messagesBody = "load.region70044";
    }

    /**
     刷新查询页面，调用查询方法
     */
    gameRegionFindByNamePublic($scope.gameRegionSearch, 1, 10);


    /**
     删除角色confirm
     */
    $scope.delete = function () {
        var deleteL = function () {
            if ($scope.regionId != "") {
                $scope.messagesConfirm.title = "load.messagesConfirmTitleByDelete";
                $scope.messagesConfirm.body = "load.messagesConfirmBodyByDelete";
                $("#messagesConfirm").modal('show');
            } else {
                $scope.messagesData.messagesTitle = "load.promptTitle";
                $scope.messagesData.messagesBody = "load.promptBody";
                $("#messagesModal").modal('show');
            }
        };
        //判断是否有权限
        checkAuthority(2, deleteL);//1：添，2：删，3：改，4：查 执行函数


    };

    /**
     分页
     */
    $scope.onPageChange = function () {
        /*
         刷新查询页面，调用查询方法
         */
        gameRegionFindByNamePublic($scope.gameRegionSearch, $scope.currentPage, 10);
    };

    /**
     删除角色确认
     */
    $scope.confirm = function () {
        /*
         调用删除方法
         */
        gameRegionFnService.regionDelete($scope.regionId).success(function (data) {
            if (data == null || data == "") {
                $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
                $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToSuccess";
                $("#messagesModal").modal('show');
            } else {
                $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
                $scope.messagesData.messagesBody = data;
                $("#messagesModal").modal('show');
            }

            /*
             刷新查询页面，调用查询方法
             */
            gameRegionFindByNamePublic($scope.gameRegionSearch, $scope.currentPage, 10);


            $scope.regionId = "";
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToFailure";
            $("#messagesModal").modal('show');
            /*
             刷新查询页面，调用查询方法
             */
            gameRegionFindByNamePublic($scope.gameRegionSearch, $scope.currentPage, 10);
            $scope.regionId = "";
        });
    };

});