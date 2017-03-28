<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html lang="en" ng-app="adminApp">
<head>
    <script type="text/javascript">
        var ctx = "${ctx}";
        <shiro:user>
        var principal = "<shiro:principal/>";
        </shiro:user>

        var loginChk = "<%=request.getSession().getAttribute("login")%>";//登录状态session
        var habit =<%=request.getSession().getAttribute("habit")%>;

        /**
         * 切换菜单
         * @param chevron
         */
        function chevronClick(chevron) {
            if ($(chevron).attr('class') == "fa fa-chevron-down") {
                $(chevron).attr('class', 'fa fa-chevron-up');
                $(".searchCriteria").hide();
            } else if ($(chevron).attr('class') == "fa fa-chevron-up") {
                $(chevron).attr('class', 'fa fa-chevron-down');
                $(".searchCriteria").show();
            }

        }

        //菜单栏显示隐藏
        function menuBarClick() {
            if ($(".menuBar").attr('class') == "menuBar sticky-header left-side-collapsed") {
                $(".menuBar").attr('class', 'menuBar sticky-header');
            } else if ($(".menuBar").attr('class') == "menuBar sticky-header") {
                $(".menuBar").attr('class', 'menuBar sticky-header left-side-collapsed');
            }
        }


        ////        function menuMouseOver(menu) {
        ////            $(menu).addClass("nav-hover");
        ////        }
        //
        //        function menuMouseOut(menu) {
        //            $(menu).removeClass("nav-hover");
        //        }
        //
        //        function menuClick(menu) {
        //            if ($(menu).attr("class").indexOf("nav-hover") > 0) {
        //                $(menu).removeClass("nav-hover");
        //            } else {
        //                $(menu).addClass("nav-hover");
        //            }
        //            if ($(menu).attr("class").indexOf("nav-active") > 0) {
        //                $(menu).removeClass("nav-active");
        //            } else {
        //                $(menu).addClass("nav-active");
        //            }
        //        }
        /* function checkVerify(i,tip){
         if(i.validity.patternMismatch===true){
         i.setCustomValidity(tip);
         }else{
         i.setCustomValidity("");
         }
         }*/
    </script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="keywords"
          content="admin, dashboard, bootstrap, template, flat, modern, theme, responsive, fluid, retina, backend, html5, css, css3">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="#" type="image/png">
    <title>AdminX</title>

    <link rel="stylesheet" type="text/css" href="${ctx}/resource/adminex/js/jquery-multi-select/css/multi-select.css"/>

    <!--common-->
    <%--<link rel="stylesheet" type="text/css" href="${ctx}/resource/adminex/css/bootstrap-datepicker/css/datepicker-custom.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/adminex/css/bootstrap-timepicker/css/timepicker.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/adminex/css/bootstrap-colorpicker/css/colorpicker.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/adminex/css/bootstrap-daterangepicker/daterangepicker-bs3.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/adminex/css/bootstrap-datetimepicker/css/datetimepicker-custom.css" />--%>


    <link rel="stylesheet" type="text/css" href="${ctx}/resource/adminex/js/fuelux/css/tree-style.css"/>
    <link href="${ctx}/resource/adminex/css/style.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css"
          href="${ctx}/resource/adminex/js/DateTimePicker/css/jquery-ui-1.8.17.custom.css">
    <link rel="stylesheet" type="text/css"
          href="${ctx}/resource/adminex/js/DateTimePicker/css/jquery-ui-timepicker-addon.css">


    <link href="${ctx}/resource/adminex/css/style-responsive.css" rel="stylesheet">
    <link href="${ctx}/resource/adminex/css/ng-pagination.css" rel="stylesheet">
    <link href="${ctx}/resource/adminex/css/DT_bootstrap.css" rel="stylesheet">
    <link href="${ctx}/resource/adminex/css/jquery.stepy.css" rel="stylesheet">
    <link href="${ctx}/resource/adminex/js/iCheck/skins/square/square.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/adminex/js/gritter/css/jquery.gritter.css"/>

    <link rel="stylesheet" type="text/css" href="${ctx}/resource/adminex/js/DateTimePicker/jedate/skin/gray.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/adminex/js/DateTimePicker/jedate/skin/green.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/adminex/js/DateTimePicker/jedate/skin/jedate.css">

    <style>
        .labelSpace label {
            margin-bottom: 5px;
        }
    </style>
</head>
<body>

