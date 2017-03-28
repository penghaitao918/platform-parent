/**
 * 版本补丁编辑controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/editionEdit/view', {
        templateUrl: ctx + '/platform/edition/editionEditInit',
        controller: 'editionEditController'
    }).when("/editionEdit/view/:id/:judge", {
        templateUrl: ctx + '/platform/edition/editionEditInit',
        controller: 'editionEditController'
    });
    ;
});


/**
 版本补丁编辑controller
 全局：$scope
 */
adminApp.controller('editionEditController', function ($scope, editionFnService, $location, uniformFnService, $routeParams, $rootScope) {
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "load.versionEditManage";
    $rootScope.menuBarData.menuBarTitle = "load.versionEditManage";
    $scope.messagesData = {};//设置提示框model数据集合

    $scope.editionData = {};//版本补丁数据源
    $scope.editionData.whiteStatusView = "2";
    var check = -1;//判断是否成功，url参数 1，是修改成功，0是修改失败，2是添加成功，3是添加失败，-1是失效\

    $scope.editionEditForm = function () {
        if ($routeParams.judge == "update") {
            editionFnService.editionUpdate($scope.editionData).success(function (data) {
                check = 1;
                if (data != null && data != "") {
                    check = data;
                }
                $location.path("/edition/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');
            }).error(function () {
                check = 0;
                $location.path("/edition/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            });
        } else {
            editionFnService.editionAdd($scope.editionData).success(function (data) {
                check = 2;
                if (data != null && data != "") {
                    check = data;
                }
                $location.path("/edition/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');
            }).error(function () {
                check = 3;
                $location.path("/edition/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            });
        }
    }

    /**
     * 如果跳转参数是update，根据id查询edition对象
     */
    if ($routeParams.judge == "update" ) {
        /*
         根据id查询公告
         */
        editionFnService.editionSelectById($routeParams.id).success(function (data) {
            $scope.editionData =data;
            $scope.editionData.validStatusView=data.validStatus.toString();
            $scope.editionData.deviceTypeView=data.deviceType.toString();
            $scope.editionData.whiteStatusView=data.whiteStatus.toString();
            delete $scope.editionData.validStatus;
            delete $scope.editionData.deviceType;
            delete $scope.editionData.whiteStatus;
            $scope.$apply();
        });

    }


});