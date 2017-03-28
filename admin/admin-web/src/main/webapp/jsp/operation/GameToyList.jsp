<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/resource/adminex/js/DateTimePicker/DateInit.js"></script>
<script src="${ctx}/resource/adminex/js/multi-select-init.js"></script>
<script src="${ctx}/resource/adminex/js/icheck-init.js"></script>

<div  >

    <div class="row"  ng-controller="gameToyController">
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
                        <input size="16" type="text" name="proTime" readonly class="ui_timepicker form-control" ng-model="gameToySearch.from" >
                    </div>
                    <div style="float: left;margin-right: 20px;" class="labelSpace">
                        <label><span translate="load.endTime"></span></label>
                        <input size="16" type="text" name="proTime" readonly class="ui_timepicker form-control" ng-model="gameToySearch.to" >
                    </div>
                    <div style="float: left;margin-right: 20px;" class="labelSpace">
                        <label><span translate="load.bindingState"></span></label>
                        <select name="bindStatus" aria-controls="editable-sample" class="form-control bindStatus"    ng-change="bindStatusChange(bindStatus)"  ng-model="bindStatus" style="float: left;" required>
                            <option value="2" translate="load.BINDING">BINDING</option>
                            <option value="3" translate="load.BOUND">BOUND</option>
                            <option value="1" translate="load.UNBIND">UNBIND</option>
                            <option value="4" translate="load.INVALID">INVALID</option>
                        </select>
                    </div>
                    <div style="float: left;margin-top: 21px;">
                        <button ng-click="searchButtonClicked()" class="btn btn-primary searchButton" ><span translate="load.search"></span>&nbsp;<i class="fa fa-search"></i></button>
                    </div>
                </div>
            </section>
        </div>
        <div class="col-sm-6">
            <section class="panel" >
                <div class="panel-body" >
                    <div class="clearfix">
                        <div class="btn-group" style="margin-top: 18px;">
                            <a  ng-click="failure()"
                                class="btn btn-danger"><span translate="load.keyFailure"></span>&nbsp<i class="fa fa-frown-o"></i></a>
                        </div>

                        <div style="float: right;">
                            <form class="searchform"  method="post" style="float:left;margin-top: 15px;" >
                            <input type="text" class="form-control searchText" name="batchCode" ng-model="gameToySearch.batchCodeSearch" placeholder="Search here..." onkeydown="globelQuery(event)"/>
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
                            <th ><span translate="load.toyToyCode"></span></th>
                            <th><span translate="load.GameId"></span></th>
                            <th><span translate="load.SiteId"></span></th>
                            <th><span translate="load.toyBatchId"></span></th>
                            <th><span translate="load.toyCreateTime"></span></th>
                            <th><span translate="load.toyProdTime"></span></th>
                            <th><span translate="load.toyBindGame"></span></th>
                            <th><span translate="load.toyBindSite"></span></th>
                            <th><span translate="load.toyBindArea"></span></th>
                            <th><span translate="load.toyBindStatus"></span></th>
                            <th><span translate="load.toyBindTime"></span></th>
                            <th><span translate="load.operation"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="gameToy in gameToys">
                            <td align="center"><input type="checkbox" class="checkbox"
                                                      data-ng-click="check($index,chk)"data-ng-model="chk" value="" style="width:50px;"></td>
                            <td style="vertical-align:middle">{{gameToy.id}}</td>
                            <td hidden="hidden">{{gameToy.id}}</td>
                            <td style="vertical-align:middle">{{gameToy.toyCode}}</td>
                            <td style="vertical-align:middle">
                                <span ng-hide="gameToy.gameId!=1" class="label label-success label-mini" >{{gameToy.gameId|NumToStr7|translate}}</span>
                                <span ng-hide="gameToy.gameId==1" class="label label-default label-mini" >{{gameToy.gameId|NumToStr7|translate}}</span>
                            </td>
                            <td style="vertical-align:middle">
                                <span ng-hide="gameToy.siteId!=2" class="label label-mini" style="background-color: #769efa">{{gameToy.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="gameToy.siteId!=3" class="label label-mini" style="background-color: #6de90f">{{gameToy.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="gameToy.siteId!=4" class="label label-mini" style="background-color: #565acb">{{gameToy.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="gameToy.siteId!=5" class="label  label-mini" style="background-color: #2fd4cd">{{gameToy.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="gameToy.siteId!=6" class="label label-mini" style="background-color: #00b7ee">{{gameToy.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="gameToy.siteId!=7" class="label label-mini" style="background-color: #b33ac4">{{gameToy.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="gameToy.siteId!=8" class="label label-mini" style="background-color: #962928">{{gameToy.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="gameToy.siteId!=9" class="label label-mini" style="background-color: #adb327">{{gameToy.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="gameToy.siteId!=10" class="label label-mini" style="background-color: #af9312">{{gameToy.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="gameToy.siteId!=11" class="label label-mini" style="background-color: #a693af">{{gameToy.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="gameToy.siteId!=12" class="label  label-mini" style="background-color: #71af5c">{{gameToy.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="gameToy.siteId!=13" class="label label-mini" style="background-color: #acaf17">{{gameToy.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="gameToy.siteId!=14" class="label label-mini" style="background-color: #af6fad">{{gameToy.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="gameToy.siteId!=15" class="label label-mini" style="background-color: #6312af">{{gameToy.siteId|NumToStr9|translate}}</span>
                                <span ng-hide="gameToy.siteId==2 || gameToy.siteId==3 || gameToy.siteId==4 || gameToy.siteId==5 || gameToy.siteId==6 || gameToy.siteId==7 || gameToy.siteId==8 || gameToy.siteId==9 || gameToy.siteId==10 || gameToy.siteId==11 || gameToy.siteId==12 || gameToy.siteId==13 || gameToy.siteId==14 || gameToy.siteId==15" class="label label-default label-mini" >{{gameToy.siteId|NumToStr9|translate}}</span>
                            </td>
                            <td style="vertical-align:middle">{{gameToy.batchId}}</td>
                            <td style="vertical-align:middle">{{gameToy.createTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td style="vertical-align:middle">{{gameToy.prodTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td style="vertical-align:middle">{{gameToy.bindGame}}</td>
                            <td style="vertical-align:middle">{{gameToy.bindSite}}</td>
                            <td style="vertical-align:middle">{{gameToy.bindArea}}</td>
                            <td style="vertical-align:middle">
                                <span ng-hide="gameToy.bindStatus!=1" class="label label-info label-mini" translate="{{gameToy.bindStatus|NumToStr15}}"></span>
                                <span ng-hide="gameToy.bindStatus!=2" class="label label-warning label-mini" translate="{{gameToy.bindStatus|NumToStr15}}"></span>
                                <span ng-hide="gameToy.bindStatus!=3" class="label label-success label-mini" translate="{{gameToy.bindStatus|NumToStr15}}"></span>
                                <span ng-hide="gameToy.bindStatus!=4" class="label label-danger label-mini" translate="{{gameToy.bindStatus|NumToStr15}}"></span>
                            </td>
                            <td style="vertical-align:middle">{{gameToy.bindTime}}</td>
                            <%--<td><a ng-click="updateClicked()" style= "cursor:pointer;color: navy" ><span translate="load.uniTitle"></span><i class="fa fa-edit"></i></a>
                            </td>--%>
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