/**
 * 权限管理controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/permission/view', {
        templateUrl: ctx + '/system/permission/permissionInit',
        controller: 'permissionController'
    });
});

/*
 权限管理controller
 全局：$scope
 权限Service:permissionFnService
 */
adminApp.controller('permissionController', function($scope,permissionFnService,$translate) {
    $scope.chk = false;//选择状态
    var id ="";//角色id字符串
    var index=0;//修改角色的下标
    $scope.permissionAddData={};//设置addForm控件model数据集合
    $scope.permissionUpdateData={};//设置updateForm控件model数据集合
    $scope.messagesData={};//设置提示框model数据集合
    $scope.messagesConfirm={};//设置确认框model数据集合
    /*
     刷新权限查询页面，调用permissionFnService的查询方法
     */
    permissionFnService.permissionFindByName($(".searchText").val(),1,10).success(function (response) {
        console.log(response);
        $scope.permissions = response.rows;
        $scope.pageCount =response.page.pageCount;
        $scope.$apply();
    });

    /*
     分页
     */
    $scope.onPageChange = function() {
        // ajax request to load data
        console.log($scope.currentPage);
        /*
         调用permissionService的查询方法，并传最新的currentPage
         */
        permissionFnService.permissionFindByName($(".searchText").val(),$scope.currentPage,10).success(function (response) {
            console.log(response);
            $scope.permissions = response.rows;
            $scope.pageCount =response.page.pageCount;
            $scope.$apply();

        });
    };

    /*
     根据permission名称模糊查询
     */
    $scope.searchButtonClicked=function(){
        /*
         调用permissionsService的查询方法
         */
        permissionFnService.permissionFindByName($(".searchText").val(),$scope.currentPage,10).success(function(data){
            $scope.permissions=data.rows;
            $scope.pageCount =data.page.pageCount;
            $scope.$apply();
        });
    };

    /*
     权限添加（弹框）
     */
    $scope.addButton=function(){
        $("#permissionModalForAdd").modal('show');;
    };



    /*
     监听addForm表单
     */
    $scope.permissionAddForm=function(){
        /*
         调用添加方法
         */
        permissionFnService.permissionAdd($scope.permissionAddData).success(function(){
            $scope.messagesData.messagesTitle="添加结果";
            $scope.messagesData.messagesBody="此次添加成功！";
            $("#messagesModal").modal('show');
            $("input").val("");
            $("textarea").val("");
            /*
             操作成功之后，调用permissionService的查询方法
             */
            permissionFnService.permissionFindByName($(".searchText").val(),$scope.currentPage,10).success(function (response) {
                console.log(response);
                $scope.permissions = response.rows;
                $scope.pageCount =response.page.pageCount;
                $scope.$apply();

            });
            $('#permissionModalForAdd').modal('hide');
        }).error(function(){
            $scope.messagesData.messagesTitle="添加结果";
            $scope.messagesData.messagesBody="添加失败！";
            $("#messagesModal").modal('show');
        });
    };



    /*
     权限修改（弹窗）
     */
    $scope.updateClicked=function(){
        var data=$(this);
        index=data[0].permission.id;
        /*
         调用permissionService的查询方法,根据id，
         */
        permissionFnService.permissionGetById(data[0].permission.id).success(function(response){
            $("#permissionModalForUpdate").modal('show');
            $scope.permissionUpdateData=response;
            $scope.$apply();
        }).error(function(){
            $scope.messagesData.messagesTitle="异常";
            $scope.messagesData.messagesBody="未知参数！";
            $("#messagesModal").modal('show');
        });

    };


    /*
     监听updateForm表单
     */
    $scope.permissionUpdateForm=function(){
        /*
         调用修改方法
         */
        $scope.permissionUpdateData.createTime=new Date($scope.permissionUpdateData.createTime);
        permissionFnService.permissionUpdate($scope.permissionUpdateData).success(function(){
            $scope.messagesData.messagesTitle="修改结果";
            $scope.messagesData.messagesBody="此次修改成功！";
            $("#messagesModal").modal('show');
            /*
             操作成功之后，调用permissionService的查询方法
             */
            permissionFnService.permissionFindByName($(".searchText").val(),$scope.currentPage,10).success(function (response) {
                console.log(response);
                $scope.permissions = response.rows;
                $scope.pageCount =response.page.pageCount;
                $scope.$apply();

            });
            $("#permissionModalForUpdate").modal('hide');
        }).error(function(){
            $scope.messagesData.messagesTitle="修改结果";
            $scope.messagesData.messagesBody="此次修改失败！";
            $("#messagesModal").modal('show');
        });
    };

    /*
     更改权限的选择状态，并保存相应的id
     */
    $scope.check= function(val,chk){
        var data=$(this);
        data[0].permission.id;

        if(chk == true){
            id += data[0].permission.id+",";
        }else{
            id = id.replace(data[0].permission.id+",","");
        }

    };

    /*
     删除角色confirm
     */
    $scope.delete= function(){
        $scope.messagesConfirm.title="删除确认";
        $scope.messagesConfirm.body="您确定要删除吗？";
        $("#messagesConfirm").modal('show');
    };

    /*
     删除角色确认
     */
    $scope.confirm= function(){
        /*
         调用删除方法
         */
        permissionFnService.permissionDelete(id).success(function(){
            $scope.messagesData.messagesTitle="删除结果";
            $scope.messagesData.messagesBody="此次删除成功！";
            $("#messagesModal").modal('show');
            /*
             操作成功之后，调用permissionService的查询方法
             */
            permissionFnService.permissionFindByName($(".searchText").val(),$scope.currentPage,10).success(function (response) {
                console.log(response);
                $scope.permissions = response.rows;
                $scope.pageCount =response.page.pageCount;
                $scope.$apply();

            });
            id="";
        }).error(function(){
            $scope.messagesData.messagesTitle="删除结果";
            $scope.messagesData.messagesBody="删除失败！";
            $("#messagesModal").modal('show');
        });;
    };


});