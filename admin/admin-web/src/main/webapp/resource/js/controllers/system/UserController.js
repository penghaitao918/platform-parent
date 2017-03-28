adminApp.config(function ($routeProvider) {
    $routeProvider.when('/user/view', {
        templateUrl: ctx + '/system/user/userInit',
        controller: 'userListController'
    }).when("/user/view/:check", {
        templateUrl: ctx + '/system/user/userInit',
        controller: 'userListController'
    });
});

adminApp.controller('userListController', function ($scope, userFnService, $location, roleFnService, $routeParams, $rootScope) {
    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "";
    $rootScope.menuBarData.menuBarTitle = "UserManage";
    $scope.chk = false;//选择状态
    var id = "";//用户id字符串
    var index = 0;//修改用户的下标
    $scope.userAddData = {};//设置addForm控件model数据集合
    $scope.userUpdateData = {};//设置updateForm控件model数据集合
    $scope.userEditData = {};
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.messagesConfirm = {};//设置确认框model数据集合
    $scope.judegePassword = "";
    $scope.selectedId = [];//渠道id集合
    $scope.selectedCode = [];//渠道code集合
    $scope.channelCode = [];//渠道code集合（显示项）
    $scope.userState = {};//修改状态条件对象
    $scope.userSearchText = "load.userSearchText";//搜索提示国际化

    if ($routeParams.check == 1) {
        $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
        $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToSuccess";
    }
    $scope.passwordId = "";
    $scope.userPasswordData = {};
    $scope.userPasswordSpecialData = {};
    $scope.passwordJudge = -1;

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
     用户添加（弹框）
     */
    $scope.addButton = function () {
        var add = function () {
            $scope.userJudge = "add";
            $scope.userEditData = {};
            $("#userModalForEdit").modal('show');
        };
        //判断是否有权限
        checkAuthority(1, add);//1：添，2：删，3：改，4：查 执行函数

    };

    /*
     调用userService的查询方法
     */
    function userFindByNamePublic(searchText, pageIndex, pageSize) {
        userFnService.userFindByName(searchText, pageIndex, pageSize).success(function (data) {
            $scope.users = data.rows;
            $scope.pageCount = data.page.pageCount;
            $scope.$apply();
        });
    }

    /*
     监听addForm表单
     */
    $scope.userEditForm = function () {

        if ($scope.userJudge == "add") {
            /*
             调用添加方法
             */
            userFnService.userAdd($scope.userEditData).success(function (data) {
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
                $("textarea").val("");
                /*
                 调用userService的查询方法
                 */
                userFindByNamePublic($(".searchText").val(), 1, 10);

                $('#userModalForEdit').modal('hide');
            }).error(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByadd";
                $scope.messagesData.messagesBody = "load.messagesBodyByaddToFailure";
                $("#messagesModal").modal('show');
            });
        } else if ($scope.userJudge == "update") {
            /*
             调用修改方法
             */
            /*$scope.userUpdateData.createTime=new Date();*/
            $scope.userEditData.createTime = new Date($scope.userEditData.createTime);
            $scope.userEditData.loginTime = new Date($scope.userEditData.loginTime);
            userFnService.userUpdate($scope.userEditData).success(function (data) {
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
                 调用userService的查询方法
                 */
                userFindByNamePublic($(".searchText").val(), $scope.currentPage, 10);

                $("#userModalForEdit").modal('hide');
            }).error(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
                $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToFailure";
                $("#messagesModal").modal('show');
            });
        }


    };

    $scope.accreditClicked = function () {
        var data = $(this);
        var accreditClicked = function () {
            $location.path("/accredit/view/" + data[0].user.id + "/" + 3);//3：用户权限
        };
        //判断是否有权限
        checkAuthority(3, accreditClicked);//1：添，2：删，3：改，4：查 执行函数

    };


    /*
     用户修改（弹窗）
     */
    $scope.updateClicked = function () {
        var data = $(this);
        var accreditClicked = function () {
            $scope.userJudge = "update";
            $scope.userEditData = {};
            /*
             调用roleService的查询方法,根据id，
             */
            userFnService.userGetById(data[0].user.id).success(function (response) {
                $("#userModalForEdit").modal('show');
                $scope.userEditData = response;
                $scope.$apply();
            }).error(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByUnusual";
                $scope.messagesData.messagesBody = "load.messagesBodyByUnusual";
                $("#messagesModal").modal('show');
            });
        };
        //判断是否有权限
        checkAuthority(3, accreditClicked);//1：添，2：删，3：改，4：查 执行函数

    };

    /*
     开启、关闭状态工具方法 ids:id集合 state 1代表开启，0代表关闭
     */
    function stateTool(ids, state) {
        $scope.userState.ids = ids;
        $scope.userState.state = state;
        userFnService.userStateUpdate($scope.userState).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
            $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToSuccess";
            $("#messagesModal").modal('show');
            /*
             调用userService的查询方法
             */
            userFindByNamePublic($(".searchText").val(), $scope.currentPage, 10);
            id = "";
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
            $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToFailure";
            $("#messagesModal").modal('show');
            /*
             调用userService的查询方法
             */
            userFindByNamePublic($(".searchText").val(), $scope.currentPage, 10);
        });
    }


    /*
     更改用户的选择状态，并保存相应的id
     */
    $scope.check = function (val, chk) {
        var data = $(this);
        console.log(data[0].user.id);
        data[0].user.id;
        if (chk == true) {
            id += data[0].user.id + ",";
        } else {
            id = id.replace(data[0].user.id + ",", "");
        }
        console.log(id);
    };

    /*
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

    /*
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

    /*
     开启
     */
    $scope.open = function () {
        var data = $(this);
        var open = function () {
            index = data[0].user.id;
            stateTool(index, 1);//1代表开启
        };
        //判断是否有权限
        checkAuthority(3, open);//1：添，2：删，3：改，4：查 执行函数
    };

    /*
     开启
     */
    $scope.close = function () {
        var data = $(this);
        var close = function () {
            index = data[0].user.id;
            stateTool(index, 0);//0代表关闭
        };
        //判断是否有权限
        checkAuthority(3, close);//1：添，2：删，3：改，4：查 执行函数

    };

    /*
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

    /*
     删除角色确认
     */
    $scope.confirm = function () {
        /*
         调用删除方法
         */
        userFnService.userDelete(id).success(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToSuccess";
            $("#messagesModal").modal('show');
            /*
             调用userService的查询方法
             */
            userFindByNamePublic($(".searchText").val(), $scope.currentPage, 10);
            id = "";
        }).error(function () {
            $scope.messagesData.messagesTitle = "load.messagesTitleByDelete";
            $scope.messagesData.messagesBody = "load.messagesBodyByDeleteToFailure";
            $("#messagesModal").modal('show');
        });
        ;
    };

    /*
     刷新user角色查询页面，调用userService的查询方法
     */
    userFindByNamePublic($(".searchText").val(), 1, 10);

    /*
     根据loginName名称模糊查询
     */
    $scope.searchButtonClicked = function () {
        /*
         调用userService的查询方法
         */
        userFindByNamePublic($(".searchText").val(), 1, 10);


    };

    /*
     分页
     */
    $scope.onPageChange = function () {
        // ajax request to load data
        console.log($scope.currentPage);
        /*
         调用userService的查询方法，并传最新的currentPage
         */
        userFindByNamePublic($(".searchText").val(), $scope.currentPage, 10);
    };

    /**
     * 修改密码（弹框）
     * @param id
     */
    $scope.changePasswordCapacity = function (id, loginName) {

        var changePasswordCapacity = function () {
            $scope.passwordId = id;
            $scope.loginName = loginName;
            $scope.userPasswordData = {};
            $scope.passwordJudge = -1;
            $scope.passwordSpecialJudge = -1;
            if (principal == "admin") {
                $("#universalForms").hide();
                $("#specialForms").show();
            } else {
                $("#universalForms").show();
                $("#specialForms").hide();
            }
            $("#userModalForPasswordUpdate").modal('show');
        };
        //判断是否有权限
        checkAuthority(3, changePasswordCapacity);//1：添，2：删，3：改，4：查 执行函数

    };

    /**
     * 监听修改密码表单
     */
    $scope.userPasswordForm = function () {
        if ($scope.userPasswordData.newPassword == $scope.userPasswordData.confirmPassword) {
            $scope.userPasswordValidate = {};
            $scope.userPasswordValidate.userId = $scope.passwordId;
            $scope.userPasswordValidate.newPassword = $scope.userPasswordData.oldPassword;

            $.ajax({
                type: "post",
                url: ctx + '/system/user/pwdValidate',
                data: $scope.userPasswordValidate,
                success: function (data) {
                    if (data) {
                        $scope.userPasswordValidate.newPassword = $scope.userPasswordData.confirmPassword;
                        userFnService.updUserPwd($scope.userPasswordValidate).success(function () {
                            $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
                            $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToSuccess";
                            $("#messagesModal").modal('show');
                            /*
                             调用userService的查询方法
                             */
                            userFindByNamePublic($(".searchText").val(), $scope.currentPage, 10);

                            $("#userModalForPasswordUpdate").modal('hide');
                        }).error(function () {
                            $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
                            $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToFailure";
                            $("#messagesModal").modal('show');
                        });
                        $scope.passwordJudge = 1;
                    } else {
                        $scope.userPasswordData = {};
                        $scope.passwordJudge = 2;//原密码
                    }
                },
                async: false
            });
        } else {
            $scope.passwordJudge = 0;
            return;
        }

    }

    /**
     * 监听修改密码表单(admin)
     */
    $scope.userPasswordSpecialForm = function () {
        if ($scope.userPasswordSpecialData.newPassword == $scope.userPasswordSpecialData.confirmPassword) {
            $scope.userPasswordValidate = {};
            $scope.userPasswordValidate.userId = $scope.passwordId;
            $scope.userPasswordValidate.newPassword = $scope.userPasswordSpecialData.confirmPassword;
            userFnService.updUserPwd($scope.userPasswordValidate).success(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
                $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToSuccess";
                $("#messagesModal").modal('show');
                /*
                 调用userService的查询方法
                 */
                userFindByNamePublic($(".searchText").val(), $scope.currentPage, 10);

                $("#userModalForPasswordUpdate").modal('hide');
            }).error(function () {
                $scope.messagesData.messagesTitle = "load.messagesTitleByUpdate";
                $scope.messagesData.messagesBody = "load.messagesBodyByUpdateToFailure";
                $("#messagesModal").modal('show');
            });

        } else {
            $scope.passwordSpecialJudge = 0;
            return;
        }

    }


});

