adminApp.config(function ($routeProvider) {
    $routeProvider.when('/syslog/view', {
        templateUrl: ctx + '/syslog/syslogInit',
        controller: 'syslogListController'
    });
});

/**
 * 日志查询
 */
adminApp.controller('syslogListController', function ($scope, syslogFnService,$rootScope) {

    $scope.syslogSerachData = {};//日志查询条件对象
    $scope.syslogSerachData.loginName = "";
    $scope.syslogSerachData.operation = "";
    $scope.syslogData = [];//日志集合
    $scope.syslog = {};//日志对象
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "SysLog";
    $scope.operationUserSearchText = "load.operationUserSearchText";//搜索提示国际化

    /**
     * 刷新日志查询数据
     * @param syslogSerachData
     * @param pageIndex
     * @param pageSize
     */
    function syslogPageListPublic(syslogSerachData, pageIndex, pageSize) {
        syslogFnService.syslogPageList(syslogSerachData, pageIndex, pageSize).success(function (response) {
            $scope.syslogData = response.rows;
            $scope.pageCount = response.page.pageCount;
            $scope.$apply();
        });
    }

    /**
     刷新日志查询数据
     */
    syslogPageListPublic($scope.syslogSerachData, 1, 10);

    /**
     查询表单监听
     */
    $scope.searchButtonClicked = function () {
        /*
         刷新日志查询页面
         */
        syslogPageListPublic($scope.syslogSerachData, 1, 10);
    }

    /**
     分页
     */
    $scope.onPageChange = function () {
        /*
         刷新日志查询页面
         */
        syslogPageListPublic($scope.syslogSerachData, $scope.currentPage, 10);
    };

    /**
     * 条件改变，查询
     * @param data
     */
    $scope.operationChange = function (data) {
        $scope.syslogSerachData.operation = data;
        /*
         刷新日志查询页面
         */
        syslogPageListPublic($scope.syslogSerachData, 1, 10);
    };

    /**
     * 查看
     * @param id
     */
    $scope.viewClicked = function (id) {
        $("#logModalForView").modal("show");
        syslogFnService.getSyslogById(id).success(function (logData) {
            $scope.syslog = logData;
            $scope.$apply();
        });

    };
});

