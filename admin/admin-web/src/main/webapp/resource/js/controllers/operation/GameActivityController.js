/**
 * 活动开关controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/gameActivity/view', {
        templateUrl: ctx + '/operation/gameActivity/gameActivityInit',
        controller: 'gameActivityController'
    }).when("/gameActivity/view/:check", {
        templateUrl: ctx + '/operation/gameActivity/gameActivityInit',
        controller: 'gameActivityController'
    });
    ;
});


/**
 活动开关controller
 全局：$scope
 */
adminApp.controller('gameActivityController', function ($scope, gameActivityFnService, $location, $routeParams, $rootScope) {
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.messagesConfirm = {};//设置确认框model数据集合
    $scope.gameActivitySearch = {};//活动查询条件集合
    $scope.gameActivitySearch.gameId = $scope.gameTransfer;//初始化gameId，与全局game同步
    $scope.gameActivitySearch.siteId = $scope.siteTransfer;//初始化gameId，与全局game同步
    $scope.activityIds = "";//公告id字符串
    $scope.activityState = {};//开关对象
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "GameActivity";
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

    function gameActivityGetPage(gameActivitySearch, pageIndex, pageSize) {
        /*
         调用查询方法，并传最新的currentPage
         */
        gameActivityFnService.gameActivityGetPage(gameActivitySearch, pageIndex, pageSize).success(function (response) {
            $scope.gameActivityDatas = response.rows;
            $scope.pageCount = response.page.pageCount;
            $scope.$apply();
        });
    }

    /**
     1：修改成功，0：修改失败，2,：添加成功，3：添加失败
     */
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
    } else if ($routeParams.check == "load.gameActivity230004") {
        $scope.messagesData.messagesTitle = "load.messagesTitleByUnusual";
        $scope.messagesData.messagesBody = "load.gameActivity230004";
    }

    /**
     添加（跳转）
     */
    $scope.addButton = function () {
        var add = function () {
            $location.path("/gameActivityEdit/view");
        };
        //判断是否有权限
        checkAuthority(1, add);//1：添，2：删，3：改，4：查 执行函数

    }

    /**
     * 修改
     * @param id
     */
    $scope.updateClicked = function (id) {
        var update = function () {
            $location.path("/gameActivityEdit/view/" + id + "/update");
        };
        //判断是否有权限
        checkAuthority(3, update);//1：添，2：删，3：改，4：查 执行函数

    }

    /**
     调用查询方法，并传最新的currentPage
     */
    gameActivityGetPage($scope.gameActivitySearch, 1, 10);


    /**
     分页
     */
    $scope.onPageChange = function () {
        /*
         调用查询方法，并传最新的currentPage
         */
        gameActivityGetPage($scope.gameActivitySearch, $scope.currentPage, 10);

    };


    /**
     开启、关闭状态工具方法 state 1代表开启，2代表关闭
     */
    function stateTool(ids, state) {
        $scope.activityState.ids = ids;
        $scope.activityState.state = state;
        gameActivityFnService.gameActivityUpdateState($scope.activityState).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
            $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToSuccess";
            $("#messagesModal").modal('show');
            /*
             调用查询方法，并传最新的currentPage
             */
            gameActivityGetPage($scope.gameActivitySearch, $scope.currentPage, 10);
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
            $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToFailure";
            $("#messagesModal").modal('show');
            /*
             调用查询方法，并传最新的currentPage
             */
            gameActivityGetPage($scope.gameActivitySearch, $scope.currentPage, 10);
        });
    }

    /**
     开启
     */
    $scope.open = function (id) {
        var open = function () {
            stateTool(id, 1);
        };
        //判断是否有权限
        checkAuthority(3, open);//1：添，2：删，3：改，4：查 执行函数
    };
    /**
     关闭
     */
    $scope.close = function (id) {
        var close = function () {
            stateTool(id, 2);
        };
        //判断是否有权限
        checkAuthority(3, close);//1：添，2：删，3：改，4：查 执行函数

    };

    /**
     更改公告的选择状态，并保存相应的id
     */
    $scope.check = function (id, chk) {
        if (chk == true) {
            $scope.activityIds += id + ",";
        } else {
            $scope.activityIds = $scope.activityIds.replace(id + ",", "");
        }

    };

    /**
     删除角色confirm
     */
    $scope.delete = function () {
        var deleteL = function () {
            if ($scope.activityIds != "") {
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
     删除角色确认
     */
    $scope.confirm = function () {

        $scope.activityState.ids = $scope.activityIds;
        $scope.activityState.state = 3;
        gameActivityFnService.gameActivityUpdateState($scope.activityState).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToSuccess";
            $("#messagesModal").modal('show');
            $scope.activityIds = "";
            /*
             调用查询方法，并传最新的currentPage
             */
            gameActivityGetPage($scope.gameActivitySearch, $scope.currentPage, 10);
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToFailure";
            $("#messagesModal").modal('show');
            $scope.activityIds = "";
            /*
             调用查询方法，并传最新的currentPage
             */
            gameActivityGetPage($scope.gameActivitySearch, $scope.currentPage, 10);
        });

    };

    /**
     一键开启
     */
    $scope.pocOpen = function () {

        var pocOpen = function () {
            if ($scope.activityIds != "") {
                stateTool($scope.activityIds, 1);
                $scope.activityIds = "";
            } else {
                $scope.messagesData.messagesTitle = "load.promptTitle";
                $scope.messagesData.messagesBody = "load.promptBody";
                $("#messagesModal").modal('show');
            }
        };
        //判断是否有权限
        checkAuthority(3, pocOpen);//1：添，2：删，3：改，4：查 执行函数


    };

    /**
     一键关闭
     */
    $scope.pocClose = function () {
        var pocClose = function () {
            if ($scope.activityIds != "") {
                stateTool($scope.activityIds, 2);
                $scope.activityIds = "";
            } else {
                $scope.messagesData.messagesTitle = "load.promptTitle";
                $scope.messagesData.messagesBody = "load.promptBody";
                $("#messagesModal").modal('show');
            }
        };
        //判断是否有权限
        checkAuthority(3, pocClose);//1：添，2：删，3：改，4：查 执行函数


    };
});