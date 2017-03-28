/**
 * 自定义表单controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/customForm/view', {
        templateUrl: ctx + '/CustomForm/CustomFormInit',
        controller: 'customFormController'
    }).when('/customForm/view/:check', {
        templateUrl: ctx + '/CustomForm/CustomFormInit',
        controller: 'customFormController'
    });
});

/**
 自定义表单管理controller
 全局：$scope
 */
adminApp.controller('customFormController', function ($scope, customFormService, $location, $routeParams, $rootScope) {
    var index = "";//修改
    $scope.customFormData = {};
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.messagesConfirm = {};//设置确认框model数据集合
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "CustomForm";

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
     添加（跳转）
     */
    $scope.addButton = function () {
        var add = function () {
            $location.path("/customFormEdit/view");
        };
        //判断是否有权限
        checkAuthority(1, add);//1：添，2：删，3：改，4：查 执行函数

    }

    if ($routeParams.check == 1) {
        $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
        $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToSuccess";
    } else if ($routeParams.check == 0) {
        $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
        $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToFailure";
    } else if ($routeParams.check == 2) {
        $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
        $scope.messagesData.messagesBody = "load.messagesBodyByaddToSuccess";
    } else if ($routeParams.check == 3) {
        $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
        $scope.messagesData.messagesBody = "load.messagesBodyByaddToFailure";
    } else if ($routeParams.check == "load.diyForm16006") {
        $scope.messagesData.messagesTitle = "load.messagesTitleByUnusual";
        $scope.messagesData.messagesBody = "load.diyForm16006";
    }

    /**
     * 查询自定义表单
     */
    function customFormAllPublic() {
        customFormService.customFormAll().success(function (response) {
            $scope.customFormList = response;
            $scope.$apply();
        });
    }

    /**
     刷新查询页面，调用查询方法
     */
    customFormAllPublic();

    /**
     修改（弹框）
     */
    $scope.updateClicked = function () {
        var data = $(this);
        var update = function () {
            index = data[0].customForm.formCode;
            $location.path("/customFormEdit/view/" + index + "/update");
        };
        //判断是否有权限
        checkAuthority(3, update);//1：添，2：删，3：改，4：查 执行函数

    };
    /**
     删除下拉框key confirm
     */
    $scope.deleteClicked = function () {
        var data = $(this);
        var update = function () {
            index = data[0].customForm.formCode;
            $scope.messagesConfirm.title = "load.messagesConfirmTitleByDelete";
            $scope.messagesConfirm.body = "load.messagesConfirmBodyByDelete";
            $("#messagesConfirm").modal('show');
        };
        //判断是否有权限
        checkAuthority(2, update);//1：添，2：删，3：改，4：查 执行函数
    };

    /**
     删除下拉框key
     */
    $scope.confirm = function () {
        $scope.customFormData.customs = index;
        /*
         调用删除方法
         */
        customFormService.customFormDeleteByKey($scope.customFormData).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToSuccess";
            $("#messagesModal").modal('show');
            /*
             刷新查询页面，调用查询方法
             */
            customFormAllPublic();
            index = "";

        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToFailure";
            $("#messagesModal").modal('show');
            /*
             /*
             刷新查询页面，调用查询方法
             */
            customFormAllPublic();

        });
    };

});




