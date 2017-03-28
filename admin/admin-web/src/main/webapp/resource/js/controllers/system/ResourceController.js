/**
 * 资源管理controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/res/view', {
        templateUrl: ctx + '/system/res/resInit',
        controller: 'resController'
    });
});

/**
 资源管理controller
 全局：$scope
 */
adminApp.controller('resController', function ($scope, resourceFnService, $rootScope) {
    $scope.chk = false;//选择状态
    var id = "";//角色id字符串
    var index = 0;//修改角色的下标
    $scope.resAddSiteFormData = {};//设置addSiteForm控件model数据集合
    $scope.resAddGameFormData = {};//设置addGameForm控件model数据集合
    $scope.resUpdateData = {};//设置updateForm控件model数据集合
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.messagesConfirm = {};//设置确认框model数据集合
    $scope.resEditFormData = {};//数据集合
    $scope.resJudge = "";//添加or修改
    $scope.resState = {};
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "ResourceManage";
    $scope.resourceSearchText = "load.resourceSearchText";//搜索提示国际化
    /**
     * 刷新查询页面，调用查询方法
     * @param searchText
     * @param pageIndex
     * @param pageSize
     */
    function resourceFindByNamePublic(searchText, pageIndex, pageSize) {
        resourceFnService.resourceFindByName(searchText, pageIndex, pageSize).success(function (response) {
            console.log(response);
            $scope.res = response.rows;
            $scope.pageCount = response.page.pageCount;
            $scope.$apply();
        });
    };

    /**
     * 更新缓存
     */
   $scope.updateResCache=function(){
        resourceFnService.updateResCache().success(function(){
            $scope.messagesData.messagesTitle = "load.UpAreaMessagesTitle";
            $scope.messagesData.messagesBody = "load.UpAreaMessagesToSuccess";
            $("#messagesModal").modal('show');
            $scope.$apply();
        }).error(function(){
            $scope.messagesData.messagesTitle = "load.UpAreaMessagesTitle";
            $scope.messagesData.messagesBody = "load.UpAreaMessagesToFailure";
            $("#messagesModal").modal('show');
            $scope.$apply();
        });
   };

    /**
     刷新查询页面，调用查询方法
     */

    resourceFindByNamePublic($(".searchText").val(), 1, 10);

    /**
     分页
     */
    $scope.onPageChange = function () {
        /*
         调用查询方法，并传最新的currentPage
         */
        resourceFindByNamePublic($(".searchText").val(), $scope.currentPage, 10);
    };

    /**
     根据名称模糊查询
     */
    $scope.searchButtonClicked = function () {
        /*
         调用查询方法
         */
        resourceFindByNamePublic($(".searchText").val(), 1, 10);
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
     Game添加（弹框）
     */
    $scope.addGameButton = function () {
        var add = function () {
            $scope.resJudge = "gameAdd";
            $scope.resEditFormData = {};
            $("#resModalForEdit").modal('show');
        };
        //判断是否有权限
        checkAuthority(1, add);//1：添，2：删，3：改，4：查 执行函数

    };


    /**
     * 监听编辑From
     */
    $scope.resEditForm = function () {
        if ($scope.resJudge == "gameAdd") {
            /*
             调用添加方法
             */
            resourceFnService.resAddGanme($scope.resEditFormData).success(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
                $scope.messagesData.messagesBody = "load.messagesBodyByaddToSuccess";
                $("#messagesModal").modal('show');
                $("input").val("");
                $("textarea").val("");
                /*
                 调用查询方法，并传最新的currentPage
                 */
                resourceFindByNamePublic($(".searchText").val(), 1, 10);

                $('#resModalForEdit').modal('hide');
            }).error(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
                $scope.messagesData.messagesBody = "load.messagesBodyByaddToFailure";
                $("#messagesModal").modal('show');
            });
        } else if ($scope.resJudge == "siteAdd") {
            /*
             调用添加方法
             */
            resourceFnService.resAddSite($scope.resEditFormData).success(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
                $scope.messagesData.messagesBody = "load.messagesBodyByaddToSuccess";
                $("#messagesModal").modal('show');
                $("input").val("");
                $("textarea").val("");
                /*
                 调用查询方法，并传最新的currentPage
                 */
                resourceFindByNamePublic($(".searchText").val(), 1, 10);

                $('#resModalForEdit').modal('hide');
            }).error(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
                $scope.messagesData.messagesBody = "load.messagesBodyByaddToFailure";
                $("#messagesModal").modal('show');
            });
        } else if ($scope.resJudge == "update") {
            /*
             调用修改方法
             */
            $scope.resEditFormData.createTime = new Date($scope.resEditFormData.createTime);
            resourceFnService.resourceUpdate($scope.resEditFormData).success(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
                $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToSuccess";
                $("#messagesModal").modal('show');
                /*
                 操作成功之后，调用查询方法
                 */
                resourceFindByNamePublic($(".searchText").val(), $scope.currentPage, 10);
                $("#resModalForEdit").modal('hide');
            }).error(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
                $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToFailure";
                $("#messagesModal").modal('show');
            });
        }
    }


    /**
     Site添加（弹框）
     */
    $scope.addSiteButton = function () {
        var add = function () {
            $scope.resJudge = "siteAdd";
            $scope.resEditFormData = {};
            $("#resModalForEdit").modal('show');
        };
        //判断是否有权限
        checkAuthority(1, add);//1：添，2：删，3：改，4：查 执行函数
    };


    /**
     更改资源的选择状态，并保存相应的id
     */
    $scope.check = function (val, chk) {
        var data = $(this);
        data[0].r.id;

        if (chk == true) {
            id += data[0].r.id + ",";
        } else {
            id = id.replace(data[0].r.id + ",", "");
        }

    };

    /**
     删除资源confirm
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
     删除资源
     */
    $scope.confirm = function () {
        /*
         调用删除方法
         */
        resourceFnService.resourceDelete(id).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToSuccess";
            $("#messagesModal").modal('show');
            /*
             操作成功之后，调用查询方法
             */
            resourceFindByNamePublic($(".searchText").val(), $scope.currentPage, 10);
            id = "";
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToFailure";
            $("#messagesModal").modal('show');
        });
    };


    /**
     资源修改（弹窗）
     */
    $scope.updateClicked = function () {
        var data = $(this);
        var update = function () {
            $scope.resEditFormData = {};
            $scope.resJudge = "update";
            /*
             调用查询方法,根据id，
             */
            resourceFnService.resourceGetById(data[0].r.id).success(function (response) {
                $("#resModalForEdit").modal('show');
                $scope.resEditFormData = response;
                $scope.$apply();
                $(".availableUpdate").val(response.available);
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
     开启、关闭状态工具方法 ids:id集合 state 1代表开启，0代表关闭
     */
    function stateTool(ids, state) {
        $scope.resState.ids = ids;
        $scope.resState.state = state;
        resourceFnService.resStateUpdate($scope.resState).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
            $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToSuccess";
            $("#messagesModal").modal('show');
            /*
             刷新查询页面，调用查询方法
             */
            resourceFindByNamePublic($(".searchText").val(), $scope.currentPage, 10);
            id = "";
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
            $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToFailure";
            $("#messagesModal").modal('show');
            /*
             刷新查询页面，调用查询方法
             */
            resourceFindByNamePublic($(".searchText").val(), $scope.currentPage, 10);
            id = "";
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
    $scope.open = function (id) {
        var open = function () {
            stateTool(id, 1);//1代表开启
        };
        //判断是否有权限
        checkAuthority(3, open);//1：添，2：删，3：改，4：查 执行函数

    };

    /**
     关闭
     */
    $scope.close = function (id) {
        var close = function () {
            stateTool(id, 0);//0代表关闭
        };
        //判断是否有权限
        checkAuthority(3, close);//1：添，2：删，3：改，4：查 执行函数
    };


});