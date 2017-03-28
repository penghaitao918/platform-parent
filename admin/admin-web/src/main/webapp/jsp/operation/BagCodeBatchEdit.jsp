<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resource/adminex/js/DateTimePicker/DateInit.js"></script>
<%--<script src="${ctx}/resource/adminex/js/multi-select-init.js"></script>--%>
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
        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px 1px 3px rgba(0, 0, 0, 0.1);
    }

    .chk_1:checked + label {
        background-color: #ECF2F7;
        border: 1px solid #92A1AC;
        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05), inset 15px 10px -12px rgba(255, 255, 255, 0.1);
        color: #243441;
    }

    .chk_1:checked + label:after {
        content: '\2714';
    / / 勾选符号 position: absolute;
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
<script>
    /* jQuery(document).ready(function(){
     $('.wysihtml5').wysihtml5();
     });*/
</script>
<section>
    <div ng-controller="bagCodeBatchEditController">
        <form role="form" ng-submit="bagCodeBatchEdit()" id="bagCodeBatchEditForm" name="bagCodeBatchEditForm">
            <div class="row">
                <div class="col-sm-6">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.basicInfo"></span>
                        </header>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.bagValidStatus"></span>:<span
                                            style="color: red">*</span></label>
                                    <select name="noticeType" aria-controls="editable-sample"
                                            class="form-control noticeType"
                                            ng-model="bagCodeBatchEditData.validStatusVal"
                                            required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                        <option value="1" translate="load.versionValid"></option>
                                        <option value="2" translate="load.versionUnValid"></option>
                                        <option value="3" translate="load.versionRemoved"></option>
                                    </select>
                                </div>
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.bagGiftBagName"></span>:<span
                                            style="color: red">*</span></label>
                                    <input type="text" class="form-control searchText"
                                           ng-model="bagCodeBatchEditData.batchName"
                                           name="batchName" required/>
                                </div>

                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.giftBagGame"></span>:<span
                                            style="color: red">*</span></label>
                                    <select name="gameList" aria-controls="editable-sample"
                                            class="form-control gameList"
                                            ng-options="game.id as game.code|translate for game in gameList"
                                            ng-model="bagCodeBatchEditData.gameId" required
                                            ng-change="gameIdChange(bagCodeBatchEditData.gameId)">
                                        <option value="" translate="load.pleaseSelect"></option>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.bagAmount"></span>:<span
                                            style="color: red">*</span></label>
                                    <input type="number" class="form-control" name="amount" min="0"
                                           max="1000000000000" ng-model="bagCodeBatchEditData.amount"
                                           required>
                                </div>

                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.bagGameArea"></span>:<span
                                            style="color: red">*</span></label>
                                    <select aria-controls="editable-sample" class="form-control"
                                            ng-model="bagCodeBatchEditData.gameAreaId"
                                            ng-options="area.id as area.areaName for area in areaLists" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                    </select>
                                </div>
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.Site"></span>:<span
                                            style="color: red">*</span></label>
                                    <select name="siteList" aria-controls="editable-sample"
                                            class="form-control siteList"
                                            ng-options="site.id as site.code|translate for site in siteList"
                                            ng-model="bagCodeBatchEditData.siteId"
                                            ng-change="siteIdChange(bagCodeBatchEditData.siteId)" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                    </select>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.bagDescribe"></span>:</label>
                              <textarea class="form-control" style="height: 150px;resize:none;"
                                        ng-model="bagCodeBatchEditData.awardGreet"></textarea>
                                </div>

                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.bagValidBeginTime"></span>:<span
                                            style="color: red">*</span></label>
                                    <input size="16" type="text" readonly class="ui_timepicker form-control"
                                           ng-model="bagCodeBatchEditData.availableStartDate" required>
                                </div>
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.bagValidEndTime"></span>:<span
                                            style="color: red">*</span></label>
                                    <input size="16" type="text" readonly class="ui_timepicker form-control"
                                           ng-model="bagCodeBatchEditData.availableEndDate" required>
                                </div>
                            </div>


                        </div>

                    </section>
                </div>

                <div class="col-sm-6">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.bagPrizeInformation"></span><span style="color:red;">&nbsp;&nbsp;(&nbsp;</span><span
                                translate="load.bagExplain" style="color:red;"></span><span style="color:red;">&nbsp;)</span>
                        </header>

                        <div class="panel-body">
                            <div class="row">
                                <aside class="col-md-4">
                                    <h4 class="drg-event-title"><span translate="load.bagBagChoice"></span><span
                                            style="color: red">*</span></h4>
                                    <div class='external-events'>
                                        <div class='external-event label label-primary'
                                             data-ng-repeat="bag in giftBagDatas">{{bag}}
                                        </div>
                                        <br/>
                                    </div>
                                </aside>
                                <button class="btn btn-warning" type="button" ng-click="bagClick()"><span
                                        translate="load.bagCheckBag"></span></button>

                            </div>

                        </div>
                    </section>
                </div>


                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.bagGetRules"></span>
                        </header>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.bagMutexId"></span>:</label>
                                    <input type="text" class="form-control searchText"
                                           ng-model="noticeSearch.bagMutexId"
                                           name="keyword"/>
                                </div>

                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.bagGetType"></span>:<span
                                            style="color: red">*</span></label>
                                    <select name="noticeType" aria-controls="editable-sample"
                                            class="form-control noticeType"
                                            ng-model="bagCodeBatchEditData.usingRule.limitedType"
                                            required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                        <option value="0" translate="load.bagUnlimited"></option>
                                        <option value="1" translate="load.bagLimitingServer"></option>
                                        <option value="2" translate="load.bagLimitingChannel"></option>
                                        <option value="3" translate="load.bagAltogether"></option>
                                        <option value="4" translate="load.bagMonthly"></option>
                                        <option value="5" translate="load.bagWeekly"></option>
                                        <option value="6" translate="load.bagEveryday"></option>
                                    </select>
                                </div>
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.bagGetAmount"></span>:<span
                                            style="color: red">*</span></label>
                                    <input type="text" class="form-control searchText"
                                           ng-model="bagCodeBatchEditData.usingRule.limitedTimes"
                                           name="keyword"/>
                                </div>

                            </div>

                            <div class="row">
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.bagRuleDescribe"></span>:</label>
                              <textarea class="form-control" style="height: 150px;resize:none;"
                                        ng-model="bagCodeBatchEditData.usingRule.ruleDesc"></textarea>
                                </div>

                            </div>

                            <div class="modal-footer">
                                <div style="float: right;margin-right: 20px;">
                                    <button type="button" class="btn btn-default" href="javascript:"
                                            onclick="history.back(); "><span translate="load.Cancel"></span></button>
                                    <button type="submit" class="btn btn-primary addSubmit"><span
                                            translate="load.Ok"></span></button>
                                </div>
                                <p ng-hide="!noticeForm.$invalid" style="color: red;float: right;margin-top: 15px;">
                                    <span translate="load.remindValidate"></span></p>

                            </div>

                        </div>

                    </section>
                </div>

            </div>
        </form>


        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="bagAddModal"
             class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                        <h4 class="modal-title"><span translate="load.bagBagChoice"></span></h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-6">
                                <form class="form-horizontal bucket-form" ng-submit="bagAdd()">
                                    <div class="form-group" style="width: 800px;">
                                        <div class="col-sm-9 icheck ">
                                            <div style="margin-left: 20px;">
                                                <a ng-click="bagAll(giftBagLists)"
                                                   class="btn btn-success"><span translate="load.checkAll"></span></a>
                                                <a ng-click="deselectAllBag(giftBagLists)"
                                                   class="btn btn-danger"><span translate="load.deselectAll"></span></a>
                                                <a ng-click="bagVersa()"
                                                   class="btn btn-info"><span
                                                        translate="load.InvertSelection"></span></a>
                                            </div>
                                        </div>
                                        <div class="col-sm-9 icheck ">
                                            <div>
                                                <div class="checkbox" data-ng-repeat="bag in giftBagLists"
                                                     align="center">
                                                    <input type="checkbox" id={{bag.packageName}} name={{bag.packageId}}
                                                           ng-click="updateBagSelection($event,bag.packageId,bag.packageName)"
                                                           ng-model="bagModel" ng-checked="bagMaster"
                                                           class="chk_1 bagChildren">
                                                    <label for={{bag.packageName}}>{{bag.packageName}}</label>
                                                </div>

                                            </div>
                                        </div>


                                    </div>
                                    <div style="width: 550px;text-align:center;">
                                        <pager page-count="pageCount" current-page="currentPage"
                                               on-page-change="onPageChange()" first-text="load.HomePage"
                                               next-text="load.NextPage" prev-text="load.PreviousPage"
                                               last-text="load.EndPage" show-goto="true"
                                               goto-text="load.GoToPage"></pager>
                                    </div>
                                    <div class="modal-footer" style="width: 550px;">

                                        <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                                translate="load.Cancel"></span></button>
                                        <button type="submit" class="btn btn-primary addSubmit"><span
                                                translate="load.Ok"></span></button>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div aria-hidden="true" role="dialog" tabindex="-1" id="messagesModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                        <h4 class="modal-title" translate="{{messagesData.messagesTitle}}"></h4>
                    </div>
                    <div class="modal-body" translate="{{messagesData.messagesBody}}">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal"><span
                                translate="load.Ok"></span></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