<div class="menuBar sticky-header">
    <section ng-controller="centerController">

        <!-- left side start-->
        <div class="left-side sticky-left-side">

            <!--logo and iconic logo start-->
            <div class="logo">
                <a href="index.html"><%--<img src="images/logo.png" alt="">--%></a>
            </div>

            <div class="logo-icon text-center">
                <a href="index.html"><%--<img src="images/logo_icon.png" alt="">--%></a>
            </div>
            <!--logo and iconic logo end-->

            <div class="left-side-inner">
                <!-- visible to small devices only -->
                <%--<div class="visible-xs hidden-sm hidden-md hidden-lg">--%>
                    <%--<div class="media logged-user">--%>
                        <%--&lt;%&ndash;<img alt="" src="images/photos/user-avatar.png" class="media-object">&ndash;%&gt;--%>
                        <%--<div class="media-body">--%>
                            <%--<h4><a href="#">John Doe</a></h4>--%>
                            <%--<span>"Hello There..."</span>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<h5 class="left-nav-title">Account Information</h5>--%>
                    <%--<ul class="nav nav-pills nav-stacked custom-nav">--%>
                        <%--<li><a href="${ctx}/system/user/profile"><i class="fa fa-user"></i> <span>Profile</span></a>--%>
                        <%--</li>--%>
                        <%--<li><a href="#"><i class="fa fa-cog"></i> <span>Settings</span></a></li>--%>
                        <%--<li><a href="${ctx}/system/logout"><i class="fa fa-sign-out"></i> <span>Sign Out</span></a></li>--%>
                    <%--</ul>--%>
                <%--</div>--%>

                <div>
                    <!--tree start-->
                    <ul class="nav nav-pills nav-stacked custom-nav">
                        <li><a href="javascript:" ng-click="skipPageBar('/homePage/view')"><i class="fa fa-home"></i> <span
                                translate="load.HomePage"></span></a></li>
                        <li class="menu-list {{root.menuName}}{{root.id}}"
                            ng-repeat="root in menu.childrenList">
                            <a href="" ng-click="menuListClicked(root.menuName,root.id,menu.childrenList)"><i
                                    class="fa {{root.menuIcon}}"></i>
                                <span translate="{{root.menuName}}"></span></a>
                            <ul class="sub-menu-list">
                                <li><a href="javascript:" ng-repeat="seed in root.childrenList"
                                       id="menu{{seed.menuName}}{{seed.id}}" ng-click="skipPage(seed)"><span
                                        translate="{{seed.menuName}}"></span></a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!--sidebar nav end-->
            </div>
        </div>
        <!-- left side end-->

        <!-- main content start-->
        <div class="main-content">

            <!-- header section start-->
            <div class="header-section">


                <!--toggle button start-->
                <a class="toggle-btn" onclick="menuBarClick()"><i class="fa fa-bars"></i></a>
                <!--toggle button end-->

                <!--search start-->
                <form class="searchform">
                    <input type="text" class="form-control" ng-model="menuSearchModel"
                           ng-keyup="myKeyUp($event,menuSearchModel)" placeholder={{SearchTips|translate}}>
                </form>
                <!--search end-->

                <!--notification menu start -->

                <div class="menu-right">


                    <ul class="notification-menu">
                        <li>

                            <a class="btn btn-default dropdown-toggle info-number" data-toggle="dropdown">
                                <span translate="load.Game"></span><span style="color: #00B83F;"
                                                                         translate="{{gameModelCode}}"></span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-head pull-right">
                                <h5 class="title" translate="load.gameTitle"></h5>
                                <ul class="dropdown-list user-list">
                                    <li class="new" style="cursor:pointer;"
                                        ng-click="resourceGameListChange('','load.OnChangeTip')">
                                        <span translate="load.OnChangeTip"></span>
                                    </li>
                                    <li class="new" style="cursor:pointer;"
                                        ng-click="resourceGameListChange(game.id,game.code)"
                                        ng-repeat="game in gameList">
                                        <span translate="{{game.code}}"></span>
                                    </li>
                                </ul>
                            </div>
                            <%--<div>--%>
                            <%--<span style="float: left;margin-top: 10px;"><label translate="load.Game"></label></span>--%>
                            <%--<span style="float: left;">--%>
                            <%--&lt;%&ndash; <select name="gameList" aria-controls="editable-sample" class="form-control gameList" >--%>
                            <%--<option ng-repeat="game in gameList" value="game.id" ng-click="resourceListChange(game.id)">{{game.code}}</option>--%>
                            <%--</select>&ndash;%&gt;--%>
                            <%--<select name="gameList" aria-controls="editable-sample"--%>
                            <%--class="form-control gameList"--%>
                            <%--ng-options="game.id as game.code|translate for game in gameList"--%>
                            <%--ng-change="resourceGameListChange(gameModel)" ng-model="gameModel">--%>
                            <%--<option value="" translate="load.OnChangeTip"></option>--%>
                            <%--</select>--%>
                            <%--&lt;%&ndash; <select name="gameList" aria-controls="editable-sample" class="form-control gameList" ng-model="gameModel" ng-change="resourceGameListChange(gameModel)"  required>--%>
                            <%--<option value="" translate="load.OnChangeTip"></option>--%>
                            <%--<option value={{game.id}} ng-repeat="game in gameList" translate={{game.code}}></option>--%>
                            <%--</select>&ndash;%&gt;--%>
                            <%--</span>--%>


                            <%--</div>--%>

                        </li>
                        <li>
                            <a class="btn btn-default dropdown-toggle info-number" data-toggle="dropdown">
                                <span translate="load.Site"></span><span style="color: #00B83F;"
                                                                         translate="{{siteModelCode}}"></span>
                            </a>

                            <div class="dropdown-menu dropdown-menu-head pull-right">
                                <h5 class="title" translate="load.siteTitle"></h5>
                                <ul class="dropdown-list user-list">
                                    <li class="new" style="cursor:pointer;"
                                        ng-click="resourceSiteListChange('','load.OnChangeTip')">
                                        <span translate="load.OnChangeTip"></span>
                                    </li>
                                    <li class="new" style="cursor:pointer;"
                                        ng-click="resourceSiteListChange(site.id,site.code)"
                                        ng-repeat="site in siteList">
                                        <span translate="{{site.code}}"></span>
                                    </li>
                                </ul>
                            </div>
                            <%--<div>--%>
                            <%--<span style="float: left;margin-top: 10px;"><label translate="load.Site"></label></span>--%>
                            <%--<span style="float: left;">--%>
                            <%--&lt;%&ndash;<select name="siteList" aria-controls="editable-sample" class="form-control siteList"  ng-change="resourceListChange(siteList)"  ng-model="siteList" >--%>
                            <%--<option ng-repeat="site in siteList" value="site.id" >{{site.code}}</option>--%>
                            <%--</select>&ndash;%&gt;--%>
                            <%--<select name="siteList" aria-controls="editable-sample"--%>
                            <%--class="form-control siteList"--%>
                            <%--ng-options="site.id as site.code|translate for site in siteList"--%>
                            <%--ng-change="resourceSiteListChange(siteModel)" ng-model="siteModel">--%>
                            <%--<option value="" translate="load.OnChangeTip"></option>--%>
                            <%--</select>--%>

                            <%--&lt;%&ndash;   <select name="siteList" aria-controls="editable-sample" class="form-control siteList" ng-model="siteModel" ng-change="resourceSiteListChange(siteModel)"  required>--%>
                            <%--<option value="" translate="load.OnChangeTip"></option>--%>
                            <%--<option value={{site.id}} ng-repeat="site in siteList" translate="site.{{site.code}}"></option>--%>
                            <%--</select>&ndash;%&gt;--%>
                            <%--</span>--%>
                            <%--</div>--%>
                        </li>
                        <li>

                            <a class="btn btn-default dropdown-toggle info-number" data-toggle="dropdown">
                                <span translate="load.LANGUAGE"></span><span style="color: #00B83F;"
                                                                             translate="{{langKey}}"></span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-head pull-right">
                                <h5 class="title" translate="load.LANGUAGEClick"></h5>
                                <ul class="dropdown-list user-list">
                                    <li class="new" style="cursor:pointer;" ng-click="setLang('default')">
                                        <span translate="load.DefaultLanguage"></span>
                                    </li>
                                    <li class="new" style="cursor:pointer;" ng-click="setLang('en_US')">
                                        <span translate="load.English"></span>
                                    </li>
                                    <li class="new" style="cursor:pointer;" ng-click="setLang('zh_ZH')">
                                        <span translate="load.Chinese"></span>
                                    </li>
                                </ul>
                            </div>
                            <%--<div>--%>
                            <%--<span style="float: left;margin-top: 10px;"><label--%>
                            <%--translate="load.LANGUAGE"></label></span>--%>
                            <%--<span style="float: left;">--%>
                            <%--&lt;%&ndash; <select name="gameList" aria-controls="editable-sample" class="form-control gameList" >--%>
                            <%--<option ng-repeat="game in gameList" value="game.id" ng-click="resourceListChange(game.id)">{{game.code}}</option>--%>
                            <%--</select>&ndash;%&gt;--%>
                            <%--<select name="gameList" aria-controls="editable-sample"--%>
                            <%--class="form-control gameList" ng-change="setLang(langKey)"--%>
                            <%--ng-model="langKey">--%>
                            <%--<option value="" translate="load.DefaultLanguage"></option>--%>
                            <%--<option value="en_US" translate="load.English"></option>--%>
                            <%--<option value="zh_ZH" translate="load.Chinese"></option>--%>
                            <%--</select>--%>
                            <%--</span>--%>
                            <%--</div>--%>
                        </li>
                        <li>
                            <a class="btn btn-default dropdown-toggle info-number" data-toggle="dropdown">
                                <i class="fa fa-tasks"></i>
                                <span class="badge">{{itemPlan.sumCount}}</span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-head pull-right">
                                <h5 class="title" translate="load.taskTitle"></h5>
                                <ul class="dropdown-list user-list">
                                    <li class="new">
                                        <a ng-click="disposePlanClick()" style="cursor:pointer;">
                                            <div class="task-info">
                                                <span translate="load.taskItemApplyFor"
                                                      style="color: #00a0e9;font-weight:bold"></span>
                                            </div>
                                            <div>
                                                <span style="float: left" translate="load.taskApplyForCount"></span>
                                                <span class="badge" style="float: left;margin-top: -1px;">{{itemPlan.applyForCount}}</span>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="new">
                                        <a ng-click="disposePlanApprovalClick()" style="cursor:pointer;">
                                            <div class="task-info">
                                                <span translate="load.taskItemApproval"
                                                      style="color: #00a0e9;font-weight:bold"></span>
                                            </div>
                                            <div>
                                                <span style="float: left" translate="load.taskUnapprovedCount"></span>
                                                <span class="badge" style="float: left;margin-top: -1px;">{{itemPlan.unapprovedCount}}</span>
                                            </div>
                                        </a>
                                    </li>

                                </ul>
                            </div>
                        </li>
                        <li>
                            <a class="btn btn-default dropdown-toggle info-number" data-toggle="dropdown">
                                <i class="fa fa-envelope-o"></i>
                                <span class="badge">5</span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-head pull-right">
                                <h5 class="title">You have 5 Mails </h5>
                                <ul class="dropdown-list normal-list">
                                    <li class="new">
                                        <a href="">
                                            <span class="thumb"><%--<img src="images/photos/user1.png" alt="" />--%></span>
                                        <span class="desc">
                                          <span class="name">John Doe <span
                                                  class="badge badge-success">new</span></span>
                                          <span class="msg">Lorem ipsum dolor sit amet...</span>
                                        </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="">
                                            <span class="thumb"><%--<img src="images/photos/user2.png" alt="" />--%></span>
                                        <span class="desc">
                                          <span class="name">Jonathan Smith</span>
                                          <span class="name">Jonathan Smith</span>
                                          <span class="msg">Lorem ipsum dolor sit amet...</span>
                                        </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="">
                                            <span class="thumb"><%--<img src="images/photos/user3.png" alt="" />--%></span>
                                        <span class="desc">
                                          <span class="name">Jane Doe</span>
                                          <span class="msg">Lorem ipsum dolor sit amet...</span>
                                        </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="">
                                            <span class="thumb"><%--<img src="images/photos/user4.png" alt="" />--%></span>
                                        <span class="desc">
                                          <span class="name">Mark Henry</span>
                                          <span class="msg">Lorem ipsum dolor sit amet...</span>
                                        </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="">
                                            <span class="thumb"><%--<img src="images/photos/user5.png" alt="" />--%></span>
                                        <span class="desc">
                                          <span class="name">Jim Doe</span>
                                          <span class="msg">Lorem ipsum dolor sit amet...</span>
                                        </span>
                                        </a>
                                    </li>
                                    <li class="new"><a href="">Read All Mails</a></li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <a class="btn btn-default dropdown-toggle info-number" data-toggle="dropdown">
                                <i class="fa fa-bell-o"></i>
                                <span class="badge">4</span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-head pull-right">
                                <h5 class="title">Notifications</h5>
                                <ul class="dropdown-list normal-list">
                                    <li class="new">
                                        <a href="">
                                            <span class="label label-danger"><i class="fa fa-bolt"></i></span>
                                            <span class="name">Server #1 overloaded.  </span>
                                            <em class="small">34 mins</em>
                                        </a>
                                    </li>
                                    <li class="new">
                                        <a href="">
                                            <span class="label label-danger"><i class="fa fa-bolt"></i></span>
                                            <span class="name">Server #3 overloaded.  </span>
                                            <em class="small">1 hrs</em>
                                        </a>
                                    </li>
                                    <li class="new">
                                        <a href="">
                                            <span class="label label-danger"><i class="fa fa-bolt"></i></span>
                                            <span class="name">Server #5 overloaded.  </span>
                                            <em class="small">4 hrs</em>
                                        </a>
                                    </li>
                                    <li class="new">
                                        <a href="">
                                            <span class="label label-danger"><i class="fa fa-bolt"></i></span>
                                            <span class="name">Server #31 overloaded.  </span>
                                            <em class="small">4 hrs</em>
                                        </a>
                                    </li>
                                    <li class="new"><a href="">See All Notifications</a></li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <a class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                <%-- <img src="images/photos/user-avatar.png" alt="" />--%>
                                <shiro:user>
                                    <div translate="load.WELCOME"></div>
                                    [<shiro:principal/>]
                                </shiro:user>
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-usermenu pull-right">
                                <li style=""><a style="cursor:pointer;" ng-click="profileJump()"><i class="fa fa-user"></i> Profile</a></li>
                                <li><a href="#"><i class="fa fa-cog"></i> Settings</a></li>
                                <li><a href="${ctx}/system/logout" class="systemLogout"><i class="fa fa-sign-out"></i>
                                    Log Out</a></li>
                            </ul>
                        </li>

                    </ul>
                </div>
                <!--notification menu end -->

            </div>
            <!-- header section end-->

            <%-- <div ui-view></div>--%>
            <section class="wrapper">
                <div class="page-heading">
                    <h3>
                        <span translate={{menuBarData.menuBarTitle}}></span>
                    </h3>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <!--breadcrumbs start -->
                        <ul class="breadcrumb panel">
                            <li><a ng-click="skipPageBar('/homePage/view')" style="cursor:pointer;"><i
                                    class="fa fa-home"></i> <span
                                    translate="load.HomePage"></span></a></li>
                            <li ng-hide="menuBarData.menuBarOneName==''"><span
                                    translate={{menuBarData.menuBarOneName}}></span></li>
                            <li ng-hide="menuBarData.menuBarTwoName==''"><a
                                    ng-click="skipPageBar(menuBarData.menuBarTwoUrl)" style="cursor:pointer;"><span
                                    translate={{menuBarData.menuBarTwoName}}></span></a></li>
                            <li class="active" ng-hide="menuBarData.menuBarThreeName==''"><span
                                    translate={{menuBarData.menuBarThreeName}}></span></li>
                        </ul>
                        <!--breadcrumbs end -->
                    </div>
                </div>

                <div ng-view></div>
            </section>
            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                 id="batchModalForAdd" data-backdrop="static" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title"><span translate="load.configuration"></span></h4>
                        </div>
                        <div class="modal-body">

                            <form role="form" ng-submit="configurationForms()" name="configurationForm">
                                <div class="form-group">
                                    <div class="form-group labelSpace">
                                        <label><span translate="load.Game"></span>:<span
                                                style="color: red">*</span></label>
                                        <select name="gameList" aria-controls="editable-sample"
                                                class="form-control gameList"
                                                ng-options="game.id as game.code|translate for game in gameList"
                                                ng-model="conGame" ng-change="conGameChange(conGame)" required>
                                            <option value="" translate="load.OnChangeTip"></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group labelSpace">
                                    <label><span translate="load.Site"></span></label>
                                    <select name="siteList" aria-controls="editable-sample"
                                            class="form-control siteList"
                                            ng-options="site.id as site.code|translate for site in siteList"
                                            ng-model="conSite" ng-change="conSiteChange(conSite)">
                                        <option value="" translate="load.OnChangeTip"></option>
                                    </select>
                                </div>

                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary addSubmit"><span
                                            translate="load.Ok"></span></button>
                                    <p ng-hide="!configurationForms.$invalid" style="color: red;margin-top: 15px;">
                                        <span translate="load.remindValidate"></span></p>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- main content end-->
    </section>
</div>
<!-- 基础js，initjs-->
<script src="${ctx}/resource/adminex/js/BasicFileImport.js"></script>
<script src="${ctx}/resource/adminex/js/ProjectFileImport.js"></script>


<!-- 基础js end-->


</body>
</html>