adminApp.config(function ($routeProvider) {
    $routeProvider.when('/roleQuery/view', {
        templateUrl: ctx + '/roleQuery/roleQueryInit',
        controller: 'roleSearchController'
    });
});

adminApp.controller('roleSearchController', function ($scope, gameAreaFnService, roleQueryService,$rootScope) {
    $scope.roleSearchData = {};//搜索条件
    $scope.roleSearchData.gameId = $scope.gameTransfer;//初始化gameId，与全局game同步
    $scope.areaSearch = {};//区服查询条件
    $scope.areaSearch.gameId = $scope.gameTransfer;
    $scope.areaSearch.siteId = $scope.siteTransfer;
    $scope.roleSearchBox=0;//0代表搜素框显示，结果信息隐藏，反之。
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "RoleQueryManage";
    /**
     * 游戏change
     * @param gameId
     */
    $scope.gameChange = function (gameId) {
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
     根据全局game，site查询区服
     */
    gameAreaFnService.gameAreaFindByGameId($scope.areaSearch).success(function (response) {
        $scope.areaLists = response;
        $scope.$apply();
    });

    /**
     * 监听表单提交
     */
    $scope.roleSearchForm=function(){
        $scope.roleSearchBox=1;
        roleQueryService.getRoleInfo($scope.roleSearchData).success(function (data) {
            $scope.gameRole=data.gameRole;
            $scope.userAccounts=data.userAccounts;
            $scope.$apply();
        });
    }

    /**
     * 返回搜索
     */
    $scope.returnSearchBox=function(){
        $scope.roleSearchBox=0;
        $scope.roleSearchData.roleId="";
        $scope.roleSearchData.userId="";
        $scope.roleSearchData.roleName="";
    }

    /**
     * 清空
     */
    $scope.empty=function(){
        $scope.roleSearchData={};
    }
});

