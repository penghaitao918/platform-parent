<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<script type="text/javascript">
    function resController($scope, $http) {


        $http.get('<c:url value="${ctx}/system/res/list"/>')
                .success(function (data) {
                    //alert(data);
                    $scope.res = data;//数据
                });
    }


</script>
<div >
    <div class="row">
        <div class="col-sm-6">
            <section class="panel">
                <div class="panel-body" ng-controller="resController">
                    <div class="clearfix">
                        <div class="btn-group">
                            <a ng-click="addGameButton()"
                               class="btn btn-success"><span translate="load.gameAdd"></span>&nbsp<i
                                    class="fa fa-plus"></i></a>
                            <a class="btn btn-info" ng-click="addSiteButton()"><span translate="load.siteAdd"></span>&nbsp<i
                                    class="fa fa-plus"></i></a>
                            <a class="btn btn-danger" ng-click="delete()"> <span translate="load.batchDelte"></span>&nbsp<i
                                    class="fa fa-times"></i></a>
                            <a class="btn btn-info" ng-click="pocOpen()"><span translate="load.OBT"></span>&nbsp<i
                                    class="fa fa-flag"></i></a>
                            <a class="btn btn-info" ng-click="pocClose()"><span translate="load.superEsc"></span>&nbsp<i
                                    class="fa fa-flag"></i></a>
                            <a class="btn btn-info" ng-click="updateResCache()"><span translate="load.refreshCache"></span>&nbsp<i
                                    class="fa fa-flag"></i></a>
                        </div>
                        <div style="float: right;">
                            <form class="searchform" method="post">
                                <input type="text" class="form-control searchText" name="keyword"
                                       placeholder={{resourceSearchText|translate}} onkeydown="globelQuery(event)"/>
                                <div style="float: left;margin-top: 7px;margin-left: 20px;">
                                    <button ng-click="searchButtonClicked()" class="btn btn-primary searchButton"><span
                                            translate="load.easyRetrieval"></span>&nbsp;<i class="fa fa-search"></i>
                                    </button>
                                </div>

                            </form>
                        </div>
                    </div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th></th>
                            <th>#</th>
                            <th><span translate="load.resResCode"></span></th>
                            <th><span translate="load.resUrl"></span></th>
                            <th><span translate="load.resCreateTime"></span></th>
                            <th><span translate="load.resAvailable"></span></th>
                            <th><span translate="load.resResType"></span></th>
                            <th><span translate="load.operation"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="r in res">
                            <td align="center"><input type="checkbox" class="checkbox"
                                                      data-ng-click="check($index,chk)" data-ng-model="chk" value=""
                                                      style="width:50px;"></td>
                            <td style="vertical-align:middle">{{r.id}}</td>
                            <td hidden="hidden">{{r.id}}</td>
                            <td style="vertical-align:middle">{{r.resCode}}</td>
                            <td style="vertical-align:middle">{{r.url}}</td>
                            <td style="vertical-align:middle">{{r.createTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td style="vertical-align:middle">
                                <span ng-hide="r.available!=1" class="label label-success label-mini"
                                      translate="{{r.available|NumToStr2}}"></span>
                                <span ng-hide="r.available!=0" class="label label-danger label-mini"
                                      translate="{{r.available|NumToStr2}}"></span>
                            </td>
                            <td style="vertical-align:middle">
                                <span ng-hide="r.resType!='game'" class="label label-primary label-mini"
                                      translate="{{r.resType|NumToStr3}}"></span>
                                <span ng-hide="r.resType!='site'" class="label label-info label-mini"
                                      translate="{{r.resType|NumToStr3}}"></span>
                            </td>
                            <td style="vertical-align:middle">
                                <a ng-click="updateClicked()" style="cursor:pointer;color: navy"><span
                                        translate="load.update"></span><i class="fa fa-edit"></i></a>
                                <a ng-click="open(r.id)" ng-hide="r.available==1" style="cursor:pointer;"><span
                                        translate="load.Open"></span><i class="fa fa-check"></i></a>
                                <a ng-click="close(r.id)" ng-hide="r.available==0"
                                   style="cursor:pointer;color: red"><span translate="load.Close"></span><i
                                        class="fa fa-times"></i></a> 
                            </td>
                        </tr>

                        </tbody>
                    </table>
                    <div class="pager">
                        <pager page-count="pageCount" current-page="currentPage" on-page-change="onPageChange()"
                               first-text="load.HomePage" next-text="load.NextPage" prev-text="load.PreviousPage"
                               last-text="load.EndPage" show-goto="true" goto-text="load.GoToPage"></pager>
                    </div>


                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                         id="resModalForEdit" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.gameAdd"></span></h4>
                                </div>
                                <div class="modal-body">

                                    <form role="form" ng-submit="resEditForm()" name="resEditForms">
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.resResCode"></span>:<span
                                                    style="color: red">*</span></label>
                                            <input type="text" class="form-control resCode" name="resCode"
                                                   ng-model="resEditFormData.resCode" required>
                                        </div>
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.resUrl"></span>:</label>
                                            <input type="text" class="form-control url" name="url"
                                                   ng-model="resEditFormData.url" id="resUrl" pattern="[^\s]*"
                                                   ng-Blur="checkVerify('resUrl','load.urlValidate')">
                                        </div>
                                        <div class="modal-footer" style="width: 550px;">
                                            <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                                    translate="load.Cancel"></span></button>
                                            <button type="submit" class="btn btn-primary addSubmit"><span
                                                    translate="load.Ok"></span></button>
                                            <p ng-hide="!resEditForms.$invalid" style="color: red;margin-top: 15px;">
                                                <span translate="load.remindValidate"></span></p>
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

