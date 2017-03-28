/**
 * notice公告controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/notice/view', {
        templateUrl: ctx + '/operation/notice/noticeInit',
        controller: 'noticeController'
    }).when("/notice/view/:check", {
        templateUrl: ctx + '/operation/notice/noticeInit',
        controller: 'noticeController'
    });
    ;
});


/**
 公告管理controller
 全局：$scope
 */
adminApp.controller('noticeController', function ($scope, noticeFnService, $location, $routeParams, $rootScope) {
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "NoticeManage";
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.messagesConfirm = {};//设置确认框model数据集合
    $scope.noticeSearch = {};//初始化查询条件对象
    $scope.noticeSearch.gameId = $scope.gameTransfer;//初始化查询gameId
    $scope.noticeSearch.siteId = $scope.siteTransfer;//初始化查询siteId
    $scope.noticeSearchText = "load.noticeSearchText";//搜索提示国际化
    /*$scope.noticeSearch.from="";
     $scope.noticeSearch.to="";*/
    $scope.noticeIds = "";//公告id字符串
    $scope.noticeState = {};//修改状态条件集合
    var index = 0;//修改角色的下标

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
            $location.path("/noticeEdit/view");
        };
        //判断是否有权限
        checkAuthority(1, add);//1：添，2：删，3：改，4：查 执行函数

    }

    /**
     调用查询方法，并传最新的currentPage
     */
    function noticeFindByNamePublic(noticeSearch, pageIndex, pageSize) {
        noticeFnService.noticeFindByName(noticeSearch, pageIndex, pageSize).success(function (response) {
            $scope.noticeDatas = response.rows;
            $scope.pageCount = response.page.pageCount;
            $scope.$apply();
        });
    }

    /**
     修改（调转）
     */
    $scope.updateClicked = function () {
        var data = $(this);
        var update = function () {
            index = data[0].notice.id;
            $location.path("/noticeEdit/view/" + index + "/update");
        };
        //判断是否有权限
        checkAuthority(3, update);//1：添，2：删，3：改，4：查 执行函数


    };

    /**
     开启、关闭状态工具方法 state 1代表开启，0代表关闭
     */
    function stateTool(ids, state) {
        $scope.noticeState.ids = ids;
        $scope.noticeState.state = state;
        noticeFnService.noticeStateUpdate($scope.noticeState).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
            $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToSuccess";
            $("#messagesModal").modal('show');
            /*
             调用查询方法，并传最新的currentPage
             */
            noticeFindByNamePublic($scope.noticeSearch, $scope.currentPage, 10);
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
            $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToFailure";
            $("#messagesModal").modal('show');
            /*
             调用查询方法，并传最新的currentPage
             */
            noticeFindByNamePublic($scope.noticeSearch, $scope.currentPage, 10);
        });
    }

    /**
     开启
     */
    $scope.open = function () {
        var data = $(this);
        var open = function () {
            index = data[0].notice.id;
            stateTool(index, 1);
        };
        //判断是否有权限
        checkAuthority(3, open);//1：添，2：删，3：改，4：查 执行函数

    };
    /**
     关闭
     */
    $scope.close = function () {
        var data = $(this);
        var close = function () {
            index = data[0].notice.id;
            stateTool(index, 0);
        };
        //判断是否有权限
        checkAuthority(3, close);//1：添，2：删，3：改，4：查 执行函数

    };

    /**
     调用查询方法，并传最新的currentPage
     */
    noticeFindByNamePublic($scope.noticeSearch, 1, 10);

    /**
     分页
     */
    $scope.onPageChange = function () {
        /*
         调用查询方法，并传最新的currentPage
         */
        noticeFindByNamePublic($scope.noticeSearch, $scope.currentPage, 10);

    };

    /**
     查询表单监听
     */
    $scope.searchButtonClicked = function () {
        /*
         调用查询方法，并传最新的currentPage
         */
        noticeFindByNamePublic($scope.noticeSearch, 1, 10);

    }


    /**
     更改公告的选择状态，并保存相应的id
     */
    $scope.check = function (val, chk) {
        var data = $(this);
        if (chk == true) {
            $scope.noticeIds += data[0].notice.id + ",";
        } else {
            $scope.noticeIds = $scope.noticeIds.replace(data[0].notice.id + ",", "");
        }

    };


    /**
     一键开启
     */
    $scope.pocOpen = function () {
        var pocOpen = function () {
            if ($scope.noticeIds != "") {
                stateTool($scope.noticeIds, 1);
                $scope.noticeIds = "";
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
            if ($scope.noticeIds != "") {
                stateTool($scope.noticeIds, 0);
                $scope.noticeIds = "";
            } else {
                $scope.messagesData.messagesTitle = "load.promptTitle";
                $scope.messagesData.messagesBody = "load.promptBody";
                $("#messagesModal").modal('show');
            }

        };
        //判断是否有权限
        checkAuthority(3, pocClose);//1：添，2：删，3：改，4：查 执行函数


    };

    /**
     删除角色confirm
     */
    $scope.delete = function () {
        var deleteL = function () {
            if ($scope.noticeIds != "") {
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
        noticeFnService.noticeDelete($scope.noticeIds).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToSuccess";
            $("#messagesModal").modal('show');
            /*
             调用查询方法，并传最新的currentPage
             */
            noticeFindByNamePublic($scope.noticeSearch, $scope.currentPage, 10);

            $scope.noticeIds = "";
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToFailure";
            $("#messagesModal").modal('show');
            $scope.noticeIds = "";
            /*
             调用查询方法，并传最新的currentPage
             */
            noticeFindByNamePublic($scope.noticeSearch, $scope.currentPage, 10);
        });

    };

});