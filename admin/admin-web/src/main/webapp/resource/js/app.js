/**
 * app配置文件
 * author：钟亮
 * @type {module|*}
 */
/*
 配置全局app
 */
var adminApp = angular.module("adminApp", ['ng-pagination', 'ngRoute', 'pascalprecht.translate', 'ng.ueditor']);

adminApp.config(function ($routeProvider) {

        $routeProvider.when('/index', {
            templateUrl: 'welcome',
            controller: 'centerController'
        });


    })

    .config(['$translateProvider', function ($translateProvider) {

        var simpleJavaPropertiesReader = function (lines) {
            var result = {};
            var patterns = [
                /^\s*([^=:]+)\s*[:|=]\s*(.*)$/ // anything before = or : (the key), then everything unitl the end
            ];
            var quotePattern = /^"(.*)"$/;
            for (var i = 0; i < lines.length; i++) {
                for (var j = 0; j < patterns.length; j++) {
                    var match = lines[i].match(patterns[j]);
                    if (match && match[0] && match[0].length > 0) {
                        var key = match[1], value = match[2];
                        if (value && value.length > 0) {
                            var quoteMatch = value.match(quotePattern);
                            if (quoteMatch && quoteMatch[0] && quoteMatch[0].length) {
                                value = quoteMatch[1];
                            }
                        }
                        result[key] = value;
                        break;
                    }
                }
            }
            return result;
        };

        // Register a loader for the static files
        // So, the module will search missing translation tables under the specified urls.
        // Those urls are [prefix][langKey][suffix].
        $translateProvider.useStaticFilesLoader({
            prefix: 'resource/i18n/',
            suffix: '.properties',
            $http: {
                transformResponse: function (data, headersGetter, status) {
                    return simpleJavaPropertiesReader(data.split('\n'));
                }
            }
        });

        // Tell the module what language to use by default
        $translateProvider.preferredLanguage('zh_ZH');

    }]);


/*adminApp.controller('centerController', ['$scope', '$translate', function ($scope, $translate) {

 $scope.setLang = function (langKey) {
 // You can change the language during runtime
 $translate.use(langKey);
 };

 }]);*/
