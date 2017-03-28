/**
 * 物品申请controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/article/view', {
        templateUrl: ctx + '/operation/article/articleInit',
        controller: 'applicationController'
    }).when("/article/view/:check", {
        templateUrl: ctx + '/operation/article/articleInit',
        controller: 'applicationController'
    });
});


/**
 物品申请controller
 全局：$scope
 */
adminApp.controller('applicationController', function ($scope, articleService, $location, $routeParams, $rootScope) {

    $scope.articleSearch = {};//初始化查询条件对象
    $scope.articleSearch.gameId = $scope.gameTransfer;//初始化查询gameId
    $scope.articleSearch.typeCode = 1;//1代表订单
    $scope.articleId = "";//区服id字符串
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.messagesConfirm = {};//设置确认框model数据集合
    /*    $scope.articleSearch.from="";
     $scope.articleSearch.to="";*/
    $scope.placeholderSearch = "load.ApprovalFromSearch";//搜索提示
    $scope.approvalStatusCode = "";//审批状态（删除时判断用）
    $scope.approvalStatusCodeByAppfor = "";//审批状态（申请时判断用）

    $scope.applyForId = -1;
    $scope.fileImportData = {};

    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "ArticleApplication";
    /*
     1：修改成功，0：修改失败，2,：添加成功，3：添加失败
     */
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
    }

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
     * 添加（跳转）
     */
    $scope.addButton = function () {
        var add = function () {
            $location.path("/articleEdit/view");
        };
        //判断是否有权限
        checkAuthority(1, add);//1：添，2：删，3：改，4：查 执行函数

    }

    /**
     *刷新数据、list
     */
    function applicationSelect(approvalSearch, pageIndex, pageSize) {
        /*
         调用查询方法，并传最新的currentPage
         */
        articleService.articleFindByName(approvalSearch, pageIndex, pageSize).success(function (response) {
            $scope.applicationDatas = response.rows;
            $scope.pageCount = response.page.pageCount;
            $scope.$apply();
        });

    }

    /**
     调用查询方法，并传最新的currentPage
     */
    applicationSelect($scope.articleSearch, 1, 10);

    /**
     * 搜索单击事件
     */
    $scope.searchButtonClicked = function () {
        /*
         调用查询方法，并传最新的currentPage
         */
        applicationSelect($scope.articleSearch, 1, 10);
    }

    /**
     * 状态改变事件
     */
    $scope.statusChange = function () {
        /*
         调用查询方法，并传最新的currentPage
         */
        applicationSelect($scope.articleSearch, 1, 10);
    }

    /**
     *分页
     */
    $scope.onPageChange = function () {
        /*
         调用查询方法，并传最新的currentPage
         */
        applicationSelect($scope.articleSearch, $scope.currentPage, 10);
    };

    /**
     *更改区服的选择状态，并保存相应的id
     */
    $scope.check = function (val, chk) {
        var data = $(this);
        if (chk == true) {
            $scope.articleId += data[0].application.mailOrder.id + ",";
            if (data[0].application.approvalStatus == 2 || data[0].application.approvalStatus == 3) {
                $scope.approvalStatusCode += data[0].application.approvalStatus + ",";
            }

            if (data[0].application.approvalStatus != 1) {
                $scope.approvalStatusCodeByAppfor += data[0].application.approvalStatus + ",";
            }
        } else {
            $scope.articleId = $scope.articleId.replace(data[0].application.mailOrder.id + ",", "");
            if (data[0].application.approvalStatus == 2 || data[0].application.approvalStatus == 3) {
                $scope.approvalStatusCode = $scope.approvalStatusCode.replace(data[0].application.approvalStatus + ",", "");
            }

            if (data[0].application.approvalStatus != 1) {
                $scope.approvalStatusCodeByAppfor = $scope.approvalStatusCode.replace(data[0].application.approvalStatus + ",", "");
            }
        }

    };

    /**
     *删除角色confirm
     */
    $scope.delete = function () {
        console.log($scope.articleId);
        var deleteL = function () {
            if ($scope.articleId != "") {
                if ($scope.approvalStatusCode.length == 0) {
                    $scope.messagesConfirm.title = "load.messagesConfirmTitleByDelete";
                    $scope.messagesConfirm.body = "load.messagesConfirmBodyByDelete";
                    $("#messagesConfirm").modal('show');
                } else {
                    $scope.messagesData.messagesTitle = "load.promptTitle";
                    $scope.messagesData.messagesBody = "load.ApprovaeMessageBodyByDelte";
                    $("#messagesModal").modal('show');
                }

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
     *删除角色确认
     */
    $scope.confirm = function () {
        /*
         调用删除方法
         */
        articleService.articleDelete($scope.articleId).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToSuccess";
            $("#messagesModal").modal('show');
            /*
             调用查询方法，并传最新的currentPage
             */
            applicationSelect($scope.articleSearch, $scope.currentPage, 10);

            $scope.articleId = "";
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToFailure";
            $("#messagesModal").modal('show');

            /*
             调用查询方法，并传最新的currentPage
             */
            applicationSelect($scope.articleSearch, $scope.currentPage, 10);

            $scope.articleId = "";
        });

    };

    /**
     *开启、关闭状态工具方法 ids:id集合
     */
    function stateTool(ids) {
        $scope.articleId = ids;
        articleService.articleApplyFor($scope.articleId).success(function () {
            $scope.messagesData.messagesTitle = "load.promptTitle";
            $scope.messagesData.messagesBody = "load.messagesBodyApplyForToSuccess";
            $("#messagesModal").modal('show');
            /*
             调用查询方法，并传最新的currentPage
             */
            applicationSelect($scope.articleSearch, $scope.currentPage, 10);

            $scope.articleId = "";
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.promptTitle";
            $scope.messagesData.messagesBody = "load.messagesBodyApplyForToFailure";
            $("#messagesModal").modal('show');
            /*
             调用查询方法，并传最新的currentPage
             */
            applicationSelect($scope.articleSearch, $scope.currentPage, 10);
            $scope.articleId = "";
        });
    }

    /**
     * 一键申请
     */
    $scope.pocApplyFor = function () {
        var pocApplyFor = function () {
            if ($scope.articleId != "") {
                if ($scope.approvalStatusCode.length == 0) {
                    $scope.applyForId = $scope.articleId;
                    $scope.messagesConfirm.title = "load.messagesConfirmTitleByApplyFor";
                    $scope.messagesConfirm.body = "load.messagesConfirmBodyByApplyFor";
                    $("#messagesConfirmApplyFor").modal('show');
                } else {
                    $scope.messagesData.messagesTitle = "load.promptTitle";
                    $scope.messagesData.messagesBody = "load.ApprovalMessageBodyByAppfor";
                    $("#messagesModal").modal('show');
                }

            } else {
                $scope.messagesData.messagesTitle = "load.promptTitle";
                $scope.messagesData.messagesBody = "load.promptBody";
                $("#messagesModal").modal('show');
            }
        };
        //判断是否有权限
        checkAuthority(3, pocApplyFor);//1：添，2：删，3：改，4：查 执行函数


    }


    /**
     * 申请
     * @param id
     */
    $scope.applyFor = function (id) {
        var applyFor = function () {
            $scope.applyForId = id;
            $scope.messagesConfirm.title = "load.messagesConfirmTitleByApplyFor";
            $scope.messagesConfirm.body = "load.messagesConfirmBodyByApplyFor";
            $("#messagesConfirmApplyFor").modal('show');
        };
        //判断是否有权限
        checkAuthority(3, applyFor);//1：添，2：删，3：改，4：查 执行函数

    }

    /**
     *申请确认
     */
    $scope.confirmApplyFor = function () {
        stateTool($scope.applyForId);
    };

    /**
     *修改（调转）
     */
    $scope.updateClicked = function (id) {
        var update = function () {
            $location.path("/articleEdit/view/" + id + "/update");
        };
        //判断是否有权限
        checkAuthority(3, update);//1：添，2：删，3：改，4：查 执行函数

    };

    /**
     *查看（调转）
     */
    $scope.updateClickedSee = function (id) {
        $location.path("/articleEdit/view/" + id + "/checkSee");
    };

    /**
     *审批查看
     */
    $scope.approvalForEye = function (id) {
        articleService.approveGetById(id).success(function (data) {
            $scope.approvalDataEye = data;
            $scope.$apply();
        });
        $("#approvalEyeModal").modal("show");
    }

    /**
     *导入个人申请（弹框）
     */
    $scope.fileImport = function () {
        var add = function () {
            $("#fileImportModal").modal("show");
        };
        //判断是否有权限
        checkAuthority(1, add);//1：添，2：删，3：改，4：查 执行函数

    }
    /**
     *刷新数据
     */
    $scope.fileImportFormsClick = function () {
        /*
         调用查询方法，并传最新的currentPage
         */
        articleService.articleFindByName($scope.articleSearch, 1, 10).success(function (response) {
            $scope.currentPage = 1;
            $scope.applicationDatas = response.rows;
            $scope.pageCount = response.page.pageCount;
            $("#exampleInputFile").val("");
            $scope.$apply();
        });
        $(".applyReason").val("");
        /* */
        $("#fileImportModal").modal("hide");

    }


});