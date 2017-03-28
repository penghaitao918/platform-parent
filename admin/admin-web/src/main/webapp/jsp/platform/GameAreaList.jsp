<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div >

    <div class="row">
        <div class="col-sm-6">
            <section class="panel">
                <header class="panel-heading">
                    <span translate="load.areaAreaTitle"></span>
                </header>
                <div class="panel-body" ng-controller="gameAreaController">
                    <div class="clearfix">
                        <div class="btn-group">
                            <a  ng-click="addButton()"
                                class="btn btn-success"><span translate="load.add"></span>&nbsp<i class="fa fa-plus"></i></a>
                            <a class="btn btn-danger" ng-click="delete()"><span translate="load.batchDelte"></span>&nbsp<i class="fa fa-times"></i></a>
                            <a class="btn btn-success" ng-click="CopyArea()"><span translate="load.CopyArea"></span>&nbsp<i class="fa fa-copy"></i></a>
                            <a class="btn btn-info" ng-click="bulkEditing()"><span translate="load.BulkEditing"></span>&nbsp<i class="fa fa-edit"></i></a>
                            <a class="btn btn-info" ng-click="pocOpen()"><span translate="load.OBT"></span>&nbsp<i class="fa fa-flag"></i></a>
                            <a class="btn btn-info" ng-click="pocClose()"><span translate="load.superEsc"></span>&nbsp<i class="fa fa-flag"></i></a>
                            <a class="btn btn-info" ng-click="refreshAreaCache()"><span translate="load.refreshAreaCache"></span>&nbsp<i class="fa fa-flag"></i></a>
                        </div>
