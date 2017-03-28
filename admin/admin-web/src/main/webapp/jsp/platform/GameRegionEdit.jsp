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
<section >
    <div ng-controller="gameRegionEditController">
        <form role="form" ng-submit="gameRegionEditForm()" id="gameRegionEditForm" name="gameRegionEditForms">
    <div class="row">
        <div class="col-sm-6">
            <section class="panel">
                <header class="panel-heading">
                    <span translate="load.basicInfo"></span>
                </header>
                <div class="panel-body" >
                    <div class="row">

                    <div class="col-md-3 form-group labelSpace">
                        <label><span translate="load.GameId"></span>:<span style="color: red">*</span></label>

                        <select name="gameList" aria-controls="editable-sample" class="form-control gameList"  ng-change="valueChange()"  ng-options="game.id as game.code|translate for game in gameList"    ng-model="gameRegionData.gameId" required>
                            <option value="" translate="load.pleaseSelect"></option>
                        </select>

                        <%--<select name="gameList" aria-controls="editable-sample" class="form-control gameList gameRegion" ng-model="gameRegionData.gameId" ng-change="valueChange()"  required>
                            <option value="" translate="load.pleaseSelect"></option>
                            <option value={{game.id}} ng-repeat="game in gameList" ng-selected="gameSelected" translate={{game.code}}></option>
                        </select>--%>

                    </div>

                    <div class="col-md-3 form-group labelSpace">
                        <label><span translate="load.areaGroupCode"></span>:<span style="color: red">*</span></label></span>
                        <input type="number" class="form-control" name="areaGroupCode" min="0" max="100000000000" id="areaGroupCode"  ng-change="valueChange()"  ng-model="gameRegionData.areaGroupCode"  required>
                    </div>
                    </div>

                    <div class="row">
                        <div class="col-md-3 form-group labelSpace">
                            <label><span translate="load.areaGroupName"></span>:<span style="color: red">*</span></label><span style="color:red;">&nbsp;&nbsp;(<span translate="load.constraintLength"></span>)</span>
                            <input type="text" class="form-control" maxlength="50"  ng-change="valueChange()" ng-model="gameRegionData.areaGroupName"  required>
                        </div>

                        <div class="col-md-3 form-group labelSpace">
                            <label><span translate="load.displayOrder"></span>:<span style="color: red">*</span></label><span style="color:red;">&nbsp;&nbsp;(<span translate="load.constraintNum"></span>)</span>
                            <input type="number"  class="form-control" min="0" max="100000000000" ng-change="valueChange()" size="16" name="displayOrder"   ng-model="gameRegionData.displayOrder"  required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-3 form-group labelSpace">
                            <label><span translate="load.areaGroupDesc"></span>:<span style="color: red">*</span></label><span style="color:red;">&nbsp;&nbsp;(<span translate="load.constraintTextAreaLength"></span>)</span>
                           <%-- <input type="text" class="form-control" ng-change="valueChange()"  ng-model="gameRegionData.areaGroupDesc" required   maxlength="20">--%>
                            <textarea class="form-control" style="height: 70px;resize:none;" class="form-control" ng-change="valueChange()"  ng-model="gameRegionData.areaGroupDesc" required   maxlength="100"></textarea>
                        </div>
                    </div>
                </div>
            </section>
        </div>




        <div class="col-sm-6">
            <section class="panel">
                <header class="panel-heading">
                    <span translate="load.jointAccess"></span>
                </header>
                <div class="panel-body" >
                    <div class="row">
                        <div class="col-md-4 form-group labelSpace">
                            <label><span translate="load.Manner"></span>:<span style="color: red">*</span></label>
                            <select name="Manner" aria-controls="editable-sample" class="form-control Manner" ng-model="connectType" ng-change="mannerChange(connectType)" required  ng-change="valueChange()">
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
                            <input type="text" class="form-control" name="ipAddress"  ng-model="EnterDressDataBase.ipAddress" maxlength="50" id="gameRegionDataDataBaseIpAddress" ng-Blur="checkVerify('gameRegionDataDataBaseIpAddress','load.ipValidate')" pattern="^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$" ng-change="valueChange()">
                        </div>

                        <div class="col-md-2 form-group labelSpace">
                            <label><span translate="load.DataBasePort"></span>:</label>
                            <input type="text" class="form-control" name="port"  ng-model="EnterDressDataBase.port" maxlength="50"  id="gameRegionDataDataBasePort" ng-Blur="checkVerify('gameRegionDataDataBasePort','load.portValidate')"  pattern="^([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$"  ng-change="valueChange()">

                        </div>

                        <div class="col-md-2 form-group labelSpace">
                            <label><span translate="load.DataBaseSqlName"></span>:</label><span style="color:red;">&nbsp;&nbsp;(<span translate="load.constraintLength"></span>)</span>
                            <input type="text" class="form-control" name="sqlName" ng-change="valueChange()" ng-model="EnterDressDataBase.sqlName"   maxlength="50">
                        </div>

                        <div class="col-md-2 form-group labelSpace">
                            <label><span translate="load.DataBaseSqlAccount"></span>:</label>
                            <input type="text" class="form-control" ng-change="valueChange()" ng-model="EnterDressDataBase.sqlAccount" name="sqlAccount" id="gameRegionSqlAccount" pattern="^[A-Za-z0-9]+$"  ng-Blur="checkVerify('gameRegionSqlAccount','load.lettersAndInteger')"  maxlength="50" >

                        </div>

                        <div class="col-md-2 form-group labelSpace">
                            <label><span translate="load.DataBaseSqlPassword"></span>:</label>
                            <input type="password" class="form-control"  ng-model="EnterDressDataBase.sqlPassword" ng-change="valueChange()">
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
                            <input type="text" class="form-control" name="socketClient" ng-model="EnterDressSocket.ipAddress" maxlength="50" id="gameRegionSocketAddress" ng-Blur="checkVerify('gameRegionSocketAddress','load.ipValidate')" pattern="^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$"  ng-change="valueChange()">
                        </div>

                        <div class="col-md-3 form-group labelSpace">
                            <label><span translate="load.socketPort"></span>:</label>
                            <input type="text" class="form-control" name="socketClientPort" ng-model="EnterDressSocket.port" maxlength="50" id="gameRegionSocketPort" ng-Blur="checkVerify('gameRegionSocketPort','load.portValidate')"  pattern="^([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$"  ng-change="valueChange()">
                        </div>
                        <div class="col-md-3 form-group labelSpace">
                            <label><span translate="load.socketKey"></span>:</label><span style="color:red;">&nbsp;&nbsp;(<span translate="load.constraintNumAndLetter"></span>)</span>
                            <input type="text" class="form-control" name="key"  ng-model="EnterDressSocket.socketKey" id="gameRegionSocketKey" pattern="^[A-Za-z0-9]+$"  ng-Blur="checkVerify('gameRegionSocketKey','load.lettersAndInteger')" maxlength="50" ng-change="valueChange()">
                        </div>
                    </div>
            </section>
        </div>

        <div class="col-sm-6 clientConnectionEntry" >
            <section class="panel">
                <header class="panel-heading">
                    <span translate="load.clientConnectionEntry"></span>
                </header>

                <div class="panel-body" >
                    <div class="clientContent" ng-repeat="client in clientContent.clientData">
                        <div class="row">
                            <div class="col-md-3 form-group labelSpace">
                                <label><span translate="load.serverName"></span>:</label><span style="color:red;">&nbsp;&nbsp;(<span translate="load.constraintLength"></span>)</span>
                                <input type="text" class="form-control" name="serverName{{$index + 1}}" ng-model="client.serverName"  id="serverName{{$index + 1}}" pattern="^[A-Za-z0-9]+$"  ng-Blur="checkVerify('serverName{{$index + 1}}','load.lettersAndInteger')" maxlength="50" ng-change="valueChange()">
                            </div>
                            <div class="col-md-3 form-group labelSpace">
                                <label><span translate="load.clientAddress"></span>:</label>
                                <input type="text" class="form-control" name="clientAddress{{$index + 1}}" ng-model="client.ipAddress" maxlength="50" id="clientAddress{{$index + 1}}" ng-Blur="checkVerify('clientAddress{{$index + 1}}','load.ipValidate')" pattern="^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$" ng-change="valueChange()">
                            </div>

                            <div class="col-md-3 form-group labelSpace">
                                <label><span translate="load.clientPort"></span>:</label>
                                <input type="text" class="form-control" name="clientPort{{$index + 1}}"  ng-model="client.port"  maxlength="50" id="clientPort{{$index + 1}}" ng-Blur="checkVerify('clientPort{{$index + 1}}','load.portValidate')"  pattern="^([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$" ng-change="valueChange()">
                            </div>


                            <button type="button" class="btn btn-danger" style="margin-top: 25px;" href="javascript:" ng-show="clientContent.canDescReply" ng-click="clientContent.clientDelete($index)"><span translate="load.Delte"></span>&nbsp<i class="fa fa-times"></i></button>
                        </div>
                    </div>
                    <div class="clientFoot">
                        <button type="button" class="btn btn-success" href="javascript:" ng-click="clientContent.clientAdd(clientCount)"><span translate="load.add"></span>&nbsp<i class="fa fa-plus"></i></button>
                    </div>

                  <%--  <div class="modal-footer">
                        <div style="float: right;margin-right: 20px;">
                            <button type="button" class="btn btn-default" href="javascript:" onclick="history.back(); "><span translate="load.Cancel"></span></button>
                            <button type="submit" class="btn btn-primary addSubmit" ng-disabled="gameRegionEditForms.$invalid"> <span translate="load.Ok"></span></button>
                        </div>
                        <p ng-hide="!gameRegionEditForms.$invalid" style="color: red;float: right;margin-top: 15px;"><span translate="load.remindValidate"></span></p>
                    </div>--%>
                </div>
            </section>
        </div>

        <div class="col-sm-6" >
            <section class="panel">
                <div class="panel-body" >
                    <div class="modal-footer">
                        <div style="float: right;margin-right: 20px;">
                            <button type="button" class="btn btn-default" href="javascript:" onclick="history.back(); "><span translate="load.Cancel"></span></button>
                            <button type="submit" class="btn btn-primary addSubmit" > <span translate="load.Ok"></span></button>
                        </div>
                        <p  style="color: red;float: right;margin-top: 15px;"><span translate="load.remindValidate"></span></p>
                    </div>
                </div>
            </section>
        </div>


    </div>
        </form>


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
</div>
</section>


