<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%--<script src="${ctx}/resource/adminex/js/multi-select-init.js"></script>--%>
<script src="${ctx}/resource/adminex/js/icheck-init.js"></script>

<script>

</script>
<section >
    <div  ng-controller="customFormEditController">
        <form role="form" ng-submit="customFormEditForm()" id="customFormEditForm" name="customFormEditForms">
            <div class="row">
                <div class="col-sm-6">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.basicInfo"></span><span style="color:red;">&nbsp;&nbsp;(<span translate="load.gameRegionAttention"></span>)</span>
                        </header>
                        <div class="panel-body" >
                            <div class="row">
                                <div class="col-md-3 form-group labelSpace">
                                    <label><span translate="load.GameId"></span>:<span style="color: red">*</span></label>
                                    <select name="gameList" aria-controls="editable-sample" class="form-control gameList"   ng-options="game.id as game.code|translate for game in gameList"   ng-model="customFormEditFormData.gameId" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                    </select>
                                </div>
                                <div class="col-md-3 form-group labelSpace">
                                    <label><span translate="load.customFormName"></span>:<span style="color: red">*</span></label>
                                    <input type="text" class="form-control"   ng-model="customFormEditFormData.formName" required   maxlength="20">
                                </div>

                                <div class="col-md-3 form-group labelSpace">
                                    <label><span translate="load.itemItemType"></span>:<span style="color: red">*</span></label>
                                    <select name="itemType" aria-controls="editable-sample" class="form-control itemType" ng-model="customFormEditFormData.itemType" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                        <option value="{{itemType.id}}" ng-repeat="itemType in itemTypeList" translate="load.itemType{{itemType.code}}"></option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>

                <div class="col-sm-6">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.applicationFormInfo"></span><span style="color:red;">&nbsp;&nbsp;(<span translate="load.controlWarning"></span>)</span>
                        </header>
                        <div class="panel-body" >
                            <div class="row">
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.controlCode"></span>:<span style="color: red">*</span></label>
                                    <input type="text" class="form-control"   ng-model="formData.formCode"  maxlength="20">
                                    <input hidden ng-model="formData.formValue" >
                                </div>

                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.controlName"></span>:<span style="color: red">*</span></label>
                                    <input type="text" class="form-control"   ng-model="formData.formName"    maxlength="20">
                                </div>

                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.controlType"></span>:</label>
                                    <select name="gameList" aria-controls="editable-sample" class="form-control"    ng-model="formData.formType"  >
                                        <option value="" translate="load.pleaseSelect"></option>
                                        <option value="text" translate="load.SelectText"></option>
                                        <option value="number" translate="load.SelectNum"></option>
                                        <option value="date" translate="load.SelectDate"></option>
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.controlUnit"></span>:</label>
                                    <input type="text" class="form-control"   ng-model="formData.formUnit"    maxlength="20">
                                </div>


                                <div class="col-md-4 form-group labelSpace">
                                    <label><span translate="load.controlRequired"></span>:</label>
                                    <select name="gameList" aria-controls="editable-sample" class="form-control"    ng-model="formData.formRequired" >
                                        <option value="" translate="load.SelectNoRequired"></option>
                                        <option value="required" translate="load.SelectRequired"></option>
                                    </select>
                                </div>


                                <div class="col-md-4 form-group">
                                    <div style="float: right;margin-right: 20px;margin-top: 30px;">
                                        <button type="button" class="btn btn-default" href="javascript:" ng-click="formEditCancel()"><span translate="load.Cancel"></span></button>
                                        <button type="button" class="btn btn-primary" ng-click="formEdit()"> <span translate="load.AddForm"></span></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>


                <div class="col-sm-6">
                    <section class="panel">
                        <header class="panel-heading">
                            <span translate="load.SceneSimulation"></span>
                        </header>
                        <div class="panel-body" >
                            <div class="row" >
                                <div class="col-md-4 form-group labelSpace" ng-repeat="analog in analogData">
                                    <label>{{analog.formName}}:<span style="color: red" ng-hide="analog.formRequired==required">*</span></label>
                                    <div class="iconic-input right">
                                        <i >{{analog.formUnit}}</i>
                                        <input type={{analog.formType}} class="form-control"  {{analog.formRequired}} name="{{analog.formCode}}"  maxlength="20">
                                    </div>

                                </div>
                                <div class="col-md-4 form-group" >
                                    <div style="float: left;margin-top: 25px;" ng-hide="analogData.length==0">
                                        <button type="button" class="btn btn-info" href="javascript:" ng-click="ResetForm()"><span translate="load.ResetForm"></span></button>
                                    </div>
                                </div>
                            </div>


                        </div>
                        <div class="modal-footer">
                            <div style="float: right;margin-right: 20px;">
                                <button type="button" class="btn btn-default" href="javascript:" onclick="history.back(); "><span translate="load.Cancel"></span></button>
                                <button type="submit" class="btn btn-primary addSubmit" > <span translate="load.Ok"></span></button>
                            </div>
                            <p ng-hide="!customFormEditForms.$invalid" style="color: red;float: right;margin-top: 15px;"><span translate="load.remindValidate"></span></p>
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