adminApp.controller('centerController', function ($rootScope, $location, menuFnService, resourceFnService, $route, $translate, userFnService, articleService) {
    $rootScope.siteList = [];
    $rootScope.COMMONALITY = {};//公共变量，用来跳转传值
    $rootScope.menu = {};
    $rootScope.ITEMLIST = [];//物品list，公用
    $rootScope.itemPlan = {};
    $rootScope.itemPlan.unapprovedCount = 0;
    $rootScope.itemPlan.applyForCount = 0;
    $rootScope.langKey = "load.DefaultLanguage";
    $rootScope.siteModelCode = "load.OnChangeTip";
    $rootScope.siteModel = "";
    $rootScope.gameModelCode = "load.OnChangeTip";
    $rootScope.gameModel = "";
    $rootScope.menuSearchModel = "";
    $rootScope.menuBarData = {};//菜单栏集合
    $rootScope.menuBarData.menuBarOneName = "";//一级菜单名称
    $rootScope.menuBarData.menuBarTwoName = "";//二级菜单名称
    $rootScope.menuBarData.menuBarTwoUrl = "";//二级菜单url
    $rootScope.menuBarData.menuBarThreeName = "";//三级菜单名称
    $rootScope.menuBarData.menuBarTitle = "";//标题
    //获取未审批个数
    articleService.itemDisposePlan(0, 2).success(function (data) {
        $rootScope.itemPlan.unapprovedCount = data;
        $rootScope.itemPlan.sumCount = $rootScope.itemPlan.unapprovedCount + $rootScope.itemPlan.applyForCount;
        $rootScope.$apply();
    });

    //获取未申请个数
    articleService.itemDisposePlan(0, 1).success(function (data) {
        $rootScope.itemPlan.applyForCount = data;
        $rootScope.itemPlan.sumCount = $rootScope.itemPlan.unapprovedCount + $rootScope.itemPlan.applyForCount;
        $rootScope.$apply();
    });


    //每隔三分钟刷新订单状态
    setInterval(function () {
        //获取未审批个数
        articleService.itemDisposePlan(0, 2).success(function (data) {
            $rootScope.itemPlan.unapprovedCount = data;
            $rootScope.itemPlan.sumCount = $rootScope.itemPlan.unapprovedCount + $rootScope.itemPlan.applyForCount;
            $rootScope.$apply();
        });

        //获取未申请个数
        articleService.itemDisposePlan(0, 1).success(function (data) {
            $rootScope.itemPlan.applyForCount = data;
            $rootScope.itemPlan.sumCount = $rootScope.itemPlan.unapprovedCount + $rootScope.itemPlan.applyForCount;
            $rootScope.$apply();
        });
        $rootScope.$apply();
    }, 360000);

    /*
     任务进度点击
     */
    $rootScope.disposePlanClick = function () {
        $.ajax({
            url: ctx + '/operation/article/articleAuthorityInit',
            data: {menuName: "ArticleApplication"},
            success: function (data) {
                if (data) {
                    $location.path("/article/view");
                } else {
                    $location.path("/approval/view");
                }
            }, async: false
        });
    }

    /*
     任务进度点击（申请）
     */
    $rootScope.disposePlanArticleClick = function () {
        $.ajax({
            url: ctx + '/operation/article/articleAuthorityInit',
            data: {menuName: "ArticleApplication"},
            success: function (data) {
                if (data) {
                    $location.path("/article/view");
                } else {
                    $location.path("/approval/view");
                }
            }, async: false
        });
    }

    /*
     任务进度点击（审核）
     */
    $rootScope.disposePlanApprovalClick = function () {
        $.ajax({
            url: ctx + '/operation/article/articleAuthorityInit',
            data: {menuName: "ApprovalManage"},
            success: function (data) {
                if (data) {
                    $location.path("/approval/view");
                } else {
                    $location.path("/article/view");
                }
            }, async: false
        });
    }

    //获取游戏
    resourceFnService.resourceGetGame().success(function (data) {
        var list = [];
        for (var key in data) {
            list.push(data[key]);
        }
        $rootScope.gameList = list;
        //for (var i = 0; i < $rootScope.gameList.length; i++) {
        //    if ($rootScope.gameList[i].code == "game.tol") {
        //        $rootScope.gameTransfer = $rootScope.gameList[i].id;//全局game
        //        $rootScope.gameModel = $rootScope.gameList[i].id;
        //    }
        //}
        $rootScope.$apply();

    });


    //获取资源
    resourceFnService.resourceGetSite().success(function (data) {
        var list = [];
        for (var key in data) {
            data[key].code = "site." + data[key].code;
            list.push(data[key]);
        }
        $rootScope.siteList = list;
        $rootScope.$apply();
        $route.reload();
    });

    /*
     调用menuService，查询菜单列表
     */
    menuFnService.menuList().success(function (data) {
        console.log(data);
        $rootScope.menu = data;
        $rootScope.$apply();
        $route.reload();
    });


    //设置翻译参数
    $rootScope.setLang = function (langKey) {
        if (langKey == "en_US") {
            $rootScope.langKey = "load.English";
        }
        if (langKey == "zh_ZH") {
            $rootScope.langKey = "load.Chinese";
        }
        if (langKey == "default") {
            langKey = "zh_ZH";
            $rootScope.langKey = "load.DefaultLanguage";
        }
        $translate.use(langKey);
        $route.reload();
    };

    $rootScope.setLang("zh_ZH");
    //菜单栏跳转
    $rootScope.skipPageBar = function (url) {
        $location.path(url);
    }

    //获取用户习惯
    $.ajax({
        type: "post",
        url: ctx + '/system/user/habitUrlQuery',
        success: function (data) {
            if (data == null || data == "") {
                $rootScope.menuBarData = {};
            } else {
                $rootScope.menuBarData = JSON.parse(data);
            }
        }

        , async: false
    });

    /*
     动态绑定url
     */
    $rootScope.skipPage = function (data) {
        for (var i = 0; i < $rootScope.menu.childrenList.length; i++) {
            if ($rootScope.menu.childrenList[i].id == data.parentId) {
                $rootScope.menuBarData.menuBarOneName = $rootScope.menu.childrenList[i].menuName;
            }
        }
        $rootScope.menuBarData.menuBarTwoName = data.menuName;//二级菜单名称
        $rootScope.menuBarData.menuBarTwoUrl = data.url;//二级菜单url
        $rootScope.menuBarData.menuBarThreeName = "";
        $rootScope.menuBarData.menuBarTitle = data.menuName;
        var habitJson = JSON.stringify($rootScope.menuBarData);
        userFnService.habitUrlEdit(habitJson);
        $location.path(data.url);
    };

    //菜单点击
    $rootScope.menuListClicked = function (name, id, menuList) {
        /*
         菜单状态切换
         */
        if ($("." + name + id).attr("class") == "menu-list " + name + id) {
            $("." + name + id).attr("class", "menu-list " + name + id + " nav-active");
            for (var i = 0; i < menuList.length; i++) {
                if (menuList[i].id != id) {
                    $("." + menuList[i].menuName + menuList[i].id).attr("class", "menu-list " + menuList[i].menuName + menuList[i].id);
                }
            }
        } else {
            $("." + name + id).attr("class", "menu-list " + name + id);
        }
    }

    function menuHandover(name, id, menuList) {
        /*
         菜单状态切换
         */
        if ($("." + name + id).attr("class") == "menu-list " + name + id) {
            $("." + name + id).attr("class", "menu-list " + name + id + " nav-active");
            for (var i = 0; i < menuList.length; i++) {
                if (menuList[i].id != id) {
                    $("." + menuList[i].menuName + menuList[i].id).attr("class", "menu-list " + menuList[i].menuName + menuList[i].id);
                }
            }
        }
    };

    $rootScope.SearchTips = "load.SearchTips";//菜单搜索国际化
    //菜单检索
    $rootScope.myKeyUp = function (e, menuSearchModel) {
        var keycode = window.event ? e.keyCode : e.which;
        if (keycode == 13) {
            //国际化替换
            var name = $translate.instant(menuSearchModel);
            if (name == null || name == "") {
                $location.path("/homePage/view");
                for (var i = 0; i < $rootScope.menu.childrenList.length; i++) {
                    $("." + $rootScope.menu.childrenList[i].menuName + $rootScope.menu.childrenList[i].id).attr("class", "menu-list " + $rootScope.menu.childrenList[i].menuName + $rootScope.menu.childrenList[i].id);
                }
            } else {
                //根据名称查询
                $.ajax({
                    url: ctx + '/system/menu/getMenuByName',
                    data: {name: name},
                    success: function (data) {
                        if (data == null || data == "") {
                            $.gritter.add({
                                // (string | mandatory) the heading of the notification
                                title: $translate.instant("load.menuTipTitle"),
                                // (string | mandatory) the text inside the notification
                                text: $translate.instant("load.menuTipBody")
                            });
                        } else {
                            //是否是一级菜单
                            if (data.parentId == 0) {
                                menuHandover(data.menuName, data.id, $rootScope.menu.childrenList);
                            } else {
                                $("." + data.menuName + data.id).trigger("click");
                                for (var i = 0; i < $rootScope.menu.childrenList.length; i++) {
                                    if (data.parentId == $rootScope.menu.childrenList[i].id) {
                                        menuHandover($rootScope.menu.childrenList[i].menuName, $rootScope.menu.childrenList[i].id, $rootScope.menu.childrenList);
                                        $rootScope.skipPage(data);
                                    }
                                }

                            }
                        }
                    }, async: false
                });
            }


        }
    }

    /**
     * 验证
     * @param i 控件id
     * @param tip 提示内容
     */
    $rootScope.checkVerify = function (i, tip) {
        if (document.getElementById(i).validity.patternMismatch === true) {
            document.getElementById(i).setCustomValidity($translate.instant(tip));
        } else {
            document.getElementById(i).setCustomValidity("");
        }
    }

    $rootScope.habit = {};
    $rootScope.gameTransfer = "";//全局game
    $rootScope.siteTransfer = "";//全局site
    /*
     绑定select，change事件
     */
    $rootScope.resourceGameListChange = function (id, code) {
        $rootScope.gameModelCode = code;
        $rootScope.gameTransfer = id;
        $rootScope.habit.gameTransfer = $rootScope.gameTransfer;
        $rootScope.habit.siteTransfer = $rootScope.siteTransfer;
        var habitJson = JSON.stringify($rootScope.habit);
        userFnService.habitEdit(habitJson);
        $route.reload();//刷新路由，view
    };
    $rootScope.resourceSiteListChange = function (id, code) {
        $rootScope.siteModelCode = code;
        $rootScope.siteTransfer = id;
        $rootScope.habit.gameTransfer = $rootScope.gameTransfer;
        $rootScope.habit.siteTransfer = $rootScope.siteTransfer;
        var habitJson = JSON.stringify($rootScope.habit);
        userFnService.habitEdit(habitJson);
        $route.reload();
    };

    /**
     * 个人中心
     */
    $rootScope.profileJump = function () {
        $location.path("/profile/view");
    };


    console.log(loginChk);
    if (loginChk == "login") {
        $location.path('/homePage/view');//跳转首页
    }
    /**
     * 判断是否是第一次登录
     */
    if (loginChk == "login") {
        if (habit != null) {
            $rootScope.conGame = habit.gameTransfer;
            $rootScope.conSite = habit.siteTransfer;
        }
        $("#batchModalForAdd").modal('show');
        loginChk = "homePage";
    }

    /**
     * 登录选择game，change事件监听
     * @param conGame
     */
    $rootScope.conGameChange = function (conGame) {
        $rootScope.conGame = conGame;
    }

    /**
     * 登录选择site，change事件监听
     * @param conGame
     */
    $rootScope.conSiteChange = function (conSite) {
        $rootScope.conSite = conSite;
    }

    /**
     * 确认配置项
     */
    $rootScope.configurationForms = function () {
        //重置抬头显示
        for (var i = 0; i < $rootScope.siteList.length; i++) {
            if ($rootScope.conSite == $rootScope.siteList[i].id) {
                $rootScope.siteModelCode = $rootScope.siteList[i].code;
            }
        }
        for (var i = 0; i < $rootScope.gameList.length; i++) {
            if ($rootScope.conGame == $rootScope.gameList[i].id) {
                $rootScope.gameModelCode = $rootScope.gameList[i].code;
            }
        }


        $rootScope.habit.gameTransfer = $rootScope.conGame;
        $rootScope.habit.siteTransfer = $rootScope.conSite;
        $rootScope.gameModel = $rootScope.conGame;
        $rootScope.siteModel = $rootScope.conSite;
        $rootScope.gameTransfer = $rootScope.conGame;
        $rootScope.siteTransfer = $rootScope.conSite;
        var habitJson = JSON.stringify($rootScope.habit);
        userFnService.habitEdit(habitJson);
        $("#batchModalForAdd").modal('hide');
        //$location.path('/homePage/view');
    }

    $rootScope.loginCs = loginChk;

    /*
     转换日期工具，time是毫秒数。format格式。
     */
    $rootScope.format = function (time, format) {
        var t = new Date(time);
        var tf = function (i) {
            return (i < 10 ? '0' : '') + i
        };
        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
            switch (a) {
                case 'yyyy':
                    return tf(t.getFullYear());
                    break;
                case 'MM':
                    return tf(t.getMonth() + 1);
                    break;
                case 'mm':
                    return tf(t.getMinutes());
                    break;
                case 'dd':
                    return tf(t.getDate());
                    break;
                case 'HH':
                    return tf(t.getHours());
                    break;
                case 'ss':
                    return tf(t.getSeconds());
                    break;
            }
        })
    }
});

