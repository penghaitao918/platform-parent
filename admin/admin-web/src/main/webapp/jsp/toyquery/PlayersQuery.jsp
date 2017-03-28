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
                <div class="panel-body" ng-controller="playersController">

                    <div class="panel-body" ng-hide="playersSearchBox==1">
                        <form class="form-horizontal " ng-submit="playersSearchForm()" id="playersSearchForm"
                              name="playersSearchForms">

                            <div class="form-group">
                                <label class="control-label col-md-3"><span translate="load.GameId"></span>:<span
                                        style="color: red">*</span></label>
                                <div class="col-md-4 col-xs-11">
                                    <select name="gameList" aria-controls="editable-sample"
                                            class="form-control gameList"
                                            ng-options="game.id as game.code|translate for game in gameList"
                                            ng-model="playersSearchData.gameId"
                                            ng-change="gameIdChange(playersSearchData.gameId)" required>
                                        <option value="" translate="load.pleaseSelect"></option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3"><span translate="load.LoadReqServerId"></span>:<span
                                        style="color: red">*</span></label>
                                <div class="col-md-4 col-xs-11">
                                    <select aria-controls="editable-sample" class="form-control"
                                            ng-model="playersSearchData.serverId"
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
                                           ng-model="playersSearchData.roleId">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3"><span translate="load.roleSearchRoleName"></span></label>
                                <div class="col-md-4 col-xs-11">
                                    <input type="text" class="form-control" maxlength="50"
                                           ng-model="playersSearchData.roleName">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3"><span translate="load.userId"></span></label>
                                <div class="col-md-4 col-xs-11">
                                    <input type="text" class="form-control" maxlength="50"
                                           ng-model="playersSearchData.userId">
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

                    <div class="panel-body" ng-hide="playersSearchBox==0">
                        <div class="panel panel-primary">
                            <%--<div class="panel-heading">--%>
                            <%--<h3 class="panel-title" translate="load.playerInfo"></h3>--%>
                            <%--</div>--%>
                            <%--<div class="panel-body" style="width: 100%;">--%>

                            <%--</div>--%>

                            <section class="panel">
                                <header class="panel-heading custom-tab">
                                    <ul class="nav nav-tabs">
                                        <li ng-repeat="parentMenu in parentMenu" class="{{parentMenu.active}}"
                                            ng-click="menuClick(parentMenu.menuName)">
                                            <a data-toggle="tab" style="cursor:pointer;"
                                               translate="{{parentMenu.menuName}}"></a>
                                        </li>
                                    </ul>
                                </header>
                                <div class="panel-body">
                                    <div class="tab-content">
                                        <div class="tab-pane active" ng-show="tabPaneState=='PlayersQueryManage'">
                                            <form class="form-horizontal " ng-submit="playersUpdateForm()"
                                                  id="playersUpdateForm" name="playersUpdateForms">
                                                <ul class="p-info" ng-hide="playersItems.length==0">
                                                    <div class="panel-body"
                                                         style="float: left;width: 500px;height: 100px;"
                                                         ng-repeat="item in playersItems">
                                                        <div style="float: left;">
                                                            <div class="title"
                                                                 style="width: 150px;font-weight:bold;"><span
                                                                    translate="{{item.name}}"></span>：
                                                            </div>
                                                            <div ng-show="item.identity==-1" class="desk"
                                                                 style="width: 250px; text-decoration: none;  overflow: hidden;  text-overflow: ellipsis; display: -webkit-box; -webkit-line-clamp:3;  -webkit-box-orient: vertical;"
                                                                 title="{{item.value}}">{{item.value}}
                                                            </div>
                                                            <textarea ng-show="item.identity==0"
                                                                      style="width: 250px;height: 80px;resize:none;"
                                                                      class="form-control"
                                                                      ng-model="this[item.dataTag]"></textarea>
                                                        </div>
                                                    </div>
                                                </ul>
                                                <div ng-hide="playersItems.length!=0" translate="load.notFindPlayer">
                                                </div>
                                                <div class="col-sm-6">
                                                    <div class="modal-footer">
                                                        <div style="float: right;margin-right: 20px;">
                                                            <button type="button" class="btn btn-primary"
                                                                    href="javascript:"
                                                                    ng-show="condition==1"
                                                                    ng-click="playersTransform()"><span
                                                                    translate="load.update"></span>
                                                            </button>
                                                            <button ng-show="condition==2" type="submit"
                                                                    class="btn btn-primary"
                                                                    href="javascript:"><span
                                                                    translate="load.Ok"></span></button>
                                                            <button type="button" class="btn btn-default"
                                                                    href="javascript:"
                                                                    ng-click="returnSearchBox()"><span
                                                                    translate="load.return"></span></button>
                                                        </div>
                                                    </div>
                                                </div>

                                            </form>
                                        </div>

                                        <div class="tab-pane active" ng-show="tabPaneState=='HeroQueryManage'">
                                            <table class="table table-striped">
                                                <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th translate="load.GetHeroResId"></th>
                                                    <th translate="load.GetHeroResName"></th>
                                                    <th translate="load.GetHeroResLv"></th>
                                                    <th translate="load.GetHeroResExp"></th>
                                                    <th translate="load.GetHeroResQuality"></th>
                                                    <th translate="load.GetHeroResSkillCount"></th>
                                                    <th translate="load.GetHeroResEquipCount"></th>
                                                    <th translate="load.operation"></th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr ng-repeat="hero in heroData">
                                                    <td>{{$index + 1}}</td>
                                                    <td>{{hero.GetHeroResId}}</td>
                                                    <td>{{hero.GetHeroResName}}</td>
                                                    <td>{{hero.GetHeroResLv}}</td>
                                                    <td>{{hero.GetHeroResExp}}</td>
                                                    <td>{{hero.GetHeroResQuality}}</td>
                                                    <td>{{hero.GetHeroResSkillCount}}</td>
                                                    <td>{{hero.GetHeroResEquipCount}}</td>
                                                    <td style="vertical-align:middle">
                                                        <a ng-click="heroUpdateClicked(hero)"
                                                           style="cursor:pointer;color: navy"><span
                                                                translate="load.heroUpdate"></span><i
                                                                class="fa fa-edit"></i></a>
                                                        <a ng-click="skillViewClicked(hero)"
                                                           style="cursor:pointer;color: navy"><span
                                                                translate="load.heroSkillView"></span><i
                                                                class="fa fa-edit"></i></a>
                                                        <a ng-click="equipViewClicked(hero)"
                                                           style="cursor:pointer;color: navy"><span
                                                                translate="load.heroEquipView"></span><i
                                                                class="fa fa-edit"></i></a>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>

                                        <div class="tab-pane active" ng-show="tabPaneState=='ItemQueryManage'">
                                            <table class="table table-striped">
                                                <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th translate="load.GetItemResItemId"></th>
                                                    <th translate="load.GetItemResItemCount"></th>
                                                    <th translate="load.operation"></th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr ng-repeat="item in itemData">
                                                    <td>{{$index + 1}}</td>
                                                    <td>{{item.GetItemResItemId}}</td>
                                                    <td>{{item.GetItemResItemCount}}</td>
                                                    <td style="vertical-align:middle">
                                                        <a ng-click="itemUpdateClicked(item)"
                                                           style="cursor:pointer;color: navy"><span
                                                                translate="load.update"></span><i
                                                                class="fa fa-edit"></i></a>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>

                                        <div class="tab-pane active" ng-show="tabPaneState=='MailQueryManage'">

                                            <div class="clearfix">
                                                <div class="btn-group" style="float: right">
                                                    <a class="btn btn-danger" ng-click="mailDelete()"><span
                                                            translate="load.batchDelte"></span>&nbsp<i
                                                            class="fa fa-times"></i></a>
                                                </div>

                                            </div>
                                            <table class="table table-striped">
                                                <thead>
                                                <tr>
                                                    <th></th>
                                                    <th>#</th>
                                                    <th translate="load.GetMailResMailId"></th>
                                                    <th translate="load.GetMailResTitle"></th>
                                                    <th translate="load.GetMailResGetTime"></th>
                                                    <th translate="load.GetMailResAccessoryCount"></th>
                                                    <th translate="load.GetMailResAccessoryIsGet"></th>
                                                    <th translate="load.operation"></th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr ng-repeat=" mail in mailData">
                                                    <td align="center"><input type="checkbox" class="checkbox"
                                                                              data-ng-click="mailCheck(mail.GetMailResMailId,chk)"
                                                                              data-ng-model="chk" value=""
                                                                              style="width:50px;"></td>
                                                    <td>{{$index + 1}}</td>
                                                    <td>{{mail.GetMailResMailId}}</td>
                                                    <td>{{mail.GetMailResTitle}}</td>
                                                    <td>{{mail.GetMailResGetTime}}</td>
                                                    <td>{{mail.GetMailResAccessoryCount}}</td>
                                                    <td>{{mail.GetMailResAccessoryIsGet}}</td>
                                                    <td style="vertical-align:middle">
                                                        <a ng-click="accessoryViewClicked(mail)"
                                                           style="cursor:pointer;color: navy"><span
                                                                translate="load.accessoryItemView"></span><i
                                                                class="fa fa-edit"></i></a>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>

                                        <div class="tab-pane active" ng-show="tabPaneState=='RuneQueryManage'">
                                            <table class="table table-striped">
                                                <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th translate="load.GetRuneResId"></th>
                                                    <th translate="load.GetRuneResType"></th>
                                                    <th translate="load.GetRuneResLv"></th>
                                                    <th translate="load.operation"></th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr ng-repeat="rune in runeData">
                                                    <td>{{$index + 1}}</td>
                                                    <td>{{rune.GetRuneResId}}</td>
                                                    <td>{{rune.GetRuneResType}}</td>
                                                    <td>{{rune.GetRuneResLv}}</td>
                                                    <td style="vertical-align:middle">
                                                        <a ng-click="runeUpdateClicked(rune)"
                                                           style="cursor:pointer;color: navy"><span
                                                                translate="load.update"></span><i
                                                                class="fa fa-edit"></i></a>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>

                                        <div class="tab-pane active" ng-show="tabPaneState=='FriendQueryManage'">
                                            <div class="clearfix">
                                                <div class="btn-group" style="float: right">
                                                    <a class="btn btn-danger" ng-click="friendDelete()"><span
                                                            translate="load.batchDelte"></span>&nbsp<i
                                                            class="fa fa-times"></i></a>
                                                </div>

                                            </div>
                                            <table class="table table-striped">
                                                <thead>
                                                <tr>
                                                    <th></th>
                                                    <th>#</th>
                                                    <th translate="load.GetFriendResID"></th>
                                                    <th translate="load.GetFriendResName"></th>
                                                    <th translate="load.GetFriendResLv"></th>
                                                    <th translate="load.GetFriendResIsOnline"></th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr ng-repeat="friend in friendData">
                                                    <td align="center"><input type="checkbox" class="checkbox"
                                                                              data-ng-click="friendCheck(friend.GetFriendResID,chk)"
                                                                              data-ng-model="chk" value=""
                                                                              style="width:50px;"></td>
                                                    <td>{{$index + 1}}</td>
                                                    <td>{{friend.GetFriendResID}}</td>
                                                    <td>{{friend.GetFriendResName}}</td>
                                                    <td>{{friend.GetFriendResLv}}</td>
                                                    <td>{{friend.GetFriendResIsOnline}}</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </section>
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

                        <div aria-hidden="true" role="dialog" tabindex="-1" id="mailMessagesConfirm" class="modal fade">
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
                                                ng-click="mailConfirm()"><span translate="load.Ok"></span></button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div aria-hidden="true" role="dialog" tabindex="-1" id="friendMessagesConfirm" class="modal fade">
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
                                                ng-click="friendConfirm()"><span translate="load.Ok"></span></button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>


                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                         id="heroUpdateViewModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.heroUpdate"></span></h4>
                                </div>
                                <div class="modal-body">

                                    <form role="form" ng-submit="heroUpdateForm()" id="heroUpdateForm"
                                          name="heroUpdateForms">


                                        <div class="form-group labelSpace">
                                            <label><span translate="load.UpdateHeroReqHeroId"></span>:<span
                                                    style="color: red">*</span></label>
                                            <input type="text" class="form-control updateHeroId" name="updateHeroId"
                                                   ng-model="heroUpdateData.UpdateHeroReqHeroId" readonly required>
                                        </div>

                                        <div class="form-group labelSpace">
                                            <label><span translate="load.GetHeroResName"></span>:<span
                                                    style="color: red">*</span></label>
                                            <input type="text" class="form-control heroName" name="heroName"
                                                   ng-model="heroUpdateData.GetHeroResName" readonly required>
                                        </div>
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.UpdateHeroReqExp"></span>:<span
                                                    style="color: red">*</span></label>
                                            <input type="text" class="form-control heroExp" name="heroExp"
                                                   ng-model="heroUpdateData.UpdateHeroReqExp" required>
                                        </div>
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.UpdateHeroReqQuality"></span>:<span
                                                    style="color: red">*</span></label>
                                            <input type="text" class="form-control heroQuality" name="heroQuality"
                                                   ng-model="heroUpdateData.UpdateHeroReqQuality" required>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                                    translate="load.Cancel"></span></button>
                                            <button type="submit" class="btn btn-warning">
                                                <span translate="load.Ok"></span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                         id="itemUpdateViewModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.itemCountUpdate"></span></h4>
                                </div>
                                <div class="modal-body">

                                    <form role="form" ng-submit="itemUpdateForm()" id="itemUpdateForm"
                                          name="itemUpdateForms">
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.UpdateItemReqItemCount"></span>:<span
                                                    style="color: red">*</span></label>
                                            <input type="text" class="form-control itemCount" name="itemCount"
                                                   ng-model="itemUpdateData.UpdateItemReqItemCount" required>
                                        </div>


                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                                    translate="load.Cancel"></span></button>
                                            <button type="submit" class="btn btn-warning">
                                                <span translate="load.Ok"></span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                         id="equipViewModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.heroEquipTitle"></span></h4>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <aside style="margin-left: 10px;">
                                            <h4 class="drg-event-title"><span translate="load.heroEquipList"></span>
                                            </h4>
                                            <div id='external-events'>
                                                <div class='external-event label label-primary'
                                                     data-ng-repeat="code in heroEquipCode"
                                                     translate={{code.GetHeroResEquipId}}></div>
                                                <br/>
                                            </div>
                                        </aside>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                                    translate="load.Cancel"></span></button>
                                            <button type="button" data-dismiss="modal" class="btn btn-warning">
                                                <span translate="load.Ok"></span></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                         id="mailViewModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.accessoryItemTitle"></span></h4>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <aside style="margin-left: 10px;">
                                            <h4 class="drg-event-title"><span translate="load.accessoryItemList"></span>
                                            </h4>
                                            <div>
                                                <div class='external-event label label-primary'
                                                     data-ng-repeat="code in mailItemCode"
                                                     translate={{code.GetMailResAccessoryItemId}}></div>
                                                <br/>
                                            </div>
                                        </aside>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                                    translate="load.Cancel"></span></button>
                                            <button type="button" data-dismiss="modal" class="btn btn-warning">
                                                <span translate="load.Ok"></span></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                         id="skillViewModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.heroSkillTitle"></span></h4>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <aside style="margin-left: 10px;">
                                            <h4 class="drg-event-title"><span translate="load.heroSkillList"></span>
                                            </h4>
                                            <div>
                                                <div class='external-event label label-primary'
                                                     data-ng-repeat="code in heroSkillCode"
                                                     translate={{code.UpdateHeroReqSkilliD}}></div>
                                                <br/>
                                            </div>
                                        </aside>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                                    translate="load.Cancel"></span></button>
                                            <button type="button" data-dismiss="modal" class="btn btn-warning">
                                                <span translate="load.Ok"></span></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                         id="runeUpdateViewModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.runeLvUpdate"></span></h4>
                                </div>
                                <div class="modal-body">

                                    <form role="form" ng-submit="runeUpdateForm()" id="runeUpdateForm"
                                          name="runeUpdateForms">
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.GetRuneResLv"></span>:<span
                                                    style="color: red">*</span></label>
                                            <input type="text" class="form-control runeLv" name="runeLv"
                                                   ng-model="runeUpdateData.UpdateRuneReqLv" required>
                                        </div>


                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                                    translate="load.Cancel"></span></button>
                                            <button type="submit" class="btn btn-warning">
                                                <span translate="load.Ok"></span></button>
                                        </div>
                                    </form>
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