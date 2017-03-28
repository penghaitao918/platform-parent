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
<div ng-controller="editionListController">
    <div class="row">
        <%--<div class="col-sm-6">--%>
        <%--<section class="panel" style="margin-top: 20px;">--%>
        <%--<header class="panel-heading">--%>
        <%--<span translate="load.searchCriteria"></span>--%>
        <%--<span class="tools pull-right">--%>
        <%--<a href="javascript:;" class="fa fa-chevron-down" onclick="chevronClick(this)"></a>--%>
        <%--</span>--%>
        <%--</header>--%>
        <%--<div class="panel-body searchCriteria">--%>

        <%--<div style="float: left;margin-right: 20px;" class="labelSpace">--%>
        <%--<label><span translate="load.logOperation"></span></label>--%>
        <%--<select name="operation" aria-controls="editable-sample" class="form-control"--%>
        <%--ng-change="operationChange(syslogSerachData.operation)"--%>
        <%--ng-model="syslogSerachData.operation" style="float: left;">--%>
        <%--<option value="" translate="load.pleaseSelect"></option>--%>
        <%--<option value="添加" translate="load.add"></option>--%>
        <%--<option value="删除" translate="load.checkDelte"></option>--%>
        <%--<option value="修改" translate="load.update"></option>--%>
        <%--</select>--%>
        <%--</div>--%>
        <%--<div style="float: left;margin-top: 21px;">--%>
        <%--<button ng-click="searchButtonClicked()" class="btn btn-primary searchButton"><span--%>
        <%--translate="load.search"></span>&nbsp;<i class="fa fa-search"></i></button>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</section>--%>
        <%--</div>--%>
        <div class="col-sm-6">
            <section class="panel">

                <div class="panel-body" style="overflow:auto;">

                    <div class="clearfix">
                        <div class="btn-group">
                            <a ng-click="addButton()"
                               class="btn btn-success"><span translate="load.versionAdd"></span>&nbsp<i
                                    class="fa fa-plus"></i></a>
                            <a ng-click="deleteButton()"
                               class="btn btn-danger"><span translate="load.versionDelete"></span>&nbsp<i
                                    class="fa fa-times"></i></a>
                        </div>
                        <%--<div style="float: right;">--%>
                            <%--<form class="searchform" method="post">--%>
                                <%--<input type="text" class="form-control searchText" name="keyword"--%>
                                       <%--ng-model="syslogSerachData.loginName" placeholder="Search here..."--%>
                                       <%--onkeydown="globelQuery(event)"/>--%>
                                <%--<div style="float: left;margin-top: 7px;margin-left: 20px;">--%>
                                    <%--<button ng-click="searchButtonClicked()" class="btn btn-primary searchButton"><span--%>
                                            <%--translate="load.easyRetrieval"></span>&nbsp;<i class="fa fa-search"></i>--%>
                                    <%--</button>--%>
                                <%--</div>--%>

                            <%--</form>--%>
                        <%--</div>--%>
                    </div>


                    <section>

                        <table class="table table-striped table-condensed table-responsive">
                            <thead>
                            <tr>
                                <th></th>
                                <th><span translate="load.versionNumber"></span></th>
                                <th><span translate="load.versionPhoneSystem"></span></th>
                                <th><span translate="load.resGame"></span></th>
                                <th><span translate="load.resSite"></span></th>
                                <th><span translate="load.versionCode"></span></th>
                                <th><span translate="load.versionFileName"></span></th>
                                <th><span translate="load.versionSignificantCondition"></span></th>
                                <th><span translate="load.operation"></span></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="version in versionData">
                                <td align="center"><input type="checkbox" class="checkbox"
                                                          data-ng-click="check(version.id,chk)" data-ng-model="chk"
                                                          value="" style="width:50px;"></td>
                                <td style="vertical-align:middle">{{version.id}}</td>
                                <td style="vertical-align:middle">
                                    <span ng-hide="version.deviceType!=1" class="label label-success label-mini">{{version.deviceType|NumToStr18|translate}}</span>
                                    <span ng-hide="version.deviceType!=2" class="label label-info label-mini">{{version.deviceType|NumToStr18|translate}}</span></td>
                                <td style="vertical-align:middle">
                                    <span ng-hide="version.gameId!=1" class="label label-success label-mini">{{version.gameId|NumToStr7|translate}}</span>
                                    <span ng-hide="version.gameId==1" class="label label-default label-mini">{{version.gameId|NumToStr7|translate}}</span>
                                </td>

                                <td style="vertical-align:middle"><span ng-hide="version.siteId!=2"
                                                                        class="label label-mini"
                                                                        style="background-color: #769efa">{{version.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="version.siteId!=3" class="label label-mini"
                                          style="background-color: #6de90f">{{version.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="version.siteId!=4" class="label label-mini"
                                          style="background-color: #565acb">{{version.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="version.siteId!=5" class="label  label-mini"
                                          style="background-color: #2fd4cd">{{version.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="version.siteId!=6" class="label label-mini"
                                          style="background-color: #00b7ee">{{version.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="version.siteId!=7" class="label label-mini"
                                          style="background-color: #b33ac4">{{version.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="version.siteId!=8" class="label label-mini"
                                          style="background-color: #962928">{{version.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="version.siteId!=9" class="label label-mini"
                                          style="background-color: #adb327">{{version.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="version.siteId!=10" class="label label-mini"
                                          style="background-color: #af9312">{{version.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="version.siteId!=11" class="label label-mini"
                                          style="background-color: #a693af">{{version.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="version.siteId!=12" class="label  label-mini"
                                          style="background-color: #71af5c">{{version.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="version.siteId!=13" class="label label-mini"
                                          style="background-color: #acaf17">{{version.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="version.siteId!=14" class="label label-mini"
                                          style="background-color: #af6fad">{{version.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="version.siteId!=15" class="label label-mini"
                                          style="background-color: #6312af">{{version.siteId|NumToStr9|translate}}</span>
                                    <span ng-hide="version.siteId==2 || version.siteId==3 || version.siteId==4 || version.siteId==5 || version.siteId==6 || version.siteId==7 || version.siteId==8 || version.siteId==9 || version.siteId==10 || version.siteId==11 || version.siteId==12 || version.siteId==13 || version.siteId==14 || version.siteId==15"
                                          class="label label-default label-mini">{{version.siteId|NumToStr9|translate}}</span>
                                </td>
                                <td style="vertical-align:middle">{{version.patchVersion}}</td>
                                <td style="vertical-align:middle">{{version.patchName}}</td>
                                <td style="vertical-align:middle">
                                    <span ng-hide="version.validStatus!=1" class="label label-success label-mini">{{version.validStatus|NumToStr19|translate}}</span>
                                    <span ng-hide="version.validStatus!=2" class="label label-danger label-mini">{{version.validStatus|NumToStr19|translate}}</span>
                                    <span ng-hide="version.validStatus!=3" class="label label-default label-mini">{{version.validStatus|NumToStr19|translate}}</span>
                                </td>
                                <td style="vertical-align:middle"><a ng-click="updateClicked(version.id)"
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
                    </section>


                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                         id="logModalForView" class="modal fade">
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
                                                    <div class="title" translate="load.logUser"></div>
                                                    <div class="desk">{{syslog.loginName}}</div>
                                                </li>
                                                <li>
                                                    <div class="title" translate="load.logOperation"></div>
                                                    <div class="desk">{{syslog.operation}}</div>
                                                </li>
                                                <li>
                                                    <div class="title" translate="load.logContent"></div>
                                                    <div class="desk">{{syslog.content}}</div>
                                                </li>
                                                <li>
                                                    <div class="title" translate="load.logCreateTime"></div>
                                                    <div class="desk">{{syslog.createTime|date:'yyyy-MM-dd HH:mm:ss'}}
                                                    </div>
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