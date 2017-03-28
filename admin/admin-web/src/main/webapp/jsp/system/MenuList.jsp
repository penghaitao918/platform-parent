<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    .radioSpace {
        float: left;
        margin-left: 10px;
    }

    .radioNorms {
        width: 20px;
        height: 20px;
        margin-top: -4px;
    }
</style>
<div>

    <div class="row">
        <div class="col-sm-6">
            <section class="panel">
                <div class="panel-body" ng-controller="menuController">
                    <div class="clearfix">
                        <div class="btn-group">
                            <a ng-click="addButton()"
                               class="btn btn-success"><span translate="load.add"></span>&nbsp<i class="fa fa-plus"></i></a>
                            <a class="btn btn-danger" ng-click="delete()"><span translate="load.batchDelte"></span>&nbsp<i
                                    class="fa fa-times"></i></a>
                            <a class="btn btn-info" ng-click="pocOpen()"><span translate="load.OBT"></span>&nbsp<i
                                    class="fa fa-flag"></i></a>
                            <a class="btn btn-info" ng-click="pocClose()"><span translate="load.superEsc"></span>&nbsp<i
                                    class="fa fa-flag"></i></a>
                        </div>

                        <div style="float: right;">
                            <form class="searchform" method="post">
                                <input type="text" class="form-control searchText" ng-model="menuSearch.menuName"
                                       name="keyword"  placeholder={{menuSearchText|translate}} onkeydown="globelQuery(event)"/>
                                <div style="float: left;margin-top: 7px;margin-left: 20px;">
                                    <button ng-click="searchButtonClicked()" class="btn btn-primary searchButton"><span
                                            translate="load.easyRetrieval"></span>&nbsp;<i class="fa fa-search"></i>
                                    </button>
                                </div>

                            </form>
                        </div>
                    </div>


                    <table class="table table-striped table-condensed table-responsive">
                        <thead>
                        <tr>
                            <th></th>
                            <th>#</th>
                            <th><span translate="load.menuMenuName"></span></th>
                            <th><span translate="load.menuUrl"></span></th>
                            <th><span translate="load.menuParentId"></span></th>
                            <th><span translate="load.menuCreateTime"></span></th>
                            <th><span translate="load.menuAvailable"></span></th>
                            <th><span translate="load.menuShowOrder"></span></th>
                            <th><span translate="load.operation"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="menu in menus">
                            <td align="center"><input type="checkbox" class="checkbox"
                                                      data-ng-click="check($index,chk)" data-ng-model="chk" value=""
                                                      style="width:50px;"></td>
                            <td style="vertical-align:middle">{{menu.id}}</td>
                            <td hidden="hidden">{{menu.id}}</td>
                            <td style="vertical-align:middle">{{menu.menuName}}</td>
                            <td style="vertical-align:middle">{{menu.url}}</td>
                            <td style="vertical-align:middle">{{menu.parentId}}</td>
                            <td style="vertical-align:middle">{{menu.createTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td style="vertical-align:middle">
                                <span ng-hide="menu.available!=1" class="label label-success label-mini"
                                      translate="{{menu.available|NumToStr}}"></span>
                                <span ng-hide="menu.available!=0" class="label label-danger label-mini"
                                      translate="{{menu.available|NumToStr}}"></span>
                            </td>
                            <td style="vertical-align:middle">{{menu.showOrder}}</td>
                            <td style="vertical-align:middle"><a ng-click="updateClicked()"
                                                                 style="cursor:pointer;color: navy"><span
                                    translate="load.update"></span><i class="fa fa-edit"></i></a>
                                <a ng-click="open()" ng-hide="menu.available==1" style="cursor:pointer;"><span
                                        translate="load.Open"></span><i class="fa fa-check"></i></a>
                                <a ng-click="close()" ng-hide="menu.available==0"
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


                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                         id="menuModalForEdit" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.add"></span></h4>
                                </div>
                                <div class="modal-body">

                                    <form role="form" name="menuEditForms" ng-submit="menuEditForm()">
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.menuMenuName"></span>:<span style="color: red">*</span></label>
                                            <input type="text" class="form-control menuName" name="menuName"
                                                   ng-model="menuData.menuName" required>
                                        </div>
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.menuUrl"></span>:<span
                                                    style="color: red">*</span></label>
                                            <input type="text" class="form-control url" name="url"
                                                   ng-model="menuData.url" id="menuUrl" required pattern="[^\s]*"
                                                   ng-Blur="checkVerify('menuUrl','load.urlValidate')">
                                            <span ng-show="menuEditForms.url.$error.url" style="color: red;"><span
                                                    translate="load.urlValidate"></span></span>
                                        </div>

                                        <div class="form-group labelSpace">
                                            <label><span translate="load.menuParentId"></span>:<span style="color: red">*</span></label>
                                            <select name="parentId" aria-controls="editable-sample"
                                                    class="form-control parentId" ng-model="menuData.parentId" required>
                                                <option value="" translate="load.pleaseSelect"></option>
                                                <option value="0" translate="BasicSettings"></option>
                                                <option value="{{menu.id}}" ng-repeat="menu in menuParent"
                                                        translate="{{menu.menuName}}"></option>
                                            </select>
                                        </div>

                                        <div class="form-group labelSpace">
                                            <label><span translate="load.menuShowOrder"></span>:<span
                                                    style="color: red">*</span></label>
                                            <input type="number" class="form-control showOrder" min="0" max="1000000000"
                                                   name="showOrder" maxlength="10" ng-model="menuData.showOrder"
                                                   required>
                                        </div>

                                        <div class="form-group labelSpace">
                                            <label><span translate="load.menuIcon"></span>:<span
                                                    style="color: red">*</span></label>
                                            <div>
                                                <div class="minimal single-row radioSpace">
                                                    <div class="radio ">
                                                        <input type="radio"
                                                               style="width:20px;height:20px;margin-top: -4px;"
                                                               name="icon-radio" value="fa-cogs"
                                                               ng-model="menuData.menuIcon" required>
                                                        <label><i class="fa fa-cogs"></i></label>
                                                    </div>
                                                </div>
                                                <div class="minimal-red single-row radioSpace">
                                                    <div class="radio ">
                                                        <input type="radio" name="icon-radio"
                                                               style="width:20px;height:20px;margin-top: -4px;"
                                                               value="fa-laptop" ng-model="menuData.menuIcon">
                                                        <label><i class="fa fa-laptop"></i></label>
                                                    </div>
                                                </div>

                                                <div class="minimal-green single-row radioSpace">
                                                    <div class="radio ">
                                                        <input type="radio" name="icon-radio"
                                                               style="width:20px;height:20px;margin-top: -4px;"
                                                               value="fa-book" ng-model="menuData.menuIcon">
                                                        <label><i class="fa fa-book"></i></label>
                                                    </div>
                                                </div>

                                                <div class="minimal-blue single-row radioSpace">
                                                    <div class="radio ">
                                                        <input type="radio" name="icon-radio"
                                                               style="width:20px;height:20px;margin-top: -4px;"
                                                               value="fa-tasks" ng-model="menuData.menuIcon">
                                                        <label><i class="fa fa-tasks"></i></label>
                                                    </div>
                                                </div>

                                                <div class="minimal-yellow single-row radioSpace">
                                                    <div class="radio ">
                                                        <input type="radio" name="icon-radio"
                                                               style="width:20px;height:20px;margin-top: -4px;"
                                                               value="fa--bar-chart-o" ng-model="menuData.menuIcon">
                                                        <label><i class="fa fa-bar-chart-o"></i></label>
                                                    </div>
                                                </div>

                                                <div class="minimal-purple single-row radioSpace">
                                                    <div class="radio ">
                                                        <input type="radio" name="icon-radio"
                                                               style="width:20px;height:20px;margin-top: -4px;"
                                                               value="fa-random" ng-model="menuData.menuIcon">
                                                        <label><i class="fa fa-random"></i></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="modal-footer" style="margin-top: 50px;">
                                            <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                                    translate="load.Cancel"></span></button>
                                            <button type="submit" class="btn btn-primary addSubmit"><span
                                                    translate="load.Ok"></span></button>
                                            <p ng-hide="!menuEditForms.$invalid" style="color: red;margin-top: 15px;">
                                                <span translate="load.remindValidate"></span></p>
                                        </div>
                                    </form>
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