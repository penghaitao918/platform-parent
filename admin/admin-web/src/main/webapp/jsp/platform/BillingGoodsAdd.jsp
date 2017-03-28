<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resource/adminex/js/DateTimePicker/DateInit.js"></script>
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
    <div class="panel-body" ng-controller="billingGoodsAddController">
        <form role="form" ng-submit="billingGoodsAddForm()" name="billingGoodsAddForm">
    <div class="row">
        <div class="col-sm-6">
            <section class="panel">
                <header class="panel-heading">
                    <span translate="load.basicInfo"></span>
                </header>
                <div class="panel-body" >
                    <div class="row">
                    <div class="col-md-3 form-group labelSpace">
                        <label><span translate="load.goodsGoodsType"></span>:</label>
                        <select name="goodsType" aria-controls="editable-sample" class="form-control goodsType" ng-model="goodsData.goodsType" required>
                                <option value="">请选择</option>
                                <option value="1">道具</option>
                                <option value="2">装备</option>
                        </select>
                    </div>

                    <div class="col-md-3 form-group labelSpace">
                        <label><span translate="load.goodsGoodsName"></span>:</label>
                        <input  type="text"   class="form-control goodsName" name="goodsName" ng-model="goodsData.goodsName" maxlength="20" required alphanum>
                        <span ng-show="billingGoodsAddForm.goodsName.$error.alphanum" style="color: red;"><span translate="load.lettersAndInteger"></span></span>
                    </div>

                    <div class="col-md-3 form-group labelSpace">
                        <label><span translate="load.goodsGoodsDesc"></span>:</label>
                        <input  type="text"   class="form-control goodsDesc" ng-model="goodsData.goodsDesc" maxlength="20" required>
                    </div>

                    <div class="col-md-3 form-group labelSpace">
                        <label><span translate="load.goodsGoodsPic"></span>:</label>
                        <input type="text" class="form-control goodsPic"  ng-model="goodsData.goodsPic" maxlength="20">
                    </div>
                    </div>

                    <div class="row">
                        <div class="col-md-3 form-group labelSpace">
                            <label><span translate="load.goodsGoodsAmount"></span>:</label>
                            <input type="number" class="form-control goodsAmount" name="goodsAmount"  ng-model="goodsData.goodsAmount" maxlength="20" min="0" max="100000000000" integer required>
                            <span ng-show="billingGoodsAddForm.goodsAmount.$error.integer" style="color: red;"><span translate="load.numLength"></span></span>
                            <span ng-show="billingGoodsAddForm.goodsAmount.$error.min || billingGoodsAddForm.goodsAmount.$error.max" style="color: red;">
                                    <span translate="load.numBetween"></span>
                            </span>
                        </div>

                        <div class="col-md-3 form-group labelSpace">
                            <label><span translate="load.goodsGoodsCount"></span>:</label>
                            <input type="text" class="form-control goodsCount" name="goodsCount"  ng-model="goodsData.goodsCount" maxlength="20" min="0" max="100000000000" required>
                            <span ng-show="billingGoodsAddForm.goodsCount.$error.integer" style="color: red;"><span translate="load.numLength"></span></span>
                            <span ng-show="billingGoodsAddForm.goodsCount.$error.min || billingGoodsAddForm.goodsCount.$error.max" style="color: red;">
                                    <span translate="load.numBetween"></span>
                            </span>
                        </div>

                        <div class="col-md-3 form-group labelSpace">
                            <label><span translate="load.goodsUnitPrice"></span>:</label>
                            <input type="text" class="form-control unitPrice"  ng-model="goodsData.unitPrice" maxlength="20" min="0" max="100000000000" required>
                            <span ng-show="billingGoodsAddForm.unitPrice.$error.integer" style="color: red;"><span translate="load.numLength"></span></span>
                            <span ng-show="billingGoodsAddForm.unitPrice.$error.min || billingGoodsAddForm.unitPrice.$error.max" style="color: red;">
                                    <span translate="load.numBetween"></span>
                            </span>
                        </div>

                        <div class="col-md-3 form-group labelSpace">
                            <label><span translate="load.goodsGoodsCode"></span>:</label>
                            <input type="text" class="form-control goodsCode" name="goodsCode"  ng-model="goodsData.goodsCode" maxlength="20"  alphanum required>
                            <span ng-show="billingGoodsAddForm.goodsCode.$error.alphanum" style="color: red;"><span translate="load.lettersAndInteger"></span></span>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-3 form-group labelSpace">
                            <label><span translate="load.goodsRelatedGoodsId"></span>:</label>
                            <input type="text" class="form-control discount" ng-model="goodsData.relatedGoodsId"  required>
                        </div>

                        <div class="col-md-3 form-group labelSpace">
                            <label><span translate="load.goodsDisaccount"></span>:</label>
                            <input type="text" class="form-control disaccount" name="disaccount"  ng-model="goodsData.disaccount" maxlength="20" alphanum required>
                            <span ng-show="billingGoodsAddForm.disaccount.$error.alphanum" style="color: red;"><span translate="load.lettersAndInteger"></span></span>
                        </div>

                        <div class="col-md-3 form-group labelSpace">
                            <label><span translate="load.goodsValidStatus"></span>:</label>
                            <select name="validStatus" aria-controls="editable-sample" class="form-control validStatus" ng-model="validStatus" required>
                                <option value="" translate="load.pleaseSelect"></option>
                                <option value="unact" translate="load.validStatusUnact"></option>
                                <option value="acting" translate="load.validStatusActing"></option>
                                <option value="locked" translate="load.validStatusLocked"></option>
                                <option value="acted" translate="load.validStatusActed"></option>
                                <option value="reject" translate="load.validStatusReject"></option>
                                <option value="error" translate="load.validStatusError"></option>
                            </select>
                        </div>

                        <div class="col-md-3 form-group labelSpace">
                            <label><span translate="load.goodsGoodsMarkTime"></span>:</label>
                            <input size="16" type="text" readonly class="ui_timepicker form-control" ng-model="goodsData.goodsMarkTime">
                        </div>
                    </div>
                </div>
            </section>
        </div>


        <div class="col-sm-6">
            <section class="panel">
                <header class="panel-heading">
                    <span translate="load.Config"></span>
                </header>

                <div class="panel-body" >
                    <div class="row">
                        <div class="col-md-4 form-group labelSpace">
                            <label><span translate="load.GameId"></span>:</label>
                        <select name="gameId" aria-controls="editable-sample" class="form-control gameId"   ng-options="game.id as game.code|translate for game in gameList"  ng-change="resourceGameListChange(gameToyGameList)"   ng-model="gameToyGameList" required>
                                <option value="" translate="load.pleaseSelect"></option>
                            </select>
                          <%--  <select name="gameId" aria-controls="editable-sample" class="form-control gameId" ng-model="gameToyGameList" ng-change="resourceGameListChange(gameToyGameList)"  required>
                                <option value="" translate="load.pleaseSelect"></option>
                                <option value={{game.id}} ng-repeat="game in gameList" ng-selected="gameSelected" translate={{game.code}}></option>
                            </select>--%>
                        </div>

                        <div class="col-md-4 form-group labelSpace">
                            <label><span translate="load.SiteId"></span>:</label>
                            <select name="siteId" aria-controls="editable-sample" class="form-control siteId"   ng-options="site.id as site.code|translate for site in siteList" ng-change="resourceSiteListChange(gameToySiteList)"  ng-model="gameToySiteList" required>
                                <option value="" translate="load.pleaseSelect"></option>
                            </select>

                           <%-- <select name="siteId" aria-controls="editable-sample" class="form-control siteId"  ng-change="resourceSiteListChange(gameToySiteList)"  required>
                                <option value="" translate="load.OnChangeTip"></option>
                                <option value={{site.id}} ng-repeat="site in siteList" ng-model="gameToySiteList" translate="site.{{site.code}}"></option>
                            </select>--%>

                           <%-- <select name="siteId" aria-controls="editable-sample" class="form-control siteId"   ng-options="site.id as site.code for site in siteList" ng-change="resourceSiteListChange(gameToySiteList)"  ng-model="gameToySiteList" required>
                                <option value="" translate="load.pleaseSelect"></option>
                            </select>--%>
                        </div>

                    </div>
                    <div class="row">
                        <aside class="col-md-3">
                            <h4 class="drg-event-title"><span translate="load.areaList"></span></h4>
                            <div class='external-events'>
                                <div class='external-event label label-primary'  data-ng-repeat="area in areaData" >{{area}}</div>
                                <br/>
                            </div>
                        </aside>
                        <button class="btn btn-warning" type="button" ng-click="areaClick()"><span translate="load.areaChoice"></span></button>
                    </div>

                    <div class="modal-footer">
                        <div style="float: right;margin-right: 20px;">
                            <button type="button" class="btn btn-default" href="javascript:" onclick="history.back(); "><span translate="load.Cancel"></span></button>
                            <button type="submit" class="btn btn-primary addSubmit" ng-disabled="billingGoodsAddForm.$invalid"> <span translate="load.Ok"></span></button>
                        </div>
                        <p ng-hide="!billingGoodsAddForm.$invalid" style="color: red;float: right;margin-top: 15px;"><span translate="load.remindValidate"></span></p>
                    </div>
                    </div>
            </section>
        </div>

       <%-- <div class="col-sm-6">
            <section class="panel">
                <header class="panel-heading">
                    奖励包设置
                </header>

                <div class="panel-body" >
                    <div class="row">
                        <aside class="col-md-3">
                            <h4 class="drg-event-title">奖励包列表</h4>
                            <div class='external-events'>
                                <div class='external-event label label-primary'  data-ng-repeat="award in awardData" >{{award}}</div>
                                <br/>
                            </div>
                        </aside>
                        <button class="btn btn-warning" type="button" ng-click="awardPackageClick()">选择奖励包</button>

                    </div>
                </div>
            </section>
        </div>

        <div class="col-sm-6">
            <section class="panel">
                <header class="panel-heading">
                    首充策略
                </header>

                <div class="panel-body" >
                    <div class="row">
                        <aside class="col-md-3">
                            <h4 class="drg-event-title">首充策略列表</h4>
                            <div class='external-events'>
                                <div class='external-event label label-primary'  data-ng-repeat="code in firstBuyNames" >{{code}}</div>
                                <br/>
                            </div>
                        </aside>
                        <button class="btn btn-warning" type="button" ng-click="firstBuyClick()">选择首充策略</button>

                    </div>

                </div>
            </section>
        </div>--%>
    </div>
        </form>
    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="channelAddModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                    <h4 class="modal-title"><span translate="load.channelChoice"></span></h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6">
                            <form class="form-horizontal bucket-form"  ng-submit="channelAdd()" >
                                <div class="form-group" style="width: 800px;">
                                    <div class="col-sm-9 icheck ">
                                        <div >
                                            <div class="checkbox" align="center">
                                                <input type="checkbox" id="all" class="chk_1" ng-model="master" ng-click="all(master,siteList)">
                                                <label for="all">全选</label>
                                            </div>

                                            <div class="checkbox" align="center">
                                                <input type="checkbox" id="versa" class="chk_1"  ng-click="versa()">
                                                <label for="versa">反选</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-9 icheck ">
                                        <div >
                                            <div class="checkbox" data-ng-repeat="site in siteList" align="center">
                                                <input type="checkbox" id={{site.id}} name={{site.code}}  ng-click="updateSelection($event,site.id,site.code)" ng-model="siteModel"  ng-checked="master" class="chk_1 children" >
                                                <label for={{site.id}}>{{site.code}}</label>
                                            </div>

                                        </div>
                                    </div>


                                </div>
                                <div class="modal-footer" style="width: 550px;">
                                    <button type="button" class="btn btn-default" data-dismiss="modal"><span translate="load.Cancel"></span></button>
                                    <button type="submit" class="btn btn-primary addSubmit"   > <span translate="load.Ok"></span></button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="areaAddModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                        <h4 class="modal-title"><span translate="load.areaChoice"></span></h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-6">
                                <form class="form-horizontal bucket-form"  ng-submit="areaAdd()" >
                                    <div class="form-group" style="width: 800px;">
                                        <div class="col-sm-9 icheck ">
                                            <div >
                                              <%--  <div class="checkbox" align="center">
                                                    <input type="checkbox" id="areaAll" class="chk_1" ng-model="areaMaster" ng-click="areaAll(areaMaster,areaLists)">
                                                    <label for="areaAll" class="allCheck">全选</label>
                                                </div>

                                                <div class="checkbox" align="center">
                                                    <input type="checkbox" id="areaVersa" class="chk_1" ng-model="areaVersaCheck"  ng-click="areaVersa(areaVersaCheck)">
                                                    <label for="areaVersa" class="allVersa">反选</label>
                                                </div>--%>

                                                <div style="margin-left: 20px;">
                                                    <a  ng-click="areaAll(areaLists)"
                                                        class="btn btn-success"><span translate="load.checkAll"></span></a>
                                                    <a   ng-click="deselectAllArea(areaLists)"
                                                        class="btn btn-danger"><span translate="load.deselectAll"></span></a>
                                                    <a  ng-click="areaVersa()"
                                                        class="btn btn-info"><span translate="load.InvertSelection"></span></a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-9 icheck ">
                                            <div >
                                                <div class="checkbox" data-ng-repeat="area in areaLists" align="center">
                                                    <input type="checkbox" id={{area.areaName}}  name={{area.id}}  ng-click="updateAreaSelection($event,area.id,area.areaName)" ng-model="areaModel"  ng-checked="areaMaster" class="chk_1 areaChildren" >
                                                    <label for={{area.areaName}}>{{area.areaName}}</label>
                                                </div>

                                            </div>
                                        </div>


                                    </div>
                                    <div class="modal-footer" style="width: 550px;">

                                        <button type="button" class="btn btn-default" data-dismiss="modal"><span translate="load.Cancel"></span></button>
                                        <button type="submit" class="btn btn-primary addSubmit"   > <span translate="load.Ok"></span></button>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="awardPackageModal" class="modal fade" >
            <div class="modal-dialog" style="width:800px;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                        <h4 class="modal-title">选择奖励包</h4>
                    </div>
                    <div class="modal-body" >
                        <div style="float: left;width:150px;margin-top: 27px;margin-left: 320px;" >
                            <form class="searchform"  method="post">
                            <select name="awardType" aria-controls="editable-sample" class="form-control awardType" ng-model="awardType" ng-change="awardChange(awardType)" required>
                                <option value="">请选择奖励类型</option>
                                <option value="1">新手奖励</option>
                                <option value="2">回归礼包</option>
                            </select>
                            </form>
                        </div>
                        <div style="float: right;margin-top: 20px;">
                            <form class="searchform"  method="post">
                                <input type="text" class="form-control searchText" name="keyword" placeholder="Search here..." ng-model="awardSearch" />
                                <div style="float: left;margin-top: 7px;">
                                    <button ng-click="searchAwardClicked()" class="btn btn-primary searchButton" >搜索</button>
                                </div>

                            </form>
                        </div>
                        <div class="row">
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
                                    <div class="modal-footer" style="width:750px;">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                        <button type="submit" class="btn btn-primary awardSubmit"> 确认</button>
                                    </div>
                                </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="firstBuyModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                        <h4 class="modal-title">选择区服</h4>
                    </div>
                    <div class="modal-body">
                        <section class="panel">
                            <div class="panel-body" >
                                <aside class="col-md-10">
                                    <h4 class="drg-event-title">基本信息</h4>
                                </aside>
                                <form class="form-horizontal bucket-form"  ng-submit="firstBuyAdd()" >
                                    <div class="row">
                                        <div class="col-md-10 form-group">
                                            <label>goodsCode:</label>
                                            <input type="text" class="form-control goodsCode"  ng-model="firstGoodsCode">
                                        </div>

                                    </div>

                                    <div class="row">
                                        <div class="col-md-10 form-group">
                                            <label>firstBuyOperation:</label>
                                            <select name="firstBuyOperation" aria-controls="editable-sample" class="form-control firstBuyOperation"     ng-change="firstBuyChange(firstBuyOperation)"   ng-model="firstBuyOperation" required>
                                                <option value="">请选择</option>
                                                <option value="*">乘法</option>
                                                <option value="+">加法</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-10 form-group">
                                            <label>operateCount:</label>
                                            <input type="number" class="form-control operateCount"  ng-model="operateCount">
                                        </div>

                                    </div>

                                    <div class="row">
                                        <aside class="col-md-10">
                                            <h4 class="drg-event-title">奖励礼包</h4>
                                            <div class='external-events'>
                                                <div class='external-event label label-primary'  data-ng-repeat="code in firstAwardData" >{{code}}</div>
                                                <br/>
                                            </div>
                                        </aside>
                                        <button class="btn btn-warning" type="button" ng-click="firstAwardClick()">选择奖励包</button>

                                    </div>
                                    <div class="modal-footer" style="width: 550px;">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                        <button type="submit" class="btn btn-primary addSubmit"   > 确认</button>
                                    </div>
                                </form>


                            </div>
                        </section>


                    </div>
                </div>
            </div>
        </div>

        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="firstAwardModal" class="modal fade" >
            <div class="modal-dialog" style="width:800px;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                        <h4 class="modal-title">选择奖励包</h4>
                    </div>
                    <div class="modal-body" >
                        <div style="float: left;width:150px;margin-top: 27px;margin-left: 320px;" >
                            <form class="searchform"  method="post">
                                <select name="firstAwardType" aria-controls="editable-sample" class="form-control firstAwardType" ng-model="firstAwardType" ng-change="firstAwardChange(firstAwardType)" required>
                                    <option value="">请选择奖励类型</option>
                                    <option value="1">新手奖励</option>
                                    <option value="2">回归礼包</option>
                                </select>
                            </form>
                        </div>
                        <div style="float: right;margin-top: 20px;">
                            <form class="searchform"  method="post">
                                <input type="text" class="form-control searchText" name="keyword" placeholder="Search here..." ng-model="firstAwardSearch" />
                                <div style="float: left;margin-top: 7px;">
                                    <button ng-click="searchFirstAwardClicked()" class="btn btn-primary searchButton" >搜索</button>
                                </div>

                            </form>
                        </div>
                        <div class="row">
                            <form class="form-horizontal bucket-form"  ng-submit="firstAwardForm()" >
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" class="checkbox"
                                                   data-ng-click="firstAwardCheckClick()" value=""></th>
                                        <th>awardId</th>
                                        <th>awardCount</th>
                                        <th>awardType</th>
                                        <th>awardName</th>
                                        <th>awardDesc</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr ng-repeat="award in firstAwardPackage">
                                        <td align="center"><input type="checkbox" class="checkbox firstAwardCheck"
                                                                  data-ng-click="firstAwardCheck($index,firstAwardChk)"data-ng-model="firstAwardChk" value={{award.awardId}} name={{award.awardName}}></td>
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
                                    <pager page-count="pageFirstAwardCount" current-page="currentFirstAwardPage" on-page-change="onFirstAwardPageChange()" first-text="首页" next-text="下一页" prev-text="上一页" last-text="尾页" show-goto="true" goto-text="跳转到"></pager>
                                </div>
                                <div class="modal-footer" style="width:750px;">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                    <button type="submit" class="btn btn-primary firstAwardSubmit"> 确认</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div aria-hidden="true"  role="dialog" tabindex="-1" id="messagesModal" class="modal fade"  >
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" >
                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                        <h4 class="modal-title" translate="{{messagesData.messagesTitle}}"></h4>
                    </div>
                    <div class="modal-body" translate="{{messagesData.messagesBody}}">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal"> <span translate="load.Ok"></span></button>
                    </div>
                </div>
            </div>
        </div>

        <div aria-hidden="true"  role="dialog" tabindex="-1" id="messagesConfirm" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                        <h4 class="modal-title" translate="{{messagesConfirm.title}}"></h4>
                    </div>
                    <div class="modal-body" translate="{{messagesConfirm.body}}">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><span translate="load.Cancel"></span></button>
                        <button type="button" class="btn btn-warning" data-dismiss="modal" ng-click="confirm()"> <span translate="load.Ok"></span></button>
                    </div>
                </div>
            </div>
        </div>
</div>
</section>


