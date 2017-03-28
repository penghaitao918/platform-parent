/**
 * 角色管理controllers文件
 *   author：钟亮
 * @type {module|*}
 */
/*var roleapp=angular.module("roleApp", ['ng-pagination','roleService']);*/
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/role/view', {
        templateUrl: ctx + '/system/role/roleInit',
        controller: 'demoCtrl'
    }).when("/role/view/:check", {
        templateUrl: ctx + '/system/role/roleInit',
        controller: 'demoCtrl'
    });
    ;
});


/**
 角色管理controller
 全局：$scope
 角色Service:roleFnService
 */
adminApp.controller('demoCtrl', function ($scope, roleFnService, $location, $routeParams, $rootScope) {
    /**
     角色信息（绑定添加）
     */
    $scope.role = "";
    $scope.description = "";
    $scope.available = "";
    $scope.memo = "";

    $scope.chk = false;//选择状态
    var id = "";//角色id字符串
    var index = 0;//修改角色的下标
    $scope.roleEditData = {};//数据集合
    $scope.roleJudge = "";
    $scope.roleAddData = {};//设置addForm控件model数据集合
    $scope.roleUpdateData = {};//设置updateForm控件model数据集合
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.messagesConfirm = {};//设置确认框model数据集合
    $scope.roleState = {};//修改状态条件对象
    $scope.roleSearchText = "load.roleSearchText";//搜索提示国际化
    if ($routeParams.check == 1) {
        $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
        $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToSuccess";
    }

    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "RoleManage";
    /**
     刷新role角色查询页面，调用roleService的查询方法
     */
    function roleFindByNamePublic(searchText, pageIndex, pageSize) {
        roleFnService.roleFindByName(searchText, pageIndex, pageSize).success(function (response) {
            console.log(response);
            $scope.roles = response.rows;
            $scope.pageCount = response.page.pageCount;
            $scope.$apply();
        });
    }

    /**
     刷新role角色查询页面，调用roleService的查询方法
     */
    roleFindByNamePublic($(".searchText").val(), 1, 10);

    /**
     开启、关闭状态工具方法 ids:id集合 state 1代表开启，0代表关闭
     */
    function stateTool(ids, state) {
        $scope.roleState.ids = ids;
        $scope.roleState.state = state;
        roleFnService.roleStateUpdate($scope.roleState).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
            $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToSuccess";
            $("#messagesModal").modal('show');
            /*
             调用roleService的查询方法，并传最新的currentPage
             */
            roleFindByNamePublic($(".searchText").val(), $scope.currentPage, 10);

            id = "";
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
            $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToFailure";
            $("#messagesModal").modal('show');
            /*
             调用roleService的查询方法，并传最新的currentPage
             */
            roleFindByNamePublic($(".searchText").val(), $scope.currentPage, 10);
        });
    }

    /**
     分页
     */
    $scope.onPageChange = function () {
        // ajax request to load data
        console.log($scope.currentPage);
        /*
         调用roleService的查询方法，并传最新的currentPage
         */
        roleFindByNamePublic($(".searchText").val(), $scope.currentPage, 10);
    };

    $scope.accreditClicked = function () {
        var data = $(this);
        $location.path("/accredit/view/" + data[0].role.id + "/" + 2);//2：角色权限
    };

    /**
     更改角色的选择状态，并保存相应的id
     */
    $scope.check = function (val, chk) {
        var data = $(this);
        data[0].role.id;

        if (chk == true) {
            id += data[0].role.id + ",";
        } else {
            id = id.replace(data[0].role.id + ",", "");
        }

    };

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
     一键开启
     */
    $scope.pocOpen = function () {

        var pocOpen = function () {
            if (id != "") {
                stateTool(id, 1);//1代表开启
            } else {
                $scope.messagesData.messagesTitle = "load.promptTitle";
                $scope.messagesData.messagesBody = "load.promptBody";
                $("#messagesModal").modal('show');
            }
        };
        //判断是否有权限
        checkAuthority(3, pocOpen);//1：添，2：删，3：改，4：查 执行函数


    };

    /**
     一键关闭
     */
    $scope.pocClose = function () {
        var pocClose = function () {
            if (id != "") {
                stateTool(id, 0);//0代表关闭
            } else {
                $scope.messagesData.messagesTitle = "load.promptTitle";
                $scope.messagesData.messagesBody = "load.promptBody";
                $("#messagesModal").modal('show');
            }
        };
        //判断是否有权限
        checkAuthority(3, pocClose);//1：添，2：删，3：改，4：查 执行函数


    };

    /**
     开启
     */
    $scope.open = function () {
        var data = $(this);
        var open = function () {
            index = data[0].role.id;
            stateTool(index, 1);//1代表开启
        };
        //判断是否有权限
        checkAuthority(3, open);//1：添，2：删，3：改，4：查 执行函数


    };

    /**
     关闭
     */
    $scope.close = function () {
        var data = $(this);
        var close = function () {
            index = data[0].role.id;
            stateTool(index, 0);//0代表关闭
        };
        //判断是否有权限
        checkAuthority(3, close);//1：添，2：删，3：改，4：查 执行函数
    };

    /**
     删除角色confirm
     */
    $scope.delete = function () {
        var deleteL = function () {
            if (id != "") {
                $scope.messagesConfirm.title = "load.messagesConfirmTitleByDelete";
                $scope.messagesConfirm.body = "load.messagesConfirmBodyByDelete";
                $("#messagesConfirm").modal('show');
            } else {
                $scope.messagesData.messagesTitle = "load.promptTitle";
                $scope.messagesData.messagesBody = "load.promptBody";
                $("#messagesModal").modal('show');
            }
        };
        //判断是否有权限
        checkAuthority(2, deleteL);//1：添，2：删，3：改，4：查 执行函数
    };

    /**
     删除角色确认
     */
    $scope.confirm = function () {
        /*
         调用roleService的删除方法
         */
        roleFnService.roleDelete(id).success(function (data) {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToSuccess";
            $("#messagesModal").modal('show');
            /*
             操作成功之后，刷新页面，调用roleService的查询方法
             */
            roleFindByNamePublic($(".searchText").val(), $scope.currentPage, 10);
            id = "";
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToFailure";
            $("#messagesModal").modal('show');
        });
    }

    /**
     角色添加（弹框）
     */
    $scope.addButton = function () {
        var add = function () {
            $scope.roleJudge = "add";
            $scope.roleEditData = {};
            $("#myModalForEdit").modal('show');
        };
        //判断是否有权限
        checkAuthority(1, add);//1：添，2：删，3：改，4：查 执行函数

    };


    /**
     监听表单
     */
    $scope.roleEditForm = function () {
        if ($scope.roleJudge == "add") {
            /*
             调用roleService的添加方法
             */
            roleFnService.roleAdd($scope.roleEditData).success(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
                $scope.messagesData.messagesBody = "load.messagesBodyByaddToSuccess";
                $("#messagesModal").modal('show');
                $("input").val("");
                $("textarea").val("");
                /*
                 操作成功之后，调用roleService的查询方法
                 */
                roleFindByNamePublic($(".searchText").val(), 1, 10);
                $('#myModalForEdit').modal('hide');
            }).error(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
                $scope.messagesData.messagesBody = "load.messagesBodyByaddToFailure";
                $("#messagesModal").modal('show');
            });
        } else if ($scope.roleJudge == "update") {
            /*
             调用roleService的修改方法
             */
            $scope.roleEditData.createTime = new Date($scope.roleEditData.createTime);
            roleFnService.roleUpdate($scope.roleEditData).success(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
                $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToSuccess";
                $("#messagesModal").modal('show');
                /*
                 操作成功之后，调用roleService的查询方法
                 */
                roleFindByNamePublic($(".searchText").val(), $scope.currentPage, 10);
                $("#myModalForEdit").modal('hide');
            }).error(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
                $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToFailure";
                $("#messagesModal").modal('show');
            });
        }

    };


    /**
     角色修改（弹窗）
     */
    $scope.updateClicked = function () {
        var data = $(this);
        var update = function () {
            $scope.roleJudge = "update";
            $scope.roleEditData = {};
            /*
             调用roleService的查询方法,根据id，
             */
            roleFnService.roleGetById(data[0].role.id).success(function (response) {
                $("#myModalForEdit").modal('show');
                $scope.roleEditData = response;
                $scope.$apply();
            }).error(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByUnusual";
                $scope.messagesData.messagesBody = "load.messagesBodyByUnusual";
                $("#messagesModal").modal('show');
            });
        };
        //判断是否有权限
        checkAuthority(3, update);//1：添，2：删，3：改，4：查 执行函数


    };


    /**
     根据role名称模糊查询
     */
    $scope.searchButtonClicked = function () {
        /*
         调用roleService的查询方法
         */
        roleFindByNamePublic($(".searchText").val(), 1, 10);
    };
});


