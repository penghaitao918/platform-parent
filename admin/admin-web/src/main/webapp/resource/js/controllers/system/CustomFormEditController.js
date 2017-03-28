/**
 * 自定义表单编辑controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/customFormEdit/view', {
        templateUrl: ctx + '/CustomForm/CustomFormEditInit',
        controller: 'customFormEditController'
    }).when('/customFormEdit/view/:id/:judge', {
        templateUrl: ctx + '/CustomForm/CustomFormEditInit',
        controller: 'customFormEditController'
    });
});

/**
 自定义表单edit管理controller
 全局：$scope
 */
adminApp.controller('customFormEditController', function ($scope, customFormService, $location, uniformFnService, $routeParams,$rootScope) {

    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "CustomFormEdit";
    $rootScope.menuBarData.menuBarTitle = "CustomFormEdit";
    $scope.customFormEditFormData = {};
    $scope.analogData = [];//表单集合
    $scope.formObject = {};//表单对象
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.customFormEditFormData.customGameList = $scope.gameTransfer;//初始化gameId，与全局game同步
    $scope.uniFormData = {};//下拉框选项集合key对象初始化
    $scope.uniFormData.key = "operation.item.itemType";//初始化动态下拉框的key
    $scope.formData = {};//表单元素对象
    var check = -1;//判断是否成功，url参数 1，是修改成功，0是修改失败，2是添加成功，3是添加失败，-1是失效
    /**
     加载下拉框
     */
    uniformFnService.uniGetKey($scope.uniFormData).success(function (response) {
        $scope.itemTypeList = JSON.parse(response.itemJson);
        if ($routeParams.id) {
            customFormService.customFormByKey({key: $routeParams.id}).success(function (response) {
                $scope.customFormEditFormData.createTime = response.createTime;
                $scope.customFormEditFormData.formName = response.formName;
                var data = response.formCode.split(".");
                if (data.length != 0) {
                    $scope.customFormEditFormData.gameId = Number(data[data.length - 2]);
                    $scope.customFormEditFormData.itemType = data[data.length - 1].toString();
                }
                $scope.analogData = JSON.parse(response.formJson);
                $scope.$apply();
            });
        }
    });


    /**
     添加新的表单元素
     */
    $scope.formEdit = function () {
        if ($scope.formData.formCode != undefined && $scope.formData.formName != undefined) {
            if ($scope.analogData.length > 4) {
                $scope.messagesData.messagesTitle = "load.messagesTitleByForm";
                $scope.messagesData.messagesBody = "load.messagesBodyByForm";
                $("#messagesModal").modal('show');
            } else {
                $scope.formObject = {};
                $scope.formObject = $scope.formData;
                $scope.analogData.push($scope.formObject);
                $scope.formData = {};
            }
        } else {
            $scope.messagesData.messagesTitle = "load.promptTitle";
            $scope.messagesData.messagesBody = "load.messagesBodyForControl";
            $("#messagesModal").modal('show');
        }


    }

    /**
     清空当前填写信息
     */
    $scope.formEditCancel = function () {
        $scope.formData = {};
    }

    /**
     重置已添加的表单元素
     */
    $scope.ResetForm = function () {
        $scope.analogData = [];
    }


    /**
     * 编辑
     */
    $scope.customFormEditForm = function () {
        for (var i = 0; i < $scope.analogData.length; i++) {
            delete $scope.analogData[i].$$hashKey;
        }
        $scope.customFormEditFormData.formJson = JSON.stringify($scope.analogData);


        if ($routeParams.judge == "update") {
            $scope.customFormEditFormData.createTime = new Date($scope.customFormEditFormData.createTime);
            /*
             调用修改方法
             */
            customFormService.customFormUpdate($scope.customFormEditFormData).success(function (data) {

                if (data == null || data == "") {
                    check = 1;
                } else {
                    check = data;
                }

                $location.path("/customForm/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            }).error(function () {
                check = 0;
                $location.path("/customForm/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');
            });
        } else {
            /*
             调用添加方法
             */
            customFormService.customFormAdd($scope.customFormEditFormData).success(function (data) {
                if (data == null || data == "") {
                    check = 2;
                } else {
                    check = data;
                }

                $location.path("/customForm/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            }).error(function () {
                check = 3;
                $location.path("/customForm/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');
            });
        }

    }
});




