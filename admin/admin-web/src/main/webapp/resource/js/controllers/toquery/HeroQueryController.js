adminApp.config(function ($routeProvider) {
    $routeProvider.when('/heroQuery/view', {
        templateUrl: ctx + '/toyQuery/heroQueryInit',
        controller: 'heroQueryController'
    });
});

adminApp.controller('heroQueryController', function ($scope, gameAreaFnService, roleQueryService, $rootScope, heroQueryService, $translate) {
    $scope.heroSearchBox = 0;//0代表搜素框显示，结果信息隐藏，反之。
    $scope.heroSearchData = {};//搜索条件
    $scope.heroSearchData.gameId = $scope.gameTransfer;//初始化gameId，与全局game同步
    $scope.heroSearchData.serverIdKey = "513";
    $scope.heroSearchData.accountKey = "512";
    $scope.heroSearchData.gmMsgType = "Gm2LogicPlayerHeroInfoGetReq";
    $scope.areaSearch = {};//区服查询条件
    $scope.areaSearch.gameId = $scope.gameTransfer;
    $scope.areaSearch.siteId = $scope.siteTransfer;
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "HeroQueryManage";
    $scope.heroItems = [];//查询结果转换

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
    $scope.heroSearchForm = function () {
        $scope.heroSearchBox = 1;
        heroQueryService.getHeroInfo($scope.heroSearchData).success(function (data) {
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
                $scope.heroItems.push(item);
            }


            $scope.$apply();
        });
    }

    /**
     * 返回搜索
     */
    $scope.returnSearchBox = function () {
        $scope.heroSearchBox = 0;
        $scope.heroSearchData.roleId = "";
        $scope.heroSearchData.userId = "";
        $scope.heroSearchData.roleName = "";
        $scope.heroItems = [];
    }

    /**
     * 清空
     */
    $scope.empty = function () {
        $scope.heroSearchData = {};
        $scope.heroItems = [];
    }

    /*
     根据全局game，site查询区服
     */
    gameAreaFnService.gameAreaFindByGameId($scope.areaSearch).success(function (response) {
        $scope.areaLists = response;
        $scope.$apply();
    });
});

