/**
 * 商品配方添加controllers文件
 *   author：钟亮
 * @type {module|*}
 */
adminApp.config(function ($routeProvider) {
    $routeProvider.when('/billingGoodsEdit/view', {
        templateUrl: ctx + '/platform/billingGoods/billingGoodsEditInit',
        controller: 'billingGoodsEditController'
    }).when('/billingGoodsEdit/view/:id/:judge', {
        templateUrl: ctx + '/platform/billingGoods/billingGoodsEditInit',
        controller: 'billingGoodsEditController'
    });
});


/**
 商品配方controller
 全局：$scope
 Service:billingGoodsFnService
 */
adminApp.controller('billingGoodsEditController', function ($scope, billingGoodsFnService, gameAreaFnService, $location, $routeParams, gameToyTypeFnService, uniformFnService,$rootScope) {

    //初始化菜单栏
    $rootScope.menuBarData.menuBarThreeName = "ProductFormulaEdit";
    $rootScope.menuBarData.menuBarTitle = "ProductFormulaEdit";
    $scope.selectedId = [];//渠道id集合
    $scope.selectedCode = [];//渠道code集合
    $scope.messagesData = {};//设置提示框model数据集合
    $scope.channelCode = [];//渠道code集合（显示项）
    $scope.areaLists = [];//区服集合
    $scope.areaId = [];//区服id集合
    $scope.areaCode = [];//区服name集合
    $scope.gameToySiteList = $scope.siteTransfer;//初始化gameToySiteList，与全局site同步
    $scope.gameToyGameList = $scope.gameTransfer;//gameToyGameList，与全局game同步
    $scope.awardPackage = [];//奖励包集合
    $scope.firstAwardPackage = [];//首充奖励包集合
    $scope.awardCondition = {};//奖励包查询条件对象
    $scope.awardCondition.awardName = "";//初始化条件
    $scope.awardCondition.awardType = -1;//初始化条件
    $scope.awardId = "";//奖励包id字符串
    $scope.awardNames = [];//奖励包显示集合
    $scope.firstAwardId = "";//首充奖励包id字符串
    $scope.firstAwardNames = [];//首充奖励包显示集合
    $scope.firstBuyNames = [];//首充显示集合
    //$scope.goodsTypeDatas = [{id: 1, code: "load.goodsGem"}, {id: 2, code: "load.goodsProp"}];
    $scope.storeTypeDatas = [{id: 0, code: "load.gemStore"}, {id: 1, code: "load.RMBStore"}];
    $scope.uniFormData = {};//下拉框选项集合key对象初始化
    $scope.uniFormData.key = "operation.item.itemType";//初始化动态下拉框的key
    $scope.flag = -1;//保存宝石类型id
    $scope.flagGoods = -1;//保存道具集合下标
    $scope.flagGem = -1;//保存宝石集合下标
    /**
     加载下拉框
     */
    uniformFnService.uniGetKey($scope.uniFormData).success(function (response) {
        response = JSON.parse(response.itemJson);
        for (var i = 0; i < response.length; i++) {
            response[i].key = "load.itemType" + response[i].key;
            if (response[i].key == "load.itemTypegem") {
                $scope.flag = response[i].value;
            }
            ;
            if (response[i].key == "load.itemTypeitem") {
                $scope.flagGoods = i;
            }
            ;
        }
        response.splice($scope.flagGoods, 1);
        $scope.goodsTypeDatas = response;
    });

    $scope.gameToyTypeSearch = {};//初始化查询条件对象
    $scope.gameToyTypeSearch.gameId = $scope.gameTransfer;//初始化查询gameId
    $scope.gameToyTypeSearch.itemTypeName = "";
    $scope.gameToyTypeSearch.itemCode = "";
    $scope.placeholderItemName = "load.placeholderItemName";//道具名称搜索国际化
    $scope.placeholderItemCode = "load.placeholderItemCode";//道具code搜索国际化

    var check = -1;//判断是否成功，url参数 1，是修改成功，0是修改失败，2是添加成功，3是添加失败，-1是失效
    $scope.goodsData = {}//商品集合;
    $scope.goodsData.gameId = $scope.gameTransfer;//初始化gameId，与全局game同步
    $scope.goodsData.siteId = $scope.siteTransfer;//初始化gameId，与全局game同步

    $scope.billingGoodsSearch = {};//商品查询条件
    $scope.billingGoodsSearch.gameId = $scope.gameTransfer;
    $scope.billingGoodsSearch.siteId = $scope.siteTransfer;


    if ($scope.goodsData.goodsType == $scope.flag) {
        $("#propIdDiv").hide();
        $("#goodsCodeDiv").show();
        $("#propDetails").hide();
        $("#goodsGoodsName").show();
        $("#propGoodsName").hide();
        $scope.goodsData.goodsName = "";
        $scope.goodsData.goodsCode = "";
        $scope.goodsData.goodsDesc = "";
    } else {
        $("#propIdDiv").show();
        $("#goodsCodeDiv").hide();
        $("#propDetails").show();
        $("#goodsGoodsName").hide();
        $("#propGoodsName").show();
        $scope.goodsData.goodsName = "";
        $scope.goodsData.goodsCode = "";
        $scope.goodsData.goodsDesc = "";
    }
    /**
     * 商城类型change
     * @param shopType
     */
    $scope.shopTypeChange = function (shopType) {

        //storeType 0代表宝石商城 1代表RMB商城
        if (shopType == 0) {
            //$scope.goodsTypeDatas = [{id: 2, code: "load.goodsProp"}];
            /**
             加载下拉框
             */
            uniformFnService.uniGetKey($scope.uniFormData).success(function (response) {
                response = JSON.parse(response.itemJson);
                for (var i = 0; i < response.length; i++) {
                    response[i].key = "load.itemType" + response[i].key;
                    if (response[i].key == "load.itemTypegem") {
                        $scope.flagGem = i;

                    }
                    ;
                    if (response[i].key == "load.itemTypeitem") {
                        $scope.flagGoods = i;
                    }
                    ;
                }
                response.splice($scope.flagGem, 1);
                response.splice($scope.flagGoods - 1, 1);
                $scope.goodsTypeDatas = response;
                $scope.$apply();
            });
            $scope.goodsData.goodsType = "";
            $("#propIdDiv").show();
            $("#goodsCodeDiv").hide();
            $("#propDetails").show();
            $("#goodsGoodsName").hide();
            $("#propGoodsName").show();
            $scope.goodsData.goodsName = "";
            $scope.goodsData.goodsCode = "";
            $scope.goodsData.goodsDesc = "";
        } else if (shopType == 1) {
            //$scope.goodsTypeDatas = [{id: 1, code: "load.goodsGem"}, {id: 2, code: "load.goodsProp"}];
            /**
             加载下拉框
             */
            uniformFnService.uniGetKey($scope.uniFormData).success(function (response) {
                response = JSON.parse(response.itemJson);
                for (var i = 0; i < response.length; i++) {
                    response[i].key = "load.itemType" + response[i].key;
                    if (response[i].key == "load.itemTypeitem") {
                        $scope.flagGoods = i;
                    }
                    ;
                }
                response.splice($scope.flagGoods, 1);
                $scope.goodsTypeDatas = response;
                $scope.$apply();
            });
            $scope.goodsData.goodsType = "";
            $("#propIdDiv").hide();
            $("#goodsCodeDiv").show();
            $("#propDetails").hide();
            $("#goodsGoodsName").show();
            $("#propGoodsName").hide();
            $scope.goodsData.goodsName = "";
            $scope.goodsData.goodsCode = "";
            $scope.goodsData.goodsDesc = "";
            $scope.goodsData.goodsPic = "";
            $scope.goodsData.relatedGoodsId = "";
        } else {
            $scope.goodsData.goodsType = "";
            $scope.goodsData.goodsName = "";
            $scope.goodsData.goodsCode = "";
            $scope.goodsData.goodsDesc = "";
            $scope.goodsData.goodsPic = "";
            $scope.goodsData.relatedGoodsId = "";
        }
    }

    /**
     * 商品类型change
     * @param goodsType
     */
    $scope.goodsTypeChange = function (goodsType) {
        //$scope.flag 代表宝石
        if (goodsType == $scope.flag) {
            $("#propIdDiv").hide();
            $("#goodsCodeDiv").show();
            $("#propDetails").hide();
            $("#goodsGoodsName").show();
            $("#propGoodsName").hide();
            $scope.goodsData.goodsName = "";
            $scope.goodsData.goodsCode = "";
            $scope.goodsData.goodsDesc = "";
            $scope.goodsData.goodsPic = "";
            $scope.goodsData.relatedGoodsId = "";
        } else {
            $("#propIdDiv").show();
            $("#goodsCodeDiv").hide();
            $("#propDetails").show();
            $("#goodsGoodsName").hide();
            $("#propGoodsName").show();
            $scope.goodsData.goodsName = "";
            $scope.goodsData.goodsCode = "";
            $scope.goodsData.goodsDesc = "";
            $scope.goodsData.goodsPic = "";
            $scope.goodsData.relatedGoodsId = "";
        }
    }

    /**
     *刷新查询页面，调用查询方法 公共方法
     */
    function itemFindByItemTypePublic(gameToyTypeSearch, pageIndex, pageSize) {
        gameToyTypeFnService.itemFindByItemType(gameToyTypeSearch, pageIndex, pageSize).success(function (response) {
            $scope.gameTypeToys = response.rows;
            $scope.pageCountItem = response.page.pageCount;
            $scope.$apply();
        });
    }

    /**
     *分页
     */
    $scope.onPageChangeItem = function () {
        /*
         刷新查询页面，调用查询方法
         */
        itemFindByItemTypePublic($scope.gameToyTypeSearch, $scope.currentPageItem, 10);
    };


    /**
     *查询表单监听
     */
    $scope.searchButtonClicked = function () {
        /*
         刷新查询页面，调用查询方法
         */
        itemFindByItemTypePublic($scope.gameToyTypeSearch, 1, 10);
    }

    /**
     物品选择（弹框）
     */
    $scope.itemSelected = function () {
        if ($scope.goodsData.gameId != "" && $scope.goodsData.gameId != undefined && $scope.goodsData.gameId != -1 && $scope.goodsData.goodsType != "" && $scope.goodsData.goodsType != undefined) {
            $scope.gameToyTypeSearch.itemType = $scope.goodsData.goodsType;//3代表道具
            $scope.gameToyTypeSearch.gameId = $scope.goodsData.gameId;
            /*
             刷新查询页面，调用查询方法
             */
            itemFindByItemTypePublic($scope.gameToyTypeSearch, 1, 10);

            $("#itemModal").modal("show");
        } else {
            $scope.messagesData.messagesTitle = "load.promptTitle";
            $scope.messagesData.messagesBody = "load.itemSelectMessagesBody";
            $("#messagesModal").modal('show');
        }

    }


    /**
     *物品选择（勾选）
     */
    $scope.itemItemChoice = function (data) {
        $scope.goodsData.goodsName = data.itemName;
        $scope.goodsData.goodsCode = data.itemCode;
        $scope.goodsData.goodsDesc = data.itemExtend;
        $("#itemModal").modal("hide");
    }

    /**
     根据全局game，site查询区服
     */
    if ($scope.billingGoodsSearch.gameId == undefined) {
        $scope.billingGoodsSearch.gameId = -1;
    }
    gameAreaFnService.gameAreaFindByGameId($scope.billingGoodsSearch).success(function (response) {
        $scope.areaLists = response;
        $scope.$apply();

        if ($routeParams.judge == "update") {
            billingGoodsFnService.goodsGetById($routeParams.id).success(function (data) {
                data.goodsMarkTime = $scope.format(data.goodsMarkTime, "yyyy-MM-dd HH:mm:ss");
                $scope.goodsData = data;
                $scope.goodsData.goodsType = data.goodsType.toString();
                $scope.validStatus = data.validStatus.toString();
                $scope.shopType = Number(data.shopType);
                $scope.gameToyGameList = data.gameId;
                $scope.gameToySiteList = data.siteId;
                $scope.goodsData.gameId = data.gameId;
                $scope.goodsData.siteId = data.siteId;
                $scope.goodsData.areaIds = data.areaIds;
                var areaCheckbox = $(".areaChildren");
                var areaIds = data.areaIds.split(",");
                for (var i = 0; i < areaIds.length; i++) {
                    for (var j = 0; j < $scope.areaLists.length; j++) {
                        if (areaIds[i] == $scope.areaLists[j].id) {
                            $scope.areaCode.push($scope.areaLists[j].areaName);
                            $scope.areaId.push($scope.areaLists[j].id);
                        }

                    }
                    /*
                     遍历checkbox，并初始化
                     */
                    for (var k = 0; k < areaCheckbox.length; k++) {
                        if (areaCheckbox[k].name == areaIds[i]) {
                            areaCheckbox[k].checked = true;
                        }
                    }
                }
                $scope.areaData = $scope.areaCode;
                $scope.$apply();
                console.log($scope.areaData);

                if ($scope.goodsData.goodsType == 1) {
                    $("#propIdDiv").hide();
                    $("#goodsCodeDiv").show();
                    $("#propDetails").hide();
                    $("#goodsGoodsName").show();
                    $("#propGoodsName").hide();
                    //$scope.goodsData.goodsName = "";
                    //$scope.goodsData.goodsCode = "";
                    //$scope.goodsData.goodsDesc = "";
                    //$scope.goodsData.goodsPic = "";
                    //$scope.goodsData.relatedGoodsId = "";
                } else if ($scope.goodsData.goodsType == 2) {
                    $("#propIdDiv").show();
                    $("#goodsCodeDiv").hide();
                    $("#propDetails").show();
                    $("#goodsGoodsName").hide();
                    $("#propGoodsName").show();
                    //$scope.goodsData.goodsName = "";
                    //$scope.goodsData.goodsCode = "";
                    //$scope.goodsData.goodsDesc = "";
                    //$scope.goodsData.goodsPic = "";
                    //$scope.goodsData.relatedGoodsId = "";
                }
            });
        }

    });

    $scope.goodsData.goodsId = $routeParams.id;

    /**
     * 调用查询方法，并传最新的currentPage
     * @param gameToyTypeSearch
     * @param pageIndex
     * @param pageSize
     */
    function gameAreaFindByNamePublic(billingGoodsSearch, pageIndex, pageSize) {
        gameAreaFnService.gameAreaFindByName(billingGoodsSearch, pageIndex, pageSize).success(function (response) {
            $scope.areaLists = response.rows;
            $scope.pageCount = response.page.pageCount;
            $scope.$apply();
        });
    }


    /**
     区服分页
     */
    $scope.onPageChange = function () {
        /*
         调用查询方法，并传最新的currentPage
         */
        gameAreaFindByNamePublic($scope.billingGoodsSearch, $scope.currentPage, 10);
    };

    /*    /!**
     奖励包列表
     *!/
     billingGoodsFnService.awardPackage($scope.awardCondition, 1, 5).success(function (data) {
     $scope.awardPackage = data.rows;//绑定奖励包
     $scope.firstAwardPackage = data.rows;//绑定首充奖励包
     $scope.pageAwardCount = data.page.pageCount;//绑定奖励包分页
     $scope.pageFirstAwardCount = data.page.pageCount;//绑定奖励包分页
     $scope.$apply();
     });*/

    /**
     首充奖励（弹框）
     */
    $scope.firstBuyClick = function () {
        $("#firstBuyModal").modal('show');
    }

    /**
     监听首充form
     */
    $scope.firstBuyAdd = function () {
        var name = $scope.firstGoodsCode + $scope.firstBuyOperation + $scope.operateCount;
        var names = [];
        names.push(name);
        $scope.firstBuyNames = names;
        $scope.messagesData.messagesTitle = "修改结果";
        $scope.messagesData.messagesBody = "首充奖励礼包修改成功！";
        $("#messagesModal").modal('show');
        $("#firstBuyModal").modal('hide');
    }

    /**
     监听商品form
     */
    $scope.billingGoodsEditForm = function () {
        $scope.goodsData.goodsMarkTime = new Date($scope.goodsData.goodsMarkTime);
        $scope.goodsData.createTime = new Date($scope.goodsData.createTime);
        delete $scope.goodsData.awardId;
        delete $scope.goodsData.firstBuyPolicy;

        $scope.goodsData.valid = $scope.validStatus;
        $scope.goodsData.shopTypeId = $scope.shopType;
        var areaids = "";
        for (var i = 0; i < $scope.areaId.length; i++) {
            areaids += $scope.areaId[i] + ",";
        }
        $scope.goodsData.areaIds = areaids;

        if ($routeParams.judge == "update") {
            delete $scope.goodsData.validStatus;
            delete $scope.goodsData.shopType;
            /*
             调用修改方法
             */
            billingGoodsFnService.billingGoodsUpdate($scope.goodsData).success(function () {
                check = 1;
                $location.path("/billingGoods/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            }).error(function () {
                check = 0;
                $location.path("/billingGoods/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');
            });
        } else {
            delete $scope.goodsData.createTime;
            delete $scope.goodsData.goodsId;
            /*
             调用添加方法
             */
            billingGoodsFnService.billingGoodsAdd($scope.goodsData).success(function () {
                check = 2;
                $location.path("/billingGoods/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');

            }).error(function () {
                check = 3;
                $location.path("/billingGoods/view/" + check);
                $scope.$apply();
                $("#messagesModal").modal('show');
            });
        }


    }

    /**
     更改奖励包的选择状态，并保存相应的id
     */
    $scope.check = function (val, chk) {
        var data = $(this);
        if (chk == true) {
            $scope.awardId += data[0].award.awardId + ",";
            $scope.awardNames.push(data[0].award.awardName);
        } else {
            var idx = $scope.awardNames.indexOf(data[0].award.awardName);
            $scope.awardNames.splice(idx, 1);
            $scope.awardId = $scope.awardId.replace(data[0].award.awardId + ",", "");
        }

    };

    /**
     更改首充奖励包的选择状态，并保存相应的id
     */
    $scope.firstAwardCheck = function (val, chk) {
        var data = $(this);
        if (chk == true) {
            $scope.firstAwardId += data[0].award.awardId + ",";
            $scope.firstAwardNames.push(data[0].award.awardName);
        } else {
            var idx = $scope.firstAwardNames.indexOf(data[0].award.awardName);
            $scope.firstAwardNames.splice(idx, 1);
            $scope.firstAwardId = $scope.firstAwardId.replace(data[0].award.awardId + ",", "");
        }

    };

    /**
     全选 （奖励包）
     */
    $scope.awardCheckClick = function () {
        var checkbox = $(".awardCheck");
        /*
         遍历checkbox，并操作
         */
        for (var i = 0; i < checkbox.length; i++) {
            if (checkbox[i].checked) {
                var idx = $scope.awardNames.indexOf(checkbox[i].name);
                $scope.awardNames.splice(idx, 1);
                $scope.awardId = $scope.awardId.replace(checkbox[i].value + ",", "");
                checkbox[i].checked = false;
            } else {
                $scope.awardId += checkbox[i].value + ",";
                $scope.awardNames.push(checkbox[i].name);
                checkbox[i].checked = true;
            }

        }
    }


    /**
     全选 （首充奖励包）
     */
    $scope.firstAwardCheckClick = function () {
        var checkbox = $(".firstAwardCheck");
        /*
         遍历checkbox，并操作
         */
        for (var i = 0; i < checkbox.length; i++) {
            if (checkbox[i].checked) {
                var idx = $scope.firstAwardNames.indexOf(checkbox[i].name);
                $scope.firstAwardNames.splice(idx, 1);
                $scope.firstAwardId = $scope.awardId.replace(checkbox[i].value + ",", "");
                checkbox[i].checked = false;
            } else {
                $scope.firstAwardId += checkbox[i].value + ",";
                $scope.firstAwardNames.push(checkbox[i].name);
                checkbox[i].checked = true;
            }

        }
    }

    /**
     奖励包form监听
     */
    $scope.awardForm = function () {
        $scope.messagesData.messagesTitle = "修改结果";
        $scope.messagesData.messagesBody = "奖励礼包修改成功！";
        $("#messagesModal").modal('show');
        $("#awardPackageModal").modal('hide');
        $scope.awardData = $scope.awardNames;
    };

    /**
     首充奖励包form监听
     */
    $scope.firstAwardForm = function () {
        $scope.messagesData.messagesTitle = "修改结果";
        $scope.messagesData.messagesBody = "首充奖励礼包修改成功！";
        $("#messagesModal").modal('show');
        $("#firstAwardModal").modal('hide');
        $scope.firstAwardData = $scope.firstAwardNames;
    };


    /**
     根据awardPackage名称模糊查询
     */
    $scope.searchAwardClicked = function () {
        /* $scope.awardCondition.awardName=$scope.awardSearch;*/
        /*
         调用awardPackage的查询方法
         */
        billingGoodsFnService.awardPackage($scope.awardCondition, $scope.currentAwardPage, 5).success(function (data) {
            $scope.awardPackage = data.rows;
            $scope.pageAwardCount = data.page.pageCount;
            $scope.$apply();
        });
    };

    /**
     根据firstAwardSearch名称模糊查询
     */
    $scope.searchFirstAwardClicked = function () {
        /* $scope.awardCondition.awardName=$scope.awardSearch;*/
        /*
         调用firstBuy的查询方法
         */
        billingGoodsFnService.awardPackage($scope.awardCondition, $scope.currentFirstAwardPage, 5).success(function (data) {
            $scope.firstAwardPackage = data.rows;
            $scope.pageFirstAwardCount = data.page.pageCount;
            $scope.$apply();
        });
    };
    /**
     奖励包分页
     */
    $scope.onAwardPageChange = function () {
        // ajax request to load data
        console.log($scope.currentAwardPage);
        /*
         调用awardPackage的查询方法
         */
        billingGoodsFnService.awardPackage($scope.awardCondition, $scope.currentAwardPage, 5).success(function (data) {
            $scope.awardPackage = data.rows;
            $scope.pageAwardCount = data.page.pageCount;
            $scope.$apply();
        });
    };

    /* /!*
     首充奖励包分页
     *!/
     $scope.onFirstAwardPageChange = function() {
     // ajax request to load data
     console.log($scope.currentFirstAwardPage);
     /!*
     调用awardPackage的查询方法
     *!/
     billingGoodsFnService.awardPackage($scope.awardCondition,$scope.currentFirstAwardPage,5).success(function(data){
     $scope.firstAwardPackage=data.rows;
     $scope.pageFirstAwardCount =data.page.pageCount;
     $scope.$apply();
     });
     };*/

    /**
     奖励方式change事件
     */
    $scope.awardChange = function (data) {
        if (data == undefined) {
            data = -1;
        }
        $scope.awardCondition.awardType = data;
    }

    /**
     首充奖励方式change事件
     */
    $scope.firstAwardChange = function (data) {
        if (data == undefined) {
            data = -1;
        }
        $scope.awardCondition.awardType = data;
    }


    /**
     绑定select，change事件
     */
    $scope.resourceGameListChange = function (id) {
        $scope.billingGoodsSearch.gameId = id;
        if ($scope.billingGoodsSearch.gameId == undefined) {
            $scope.billingGoodsSearch.gameId = -1;
        }
        if ($scope.billingGoodsSearch.siteId == undefined) {
            $scope.billingGoodsSearch.siteId = -1;
        }
        $scope.goodsData.gameId = id;
        /*
         刷新查询页面，调用查询方法
         */
        gameAreaFnService.gameAreaFindByName($scope.billingGoodsSearch, 1, 10).success(function (response) {
            $scope.areaLists = response.rows;
            $scope.pageCount = response.page.pageCount;
            $scope.$apply();
        });


        /*billingGoodsFnService.areaList(id,$scope.gameToySiteList).success(function(data){
         $scope.gameToyGameList=id;
         $scope.goodsData.gameId=id;
         $scope.areaLists=data;
         });*/
    };

    $scope.resourceSiteListChange = function (id) {
        $scope.billingGoodsSearch.siteId = id;
        if ($scope.billingGoodsSearch.gameId == undefined) {
            $scope.billingGoodsSearch.gameId = -1;
        }
        if ($scope.billingGoodsSearch.siteId == undefined) {
            $scope.billingGoodsSearch.siteId = -1;
        }

        /*
         刷新查询页面，调用查询方法
         */
        gameAreaFnService.gameAreaFindByName($scope.billingGoodsSearch, 1, 10).success(function (response) {
            $scope.goodsData.siteId = id;
            $scope.areaLists = response.rows;
            $scope.pageCount = response.page.pageCount;
            $scope.$apply();
        });

    };

    /**
     选择区服（弹框）
     */
    $scope.areaClick = function () {
        $("#areaAddModal").modal('show');
    }
    /**
     选择渠道（弹框）
     */
    $scope.channelClick = function () {
        $("#channelAddModal").modal('show');
    }

    /**
     选择奖励包（弹框）
     */
    $scope.awardPackageClick = function () {
        $("#awardPackageModal").modal('show');
    }


    /**
     首充奖励包（弹框）
     */
    $scope.firstAwardClick = function () {
        $("#firstAwardModal").modal('show');
    }

    /**
     监听areaAdd表单
     */
    $scope.areaAdd = function () {

        //$scope.messagesData.messagesTitle = "修改结果";
        //$scope.messagesData.messagesBody = "区服修改成功！";
        //$("#messagesModal").modal('show');
        $("#areaAddModal").modal('hide');
        $scope.areaData = $scope.areaCode;

    };

    /*
     区服 checkbox单击操作
     */
    $scope.updateAreaSelection = function ($event, id, code) {
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');//判断是否选中
        updateAreaSelected(action, id, code);//选中判断逻辑方法
    }


    /**
     选中判断逻辑方法（区服）
     */
    var updateAreaSelected = function (action, id, code) {
        /*
         true添加集合元素
         */
        if (action == 'add' && $scope.areaId.indexOf(id) == -1) {
            $scope.areaId.push(id);
            $scope.areaCode.push(code);
        }
        /*
         false移除集合元素
         */
        if (action == 'remove' && $scope.areaId.indexOf(id) != -1) {
            var idx = $scope.areaId.indexOf(id);
            $scope.areaId.splice(idx, 1);
            $scope.areaCode.splice(idx, 1);
        }
    }


    /**
     全选，遍历checkboxModel，正向操作
     */
    $scope.areaAll = function (v) {
        var checkbox = $(".areaChildren");
        for (var i = 0; i < checkbox.length; i++) {
            checkbox[i].checked = true;
        }
        var idData = [];
        var codeData = [];
        $scope.areaModel = true;
        for (var i = 0; i < v.length; i++) {
            idData.push(v[i].id);
            codeData.push(v[i].areaName);
        }
        $scope.areaId = idData;
        $scope.areaCode = codeData;
    };

    /**
     全不选
     */
    $scope.deselectAllArea = function () {
        var checkbox = $(".areaChildren");
        for (var i = 0; i < checkbox.length; i++) {
            checkbox[i].checked = false;
        }
        $scope.areaId = [];
        $scope.areaCode = [];
    }

    /**
     反选
     */
    $scope.areaVersa = function () {
        var checkbox = $(".areaChildren");
        /*
         遍历checkbox，并反向操作
         */
        for (var i = 0; i < checkbox.length; i++) {
            if (checkbox[i].checked) {

                checkbox[i].checked = false;
                var idx = $scope.areaId.indexOf(Number(checkbox[i].name));
                $scope.areaId.splice(idx, 1);
                $scope.areaCode.splice(idx, 1);

            } else {
                $scope.areaId.push(Number(checkbox[i].name));
                $scope.areaCode.push(checkbox[i].id);
                checkbox[i].checked = true;

            }

        }
    }

});