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
                <div class="panel-body" ng-controller="goodsQueryController">

                    <div class="panel-body" ng-hide="goodsQuerySearchBox==1">
                        <form class="form-horizontal " ng-submit="goodsQuerySearchForm()" id="goodsQuerySearchForm"
                              name="goodsQuerySearchForms">

                            <div class="form-group">
                                <label class="control-label col-md-3"><span translate="load.GameId"></span>:<span
                                        style="color: red">*</span></label>
                                <div class="col-md-4 col-xs-11">
                                    <select name="gameList" aria-controls="editable-sample"
                                            class="form-control gameList"
                                            ng-options="game.id as game.code|translate for game in gameList"
                                            ng-model="goodsQuerySearchData.gameId"  ng-change="gameIdChange(goodsQuerySearchData.gameId)"  required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3"><span translate="load.serverId"></span>:<span
                                        style="color: red">*</span></label>
                                <div class="col-md-4 col-xs-11">
                                    <select aria-controls="editable-sample" class="form-control"
                                            ng-model="goodsQuerySearchData.serverId"
                                            ng-options="area.id as area.areaName for area in areaLists" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3"><span
                                        translate="load.roleSearchRoleId"></span></label>
                                <div class="col-md-4 col-xs-11">
                                    <input type="text" class="form-control" maxlength="50"
                                           ng-model="goodsQuerySearchData.roleId">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3"><span translate="load.roleSearchRoleName"></span></label>
                                <div class="col-md-4 col-xs-11">
                                    <input type="text" class="form-control" maxlength="50"
                                           ng-model="goodsQuerySearchData.roleName">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3"><span translate="load.userId"></span></label>
                                <div class="col-md-4 col-xs-11">
                                    <input type="text" class="form-control" maxlength="50"
                                           ng-model="goodsQuerySearchData.userId">
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

                    <div class="panel-body" ng-hide="goodsQuerySearchBox==0">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title" translate="load.playerInfo"></h3>
                            </div>
                            <div class="panel-body" style="width: 100%;">
                                <ul class="p-info" ng-hide="goodsQueryItems.length==0">
                                    <div class="panel-body" style="float: left;width: 500px;height: 100px;"
                                         ng-repeat="item in goodsQueryItems">
                                        <div style="float: left;">
                                            <div class="title" style="width: 150px;font-weight:bold;">{{item.name}}：
                                            </div>
                                            <div class="desk"
                                                 style="width: 250px; text-decoration: none;  overflow: hidden;  text-overflow: ellipsis; display: -webkit-box; -webkit-line-clamp:3;  -webkit-box-orient: vertical;"
                                                 title="{{item.value}}">{{item.value}}
                                            </div>
                                        </div>
                                    </div>
                                    <%--<li ng-repeat="item in playersItems">--%>
                                    <%--<div class="title">{{item.name}}</div>--%>
                                    <%--<div class="desk" style="width: 500px;">{{item.value}}</div>--%>
                                    <%--</li>--%>
                                </ul>
                                <div ng-hide="goodsQueryItems.length!=0"  translate="load.notFindPlayer">
                                </div>
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