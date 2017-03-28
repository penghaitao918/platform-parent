<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resource/adminex/js/DateTimePicker/DateInit.js"></script>
<script src="${ctx}/resource/adminex/js/multi-select-init.js"></script>
<script src="${ctx}/resource/adminex/js/icheck-init.js"></script>
<div>
    <div class="row" ng-controller="bagCodeDisposeController">
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
                        <label><span translate="load.codeDisposeBatchCode"></span></label>
                        <input type="text" class="form-control searchText" ng-model="noticeSearch.noticeTitle"
                               name="keyword"  />
                    </div>
                    <div style="float: left;margin-right: 20px;" class="labelSpace">
                        <label><span translate="load.codeDisposeUseStatus"></span></label>
                        <select name="codeDisposeUseStatus" aria-controls="editable-sample"
                                class="form-control noticeType" ng-model="noticeData.noticeTypes"
                                ng-change="noticeTypeChange(noticeData.noticeTypes)" required>
                            <option value="" translate="load.pleaseSelect"></option>
                            <option value="1" translate="load.noticePopup"></option>
                            <option value="2" translate="load.noticeRoll"></option>
                            <option value="3" translate="load.noticeSystem"></option>
                        </select>
                    </div>
                    <div style="float: left;margin-right: 20px;" class="labelSpace">
                        <label><span translate="load.codeDisposeBagCode"></span></label>
                        <input type="text" class="form-control searchText" ng-model="noticeSearch.noticeTitle"
                               name="keyword"  />
                    </div>
                    <div style="float:left;margin-right: 20px;" class="labelSpace">
                        <label><span translate="load.codeDisposeRoleId"></span></label>
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
                    <div class="clearfix">
                        <div class="btn-group" style="margin-top: 18px;">
                            <a ng-click="addButton()"
                               class="btn btn-success"><span translate="load.addBagBatch"></span>&nbsp<i class="fa fa-plus"></i></a>
                            <a class="btn btn-danger" ng-click="delete()"><span translate="load.batchDelte"></span>&nbsp<i
                                    class="fa fa-times"></i></a>
                            <a class="btn btn-info" ng-click="pocOpen()"><span translate="load.deriveBagBatch"></span>&nbsp<i
                                    class="fa fa-flag"></i></a>
                        </div>

                        <div style="float: right;">
                            <form class="searchform labelSpace" method="post" style="float:left;margin-top: 15px;">
                                <input type="text" class="form-control searchText" ng-model="noticeSearch.noticeTitle"
                                       name="keyword" placeholder="Search here..." onkeydown="globelQuery(event)"/>
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
                            <th><span translate="load.codeDisposeNumber"></span></th>
                            <th><span translate="load.codeDisposeGameName"></span></th>
                            <th><span translate="load.codeDisposeGameArea"></span></th>
                            <th><span translate="load.codeDisposeArea"></span></th>
                            <th><span translate="load.codeDisposeSite"></span></th>
                            <th><span translate="load.codeDisposeUseRole"></span></th>
                            <th><span translate="load.codeDisposeUseTime"></span></th>
                            <th><span translate="load.codeDisposeValidStatus"></span></th>
                            <th><span translate="load.operation"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="bagCode in bagCodeDatas">
                            <td align="center"><input type="checkbox" class="checkbox"
                                                      data-ng-click="check($index,chk)" data-ng-model="chk" value=""
                                                      style="width:50px;"></td>
                            <td style="vertical-align:middle">{{notice.id}}</td>
                            <td style="vertical-align:middle">{{notice.noticeTitle}}</td>
                            <td style="vertical-align:middle">
                                <span ng-hide="notice.noticeType!=1" class="label label-primary label-mini"
                                      translate="{{notice.noticeType|NumToStr14}}"></span>
                                <span ng-hide="notice.noticeType!=2" class="label label-info label-mini"
                                      translate="{{notice.noticeType|NumToStr14}}"></span>
                                <span ng-hide="notice.noticeType!=3" class="label label-default label-mini"
                                      translate="{{notice.noticeType|NumToStr14}}"></span>
                            </td>
                            <td style="vertical-align:middle">
                                <span ng-hide="notice.noticeState!=1" class="label label-success label-mini"
                                      translate="{{notice.noticeState|NumToStr2}}"></span>
                                <span ng-hide="notice.noticeState!=0" class="label label-danger label-mini"
                                      translate="{{notice.noticeState|NumToStr2}}"></span>
                            </td>
                            <td style="vertical-align:middle">{{notice.createTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td style="vertical-align:middle">{{notice.beginTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
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