<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<div>
    <div class="row">
        <div class="col-sm-6">
            <section class="panel">
                <div class="panel-body" ng-controller="uniformController">
                    <div class="clearfix">
                        <div class="btn-group">
                            <a class="btn btn-success" ng-click="addButton()"><span translate="load.add"></span>&nbsp<i
                                    class="fa fa-plus"></i></a>
                            <a class="btn btn-info" ng-click="refreshButton()"><span translate="load.refreshCache"></span>&nbsp<i
                                    class="fa fa-flag"></i></a>
                        </div>
                        <div style="float: right;">
                        </div>
                    </div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th></th>
                            <th>#</th>
                            <th><span translate="load.uniId"></span></th>
                            <th><span translate="load.createTime"></span></th>
                            <th><span translate="load.operation"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="r in res">
                            <td></td>
                            <td>{{r.id}}</td>
                            <td>{{r.dictionaryName}}</td>
                            <td>{{r.createTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td>
                                <a ng-click="updateClicked()" style="cursor:pointer;color: navy"><span
                                        translate="load.update"></span><i class="fa fa-edit"></i></a>
                                <a ng-click="deleteClicked(r.dictionaryName)" style="cursor:pointer;color: red"><span
                                        translate="load.Delte"></span><i class="fa fa-times"></i></a>
                            </td>
                        </tr>

                        </tbody>
                    </table>


                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                         id="uniformEditModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.add"
                                                                  ng-hide="flag=='update'"></span><span
                                            translate="load.update" ng-hide="flag=='add'"></span></h4>
                                </div>
                                <div class="modal-body">

                                    <form role="form" ng-submit="uniformEdit()">
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.uniUniCode"></span>:</label>
                                            <input type="text" class="form-control dictionaryName" name="dictionaryName"
                                                   ng-model="uniform.dictionaryName" required>
                                        </div>
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.GameId"></span>:<span
                                                    style="color: red">*</span></label>
                                            <select name="gameList" aria-controls="editable-sample" id="gameList"
                                                    class="form-control gameList"
                                                    ng-options="game.id as game.code|translate for game in gameList"
                                                    ng-model="uniform.gameId">
                                                <option value="" translate="load.pleaseSelect"></option>
                                            </select>
                                        </div>
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.SiteId"></span>:<span
                                                    style="color: red">*</span></label>
                                            <select name="siteId" id="siteList" aria-controls="editable-sample"
                                                    class="form-control siteId"
                                                    ng-options="site.id as site.code|translate for site in siteList"
                                                    ng-model="uniform.siteId">
                                                <option value="" translate="load.pleaseSelect"></option>
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <div class="panel panel-default">
                                                <div class="panel-heading" translate="load.uniItemData"></div>
                                                <div class="panel-body">
                                                    <div class="col-md-4 form-group">
                                                        <input type="text" class="form-control idFrom"
                                                               ng-model="selectData.key">
                                                    </div>
                                                    <div class="col-md-4 form-group">
                                                        <input type="text" class="form-control codeFrom"
                                                               ng-model="selectData.value">
                                                    </div>
                                                    <div class="col-md-4 form-group">
                                                        <button type="button" class="btn btn-primary"
                                                                ng-click="uniAddClicked()"><span
                                                                translate="load.add"></span>
                                                        </button>
                                                    </div>

                                                    <table class="table">
                                                        <thead>
                                                        <tr>
                                                            <th><span translate="load.uniCode"></span></th>
                                                            <th><span translate="load.uniId"></span></th>
                                                            <th><span translate="load.operation"></span></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr ng-repeat="r in selectDatas">
                                                            <td>{{r.key}}</td>
                                                            <td>{{r.value}}</td>
                                                            <td>
                                                                <a ng-click="uniDeleteClicked()"><span
                                                                        translate="load.Delte"></span></a>
                                                            </td>
                                                        </tr>

                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="modal-footer" style="width: 550px;">
                                            <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                                    translate="load.Cancel"></span></button>
                                            <button type="submit" class="btn btn-primary updateSubmit"><span
                                                    translate="load.Ok"></span></button>
                                        </div>

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                         id="resModalForUpdate" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.update"></span></h4>
                                </div>
                                <div class="modal-body">

                                    <form role="form" ng-submit="uniformUpdate()">
                                        <div class="form-group">
                                            <label><span translate="load.uniUniCode"></span>:</label>
                                            <input type="text" class="form-control uniCode" name="uniCode"
                                                   ng-model="uniformUpdate.uniCode" required>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-4 form-group">
                                                <input type="text" class="form-control idFrom"
                                                       ng-model="selectData.key">
                                            </div>
                                            <div class="col-md-4 form-group">
                                                <input type="text" class="form-control codeFrom"
                                                       ng-model="selectData.value">
                                            </div>
                                            <div class="col-md-4 form-group">
                                                <button type="button" class="btn btn-primary"
                                                        ng-click="uniAddClicked()"><span translate="load.add"></span>
                                                </button>
                                            </div>

                                        </div>
                                        <div class="form-group">
                                            <table class="table">
                                                <thead>
                                                <tr>
                                                    <th><span translate="load.uniCode"></span></th>
                                                    <th><span translate="load.uniId"></span></th>
                                                    <th><span translate="load.operation"></span></th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr ng-repeat="r in selectDatas">
                                                    <td>{{r.key}}</td>
                                                    <td>{{r.value}}</td>
                                                    <td>
                                                        <a ng-click="uniDeleteClicked()"><span
                                                                translate="load.Delte"></span></a>
                                                    </td>
                                                </tr>

                                                </tbody>
                                            </table>
                                        </div>

                                        <div class="modal-footer" style="width: 550px;">
                                            <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                                    translate="load.Cancel"></span></button>
                                            <button type="submit" class="btn btn-primary updateSubmit"><span
                                                    translate="load.Ok"></span></button>
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

<script type="text/javascript">
    function globelQuery(e) {
        if (!e)
            e = window.event;
        if ((e.keyCode || e.which) == 13) {
            $(".searchButton").click();
        }
    }

</script>

<%--
<form name="excelImportForm" action="${pageContext.request.contextPath}/system/res/importExcel" method="post" onsubmit="return checkImportPath();" enctype="multipart/form-data" id="excelImportForm">
    <input  type="hidden" name="ids" id="ids">
    <div class="modal-body">
        <div class="row gap">
            <label class="col-sm-7 control-label"><input class="btn btn-default" id="excel_file" type="file" name="filename"  accept="xls"/></label>
            <div class="col-sm-3">

                <input class="btn btn-primary" id="excel_button" type="submit" value="导入Excel"/>
            </div>
        </div>

    </div>

    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onClick="uncheckBoxes();">取消</button>
    </div>
--%>

