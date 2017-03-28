/**
 * 批次管理controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/homePage/view', {
        templateUrl: ctx + '/system/HomePage',
        controller: 'homePageController'
    }).when('/homePage/view/:login', {
        templateUrl: ctx + '/system/HomePage',
        controller: 'homePageController'
    });
});


/**
 玩具管理controller
 全局：$scope
 */
adminApp.controller('homePageController', function ($rootScope, $scope, userFnService) {
//初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "load.HomePage";
    $rootScope.menuBarData.menuBarOneName="";
    $rootScope.menuBarData.menuBarTwoName="";
});