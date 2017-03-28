/**
 * 商品配方添加controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/billingGoodsAdd/view', {
        templateUrl: ctx + '/platform/billingGoods/billingGoodsAddInit',
        controller: 'billingGoodsAddController'
    });
});


/*
 商品配方controller
 全局：$scope
 Service:billingGoodsFnService
 */
adminApp.controller('billingGoodsAddController', function($scope,billingGoodsFnService,gameAreaFnService,$location) {


    $scope.selectedId = [];//渠道id集合
    $scope.selectedCode = [];//渠道code集合
    $scope.messagesData={};//设置提示框model数据集合
    $scope.channelCode=[];//渠道code集合（显示项）
    $scope.areaLists =[];//区服集合
    $scope.areaId = [];//区服id集合
    $scope.areaCode=[];//区服name集合
    $scope.gameToySiteList=$scope.siteTransfer;//初始化gameToySiteList，与全局site同步
    $scope.siteSelected=$scope.siteTransfer;//更改siteId选择项
    $scope.gameToyGameList=$scope.gameTransfer;//gameToyGameList，与全局game同步
    $scope.gameSelected=$scope.gameTransfer;//更改gameId选择项

    $scope.awardPackage =[];//奖励包集合
    $scope.firstAwardPackage=[];//首充奖励包集合
    $scope.awardCondition={};//奖励包查询条件对象
    $scope.awardCondition.awardName="";//初始化条件
    $scope.awardCondition.awardType=-1;//初始化条件
    $scope.awardId="";//奖励包id字符串
    $scope.awardNames = [];//奖励包显示集合
    $scope.firstAwardId="";//首充奖励包id字符串
    $scope.firstAwardNames=[];//首充奖励包显示集合
    $scope.firstBuyNames=[];//首充显示集合

    $scope.goodsData={}//商品集合;
    $scope.goodsData.gameId=$scope.gameTransfer;//初始化gameId，与全局game同步
    $scope.goodsData.siteId=$scope.siteTransfer;//初始化siteId，与全局site同步


    $scope.billingGoodsSearch={};//商品查询条件
    $scope.billingGoodsSearch.gameId=$scope.gameTransfer;
    $scope.billingGoodsSearch.siteId=$scope.siteTransfer;
    /*
     根据全局game，site查询区服
     */
    if($scope.billingGoodsSearch.gameId==undefined){
        $scope.billingGoodsSearch.gameId=-1;
    }
    gameAreaFnService.gameAreaFindByGameId($scope.billingGoodsSearch).success(function (response) {
        $scope.areaLists = response;
        $scope.$apply();
    });




    /*
    奖励包列表
     */
    billingGoodsFnService.awardPackage($scope.awardCondition,1,5).success(function(data){
        $scope.awardPackage=data.rows;//绑定奖励包
        $scope.firstAwardPackage=data.rows;//绑定首充奖励包
        $scope.pageAwardCount =data.page.pageCount;//绑定奖励包分页
        $scope.pageFirstAwardCount =data.page.pageCount;//绑定奖励包分页
        $scope.$apply();
    });

    /*
    首充奖励（弹框）
     */
    $scope.firstBuyClick=function(){
        $("#firstBuyModal").modal('show');
    }

    /*
    监听首充form
     */
    $scope.firstBuyAdd=function(){
        var name=$scope.firstGoodsCode+$scope.firstBuyOperation+$scope.operateCount;
        var names=[];
        names.push(name);
        $scope.firstBuyNames=names;
        $scope.messagesData.messagesTitle="修改结果";
        $scope.messagesData.messagesBody="首充奖励礼包修改成功！";
        $("#messagesModal").modal('show');
        $("#firstBuyModal").modal('hide');
    }

    /*
     监听商品form
     */
    $scope.billingGoodsAddForm=function(){
        $scope.goodsData.goodsMarkTime=new Date($scope.goodsData.goodsMarkTime);
        $scope.goodsData.valid=$scope.validStatus;
        var areaids="";
        for(var i=0;i<$scope.areaId.length;i++){
            areaids+=$scope.areaId[i]+",";
        }
        $scope.goodsData.areaIds=areaids;
        /*
         调用添加方法
         */
        billingGoodsFnService.billingGoodsAdd($scope.goodsData).success(function(){
            $scope.messagesData.messagesTitle="添加结果";
            $scope.messagesData.messagesBody="此次添加成功！";
            $scope.$apply();
            $("#messagesModal").modal('show');

        }).error(function(){
            $scope.messagesData.messagesTitle="添加结果";
            $scope.messagesData.messagesBody="添加失败！";
            $scope.$apply();
            $("#messagesModal").modal('show');
        });
        $location.path("/billingGoods/view");
    }

    /*
     更改奖励包的选择状态，并保存相应的id
     */
    $scope.check= function(val,chk){
        var data=$(this);
        if(chk == true){
            $scope.awardId += data[0].award.awardId+",";
            $scope.awardNames.push(data[0].award.awardName);
        }else{
            var idx = $scope.awardNames.indexOf(data[0].award.awardName);
            $scope.awardNames.splice(idx,1);
            $scope.awardId = $scope.awardId.replace(data[0].award.awardId+",","");
        }

    };

    /*
     更改首充奖励包的选择状态，并保存相应的id
     */
    $scope.firstAwardCheck= function(val,chk){
        var data=$(this);
        if(chk == true){
            $scope.firstAwardId += data[0].award.awardId+",";
            $scope.firstAwardNames.push(data[0].award.awardName);
        }else{
            var idx = $scope.firstAwardNames.indexOf(data[0].award.awardName);
            $scope.firstAwardNames.splice(idx,1);
            $scope.firstAwardId = $scope.firstAwardId.replace(data[0].award.awardId+",","");
        }

    };

    /*
     全选 （奖励包）
     */
    $scope.awardCheckClick=function(){
        var checkbox=$(".awardCheck");
        /*
         遍历checkbox，并操作
         */
        for(var i=0;i<checkbox.length;i++){
            if(checkbox[i].checked){
                var idx = $scope.awardNames.indexOf(checkbox[i].name);
                $scope.awardNames.splice(idx,1);
                $scope.awardId = $scope.awardId.replace(checkbox[i].value+",","");
                checkbox[i].checked=false;
            } else{
                $scope.awardId += checkbox[i].value+",";
                $scope.awardNames.push(checkbox[i].name);
                checkbox[i].checked=true;
            }

        }
    }



    /*
     全选 （首充奖励包）
     */
    $scope.firstAwardCheckClick=function(){
        var checkbox=$(".firstAwardCheck");
        /*
         遍历checkbox，并操作
         */
        for(var i=0;i<checkbox.length;i++){
            if(checkbox[i].checked){
                var idx = $scope.firstAwardNames.indexOf(checkbox[i].name);
                $scope.firstAwardNames.splice(idx,1);
                $scope.firstAwardId = $scope.awardId.replace(checkbox[i].value+",","");
                checkbox[i].checked=false;
            } else{
                $scope.firstAwardId += checkbox[i].value+",";
                $scope.firstAwardNames.push(checkbox[i].name);
                checkbox[i].checked=true;
            }

        }
    }

    /*
    奖励包form监听
     */
    $scope.awardForm=function(){
        $scope.messagesData.messagesTitle="修改结果";
        $scope.messagesData.messagesBody="奖励礼包修改成功！";
        $("#messagesModal").modal('show');
        $("#awardPackageModal").modal('hide');
        $scope.awardData=$scope.awardNames;
    };

    /*
     首充奖励包form监听
     */
    $scope.firstAwardForm=function(){
        $scope.messagesData.messagesTitle="修改结果";
        $scope.messagesData.messagesBody="首充奖励礼包修改成功！";
        $("#messagesModal").modal('show');
        $("#firstAwardModal").modal('hide');
        $scope.firstAwardData=$scope.firstAwardNames;
    };


    /*
     根据awardPackage名称模糊查询
     */
    $scope.searchAwardClicked=function(){
       /* $scope.awardCondition.awardName=$scope.awardSearch;*/
        /*
         调用awardPackage的查询方法
         */
        billingGoodsFnService.awardPackage($scope.awardCondition,$scope.currentAwardPage,5).success(function(data){
            $scope.awardPackage=data.rows;
            $scope.pageAwardCount =data.page.pageCount;
            $scope.$apply();
        });
    };

    /*
     根据firstAwardSearch名称模糊查询
     */
    $scope.searchFirstAwardClicked=function(){
        /* $scope.awardCondition.awardName=$scope.awardSearch;*/
        /*
         调用firstBuy的查询方法
         */
        billingGoodsFnService.awardPackage($scope.awardCondition,$scope.currentFirstAwardPage,5).success(function(data){
            $scope.firstAwardPackage=data.rows;
            $scope.pageFirstAwardCount =data.page.pageCount;
            $scope.$apply();
        });
    };
    /*
     奖励包分页
     */
    $scope.onAwardPageChange = function() {
        // ajax request to load data
        console.log($scope.currentAwardPage);
        /*
         调用awardPackage的查询方法
         */
        billingGoodsFnService.awardPackage($scope.awardCondition,$scope.currentAwardPage,5).success(function(data){
            $scope.awardPackage=data.rows;
            $scope.pageAwardCount =data.page.pageCount;
            $scope.$apply();
        });
    };

    /*
     首充奖励包分页
     */
    $scope.onFirstAwardPageChange = function() {
        // ajax request to load data
        console.log($scope.currentFirstAwardPage);
        /*
         调用awardPackage的查询方法
         */
        billingGoodsFnService.awardPackage($scope.awardCondition,$scope.currentFirstAwardPage,5).success(function(data){
            $scope.firstAwardPackage=data.rows;
            $scope.pageFirstAwardCount =data.page.pageCount;
            $scope.$apply();
        });
    };

    /*
    奖励方式change事件
     */
    $scope.awardChange=function(data){
        if(data==undefined){
            data=-1;
        }
        $scope.awardCondition.awardType=data;
    }

    /*
     首充奖励方式change事件
     */
    $scope.firstAwardChange=function(data){
        if(data==undefined){
            data=-1;
        }
        $scope.awardCondition.awardType=data;
    }


    /*
     绑定select，change事件
     */
    $scope.resourceGameListChange=function(id){
        $scope.billingGoodsSearch.gameId=id;
        if($scope.billingGoodsSearch.gameId==undefined){
            $scope.billingGoodsSearch.gameId=-1;
        }
        gameAreaFnService.gameAreaFindByGameId($scope.billingGoodsSearch).success(function (response) {
            $scope.areaLists = response;
            $scope.$apply();
        });


        /*billingGoodsFnService.areaList(id,$scope.gameToySiteList).success(function(data){
            $scope.gameToyGameList=id;
            $scope.goodsData.gameId=id;
            $scope.areaLists=data;
        });*/
    };



    /*
     选择区服（弹框）
     */
    $scope.areaClick=function(){
        $("#areaAddModal").modal('show');
    }
    /*
    选择渠道（弹框）
     */
    $scope.channelClick=function(){
        $("#channelAddModal").modal('show');
    }

    /*
     选择奖励包（弹框）
     */
    $scope.awardPackageClick=function(){
        $("#awardPackageModal").modal('show');
    }


    /*
     首充奖励包（弹框）
     */
    $scope.firstAwardClick=function(){
        $("#firstAwardModal").modal('show');
    }

    /*
     监听areaAdd表单
     */
    $scope.areaAdd=function(){

        $scope.messagesData.messagesTitle="修改结果";
        $scope.messagesData.messagesBody="区服修改成功！";
        $("#messagesModal").modal('show');
        $("#areaAddModal").modal('hide');
        $scope.areaData=$scope.areaCode;

    };

    /*
    区服 checkbox单击操作
     */
    $scope.updateAreaSelection = function($event, id,code){
        var checkbox = $event.target;
        var action = (checkbox.checked?'add':'remove');//判断是否选中
        updateAreaSelected(action,id,code);//选中判断逻辑方法
    }


    /*
     选中判断逻辑方法（区服）
     */
    var updateAreaSelected = function(action,id,code){
        /*
         true添加集合元素
         */
        if(action == 'add' && $scope.areaId.indexOf(id) == -1){
            $scope.areaId.push(id);
            $scope.areaCode.push(code);
        }
        /*
         false移除集合元素
         */
        if(action == 'remove' && $scope.areaId.indexOf(id)!=-1){
            var idx = $scope.areaId.indexOf(id);
            $scope.areaId.splice(idx,1);
            $scope.areaCode.splice(idx,1);
        }
    }

/*
    /!*
     全选，遍历checkboxModel，正向操作 （区服）
     *!/
    $scope.areaAll= function (c,v) {
        if(c==true){
            $(".allCheck").text("取消全选");
            var idData=[];
            var codeData=[];
            $scope.areaModel=true;
            for(var i=0;i< v.length;i++){
                idData.push(v[i].id);
                codeData.push(v[i].areaName);
            }
            $scope.areaId=idData;
            $scope.areaCode=codeData;
        }else{
            $scope.areaModel=false;
            $scope.areaId=[];
            $scope.areaCode=[];
            $(".allCheck").text("全选");
        }

    };

    /!*
     反选 （区服）
     *!/
    $scope.areaVersa=function(c){
        if(c==true){
            $(".allVersa").text("取消反选");
        }else{
            $(".allVersa").text("反选");
        }
        var checkbox=$(".areaChildren");
        /!*
         遍历checkbox，并反向操作
         *!/
        for(var i=0;i<checkbox.length;i++){
            if(checkbox[i].checked){

                checkbox[i].checked=false;
                var idx = $scope.areaId.indexOf(Number(checkbox[i].name));
                $scope.areaId.splice(idx,1);
                $scope.areaCode.splice(idx,1);

            } else{
                $scope.areaId.push(Number(checkbox[i].name));
                $scope.areaCode.push(checkbox[i].id);
                checkbox[i].checked=true;

            }

        }
    }*/


    /*
     全选，遍历checkboxModel，正向操作
     */
    $scope.areaAll= function (v) {
        var checkbox=$(".areaChildren");
        for(var i=0;i<checkbox.length;i++){
            checkbox[i].checked=true;
        }
        var idData=[];
        var codeData=[];
        $scope.areaModel=true;
        for(var i=0;i< v.length;i++){
            idData.push(v[i].id);
            codeData.push(v[i].areaName);
        }
        $scope.areaId=idData;
        $scope.areaCode=codeData;
    };

    /*
     全不选
     */
    $scope.deselectAllArea=function(){
        var checkbox=$(".areaChildren");
        for(var i=0;i<checkbox.length;i++){
            checkbox[i].checked=false;
        }
        $scope.areaId=[];
        $scope.areaCode=[];
    }

    /*
     反选
     */
    $scope.areaVersa=function(){
        var checkbox=$(".areaChildren");
        /*
         遍历checkbox，并反向操作
         */
        for(var i=0;i<checkbox.length;i++){
            if(checkbox[i].checked){

                checkbox[i].checked=false;
                var idx = $scope.areaId.indexOf(Number(checkbox[i].name));
                $scope.areaId.splice(idx,1);
                $scope.areaCode.splice(idx,1);

            } else{
                $scope.areaId.push(Number(checkbox[i].name));
                $scope.areaCode.push(checkbox[i].id);
                checkbox[i].checked=true;

            }

        }
    }

});