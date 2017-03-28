/**
 * 礼包码-批次礼包管理controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/batchGiftBag/view', {
        templateUrl: ctx + '/operation/bagCode/batchGiftBagInit',
        controller: 'batchGiftBagController'
    }).when("/batchGiftBag/view/:check", {
        templateUrl: ctx + '/operation/bagCode/batchGiftBagInit',
        controller: 'batchGiftBagController'
    });

});


/**
 礼包码-批次礼包管理controller
 全局：$scope
 */
adminApp.controller('batchGiftBagController', function ($scope, batchGiftBagFnService, $location, $routeParams, $rootScope) {

    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "load.giftBagTitle";

    $scope.messagesData = {};//设置提示框model数据集合
    $scope.messagesConfirm = {};//设置确认框model数据集合
    $scope.giftBagSearch = {};//批次礼包集合
    $scope.giftBagSearch.gameId = $scope.gameTransfer;//初始化gameId，与全局game同步
    $scope.giftBagIds = "";//批次礼包id字符串
    $scope.giftBagSearchText = "load.giftBagSearchText";//搜索提示国际化
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
    }

    function gameGiftBagGetPage(giftBagSearch, pageIndex, pageSize) {
        /*
         调用查询方法，并传最新的currentPage
         */
        batchGiftBagFnService.gameGiftBagGetPage(giftBagSearch, pageIndex, pageSize).success(function (response) {
            $scope.bagCodeDatas = response.rows;
            $scope.pageCount = response.page.pageCount;
            $scope.$apply();
        });
    }

    /**
     * 搜索单击事件
     */
    $scope.searchButtonClicked = function () {
        /*
         调用查询方法，并传最新的currentPage
         */
        gameGiftBagGetPage($scope.giftBagSearch, 1, 10);
    }

    /**
     调用查询方法，并传最新的currentPage
     */
    gameGiftBagGetPage($scope.giftBagSearch, 1, 10);
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
     添加（跳转）
     */
    $scope.addButton = function () {
        var add = function () {
            $location.path("/batchGiftBagEdit/view");
        };
        //判断是否有权限
        checkAuthority(1, add);//1：添，2：删，3：改，4：查 执行函数

    };

    /**
     编辑（跳转）
     */
    $scope.updateClicked = function (id) {
        var update = function () {
            $location.path("/batchGiftBagEdit/view/" + id + "/update");
        };
        //判断是否有权限
        checkAuthority(3, update);//1：添，2：删，3：改，4：查 执行函数


    };

    /**
     更改选择状态，并保存相应的id
     */
    $scope.check = function (id, chk) {
        if (chk == true) {
            $scope.giftBagIds += id + ",";
        } else {
            $scope.giftBagIds = $scope.giftBagIds.replace(id + ",", "");
        }

    };

    /**
     删除角色confirm
     */
    $scope.delete = function () {
        var deleteL = function () {
            if ($scope.giftBagIds != "") {
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
        /*
         调用删除方法
         */
        batchGiftBagFnService.giftBagDelete($scope.giftBagIds).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToSuccess";
            $("#messagesModal").modal('show');
            /*
             调用查询方法，并传最新的currentPage
             */
            gameGiftBagGetPage($scope.giftBagSearch, $scope.currentPage, 10);

            $scope.noticeIds = "";
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToFailure";
            $("#messagesModal").modal('show');
            $scope.noticeIds = "";
            /*
             调用查询方法，并传最新的currentPage
             */
            gameGiftBagGetPage($scope.giftBagSearch, $scope.currentPage, 10);
        });

    };

});