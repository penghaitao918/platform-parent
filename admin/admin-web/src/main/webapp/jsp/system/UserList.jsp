<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resource/adminex/js/multi-select-init.js"></script>
<script src="${ctx}/resource/adminex/js/icheck-init.js"></script>
<script src="${ctx}/resource/adminex/js/fuelux/js/tree.min.js"></script>
<script src="${ctx}/resource/adminex/js/tree-init.js"></script>

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
<div>
    <div class="row">
        <div class="col-sm-6">
            <section class="panel">
                <div class="panel-body" style="overflow:auto;" ng-controller="userListController">

                    <div class="clearfix">
                        <div class="btn-group">

                            <a class="btn btn-success" ng-click="addButton()"><span translate="load.add"></span>&nbsp;<i
                                    class="fa fa-plus"></i></a>

                            &nbsp&nbsp&nbsp

                            <a class="btn btn-danger" ng-click="delete()"><span translate="load.batchDelte"></span>&nbsp<i
                                    class="fa fa-times"></i></a>
                            <a class="btn btn-info" ng-click="pocClose()"><span translate="load.OneKeyLock"></span>&nbsp<i
                                    class="fa fa-flag"></i></a>
                            <a class="btn btn-info" ng-click="pocOpen()"><span translate="load.OneKeyUnlock"></span>&nbsp<i
                                    class="fa fa-flag"></i></a>
                        </div>

                        <div style="float: right;">
                            <form class="searchform" method="post">
                                <input type="text" class="form-control searchText" name="keyword"
                                       placeholder={{userSearchText|translate}} onkeydown="globelQuery(event)"/>
                                <div style="float: left;margin-top: 7px;margin-left: 20px;">
                                    <button ng-click="searchButtonClicked()" class="btn btn-primary searchButton"><span
                                            translate="load.easyRetrieval"></span>&nbsp;<i class="fa fa-search"></i>
                                    </button>
                                </div>

                            </form>
                        </div>
                    </div>


                    <section>

                        <table class="table table-striped table-condensed table-responsive">
                            <thead>
                            <tr>
                                <th></th>
                                <th>#</th>
                                <th><span translate="load.userLoginName"></span></th>
                                <th><span translate="load.userNikeName"></span></th>
                                <th><span translate="load.userLoginTime"></span></th>
                                <th><span translate="load.userLocked"></span></th>
                                <th><span translate="load.passwordOperation"></span></th>
                                <th><span translate="load.lockOperation"></span></th>
                                <th><span translate="load.operation"></span></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="user in users">
                                <td align="center"><input type="checkbox" class="checkbox"
                                                          data-ng-click="check($index,chk)" data-ng-model="chk" value=""
                                                          style="width:50px;"></td>
                                <td style="vertical-align:middle">{{user.id}}</td>
                                <td hidden="hidden">{{user.id}}</td>
                                <td style="vertical-align:middle">{{user.loginName}}</td>
                                <td style="vertical-align:middle">{{user.nikeName}}</td>
                                <td style="vertical-align:middle">{{user.loginTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                                <td style="vertical-align:middle"><span ng-hide="user.locked!=1"
                                                                        class="label label-success label-mini"
                                                                        translate="{{user.locked|NumToStr}}"></span>
                                    <span ng-hide="user.locked!=0" class="label label-danger label-mini"
                                          translate="{{user.locked|NumToStr}}"></span>
                                </td>
                                <td style="vertical-align:middle"><a
                                        ng-click="changePasswordCapacity(user.id,user.loginName)"
                                        style="cursor:pointer;color: #00a0e9"><span
                                        translate="load.userChangePassword"></span><i class="fa fa-edit"></i></a></td>
                                <td style="vertical-align:middle"><a ng-click="open()" ng-hide="user.locked==1"
                                                                     style="cursor:pointer;"><span
                                        translate="load.Unlock"></span><i class="fa fa-check"></i></a>
                                    <a ng-click="close()" ng-hide="user.locked==0"
                                       style="cursor:pointer;color: red"><span translate="load.Lock"></span><i
                                            class="fa fa-times"></i></a>
                                </td>
                                <td style="vertical-align:middle"><a ng-click="updateClicked()"
                                                                     style="cursor:pointer;color: navy"><span
                                        translate="load.update"></span><i class="fa fa-edit"></i></a>
                                    <%--  <a ng-click="roleClicked()" style= "cursor:pointer;color: #00a0e9" ><span translate="load.crownAngle"></span><i class="fa fa-gavel"></i></a>--%>
                                    <a ng-click="accreditClicked()" style="cursor:pointer; color: #00a0e9"><span
                                            translate="load.accredit"></span><i class="fa fa-gavel"></i></a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <div class="pager">
                            <pager page-count="pageCount" current-page="currentPage" on-page-change="onPageChange()"
                                   first-text="load.HomePage" next-text="load.NextPage" prev-text="load.PreviousPage"
                                   last-text="load.EndPage" show-goto="true" goto-text="load.GoToPage"></pager>
                        </div>
                    </section>


                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                         id="userModalForEdit" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.add"></span></h4>
                                </div>
                                <div class="modal-body">

                                    <form ng-submit="userEditForm()" name="userEditForms">
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.userLoginName"></span><label
                                                    style="color: red;">*</label></label>
                                            <input class="form-control loginName" name="loginName" type="text"
                                                   ng-model="userEditData.loginName" required/>
                                        </div>
                                        <div class="form-group labelSpace" ng-hide="userJudge=='update'">
                                            <label><span translate="load.userPassword"></span><label
                                                    style="color: red;">*</label><span
                                                    style="color:red;">&nbsp;&nbsp;(<span
                                                    translate="load.passwordValidate"></span>)</span></label>
                                            <input class="form-control password" name="password" id="userPassword"
                                                   type="password" minlength="6" maxlength="50"
                                                   ng-model="userEditData.password" required/>
                                        </div>
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.userNikeName"></span><label
                                                    style="color: red;">*</label></label>
                                            <input class="form-control nikeName" name="nikeName" type="text"
                                                   ng-model="userEditData.nikeName" required/>
                                        </div>
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.userRealName"></span><label
                                                    style="color: red;">*</label></label>
                                            <input class="form-control realName" name="realName" type="text"
                                                   ng-model="userEditData.realName" required/>
                                        </div>
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.userMobile"></span><label
                                                    style="color: red;">*</label></label>
                                            <input class="form-control mobile" name="mobile" type="text" maxlength="11"
                                                   id="userMobile" ng-model="userEditData.mobile"
                                                   pattern="^1[3|4|5|7|8]\d{9}$"
                                                   ng-Blur="checkVerify('userMobile','load.phoneValidate')" required/>
                                        </div>
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.userEmail"></span><label
                                                    style="color: red;">*</label></label>
                                            <input class="form-control email" name="email" type="text" id="userEmail"
                                                   ng-model="userEditData.email"
                                                   pattern="^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$"
                                                   maxlength="50"
                                                   ng-Blur="checkVerify('userEmail','load.emailValidate')" required/>
                                        </div>
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.userMemo"></span></label>
                                            <textarea class="form-control memo" name="memo" style="height: 100px;"
                                                      ng-model="userEditData.memo" optional></textarea>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                                    translate="load.Cancel"></span></button>
                                            <button type="submit" class="btn btn-primary addSubmit"><span
                                                    translate="load.Ok"></span></button>
                                            <p ng-hide="!userEditForms.$invalid" style="color: red;margin-top: 15px;">
                                                <span translate="load.remindValidate"></span></p>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                         id="userModalForPasswordUpdate" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.userChangePassword"></span></h4>
                                </div>
                                <div class="modal-body">
                                    <form ng-submit="userPasswordForm()" name="userPasswordForms" id="universalForms">
                                        <div class="form-group">
                                            <label><span translate="load.userOldPassword"></span><label
                                                    style="color: red;">*</label></label>
                                            <input class="form-control oldPassword" name="oldPassword" type="password"
                                                   minlength="6" maxlength="50" ng-model="userPasswordData.oldPassword"
                                                   required/>
                                        </div>
                                        <div class="form-group ">
                                            <label><span translate="load.userNewPassword"></span><label
                                                    style="color: red;">*</label></label><span style="color:red;">&nbsp;&nbsp;(<span
                                                translate="load.passwordValidate"></span>)</span>
                                            <input class="form-control newPassword" name="newPassword" minlength="6"
                                                   maxlength="50" type="password" maxlength="6"
                                                   ng-model="userPasswordData.newPassword" required/>
                                        </div>
                                        <div class="form-group ">
                                            <label><span translate="load.userConfirmPassword"></span><label
                                                    style="color: red;">*</label></label>
                                            <input class="form-control confirmPassword" name="confirmPassword"
                                                   minlength="6" maxlength="50" type="password"
                                                   ng-model="userPasswordData.confirmPassword" required/>
                                        </div>
                                        <div class="modal-footer" style="width: 550px;">
                                            <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                                    translate="load.Cancel"></span></button>
                                            <button type="submit" class="btn btn-primary addSubmit"><span
                                                    translate="load.Ok"></span></button>
                                            <p ng-hide="!userPasswordForms.$invalid"
                                               style="color: red;margin-top: 15px;"><span
                                                    translate="load.remindValidate"></span></p>
                                            <p ng-hide="passwordJudge!=0" style="color: red;margin-top: 15px;"><span
                                                    translate="load.userPasswordPrompt"></span></p>
                                            <p ng-hide="passwordJudge!=2" style="color: red;margin-top: 15px;"><span
                                                    translate="load.userPasswordPromptFormer"></span></p>
                                        </div>
                                    </form>
                                    <form ng-submit="userPasswordSpecialForm()" name="userPasswordSpecialForms"
                                          id="specialForms" hidden>
                                        <div class="form-group ">
                                            <label><span translate="load.userNewPassword"></span><label
                                                    style="color: red;">*</label></label><span style="color:red;">&nbsp;&nbsp;(<span
                                                translate="load.passwordValidate"></span>)</span>
                                            <input class="form-control newPassword" name="newPassword" minlength="6"
                                                   maxlength="50" type="password" maxlength="6"
                                                   ng-model="userPasswordSpecialData.newPassword" required/>
                                        </div>
                                        <div class="form-group ">
                                            <label><span translate="load.userConfirmPassword"></span><label
                                                    style="color: red;">*</label></label>
                                            <input class="form-control confirmPassword" name="confirmPassword"
                                                   minlength="6" maxlength="50" type="password"
                                                   ng-model="userPasswordSpecialData.confirmPassword" required/>
                                        </div>
                                        <div class="modal-footer" style="width: 550px;">
                                            <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                                    translate="load.Cancel"></span></button>
                                            <button type="submit" class="btn btn-primary addSubmit"><span
                                                    translate="load.Ok"></span></button>
                                            <p ng-hide="!userPasswordSpecialForms.$invalid"
                                               style="color: red;margin-top: 15px;"><span
                                                    translate="load.remindValidate"></span></p>
                                            <p ng-hide="passwordSpecialJudge!=0"
                                               style="color: red;margin-top: 15px;"><span
                                                    translate="load.userPasswordPrompt"></span></p>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>


                    <div aria-hidden="true" role="dialog" tabindex="-1" id="messagesModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
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

                    <div aria-hidden="true" role="dialog" tabindex="-1" id="messagesConfirm" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title" translate="{{messagesConfirm.title}}"></h4>
                                </div>
                                <div class="modal-body" translate="{{messagesConfirm.body}}">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                            translate="load.Cancel"></span></button>
                                    <button type="button" class="btn btn-warning" data-dismiss="modal"
                                            ng-click="confirm()"><span translate="load.Ok"></span></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


            </section>
        </div>
    </div>
</div>
