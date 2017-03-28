<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resource/adminex/js/multi-select-init.js"></script>
<script src="${ctx}/resource/adminex/js/icheck-init.js"></script>

<div >

    <div class="row">
        <div class="col-sm-6">
            <section class="panel">
                <div class="panel-body" ng-controller="gameToyTypeController">
                    <div class="clearfix">
                        <div class="btn-group" style="margin-top: 18px;">
                            <a ng-click="addButton()"
                               class="btn btn-success"><span translate="load.add"></span>&nbsp<i class="fa fa-plus"></i></a>
                            <a class="btn btn-danger" ng-click="delete()"><span translate="load.batchDelte"></span>&nbsp<i
                                    class="fa fa-times"></i></a>
                            <a class="btn btn-info" ng-click="pocOpen()"><span translate="load.OBT"></span>&nbsp<i
                                    class="fa fa-flag"></i></a>
                            <a class="btn btn-danger" ng-click="pocClose()"><span translate="load.superEsc"></span>&nbsp<i
                                    class="fa fa-flag"></i></a>
                            <a class="btn btn-info" ng-click="fileImport()"><span
                                    translate="load.individualImport"></span>&nbsp<i class="fa fa-file-text-o"></i></a>
                        </div>
                        <div style="float: right;">
                            <form class="searchform" method="post" style="float:left;margin-top: 18px;">
                                <input type="text" class="form-control searchText" ng-model="gameToyTypeSearch.name"
                                       name="keyword" placeholder={{gameItemNameSearchText|translate}} onkeydown="globelQuery(event)"/>
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
                            <th><span translate="load.itemItemCode"></span></th>
                            <th><span translate="load.itemItemName"></span></th>
                            <th><span translate="load.itemItemType"></span></th>
                            <th><span translate="load.itemExtend"></span></th>
                            <th><span translate="load.Available"></span></th>
                            <th><span translate="load.operation"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="gameToyType in gameTypeToys">
                            <td align="center"><input type="checkbox" class="checkbox"
                                                      data-ng-click="check($index,chk)" data-ng-model="chk" value=""
                                                      style="width:50px;"></td>
                            <td style="vertical-align:middle">{{gameToyType.id}}</td>
                            <td style="vertical-align:middle">{{gameToyType.itemCode}}</td>
                            <td style="vertical-align:middle">{{gameToyType.itemName}}</td>
                            <td style="vertical-align:middle">
                                <span ng-show="gameToyType.itemType==1" class="label label-success label-mini">{{gameToyType.itemType|NumToStr16|translate}}</span>
                                <span ng-show="gameToyType.itemType==2" class="label label-danger label-mini">{{gameToyType.itemType|NumToStr16|translate}}</span>
                                <span ng-show="gameToyType.itemType==3" class="label label-primary label-mini">{{gameToyType.itemType|NumToStr16|translate}}</span>
                                <span ng-show="gameToyType.itemType==4" class="label label-default label-mini">{{gameToyType.itemType|NumToStr16|translate}}</span>
                                <span ng-show="gameToyType.itemType==5" class="label label-info label-mini">{{gameToyType.itemType|NumToStr16|translate}}</span>
                            </td>
                            <td style="vertical-align:middle">{{gameToyType.itemExtend}}</td>
                            <td style="vertical-align:middle">
                                <span ng-hide="gameToyType.available!=1" class="label label-success label-mini"
                                      translate="{{gameToyType.available|NumToStr}}"></span>
                                <span ng-hide="gameToyType.available!=0" class="label label-danger label-mini"
                                      translate="{{gameToyType.available|NumToStr}}"></span>
                            </td>
                            <td><a ng-click="updateClicked()" style="cursor:pointer;color: navy"><span
                                    translate="load.update"></span><i class="fa fa-edit"></i></a>
                                <a ng-click="open()" ng-hide="gameToyType.available==1" style="cursor:pointer;"><span
                                        translate="load.Open"></span><i class="fa fa-check"></i></a>
                                <a ng-click="close()" ng-hide="gameToyType.available==0"
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
                         id="gameToyTypeModalForEdit" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.add"></span></h4>
                                </div>
                                <div class="modal-body">

                                    <form role="form" ng-submit="gameToyTypeEditForms()" name="gameToyTypeEditForm">
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.itemItemCode"></span>:<span style="color: red">*</span></label>
                                            <input type="number" min="0" max="100000000000"
                                                   class="form-control itemCode" name="itemCode"
                                                   ng-model="gameToyTypeEditData.itemCode" required>
                                        </div>
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.itemItemName"></span>:<span style="color: red">*</span></label>
                                            <input type="text" class="form-control itemName" name="itemName"
                                                   ng-model="gameToyTypeEditData.itemName" required>
                                        </div>
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.itemItemType"></span>:<span style="color: red">*</span></label>
                                            <select name="itemType" aria-controls="editable-sample"
                                                    class="form-control itemType"
                                                    ng-model="gameToyTypeEditData.itemType" required>
                                                <option value="" translate="load.pleaseSelect"></option>
                                                <option value="{{itemType.value}}" ng-repeat="itemType in itemTypeList"
                                                        translate="load.itemType{{itemType.key}}"></option>
                                            </select>
                                        </div>
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.itemExtend"></span>:</label>
                                            <%--  <input type="text" class="form-control itemExtend" name="itemExtend"  ng-model="gameToyTypeEditData.itemExtend" required>--%>
                                            <textarea class="form-control itemExtend" style="height: 150px;resize:none;"
                                                      name="itemExtend"
                                                      ng-model="gameToyTypeEditData.itemExtend"></textarea>
                                        </div>

                                        <div class="form-group labelSpace">
                                            <label><span translate="load.GameId"></span>:<span
                                                    style="color: red">*</span></label>
                                            <select name="gameList" aria-controls="editable-sample"
                                                    class="form-control gameList"
                                                    ng-options="game.id as game.code|translate for game in gameList"
                                                    ng-change="resourceGameListChange(gameToyGameList)"
                                                    ng-model="gameToyGameList" required>
                                                <option value="" translate="load.pleaseSelect"></option>
                                            </select>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                                    translate="load.Cancel"></span></button>
                                            <button type="submit" class="btn btn-primary addSubmit"><span
                                                    translate="load.Ok"></span></button>
                                            <p ng-hide="!gameToyTypeEditForm.$invalid"
                                               style="color: red;margin-top: 15px;"><span
                                                    translate="load.remindValidate"></span></p>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                         id="fileImportModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.individualImport"></span></h4>
                                </div>
                                <div class="modal-body">
                                    <form action="${ctx}/operation/gameToyType/upload" name="fileImportForms"
                                          target="_blank" method="post" enctype="multipart/form-data">
                                        <div class="row">
                                            <div class="col-md-10">
                                                <div class="form-group labelSpace">
                                                    <label for="exampleInputFile" translate="load.import"></label>
                                                    <input type="file" id="exampleInputFile" name="file">
                                                </div>

                                                <div class="form-group labelSpace">
                                                    <label for="downloadInputFile"
                                                           translate="load.downloadInputFile"></label>
                                                    <a class="btn btn-info" href="${ctx}/operation/gameToyType/download"
                                                       target="MailOrder" id="downloadInputFile"
                                                       translate="load.downloadInputFile"></a>
                                                </div>

                                                <div class="modal-footer" style="width: 550px;">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        <span translate="load.Cancel"></span></button>
                                                    <button type="submit" class="btn btn-primary addSubmit"
                                                            ng-click="fileImportFormsClick()"><span
                                                            translate="load.Ok"></span></button>
                                                </div>
                                            </div>
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