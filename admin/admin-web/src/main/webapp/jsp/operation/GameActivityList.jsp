<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resource/adminex/js/DateTimePicker/DateInit.js"></script>
<script src="${ctx}/resource/adminex/js/multi-select-init.js"></script>
<script src="${ctx}/resource/adminex/js/icheck-init.js"></script>
<div >
    <div class="row" ng-controller="gameActivityController">
        <div class="col-sm-6">
            <section class="panel">
                <div class="panel-body">
                    <div class="clearfix">
                        <div class="btn-group" style="margin-top: 18px;">
                            <a ng-click="addButton()"
                               class="btn btn-success"><span translate="load.add"></span>&nbsp<i class="fa fa-plus"></i></a>
                            <a class="btn btn-info" ng-click="pocOpen()"><span translate="load.OBT"></span>&nbsp<i
                                    class="fa fa-flag"></i></a>
                            <a class="btn btn-danger" ng-click="pocClose()"><span translate="load.superEsc"></span>&nbsp<i
                                    class="fa fa-flag"></i></a>
                            <a class="btn btn-danger" ng-click="delete()"><span translate="load.batchDelte"></span>&nbsp<i class="fa fa-times"></i></a>
                        </div>
                    </div>


                    <table class="table">
                        <thead>
                        <tr>
                            <th></th>
                            <th><span translate="load.activityId"></span></th>
                            <th><span translate="load.activityTaskId"></span></th>
                            <th><span translate="load.activityContent"></span></th>
                            <th><span translate="load.activityValidStatus"></span></th>
                            <th><span translate="load.activityStartTime"></span></th>
                            <th><span translate="load.activityEndTime"></span></th>
                            <th><span translate="load.operation"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="activity in gameActivityDatas">
                            <td align="center"><input type="checkbox" class="checkbox"
                                                      data-ng-click="check(activity.id,chk)" data-ng-model="chk"
                                                      value="" style="width:50px;"></td>
                            <td style="vertical-align:middle">{{activity.activityId}}</td>
                            <td style="vertical-align:middle">{{activity.taskId}}</td>
                            <td style="vertical-align:middle">{{activity.activityContent}}</td>
                            <td style="vertical-align:middle">
                                <span ng-hide="activity.validStatus!=1" class="label label-success label-mini"
                                      translate="{{activity.validStatus|NumToStr17}}"></span>
                                <span ng-hide="activity.validStatus!=2" class="label label-danger label-mini"
                                      translate="{{activity.validStatus|NumToStr17}}"></span></td>
                            <td style="vertical-align:middle">{{activity.startTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td style="vertical-align:middle">{{activity.endTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td style="vertical-align:middle"><a ng-click="updateClicked(activity.id)"
                                                                 style="cursor:pointer;color: navy"><span
                                    translate="load.update"></span><i class="fa fa-edit"></i></a>
                                <a ng-click="open(activity.id)" ng-hide="activity.validStatus==1"
                                   style="cursor:pointer;"><span translate="load.Open"></span><i
                                        class="fa fa-check"></i></a>
                                <a ng-click="close(activity.id)" ng-hide="activity.validStatus==2"
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