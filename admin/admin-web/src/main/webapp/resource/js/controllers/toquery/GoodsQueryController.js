adminApp.config(function ($routeProvider) {
    $routeProvider.when('/goodsQuery/view', {
        templateUrl: ctx + '/toyQuery/goodsQueryInit',
        controller: 'goodsQueryController'
    });
});

adminApp.controller('goodsQueryController', function ($scope, gameAreaFnService, roleQueryService, $rootScope, goodsQueryService, $translate) {
    $scope.goodsQuerySearchBox = 0;//0代表搜素框显示，结果信息隐藏，反之。
    $scope.goodsQuerySearchData = {};//搜索条件
    $scope.goodsQuerySearchData.gameId = $scope.gameTransfer;//初始化gameId，与全局game同步
    $scope.goodsQuerySearchData.serverIdKey = "513";
    $scope.goodsQuerySearchData.accountKey = "512";
    $scope.goodsQuerySearchData.gmMsgType = "Gm2LogicPlayerItemInfoGetReq";
    $scope.areaSearch = {};//区服查询条件
    $scope.areaSearch.gameId = $scope.gameTransfer;
    $scope.areaSearch.siteId = $scope.siteTransfer;
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "GoodsQueryManage";
    $scope.goodsQueryItems = [];//查询结果转换

    /**
     *gameId改变时，查询自定义表单
     */
    $scope.gameIdChange = function (gameId) {
        $scope.areaSearch.gameId = gameId;
        /*
         根据全局game，site查询区服
         */
        gameAreaFnService.gameAreaFindByGameId($scope.areaSearch).success(function (response) {
            $scope.areaLists = response;
            $scope.$apply();
        });
    }
    /**
     * 监听玩家查询表单
     */
    $scope.goodsQuerySearchForm = function () {
        $scope.goodsQuerySearchBox = 1;
        goodsQueryService.getGoodsInfo($scope.goodsQuerySearchData).success(function (data) {
            //data = '{"load.blockSecond":"2323d 23","load.accountId":"多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多的反馈老妇女失控裸女的咖啡但是可能的服了你上岛咖啡的烦恼斯柯达你发","load.blockRes":"大幅度发大幅度发大幅度发的冯绍","load.exp":"大幅度发的发带你看发到付呢的可能反打是啊的狂蜂浪蝶你分段放得开发你",' +
            //    '"load.unionName":"大幅度发的发带你看发到付呢的可能反打是啊的狂蜂浪蝶你分段放得开发你","load.unionContribution":"大幅度发你","load.heroCount":"2323d 23","load.gold":"金币","load.rmb":"充值货币","load.PVECoin":"副本货币"}';
            var dataItem = [];
            if (data != "" && data != null) {
                dataItem = JSON.parse(data);
            }

            for (var key in dataItem) {
                var item = {};
                item.name = $translate.instant(key);
                item.value = dataItem[key];
                $scope.goodsItems.push(item);
            }


            $scope.$apply();
        });
    }

    /**
     * 返回搜索
     */
    $scope.returnSearchBox = function () {
        $scope.goodsQuerySearchBox = 0;
        $scope.goodsQuerySearchData.roleId = "";
        $scope.goodsQuerySearchData.userId = "";
        $scope.goodsQuerySearchData.roleName = "";
        $scope.goodsQueryItems = [];
    }

    /**
     * 清空
     */
    $scope.empty = function () {
        $scope.goodsQuerySearchData = {};
        $scope.goodsQueryoItems = [];
    }

    /*
     根据全局game，site查询区服
     */
    gameAreaFnService.gameAreaFindByGameId($scope.areaSearch).success(function (response) {
        $scope.areaLists = response;
        $scope.$apply();
    });
});