//是否可用
adminApp.filter('NumToStr', function () {
    return function (num) {
        switch (num) {
            case 1:
                return "load.numToStrAvailable";
                break;
            case 0:
                return "load.numToStrUnavailable";
                break;
        }
    };
});

//打开关闭
adminApp.filter('NumToStr2', function () {
    return function (num) {
        switch (num) {
            case 1:
                return "load.Open";
                break;
            case 2:
                return "load.Close";
                break;
        }
    };
});

//游戏渠道
adminApp.filter('NumToStr3', function () {
    return function (num) {
        switch (num) {
            case 'game':
                return "load.resGame";
                break;
            case 'site':
                return "load.resSite";
                break;
        }

    };
});

//服务器状态
adminApp.filter('NumToStr4', function () {
    return function (num) {
        switch (num) {
            case 1:
                return "load.Normal";
                break;
            case 2:
                return "load.Maintaining";
                break;
        }
    };
});

//负载状态
adminApp.filter('NumToStr5', function () {
    return function (num) {
        switch (num) {
            case 1:
                return "load.loadStatusFree";
                break;
            case 2:
                return "load.loadStatusFull";
                break;
            case 3:
                return "load.loadStatusFluency";
                break;
            case 4:
                return "load.loadStatusBusy";
                break;
        }
    };
});

