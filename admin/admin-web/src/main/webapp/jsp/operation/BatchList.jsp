    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resource/adminex/js/DateTimePicker/DateInit.js"></script>
<script src="${ctx}/resource/adminex/js/multi-select-init.js"></script>
<script src="${ctx}/resource/adminex/js/icheck-init.js"></script>

<div >
    <div class="row" ng-controller="batchController">
        <div class="col-sm-6">
            <section class="panel" style="margin-top: 20px;">
                <header class="panel-heading">
                    <span translate="load.searchCriteria"></span>
                    <span class="tools pull-right">
                       <a href="javascript:;" class="fa fa-chevron-down" onclick="chevronClick(this)"></a>
                    </span>
                </header>
                <div class="panel-body searchCriteria" >
                    <div style="float: left;margin-right: 20px;" class="labelSpace">
                        <label><span translate="load.startTime"></span></label>
                        <input size="16" type="text" name="proTime" readonly class="ui_timepicker form-control" ng-model="batchSearch.from" >
                    </div>
                    <div style="float: left;margin-right: 20px;" class="labelSpace">
                        <label><span translate="load.endTime"></span></label>
                        <input size="16" type="text" name="proTime" readonly class="ui_timepicker form-control" ng-model="batchSearch.to" >
                    </div>
                    <div style="float: left;margin-right: 20px;" class="labelSpace">
                        <label><span translate="load.batchToyType"></span></label>
                        <select name="itemType" aria-controls="editable-sample" class="form-control itemType"   ng-change="itemTypeChange(itemType)" ng-model="itemType"  ng-options="itemType.itemCode as itemType.itemName for itemType in itemTypeList" required>
                            <option value="" translate="load.pleaseSelect"></option>
                        </select>
                    </div>
                    <div style="float: left;margin-top: 21px;">
                        <button ng-click="searchButtonClicked()" class="btn btn-primary searchButton" ><span translate="load.search"></span>&nbsp;<i class="fa fa-search"></i></button>
                    </div>
                </div>
            </section>
        </div>
        <div class="col-sm-6">
            <section class="panel">
                <div class="panel-body" >
                    <div class="clearfix">
                        <div class="btn-group" style="margin-top: 18px;">
                            <a  ng-click="addButton()"
                                class="btn btn-success"><span translate="load.add"></span>&nbsp<i class="fa fa-plus"></i></a>
                           <%-- <a  ng-click="failure()"
                                class="btn btn-primary">一键失效</a>--%>
                        </div>

                        <div style="float: right;">
                            <form class="searchform"  method="post" style="float:left;margin-top: 15px;" >
                            <input type="text" class="form-control searchText" name="batchCode" ng-model="batchSearch.batchCode" placeholder="Search here..." onkeydown="globelQuery(event)"/>
                                <div style="float: left;margin-top: 7px;margin-left: 20px;">
                                    <button ng-click="searchButtonClicked()" class="btn btn-primary searchButton" ><span translate="load.easyRetrieval"></span>&nbsp;<i class="fa fa-search"></i></button>
                                </div>
                        </form>

                        </div>

                        <div style="float: right;">



                        </div>
                    </div>


                    <table class="table">
                        <thead>
                        <tr>
                            <th></th>
                            <th>#</th>
                            <th ><span translate="load.batchMaxCode"></span></th>
                            <th><span translate="load.GameId"></span></th>
                            <th><span translate="load.SiteId"></span></th>
                            <th><span translate="load.batchBatchCode"></span></th>
                            <th><span translate="load.batchActiveCode"></span></th>
                            <th><span translate="load.batchCreateTime"></span></th>
                            <th><span translate="load.batchProTime"></span></th>
                            <th><span translate="load.operation"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="batch in batchs">
                            <td align="center"><input type="checkbox" class="checkbox"
                                                      data-ng-click="check($index,chk)"data-ng-model="chk" value="" style="width:50px;"></td>
                            <td style="vertical-align:middle">{{batch.id}}</td>
                            <td hidden="hidden">{{batch.id}}</td>
                            <td style="vertical-align:middle">{{batch.maxCode}}</td>
                            <td style="vertical-align:middle">
                                <span ng-hide="batch.gameId!=1" class="label label-success label-mini" >{{batch.gameId|NumToStr7|translate}}</span>
                                <span ng-hide="batch.gameId==1" class="label label-default label-mini" >{{batch.gameId|NumToStr7|translate}}</span>
                            </td>
                            <td style="vertical-align:middle">
                                <span ng-hide="batch.siteId!=2" class="label label-mini" style="background-color: #769efa">{{batch.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="batch.siteId!=3" class="label label-mini" style="background-color: #6de90f">{{batch.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="batch.siteId!=4" class="label label-mini" style="background-color: #565acb">{{batch.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="batch.siteId!=5" class="label  label-mini" style="background-color: #2fd4cd">{{batch.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="batch.siteId!=6" class="label label-mini" style="background-color: #00b7ee">{{batch.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="batch.siteId!=7" class="label label-mini" style="background-color: #b33ac4">{{batch.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="batch.siteId!=8" class="label label-mini" style="background-color: #962928">{{batch.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="batch.siteId!=9" class="label label-mini" style="background-color: #adb327">{{batch.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="batch.siteId!=10" class="label label-mini" style="background-color: #af9312">{{batch.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="batch.siteId!=11" class="label label-mini" style="background-color: #a693af">{{batch.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="batch.siteId!=12" class="label  label-mini" style="background-color: #71af5c">{{batch.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="batch.siteId!=13" class="label label-mini" style="background-color: #acaf17">{{batch.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="batch.siteId!=14" class="label label-mini" style="background-color: #af6fad">{{batch.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="batch.siteId!=15" class="label label-mini" style="background-color: #6312af">{{batch.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="batch.siteId==2 || batch.siteId==3 || batch.siteId==4 || batch.siteId==5 || batch.siteId==6 || batch.siteId==7 || batch.siteId==8 || batch.siteId==9 || batch.siteId==10 || batch.siteId==11 || batch.siteId==12 || batch.siteId==13 || batch.siteId==14 || batch.siteId==15" class="label label-default label-mini" >{{batch.siteId|NumToStr9|translate}}</span>
                            </td>
                            <td style="vertical-align:middle">{{batch.batchCode}}</td>
                            <td style="vertical-align:middle">{{batch.activeCode}}</td>
                            <td style="vertical-align:middle">{{batch.createTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td style="vertical-align:middle">{{batch.prodTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td style="vertical-align:middle"><a ng-click="updateClicked()" style= "cursor:pointer;color: navy" ><span translate="load.update"></span><i class="fa fa-edit"></i></a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="pager">
                        <pager page-count="pageCount" current-page="currentPage" on-page-change="onPageChange()" first-text="load.HomePage" next-text="load.NextPage" prev-text="load.PreviousPage" last-text="load.EndPage" show-goto="true" goto-text="load.GoToPage"></pager>
                    </div>


                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="batchModalForAdd" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                    <h4 class="modal-title"><span translate="load.add"></span></h4>
                                </div>
                                <div class="modal-body">

                                    <form role="form" ng-submit="batchAddForms()" name="batchAddForm">
                                        <div class="form-group">
                                            <div class="form-group labelSpace">
                                                <label><span translate="load.batchMaxCode"></span>:<span style="color: red">*</span></label>
                                                <input type="number"  class="form-control code" name="maxCode"  min="0" max="1000000000000" ng-model="batchAddData.maxCode" required  integer>
                                            </div>
                                        </div>
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.batchBatchCode"></span>:<span style="color: red">*</span></label>
                                            <input type="text" class="form-control batchCode" name="batchCode"  ng-model="batchAddData.batchCode" required>
                                        </div>

                                        <div class="form-group labelSpace">
                                            <label><span translate="load.batchToyType"></span>:<span style="color: red">*</span></label>
                                            <select name="itemType" aria-controls="editable-sample" class="form-control itemType" ng-model="batchAddData.itemType"  ng-options="itemType.itemCode as itemType.itemName for itemType in itemTypeList" required>
                                                <option value="" translate="load.pleaseSelect"></option>
                                            </select>
                                        </div>

                                        <div class="form-group labelSpace">
                                            <label><span translate="load.batchProTime"></span>:<span style="color: red">*</span></label>
                                            <input size="16" type="text" name="proTime" readonly class="ui_timepicker form-control" ng-model="batchAddData.proTime" required>

                                        </div>

                                        <div class="form-group labelSpace">
                                            <label><span translate="load.GameId"></span>:<span style="color: red">*</span></label>
                                            <select name="gameList" aria-controls="editable-sample" class="form-control gameList"   ng-options="game.id as game.code|translate for game in gameList"  ng-change="resourceGameListChange(batchGameList)"   ng-model="batchGameList" required>
                                                <option value="" translate="load.pleaseSelect"></option>
                                            </select>

                                          <%--  <select name="gameList" aria-controls="editable-sample" class="form-control gameList" ng-model="batchGameList" ng-change="resourceGameListChange(batchGameList)"  required>
                                                <option value="" translate="load.pleaseSelect"></option>
                                                <option value={{game.id}} ng-repeat="game in gameList" ng-selected="gameSelected" translate={{game.code}}></option>
                                            </select> --%>
                                        </div>

                                        <div class="form-group labelSpace">
                                            <label><span translate="load.SiteId"></span>:<span style="color: red">*</span></label>
                                            <select name="siteList" aria-controls="editable-sample" class="form-control siteList"   ng-options="site.id as site.code|translate for site in siteList" ng-change="resourceSiteListChange(batchSiteList)"  ng-model="batchSiteList" required>
                                                <option value="" translate="load.pleaseSelect"></option>
                                            </select>

                                            <%--<select name="siteList" aria-controls="editable-sample" class="form-control siteList" ng-model="batchSiteList" ng-change="resourceSiteListChange(batchSiteList)"  required>
                                                <option value="" translate="load.pleaseSelect"></option>
                                                <option value={{site.id}} ng-repeat="site in siteList" ng-selected="siteTransfer" translate="site.{{site.code}}"></option>
                                            </select>--%>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal"><span translate="load.Cancel"></span></button>
                                            <button type="submit" class="btn btn-primary addSubmit"  ><span translate="load.Ok"></span></button>
                                            <p ng-hide="!batchAddForm.$invalid" style="color: red;margin-top: 15px;"><span translate="load.remindValidate"></span></p>
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