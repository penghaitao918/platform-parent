/**
 * 礼包码-批次编辑controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/bagCodeBatchEdit/view', {
        templateUrl: ctx + '/operation/bagCode/bagCodeBatchEdit',
        controller: 'bagCodeBatchEditController'
    }).when('/bagCodeBatchEdit/view/:id/:judge', {
        templateUrl: ctx + '/operation/bagCode/bagCodeBatchEdit',
        controller: 'bagCodeBatchEditController'
    });
});


/**
 礼包码-批次编辑controller
 全局：$scope
 */
adminApp.controller('bagCodeBatchEditController', function ($scope, batchGiftBagFnService, gameAreaFnService, $location, $routeParams, $rootScope, bagCodeBatchFnService) {

    $scope.bagCodeBatchEditData = {};//批次集合
    $scope.bagCodeBatchEditData.usingRule = {};//领取规则
    $scope.giftBagDatas = [];//显示集合
    $scope.giftBagId = [];//礼包id集合
    $scope.giftBagCode = [];//礼包name集合
    $scope.giftBagLists = [];//礼包集合

    $scope.areaSearch = {};//区服查询条件
    $scope.areaSearch.gameId = $scope.gameTransfer;
    $scope.areaSearch.siteId = $scope.siteTransfer;

    $scope.messagesData = {};//设置提示框model数据集合
    var check = -1;//判断是否成功，url参数 1，是修改成功，0是修改失败，2是添加成功，3是添加失败，-1是失效
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "load.bagBatchEditTitle";
    $rootScope.menuBarData.menuBarTitle = "load.bagBatchEditTitle";
    /**
     *根据全局game，site查询区服 公共方法
     */
    function gameAreaFindByGameIdSelect(areaSearch) {
        gameAreaFnService.gameAreaFindByGameId(areaSearch).success(function (response) {
            $scope.areaLists = response;
            $scope.$apply();
        });
    }

    /**
     *根据全局game，site查询区服
     */
    gameAreaFindByGameIdSelect($scope.areaSearch);


    /**
     *gameId改变时，查询自定义表单
     */
    $scope.gameIdChange = function (gameId) {
        $scope.areaSearch.gameId = gameId;
        /*
         根据全局game，site查询区服
         */
        gameAreaFindByGameIdSelect($scope.areaSearch);

    }

    /**
     *siteId改变时，查询自定义表单
     */
    $scope.siteIdChange = function (siteId) {
        $scope.areaSearch.siteId = siteId;
        /*
         根据全局game，site查询区服
         */
        gameAreaFindByGameIdSelect($scope.areaSearch);

    }


    /*
     选择礼包（弹框）
     */
    $scope.bagClick = function () {
        $("#bagAddModal").modal('show');
    }
    $scope.bagListCondition = {};
    $scope.bagListCondition.gameId = $scope.gameTransfer;//初始化gameId，与全局game同步

    if ($scope.bagListCondition.gameId == "") {
        $scope.bagListCondition.gameId = -1;
    }
    //查询礼包
    $.ajax({
        url: ctx + "/operation/bagCode/getList",
        data: {"gameId": $scope.bagListCondition.gameId},
        success: function (response) {
            $scope.giftBagLists = response;

        }, async: false
    });

    /*
     监听bagAdd表单
     */
    $scope.bagAdd = function () {
        $("#bagAddModal").modal('hide');
        $scope.giftBagDatas = $scope.giftBagCode;

    };

    /*
     checkbox单击操作
     */
    $scope.updateBagSelection = function ($event, id, code) {
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');//判断是否选中
        updateBagSelected(action, id, code);//选中判断逻辑方法
    }


    /*
     选中判断逻辑方法
     */
    var updateBagSelected = function (action, id, code) {
        /*
         true添加集合元素
         */
        if (action == 'add' && $scope.giftBagId.indexOf(id) == -1) {
            $scope.giftBagId.push(id);
            $scope.giftBagCode.push(code);
        }
        /*
         false移除集合元素
         */
        if (action == 'remove' && $scope.giftBagId.indexOf(id) != -1) {
            var idx = $scope.giftBagId.indexOf(id);
            $scope.giftBagId.splice(idx, 1);
            $scope.giftBagCode.splice(idx, 1);
        }
    }


    /*
     全选，遍历checkboxModel，正向操作
     */
    $scope.bagAll = function (v) {
        var checkbox = $(".bagChildren");
        for (var i = 0; i < checkbox.length; i++) {
            checkbox[i].checked = true;
        }
        var idData = [];
        var codeData = [];
        $scope.bagModel = true;
        for (var i = 0; i < v.length; i++) {
            idData.push(v[i].packageId);
            codeData.push(v[i].packageName);
        }
        $scope.giftBagId = idData;
        $scope.giftBagCode = codeData;
    };

    /*
     全不选
     */
    $scope.deselectAllBag = function () {
        var checkbox = $(".bagChildren");
        for (var i = 0; i < checkbox.length; i++) {
            checkbox[i].checked = false;
        }
        $scope.giftBagId = [];
        $scope.giftBagCode = [];
    }

    /*
     反选
     */
    $scope.bagVersa = function () {
        var checkbox = $(".bagChildren");
        /*
         遍历checkbox，并反向操作
         */
        for (var i = 0; i < checkbox.length; i++) {
            if (checkbox[i].checked) {

                checkbox[i].checked = false;
                var idx = $scope.giftBagId.indexOf(Number(checkbox[i].name));
                $scope.giftBagId.splice(idx, 1);
                $scope.giftBagCode.splice(idx, 1);

            } else {
                $scope.giftBagId.push(Number(checkbox[i].name));
                $scope.giftBagCode.push(checkbox[i].id);
                checkbox[i].checked = true;

            }

        }
    }

    /**
     * 监听Add提交表单
     */
    $scope.bagCodeBatchEdit = function () {
        //var packageId = "";
        //for (var i = 0; i < $scope.giftBagId.length; i++) {
        //    packageId += $scope.giftBagId[i] + ",";
        //}
        if ($scope.giftBagId.length > 0) {
            $scope.bagCodeBatchEditData.packageId = $scope.giftBagId[0];
        }

        $scope.bagCodeBatchEditData.availableStartDate = new Date($scope.bagCodeBatchEditData.availableStartDate);
        $scope.bagCodeBatchEditData.availableEndDate = new Date($scope.bagCodeBatchEditData.availableEndDate);
        $scope.bagCodeBatchEditData.usingRule = JSON.stringify($scope.bagCodeBatchEditData.usingRule);
        if ($routeParams.judge == "update") {
            $scope.bagCodeBatchEditData.createDate = new Date($scope.bagCodeBatchEditData.createDate);
            bagCodeBatchFnService.batchCodeUpdate($scope.bagCodeBatchEditData).success(function () {
                check = 1;
                $location.path("/bagCodeBatch/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            }).error(function () {
                check = 0;
                $location.path("/bagCodeBatch/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');
            });
        } else {
            bagCodeBatchFnService.batchCodeAdd($scope.bagCodeBatchEditData).success(function () {
                check = 2;
                $location.path("/bagCodeBatch/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            }).error(function () {
                check = 3;
                $location.path("/bagCodeBatch/view/" + check);
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
        bagCodeBatchFnService.batchCodeGetById($routeParams.id).success(function (data) {
            $scope.areaSearch.gameId=data.gameId;
            $scope.areaSearch.siteId=data.siteId;
            gameAreaFindByGameIdSelect($scope.areaSearch);
            $scope.bagCodeBatchEditData = data;
            $scope.bagCodeBatchEditData.createDate = $scope.format( $scope.bagCodeBatchEditData.createDate, "yyyy-MM-dd HH:mm:ss");
            $scope.bagCodeBatchEditData.availableStartDate = $scope.format( $scope.bagCodeBatchEditData.availableStartDate, "yyyy-MM-dd HH:mm:ss");
            $scope.bagCodeBatchEditData.availableEndDate = $scope.format( $scope.bagCodeBatchEditData.availableEndDate, "yyyy-MM-dd HH:mm:ss");
            $scope.bagCodeBatchEditData.validStatusVal = data.validStatus.toString();
            $scope.bagCodeBatchEditData.usingRule = JSON.parse(data.usingRule);

            delete  $scope.bagCodeBatchEditData.validStatus;

            /*
             初始化
             */
            var bagCheckbox = $(".bagChildren");
            var bagIds = data.packageId.toString().split(",");
            for (var i = 0; i < bagIds.length; i++) {
                for (var j = 0; j < $scope.giftBagLists.length; j++) {
                    if (bagIds[i] == $scope.giftBagLists[j].packageId) {
                        $scope.giftBagCode.push($scope.giftBagLists[j].packageName);
                        $scope.giftBagId.push($scope.giftBagLists[j].packageId);
                    }
                }

                /*
                 遍历checkbox，并初始化
                 */
                for (var k = 0; k < bagCheckbox.length; k++) {
                    if (bagCheckbox[k].name == bagIds[i]) {
                        bagCheckbox[k].checked = true;
                    }
                }
            }
            $scope.giftBagDatas=$scope.giftBagCode;
            $scope.$apply();
        });
    }


});