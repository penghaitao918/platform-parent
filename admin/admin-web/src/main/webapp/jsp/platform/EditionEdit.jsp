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
<section>
    <div ng-controller="editionEditController">
        <form role="form" ng-submit="editionEditForm()" id="editionEditForms" name="editionEditForms">
            <div class="row">
                <div class="col-sm-6">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.basicInfo"></span>
                        </header>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.versionSignificantCondition"></span>:<span
                                            style="color: red">*</span></label>
                                    <select name="validStatus" aria-controls="editable-sample"
                                            class="form-control validStatus"
                                            ng-model="editionData.validStatusView" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                        <option value="1" translate="load.versionValid"></option>
                                        <option value="2" translate="load.versionUnValid"></option>
                                        <option value="3" translate="load.versionRemoved"></option>
                                    </select>
                                </div>
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.resGame"></span>:<span
                                            style="color: red">*</span></label>
                                    <select name="gameList" aria-controls="editable-sample"
                                            class="form-control gameList"
                                            ng-options="game.id as game.code|translate for game in gameList"
                                            ng-model="editionData.gameId" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                    </select>

                                </div>

                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.resSite"></span>:<span
                                            style="color: red">*</span></label>
                                    <select name="siteList" aria-controls="editable-sample"
                                            class="form-control siteList"
                                            ng-options="site.id as site.code|translate for site in siteList"
                                            ng-model="editionData.siteId" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                    </select>

                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.versionDescribe"></span>:</label>
                                    <textarea class="form-control" style="height: 150px;resize:none;"
                                              ng-model="editionData.patchDesc"></textarea>
                                </div>
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.versionCode"></span>:<span style="color: red">*</span></label>
                                    <input type="number" class="form-control" maxlength="20"
                                           ng-model="editionData.patchVersion"  min="0"  required >
                                </div>
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.versionPhoneSystem"></span>:<span
                                            style="color: red">*</span></label>
                                    <select name="versionPhoneSystem" aria-controls="editable-sample"
                                            class="form-control versionPhoneSystem"
                                            ng-model="editionData.deviceTypeView" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                        <option value="1" translate="load.versionAndroid"></option>
                                        <option value="2" translate="load.versionIos"></option>
                                    </select>
                                </div>
                            </div>

                        </div>
                    </section>
                </div>

                <div class="col-sm-6">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.versionUpdateInfo"></span>
                        </header>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.versionFileName"></span>:<span
                                            style="color: red">*</span></label><span
                                        style="color:red;">&nbsp;&nbsp;(<span
                                        translate="load.versionFileNameInfo"></span>)</span>
                                    <input type="text" class="form-control" maxlength="20"
                                           ng-model="editionData.patchName" required ng-change="valueChange()">
                                </div>
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.versionFileURL"></span>:<span
                                            style="color: red">*</span></label>
                                    <input type="text" class="form-control" maxlength="20"
                                           ng-model="editionData.patchUrl" required ng-change="valueChange()">
                                </div>
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.versionFileSize"></span>:<span
                                            style="color: red">*</span></label>
                                    <div class="iconic-input right">
                                        <i>KB</i>
                                        <input type="number" class="form-control" maxlength="20"
                                               ng-model="editionData.patchSize" min="0" required ng-change="valueChange()">
                                    </div>
                                </div>
                            </div>

                        </div>
                    </section>
                </div>

                <div class="col-sm-6">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.versionUpdateInfo"></span>
                        </header>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.versionStartWhitelists"></span>:<span
                                            style="color: red">*</span></label>
                                    <select name="whiteStatus" aria-controls="editable-sample"
                                            class="form-control gameList"
                                            ng-model="editionData.whiteStatusView" required>
                                        <option value="1" translate="load.versionYes"></option>
                                        <option value="2" translate="load.versionNo"></option>
                                    </select>
                                </div>
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.versionWhitelistsIp"></span>:<span
                                            style="color: red">*</span></label><span
                                        style="color:red;">&nbsp;&nbsp;(<span
                                        translate="load.versionWhitelistsIpInfo"></span>)</span>
                                     <textarea class="form-control" style="height: 150px;resize:none;"
                                               ng-model="editionData.whiteContent"></textarea>
                                </div>
                            </div>

                            <div class="modal-footer">
                                <div style="float: right;margin-right: 20px;">
                                    <button type="button" class="btn btn-default" href="javascript:"
                                            onclick="history.back(); "><span translate="load.Cancel"></span></button>
                                    <button type="submit" class="btn btn-primary addSubmit"><span
                                            translate="load.Ok"></span></button>
                                </div>
                                <p ng-hide="!editionEditForms.$invalid" style="color: red;float: right;margin-top: 15px;">
                                    <span translate="load.remindValidate"></span></p>
                            </div>
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
                                                    <label for={{site.id}} translate="{{site.code}}"></label>
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


