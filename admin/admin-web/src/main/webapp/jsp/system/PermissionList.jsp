<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="wrapper">
    <div class="row">
        <div class="col-sm-6">
            <section class="panel">
                <header class="panel-heading">
                    权限管理
        <span class="tools pull-right">
           <a href="javascript:;" class="fa fa-chevron-down"></a>
           <a href="javascript:;" class="fa fa-times"></a>
        </span>
                </header>
                <div class="panel-body" ng-controller="permissionController">
                    <div class="clearfix">
                        <div class="btn-group">
                            <a  ng-click="addButton()"
                               class="btn btn-success">添加&nbsp<i class="fa fa-plus"></i></a>
                            <a class="btn btn-danger" ng-click="delete()">批量删除&nbsp<i class="fa fa-times"></i></a>
                        </div>

                        <div style="float: right;">
                            <form class="searchform"  method="post">
                                <input type="text" class="form-control searchText" name="keyword" placeholder="Search here..." onkeydown="globelQuery(event)"/>
                                <div style="float: left;margin-top: 7px;">
                                    <button ng-click="searchButtonClicked()" class="btn btn-primary searchButton" >搜索</button>
                                </div>

                            </form>
                        </div>
                    </div>


                    <table class="table">
                        <thead>
                        <tr>
                            <th></th>
                            <th>#</th>
                            <th>resourceId</th>
                            <th>actionId</th>
                            <th>resourceType</th>
                            <th>permissionValue</th>
                            <th>createTime</th>
                            <th>available</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="permission in permissions">
                            <td align="center"><input type="checkbox" class="checkbox"
                                                      data-ng-click="check($index,chk)"data-ng-model="chk" value="" style="width:50px;"></td>
                            <td>{{$index + 1}}</td>
                            <td hidden="hidden">{{permission.id}}</td>
                            <td>{{permission.resourceId}}</td>
                            <td>{{permission.actionId}}</td>
                            <td>{{permission.type}}</td>
                            <td>{{permission.permissionValue}}</td>
                            <td>{{permission.createTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td>{{permission.available}}</td>
                            <td><a ng-click="updateClicked()" style= "cursor:pointer;color: navy" >修改<i class="fa fa-edit"></i></a>
                               </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="pager">
                        <pager page-count="pageCount" current-page="currentPage" on-page-change="onPageChange()" first-text="首页" next-text="下一页" prev-text="上一页" last-text="尾页" show-goto="true" goto-text="跳转到"></pager>
                    </div>


                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="permissionModalForAdd" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                    <h4 class="modal-title">添加</h4>
                                </div>
                                <div class="modal-body">

                                    <form role="form" ng-submit="permissionAddForm()">
                                        <div class="form-group">
                                            <label >resourceId:</label>
                                            <input type="number"  class="form-control resourceId" min="0" max="100000000000" name="resourceId" placeholder="Enter resourceId" ng-model="permissionAddData.resourceId" id="resourceId"  pattern="^\-?\d*$" ng-keyup="checkVerify('resourceId','格式不正确')" required>
                                        </div>
                                        <div class="form-group">
                                            <label>actionId:</label>
                                            <input type="text" class="form-control actionId" name="actionId" placeholder="Enter actionId" ng-model="permissionAddData.actionId"  id="actionId" pattern="^\-?\d*$"  ng-keyup="checkVerify('actionId','load.add')" required  >
                                        </div>
                                        <div class="form-group">
                                            <label>resourceType:</label>
                                            <input type="text" class="form-control resourceType" name="type" placeholder="Enter type" ng-model="permissionAddData.type" required>
                                        </div>

                                        <div class="form-group">
                                            <label>permissionValue:</label>
                                            <input type="text" class="form-control permissionValue" name="permissionValue" placeholder="Enter permissionValue" ng-model="permissionAddData.permissionValue" required>
                                        </div>
                                        <div class="form-group">
                                            <label>available:</label>
                                            <input type="text" class="form-control available" name="available" placeholder="Enter available" ng-model="permissionAddData.available" required>
                                        </div>

                                        <button type="submit" class="btn btn-primary addSubmit"  >添加</button>


                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="permissionModalForUpdate" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                    <h4 class="modal-title">修改</h4>
                                </div>
                                <div class="modal-body">

                                    <form role="form" ng-submit="permissionUpdateForm()">
                                        <input type="hidden"  class="form-control permissionIdUpdate" name="id" ng-model="permissionUpdateData.id" >
                                        <div class="form-group">
                                            <label >resourceId:</label>
                                            <input type="text"  class="form-control resourceIdUpdate" name="resourceId" placeholder="Enter resourceId" ng-model="permissionUpdateData.resourceId">
                                        </div>
                                        <div class="form-group">
                                            <label>actionId:</label>
                                            <input type="text" class="form-control actionIdUpdate" name="actionId" placeholder="Enter actionId" ng-model="permissionUpdateData.actionId">
                                        </div>
                                        <div class="form-group">
                                            <label>resourceType:</label>
                                            <input type="text" class="form-control resourceTypeUpdate" name="resourceType" placeholder="Enter type" ng-model="permissionUpdateData.type" >
                                        </div>

                                        <div class="form-group">
                                            <label>permissionValue:</label>
                                            <input type="text" class="form-control permissionValueUpdate" name="permissionValue" placeholder="Enter permissionValue" ng-model="permissionUpdateData.permissionValue">
                                        </div>
                                        <div class="form-group">
                                            <label>available:</label>
                                            <input type="text" class="form-control availableUpdate" name="available" placeholder="Enter available" ng-model="permissionUpdateData.available">
                                        </div>

                                        <button type="submit" class="btn btn-primary updateSubmit" >修改</button>


                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div aria-hidden="true"  role="dialog" tabindex="-1" id="messagesModal" class="modal fade"  >
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header" >
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                    <h4 class="modal-title">{{messagesData.messagesTitle}}</h4>
                                </div>
                                <div class="modal-body">
                                    {{messagesData.messagesBody}}
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" data-dismiss="modal"> Ok</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div aria-hidden="true"  role="dialog" tabindex="-1" id="messagesConfirm" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                    <h4 class="modal-title">{{messagesConfirm.title}}</h4>
                                </div>
                                <div class="modal-body">

                                    {{messagesConfirm.body}}

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                    <button type="button" class="btn btn-warning" data-dismiss="modal" ng-click="confirm()"> 确认</button>
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