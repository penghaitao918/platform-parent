<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%--<script src="${ctx}/resource/adminex/js/multi-select-init.js"></script>--%>
<script src="${ctx}/resource/adminex/js/icheck-init.js"></script>
<script src="${ctx}/resource/adminex/js/DateTimePicker/DateInit.js"></script>

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

</script>
<section >
    <div  ng-controller="articleEditController">
        <form role="form" ng-submit="articleAddForm()" id="articleAddForm" name="articleAddForms">
            <div class="row">
                <div class="col-sm-6">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.ApplicationInformation"></span>
                        </header>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.GameId"></span>:<span
                                            style="color: red">*</span></label>
                                    <select name="gameList" aria-controls="editable-sample"
                                            class="form-control gameList"
                                            ng-change="gameIdChange(articleEditData.gameId)"
                                            ng-options="game.id as game.code|translate for game in gameList"
                                            ng-model="articleEditData.gameId" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                    </select>
                                </div>

                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.applicationSendByType"></span>:<span
                                            style="color: red">*</span></label>
                                    <select aria-controls="editable-sample" class="form-control"
                                            ng-model="articleEditData.sendType" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                        <option value="1" translate="load.PersonalRule"></option>
                                        <option value="2" translate="load.AllRule"></option>
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.applicationSendIdType"></span>:<span
                                            style="color: red">*</span></label>
                                    <select aria-controls="editable-sample" class="form-control"
                                            ng-model="articleEditData.sendByType" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                        <option value="1" translate="load.platformId"></option>
                                        <option value="2" translate="load.roleId"></option>
                                        <option value="3" translate="load.userId"></option>
                                    </select>
                                </div>

                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.applicationServer"></span>:<span
                                            style="color: red">*</span></label>
                                    <select aria-controls="editable-sample" class="form-control"
                                            ng-model="articleEditData.areaIds"
                                            ng-options="area.id as area.areaName for area in areaLists" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                    </select>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col-md-5 form-group labelSpace">
                                    <label><span translate="load.applicationSenderObject"></span>:<span
                                            style="color: red">*</span></label><span
                                        style="color:red;">&nbsp;&nbsp;(<span
                                        translate="load.applicationSenderObjectState"></span>)</span>
                                    <textarea class="form-control" style="height: 150px;resize:none;"
                                              ng-model="articleEditData.sendByValue"></textarea>
                                </div>

                                <div class="col-md-5 form-group labelSpace">
                                    <label><span translate="load.applicationReasons"></span>:</label>
                                    <textarea class="form-control" style="height: 150px;resize:none;"
                                              ng-model="articleEditData.applyReason"></textarea>
                                </div>
                            </div>

                        </div>
                    </section>


                </div>

                <div class="col-sm-6">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.ApplicationEmail"></span>
                        </header>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-3 form-group labelSpace">
                                    <label><span translate="load.applicationMailType"></span>:<span
                                            style="color: red">*</span></label>
                                    <select aria-controls="editable-sample" class="form-control"
                                            ng-model="articleEditData.mailType" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                        <option value="1" translate="load.ActivityReward"></option>
                                        <option value="2" translate="load.IndividualCompensation"></option>
                                        <option value="3" translate="load.InternalApplication"></option>
                                        <option value="4" translate="load.RechargeCompensation"></option>
                                        <option value="5" translate="load.Other"></option>
                                    </select>
                                </div>
                                <div class="col-md-3 form-group labelSpace">
                                    <label><span translate="load.applicationEmailTitle"></span>:<span
                                            style="color: red">*</span></label>
                                    <input type="text" class="form-control" maxlength="20"
                                           ng-model="articleEditData.mailTitle" required>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-5 form-group labelSpace">
                                    <label><span translate="load.applicationMailContent"></span>:</label>
                                    <textarea class="form-control" style="height: 150px;resize:none;"
                                              ng-model="articleEditData.mailContent"></textarea>
                                </div>

                            </div>

                        </div>

                    </section>


                </div>


                <div class="col-sm-6">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.applicationEmailContent"></span><span style="color:red;">&nbsp;&nbsp;(<span
                                translate="load.articleAttention"></span>)</span>
                        </header>
                        <div class="panel-body">
                            <div class="clientContent" ng-repeat="item in articleEditData.item">
                                <div style="margin-top: 20px;">

                                    <div class="row">
                                        <div class="col-md-3 form-group labelSpace">
                                            <label><span translate="load.itemItemType"></span>:</label>
                                            <select name="itemType" aria-controls="editable-sample"
                                                    ng-change="itemTypeChange(item.itemType,$index)"
                                                    ng-options="itemType.value as itemType.key|translate for itemType in itemTypeList"
                                                    class="form-control itemType" ng-model="item.itemType" required>
                                                <option value="" translate="load.pleaseSelect"></option>
                                                <%--   <option value="{{itemType.id}}" ng-repeat="itemType in itemTypeList" translate="load.itemType{{itemType.code}}"></option>--%>
                                            </select>
                                        </div>

                                        <div class="prop">
                                            <div class="col-md-3 form-group labelSpace">
                                                <label><span translate="load.applicationItemName"></span>:<span
                                                        style="color: red">*</span></label>
                                                <div class="input-group m-bot15">
                                                    <input type="text" class="form-control" ng-model="item.itemName"
                                                           ng-click="itemSelected(item.itemType,$index)" readonly/>
                                              <span class="input-group-btn">
                                                <button class="btn btn-default"
                                                        ng-click="itemSelected(item.itemType,$index)"
                                                        style="height: 34px;" type="button"><i class="fa fa-search"></i>
                                                </button>
                                              </span>
                                                </div>
                                            </div>

                                            <div class="col-md-3 form-group labelSpace">
                                                <label><span translate="load.applicationItemNum"></span>:<span
                                                        style="color: red">*</span></label>
                                                <input type="number" class="form-control" min="0" max="100000000"
                                                       ng-model="item.value" required/>
                                            </div>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-3 form-group labelSpace"
                                             ng-repeat="analog in item.extraData">
                                            <label>{{analog.formName}}:<span style="color: red"
                                                                             ng-hide="analog.formRequired==required">*</span></label>
                                            <div class="iconic-input right">
                                                <i>{{analog.formUnit}}</i>
                                                <input type={{analog.formType}} class="form-control"
                                                       ng-model="analog.formValue" {{analog.formRequired}}
                                                       name="{{analog.formCode}}" maxlength="20">
                                            </div>

                                        </div>

                                    </div>

                                    <button type="button" class="btn btn-danger" style="margin-top: 25px;"
                                            href="javascript:" ng-show="clientContent.canDescReply"
                                            ng-click="clientContent.clientDelete($index)"><span
                                            translate="load.Delte"></span>&nbsp<i class="fa fa-times"></i></button>

                                    <header class="panel-heading">

                                    </header>
                                </div>
                            </div>
                            <div class="clientFoot">
                                <button type="button" class="btn btn-success" href="javascript:"
                                        ng-click="clientContent.clientAdd(clientCount)"><span
                                        translate="load.add"></span>&nbsp<i class="fa fa-plus"></i></button>
                            </div>


                        </div>

                        <div class="modal-footer">
                            <div style="float: right;margin-right: 20px;">
                                <button type="button" class="btn btn-default" href="javascript:"
                                        onclick="history.back(); "><span translate="load.Cancel"></span></button>
                                <button type="submit" class="btn btn-primary articleAddSubmit"><span
                                        translate="load.Ok"></span></button>
                            </div>
                            <p ng-hide="!articleAddForms.$invalid" style="color: red;float: right;margin-top: 15px;">
                                <span translate="load.remindValidate"></span></p>
                        </div>

                    </section>


                </div>
            </div>
        </form>

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

        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="itemModal"
             class="modal fade ">
            <div class="modal-dialog" style="width: 900px;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                        <h4 class="modal-title"><span translate="load.itemSelect"></span></h4>
                    </div>
                    <div class="modal-body">
                        <div class="clearfix">
                            <div style="float: right;">
                                <form class="searchform" name="searchForms" method="post"
                                      style="float:left;margin-top: 18px;">
                                    <input type="number" class="form-control searchText" name="itemCodeSearch"
                                           ng-model="gameToyTypeSearch.itemCode"
                                           placeholder={{placeholderItemCode|translate}}
                                           onkeydown="globelQuery(event)"/>
                                    <input type="text" class="form-control searchText"
                                           ng-model="gameToyTypeSearch.itemTypeName"
                                           placeholder={{placeholderItemName|translate}}
                                           onkeydown="globelQuery(event)"/>
                                    <div style="float: left;margin-top: 7px;">
                                        <button ng-click="searchButtonClicked()" class="btn btn-primary searchButton">
                                            <span translate="load.search"></span>&nbsp;<i class="fa fa-search"></i>
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>

                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th><span translate="load.itemItemCode"></span></th>
                                <th><span translate="load.itemItemName"></span></th>
                                <th><span translate="load.itemItemType"></span></th>
                                <th><span translate="load.itemExtend"></span></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="gameToyType in gameTypeToys" ng-click="itemItemChoice()">
                                <td>{{gameToyType.id}}</td>
                                <td>{{gameToyType.itemCode}}</td>
                                <td>{{gameToyType.itemName}}</td>
                                <td><span ng-show="gameToyType.itemType==1" class="label label-success label-mini">{{gameToyType.itemType|NumToStr16|translate}}</span>
                                    <span ng-show="gameToyType.itemType==2" class="label label-danger label-mini">{{gameToyType.itemType|NumToStr16|translate}}</span>
                                    <span ng-show="gameToyType.itemType==3" class="label label-primary label-mini">{{gameToyType.itemType|NumToStr16|translate}}</span>
                                    <span ng-show="gameToyType.itemType==4" class="label label-default label-mini">{{gameToyType.itemType|NumToStr16|translate}}</span>
                                    <span ng-show="gameToyType.itemType==5" class="label label-info label-mini">{{gameToyType.itemType|NumToStr16|translate}}</span>
                                </td>
                                <td>{{gameToyType.itemExtend}} <span style="float: right;color: red;"
                                                                     ng-show="gameToyType.lately==1"
                                                                     translate="load.gameToyTypeLately"></span>
                                </td>

                            </tr>
                            </tbody>
                        </table>
                        <div class="pager">
                            <pager page-count="pageCount" current-page="currentPage" on-page-change="onPageChange()"
                                   first-text="load.HomePage" next-text="load.NextPage" prev-text="load.PreviousPage"
                                   last-text="load.EndPage" show-goto="true" goto-text="load.GoToPage"></pager>
                        </div>
                    </div>
                    <div class="modal-footer" style="width: 900px;">
                        <button type="button" class="btn btn-danger" data-dismiss="modal"><span
                                translate="load.Cancel"></span></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script type="text/javascript">
    function globelQuery(e) {
        if (!e)
            e = window.event;
        if ((e.keyCode || e.which) == 13) {
            $(".searchButton").click();
        }
    }

</script>
