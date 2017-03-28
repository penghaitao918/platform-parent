<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resource/adminex/js/multi-select-init.js"></script>
<script src="${ctx}/resource/adminex/js/icheck-init.js"></script>
<style>

    .chk_1 {
        display: none;
    }

    .chk_1 + label {
        background-color: #FFF;
        border: 1px solid #C1CACA;
        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05);
        padding: 7px;
        border-radius: 5px;
        display: inline-block;
        position: relative;
        margin-right: -20px;
    }
    .chk_1 + label:active {
        box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px 1px 3px rgba(0,0,0,0.1);
    }

    .chk_1:checked + label {
        background-color: #ECF2F7;
        border: 1px solid #92A1AC;
        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05), inset 15px 10px -12px rgba(255, 255, 255, 0.1);
        color: #243441;
    }

    .chk_1:checked + label:after {
        content: '\2714'; //勾选符号
    position: absolute;
        top: 0px;
        left: 0px;
        color: red;
        width: 100%;
        text-align: center;
        font-size: 1.4em;
        padding: 1px 0 0 0;
        vertical-align: text-top;
    }

</style>
<section class="wrapper">

        <div class="row">
            <div class="col-sm-6">
                <section class="panel">
                    <header class="panel-heading">
                        奖励包
                <span class="tools pull-right">
                    <a href="javascript:;" class="fa fa-chevron-down"></a>
                     <a href="javascript:;" class="fa fa-times"></a>
                </span>
                    </header>
                    <div class="panel-body" ng-controller="awardPackageController">
                        <div class="clearfix">
                            <div class="btn-group" style="margin-top: 25px;">
                                <a  ng-click="addButton()"
                                    class="btn btn-primary">添加&nbsp<i class="fa fa-plus"></i></a>
                                <a class="btn btn-primary" ng-click="delete()">批量删除</a>
                                <div style="float: left;margin-left: 970px;">
                                    <form class="searchform"  method="post">
                                        <select name="awardType" aria-controls="editable-sample" class="form-control awardType" ng-model="awardType" ng-change="awardChange(awardType)" required>
                                            <option value="">请选择奖励类型</option>
                                            <option value="1">新手奖励</option>
                                            <option value="2">回归礼包</option>
                                        </select>
                                    </form>
                                </div>
                            </div>

                            <div style="float: right;margin-top: 20px;">
                                <form class="searchform"  method="post">
                                    <input type="text" class="form-control searchText" name="keyword" placeholder="Search here..." ng-model="awardSearch" />
                                    <div style="float: left;margin-top: 7px;">
                                        <button ng-click="searchAwardClicked()" class="btn btn-primary searchButton" >搜索</button>
                                    </div>

                                </form>
                            </div>
                        </div>
                        <form class="form-horizontal bucket-form"  ng-submit="awardForm()" >
                            <table class="table">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="checkbox"
                                               data-ng-click="awardCheckClick()" value=""></th>
                                    <th>awardId</th>
                                    <th>awardCount</th>
                                    <th>awardType</th>
                                    <th>awardName</th>
                                    <th>awardDesc</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr ng-repeat="award in awardPackage">
                                    <td align="center"><input type="checkbox" class="checkbox awardCheck"
                                                              data-ng-click="check($index,chk)"data-ng-model="chk" value={{award.awardId}} name={{award.awardName}}></td>
                                    <td>{{award.awardId}}</td>
                                    <td>{{award.awardCount}}</td>
                                    <td>{{award.awardType}}</td>
                                    <td>{{award.awardName}}</td>
                                    <td>{{award.awardDesc}}</td>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <div class="pager">
                                <pager page-count="pageAwardCount" current-page="currentAwardPage" on-page-change="onAwardPageChange()" first-text="load.HomePage" next-text="load.NextPage" prev-text="load.PreviousPage" last-text="load.EndPage" show-goto="true" goto-text="load.GoToPage"></pager>
                            </div>
                        </form>


                </section>
            </div>
        </div>
</div>
</section>


