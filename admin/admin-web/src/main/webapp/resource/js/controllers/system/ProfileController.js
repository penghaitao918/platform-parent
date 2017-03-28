adminApp.config(function ($routeProvider) {
    $routeProvider.when('/profile/view', {
        templateUrl: ctx + '/system/user/profileInit',
        controller: 'profileController'
    });
});

/**
 * 用户信息
 */
adminApp.controller('profileController', function ($scope, $location, profileService, userFnService,$rootScope) {
    $scope.profileData = {};
    $scope.passwordId = "";
    $scope.userPasswordData = {};
    $scope.passwordJudge = -1;
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.messagesConfirm = {};//设置确认框model数据集合
    profileService.getUserByName().success(function (data) {
        $scope.profileData = data;
        console.log(data);
        $scope.$apply();
    });

    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "ProfileEdit";
    $rootScope.menuBarData.menuBarTitle = "ProfileEdit";

    /**
     * 修改密码（弹框）
     * @param id
     */
    $scope.changePasswordCapacity = function (id, loginName) {
        $scope.passwordId = id;
        $scope.loginName = loginName;
        $scope.userPasswordData = {};
        $scope.passwordJudge = -1;
        $("#userModalForPasswordUpdate").modal('show');
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
                            $scope.messagesData.messagesBody = "load.MesssageBodyPasswordUpdate";
                            $scope.$apply();
                            $("#messagesModal").modal('show');
                            $("#userModalForPasswordUpdate").modal('hide');
                            userFnService.logout();
                            $('#userModalForPasswordUpdate').on('hidden.bs.modal', function () {
                                setTimeout("location.reload()", 2000);
                            })

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
});

