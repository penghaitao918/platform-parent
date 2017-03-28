<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
.profileDiv{margin-top: 7px;}
</style>
<div >
    <div class="row">
        <div class="col-sm-6">
            <section class="panel">
                <header class="panel-heading">
                    <span translate="load.userBasicInfo"></span>
                </header>
                <div class="panel-body" ng-controller="profileController">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">{{profileData.loginName}}</h3>
                        </div>
                        <div class="panel-body">
                            <form id="default" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-2 col-sm-2 control-label"><span translate="load.userLoginName"></span>:</label>
                                <div class="col-md-6 col-sm-6 profileDiv" >
                                    {{profileData.loginName}}
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 col-sm-2 control-label"><span translate="load.userNikeName"></span>:</label>
                                <div class="col-md-6 col-sm-6 profileDiv" >
                                    {{profileData.nikeName}}
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 col-sm-2 control-label"><span translate="load.userRealName"></span>:</label>
                                <div class="col-md-6 col-sm-6 profileDiv" >
                                    {{profileData.realName}}
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 col-sm-2 control-label"><span translate="load.userMobile"></span>:</label>
                                <div class="col-md-6 col-sm-6 profileDiv" >
                                    {{profileData.mobile}}
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 col-sm-2 control-label"><span translate="load.userEmail"></span>:</label>
                                <div class="col-md-6 col-sm-6 profileDiv" >
                                    {{profileData.email}}
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 col-sm-2 control-label"><span translate="load.userMemo"></span>:</label>
                                <div class="col-md-6 col-sm-6 profileDiv" >
                                    {{profileData.memo}}
                                </div>
                            </div>
                            </form>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" href="javascript:" ng-click="changePasswordCapacity(user.id,user.loginName)" ><span translate="load.userChangePassword"></span><i class="fa fa-edit"></i></button>
                                <button type="button" class="btn btn-default" href="javascript:" onclick="history.back(); "><span translate="load.return"></span></button>
                            </div>
                        </div>

                        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="userModalForPasswordUpdate" class="modal fade">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                        <h4 class="modal-title"><span translate="load.userChangePassword"></span></h4>
                                    </div>
                                    <div class="modal-body">
                                        <form  ng-submit="userPasswordForm()" name="userPasswordForms">
                                            <div class="form-group">
                                                <label><span translate="load.userOldPassword"></span><label style="color: red;">*</label></label>
                                                <input class="form-control oldPassword"  name="oldPassword" type="password"  minlength="6"  maxlength="50"  ng-model="userPasswordData.oldPassword" required/>
                                            <span ng-show="userPasswordForms.oldPassword.$error.minlength" style="color: red;">
                                                <span translate="load.passwordLength"></span>
                                             </span>
                                            </div>
                                            <div class="form-group ">
                                                <label><span translate="load.userNewPassword"></span><label style="color: red;">*</label></label><span style="color:red;">&nbsp;&nbsp;(<span translate="load.passwordValidate"></span>)</span>
                                                <input class="form-control newPassword"  name="newPassword"  minlength="6"  maxlength="50"  type="password" maxlength="6" ng-model="userPasswordData.newPassword" required/>
                                            <span ng-show="userPasswordForms.newPassword.$error.minlength" style="color: red;">
                                                <span translate="load.passwordLength"></span>
                                             </span>
                                            </div>
                                            <div class="form-group ">
                                                <label><span translate="load.userConfirmPassword"></span><label style="color: red;">*</label></label>
                                                <input class="form-control confirmPassword"  name="confirmPassword"  minlength="6"  maxlength="50"  type="password"  ng-model="userPasswordData.confirmPassword" required/>
                                            <span ng-show="userPasswordForms.confirmPassword.$error.minlength" style="color: red;">
                                                <span translate="load.passwordLength"></span>
                                             </span>
                                            </div>
                                            <div class="modal-footer" style="width: 550px;">
                                                <button type="button" class="btn btn-default" data-dismiss="modal"><span translate="load.Cancel"></span></button>
                                                <button type="submit" class="btn btn-primary addSubmit"   ng-disabled="userPasswordForms.$invalid" > <span translate="load.Ok"></span></button>
                                                <p ng-hide="!userPasswordForms.$invalid" style="color: red;margin-top: 15px;"><span translate="load.remindValidate"></span></p>
                                                <p ng-hide="passwordJudge!=0" style="color: red;margin-top: 15px;"><span translate="load.userPasswordPrompt"></span></p>
                                                <p ng-hide="passwordJudge!=2" style="color: red;margin-top: 15px;"><span translate="load.userPasswordPromptFormer"></span></p>
                                            </div>
                                        </form>
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
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>

