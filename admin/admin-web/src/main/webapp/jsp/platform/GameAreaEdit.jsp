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
    <div ng-controller="gameAreaEditController">
        <form role="form" ng-submit="gameAreaEditForm()" id="gameAreaForm" name="gameAreaForm">
            <div class="row">
                <div class="col-sm-6">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.basicInfo"></span>
                        </header>
                        <div class="panel-body">
                            <div class="row">
                                <%--  <div class="col-md-3 form-group">
                                      <label><span translate="load.areaValidStatus"></span>:<span style="color: red">*</span></label>
                                      <select name="validStatus" aria-controls="editable-sample" class="form-control validStatus" ng-model="validStatus" required >
                                          <option value="" translate="load.pleaseSelect"></option>
                                          <option value="unact" translate="load.validStatusUnact"></option>
                                          <option value="acting" translate="load.validStatusActing"></option>
                                          <option value="locked" translate="load.validStatusLocked"></option>
                                          <option value="acted" translate="load.validStatusActed"></option>
                                          <option value="reject" translate="load.validStatusReject"></option>
                                          <option value="error" translate="load.validStatusError"></option>
                                      </select>
                                  </div>--%>
                                <div class="col-md-3 form-group labelSpace">
                                    <label><span translate="load.areaAreaName"></span>:<span style="color: red">*</span></label><span
                                        style="color:red;">&nbsp;&nbsp;(<span translate="load.constraintLength"></span>)</span>
                                    <input type="text" class="form-control" maxlength="20"
                                           ng-model="gameAreasData.areaName" required ng-change="valueChange()">
                                </div>
                                <div class="col-md-3 form-group labelSpace">
                                    <label><span translate="load.areaAvailableTime"></span>:<span
                                            style="color: red">*</span></label>
                                    <input size="16" type="text" readonly class="ui_timepicker form-control"
                                           ng-model="gameAreasData.availableTime" required ng-change="valueChange()">

                                </div>

                                <div class="col-md-3 form-group labelSpace">
                                    <label><span translate="load.GameId"></span>:<span
                                            style="color: red">*</span></label>
                                    <select name="gameList" aria-controls="editable-sample"
                                            class="form-control gameList"
                                            ng-options="game.id as game.code|translate for game in gameList"
                                            ng-change="resourceGameListChange(gameToyGameList)"
                                            ng-model="gameToyGameList" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                    </select>

                                    <%--<select name="gameId" aria-controls="editable-sample" class="form-control gameId" ng-model="gameToyGameList" ng-change="resourceGameListChange(gameToyGameList)"  required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                        <option value={{game.id}} ng-repeat="game in gameList" ng-selected="gameSelected" translate={{game.code}}></option>
                                    </select>--%>
                                </div>


                            </div>

                            <div class="row">


                                <div class="col-md-3 form-group labelSpace">
                                    <label><span translate="load.areaSupportVersionMin"></span>:<span
                                            style="color: red">*</span></label><span
                                        style="color:red;">&nbsp;&nbsp;(<span
                                        translate="load.versionsExplainMax"></span>)</span>
                                    <input type="number" class="form-control" name="supportVersionMin" min="0"
                                           max="1000000000000" ng-model="gameAreasData.supportVersionMin"
                                           ng-change="valueChange()" required>
                                </div>

                                <div class="col-md-3 form-group labelSpace">
                                    <label><span translate="load.areaSupportVersionMax"></span>:<span
                                            style="color: red">*</span></label><span
                                        style="color:red;">&nbsp;&nbsp;(<span
                                        translate="load.versionsExplainMin"></span>)</span>
                                    <input type="number" class="form-control" name="supportVersionMax" min="0"
                                           max="1000000000000" ng-model="gameAreasData.supportVersionMax"
                                           ng-change="valueChange()" required>
                                </div>
                                <div class="col-md-3 form-group labelSpace">
                                    <label><span translate="load.areaAreaCode"></span>:<span style="color: red">*</span></label></span>
                                    <input type="number" class="form-control areaAreaCode" name="areaCode" min="0"
                                           max="1000000000000" ng-model="gameAreasData.areaCode" id="gameAreaCode"
                                           required ng-change="valueChange()">

                                </div>

                            </div>

                            <div class="row">
                                <div class="col-md-3 form-group labelSpace">
                                    <label><span translate="load.areaAreaDesc"></span></label>:<span style="color:red;">&nbsp;&nbsp;(<span
                                        translate="load.constraintTextAreaLength"></span>)</span>
                                    <textarea style="height: 70px;resize:none;" class="form-control"
                                              ng-model="gameAreasData.areaDesc" ng-change="valueChange()"
                                              maxlength="100"></textarea>
                                </div>


                                <div class="col-md-3 form-group labelSpace">
                                    <label><span translate="load.areaGroupName"></span>:<span
                                            style="color: red">*</span></label>
                                    <select name="gameList" aria-controls="editable-sample"
                                            class="form-control regionList" ng-change="valueChange()"
                                            ng-options="region.id as region.areaGroupName for region in regionList"
                                            ng-model="gameAreasData.areaGroupId" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                    </select>
                                </div>
                                <div class="col-md-3 form-group labelSpace">
                                    <label><span translate="load.areaDisplayOrder"></span>:<span
                                            style="color: red">*</span></label><span
                                        style="color:red;">&nbsp;&nbsp;(<span
                                        translate="load.areaSortState"></span>)</span>
                                    <input type="number" class="form-control" min="0" max="1000000000000" size="16"
                                           name="displayOrder" ng-model="gameAreasData.displayOrder"
                                           ng-change="valueChange()" required>
                                </div>
                            </div>


                        </div>
                    </section>
                </div>


                <div class="col-sm-6">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.serverConfig"></span><span style="color:red;">&nbsp;&nbsp;(<span
                                translate="load.areaStateExplain"></span>)</span>
                        </header>

                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.areaMaintenanceStatus"></span>:<span
                                            style="color: red">*</span></label>
                                    <select name="maintenanceStatus" aria-controls="editable-sample"
                                            class="form-control maintenanceStatus" ng-model="maintenanceStatus"
                                            ng-change="valueChange()" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                        <option value="1" translate="load.Normal"></option>
                                        <option value="2" translate="load.Maintaining"></option>
                                    </select>
                                </div>

                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.areaAreaTag"></span>:<span style="color: red">*</span></label>
                                    <select name="areaTag" aria-controls="editable-sample" class="form-control areaTag"
                                            ng-model="gameAreasData.areaTag" ng-change="valueChange()" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                        <option value="{{areaTag.value}}" ng-repeat="areaTag in areaTagList"
                                                translate="load.areaTag{{areaTag.key}}">{{areaTag.key}}
                                        </option>
                                    </select>
                                </div>

                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.areaLoadStatus"></span>:<span
                                            style="color: red">*</span></label>
                                    <select name="loadStatus" aria-controls="editable-sample"
                                            class="form-control loadStatus" ng-model="gameAreasData.loadStatus" required
                                            ng-change="valueChange()">
                                        <option value="" translate="load.pleaseSelect"></option>
                                        <option value="1" translate="load.loadStatusFree"></option>
                                        <option value="2" translate="load.loadStatusFull"></option>
                                        <option value="3" translate="load.loadStatusFluency"></option>
                                        <option value="4" translate="load.loadStatusBusy"></option>
                                    </select>
                                </div>


                            </div>
                        </div>
                    </section>
                </div>

                <%--<div class="col-sm-6 jointAccess" hidden="hidden">--%>
                <div class="col-sm-6">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.jointAccess"></span><span style="color:red;">&nbsp;&nbsp;(<span
                                translate="load.platformExplain"></span>)</span>
                        </header>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.Manner"></span>:<span
                                            style="color: red">*</span></label>
                                    <select name="manner" aria-controls="editable-sample" class="form-control"
                                            ng-model="connectType" ng-change="mannerChange(connectType)"
                                            ng-change="valueChange()">
                                        <%--<option ng-repeat="validStatus in validStatusList" value="validStatus.id">{{validStatus.code}}</option>--%>
                                        <option value="" translate="load.pleaseSelect"></option>
                                        <option value="DATABASE" translate="load.connectTypeDatabase"></option>
                                        <option value="SOCKET" translate="load.connectTypeSocket"></option>
                                    </select>
                                </div>
                            </div>

                            <div class="row dataBase" hidden="hidden">
                                <div class="col-md-2 form-group labelSpace">
                                    <label><span translate="load.DataBaseIpAddress"></span>:</label>
                                    <input type="text" class="form-control" name="ipAddress"
                                           ng-model="EnterDressDataBase.ipAddress" maxlength="50" id="DataBaseIpAddress"
                                           ng-Blur="checkVerify('DataBaseIpAddress','load.ipValidate')"
                                           pattern="^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$"
                                           ng-change="valueChange()">
                                </div>

                                <div class="col-md-2 form-group labelSpace">
                                    <label><span translate="load.DataBasePort"></span>:</label>
                                    <input type="text" class="form-control" name="port"
                                           ng-model="EnterDressDataBase.port" maxlength="50" id="DataBasePort"
                                           ng-Blur="checkVerify('DataBasePort','load.portValidate')"
                                           pattern="^([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$"
                                           ng-change="valueChange()">
                                </div>

                                <div class="col-md-2 form-group labelSpace">
                                    <label><span translate="load.DataBaseSqlName"></span>:</label><span
                                        style="color:red;">&nbsp;&nbsp;(<span translate="load.constraintLength"></span>)</span>
                                    <input type="text" class="form-control" name="sqlName"
                                           ng-model="EnterDressDataBase.sqlName" maxlength="50"
                                           ng-change="valueChange()">
                                </div>

                                <div class="col-md-2 form-group labelSpace">
                                    <label><span translate="load.DataBaseSqlAccount"></span>:</label>
                                    <input type="text" class="form-control" ng-model="EnterDressDataBase.sqlAccount"
                                           name="sqlAccount" id="gameSqlAccount" pattern="^[A-Za-z0-9]+$"
                                           ng-Blur="checkVerify('gameSqlAccount','load.lettersAndInteger')"
                                           maxlength="50" ng-change="valueChange()">

                                </div>

                                <div class="col-md-2 form-group labelSpace">
                                    <label><span translate="load.DataBaseSqlPassword"></span>:</label>
                                    <input type="password" class="form-control" maxlength="50" minlength="6"
                                           ng-model="EnterDressDataBase.sqlPassword" ng-change="valueChange()">
                                </div>

                            </div>

                            <%--  <div class="row dataBase">
                                  <div class="col-md-3 form-group">
                                      <label>Account:</label>
                                      <input type="text" class="form-control" placeholder=".col-md-3" ng-model="gameAreasData.account">
                                  </div>

                                  <div class="col-md-3 form-group">
                                      <label>password:</label>
                                      <input type="password" class="form-control" placeholder=".col-md-3" ng-model="gameAreasData.password">
                                  </div>
                              </div>--%>

                            <div class="row socket" hidden="hidden">
                                <div class="col-md-3 form-group labelSpace">
                                    <label><span translate="load.socketAddress"></span>:</label>
                                    <input type="text" class="form-control" name="socketClient"
                                           ng-model="EnterDressSocket.ipAddress" maxlength="50" id="socketAddress"
                                           ng-Blur="checkVerify('socketAddress','load.ipValidate')"
                                           pattern="^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$"
                                           ng-change="valueChange()">
                                </div>

                                <div class="col-md-3 form-group labelSpace">
                                    <label><span translate="load.socketPort"></span>:</label>
                                    <input type="text" class="form-control" name="socketClientPort"
                                           ng-model="EnterDressSocket.port" maxlength="50" id="socketPort"
                                           ng-Blur="checkVerify('socketPort','load.portValidate')"
                                           pattern="^([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$"
                                           ng-change="valueChange()">
                                </div>
                                <div class="col-md-3 form-group labelSpace">
                                    <label><span translate="load.socketKey"></span>:</label><span style="color:red;">&nbsp;&nbsp;(<span
                                        translate="load.constraintNumAndLetter"></span>)</span>
                                    <input type="text" class="form-control" name="key"
                                           ng-model="EnterDressSocket.socketKey" id="socketKey" pattern="^[A-Za-z0-9]+$"
                                           ng-Blur="checkVerify('socketKey','load.lettersAndInteger')" maxlength="50"
                                           ng-change="valueChange()">
                                </div>
                            </div>
                        </div>
                    </section>
                </div>

                <%--<div class="col-sm-6 clientConnectionEntry">--%>
                <div class="col-sm-6 ">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.clientConnectionEntry"></span><span
                                style="color:red;">&nbsp;&nbsp;(<span translate="load.clientExplain"></span>)</span>
                        </header>

                        <div class="panel-body">
                            <div class="clientContent" ng-repeat="client in clientContent.clientData">
                                <div class="row">
                                    <div class="col-md-3 form-group labelSpace">
                                        <label><span translate="load.serverName"></span>:</label><span
                                            style="color:red;">&nbsp;&nbsp;(<span
                                            translate="load.constraintLength"></span>)</span>
                                        <input type="text" class="form-control" name="serverName{{$index + 1}}"
                                               ng-model="client.serverName" id="gameAreaServerName{{$index + 1}}"
                                               pattern="^[A-Za-z0-9]+$"
                                               ng-Blur="checkVerify('gameAreaServerName{{$index + 1}}','load.lettersAndInteger')"
                                               maxlength="50" ng-change="valueChange()">
                                    </div>
                                    <div class="col-md-3 form-group labelSpace">
                                        <label><span translate="load.clientAddress"></span>:</label>
                                        <input type="text" class="form-control" name="clientAddress{{$index + 1}}"
                                               ng-model="client.ipAddress" maxlength="50"
                                               id="gameAreaClientAddress{{$index + 1}}"
                                               ng-Blur="checkVerify('gameAreaClientAddress{{$index + 1}}','load.ipValidate')"
                                               pattern="^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$"
                                               ng-change="valueChange()">
                                    </div>

                                    <div class="col-md-3 form-group labelSpace">
                                        <label><span translate="load.clientPort"></span>:</label>
                                        <input type="text" class="form-control" name="clientPort{{$index + 1}}"
                                               ng-model="client.port" maxlength="50"
                                               id="gameAreaClientPort{{$index + 1}}"
                                               ng-Blur="checkVerify('gameAreaClientPort{{$index + 1}}','load.portValidate')"
                                               pattern="^([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$"
                                               ng-change="valueChange()">
                                    </div>


                                    <button type="button" class="btn btn-danger" style="margin-top: 25px;"
                                            href="javascript:" ng-show="clientContent.canDescReply"
                                            ng-click="clientContent.clientDelete($index)"><span
                                            translate="load.Delte"></span>&nbsp<i class="fa fa-times"></i></button>
                                </div>
                            </div>
                            <div class="clientFoot">
                                <button type="button" class="btn btn-success" href="javascript:"
                                        ng-click="clientContent.clientAdd(clientCount)"><span
                                        translate="load.add"></span>&nbsp<i class="fa fa-plus"></i></button>
                            </div>

                        </div>
                    </section>
                </div>

                <div class="col-sm-6">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.channelInclude"></span><span style="color:red;">&nbsp;&nbsp;</span>
                        </header>

                        <div class="panel-body">
                            <div class="row">
                                <aside class="col-md-3">
                                    <h4 class="drg-event-title"><span translate="load.siteAdd"></span></h4>
                                    <div id='external-events'>
                                        <div class='external-event label label-primary'
                                             data-ng-repeat="code in channelCode" translate="{{code}}">{{code}}
                                        </div>
                                        <br/>
                                    </div>
                                </aside>
                                <button class="btn btn-warning" type="button" ng-click="channelClick()"><span
                                        translate="load.channelList"></span></button>

                            </div>
                            <div class="modal-footer">
                                <div style="float: right;margin-right: 20px;">
                                    <button type="button" class="btn btn-default" href="javascript:"
                                            onclick="history.back(); "><span translate="load.Cancel"></span></button>
                                    <button type="submit" class="btn btn-primary addSubmit"><span
                                            translate="load.Ok"></span></button>
                                </div>
                                <p ng-hide="!gameAreaForm.$invalid" style="color: red;float: right;margin-top: 15px;">
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


