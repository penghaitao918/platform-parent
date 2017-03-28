/**
 * 礼包码-礼包码处理controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/bagCodeDispose/view', {
        templateUrl: ctx + '/operation/bagCode/bagCodeDisposeInit',
        controller: 'bagCodeDisposeController'
    }).when("/bagCodeDispose/view/:check", {
        templateUrl: ctx + '/operation/bagCode/bagCodeDisposeInit',
        controller: 'bagCodeDisposeController'
    });

});


/**
 礼包码-礼包码处理controller
 全局：$scope
 */
adminApp.controller('bagCodeDisposeController', function ($scope, bagCodeBatchFnService, $location, $routeParams, $rootScope) {


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


});