//服务器标记、
adminApp.filter('NumToStr6', function () {
    return function (num) {
        switch (num) {
            case 1:
                return "load.areaTagNewArea";
                break;
            case 2:
                return "load.areaTagHot";
                break;
            case 3:
                return "load.areaTagRecommend";
                break;
        }
    };
});

//游戏
adminApp.filter('NumToStr7', function () {
    return function (num) {
        switch (num) {
            case 1:
                return "game.tol";
                break;
            case 21:
                return "game.jokes"
                break;
        }
    };
});

//物品类型、
adminApp.filter('NumToStr8', function () {
    return function (num) {
        switch (num) {
            case 1:
                return "load.itemTypeToys";
                break;
            case 2:
                return "load.itemTypeEquip";
                break;
            case 3:
                return "load.itemTypeitem";
                break;
            case 4:
                return "load.itemTypegem";
                break;
            case 5:
                return "load.itemTypegoods";
                break;
        }

    };
});

//渠道
adminApp.filter('NumToStr9', function () {
    return function (num) {
        switch (num) {
            case 2:
                return "site.mobile";
                break;
            case 3:
                return "site.pc";
                break;
            case 4:
                return "site.web";
                break;
            case 5:
                return "site.tv";
                break;
            case 6:
                return "site.mobile.android";
                break;
            case 7:
                return "site.mobile.ios";
                break;
            case 8:
                return "site.mobile.wp";
                break;
            case 9:
                return "site.tv.letv";
                break;
            case 10:
                return "site.mobile.android.letv";
                break;
            case 11:
                return "site.group.letv";
                break;
            case 12:
                return "site.pc.test";
                break;
            case 13:
                return "site.pc.ps";
                break;
            case 14:
                return "site.pc.xbox";
                break;
            case 15:
                return "site.pc.ffd";
                break;
        }

    };
});

