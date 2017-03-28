adminApp.config(function ($routeProvider) {
    $routeProvider.when('/mailOrderFailure/view', {
        templateUrl: ctx + '/operation/mailOrderFailure/mailOrderFailureInit',
        controller: 'mailOrderFailureController'
    });
});

/**
 * 邮件订单错误信息操作
 */
adminApp.controller('mailOrderFailureController', function ($scope, mailOrderFailureFnService, $rootScope) {
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "MailOrderFailure";
    $scope.mailOrderIds = "";
    $scope.mailOrderFailureSearch = {};
    $scope.orderFailureDataSee = {};
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.messagesConfirm = {};//设置确认框model数据集合
    $scope.placeholderSearch = "load.ApprovalFromSearch";//搜索提示
    /**
     刷新页面工具方法
     */
    function mailOrderFailureSelect(mailOrderFailureSearch, pageIndex, pageSize) {
        /*
         刷新查询页面，调用Service的查询方法
         */
        mailOrderFailureFnService.mailOrderFailurePageList(mailOrderFailureSearch, pageIndex, pageSize).success(function (response) {
            $scope.orderFailureData = response.rows;
            $scope.pageCount = response.page.pageCount;
            $scope.$apply();
        });
    }

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
     调用查询方法，并传最新的currentPage
     */
    mailOrderFailureSelect($scope.mailOrderFailureSearch, 1, 10);


    $scope.searchButtonClicked = function () {
        /*
         调用查询方法，并传最新的currentPage
         */
        mailOrderFailureSelect($scope.mailOrderFailureSearch, 1, 10);
    }

    /**
     分页
     */
    $scope.onPageChange = function () {
        /*
         调用查询方法，并传最新的currentPage
         */
        mailOrderFailureSelect($scope.mailOrderFailureSearch, $scope.currentPage, 10);
    };

    /**
     查看
     */
    $scope.viewClicked = function (id) {
        mailOrderFailureFnService.mailOrderFailureById(id).success(function (data) {
            $scope.orderFailureDataSee = data;
            $scope.$apply();
        });
        $("#failureModalForView").modal("show");
    };


    /**
     更改选择状态，并保存相应的id
     */
    $scope.check = function (val, chk) {
        var data = $(this);
        if (chk == true) {
            $scope.mailOrderIds += data[0].orderFailure.id + ",";
        } else {
            $scope.mailOrderIds = $scope.mailOrderIds.replace(data[0].orderFailure.id + ",", "");
        }

    };


    /**
     删除角色confirm
     */
    $scope.delete = function () {
        var deleteL = function () {
            if ($scope.mailOrderIds != "") {
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
        console.log($scope.mailOrderIds);
        /*
         调用删除方法
         */
        mailOrderFailureFnService.mailOrderFailureDelete($scope.mailOrderIds).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToSuccess";
            $("#messagesModal").modal('show');
            /*
             刷新查询页面
             */
            mailOrderFailureSelect($scope.mailOrderFailureSearch, $scope.currentPage, 10);

            $scope.mailOrderIds = "";
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToFailure";
            $("#messagesModal").modal('show');
            $scope.mailOrderIds = "";
            /*
             刷新查询页面
             */
            mailOrderFailureSelect($scope.mailOrderFailureSearch, $scope.currentPage, 10);
        });
    };
});

