var TreeView = function () {

    return {
        //main function to initiate the module
        init: function (data, editData) {

            var DataSourceTree = function (options) {
                this._data = options.data;
            };
            var cont = 0;
            DataSourceTree.prototype = {

                data: function (options, callback) {
                    var self = this;
                    if (options.search) {
                        callback({data: 0, start: 0, end: 0, count: 0, pages: 0, page: 0});
                    }
                    else if (options.data) {
                        callback({data: options.data, start: 0, end: 0, count: 0, pages: 0, page: 0});
                    }
                    else if (cont == 0) {
                        callback({data: self._data, start: 0, end: 0, count: 0, pages: 0, page: 0});
                    }
                    else {
                        callback({data: 0, start: 0, end: 0, count: 0, pages: 0, page: 0});
                    }
                    cont = cont + 1;
                }
            };

            var datas = "";//初始化数据
            /*
             从url中获取参数
             */
            var urls = window.location.href.split("/");
            var userId = urls[urls.length - 2];
            var typeId = urls[urls.length - 1];
            /*
             数据拼接，绑定
             */
            /*  $.get(ctx + '/system/menu/getMenu', function (data) {*/

            var data2 = [];
            for (var items in data.childrenList) {
                var itemData = {additionalParameters: {}};
                data2.push(itemData);
                /*
                 ajax获取后台数据
                 */
                $.ajax({
                    url: ctx + "/system/relation/parentMenuIsAll",
                    data: {userRoleId: userId, typeId: typeId, menuParentId: data.childrenList[items].id},
                    success: function (itemData) {
                        console.log(itemData)
                        if (itemData) {
                            data2[items].name = data.childrenList[items].menuName + "&nbsp; <input type='checkbox' disabled='true' checked='checked' class='checkbox checkDis menuCheck" + data.childrenList[items].id + "' value='" + data.childrenList[items].id + "' style='width: 15px;float: left;margin-top: 1px;' >";
                        } else {
                            data2[items].name = data.childrenList[items].menuName + "&nbsp; <input type='checkbox' disabled='true' class='checkbox checkDis menuCheck" + data.childrenList[items].id + "' value='" + data.childrenList[items].id + "' style='width: 15px;float: left;margin-top: 1px;' >";
                        }

                    },
                    async: false
                }).responseText;


                data2[items].type = "folder";
                data2[items].additionalParameters.id = data.childrenList[items].id;
                data2[items].data = [];
                /*
                 绑定子菜单项
                 */
                for (var itm in data.childrenList[items].childrenList) {
                    var itmData = {additionalParameters: {}};
                    data2[items].data.push(itmData);
                    var hint = "";
                    /*  data2[items].data[itm].name = data.childrenList[items].childrenList[itm].menuName;*/
                    data2[items].data[itm].type = "item";
                    data2[items].data[itm].additionalParameters.id = data.childrenList[items].childrenList[itm].id;
                    data2[items].data[itm].data = [];
                    $.ajax({
                        url: ctx + "/system/relation/theRelationById",
                        data: {userId: userId, type: typeId, menuId: data.childrenList[items].childrenList[itm].id},
                        success: function (itemData) {
                            hint = "<div class='munuDataItems" + data.childrenList[items].childrenList[itm].id + "'>";
                            for (var i = 0; i < itemData.length; i++) {

                                if (itemData[i] == 1) {
                                    hint += " " + editData.checkAdd;
                                }
                                if (itemData[i] == 2) {
                                    hint += " " + editData.checkDelte;
                                }
                                if (itemData[i] == 3) {
                                    hint += " " + editData.checkUpdate;
                                }
                                if (itemData[i] == 4) {
                                    hint += " " + editData.checkSelect;
                                }


                            }
                        },
                        async: false
                    }).responseText;
                    /*   $.post("/admin-web/system/relation/relationById",{userId:userId,type:typeId,menuId:data.childrenList[items].childrenList[itm].id},function(itemData){

                     });*/
                    hint += "</div>";
                    /*data2[items].data[itm].name = data.childrenList[items].childrenList[itm].menuName+"&nbsp; <input type='checkbox' class='checkbox menuCheck"+data.childrenList[items].childrenList[itm].id+"' value='"+data.childrenList[items].childrenList[itm].id+"' style='width: 15px;float: left;margin-top: 1px;' >"+hint;*/
                    data2[items].data[itm].name = data.childrenList[items].childrenList[itm].menuName + "&nbsp; <input type='checkbox'   class='checkbox  menuCheckChild" + data.childrenList[items].childrenList[itm].id + "' value='" + data.childrenList[items].childrenList[itm].id + "' style='width: 15px;float: left;margin-top: 1px;' >" + hint;
                    hint = "";

                }
            }
            datas = data2;

            /*
             绑定树
             */
            var treeDataSource = new DataSourceTree({
                data: datas
            });

            $('#MyTree').tree({
                dataSource: treeDataSource,
                multiSelect: false,
                loadingHTML: '<div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>',
                'open-icon': 'ace-icon tree-minus',
                'close-icon': 'ace-icon tree-plus',
                'selectable': false,
                'selected-icon': 'ace-icon fa fa-check',
                'unselected-icon': 'ace-icon fa fa-times',
                cacheItems: true,
                folderSelect: false
            });

            /*         });*/


            /*
             初始化参数
             */
            var treeId = 0;
            var menuIdBytree = 0;
            var menuItems = {};
            /*
             绑定子菜单项的单击事件
             */
            $('#MyTree').on('selected', function (evt, data) {
                treeId = data.info[0].additionalParameters.id;
                $(".checkbox.op").attr('checked', false);
                /*
                 获取数据，绑定checkbox
                 */
                $.post(ctx + "/system/relation/theRelationById", {
                    userId: userId,
                    type: typeId,
                    menuId: treeId
                }, function (data) {
                    for (var i = 0; i < data.length; i++) {
                        if (data[i] == 1) {
                            $(".addCheck").attr('checked', true);
                        }
                        if (data[i] == 2) {
                            $(".deleteCheck").attr('checked', true);
                        }
                        if (data[i] == 3) {
                            $(".updateCheck").attr('checked', true);
                        }
                        if (data[i] == 4) {
                            $(".selectCheck").attr('checked', true);
                        }


                    }

                });
                $(".accreditChecks").show();
                $(".menuCheckChild" + treeId).click(function () {

                    service(userId, typeId, treeId, $(".addCheck").val(), this.checked);
                    service(userId, typeId, treeId, $(".deleteCheck").val(), this.checked);
                    service(userId, typeId, treeId, $(".updateCheck").val(), this.checked);
                    service(userId, typeId, treeId, $(".selectCheck").val(), this.checked);

                    if (this.checked) {
                        $(".munuDataItems" + treeId).text($(treeId).text() + " " + editData.checkAdd + " " + editData.checkDelte + " " + editData.checkUpdate + " " + editData.checkSelect);
                    } else {
                        $(".munuDataItems" + treeId).text($(treeId).text() + "");
                    }
                });


            });


            /*
             checkbox绑定事件，并实时查询数据动态，动态改变tree
             */
            $(".addCheck").click(function () {
                service(userId, typeId, treeId, $(".addCheck").val(), this.checked);
                $.ajax({
                    url: ctx + "/system/relation/parentMenuIsAll",
                    data: {userRoleId: userId, typeId: typeId, menuParentId: menuIdBytree},
                    success: function (itemData) {
                        console.log(itemData)
                        if (itemData) {
                            $(".menuCheck" + menuIdBytree).attr("checked", true);
                        } else {
                            $(".menuCheck" + menuIdBytree).attr("checked", false);
                        }

                    },
                    async: false
                }).responseText;


                if ($(".addCheck").attr("checked") == "checked" && $(".deleteCheck").attr("checked") == "checked" && $(".updateCheck").attr("checked") == "checked" && $(".selectCheck").attr("checked") == "checked") {
                    $(".menuCheckChild" + treeId).attr("checked", true);
                } else {
                    $(".menuCheckChild" + treeId).attr("checked", false);
                }

                if (this.checked) {
                    $(".munuDataItems" + treeId).text($(".munuDataItems" + treeId).text() + " " + editData.checkAdd);
                } else {
                    $(".munuDataItems" + treeId).text($(".munuDataItems" + treeId).text().replace(" " + editData.checkAdd, ""));
                    $(".munuDataItems" + treeId).text($(".munuDataItems" + treeId).text().replace("" + editData.checkAdd, ""));
                }
            });
            $(".deleteCheck").click(function () {
                service(userId, typeId, treeId, $(".deleteCheck").val(), this.checked);
                $.ajax({
                    url: ctx + "/system/relation/parentMenuIsAll",
                    data: {userRoleId: userId, typeId: typeId, menuParentId: menuIdBytree},
                    success: function (itemData) {
                        console.log(itemData)
                        if (itemData) {
                            $(".menuCheck" + menuIdBytree).attr("checked", true);
                        } else {
                            $(".menuCheck" + menuIdBytree).attr("checked", false);
                        }

                    },
                    async: false
                }).responseText;

                if ($(".addCheck").attr("checked") == "checked" && $(".deleteCheck").attr("checked") == "checked" && $(".updateCheck").attr("checked") == "checked" && $(".selectCheck").attr("checked") == "checked") {
                    $(".menuCheckChild" + treeId).attr("checked", true);
                } else {
                    $(".menuCheckChild" + treeId).attr("checked", false);
                }
                if (this.checked) {
                    $(".munuDataItems" + treeId).text($(".munuDataItems" + treeId).text() + " " + editData.checkDelte);
                } else {
                    $(".munuDataItems" + treeId).text($(".munuDataItems" + treeId).text().replace(" " + editData.checkDelte, ""));
                    $(".munuDataItems" + treeId).text($(".munuDataItems" + treeId).text().replace("" + editData.checkDelte, ""));
                }
            });
            $(".updateCheck").click(function () {
                service(userId, typeId, treeId, $(".updateCheck").val(), this.checked);
                $.ajax({
                    url: ctx + "/system/relation/parentMenuIsAll",
                    data: {userRoleId: userId, typeId: typeId, menuParentId: menuIdBytree},
                    success: function (itemData) {
                        console.log(itemData)
                        if (itemData) {
                            $(".menuCheck" + menuIdBytree).attr("checked", true);
                        } else {
                            $(".menuCheck" + menuIdBytree).attr("checked", false);
                        }

                    },
                    async: false
                }).responseText;
                if ($(".addCheck").attr("checked") == "checked" && $(".deleteCheck").attr("checked") == "checked" && $(".updateCheck").attr("checked") == "checked" && $(".selectCheck").attr("checked") == "checked") {
                    $(".menuCheckChild" + treeId).attr("checked", true);
                } else {
                    $(".menuCheckChild" + treeId).attr("checked", false);
                }
                if (this.checked) {
                    console.log($(".munuDataItems" + treeId).text());
                    $(".munuDataItems" + treeId).text($(".munuDataItems" + treeId).text() + " " + editData.checkUpdate);
                } else {
                    $(".munuDataItems" + treeId).text($(".munuDataItems" + treeId).text().replace(" " + editData.checkUpdate, ""));
                    $(".munuDataItems" + treeId).text($(".munuDataItems" + treeId).text().replace("" + editData.checkUpdate, ""));
                }
            });
            $(".selectCheck").click(function () {
                service(userId, typeId, treeId, $(".selectCheck").val(), this.checked);
                $.ajax({
                    url: ctx + "/system/relation/parentMenuIsAll",
                    data: {userRoleId: userId, typeId: typeId, menuParentId: menuIdBytree},
                    success: function (itemData) {
                        console.log(itemData)
                        if (itemData) {
                            $(".menuCheck" + menuIdBytree).attr("checked", true);
                        } else {
                            $(".menuCheck" + menuIdBytree).attr("checked", false);
                        }

                    },
                    async: false
                }).responseText;
                if ($(".addCheck").attr("checked") == "checked" && $(".deleteCheck").attr("checked") == "checked" && $(".updateCheck").attr("checked") == "checked" && $(".selectCheck").attr("checked") == "checked") {
                    $(".menuCheckChild" + treeId).attr("checked", true);
                } else {
                    $(".menuCheckChild" + treeId).attr("checked", false);
                }
                if (this.checked) {
                    console.log($(".munuDataItems" + treeId).text());
                    $(".munuDataItems" + treeId).text($(".munuDataItems" + treeId).text() + " " + editData.checkSelect);
                } else {
                    $(".munuDataItems" + treeId).text($(".munuDataItems" + treeId).text().replace(" " + editData.checkSelect, ""));
                    $(".munuDataItems" + treeId).text($(".munuDataItems" + treeId).text().replace("" + editData.checkSelect, ""));
                }
            });
            function service(userId, type, menuId, opId, judge) {
                $.post(ctx + '/system/relation/appendRelation', {
                    userId: userId,
                    type: type,
                    menuId: menuId,
                    opId: opId,
                    judge: judge
                }, function () {

                })
            };

            /*
             父菜单打开的单击事件
             */
            $('#MyTree').on('opened', function (evt, data) {
                $(".checkDis").attr("disabled", true);

                var newData = [];
                newData = data;
                menuIdBytree = data.additionalParameters.id;
                $(".menuCheck" + menuIdBytree).attr("disabled", false);
                $(".accreditChecks").hide();
                /* if($(".menuCheck"+menuIdBytree).attr("checked")=="checked"){
                 $(".menuCheck"+menuIdBytree).trigger("click");
                 $(".menuCheck"+menuIdBytree).trigger("click");
                 }else {
                 $(".menuCheck"+menuIdBytree).trigger("click");
                 $(".menuCheck"+menuIdBytree).trigger("click");
                 }*/
                /*
                 父菜单checkbox与tree动态绑定，
                 */
                $(".menuCheck" + menuIdBytree).click(function ($event) {
                    console.log(111)
                    $(".menuCheck" + menuIdBytree).attr("checked", this.checked);
                    if (this.checked) {
                        $.post(ctx + '/system/relation/appendAllRelation', {
                            userRoleId: userId,
                            typeId: typeId,
                            menuParentId: menuIdBytree,
                            flag: true
                        }, function () {

                        })
                        for (var i = 0; i < data.data.length; i++) {
                            $(".munuDataItems" + data.data[i].additionalParameters.id).text($(data.data[i].additionalParameters.id).text() + " " + editData.checkAdd + " " + editData.checkDelte + " " + editData.checkUpdate + " " + editData.checkSelect);
                            $(".menuCheck" + data.data[i].additionalParameters.id).attr("checked", true);
                        }
                    } else {
                        $.post(ctx + '/system/relation/appendAllRelation', {
                            userRoleId: userId,
                            typeId: typeId,
                            menuParentId: menuIdBytree,
                            flag: false
                        }, function () {

                        })
                        for (var i = 0; i < data.data.length; i++) {
                            $(".munuDataItems" + data.data[i].additionalParameters.id).text($(data.data[i].additionalParameters.id).text() + "");
                            $(".menuCheck" + data.data[i].additionalParameters.id).attr("checked", false);
                        }
                    }
                    /*for(var i=0;i<data.data.length;i++){
                     $(".menuCheck"+data.data[i].additionalParameters.id).change(function($event){
                     var thePd=true;
                     for(var j=0;j<newData.data.length;j++){
                     if($(".menuCheck"+newData.data[j].additionalParameters.id).attr("checked")!="checked"){
                     thePd=false;
                     }
                     }

                     if(thePd){
                     $(".menuCheck"+menuIdBytree).attr("checked",true);
                     }else{
                     $(".menuCheck"+menuIdBytree).attr("checked",false);
                     }

                     });
                     }*/
                    $(".checkbox.op").attr('checked', false);

                    $(".accreditChecks").hide();
                    $event.stopPropagation();
                });
            });


            $('#MyTree').on('closed', function (evt, data) {
                $(".accreditChecks").hide();
            });


        }
    };

}();