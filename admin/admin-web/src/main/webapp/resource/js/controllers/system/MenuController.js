/**
 * 菜单管理controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/menu/view', {
        templateUrl: ctx + '/system/menu/menuInit',
        controller: 'menuController'
    });
});
/*
 菜单管理controller
 全局：$scope
 */
adminApp.controller('menuController', function ($scope, menuFnService, $rootScope) {
    $scope.chk = false;//选择状态
    var id = "";//角色id字符串
    var index = 0;//修改角色的下标
    $scope.menuIds = "";//公告id字符串
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.messagesConfirm = {};//设置确认框model数据集合
    $scope.menuSearch = {};//初始化查询条件对象
    $scope.menuSearch.menuName = "";
    $scope.menuData = {};//menu集合
    /* $scope.menuData.menuIcon="fa-cogs";*/
    $scope.menuState = {};//修改状态条件对象
    $scope.menuParent = {};
    $scope.menuJudge = "";
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "MenuManage";
    $scope.menuSearchText = "load.menuSearchText";//搜索提示国际化
    /**
     开启、关闭状态工具方法 ids:id集合 state 1代表开启，0代表关闭
     */
    function stateTool(ids, state) {
        $scope.menuState.ids = ids;
        $scope.menuState.state = state;
        menuFnService.menuStateUpdate($scope.menuState).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
            $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToSuccess";
            $("#messagesModal").modal('show');
            /*
             刷新查询页面，调用Service的查询方法
             */

            menuSelect($scope.menuSearch, $scope.currentPage, 10);

            $scope.menuIds = "";
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
            $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToFailure";
            $("#messagesModal").modal('show');
            /*
             刷新查询页面，调用Service的查询方法
             */
            menuSelect($scope.menuSearch, $scope.currentPage, 10);

            $scope.menuIds = "";
        });
    }

    /**
     刷新查询页面，调用Service的查询方法
     */
    menuFnService.getParentMenu().success(function (response) {
        $scope.menuParent = response;
    });

    /**
     刷新页面工具方法
     */
    function menuSelect(menuSearch, pageIndex, pageSize) {
        /*
         刷新查询页面，调用Service的查询方法
         */
        menuFnService.menuFindByName(menuSearch, pageIndex, pageSize).success(function (response) {
            $scope.menus = response.rows;
            $scope.pageCount = response.page.pageCount;
            $scope.$apply();
        });
    }

    /**
     刷新查询页面
     */
    menuSelect($scope.menuSearch, 1, 10);

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
     用户添加（弹框）
     */
    $scope.addButton = function () {

        var add = function () {
            $scope.menuJudge = "add";
            $scope.menuData = {};
            $("#menuModalForEdit").modal('show');
        };
        //判断是否有权限
        checkAuthority(1, add);//1：添，2：删，3：改，4：查 执行函数
    };


    /**
     监听表单
     */
    $scope.menuEditForm = function () {
        if ($scope.menuJudge == "add") {
            menuFnService.menuAdd($scope.menuData).success(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
                $scope.messagesData.messagesBody = "load.messagesBodyByaddToSuccess";
                $("#messagesModal").modal('show');
                $("input").val("");
                $scope.menuData.parentId = "";
                /*
                 刷新查询页面
                 */
                menuSelect($scope.menuSearch, 1, 10);

                $('#menuModalForEdit').modal('hide');

            }).error(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
                $scope.messagesData.messagesBody = "load.messagesBodyByaddToFailure";
                $("#messagesModal").modal('show');

            });
        } else if ($scope.menuJudge = "update") {
            /*
             调用修改方法
             */
            $scope.menuData.createTime = new Date($scope.menuData.createTime);
            menuFnService.menuUpdate($scope.menuData).success(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
                $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToSuccess";
                $("#messagesModal").modal('show');
                /*
                 刷新查询页面
                 */
                menuSelect($scope.menuSearch, $scope.currentPage, 10);

                $("#menuModalForEdit").modal('hide');
            }).error(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
                $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToFailure";
                $("#messagesModal").modal('show');
                $scope.$apply();
            });
        }


    };

    /**
     用户修改（弹窗）
     */
    $scope.updateClicked = function () {
        var data = $(this);
        var update = function () {
            $scope.menuJudge = "update";
            $scope.menuData = {};
            /*
             调用Service的查询方法,根据id，
             */
            menuFnService.menuGetById(data[0].menu.id).success(function (response) {
                $("#menuModalForEdit").modal('show');
                $scope.menuData = response;
                $scope.menuData.parentId = response.parentId.toString();
                $scope.$apply();
            }).error(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByUnusual";
                $scope.messagesData.messagesBody = "load.messagesBodyByUnusual";
                $("#messagesModal").modal('show');
            });
        }

        //判断是否有权限
        checkAuthority(3, update);//1：添，2：删，3：改，4：查 执行函数

    };


    /**
     一键开启
     */
    $scope.pocOpen = function () {

        var pocOpen = function () {
            if ($scope.menuIds != "") {
                stateTool($scope.menuIds, 1);//1代表开启
            } else {
                $scope.messagesData.messagesTitle = "load.promptTitle";
                $scope.messagesData.messagesBody = "load.promptBody";
                $("#messagesModal").modal('show');
            }
        }

        //判断是否有权限
        checkAuthority(3, pocOpen);//1：添，2：删，3：改，4：查 执行函数
    };

    /**
     一键关闭
     */
    $scope.pocClose = function () {
        var pocClose = function () {
            if ($scope.menuIds != "") {
                stateTool($scope.menuIds, 0);//0代表关闭
            } else {
                $scope.messagesData.messagesTitle = "load.promptTitle";
                $scope.messagesData.messagesBody = "load.promptBody";
                $("#messagesModal").modal('show');
            }

        }
        //判断是否有权限
        checkAuthority(3, pocClose);//1：添，2：删，3：改，4：查 执行函数

    };

    /**
     开启
     */
    $scope.open = function () {
        var data = $(this);
        var open = function () {
            index = data[0].menu.id;
            stateTool(index, 1);//1代表开启
        }
        //判断是否有权限
        checkAuthority(3, open);//1：添，2：删，3：改，4：查 执行函数
    };

    /**
     关闭
     */
    $scope.close = function () {
        var data = $(this);
        var close = function () {
            index = data[0].menu.id;
            stateTool(index, 0);//0代表关闭
        }
        //判断是否有权限
        checkAuthority(3, close);//1：添，2：删，3：改，4：查 执行函数

    };

    /**
     分页
     */
    $scope.onPageChange = function () {
        /*
         刷新查询页面
         */
        menuSelect($scope.menuSearch, $scope.currentPage, 10);
    };

    /**
     查询表单监听
     */
    $scope.searchButtonClicked = function () {
        /*
         刷新查询页面，调用Service的查询方法
         */
        menuFnService.menuFindByName($scope.menuSearch, 1, 10).success(function (response) {
            console.log(response);
            $scope.menus = response.rows;
            $scope.pageCount = response.page.pageCount;
            $scope.$apply();
        });
    }

    /**
     更改公告的选择状态，并保存相应的id
     */
    $scope.check = function (val, chk) {
        var data = $(this);
        if (chk == true) {
            $scope.menuIds += data[0].menu.id + ",";
        } else {
            $scope.menuIds = $scope.menuIds.replace(data[0].menu.id + ",", "");
        }

    };

    /**
     删除角色confirm
     */
    $scope.delete = function () {
        var deleteL = function () {
            if ($scope.menuIds != "") {
                $scope.messagesConfirm.title = "load.messagesConfirmTitleByDelete";
                $scope.messagesConfirm.body = "load.messagesConfirmBodyByDelete";
                $("#messagesConfirm").modal('show');
            } else {
                $scope.messagesData.messagesTitle = "load.promptTitle";
                $scope.messagesData.messagesBody = "load.promptBody";
                $("#messagesModal").modal('show');
            }
        }
        //判断是否有权限
        checkAuthority(2, deleteL);//1：添，2：删，3：改，4：查 执行函数


    };

    /**
     删除角色确认
     */
    $scope.confirm = function () {
        console.log($scope.menuIds);
        /*
         调用删除方法
         */
        menuFnService.menuDelete($scope.menuIds).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToSuccess";
            $("#messagesModal").modal('show');
            /*
             刷新查询页面
             */
            menuSelect($scope.menuSearch, $scope.currentPage, 10);

            $scope.menuIds = "";
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToFailure";
            $("#messagesModal").modal('show');
            $scope.menuIds = "";
            /*
             刷新查询页面
             */
            menuSelect($scope.menuSearch, $scope.currentPage, 10);
        });
    };

});
