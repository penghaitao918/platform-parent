<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resource/adminex/js/DateTimePicker/DateInit.js"></script>
<script src="${ctx}/resource/adminex/js/multi-select-init.js"></script>
<script src="${ctx}/resource/adminex/js/icheck-init.js"></script>
<div >
    <div class="row" ng-controller="noticeController">
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
                        <input size="16" type="text" name="proTime" readonly class="ui_timepicker form-control" ng-model="noticeSearch.from" >
                    </div>
                    <div style="float: left;margin-right: 20px;" class="labelSpace">
                        <label><span translate="load.endTime"></span></label>
                        <input size="16" type="text" name="proTime" readonly class="ui_timepicker form-control" ng-model="noticeSearch.to" >
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
                            <a class="btn btn-danger" ng-click="delete()"><span translate="load.batchDelte"></span>&nbsp<i class="fa fa-times"></i></a>
                            <a class="btn btn-info" ng-click="pocOpen()"><span translate="load.OBT"></span>&nbsp<i class="fa fa-flag"></i></a>
                            <a class="btn btn-danger" ng-click="pocClose()"><span translate="load.superEsc"></span>&nbsp<i class="fa fa-flag"></i></a>
                        </div>

                        <div style="float: right;">
                            <form class="searchform labelSpace"  method="post" style="float:left;margin-top: 15px;">
                                <input type="text" class="form-control searchText"   ng-model="noticeSearch.noticeTitle"  name="keyword" placeholder={{noticeSearchText|translate}} onkeydown="globelQuery(event)"/>
                                <div style="float: left;margin-top: 7px;margin-left: 20px;">
                                    <button ng-click="searchButtonClicked()" class="btn btn-primary searchButton" ><span translate="load.easyRetrieval"></span>&nbsp;<i class="fa fa-search"></i></button>
                                </div>
                            </form>
                        </div>
                    </div>


                    <table class="table">
                        <thead>
                        <tr>
                            <th></th>
                            <th>id</th>
                            <th><span translate="load.noticeNoticeTitle"></span></th>
                            <th><span translate="load.noticeNoticeType"></span></th>
                            <th><span translate="load.noticeNoticeState"></span></th>
                            <th><span translate="load.noticeCreateTime"></span></th>
                            <th><span translate="load.startTime"></span></th>
                            <th><span translate="load.endTime"></span></th>
                            <th><span translate="load.noticePublishSpace"></span></th>
                            <th><span translate="load.noticePublisher"></span></th>
                            <th><span translate="load.noticePublishCount"></span></th>
                            <th><span translate="load.operation"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="notice in noticeDatas">
                            <td align="center"><input type="checkbox" class="checkbox"
                                                      data-ng-click="check($index,chk)"data-ng-model="chk" value="" style="width:50px;"></td>
                            <td style="vertical-align:middle">{{notice.id}}</td>
                            <td style="vertical-align:middle">{{notice.noticeTitle}}</td>
                            <td style="vertical-align:middle">
                                <span ng-hide="notice.noticeType!=1" class="label label-primary label-mini" translate="{{notice.noticeType|NumToStr14}}"></span>
                                <span ng-hide="notice.noticeType!=2" class="label label-info label-mini" translate="{{notice.noticeType|NumToStr14}}"></span>
                                <span ng-hide="notice.noticeType!=3" class="label label-default label-mini" translate="{{notice.noticeType|NumToStr14}}"></span>
                            </td>
                            <td style="vertical-align:middle">
                                <span ng-hide="notice.noticeState!=1" class="label label-success label-mini" translate="{{notice.noticeState|NumToStr2}}"></span>
                                <span ng-hide="notice.noticeState!=0" class="label label-danger label-mini" translate="{{notice.noticeState|NumToStr2}}"></span>
                            </td>
                            <td style="vertical-align:middle">{{notice.createTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td style="vertical-align:middle">{{notice.beginTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td style="vertical-align:middle">{{notice.endTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td style="vertical-align:middle">{{notice.publishSpace}}</td>
                            <td style="vertical-align:middle">{{notice.publisher}}</td>
                            <td style="vertical-align:middle">{{notice.publishCount}}</td>
                            <td style="vertical-align:middle"><a ng-click="updateClicked()" style= "cursor:pointer;color: navy" ><span translate="load.update"></span><i class="fa fa-edit"></i></a>
                                <a ng-click="open()" ng-hide="notice.noticeState==1" style= "cursor:pointer;"><span translate="load.Open"></span><i class="fa fa-check"></i></a>
                                <a ng-click="close()" ng-hide="notice.noticeState==0" style= "cursor:pointer;color: red" ><span translate="load.Close"></span><i class="fa fa-times"></i></a> 
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="pager">
                        <pager page-count="pageCount" current-page="currentPage" on-page-change="onPageChange()" first-text="load.HomePage" next-text="load.NextPage" prev-text="load.PreviousPage" last-text="load.EndPage" show-goto="true" goto-text="load.GoToPage"></pager>
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