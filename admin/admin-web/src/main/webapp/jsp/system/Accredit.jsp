<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resource/adminex/js/multi-select-init.js"></script>
<script src="${ctx}/resource/adminex/js/icheck-init.js"></script>
<script src="${ctx}/resource/adminex/js/fuelux/js/tree.min.js"></script>
<%--<script src="${ctx}/resource/adminex/js/tree-init.js"></script>--%>
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
    /*
     jQuery(document).ready(function() {
     TreeView.init();
     });
     */


</script>

<div >
        <div >
            <section class="panel" >
                <div class="panel-body" style="overflow:auto;" ng-controller="accreditController">
                    <section class="panel">
                        <header class="panel-heading custom-tab">
                            <ul class="nav nav-tabs">
                                <li ng-repeat="parentMenu in parentMenu" class="{{parentMenu.active}}"
                                    ng-click="menuClick(parentMenu.id)">
                                    <a data-toggle="tab" style="cursor:pointer;" translate="{{parentMenu.menuName}}"></a>
                                </li>
                            </ul>
                        </header>
                        <div class="panel-body">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="clearfix">
                                        <div class="btn-group" style="float: right">
                                            <a  ng-click="allAuthority(true)" class="btn btn-success"><span translate="load.allAuthority"></span>&nbsp<i class="fa fa-plus"></i></a>
                                            <a class="btn btn-danger" ng-click="allAuthority(false)"><span translate="load.cancelAuthority"></span>&nbsp<i class="fa fa-times"></i></a>
                                        </div>
                                    </div>
                                    <table class="table table-striped table-condensed table-responsive">
                                        <thead>
                                        <tr>
                                            <th>#</th>
                                            <th><span translate="load.menuMenuName"></span></th>
                                            <th><span translate="load.checkAdd"></span></th>
                                            <th><span translate="load.checkDelte"></span></th>
                                            <th><span translate="load.checkUpdate"></span></th>
                                            <th><span translate="load.checkSelect"></span></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr ng-repeat="menu in menus">
                                            <td style="vertical-align:middle">{{menu.menu.id}}</td>
                                            <td hidden="hidden">{{menu.menu.id}}</td>
                                            <td style="vertical-align:middle" translate="{{menu.menu.menuName}}"></td>
                                            <td style="vertical-align:middle">
                                                <span class="label {{menu.menu.addlabelType}} label-mini"
                                                      style="cursor:pointer; "
                                                      data-ng-click="authorityCheck(menu.menu.id,menu.menu.chkBoxAdd,1)"
                                                      translate="{{menu.menu.chkBoxAdd}}"></span>
                                            </td>
                                            <%--<input type="checkbox"  data-ng-click="authorityCheck(menu.id,chkAdd,1)" data-ng-model="chkAdd"  class="checkbox op addCheck"  style="width: 20px;" ></td>--%>
                                            <td style="vertical-align:middle">
                                                <span class="label {{menu.menu.deletelabelType}} label-mini"
                                                      style="cursor:pointer; "
                                                      data-ng-click="authorityCheck(menu.menu.id,menu.menu.chkBoxDelete,2)"
                                                      translate="{{menu.menu.chkBoxDelete}}"></span>
                                                <%--<input type="checkbox" class="checkbox op deleteCheck" data-ng-click="authorityCheck(menu.id,chkDelete,2)"data-ng-model="chkDelete" value="2" style="width: 20px;" >--%>
                                            </td>
                                            <td style="vertical-align:middle">
                                                <span class="label {{menu.menu.updatelabelType}} label-mini"
                                                      style="cursor:pointer; "
                                                      data-ng-click="authorityCheck(menu.menu.id,menu.menu.chkBoxUpdate,3)"
                                                      translate="{{menu.menu.chkBoxUpdate}}"></span>
                                                <%--<input type="checkbox" class="checkbox op updateCheck" data-ng-click="authorityCheck(menu.id,chkUpdate,3)"data-ng-model="chkUpdate" value="3" style="width: 20px;" >--%>
                                            </td>
                                            <td style="vertical-align:middle">
                                                <span class="label {{menu.menu.selectlabelType}} label-mini"
                                                      style="cursor:pointer; "
                                                      data-ng-click="authorityCheck(menu.menu.id,menu.menu.chkBoxSelect,4)"
                                                      translate="{{menu.menu.chkBoxSelect}}"></span>
                                                <%--<input type="checkbox" class="checkbox op selectCheck" data-ng-click="authorityCheck(menu.id,chkSelect,4)"data-ng-model="chkSelect" value="4" style="width: 20px;" >--%>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <div class="pager">
                                        <pager page-count="pageCount" current-page="currentPage"
                                               on-page-change="onPageChange()" first-text="load.HomePage"
                                               next-text="load.NextPage" prev-text="load.PreviousPage"
                                               last-text="load.EndPage" show-goto="true"
                                               goto-text="load.GoToPage"></pager>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                    <%--<section class="panel panel-primary"  >--%>
                    <%--<header class="panel-heading">--%>
                    <%--<span translate="load.searchCriteria"></span>--%>
                    <%--</header>--%>
                    <%--<div class="panel-body searchCriteria" >--%>
                    <%--</div>--%>
                    <%--</section>--%>


                    <%--<div class="row">--%>
                    <%--<div class="col-md-6">--%>
                    <%--<div class="panel">--%>
                    <%--<div class="panel-heading">--%>
                    <%--<span translate="load.userAccredit"></span>--%>
                    <%--</div>--%>
                    <%--<div class="panel-body">--%>
                    <%--<div id="MyTree" class="tree tree-solid-line" ng-init="init()">--%>
                    <%--<div class = "tree-folder" style="display:none;">--%>
                    <%--<div class="tree-folder-header">--%>
                    <%--<i class="fa fa-folder"></i>--%>
                    <%--<div class="tree-folder-name"></div>--%>
                    <%--</div>--%>
                    <%--<div class="tree-folder-content"></div>--%>
                    <%--<div class="tree-loader" style="display:none"></div>--%>
                    <%--</div>--%>
                    <%--<div class="tree-item" style="display:none;">--%>
                    <%--<i class="tree-dot"></i>--%>
                    <%--<div class="tree-item-name"></div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="col-md-6">--%>
                    <%--<div class="panel">--%>
                    <%--<div class="panel-heading">--%>
                    <%--<span translate="load.Options"></span>--%>
                    <%--</div>--%>
                    <%--<div class="panel-body">--%>
                    <%--<div class="accreditChecks" hidden="hidden">--%>
                    <%--<div class="checkbox " style="float: left;margin-top: -5px;">--%>
                    <%--<label><span translate="load.checkAdd"></span> </label>--%>
                    <%--<input type="checkbox" class="checkbox op addCheck" value="1" style="width: 20px;" >--%>
                    <%--</div>--%>
                    <%--<div class="checkbox " style="float: left">--%>
                    <%--<label><span translate="load.checkDelte"></span></label>--%>
                    <%--<input type="checkbox" class="checkbox op deleteCheck" value="2" style="width: 20px;" >--%>
                    <%--</div>--%>
                    <%--<div class="checkbox " style="float: left">--%>
                    <%--<label><span translate="load.checkUpdate"></span></label>--%>
                    <%--<input type="checkbox" class="checkbox op updateCheck" value="3" style="width: 20px;">--%>
                    <%--</div>--%>
                    <%--<div class="checkbox " style="float: left">--%>
                    <%--<label><span translate="load.checkSelect"></span></label>--%>
                    <%--<input type="checkbox" class="checkbox op selectCheck" value="4" style="width: 20px;">--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>


                            <section class="panel">
                                <header class="panel-heading">
                                    <span translate="load.includeGame"></span>
                                </header>

                                <div class="panel-body">
                                    <div class="row">
                                        <aside class="col-md-3">
                                            <h4 class="drg-event-title"><span translate="load.GameList"></span></h4>
                                            <div class='external-events'>
                                                <div class='external-event label label-primary'
                                                     data-ng-repeat="game in gameChannelCode" translate={{game}}></div>
                                                <br/>
                                            </div>
                                        </aside>
                                        <button class="btn btn-warning" type="button" ng-click="gameChannelClick()">
                                            <span translate="load.SelectGame"></span></button>

                                    </div>
                                </div>
                            </section>
                        </div>

                        <div class="col-sm-6">
                            <section class="panel">
                                <header class="panel-heading">
                                    <span translate="load.channelInclude"></span>
                                </header>

                                <div class="panel-body">
                                    <div class="row">
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

                                    </div>
                                </div>
                            </section>

                        </div>

                        <div class="col-sm-6" ng-hide="roleHide==2">
                            <section class="panel">
                                <header class="panel-heading">
                                    <span translate="load.roleInclude"></span>
                                </header>

                                <div class="panel-body">
                                    <div class="row">
                                        <aside class="col-md-3">
                                            <h4 class="drg-event-title"><span translate="load.roleList"></span></h4>
                                            <div>
                                                <div class='external-event label label-primary'
                                                     data-ng-repeat="code in roleCode">{{code}}
                                                </div>
                                                <br/>
                                            </div>
                                        </aside>
                                        <button class="btn btn-warning" type="button" ng-click="roleClicked()"><span
                                                translate="load.rolePlay"></span></button>

                                    </div>
                                </div>
                            </section>

                        </div>


                        <div class="modal-footer">
                            <div style="float: right;margin-right: 20px;">
                                <button type="button" class="btn btn-default" href="javascript:"
                                        onclick="history.back(); "><span translate="load.Cancel"></span></button>
                                <button type="button" class="btn btn-primary" href="javascript:"
                                        ng-click="affirmButton()"><span translate="load.Ok"></span></button>
                            </div>
                            <p ng-hide="!gameAreaForm.$invalid" style="color: red;float: right;margin-top: 15px;"><span
                                    translate="load.remindValidate"></span></p>
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

                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="roleAddModal"
                         class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.rolePlay"></span></h4>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <form class="form-horizontal bucket-form" ng-submit="roleAdd()">
                                                <div class="form-group" style="width: 800px;">
                                                    <div class="col-sm-9 icheck ">
                                                        <div style="margin-left: 20px;">
                                                            <a ng-click="allRole(roles)"
                                                               class="btn btn-success"><span
                                                                    translate="load.checkAll"></span></a>
                                                            <a ng-click="deselectAllRole(roles)"
                                                               class="btn btn-danger"><span
                                                                    translate="load.deselectAll"></span></a>

                                                            <a ng-click="versaRole()"
                                                               class="btn btn-info"><span
                                                                    translate="load.InvertSelection"></span></a>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-9 icheck ">
                                                        <div>
                                                            <div class="checkbox" data-ng-repeat="role in roles"
                                                                 align="center">
                                                                <input type="checkbox" id={{role.role}}{{role.id}}
                                                                       name={{role.id}} title="{{role.role}}"
                                                                       ng-click="updateSelectionRole($event,role.id,role.role)"
                                                                       ng-model="roleModel" ng-checked="master"
                                                                       class="chk_1 roleChildren">
                                                                <label for={{role.role}}{{role.id}}>{{role.role}}</label>
                                                            </div>

                                                        </div>
                                                    </div>


                                                </div>
                                                <div class="modal-footer" style="width: 550px;">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        <span translate="load.Cancel"></span></button>
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

                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                         id="gameChannelAddModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.includeGame"></span></h4>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <form class="form-horizontal bucket-form" ng-submit="gameChannelAdd()">
                                                <div class="form-group" style="width: 800px;">
                                                    <div class="col-sm-9 icheck ">
                                                        <%--<div >
                                                            <div class="checkbox" align="center">
                                                                <input type="checkbox" id="gameAll" class="chk_1" ng-model="gameMaster" ng-click="gameAll(gameMaster,gameList)">
                                                                <label for="gameAll" class="allGameCheck">全选</label>
                                                            </div>

                                                            <div class="checkbox" align="center">
                                                                <input type="checkbox" id="gameVersa" class="chk_1"  ng-model="versaGameCheck"  ng-click="gameVersa(versaGameCheck)">
                                                                <label for="gameVersa" class="allGameVersa">反选</label>

                                                            </div>
                                                        </div>--%>
                                                        <div style="margin-left: 20px;">
                                                            <a ng-click="gameAll(gameList)"
                                                               class="btn btn-success"><span
                                                                    translate="load.checkAll"></span></a>
                                                            <a ng-click="deselectAllGame(gameList)"
                                                               class="btn btn-danger"><span
                                                                    translate="load.deselectAll"></span></a>

                                                            <a ng-click="gameVersa()"
                                                               class="btn btn-info"><span
                                                                    translate="load.InvertSelection"></span></a>
                                                        </div>

                                                    </div>
                                                    <div class="col-sm-9 icheck ">
                                                        <div>
                                                            <div class="checkbox" data-ng-repeat="game in gameList"
                                                                 align="center">
                                                                <input type="checkbox" id={{game.code}}{{game.id}}
                                                                       name={{game.id}} title={{game.code}}
                                                                       ng-click="updateGameSelection($event,game.id,game.code)"
                                                                       ng-model="gameModel" ng-checked="gameMaster"
                                                                       class="chk_1 gameChildren">
                                                                <label for={{game.code}}{{game.id}}
                                                                       translate={{game.code}}></label>
                                                            </div>

                                                        </div>
                                                    </div>


                                                </div>
                                                <div class="modal-footer" style="width: 550px;">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        <span translate="load.Cancel"></span></button>
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

                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                         id="channelAddModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.channelInclude"></span></h4>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <form class="form-horizontal bucket-form" ng-submit="channelAdd()">
                                                <div class="form-group" style="width: 800px;">
                                                    <div class="col-sm-9 icheck ">
                                                        <div style="margin-left: 20px;">
                                                            <a ng-model="master" ng-click="all(siteList)"
                                                               class="btn btn-success"><span
                                                                    translate="load.checkAll"></span></a>
                                                            <a ng-model="master" ng-click="deselectAll(siteList)"
                                                               class="btn btn-danger"><span
                                                                    translate="load.deselectAll"></span></a>

                                                            <a ng-model="versaCheck" ng-click="versa(versaCheck)"
                                                               class="btn btn-info"><span
                                                                    translate="load.InvertSelection"></span></a>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-9 icheck ">
                                                        <div>
                                                            <div class="checkbox" data-ng-repeat="site in siteList"
                                                                 align="center">
                                                                <input type="checkbox" id={{site.code}}{{site.id}}
                                                                       name={{site.id}} title={{site.code}}
                                                                       ng-click="updateSelection($event,site.id,site.code)"
                                                                       ng-model="siteModel" ng-checked="master"
                                                                       class="chk_1 children">
                                                                <label for={{site.code}}{{site.id}}
                                                                       translate={{site.code}}></label>
                                                            </div>

                                                        </div>
                                                    </div>


                                                </div>
                                                <div class="modal-footer" style="width: 550px;">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        <span translate="load.Cancel"></span></button>
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



            </section>
        </div>
</div>
