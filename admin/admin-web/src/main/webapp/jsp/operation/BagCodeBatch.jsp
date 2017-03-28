<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resource/adminex/js/DateTimePicker/DateInit.js"></script>
<script src="${ctx}/resource/adminex/js/multi-select-init.js"></script>
<script src="${ctx}/resource/adminex/js/icheck-init.js"></script>
<div>
    <div class="row" ng-controller="bagCodeBatchController">
        <div class="col-sm-6">
            <section class="panel" style="margin-top: 20px;">
                <header class="panel-heading">
                    <span translate="load.searchCriteria"></span>
                    <span class="tools pull-right">
                       <a href="javascript:;" class="fa fa-chevron-down" onclick="chevronClick(this)"></a>
                    </span>
                </header>
                <div class="panel-body searchCriteria">

                    <div style="float: left;margin-right: 20px;" class="labelSpace">
                        <label><span translate="load.startTime"></span></label>
                        <input size="16" type="text" name="proTime" readonly class="ui_timepicker form-control"
                               ng-model="bagCodeSearch.from">
                    </div>
                    <div style="float: left;margin-right: 20px;" class="labelSpace">
                        <label><span translate="load.endTime"></span></label>
                        <input size="16" type="text" name="proTime" readonly class="ui_timepicker form-control"
                               ng-model="bagCodeSearch.to">
                    </div>
                    <div style="float:left;margin-right: 20px;" class="labelSpace">
                        <label><span translate="load.bagGiftBagName"></span></label>
                        <input type="text" class="form-control searchText" ng-model="bagCodeSearch.batchName"
                               name="keyword"  />
                    </div>
                    <div style="float: left;margin-top: 21px;">
                        <button ng-click="searchButtonClicked()" class="btn btn-primary searchButton"><span
                                translate="load.search"></span>&nbsp;<i class="fa fa-search"></i></button>
                    </div>
                </div>
            </section>
        </div>
        <div class="col-sm-6">
            <section class="panel">
                <div class="panel-body">
                    <div class="clearfix">
                        <div class="btn-group" style="margin-top: 18px;">
                            <a ng-click="addButton()"
                               class="btn btn-success"><span translate="load.addBagBatch"></span>&nbsp<i class="fa fa-plus"></i></a>
                            <a class="btn btn-danger" ng-click="delete()"><span translate="load.batchDelte"></span>&nbsp<i
                                    class="fa fa-times"></i></a>
                        </div>

                        <div style="float: right;">
                            <form class="searchform labelSpace" method="post" style="float:left;margin-top: 15px;">
                                <input type="text" class="form-control searchText" ng-model="bagCodeSearch.batchName"
                                       name="keyword" placeholder={{giftBagSearchText|translate}} onkeydown="globelQuery(event)"/>
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
                            <%--<th><span translate="load.bagNumber"></span></th>--%>
                            <th><span translate="load.bagBatchId"></span></th>
                            <%--<th><span translate="load.bagMutexId"></span></th>--%>
                            <th><span translate="load.bagGiftBagName"></span></th>
                            <th><span translate="load.resGame"></span></th>
                            <th><span translate="load.bagGameArea"></span></th>
                            <th><span translate="load.resSite"></span></th>
                            <th><span translate="load.bagAmount"></span></th>
                            <th><span translate="load.bagCreateTime"></span></th>
                            <th><span translate="load.bagExpireTime"></span></th>
                            <th><span translate="load.bagValidStatus"></span></th>
                            <th><span translate="load.operation"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="bagCode in bagCodeDatas">
                            <td align="center"><input type="checkbox" class="checkbox"
                                                      data-ng-click="check(bagCode.batchId,chk)" data-ng-model="chk" value=""
                                                      style="width:50px;"></td>
                            <td style="vertical-align:middle">{{bagCode.batchId}}</td>
                            <td style="vertical-align:middle">{{bagCode.batchName}}</td>
                            <td style="vertical-align:middle">
                                <span ng-hide="bagCode.gameId!=1" class="label label-success label-mini" >{{bagCode.gameId|NumToStr7|translate}}</span>
                                <span ng-hide="bagCode.gameId==1" class="label label-default label-mini" >{{bagCode.gameId|NumToStr7|translate}}</span>
                            </td>
                            <td style="vertical-align:middle">
                                {{bagCode.gameAreaId}}
                            </td>
                            <td style="vertical-align:middle">
                                <span ng-hide="bagCode.siteId!=2"
                                      class="label label-mini"
                                      style="background-color: #769efa">{{bagCode.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="bagCode.siteId!=3" class="label label-mini"
                                          style="background-color: #6de90f">{{bagCode.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="bagCode.siteId!=4" class="label label-mini"
                                          style="background-color: #565acb">{{bagCode.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="bagCode.siteId!=5" class="label  label-mini"
                                          style="background-color: #2fd4cd">{{bagCode.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="bagCode.siteId!=6" class="label label-mini"
                                          style="background-color: #00b7ee">{{bagCode.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="bagCode.siteId!=7" class="label label-mini"
                                          style="background-color: #b33ac4">{{bagCode.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="bagCode.siteId!=8" class="label label-mini"
                                          style="background-color: #962928">{{bagCode.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="bagCode.siteId!=9" class="label label-mini"
                                          style="background-color: #adb327">{{bagCode.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="bagCode.siteId!=10" class="label label-mini"
                                          style="background-color: #af9312">{{bagCode.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="bagCode.siteId!=11" class="label label-mini"
                                          style="background-color: #a693af">{{bagCode.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="bagCode.siteId!=12" class="label  label-mini"
                                          style="background-color: #71af5c">{{bagCode.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="bagCode.siteId!=13" class="label label-mini"
                                          style="background-color: #acaf17">{{bagCode.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="bagCode.siteId!=14" class="label label-mini"
                                          style="background-color: #af6fad">{{bagCode.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="bagCode.siteId!=15" class="label label-mini"
                                          style="background-color: #6312af">{{bagCode.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="bagCode.siteId==2 || bagCode.siteId==3 || bagCode.siteId==4 || bagCode.siteId==5 || bagCode.siteId==6 || bagCode.siteId==7 || bagCode.siteId==8 || bagCode.siteId==9 || bagCode.siteId==10 || bagCode.siteId==11 || bagCode.siteId==12 || bagCode.siteId==13 || bagCode.siteId==14 || bagCode.siteId==15"
                                          class="label label-default label-mini">{{bagCode.siteId|NumToStr9|translate}}</span>
                            </td>
                            <td style="vertical-align:middle">{{bagCode.amount}}</td>
                            <td style="vertical-align:middle">{{bagCode.createDate|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td style="vertical-align:middle">{{bagCode.availableEndDate|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td style="vertical-align:middle">
                                <span ng-hide="bagCode.validStatus!=1" class="label label-success label-mini">{{bagCode.validStatus|NumToStr19|translate}}</span>
                                <span ng-hide="bagCode.validStatus!=2" class="label label-danger label-mini">{{bagCode.validStatus|NumToStr19|translate}}</span>
                                <span ng-hide="bagCode.validStatus!=3" class="label label-default label-mini">{{bagCode.validStatus|NumToStr19|translate}}</span>
                            </td>
                            <td style="vertical-align:middle"><a ng-click="updateClicked(bagCode.batchId)"
                                                                 style="cursor:pointer;color: navy"><span
                                    translate="load.update"></span><i class="fa fa-edit"></i></a>
                                <a  href="${ctx}/operation/bagCode/getBatchCodeExcel/{{bagCode.batchId}}" style="cursor:pointer;color: navy"  translate="load.bagExport"><i class="fa fa-edit"></i></a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="pager">
                        <pager page-count="pageCount" current-page="currentPage" on-page-change="onPageChange()"
                               first-text="load.HomePage" next-text="load.NextPage" prev-text="load.PreviousPage"
                               last-text="load.EndPage" show-goto="true" goto-text="load.GoToPage"></pager>
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