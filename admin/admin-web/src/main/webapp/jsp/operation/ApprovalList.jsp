<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resource/adminex/js/multi-select-init.js"></script>
<script src="${ctx}/resource/adminex/js/icheck-init.js"></script>
<script src="${ctx}/resource/adminex/js/DateTimePicker/DateInit.js"></script>
<div>
    <div class="row" ng-controller="approvalController">
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
                        <input size="16" type="text" name="proTime" readonly class="ui_timepicker form-control" ng-model="articleSearch.from" >
                    </div>
                    <div style="float: left;margin-right: 20px;" class="labelSpace">
                        <label><span translate="load.endTime"></span></label>
                        <input size="16" type="text" name="proTime" readonly class="ui_timepicker form-control" ng-model="articleSearch.to" >
                    </div>
                    <form class="searchform labelSpace"  method="post" style="float:left;margin-right: 20px;">
                        <label><span translate="load.applicationMailType"></span></label>
                        <select name="bindStatus" aria-controls="editable-sample" class="form-control bindStatus"    ng-change="statusChange(mailType)"  ng-model="articleSearch.mailType" style="float: left;" required>
                            <option value="" translate="load.pleaseSelect"></option>
                            <option value="1" translate="load.ActivityReward"></option>
                            <option value="2" translate="load.IndividualCompensation"></option>
                            <option value="3" translate="load.InternalApplication"></option>
                            <option value="4" translate="load.RechargeCompensation"></option>
                            <option value="5" translate="load.Other"></option>
                        </select>
                    </form>
                    <form class="searchform labelSpace"  method="post" style="float:left;margin-right: 20px;">
                        <label><span translate="load.applicationSendByType"></span></label>
                        <select name="bindStatus" aria-controls="editable-sample" class="form-control bindStatus"    ng-change="statusChange(sendType)"  ng-model="articleSearch.sendType" style="float: left;" required>
                            <option value="" translate="load.pleaseSelect"></option>
                            <option value="1" translate="load.PersonalRule"></option>
                            <option value="2" translate="load.AllRule"></option>
                        </select>
                    </form>
                    <form class="searchform labelSpace"  method="post" style="float:left;margin-right: 20px;">
                        <label><span translate="load.applicationMailState"></span></label>
                        <select name="bindStatus" aria-controls="editable-sample" class="form-control bindStatus"    ng-change="statusChange(sendStatus)"  ng-model="articleSearch.sendStatus" style="float: left;" required>
                            <option value="" translate="load.pleaseSelect"></option>
                            <option value="1" translate="load.Unsent"></option>
                            <option value="2" translate="load.sending"></option>
                            <option value="3" translate="load.sendSuccess"></option>
                            <option value="4" translate="load.sendFailed"></option>
                        </select>
                    </form>
                    <form class="searchform labelSpace"  method="post" style="float:left;margin-right: 20px;">
                        <label><span translate="load.applicationApprovalStatus"></span></label>
                        <select name="bindStatus" aria-controls="editable-sample" class="form-control bindStatus"    ng-change="statusChange(approvalStatus)"  ng-model="articleSearch.approvalStatus" style="float: left;" required>
                            <option value="" translate="load.pleaseSelect"></option>
                            <option value="1" translate="load.NotApply"></option>
                            <option value="2" translate="load.NotApprove"></option>
                            <option value="3" translate="load.ApprovalMessagesBodyToSuccess"></option>
                            <option value="4" translate="load.ApprovalMessagesBodyToFailure"></option>
                        </select>
                    </form>

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
                            <a class="btn btn-info" ng-click="pocApprovalFor()"><span translate="load.oneKeyApproval"></span>&nbsp<i class="fa fa-flag"></i></a>
                        </div>

                        <div style="float: right;">

                            <form class="searchform"  method="post" style="float:left;margin-top: 15px;">
                                <input type="text" class="form-control searchText"   ng-model="articleSearch.name"  name="keyword" placeholder={{placeholderSearch|translate}} onkeydown="globelQuery(event)"/>
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
                            <th><span translate="load.applicationEmailTitle"></span></th>
                            <th><span translate="load.applicationGameId"></span></th>
                            <th><span translate="load.applicationAreaIds"></span></th>
                            <th><span translate="load.applicationCreateTime"></span></th>
                            <th><span translate="load.applicationMailType"></span></th>
                            <th><span translate="load.applicationSendType"></span></th>
                            <th><span translate="load.applicationMailState"></span></th>
                            <th><span translate="load.applicationApprovalStatus"></span></th>
                            <th><span translate="load.operation"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="application in applicationDatas">
                            <td align="center"><input type="checkbox" class="checkbox"
                                                      data-ng-click="check($index,chk)"data-ng-model="chk" value="" style="width:50px;"></td>
                            <td style="vertical-align:middle">{{application.mailOrder.id}}</td>
                            <td style="vertical-align:middle">{{application.mailOrder.mailTitle}}</td>
                            <td style="vertical-align:middle">
                                <span ng-hide="application.mailOrder.gameId!=1" class="label label-success label-mini" >{{application.mailOrder.gameId|NumToStr7|translate}}</span>
                                <span ng-hide="application.mailOrder.gameId==1" class="label label-default label-mini" >{{application.mailOrder.gameId|NumToStr7|translate}}</span>
                            </td>
                            <td style="vertical-align:middle">{{application.mailOrder.areaIds}}</td>
                            <td style="vertical-align:middle">{{application.mailOrder.createTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td style="vertical-align:middle">
                                <span ng-hide="application.mailOrder.mailType!=1" class="label label-success label-mini" >{{application.mailOrder.mailType|NumToStr10|translate}}</span>
                                <span ng-hide="application.mailOrder.mailType!=2" class="label label-default label-mini" >{{application.mailOrder.mailType|NumToStr10|translate}}</span>
                                <span ng-hide="application.mailOrder.mailType!=3" class="label label-primary label-mini" >{{application.mailOrder.mailType|NumToStr10|translate}}</span>
                                <span ng-hide="application.mailOrder.mailType!=4" class="label label-Warning label-mini" >{{application.mailOrder.mailType|NumToStr10|translate}}</span>
                                <span ng-hide="application.mailOrder.mailType!=5" class="label label-info label-mini" >{{application.mailOrder.mailType|NumToStr10|translate}}</span>
                            </td>
                            <td style="vertical-align:middle">
                                <span ng-hide="application.mailOrder.sendType!=1" class="label label-primary label-mini" >{{application.mailOrder.sendType|NumToStr11|translate}}</span>
                                <span ng-hide="application.mailOrder.sendType!=2" class="label label-info label-mini" >{{application.mailOrder.sendType|NumToStr11|translate}}</span>
                            </td>
                            <td style="vertical-align:middle">
                                <span ng-hide="application.mailOrder.sendStatus!=1" class="label label-info label-mini" >{{application.mailOrder.sendStatus|NumToStr12|translate}}</span>
                                <span ng-hide="application.mailOrder.sendStatus!=2" class="label label-Warning label-mini" >{{application.mailOrder.sendStatus|NumToStr12|translate}}</span>
                                <span ng-hide="application.mailOrder.sendStatus!=3" class="label label-success label-mini" >{{application.mailOrder.sendStatus|NumToStr12|translate}}</span>
                                <span ng-hide="application.mailOrder.sendStatus!=4" class="label label-danger label-mini" >{{application.mailOrder.sendStatus|NumToStr12|translate}}</span>
                            </td>
                            <td style="vertical-align:middle">
                                <span ng-hide="application.approvalStatus!=1" class="label label-info label-mini" > {{application.approvalStatus|NumToStr13|translate}}</span>
                                <span ng-hide="application.approvalStatus!=2" class="label label-Warning label-mini" > {{application.approvalStatus|NumToStr13|translate}}</span>
                                <span ng-hide="application.approvalStatus!=3" class="label label-success label-mini" > {{application.approvalStatus|NumToStr13|translate}}</span>
                                <span ng-hide="application.approvalStatus!=4" class="label label-danger label-mini" > {{application.approvalStatus|NumToStr13|translate}}</span>
                            </td>
                            <td style="vertical-align:middle"><a ng-click="updateClicked(application.mailOrder.id)" style= "cursor:pointer;color: navy" ><span translate="load.applicationSee"></span><i class="fa fa-eye"></i></a>
                                <a ng-click="approvalFor(application.mailOrder.id)" ng-hide="application.approvalStatus!=2" style= "cursor:pointer;"><span translate="load.ApprovalButton"></span><i class="fa fa-check"></i></a>
                                <a  ng-click="approvalForEye(application.mailOrder.id)" ng-hide="application.approvalStatus==2" style= "cursor:pointer;color:#55b2ff"><span translate="load.GetApprovalFrom"></span><i class="fa fa-eye"></i></a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="pager">
                        <pager page-count="pageCount" current-page="currentPage" on-page-change="onPageChange()" first-text="load.HomePage" next-text="load.NextPage" prev-text="load.PreviousPage" last-text="load.EndPage" show-goto="true" goto-text="load.GoToPage"></pager>
                    </div>

                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="approvalAddModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                    <h4 class="modal-title"><span translate="load.ApprovalFrom"></span></h4>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-md-10">
                                            <form class="form-horizontal bucket-form" >
                                                <div class="form-group">
                                                        <label><span translate="load.applicationApprovalContent"></span>:</label></span>
                                                        <textarea class="form-control" style="height: 150px;resize:none;" ng-model="approvalData.approvalContent"></textarea>                                                    </div>
                                                <div class="modal-footer" style="width: 550px;">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal"><span translate="load.Cancel"></span></button>
                                                    <a class="btn btn-danger" ng-click="approvalFromAdd(4)"><span translate="load.ApprovalFromRetrun"></span>&nbsp<i class="fa fa-times"></i></a>
                                                    <button type="submit" class="btn btn-primary addSubmit"  ng-click="approvalFromAdd(3)" > <span translate="load.ApprovalFromSuccess"></span></button>
                                                </div>
                                            </form>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="approvalEyeModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                    <h4 class="modal-title"><span translate="load.ApprovalFrom"></span></h4>
                                </div>
                                <div class="modal-body">
                                    <div class="row">

                                        <div class="col-md-10">
                                            <div class="form-group">
                                                <label><span translate="load.applicationApplyUser"></span>:</label></span>
                                                <input readonly class="form-control"  ng-model="approvalDataEye.applyUser" />
                                            </div>
                                        </div>
                                        <div class="col-md-10">
                                                <div class="form-group">
                                                    <label><span translate="load.applicationApprovalContent"></span>:</label></span>
                                                    <textarea readonly class="form-control" style="height: 150px;resize:none;" ng-model="approvalDataEye.approvalContent"></textarea>
                                                </div>
                                                <div class="modal-footer" style="width: 550px;">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal"><span translate="load.Cancel"></span></button>
                                                    <button type="submit" class="btn btn-primary addSubmit" data-dismiss="modal"> <span translate="load.Ok"></span></button>
                                                </div>
                                        </div>
                                    </div>
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
