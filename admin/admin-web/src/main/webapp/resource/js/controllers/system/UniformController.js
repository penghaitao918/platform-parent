/**
 * 数据字典管理controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/uniform/view', {
        templateUrl: ctx + '/unify/uniform/uniformInit',
        controller: 'uniformController'
    });
});

/*
 数据字典管理controller
 全局：$scope
 资源Service:resourceFnService
 */
adminApp.controller('uniformController', function ($scope, uniformFnService, resourceFnService, $rootScope) {
    $scope.chk = false;//选择状态
    var id = "";//角色id字符串
    var index = "";//修改
    $scope.resUpdateData = {};//设置updateForm控件model数据集合
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.messagesConfirm = {};//设置确认框model数据集合
    $scope.uniFormData = {};//下拉框条件对象
    $scope.uniFormData.gameId = $scope.gameTransfer;//初始化查询gameId
    $scope.uniFormData.siteId = $scope.siteTransfer;//初始化查询gameId
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "DropBoxConfiguration";
    $scope.selectDatas = [];//下拉框集合
    $scope.selectData = {};//下拉框对象
    /*
     初始化数值
     */
    $scope.selectData.key = "";
    $scope.selectData.value = "";
    $scope.uniform = {};
    $scope.uniform.dictionaryName = "";
    $scope.uniform.gameId = $scope.gameTransfer;//初始化查询gameId
    $scope.uniform.siteId = $scope.siteTransfer;//初始化查询gameId

    $scope.flag = "";


    /*
     下拉框添加
     */
    $scope.uniAddClicked = function () {
        if ($scope.selectDatas == "") {
            var data = [];
        } else {
            var data = $scope.selectDatas;//临时数据集合
        }
        var selectData = {};

        if ($(".idFrom").val() != "" && $(".codeFrom").val() != "") {
            selectData.key = $(".idFrom").val();
            selectData.value = $(".codeFrom").val();
            data.push(selectData);
            $scope.selectDatas = data;
            $(".idFrom").val("");
            $(".codeFrom").val("");
        } else {
            return
        }

    };

    /*
     下拉框对象删除
     */
    $scope.uniDeleteClicked = function () {
        var List = $scope.selectDatas;
        var data = $(this);
        index = data[0].$index;
        List.splice(index, 1);
    };


    /*
     刷新查询页面，调用查询方法
     */
    uniformFnService.uniAll($scope.uniform.gameId, $scope.uniform.siteId).success(function (response) {
        $scope.res = response;
        $scope.$apply();
    });

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

    /*
     删除下拉框key confirm
     */
    $scope.deleteClicked = function (dictionaryName) {
        var deleteL = function () {
            index = dictionaryName;
            $scope.messagesConfirm.title = "load.messagesConfirmTitleByDelete";
            $scope.messagesConfirm.body = "load.messagesConfirmBodyByDelete";
            $("#messagesConfirm").modal('show');
        };
        //判断是否有权限
        checkAuthority(2, deleteL);//1：添，2：删，3：改，4：查 执行函数

    };

    /*
    刷新缓存
     */
    $scope.refreshCache=function(){
        uniformFnService.refreshCache().success(function(){
            /*
             刷新查询页面，调用查询方法
             */
            uniformFnService.uniAll($scope.uniform.gameId, $scope.uniform.siteId).success(function (response) {
                $scope.res = response;
                $scope.$apply();
            });
        });
    }

    /*
     删除下拉框key
     */
    $scope.confirm = function () {
        $scope.uniFormData.key = index;
        /*
         调用删除方法
         */
        uniformFnService.uniDeleteByKey($scope.uniFormData).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToSuccess";
            $("#messagesModal").modal('show');
            /*
             刷新查询页面，调用查询方法
             */
            uniformFnService.uniAll($scope.uniform.gameId, $scope.uniform.siteId).success(function (response) {
                $scope.res = response;
                $scope.$apply();
            });
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToFailure";
            $("#messagesModal").modal('show');
            /*
             刷新查询页面，调用查询方法
             */
            uniformFnService.uniAll($scope.uniform.gameId, $scope.uniform.siteId).success(function (response) {
                $scope.res = response;
                $scope.$apply();
            });

        });
    };

    /*
     修改（弹框）
     */
    $scope.updateClicked = function () {
        var data = $(this);
        var update = function () {
            $scope.flag = "update";
            $(".dictionaryName").attr("readonly", "readonly");
            index = data[0].r;
            $scope.uniFormData.key = index.dictionaryName;
            $("#gameList").attr("disabled", true);
            $("#siteList").attr("disabled", true);
            $scope.uniFormData.gameId = $scope.gameTransfer;//初始化查询gameId
            $scope.uniFormData.siteId = $scope.siteTransfer;//初始化查询gameId
            uniformFnService.uniGetKey($scope.uniFormData).success(function (response) {
                $scope.uniform = response;
                $scope.selectDatas = JSON.parse(response.itemJson);
                $scope.$apply();
                $("#uniformEditModal").modal('show');
                console.log(response);
            });
        };
        //判断是否有权限
        checkAuthority(3, update);//1：添，2：删，3：改，4：查 执行函数
    };

    /*
     监听uniformEdit表单
     */
    $scope.uniformEdit = function () {
        /* var ids=[];
         var code=[];*/
        for (var i = 0; i < $scope.selectDatas.length; i++) {
            delete $scope.selectDatas[i].$$hashKey;
            /*  ids.push($scope.selectDatas[i].id);
             code.push($scope.selectDatas[i].code);*/
        }
        /* $scope.uniform.id=ids;
         $scope.uniform.code=code;*/
        $scope.uniform.itemJson = JSON.stringify($scope.selectDatas);

        if ($scope.flag == "add") {
            /*
             调用添加方法
             */
            uniformFnService.uniAdd($scope.uniform).success(function (data) {
                if (data == null || data == "") {
                    $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
                    $scope.messagesData.messagesBody = "load.messagesBodyByaddToSuccess";
                    $("#messagesModal").modal('show');
                } else {
                    $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
                    $scope.messagesData.messagesBody = data;
                    $("#messagesModal").modal('show');
                }
                $scope.selectDatas = [];
                $scope.$apply();
                $('#uniformEditModal').modal('hide');

                /*
                 刷新查询页面，调用查询方法
                 */
                uniformFnService.uniAll($scope.uniform.gameId, $scope.uniform.siteId).success(function (response) {
                    $scope.res = response;
                    $scope.$apply();
                });
            }).error(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
                $scope.messagesData.messagesBody = "load.messagesBodyByaddToFailure";
                $scope.selectDatas = [];
                $scope.$apply();
                $("#messagesModal").modal('show');
                $('#uniformEditModal').modal('hide');
                /*
                 刷新查询页面，调用查询方法
                 */
                uniformFnService.uniAll($scope.uniform.gameId, $scope.uniform.siteId).success(function (response) {
                    $scope.res = response;
                    $scope.$apply();
                });
            });
        } else if ($scope.flag == "update") {

            $scope.uniform.createTime = new Date($scope.uniform.createTime);
            /*
             调用添加方法
             */
            uniformFnService.uniUpdate($scope.uniform).success(function (data) {
                if (data == null || data == "") {
                    $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
                    $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToSuccess";
                    $("#messagesModal").modal('show');
                } else {
                    $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
                    $scope.messagesData.messagesBody = data;
                    $("#messagesModal").modal('show');
                }
                $scope.selectDatas = [];
                $scope.$apply();
                $('#uniformEditModal').modal('hide');

                /*
                 刷新查询页面，调用查询方法
                 */
                uniformFnService.uniAll($scope.uniform.gameId, $scope.uniform.siteId).success(function (response) {
                    $scope.res = response;
                    $scope.$apply();
                });
            }).error(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
                $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToFailure";
                $scope.selectDatas = [];
                $scope.$apply();
                $("#messagesModal").modal('show');
                $('#uniformEditModal').modal('hide');
                /*
                 刷新查询页面，调用查询方法
                 */
                uniformFnService.uniAll($scope.uniform.gameId, $scope.uniform.siteId).success(function (response) {
                    $scope.res = response;
                    $scope.$apply();
                });
            });
        }

    };
    /*
     添加（弹框）
     */
    $scope.addButton = function () {

        var add = function () {
            $scope.flag = "add";
            $(".dictionaryName").attr("readonly", false);
            $scope.uniform = {};
            $scope.selectDatas = [];
            $("#gameList").attr("disabled", false);
            $("#siteList").attr("disabled", false);

            $("#uniformEditModal").modal('show');
        };
        //判断是否有权限
        checkAuthority(1, add);//1：添，2：删，3：改，4：查 执行函数

    };


});




