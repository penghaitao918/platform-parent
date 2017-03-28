/**
 * 玩具管理controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/gameToy/view', {
        templateUrl: ctx + '/operation/gameToy/gameToyInit',
        controller: 'gameToyController'
    });
});


/**
 玩具管理controller
 全局：$scope
 */
adminApp.controller('gameToyController', function ($scope, gameToyFnService, $rootScope) {
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "GameToyManage";
    $scope.gameToyAddData = {};//设置addForm控件model数据集合

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
     玩具添加（弹框）
     */
    $scope.addButton = function () {
        var add = function () {
            $("#gameToyModalForAdd").modal('show');
        };
        //判断是否有权限
        checkAuthority(1, add);//1：添，2：删，3：改，4：查 执行函数
    };

    $scope.gameToySearch = {};//初始化查询条件对象
    $scope.gameToySearch.gameId = $scope.gameTransfer;//初始化查询gameId
    $scope.gameToySearch.siteId = $scope.siteTransfer;//初始化查询siteId
    $scope.gameToySearch.bindStatus = "";//初始化状态
    $scope.gameToySearch.from = "";
    $scope.gameToySearch.to = "";
    $scope.gameToyAddData.gameId = $scope.gameTransfer;//初始化gameId，与全局game同步
    $scope.gameToyAddData.siteId = $scope.siteTransfer;//初始化siteId，与全局site同步


    $scope.bindStatus = "1";
    $scope.gameToySearch.bindStatus = 1;
    $scope.gameToySiteList = $scope.siteTransfer;//初始化gameToySiteList，与全局site同步
    $scope.gameToyGameList = $scope.gameTransfer;//gameToyGameList，与全局game同步

    $scope.messagesData = {};//设置提示框model数据集合
    $scope.messagesConfirm = {};//设置确认框model数据集合
    $scope.gameToyIds = "";//选中ids集合


    /**
     绑定game,site select，change事件
     */
    $scope.resourceGameListChange = function (id) {
        $scope.gameToyAddData.gameId = id;
        console.log(id);
    };

    $scope.resourceSiteListChange = function (id) {
        $scope.gameToyAddData.siteId = id;
        console.log(id);
    };

    /**
     刷新查询页面，调用查询方法
     */
    function gameToyFindByNamePublic(gameToySearch, pageIndex, pageSize) {
        gameToyFnService.gameToyFindByName(gameToySearch, pageIndex, pageSize).success(function (response) {
            console.log(response);
            $scope.gameToys = response.rows;
            $scope.pageCount = response.page.pageCount;
            $scope.$apply();
        });
    }

    /**
     绑定状态change事件
     */
    $scope.bindStatusChange = function (bindStatus) {
        $scope.gameToySearch.bindStatus = bindStatus;
        /*
         刷新查询页面，调用查询方法
         */
        gameToyFindByNamePublic($scope.gameToySearch, 1, 10);
    };

    /**
     查询表单监听
     */
    $scope.searchButtonClicked = function () {
        /*
         刷新查询页面，调用查询方法
         */
        gameToyFindByNamePublic($scope.gameToySearch, 1, 10);
    }


    /**
     刷新查询页面，调用查询方法
     */
    gameToyFindByNamePublic($scope.gameToySearch, 1, 10);

    /**
     分页
     */
    $scope.onPageChange = function () {
        /*
         刷新查询页面，调用查询方法
         */
        gameToyFindByNamePublic($scope.gameToySearch, $scope.currentPage, 10);
    };

    /**
     监听addForm表单
     */
    $scope.gameToyAddForms = function () {
        $scope.gameToyAddData.proTime = new Date($scope.gameToyAddData.proTime);
        if ($scope.gameToyAddData.codeFrom >= $scope.gameToyAddData.codeEnd) {
            $scope.messagesData.messagesTitle = "警告";
            $scope.messagesData.messagesBody = "codeEnd必须大于codeFrom！";
            $("#messagesModal").modal('show');
        } else {
            /*
             调用添加方法
             */
            gameToyFnService.gameToyAdd($scope.gameToyAddData).success(function () {
                $scope.messagesData.messagesTitle = "添加结果";
                $scope.messagesData.messagesBody = "此次添加成功！";
                $("#messagesModal").modal('show');
                $("input").val("");
                $("textarea").val("");
                /*
                 刷新查询页面，调用查询方法
                 */
                gameToyFindByNamePublic($scope.gameToySearch, 1, 10);

                $('#gameToyModalForAdd').modal('hide');
            }).error(function (data) {
                $scope.messagesData.messagesTitle = "添加结果";
                $scope.messagesData.messagesBody = "创建Id重复";
                $scope.$apply();
                $("#messagesModal").modal('show');
                /*
                 刷新查询页面，调用查询方法
                 */
                gameToyFindByNamePublic($scope.gameToySearch, 1, 10);
            });
        }
    };


    /**
     更改选择状态，并保存相应的id
     */
    $scope.check = function (val, chk) {
        var data = $(this);
        if (chk == true) {
            $scope.gameToyIds += data[0].gameToy.id + ",";
        } else {
            $scope.gameToyIds = $scope.gameToyIds.replace(data[0].gameToy.id + ",", "");
        }

    };

    $scope.failure = function () {
        if ($scope.gameToyIds != "") {
            $scope.gameToySearch.ids = $scope.gameToyIds;
            gameToyFnService.gameToyDisabled($scope.gameToySearch).success(function (response) {
                $scope.messagesData.messagesTitle = "load.messagesTitleByOperate";
                $scope.messagesData.messagesBody = "load.messagesBodyByOperateToSuccess";
                $("#messagesModal").modal('show');
                /*
                 刷新查询页面，调用查询方法
                 */
                gameToyFindByNamePublic($scope.gameToySearch, 1, 10);
            }).error(function (data) {
                $scope.messagesData.messagesTitle = "load.messagesTitleByOperate";
                $scope.messagesData.messagesBody = "load.messagesBodyByOperateToFailure";
                $scope.$apply();
                $("#messagesModal").modal('show');
                /*
                 刷新查询页面，调用查询方法
                 */
                gameToyFindByNamePublic($scope.gameToySearch, 1, 10);
            });
        } else {
            $scope.messagesData.messagesTitle = "load.promptTitle";
            $scope.messagesData.messagesBody = "load.promptBody";
            $("#messagesModal").modal('show');
        }

    }
});