<%--
                        <div style="float: right;">
                            <form class="searchform"  method="post">
                                <input type="text" class="form-control searchText" ng-model="gameAreaSearch.areaName" name="keyword" placeholder="Search here..." onkeydown="globelQuery(event)"/>
                                <div style="float: left;margin-top: 7px;">
                                    <button ng-click="searchButtonClicked()" class="btn btn-primary searchButton" ><span translate="load.search"></span>&nbsp;<i class="fa fa-search"></i></button>
                                </div>

                            </form>
                        </div>--%>
                    </div>


                    <table class="table">
                        <thead>
                        <tr>
                            <th></th>
                            <th>#</th>
                            <th><span translate="load.areaAreaCode"></span></th>
                            <th><span translate="load.areaAreaName"></span></th>
                            <th><span translate="load.GameId"></span></th>
                            <th><span translate="load.areaAreaTag"></span></th>
                            <th><span translate="load.areaLoadStatus"></span></th>
                            <th><span translate="load.areaMaintenanceStatus"></span></th>
                            <th><span translate="load.areaValidStatus"></span></th>
                            <th><span translate="load.areaAvailableTime"></span></th>
                            <%--<th>clientEnterAddr</th>
                            <th>serverEnterAddr</th>--%>
                            <th><span translate="load.operation"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="gameArea in gameAreas">
                            <td align="center"><input type="checkbox" class="checkbox"
                                                      data-ng-click="check($index,chk)"data-ng-model="chk" value="" style="width:50px;"></td>
                            <td style="vertical-align:middle">{{gameArea.id}}</td>
                            <td hidden="hidden">{{gameArea.id}}</td>
                            <td style="vertical-align:middle">{{gameArea.areaCode}}</td>
                            <td style="vertical-align:middle">{{gameArea.areaName}}</td>
                            <td style="vertical-align:middle" >
                                <span ng-hide="gameArea.gameId!=1" class="label label-success label-mini" >{{gameArea.gameId|NumToStr7|translate}}</span>
                                <span ng-hide="gameArea.gameId==1" class="label label-default label-mini" >{{gameArea.gameId|NumToStr7|translate}}</span>
                            </td>
                            <td style="vertical-align:middle">
                                <span ng-hide="gameArea.areaTag!=1" class="label label-success label-mini" translate="{{gameArea.areaTag|NumToStr6}}"></span>
                                <span ng-hide="gameArea.areaTag!=2" class="label label-danger label-mini" translate="{{gameArea.areaTag|NumToStr6}}"></span>
                                <span ng-hide="gameArea.areaTag!=3" class="label label-info label-mini" translate="{{gameArea.areaTag|NumToStr6}}"></span>
                            </td>
                            <td style="vertical-align:middle" >
                                <span ng-hide="gameArea.loadStatus!=1" class="label label-info label-mini" translate="{{gameArea.loadStatus|NumToStr5}}"></span>
                                <span ng-hide="gameArea.loadStatus!=2" class="label label-danger label-mini" translate="{{gameArea.loadStatus|NumToStr5}}"></span>
                                <span ng-hide="gameArea.loadStatus!=3" class="label label-success label-mini" translate="{{gameArea.loadStatus|NumToStr5}}"></span>
                                <span ng-hide="gameArea.loadStatus!=4" class="label label-warning label-mini" translate="{{gameArea.loadStatus|NumToStr5}}"></span>
                            </td>
                            <td style="vertical-align:middle" >
                                <span ng-hide="gameArea.maintenanceStatus!=1" class="label label-success label-mini" translate="{{gameArea.maintenanceStatus|NumToStr4}}"></span>
                                <span ng-hide="gameArea.maintenanceStatus!=2" class="label label-warning label-mini" translate="{{gameArea.maintenanceStatus|NumToStr4}}"></span>
                            </td>
                            <td style="vertical-align:middle" translate="{{gameArea.validStatus|NumToStr2}}">
                                <span ng-hide="gameArea.validStatus!=1" class="label label-success label-mini" translate="{{gameArea.validStatus|NumToStr2}}"></span>
                                <span ng-hide="gameArea.validStatus!=0" class="label label-danger label-mini" translate="{{gameArea.validStatus|NumToStr2}}"></span>
                            </td>
                            <td style="vertical-align:middle">{{gameArea.availableTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <%--<td>{{gameArea.clientEnterAddr}}</td>
                            <td>{{gameArea.serverEnterAddr}}</td>--%>
                            <td style="vertical-align:middle"><a ng-click="updateClicked(gameArea.id)" style= "cursor:pointer;color: navy" ><span translate="load.update"></span><i class="fa fa-edit"></i></a>
                            <a ng-click="open()" ng-hide="gameArea.validStatus==1" style= "cursor:pointer;"><span translate="load.Open"></span><i class="fa fa-check"></i></a>
                            <a ng-click="close()" ng-hide="gameArea.validStatus==0" style= "cursor:pointer;color: red" ><span translate="load.Close"></span><i class="fa fa-times"></i></a> 
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="pager">
                        <pager page-count="pageCount" current-page="currentPage" on-page-change="onPageChange()" first-text="load.HomePage" next-text="load.NextPage" prev-text="load.PreviousPage" last-text="load.EndPage" show-goto="true" goto-text="load.GoToPage"></pager>
                    </div>


                    <div aria-hidden="true" role="dialog" tabindex="-1" id="BulkUpdate" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                    <h4 class="modal-title"><span translate="load.add"></span></h4>
                                </div>
                                <div class="modal-body">

                                    <form role="form" ng-submit="batchForms()" name="batchForm">
                                      <%--  <div class="form-group">
                                            <label><span translate="load.areaValidStatus"></span>:<span style="color: red">*</span></label>
                                            <select name="validStatus" aria-controls="editable-sample" class="form-control validStatus" ng-model="batchArea.batchValidStatus" required>
                                                <option value="" translate="load.pleaseSelect"></option>
                                                <option value="unact" translate="load.validStatusUnact"></option>
                                                <option value="acting" translate="load.validStatusActing"></option>
                                                <option value="locked" translate="load.validStatusLocked"></option>
                                                <option value="acted" translate="load.validStatusActed"></option>
                                                <option value="reject" translate="load.validStatusReject"></option>
                                                <option value="error" translate="load.validStatusError"></option>
                                            </select>
                                        </div>--%>
                                        <div class="form-group">
                                            <label><span translate="load.areaMaintenanceStatus"></span>:<span style="color: red">*</span></label>
                                            <select name="maintenanceStatus" aria-controls="editable-sample" class="form-control maintenanceStatus" ng-model="batchArea.batchMaintenanceStatus" required>
                                                <option value="" translate="load.pleaseSelect"></option>
                                                <option value="1" translate="load.Normal"></option>
                                                <option value="2" translate="load.Maintaining"></option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label><span translate="load.areaLoadStatus"></span>:<span style="color: red">*</span></label>
                                            <select name="loadStatus" aria-controls="editable-sample" class="form-control loadStatus" ng-model="batchArea.loadStatus" required>
                                                <option value="" translate="load.pleaseSelect"></option>
                                                <option value="1" translate="load.loadStatusFree"></option>
                                                <option value="2" translate="load.loadStatusFull"></option>
                                                <option value="3" translate="load.loadStatusFluency"></option>
                                                <option value="4" translate="load.loadStatusBusy"></option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label><span translate="load.areaSupportVersionMin"></span>:<span style="color: red">*</span></label>
                                            <input type="number" class="form-control" name="supportVersionMin" min="0" max="100000000000"  ng-model="batchArea.supportVersionMin" integer  required>
                                            <span ng-show="batchForm.supportVersionMin.$error.integer" style="color: red;"><span translate="load.numLength"></span></span>
                                             <span ng-show="batchForm.supportVersionMin.$error.min || batchForm.supportVersionMin.$error.max" style="color: red;">
                                                     <span translate="load.numBetween"></span>
                                             </span>
                                        </div>

                                        <div class="form-group">
                                            <label><span translate="load.areaSupportVersionMax"></span>:<span style="color: red">*</span></label>
                                            <input type="number" class="form-control" name="supportVersionMax" min="0" max="100000000000"  ng-model="batchArea.supportVersionMax" integer  required>
                                            <span ng-show="batchForm.supportVersionMax.$error.integer" style="color: red;"><span translate="load.numLength"></span></span>
                                             <span ng-show="batchForm.supportVersionMax.$error.min || batchForm.supportVersionMax.$error.max" style="color: red;">
                                                <span translate="load.numBetween"></span>
                                            </span>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal"><span translate="load.Cancel"></span></button>
                                            <button type="submit" class="btn btn-primary addSubmit" ng-disabled="batchForm.$invalid" ><span translate="load.Ok"></span></button>
                                            <p ng-hide="!batchForm.$invalid" style="color: red;margin-top: 15px;"><span translate="load.remindValidate"></span></p>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%--<div aria-hidden="true" role="dialog" tabindex="-1" id="gameAreaModalForUpdate" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                    <h4 class="modal-title">修改</h4>
                                </div>
                                <div class="modal-body">

                                    <form role="form" ng-submit="gameAreaUpdateForm()">
                                        <input type="hidden"  class="form-control gameToyUpdate" name="id" ng-model="gameToyUpdateData.id" >
                                        <div class="form-group">
                                            <label >code:</label>
                                            <input type="text"  class="form-control" name="code" placeholder="Enter code" ng-model="gameToyUpdateData.code" required>
                                        </div>
                                        <div class="form-group">
                                            <label>gameId:</label>
                                            &lt;%&ndash;  <input type="text" class="form-control" name="gameId" placeholder="Enter gameId" ng-model="gameToyUpdateData.gameId">&ndash;%&gt;
                                            <select name="gameList" aria-controls="editable-sample" class="form-control gameList" ng-model="gameToyUpdateData.gameId" required>
                                                <option ng-repeat="game in gameList" value="game.id">{{game.code}}</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>siteId:</label>
                                            &lt;%&ndash; <input type="text" class="form-control" name="siteId" placeholder="Enter siteId" ng-model="gameToyUpdateData.siteId" >&ndash;%&gt;
                                            <select name="siteList" aria-controls="editable-sample" class="form-control siteList"  ng-model="gameToyUpdateData.siteId" required>
                                                <option ng-repeat="site in siteList" value="site.id">{{site.code}}</option>
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <label>bindUid:</label>
                                            <input type="text" class="form-control" name="bindUid" placeholder="Enter bindUid" ng-model="gameToyUpdateData.bindUid" required>
                                        </div>
                                        <div class="form-group">
                                            <label>bindGame:</label>
                                            <input type="text" class="form-control" name="bindGame" placeholder="Enter bindGame" ng-model="gameToyUpdateData.bindGame" required>
                                        </div>
                                        <div class="form-group">
                                            <label>bindSite:</label>
                                            <input type="text" class="form-control" name="bindSite" placeholder="Enter bindSite" ng-model="gameToyUpdateData.bindSite" required>
                                        </div>
                                        <div class="form-group">
                                            <label>bindArea:</label>
                                            <input type="text" class="form-control" name="bindArea" placeholder="Enter bindArea" ng-model="gameToyUpdateData.bindArea" required>
                                        </div>
                                        <div class="form-group">
                                            <label>bindStatus:</label>
                                            <input type="text" class="form-control" name="bindStatus" placeholder="Enter bindStatus" ng-model="gameToyUpdateData.bindStatus" required>
                                        </div>
                                        <div class="form-group">
                                            <label>bindTime:</label>
                                            <input type="text" class="form_datetime form-control" name="bindTime" placeholder="Enter bindTime" ng-model="gameToyUpdateData.bindTime" required>
                                        </div>


                                        <button type="submit" class="btn btn-primary updateSubmit" >修改</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>--%>

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