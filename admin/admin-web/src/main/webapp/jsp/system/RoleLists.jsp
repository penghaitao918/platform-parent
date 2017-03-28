<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--body wrapper start-->
<div>

    <div class="row">
        <div class="col-sm-12">
            <section class="panel">
                <div ng-controller="demoCtrl">

                    <div class="panel-body">
                        <div class="adv-table editable-table ">
                            <div class="clearfix">
                                <div class="btn-group">
                                    <button class="btn btn-success" ng-click="addButton()">
                                        <span translate="load.add"></span>&nbsp;<i class="fa fa-plus"></i>
                                    </button>

                                    <button id="" class="btn btn-danger" ng-click="delete()">
                                        <span translate="load.batchDelte"></span> <i class="fa fa-times"></i>
                                    </button>

                                    <a class="btn btn-info" ng-click="pocClose()"><span
                                            translate="load.OneKeyLock"></span>&nbsp<i class="fa fa-flag"></i></a>
                                    <a class="btn btn-info" ng-click="pocOpen()"><span
                                            translate="load.OneKeyUnlock"></span>&nbsp<i class="fa fa-flag"></i></a>
                                </div>

                                <div style="float: right;">
                                    <form class="searchform" method="post">
                                        <input type="text" class="form-control searchText" name="keyword"
                                               placeholder={{roleSearchText|translate}}  onkeydown="globelQuery(event)"/>
                                        <div style="float: left;margin-top: 7px;margin-left: 20px;">
                                            <button ng-click="searchButtonClicked()"
                                                    class="btn btn-primary searchButton"><span
                                                    translate="load.easyRetrieval"></span>&nbsp;<i
                                                    class="fa fa-search"></i></button>
                                        </div>

                                    </form>
                                </div>
                            </div>
                            <div class="space15"></div>

                            <table class="table">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th>#</th>
                                    <th hidden="hidden">id</th>
                                    <th><span translate="load.roleRoleName"></span></th>
                                    <th><span translate="load.roleCreateTime"></span></th>
                                    <th><span translate="load.roleDescription"></span></th>
                                    <th><span translate="load.roleAvailable"></span></th>
                                    <th><span translate="load.roleMemo"></span></th>
                                    <th><span translate="load.lockOperation"></span></th>
                                    <th><span translate="load.operation"></span></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr ng-repeat="role in roles">
                                    <td align="center"><input type="checkbox" class="checkbox"
                                                              data-ng-click="check($index,chk)" data-ng-model="chk"
                                                              value="" style="width:50px;"></td>
                                    <td style="vertical-align:middle">{{role.id}}</td>
                                    <td hidden="hidden">{{role.id}}</td>
                                    <td style="vertical-align:middle">
                                        {{role.role}}
                                    </td>
                                    <td style="vertical-align:middle">{{role.createTime|date:'yyyy-MM-dd HH:mm:ss'}}
                                    </td>
                                    <td style="vertical-align:middle">{{role.description}}</td>
                                    <td style="vertical-align:middle">
                                        <span ng-hide="role.available!=1" class="label label-success label-mini"
                                              translate="{{role.available|NumToStr}}"></span>
                                        <span ng-hide="role.available!=0" class="label label-danger label-mini"
                                              translate="{{role.available|NumToStr}}"></span>
                                    </td>
                                    <td style="vertical-align:middle">{{role.memo}}</td>
                                    <td style="vertical-align:middle"><a ng-click="open()" ng-hide="role.available==1"
                                                                         style="cursor:pointer;"><span
                                            translate="load.Unlock"></span><i class="fa fa-check"></i></a>
                                        <a ng-click="close()" ng-hide="role.available==0"
                                           style="cursor:pointer;color: red"><span translate="load.Lock"></span><i
                                                class="fa fa-times"></i></a></td>
                                    <td style="vertical-align:middle">
                                        <%--<div style="padding:3px;margin:0;float:left;box-sizing:border-box;"><button ng-click="updateClicked()" class="btn btn-primary">update</button></div>--%>
                                        <a ng-click="updateClicked()" style="cursor:pointer;"><span
                                                translate="load.update"></span><i class="fa fa-edit"></i></a>
                                        <a ng-click="accreditClicked()" style="cursor:pointer;"><span
                                                translate="load.accredit"></span><i class="fa fa-gavel"></i></a> 
                                        <!--   <div style="padding:3px;margin:0;float:left;box-sizing:border-box;"><button ng-click="deleteClicked()" class="btn btn-primary">delete</button></div>-->
                                    </td>
                                </tr>

                                </tbody>
                            </table>
                            <div class="pager">
                                <pager page-count="pageCount" current-page="currentPage" on-page-change="onPageChange()"
                                       first-text="load.HomePage" next-text="load.NextPage"
                                       prev-text="load.PreviousPage" last-text="load.EndPage" show-goto="true"
                                       goto-text="load.GoToPage"></pager>
                            </div>
                        </div>
                    </div>


                    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                         id="myModalForEdit" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×
                                    </button>
                                    <h4 class="modal-title"><span translate="load.add"></span></h4>
                                </div>
                                <div class="modal-body">

                                    <form role="form" ng-submit="roleEditForm()" name="roleEditForms">
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.roleRoleName"></span>:<span style="color: red">*</span></label>
                                            <input type="text" class="form-control role" name="role"
                                                   ng-model="roleEditData.role" required>
                                        </div>
                                        <div class="form-group labelSpace">
                                            <label><span translate="load.roleDescription"></span>:</label>
                                            <input type="text" class="form-control description" name="description"
                                                   ng-model="roleEditData.description">
                                        </div>

                                        <div class="form-group labelSpace">
                                            <label><span translate="load.roleMemo"></span>:</label>
                                                    <textarea class="form-control memo" name="memo"
                                                              style="height: 100px;" ng-model="roleEditData.memo"
                                                              optional>

                                                    </textarea>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                                    translate="load.Cancel"></span></button>
                                            <button type="submit" class="btn btn-primary addSubmit"><span
                                                    translate="load.Ok"></span></button>
                                            <p ng-hide="!roleEditForms.$invalid" style="color: red;margin-top: 15px;">
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
<!--body wrapper end-->

<!--footer section start-->

<!--footer section end-->


</div>
<!-- main content end-->
<script type="text/javascript">
    function globelQuery(e) {
        if (!e)
            e = window.event;
        if ((e.keyCode || e.which) == 13) {
            $(".searchButton").click();
        }
    }

</script>
