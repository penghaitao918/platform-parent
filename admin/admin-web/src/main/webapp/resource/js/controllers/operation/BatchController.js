/**
 * 批次管理controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/batch/view', {
        templateUrl: ctx + '/operation/batch/batchInit',
        controller: 'batchController'
    });
});


/**
 玩具管理controller
 全局：$scope
 */
adminApp.controller('batchController', function ($scope, batchFnService, gameToyTypeFnService, $rootScope) {

    $scope.batchAddData = {};//设置addForm控件model数据集合
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
     *玩具添加（弹框）
     */
    $scope.addButton = function () {
        var add = function () {
            $("#batchModalForAdd").modal('show');
        };
        //判断是否有权限
        checkAuthority(1, add);//1：添，2：删，3：改，4：查 执行函数

    };

    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "BatchManage";

    $scope.batchSearch = {};//初始化查询条件对象
    $scope.batchSearch.gameId = $scope.gameTransfer;//初始化查询gameId
    $scope.gameSelected = $scope.gameTransfer;//更改gameId选择项

    $scope.batchSearch.siteId = $scope.siteTransfer;//初始化查询siteId
    $scope.siteSelected = $scope.siteTransfer;//更改siteId选择项

    $scope.batchSearch.from = "";
    $scope.batchSearch.to = "";
    $scope.batchAddData.gameId = $scope.gameTransfer;//初始化gameId，与全局game同步
    $scope.batchAddData.siteId = $scope.siteTransfer;//初始化siteId，与全局site同步


    $scope.batchSiteList = $scope.siteTransfer;//初始化batchSiteList，与全局site同步
    $scope.batchGameList = $scope.gameTransfer;//batchGameList，与全局game同步

    $scope.messagesData = {};//设置提示框model数据集合
    $scope.messagesConfirm = {};//设置确认框model数据集合
    $scope.batchIds = "";//选中ids集合


    /**
     *绑定game,site select，change事件
     */
    $scope.resourceGameListChange = function (id) {
        $scope.batchAddData.gameId = id;
        console.log(id);
    };

    $scope.resourceSiteListChange = function (id) {
        $scope.batchAddData.siteId = id;
        console.log(id);
    };

    /**
     *绑定itemType change事件
     */
    $scope.itemTypeChange = function (itemType) {
        $scope.batchSearch.itemType = itemType;
        /*
         刷新查询页面，调用查询方法
         */
        batchFindByNamePublic($scope.batchSearch, 1, 10);
    };

    /**
     *查询玩具类型，默认id为1，玩具
     */
    gameToyTypeFnService.itemGetByType(1).success(function (response) {
        $scope.itemTypeList = response;
        $scope.$apply();
    });
    /**
     刷新查询页面，调用查询方法 公共方法
     */
    function batchFindByNamePublic(batchSearch, pageIndex, pageSize) {
        batchFnService.batchFindByName(batchSearch, pageIndex, pageSize).success(function (response) {
            console.log(response);
            $scope.batchs = response.rows;
            $scope.pageCount = response.page.pageCount;
            $scope.$apply();
        });
    }

    /**
     *查询表单监听
     */
    $scope.searchButtonClicked = function () {
        /*
         刷新查询页面，调用查询方法
         */
        batchFindByNamePublic($scope.batchSearch, 1, 10);
    }


    /**
     *刷新查询页面，调用查询方法
     */
    batchFindByNamePublic($scope.batchSearch, 1, 10);
    /**
     *分页
     */
    $scope.onPageChange = function () {

        /*
         刷新查询页面，调用查询方法
         */
        batchFindByNamePublic($scope.batchSearch, $scope.currentPage, 10);
    };

    /**
     *监听addForm表单
     */
    $scope.batchAddForms = function () {
        $scope.batchAddData.proTime = new Date($scope.batchAddData.proTime);
        /*
         调用添加方法
         */
        batchFnService.batchAdd($scope.batchAddData).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
            $scope.messagesData.messagesBody = "load.messagesBodyByaddToSuccess";
            $("#messagesModal").modal('show');
            $("input").val("");
            $("textarea").val("");
            /*
             刷新查询页面，调用查询方法
             */
            batchFindByNamePublic($scope.batchSearch, 1, 10);

            $('#batchModalForAdd').modal('hide');
        }).error(function (data) {
            $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
            $scope.messagesData.messagesBody = "load.Repeat";
            $scope.$apply();
            $("#messagesModal").modal('show');
            /*
             刷新查询页面，调用查询方法
             */
            batchFindByNamePublic($scope.batchSearch, 1, 10);
        });

    };


    /**
     *更改选择状态，并保存相应的id
     */
    $scope.check = function (val, chk) {
        var data = $(this);
        if (chk == true) {
            $scope.batchIds += data[0].batch.id + ",";
        } else {
            $scope.batchIds = $scope.batchIds.replace(data[0].batch.id + ",", "");
        }

    };

    $scope.failure = function () {
        if ($scope.batchIds != "") {
            $scope.batchSearch.ids = $scope.batchIds;
            batchFnService.batchDisabled($scope.batchSearch).success(function (response) {
                $scope.messagesData.messagesTitle = "load.messagesTitleByOperate";
                $scope.messagesData.messagesBody = "load.messagesBodyByOperateToSuccess";
                $("#messagesModal").modal('show');
                /*
                 刷新查询页面，调用查询方法
                 */
                batchFindByNamePublic($scope.batchSearch, 1, 10);
            }).error(function (data) {
                $scope.messagesData.messagesTitle = "load.messagesTitleByOperate";
                $scope.messagesData.messagesBody = "load.messagesBodyByOperateToFailure";
                $scope.$apply();
                $("#messagesModal").modal('show');
                /*
                 刷新查询页面，调用查询方法
                 */
                batchFindByNamePublic($scope.batchSearch, 1, 10);
            });
            ;
        } else {
            $scope.messagesData.messagesTitle = "load.promptTitle";
            $scope.messagesData.messagesBody = "load.promptBody";
            $("#messagesModal").modal('show');
        }

    }
});