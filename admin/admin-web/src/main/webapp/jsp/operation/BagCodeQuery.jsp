<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resource/adminex/js/DateTimePicker/DateInit.js"></script>
<script src="${ctx}/resource/adminex/js/multi-select-init.js"></script>
<script src="${ctx}/resource/adminex/js/icheck-init.js"></script>
<div>
    <div class="row" ng-controller="bagCodeQueryController">
        <div class="col-sm-6">
            <section class="panel" style="margin-top: 20px;">
                <header class="panel-heading">
                    <span translate="load.searchCriteria"></span>
                    <span class="tools pull-right">
                       <a href="javascript:;" class="fa fa-chevron-down" onclick="chevronClick(this)"></a>
                    </span>
                </header>
                <div class="panel-body searchCriteria">
                    <div style="float:left;margin-right: 20px;" class="labelSpace">
                        <label><span translate="load.codeQueryInputCode"></span></label>
                        <input type="text" class="form-control searchText" ng-model="noticeSearch.noticeTitle"
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



                    <table class="table">
                        <thead>
                        <tr>
                            <th></th>
                            <th><span translate="load.codeQueryNumber"></span></th>
                            <th><span translate="load.codeQueryBatchCode"></span></th>
                            <th><span translate="load.codeQueryGameName"></span></th>
                            <th><span translate="load.codeQueryGameArea"></span></th>
                            <th><span translate="load.codeQueryServer"></span></th>
                            <th><span translate="load.codeQueryChannel"></span></th>
                            <th><span translate="load.codeQueryUserStatus"></span></th>
                            <th><span translate="load.codeQueryUserAccount"></span></th>
                            <th><span translate="load.codeQueryUserTime"></span></th>
                            <th><span translate="load.codeQueryValidStatus"></span></th>
                            <th><span translate="load.operation"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="bagCode in bagCodeQueryDatas">
                            <td align="center"><input type="checkbox" class="checkbox"
                                                      data-ng-click="check($index,chk)" data-ng-model="chk" value=""
                                                      style="width:50px;"></td>
                            <td style="vertical-align:middle">{{bagCode.promoteCode}}</td>
                            <td style="vertical-align:middle">{{bagCode.batchId}}</td>
                            <td style="vertical-align:middle">
                                {{bagCode.drawGameId}}
                            </td>
                            <td style="vertical-align:middle">
                                {{bagCode.drawGameAreaId}}
                            </td>
                            <td style="vertical-align:middle">{{notice.drawSiteId}}</td>
                            <td style="vertical-align:middle">{{notice.beginTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td style="vertical-align:middle">{{notice.endTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td style="vertical-align:middle">{{notice.publishCount}}</td>
                            <td style="vertical-align:middle"><a ng-click="updateClicked()"
                                                                 style="cursor:pointer;color: navy"><span
                                    translate="load.update"></span><i class="fa fa-edit"></i></a>
                                <a ng-click="open()" ng-hide="notice.noticeState==1" style="cursor:pointer;"><span
                                        translate="load.Open"></span><i class="fa fa-check"></i></a>
                                <a ng-click="close()" ng-hide="notice.noticeState==0" style="cursor:pointer;color: red"><span
                                        translate="load.Close"></span><i class="fa fa-times"></i></a> 
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