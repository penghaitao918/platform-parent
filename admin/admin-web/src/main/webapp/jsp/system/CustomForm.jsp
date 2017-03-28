
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div  >
    <div class="row">
        <div class="col-sm-6">
            <section class="panel">
                <div class="panel-body" ng-controller="customFormController">
                    <div class="clearfix">
                        <div class="btn-group">
                            <a class="btn btn-success" ng-click="addButton()"><span translate="load.add"></span>&nbsp<i class="fa fa-plus"></i></a>
                        </div>
                        <div style="float: right;">
                        </div>
                    </div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th></th>
                            <th>#</th>
                            <th><span translate="load.customFormName"></span></th>
                            <th><span translate="load.customFormCode"></span></th>
                            <th><span translate="load.itemItemType"></span></th>
                            <th><span translate="load.GameId"></span></th>
                            <th><span translate="load.createTime"></span></th>
                            <th><span translate="load.operation"></span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="customForm in customFormList">
                            <td></td>
                            <td>{{$index + 1}}</td>
                            <td>{{customForm.formName}}</td>
                            <td>{{customForm.formCode}}</td>
                            <td>{{customForm.itemType}}</td>
                            <td>{{customForm.gameId}}</td>
                            <td>{{customForm.createTime|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td>
                                <a ng-click="updateClicked()" style= "cursor:pointer;color: navy" ><span translate="load.update"></span><i class="fa fa-edit"></i></a>
                                <a ng-click="deleteClicked()" style= "cursor:pointer;color: red" ><span translate="load.Delte"></span><i class="fa fa-times"></i></a>
                            </td>
                        </tr>

                        </tbody>
                    </table>



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