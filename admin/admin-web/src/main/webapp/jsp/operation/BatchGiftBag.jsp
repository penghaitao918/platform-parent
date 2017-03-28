<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resource/adminex/js/DateTimePicker/DateInit.js"></script>
<script src="${ctx}/resource/adminex/js/multi-select-init.js"></script>
<script src="${ctx}/resource/adminex/js/icheck-init.js"></script>
<div>
    <div class="row" ng-controller="batchGiftBagController">
        <div class="col-sm-6">
            <section class="panel">
                <div class="panel-body">
                    <div class="clearfix">
                        <div class="btn-group" style="margin-top: 18px;">
                            <a ng-click="addButton()"
                               class="btn btn-success"><span translate="load.addGiftBag"></span>&nbsp<i
                                    class="fa fa-plus"></i></a>
                            <a class="btn btn-danger" ng-click="delete()"><span translate="load.batchDelte"></span>&nbsp<i
                                    class="fa fa-times"></i></a>
                        </div>

                        <div style="float: right;">
                            <form class="searchform labelSpace" method="post" style="float:left;margin-top: 15px;">
                                <input type="text" class="form-control searchText" ng-model="giftBagSearch.packageName"
                                       name="keyword"    placeholder={{giftBagSearchText|translate}} onkeydown="globelQuery(event)"/>
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
                            <th><span translate="load.giftBagNumber"></span></th>
                            <th><span translate="load.giftBagApplyGame"></span></th>
                            <th><span translate="load.giftBagName"></span></th>
                            <th><span translate="load.giftBagExplain"></span></th>
                            <th><span translate="load.giftBagRemark"></span></th>
                            <th><span translate="load.giftBagProduceTime"></span></th>
                            <th><span translate="load.giftValidStatus"></span></th>
                            <th><span translate="load.operation"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="bagCode in bagCodeDatas">
                            <td align="center"><input type="checkbox" class="checkbox"
                                                      data-ng-click="check(bagCode.packageId,chk)" data-ng-model="chk" value=""
                                                      style="width:50px;"></td>
                            <td style="vertical-align:middle">{{bagCode.packageId}}</td>
                            <td style="vertical-align:middle"> <span ng-hide="bagCode.gameId!=1" class="label label-success label-mini" >{{bagCode.gameId|NumToStr7|translate}}</span>
                                <span ng-hide="bagCode.gameId==1" class="label label-default label-mini" >{{bagCode.gameId|NumToStr7|translate}}</span></td>
                            <td style="vertical-align:middle">
                                {{bagCode.packageName}}
                            </td>
                            <td style="vertical-align:middle">
                                {{bagCode.packageGreet}}
                            </td>
                            <td style="vertical-align:middle"> {{bagCode.packageGreet}}</td>
                            <td style="vertical-align:middle"> {{bagCode.createDate|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td style="vertical-align:middle">  <span ng-hide="bagCode.validStatus!=1"
                                                                      class="label label-success label-mini"
                                                                      translate="{{bagCode.validStatus|NumToStr20}}"></span>
                                <span ng-hide="bagCode.validStatus!=2" class="label label-danger label-mini"
                                      translate="{{bagCode.validStatus|NumToStr20}}"></span></td>
                            <td style="vertical-align:middle"><a ng-click="updateClicked(bagCode.packageId)"
                                                                 style="cursor:pointer;color: navy"><span
                                    translate="load.update"></span><i class="fa fa-edit"></i></a>
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