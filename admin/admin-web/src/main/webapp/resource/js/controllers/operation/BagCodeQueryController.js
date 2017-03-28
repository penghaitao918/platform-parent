/**
 * 礼包码-礼包码查询controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/bagCodeQuery/view', {
        templateUrl: ctx + '/operation/bagCode/bagCodeQueryInit',
        controller: 'bagCodeQueryController'
    }).when("/bagCodeQuery/view/:check", {
        templateUrl: ctx + '/operation/bagCode/bagCodeQueryInit',
        controller: 'bagCodeQueryController'
    });

});


/**
 礼包码-礼包码查询controller
 全局：$scope
 */
adminApp.controller('bagCodeQueryController', function ($scope, bagCodeQueryFnService, $location, $routeParams, $rootScope) {

    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "load.codeQueryTitle";
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.messagesConfirm = {};//设置确认框model数据集合
    $scope.bagCodeQuerySearch = {};
    $scope.bagCodeQuerySearch.gameId = $scope.gameTransfer;//初始化gameId，与全局game同步
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
     * 分页
     * @param bagCodeQuerySearch
     * @param pageIndex
     * @param pageSize
     */
    function batchCodeQueryPage(bagCodeQuerySearch, pageIndex, pageSize) {
        /*
         调用查询方法，并传最新的currentPage
         */
        bagCodeQueryFnService.batchCodeQueryPage(bagCodeQuerySearch, pageIndex, pageSize).success(function (response) {
            $scope.bagCodeQueryDatas = response.rows;
            $scope.pageCount = response.page.pageCount;
            $scope.$apply();
        });
    }

    /**
     调用查询方法，并传最新的currentPage
     */
    batchCodeQueryPage($scope.bagCodeQuerySearch, 1, 10);

});