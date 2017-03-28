<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resource/adminex/js/icheck-init.js"></script>
<script src="${ctx}/resource/adminex/js/DateTimePicker/DateInit.js"></script>

<style>

    .chk_1 {
        display: none;
    }

    .chk_1 + label {
        background-color: #FFF;
        border: 1px solid #C1CACA;
        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05);
        padding: 7px;
        border-radius: 5px;
        display: inline-block;
        position: relative;
        margin-right: -20px;
    }

    .chk_1 + label:active {
        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px 1px 3px rgba(0, 0, 0, 0.1);
    }

    .chk_1:checked + label {
        background-color: #ECF2F7;
        border: 1px solid #92A1AC;
        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05), inset 15px 10px -12px rgba(255, 255, 255, 0.1);
        color: #243441;
    }

    .chk_1:checked + label:after {
        content: '\2714';
    / / 勾选符号 position: absolute;
        top: 0px;
        left: 0px;
        color: red;
        width: 100%;
        text-align: center;
        font-size: 1.4em;
        padding: 1px 0 0 0;
        vertical-align: text-top;
    }


</style>
<div  ng-controller="mailOrderFailureController">

    <div class="row">
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
                               ng-model="mailOrderFailureSearch.from">
                    </div>
                    <div style="float: left;margin-right: 20px;" class="labelSpace">
                        <label><span translate="load.endTime"></span></label>
                        <input size="16" type="text" name="proTime" readonly class="ui_timepicker form-control"
                               ng-model="mailOrderFailureSearch.to">
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
                <div class="panel-body" style="overflow:auto;">

                    <div class="clearfix">

                        <div class="btn-group">
                            <a class="btn btn-danger" ng-click="delete()"><span translate="load.batchDelte"></span>&nbsp<i
                                    class="fa fa-times"></i></a>
                        </div>
                        <div style="float: right;">
                            <form class="searchform" method="post">
                                <input type="text" class="form-control searchText" name="keyword"
                                       ng-model="mailOrderFailureSearch.mailTitle" placeholder={{placeholderSearch|translate}}
                                       onkeydown="globelQuery(event)"/>
                                <div style="float: left;margin-top: 7px;margin-left: 20px;">
                                    <button ng-click="searchButtonClicked()" class="btn btn-primary searchButton"><span
                                            translate="load.easyRetrieval"></span>&nbsp;<i class="fa fa-search"></i>
                                    </button>
                                </div>

                            </form>
                        </div>
                    </div>


                    <section>

                        <table class="table table-striped table-condensed table-responsive">
                            <thead>
                            <tr>
                                <th></th>
                                <th>#</th>
                                <th><span translate="load.mailOrderId"></span></th>
                                <th><span translate="load.mailTitle"></span></th>
                                <th><span translate="load.failureReasons"></span></th>
                                <th><span translate="load.createTime"></span></th>
                                <th><span translate="load.remark"></span></th>
                                <th><span translate="load.operation"></span></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="orderFailure in orderFailureData">
                                <td align="center"><input type="checkbox" class="checkbox"
                                                          data-ng-click="check($index,chk)" data-ng-model="chk" value=""
                                                          style="width:50px;"></td>
                                <td style="vertical-align:middle">{{$index + 1}}</td>
                                <td hidden="hidden">{{orderFailure.id}}</td>
                                <td style="vertical-align:middle">{{orderFailure.mailOrderId}}</td>
                                <td style="vertical-align:middle">{{orderFailure.mailTitle}}</td>
                                <td style="vertical-align:middle">{{orderFailure.failureReasons}}</td>
                                <td style="vertical-align:middle">{{orderFailure.createTime|date:'yyyy-MM-dd
                                    HH:mm:ss'}}
                                </td>
                                <td style="vertical-align:middle">{{orderFailure.remark}}</td>
                                <td style="vertical-align:middle"><a ng-click="viewClicked(orderFailure.id)"
                                                                     style="cursor:pointer;color: navy"><span
                                        translate="load.applicationSee"></span><i class="fa fa-eye"></i></a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <div class="pager">
                            <pager page-count="pageCount" current-page="currentPage" on-page-change="onPageChange()"
                                   first-text="load.HomePage" next-text="load.NextPage" prev-text="load.PreviousPage"
                                   last-text="load.EndPage" show-goto="true" goto-text="load.GoToPage"></pager>
                        </div>
                    </section>


                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                         id="failureModalForView" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.logDetails"></span></h4>
                                </div>
                                <div class="modal-body">
                                    <div class="panel-body">
                                        <div class="panel-body">
                                            <ul class="p-info">
                                                <li>
                                                    <div class="title" translate="load.mailOrderId"></div>
                                                    <div class="desk">{{orderFailureDataSee.mailOrderId}}</div>
                                                </li>
                                                <li>
                                                    <div class="title" translate="load.mailTitle"></div>
                                                    <div class="desk">{{orderFailureDataSee.mailTitle}}</div>
                                                </li>
                                                <li>
                                                    <div class="title" translate="load.failureReasons"></div>
                                                    <div class="desk">{{orderFailureDataSee.failureReasons}}</div>
                                                </li>
                                                <li>
                                                    <div class="title" translate="load.createTime"></div>
                                                    <div class="desk">{{orderFailureDataSee.createTime|date:'yyyy-MM-dd
                                                        HH:mm:ss'}}
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="title" translate="load.remark"></div>
                                                    <div class="desk">{{orderFailureDataSee.remark}}</div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                                translate="load.Cancel"></span></button>
                                    </div>
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
                                            ng-click="confirm()">
                                        <span translate="load.Ok"></span></button>
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