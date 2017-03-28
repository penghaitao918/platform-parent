<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resource/adminex/js/DateTimePicker/DateInit.js"></script>
<%--<script src="${ctx}/resource/adminex/js/multi-select-init.js"></script>--%>
<script src="${ctx}/resource/adminex/js/icheck-init.js"></script>
<script src="${ctx}/resource/adminex/js/ios-switch/switchery.js"></script>
<script src="${ctx}/resource/adminex/js/ios-switch/ios-init.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resource/adminex/js/ios-switch/switchery.css"/>
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
<div >
    <div  ng-controller="gameActivityEditController">
        <form role="form" ng-submit="gameActivityEditForm()" id="gameActivityEditForm" name="gameActivityEditForms">
            <div class="row">
                <div class="col-sm-6">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.ApplicationInformation"></span>
                        </header>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.activityId"></span>:<span
                                            style="color: red">*</span></label>
                                    <input type="number" class="form-control activityId" min="0" max="100000000"
                                           ng-model="activity.activityId" required/>
                                </div>
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.activityStartTime"></span>:<span
                                            style="color: red">*</span></label>
                                    <input size="16" type="text" name="startTime" readonly
                                           class="ui_timepicker form-control" ng-model="activity.startTime" required>
                                </div>

                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.activityEndTime"></span>:<span
                                            style="color: red">*</span></label>
                                    <input size="16" type="text" name="endTime" readonly
                                           class="ui_timepicker form-control" ng-model="activity.endTime" required>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.GameId"></span>:<span
                                            style="color: red">*</span></label>
                                    <select name="gameList" aria-controls="editable-sample"
                                            class="form-control gameList activityGameList"
                                            ng-options="game.id as game.code|translate for game in gameList"
                                            ng-change="resourceGameListChange(activityGameList)"
                                            ng-model="activityGameList"  required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                    </select>
                                </div>
                                <div class="col-md-4 form-group labelSpace">
                                    <div class="checkbox single-row"  style="margin-top: 23px;">
                                        <input type="checkbox" id="activityCheck" class="checkbox activityCheck" style="width:50px;cursor:pointer" ng-model="activity.validStatusCheck"  ng-true-value="1" ng-false-value="2"/>
                                        <div style="margin-top: 23px;"><label for="activityCheck" style="color: red;" translate="load.activityInstructions"></label></div>

                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.activityTaskId"></span>:(<span
                                            style="color: red" translate="load.applicationSenderObjectState"></span>)<span
                                            style="color: red" >*</span></label>
                                    <textarea class="form-control activityTaskId" style="height: 150px;resize:none;"
                                              ng-model="activity.taskId" required></textarea>
                                </div>
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.activityContent"></span>:</label>
                                   <textarea class="form-control" style="height: 150px;resize:none;"
                                             ng-model="activity.activityContent"></textarea>
                                </div>


                            </div>


                        </div>
                    </section>


                </div>

                <div class="col-sm-6">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.channelorarea"></span>
                        </header>

                        <div class="panel-body">
                            <div class="row" style="margin-top: 30px;">
                                <aside class="col-md-3">
                                    <h4 class="drg-event-title"><span translate="load.channelList"></span></h4>
                                    <div id='external-events'>
                                        <div class='external-event label label-primary'
                                             data-ng-repeat="code in channelCode" translate={{code}}></div>
                                        <br/>
                                    </div>
                                </aside>
                                <button class="btn btn-warning" type="button" ng-click="channelClick()"><span
                                        translate="load.channelChoice"></span></button>


                                <aside class="col-md-3">
                                    <h4 class="drg-event-title"><span translate="load.areaList"></span></h4>
                                    <div class='external-events'>
                                        <div class='external-event label label-primary'
                                             data-ng-repeat="area in areaData">{{area}}
                                        </div>
                                        <br/>
                                    </div>
                                </aside>
                                <button class="btn btn-warning" type="button" ng-click="areaClick()"><span
                                        translate="load.areaChoice"></span></button>

                            </div>

                        </div>

                        <div class="modal-footer">
                            <div style="float: right;margin-right: 20px;">
                                <button type="button" class="btn btn-default" href="javascript:"
                                        onclick="history.back(); "><span translate="load.Cancel"></span></button>
                                <button type="submit" class="btn btn-primary articleAddSubmit"><span
                                        translate="load.Ok"></span></button>
                            </div>
                            <p ng-hide="!gameActivityEditForms.$invalid" style="color: red;float: right;margin-top: 15px;">
                                <span translate="load.remindValidate"></span></p>
                        </div>
                    </section>
                </div>

            </div>
        </form>


        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="channelAddModal"
             class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                        <h4 class="modal-title"><span translate="load.channelChoice"></span></h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-6">
                                <form class="form-horizontal bucket-form" ng-submit="channelAdd()">
                                    <div class="form-group" style="width: 800px;">
                                        <div class="col-sm-9 icheck ">
                                            <div style="margin-left: 20px;">
                                                <a ng-model="master" ng-click="all(siteList)"
                                                   class="btn btn-success"><span translate="load.checkAll"></span></a>
                                                <a ng-model="master" ng-click="deselectAll(siteList)"
                                                   class="btn btn-danger"><span translate="load.deselectAll"></span></a>

                                                <a ng-model="versaCheck" ng-click="versa(versaCheck)"
                                                   class="btn btn-info"><span
                                                        translate="load.InvertSelection"></span></a>
                                            </div>
                                        </div>
                                        <div class="col-sm-9 icheck ">
                                            <div>
                                                <div class="checkbox" data-ng-repeat="site in siteList" align="center">
                                                    <input type="checkbox" id={{site.id}} name={{site.code}}
                                                           ng-click="updateSelection($event,site.id,site.code)"
                                                           ng-model="siteModel" ng-checked="master"
                                                           class="chk_1 children">
                                                    <label for={{site.id}} translate={{site.code}}></label>
                                                </div>

                                            </div>
                                        </div>


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

        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="areaAddModal"
             class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                        <h4 class="modal-title"><span translate="load.areaChoice"></span></h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-6">
                                <form class="form-horizontal bucket-form" ng-submit="areaAdd()">
                                    <div class="form-group" style="width: 800px;">
                                        <div class="col-sm-9 icheck ">
                                            <div style="margin-left: 20px;">
                                                <a ng-click="areaAll(areaLists)"
                                                   class="btn btn-success"><span translate="load.checkAll"></span></a>
                                                <a ng-click="deselectAllArea(areaLists)"
                                                   class="btn btn-danger"><span translate="load.deselectAll"></span></a>
                                                <a ng-click="areaVersa()"
                                                   class="btn btn-info"><span
                                                        translate="load.InvertSelection"></span></a>
                                            </div>
                                        </div>
                                        <div class="col-sm-9 icheck ">
                                            <div>
                                                <div class="checkbox" data-ng-repeat="area in areaLists" align="center">
                                                    <input type="checkbox" id={{area.areaName}} name={{area.id}}
                                                           ng-click="updateAreaSelection($event,area.id,area.areaName)"
                                                           ng-model="areaModel" ng-checked="areaMaster"
                                                           class="chk_1 areaChildren">
                                                    <label for={{area.areaName}}>{{area.areaName}}</label>
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
</div>


