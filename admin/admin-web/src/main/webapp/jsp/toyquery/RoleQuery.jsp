<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    .radioSpace {
        float: left;
        margin-left: 10px;
    }

    .radioNorms {
        width: 20px;
        height: 20px;
        margin-top: -4px;
    }
</style>
<div>
    <div class="row">
        <div class="col-sm-6">
            <section class="panel">
                <header class="panel-heading">
                    <span style="color: red" translate="load.roleSearchPrompt"></span>
                </header>
                <div class="panel-body" ng-controller="roleSearchController">

                    <div class="panel-body" ng-hide="roleSearchBox==1">
                        <form class="form-horizontal " ng-submit="roleSearchForm()" id="roleSearchForm"
                              name="roleSearchForms">

                            <div class="form-group">
                                <label class="control-label col-md-3"><span translate="load.GameId"></span>:<span
                                        style="color: red">*</span></label>
                                <div class="col-md-4 col-xs-11">
                                    <select name="gameList" aria-controls="editable-sample"
                                            class="form-control gameList" ng-change="gameChange(roleSearchData.gameId)"
                                            ng-options="game.id as game.code|translate for game in gameList"
                                            ng-model="roleSearchData.gameId" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3"><span
                                        translate="load.applicationServer"></span>:</label>
                                <div class="col-md-4 col-xs-11">
                                    <select aria-controls="editable-sample" class="form-control"
                                            ng-model="roleSearchData.areaId"
                                            ng-options="area.id as area.areaName for area in areaLists">
                                        <option value="" translate="load.pleaseSelect"></option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3"><span
                                        translate="load.playersRoleID"></span></label>
                                <div class="col-md-4 col-xs-11">
                                    <input type="text" class="form-control" maxlength="50"
                                           ng-model="roleSearchData.roleId">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3"><span
                                        translate="load.playersRoleName"></span></label>
                                <div class="col-md-4 col-xs-11">
                                    <input type="text" class="form-control" maxlength="50"
                                           ng-model="roleSearchData.roleName">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3"><span translate="load.platformId"></span></label>
                                <div class="col-md-4 col-xs-11">
                                    <input type="text" class="form-control" maxlength="50"
                                           ng-model="roleSearchData.userId">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3"></label>
                                <div class="col-md-4 col-xs-11">
                                    <button type="button" class="btn btn-default" ng-click="empty()"><span
                                            translate="load.Empty"></span></button>
                                    <button type="submit" class="btn btn-primary"><span
                                            translate="load.checkSelect"></span></button>
                                </div>
                            </div>


                        </form>
                    </div>

                    <div class="panel-body" ng-hide="roleSearchBox==0">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title" translate="load.playerInfo"></h3>
                            </div>
                            <div class="panel-body">
                                <ul class="p-info" ng-hide="gameRole==null">
                                    <li>
                                        <div class="title" translate="load.roleSearchRoleId"></div>
                                        <div class="desk" style="width: 500px;">{{gameRole.roleId}}</div>
                                    </li>
                                    <li>
                                        <div class="title" translate="load.roleSearchRoleName"></div>
                                        <div class="desk" style="width: 500px;">{{gameRole.roleName}}</div>
                                    </li>
                                    <li>
                                        <div class="title" translate="load.roleSearchGame"></div>
                                        <div class="desk" style="width: 500px;">{{gameRole.gameId}}</div>
                                    </li>
                                    <li>
                                        <div class="title" translate="load.roleSearchArea"></div>
                                        <div class="desk" style="width: 500px;">{{gameRole.areaId}}</div>
                                    </li>
                                    <li>
                                        <div class="title" translate="load.roleSearchRoleLevel"></div>
                                        <div class="desk" style="width: 500px;">{{gameRole.roleLevel}}</div>
                                    </li>
                                    <li>
                                        <div class="title" translate="load.roleSearchLastLoginTime"></div>
                                        <div class="desk" style="width: 500px;">{{gameRole.lastLoginTime|date:'yyyy-MM-dd HH:mm:ss'}}</div>
                                    </li>
                                    <li>
                                        <div class="title" translate="load.roleSearchLastLogoutTime"></div>
                                        <div class="desk" style="width: 500px;">{{gameRole.lastLogoutTime|date:'yyyy-MM-dd HH:mm:ss'}}</div>
                                    </li>
                                    <li>
                                        <div class="title" translate="load.createTime"></div>
                                        <div class="desk" style="width: 500px;">{{gameRole.createTime|date:'yyyy-MM-dd HH:mm:ss'}}</div>
                                    </li>
                                </ul>
                                <div ng-hide="gameRole!=null" translate="load.notFindPlayer"></div>
                                <hr/>
                                <h2>
                                    <b translate="load.platformInfo"></b>
                                </h2>
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th translate="load.roleSearchAccountId"></th>
                                        <th translate="load.roleSearchAccountName"></th>
                                        <th translate="load.roleSearchSite"></th>
                                        <th translate="load.roleSearchCreateIp"></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr ng-repeat="user in userAccounts">
                                        <td>{{$index + 1}}</td>
                                        <td>{{user.accountId}}</td>
                                        <td>{{user.accountName}}</td>
                                        <td>{{user.siteId}}</td>
                                        <td>{{user.createIp}}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="modal-footer">
                                <div style="float: right;margin-right: 20px;">
                                    <button type="button" class="btn btn-default" href="javascript:"
                                            ng-click="returnSearchBox()"><span translate="load.return"></span></button>
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


<script type="text/javascript">
    function globelQuery(e) {
        if (!e)
            e = window.event;
        if ((e.keyCode || e.which) == 13) {
            $(".searchButton").click();
        }
    }

</script>