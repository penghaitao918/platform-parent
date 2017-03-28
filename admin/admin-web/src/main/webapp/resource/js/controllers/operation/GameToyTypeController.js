/**
 * 道具管理controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/gameToyType/view', {
        templateUrl: ctx + '/operation/gameToyType/gameToyTypeInit',
        controller: 'gameToyTypeController'
    });
});


/**
 道具管理controller
 全局：$scope
 */
adminApp.controller('gameToyTypeController', function ($scope, gameToyTypeFnService, uniformFnService, $rootScope) {
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "GameItemManage";
    $scope.chk = false;//选择状态
    var id = "";//用户id字符串
    $scope.gameToyTypeJudge = "";//添加or修改
    $scope.gameToyTypeEditData = {};//数据集合

    $scope.gameToyTypeAddData = {};//设置addForm控件model数据集合
    $scope.gameToyTypeUpdateData = {};
    $scope.gameItemNameSearchText = "load.gameItemNameSearchText";//搜索提示国际化

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
     玩具添加（弹框）
     */
    $scope.addButton = function () {
        var add = function () {
            $scope.gameToyTypeEditData = {};
            $scope.gameToyTypeEditData.gameId = $scope.gameTransfer;//初始化gameId，与全局game同步
            $scope.gameToyTypeJudge = "add";
            $("#gameToyTypeModalForEdit").modal('show');
        };
        //判断是否有权限
        checkAuthority(1, add);//1：添，2：删，3：改，4：查 执行函数

    };

    $scope.gameToyTypeSearch = {};//初始化查询条件对象
    $scope.gameToyTypeSearch.name = "";
    $scope.gameToyTypeSearch.gameId = $scope.gameTransfer;//初始化查询gameId
    if ($scope.gameToyTypeSearch.gameId == null) {
        $scope.gameToyTypeSearch.gameId = -1;
    }
    $scope.gameToyTypeEditData.gameId = $scope.gameTransfer;//初始化gameId，与全局game同步

    $scope.gameToyGameList = $scope.gameTransfer;//gameToyGameList，与全局game同步

    $scope.messagesData = {};//设置提示框model数据集合
    $scope.messagesConfirm = {};//设置确认框model数据集合
    $scope.gameToyTypeIds = "";//选中ids集合
    $scope.typeState = {};//修改状态条件集合
    $scope.uniFormData = {};//下拉框选项集合key对象初始化
    $scope.itemTypeList = [];//动态下拉框初始化
    /**
     绑定game,site select，change事件
     */
    $scope.resourceGameListChange = function (id) {
        $scope.gameToyTypeEditData.gameId = id;
        console.log(id);
    };
    $scope.uniFormData.gameId = $scope.gameTransfer;//初始化查询gameId
    if ($scope.uniFormData.gameId == null) {
        $scope.uniFormData.gameId = -1;
    }
    $scope.uniFormData.key = "operation.item.itemType";//初始化动态下拉框的key
    /**
     加载下拉框
     */
    uniformFnService.uniGetKey($scope.uniFormData).success(function (response) {
        $scope.itemTypeList = JSON.parse(response.itemJson);
    });

    function gameToyTypeFindByNamePublic(gameToyTypeSearch, pageIndex, pageSize) {
        gameToyTypeFnService.gameToyTypeFindByName(gameToyTypeSearch, pageIndex, pageSize).success(function (response) {
            $scope.gameTypeToys = response.rows;
            $scope.pageCount = response.page.pageCount;
            $scope.$apply();
        });
    };

    /**
     查询表单监听
     */
    $scope.searchButtonClicked = function () {
        /*
         刷新查询页面，调用查询方法
         */
        gameToyTypeFindByNamePublic($scope.gameToyTypeSearch, 1, 10);

    }


    /**
     刷新查询页面，调用查询方法
     */
    gameToyTypeFindByNamePublic($scope.gameToyTypeSearch, 1, 10);

    /**
     分页
     */
    $scope.onPageChange = function () {
        /*
         刷新查询页面，调用查询方法
         */
        gameToyTypeFindByNamePublic($scope.gameToyTypeSearch, $scope.currentPage, 10);
    };


    /**
     监听编辑表单
     */
    $scope.gameToyTypeEditForms = function () {

        if ($scope.gameToyTypeJudge == "add") {
            /*
             调用添加方法
             */
            gameToyTypeFnService.gameToyTypeAdd($scope.gameToyTypeEditData).success(function (data) {

                if (data == null || data == "") {
                    $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
                    $scope.messagesData.messagesBody = "load.messagesBodyByaddToSuccess";
                    $("#messagesModal").modal('show');
                } else {
                    $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
                    $scope.messagesData.messagesBody = data;
                    $("#messagesModal").modal('show');
                }

                $("input").val("");
                /*
                 刷新查询页面，调用查询方法
                 */
                gameToyTypeFindByNamePublic($scope.gameToyTypeSearch, 1, 10);

                $('#gameToyTypeModalForEdit').modal('hide');
            }).error(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
                $scope.messagesData.messagesBody = "load.messagesBodyByaddToFailure";
                $scope.$apply();
                $("#messagesModal").modal('show');
                /*
                 刷新查询页面，调用查询方法
                 */
                gameToyTypeFindByNamePublic($scope.gameToyTypeSearch, 1, 10);
            });
            $scope.gameToyGameList = "";
        } else if ($scope.gameToyTypeJudge == "update") {
            /*
             调用修改方法
             */
            $scope.gameToyTypeEditData.createTime = new Date($scope.gameToyTypeEditData.createTime);
            gameToyTypeFnService.gameToyTypeUpdate($scope.gameToyTypeEditData).success(function (data) {

                if (data == null || data == "") {
                    $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
                    $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToSuccess";
                    $("#messagesModal").modal('show');
                } else {
                    $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
                    $scope.messagesData.messagesBody = data;
                    $("#messagesModal").modal('show');
                }
                /*
                 刷新查询页面，调用查询方法
                 */
                gameToyTypeFindByNamePublic($scope.gameToyTypeSearch, $scope.currentPage, 10);
                $("#gameToyTypeModalForEdit").modal('hide');
            }).error(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
                $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToFailure";
                $("#messagesModal").modal('show');
            });
        }


    };

    /**
     导入（弹框）
     */
    $scope.fileImport = function () {
        var add = function () {
            $("#fileImportModal").modal("show");
        };
        //判断是否有权限
        checkAuthority(1, add);//1：添，2：删，3：改，4：查 执行函数

    }

    /**
     导入提交
     */
    $scope.fileImportFormsClick = function () {
        $("#fileImportModal").modal("hide");
        /*
         刷新查询页面，调用查询方法
         */
        gameToyTypeFindByNamePublic($scope.gameToyTypeSearch, 1, 10);
    }

    /**
     更改选择状态，并保存相应的id
     */
    $scope.check = function (val, chk) {
        var data = $(this);
        if (chk == true) {
            $scope.gameToyTypeIds += data[0].gameToyType.id + ",";
        } else {
            $scope.gameToyTypeIds = $scope.gameToyTypeIds.replace(data[0].gameToyType.id + ",", "");
        }

    };


    /**
     删除角色confirm
     */
    $scope.delete = function () {

        var deleteL = function () {
            if ($scope.gameToyTypeIds != "") {
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
         调用删除方法
         */
        gameToyTypeFnService.gameToyTypeDelete($scope.gameToyTypeIds).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToSuccess";
            $("#messagesModal").modal('show');

            /*
             刷新查询页面，调用查询方法
             */
            gameToyTypeFindByNamePublic($scope.gameToyTypeSearch, $scope.currentPage, 10);

            $scope.gameToyTypeIds = "";
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToFailure";
            $("#messagesModal").modal('show');
        });
    };


    /**
     用户修改（弹窗）
     */
    $scope.updateClicked = function () {
        var data = $(this);
        var update = function () {
            $scope.gameToyTypeEditData = {};
            $scope.gameToyTypeJudge = "update";
            /*
             调用roleService的查询方法,根据id，
             */
            gameToyTypeFnService.gameToyTypeGetByeId(data[0].gameToyType.id).success(function (response) {
                $("#gameToyTypeModalForEdit").modal('show');
                $scope.gameToyTypeEditData = response;
                $scope.gameToyTypeEditData.itemType = response.itemType.toString();
                $scope.gameToyGameList = response.gameId;
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
     绑定game,site select，change事件
     */
    $scope.updateGameListChange = function (id) {
        $scope.gameToyTypeUpdateData.gameId = id;
    };


    /**
     开启、关闭状态工具方法 state 1代表开启，0代表关闭
     */
    function stateTool(ids, state) {
        $scope.typeState.ids = ids;
        $scope.typeState.state = state;
        gameToyTypeFnService.stateUpdate($scope.typeState).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
            $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToSuccess";
            $("#messagesModal").modal('show');
            /*
             刷新查询页面，调用查询方法
             */
            gameToyTypeFindByNamePublic($scope.gameToyTypeSearch, $scope.currentPage, 10);
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
            $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToFailure";
            $("#messagesModal").modal('show');
            /*
             刷新查询页面，调用查询方法
             */
            gameToyTypeFindByNamePublic($scope.gameToyTypeSearch, $scope.currentPage, 10);
        });
        ;
    }

    /**
     开启
     */
    $scope.open = function () {
        var data = $(this);
        var open = function () {
            stateTool(data[0].gameToyType.id, 1);
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
            stateTool(data[0].gameToyType.id, 0);
        };
        //判断是否有权限
        checkAuthority(3, close);//1：添，2：删，3：改，4：查 执行函数

    };

    /**
     一键开启
     */
    $scope.pocOpen = function () {
        var pocOpen = function () {
            if ($scope.gameToyTypeIds != "") {
                stateTool($scope.gameToyTypeIds, 1);
                $scope.gameToyTypeIds = "";
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
            if ($scope.gameToyTypeIds != "") {
                stateTool($scope.gameToyTypeIds, 0);
                $scope.gameToyTypeIds = "";
            } else {
                $scope.messagesData.messagesTitle = "load.promptTitle";
                $scope.messagesData.messagesBody = "load.promptBody";
                $("#messagesModal").modal('show');
            }

        };
        //判断是否有权限
        checkAuthority(3, pocClose);//1：添，2：删，3：改，4：查 执行函数

    };
});