//邮件类型
adminApp.filter('NumToStr10', function () {
    return function (num) {
        switch (num) {
            case 1:
                return "load.ActivityReward";
                break;
            case 2:
                return "load.IndividualCompensation";
                break;
            case 3:
                return "load.InternalApplication";
                break;
            case 4:
                return "load.RechargeCompensation";
                break;
            case 5:
                return "load.Other";
                break;
        }
    };
});

//发送类型
adminApp.filter('NumToStr11', function () {
    return function (num) {
        switch (num) {
            case 1:
                return "load.PersonalRule";
                break;
            case 2:
                return "load.AllRule";
                break;

        }

    };
});

//发送状态
adminApp.filter('NumToStr12', function () {
    return function (num) {
        switch (num) {
            case 1:
                return "load.Unsent";
                break;
            case 2:
                return "load.sending";
                break;
            case 3:
                return "load.sendSuccess";
                break;
            case 4:
                return "load.sendFailed";
                break;
        }
    };
});

//审批状态
adminApp.filter('NumToStr13', function () {
    return function (num) {
        switch (num) {
            case 1:
                return "load.NotApply";
                break;
            case 2:
                return "load.NotApprove";
                break;
            case 3:
                return "load.ApprovalMessagesBodyToSuccess";
                break;
            case 4:
                return "load.ApprovalMessagesBodyToFailure";
                break;
        }

    };
});

//公告类型
adminApp.filter('NumToStr14', function () {
    return function (num) {
        switch (num) {
            case 1:
                return "load.noticePopup";
                break;
            case 2:
                return "load.noticeRoll";
                break;
            case 3:
                return "load.noticeSystem";
                break;
        }
    };
});

//绑定状态
adminApp.filter('NumToStr15', function () {
    return function (num) {
        switch (num) {
            case 1:
                return "load.UNBIND";
                break;
            case 2:
                return "load.BINDING";
                break;
            case 3:
                return "load.BOUND";
                break;
            case 4:
                return "load.INVALID";
                break;
        }
    };
});

//商品类型
adminApp.filter('NumToStr16', function () {
    return function (num) {
        switch (num) {
            case 1:
                return "load.itemTypeToys";
                break;
            case 2:
                return "load.itemTypeEquip";
                break;
            case 3:
                return "load.itemTypeitem";
                break;
            case 4:
                return "load.itemTypegem";
                break;
            case 5:
                return "load.itemTypegoods";
                break;
        }
    };
});

//活动打开关闭
adminApp.filter('NumToStr17', function () {
    return function (num) {
        switch (num) {
            case 1:
                return "load.Open";
                break;
            case 2:
                return "load.Close";
                break;
        }
    };
});

//手机系统
adminApp.filter('NumToStr18', function () {
    return function (num) {
        switch (num) {
            case 1:
                return "load.versionAndroid";
                break;
            case 2:
                return "load.versionIos";
                break;
        }
    };
});

//有效状态（版本更新）
adminApp.filter('NumToStr19', function () {
    return function (num) {
        switch (num) {
            case 1:
                return "load.versionValid";
                break;
            case 2:
                return "load.versionUnValid";
                break;
            case 3:
                return "load.versionRemoved";
                break;
        }
    };
});

//是或否
adminApp.filter('NumToStr20', function () {
    return function (num) {
        switch (num) {
            case 2:
                return "load.giftBagNo";
                break;
            case 1:
                return "load.giftBagYes";
                break;
        }
    };
});