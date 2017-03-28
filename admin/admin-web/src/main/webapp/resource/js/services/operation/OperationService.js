/**
 * OperationService文件
 * author：钟亮
 * @type {module|*}
 */
/*
 物品申请Service
 */
adminApp.service('articleService', function () {

    /*
     添加信息
     对象：
     */
    this.articleAdd = function (article) {
        return $.post(ctx + '/operation/article/insert', article);
    }

    /*
     查询（分页）
     对象：
     指定页：pageIndex
     每页条数：pageSize
     */
    this.articleFindByName = function (article, pageIndex, pageSize) {
        return $.post(ctx + '/operation/article/pageList', {
            approvalStatus: article.approvalStatus,
            "mailType": article.mailType,
            "sendType": article.sendType,
            "sendStatus": article.sendStatus,
            "from": article.from,
            "to": article.to,
            "name": article.name,
            "typeCode": article.typeCode,
            "gameId": article.gameId,
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "sort": null,
            "order": null
        });
    }

    /*
     删除信息
     id：articleId
     */
    this.articleDelete = function (articleId) {
        return $.get(ctx + '/operation/article/articleDelete', {"articleIds": articleId});
    }

    /*
     一键申请信息
     id：articleId
     */
    this.articleApplyFor = function (articleId) {
        return $.get(ctx + '/operation/article/articleApplyFor', {"articleIds": articleId});
    }

    /*
     根据id查询article
     id：articleId
     */
    this.articleGetById = function (articleId) {
        return $.get(ctx + '/operation/article/getArticle/' + articleId);
    }

    /*
     根据id查询article
     id：articleId
     */
    this.approveGetById = function (approveId) {
        return $.get(ctx + '/operation/article/getApprove/' + approveId);
    }

    /*
     修改信息
     对象：
     */
    this.articleUpdate = function (article) {
        return $.post(ctx + '/operation/article/update', article);
    }

    /*
     提交审批
     对象：
     */
    this.approvalFromAdd = function (approval) {
        return $.post(ctx + '/operation/article/approvalAdd', approval);
    }

    /*
     提交审批
     对象：
     */
    this.importExcel = function (importExcel) {
        return $.post(ctx + '/operation/article/importExcel', importExcel);
    }

    /*
     查询物品申请、审批进度
     */
    this.itemDisposePlan = function (sendStatus, approvalStatus) {
        return $.post(ctx + '/operation/article/selectCountByMid', {
            sendStatus: sendStatus,
            approvalStatus: approvalStatus
        });
    }
});

/*
 玩具批量管理Service
 */
adminApp.service('batchFnService', function () {


    /*
     添加信息
     对象：batch
     */
    this.batchAdd = function (batch) {
        return $.post(ctx + '/operation/batch/insert', {
            "maxCode": batch.maxCode,
            "itemType": batch.itemType,
            "gameId": batch.gameId,
            "siteId": batch.siteId,
            "batchCode": batch.batchCode,
            "proTime": batch.proTime
        });
    }

    /*
     根据gameToy查询gameToy信息（分页）
     对象：batch
     指定页：pageIndex
     每页条数：pageSize
     */
    this.batchFindByName = function (batch, pageIndex, pageSize) {
        return $.post(ctx + '/operation/batch/pageList', {
            "from": batch.from,
            "to": batch.to,
            "toyType": batch.itemType,
            "batchCode": batch.batchCode,
            "gameId": batch.gameId,
            "siteId": batch.siteId,
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "sort": null,
            "order": null
        });
    }

});

/*
 玩具管理Service
 */
adminApp.service('gameToyFnService', function () {


    /*
     添加信息
     对象：gametoy
     */
    this.gameToyAdd = function (gametoy) {
        return $.post(ctx + '/operation/gameToy/insert', {
            "codeFrom": gametoy.codeFrom,
            "codeEnd": gametoy.codeEnd,
            "gameId": gametoy.gameId,
            "siteId": gametoy.siteId,
            "batchCode": gametoy.batchCode,
            "proTime": gametoy.proTime
        });
    }

    /*
     根据gameToy查询gameToy信息（分页）
     对象：gameToy
     指定页：pageIndex
     每页条数：pageSize
     */
    this.gameToyFindByName = function (gameToy, pageIndex, pageSize) {
        return $.post(ctx + '/operation/gameToy/pageList', {
            "from": gameToy.from,
            "to": gameToy.to,
            "batchCode": gameToy.batchCodeSearch,
            "gameId": gameToy.gameId,
            "siteId": gameToy.siteId,
            "bindStatus": gameToy.bindStatus,
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "sort": null,
            "order": null
        });
    }
    /*
     一键失效
     对象：gametoy
     */
    this.gameToyDisabled = function (gametoy) {
        return $.post(ctx + '/operation/gameToy/disabled', {"ids": gametoy.ids});
    }

});

/*
 游戏物品配置Service
 */
adminApp.service('gameToyTypeFnService', function () {


    /*
     添加信息
     对象：gametoy
     */
    this.gameToyTypeAdd = function (gameToyType) {
        return $.post(ctx + '/operation/gameToyType/addItemType', gameToyType);
    }

    /*
     根据gameToyType查询gameToyType信息（分页）
     对象：gameToy
     指定页：pageIndex
     每页条数：pageSize
     */
    this.gameToyTypeFindByName = function (gameToyType, pageIndex, pageSize) {
        if (gameToyType.gameId == "") {
            gameToyType.gameId = 0;
        }
        return $.post(ctx + '/operation/gameToyType/pageList', {
            name: gameToyType.name,
            gameId: gameToyType.gameId,
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "sort": null,
            "order": null
        });
    }

    /*
     item，用户选择习惯
     */
    this.itemHabitEdit = function (itemId, itemType) {
        return $.post(ctx + '/operation/gameToyType/habitEdit', {itemId: itemId, itemType: itemType});
    }

    /*
     根据道具名称、道具类型，查询gameToyType信息（分页）
     对象：gameToy
     指定页：pageIndex
     每页条数：pageSize
     */
    this.itemFindByItemType = function (gameToyType, pageIndex, pageSize) {
        return $.post(ctx + '/operation/gameToyType/pageListByType', {
            itemCode: gameToyType.itemCode,
            itemType: gameToyType.itemType,
            itemTypeName: gameToyType.itemTypeName,
            gameId: gameToyType.gameId,
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "sort": null,
            "order": null
        });
    }

    /*
     修改信息
     */
    this.gameToyTypeUpdate = function (gameToyType) {
        return $.post(ctx + '/operation/gameToyType/updateItemType', gameToyType);
    }
    /*
     删除信息
     */
    this.gameToyTypeDelete = function (gameToyType) {
        return $.post(ctx + '/operation/gameToyType/delItemType', {"itemTypeIds": gameToyType});
    }

    /*
     根据id查询信息
     权限id：
     */
    this.gameToyTypeGetByeId = function (gameToyTypeId) {
        return $.get(ctx + '/operation/gameToyType/selItemTypeById', {"id": gameToyTypeId});
    }

    /*
     根据typeId查询item
     权限id：
     */
    this.itemGetByType = function (typeId) {
        return $.get(ctx + '/operation/gameToyType/selItemTypeByType', {"type": typeId});
    }

    /*
     修改状态
     权限id：
     */
    this.stateUpdate = function (gameToyType) {
        return $.post(ctx + '/operation/gameToyType/updateState', gameToyType);
    }

    /*
     查询所有物品
     权限id：
     */
    this.itemGetByAll = function () {
        return $.get(ctx + '/operation/gameToyType/selItemTypeByAll');
    }
});

/*
 物品发送错误信息Service
 */
adminApp.service('mailOrderFailureFnService', function () {
    /*
     查询信息（分页）
     指定页：pageIndex
     每页条数：pageSize
     */
    this.mailOrderFailurePageList = function (mailOrderFailure, pageIndex, pageSize) {
        return $.post(ctx + '/operation/mailOrderFailure/pageList', {
            "mailTitle": mailOrderFailure.mailTitle,
            "to": mailOrderFailure.to,
            "from": mailOrderFailure.from,
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "sort": null,
            "order": null
        });
    };

    /**
     * 根据id查询对象
     * @param id
     * @returns {*}
     */
    this.mailOrderFailureById = function (id) {
        return $.post(ctx + '/operation/mailOrderFailure/getMailOrderFailure/' + id);
    }

    /*
     删除信息
     id：mailOrderFailureIds
     */
    this.mailOrderFailureDelete = function (mailOrderFailureIds) {
        return $.get(ctx + '/operation/mailOrderFailure/mailOrderFailureDelete', {"mailOrderFailureIds": mailOrderFailureIds});
    }


});

/*
 公告管理Service
 */
adminApp.service('noticeFnService', function () {

    /*
     添加信息
     对象：gametoy
     */
    this.noticeAdd = function (notice) {
        return $.post(ctx + '/operation/notice/insert', notice);
    }
    /*
     根据id查询信息
     权限id：
     */
    this.noticeGetById = function (noticeId) {
        return $.get(ctx + '/operation/notice/getNotice', {"noticeId": noticeId});
    }


    /*
     修改信息
     权限id：
     */
    this.noticeUpdate = function (notice) {
        return $.post(ctx + '/operation/notice/update', notice);
    }

    /*
     删除信息
     权限id：
     */
    this.noticeDelete = function (notice) {
        return $.post(ctx + '/operation/notice/delete', {"noticeIds": notice});
    }

    /*
     根据gameToy查询gameToy信息（分页）
     对象：gameToy
     指定页：pageIndex
     每页条数：pageSize
     */
    this.noticeFindByName = function (notice, pageIndex, pageSize) {
        if (notice.gameId == "") {
            notice.gameId = 1;
        }
        return $.post(ctx + '/operation/notice/pageList', {
            "noticeTitle": notice.noticeTitle,
            "from": notice.from,
            "to": notice.to,
            "gameId": notice.gameId,
            "siteId": notice.siteId,
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "sort": null,
            "order": null
        });
    }

    /*
     修改状态
     权限id：
     */
    this.noticeStateUpdate = function (notice) {
        return $.post(ctx + '/operation/notice/updateState', notice);
    }
});

/*
 活动开关管理Service
 */
adminApp.service('gameActivityFnService', function () {
    /*
     根据gameActivity查询gameActivity信息（分页）
     对象：gameActivity
     指定页：pageIndex
     每页条数：pageSize
     */
    this.gameActivityGetPage = function (gameActivity, pageIndex, pageSize) {
        if (gameActivity.gameId == "") {
            gameActivity.gameId = 1;
        }
        return $.post(ctx + '/operation/gameActivity/pageList', {
            "from": gameActivity.from,
            "to": gameActivity.to,
            "gameId": gameActivity.gameId,
            "siteId": gameActivity.siteId,
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "sort": null,
            "order": null
        });
    }

    /**
     * 根据id查询活动
     * @param activityId
     * @returns {*}
     */
    this.gameActivityGetById = function (activityId) {
        return $.post(ctx + '/operation/gameActivity/gameActivityGetById', {"activityId": activityId});
    }

    /**
     * 修改
     * @param activity
     * @returns {*}
     */
    this.gameActivityUpdate = function (activity) {
        return $.post(ctx + '/operation/gameActivity/gameActivityUpdate', activity);
    }

    /**
     * 添加
     * @param activity
     * @returns {*}
     */
    this.gameActivityAdd = function (activity) {
        return $.post(ctx + '/operation/gameActivity/gameActivityAdd', activity);
    }

    /**
     * 活动开关
     * @param activityState
     * @returns {*}
     */
    this.gameActivityUpdateState = function (activityState) {
        return $.post(ctx + '/operation/gameActivity/updateState', activityState);
    }
});

/*
 礼包码-批次管理Service
 */
adminApp.service('bagCodeBatchFnService', function () {

    /**
     * 添加
     * @param bagBatchData
     * @returns {*}
     */
    this.batchCodeAdd = function (bagBatchData) {
        return $.post(ctx + '/operation/bagCode/batchCodeAdd', bagBatchData);
    }

    /**
     * 修改
     * @param bagBatchData
     * @returns {*}
     */
    this.batchCodeUpdate = function (bagBatchData) {
        return $.post(ctx + '/operation/bagCode/batchCodeUpdate', bagBatchData);
    }

    /*
     删除信息
     */
    this.batchCodeDelete = function (ids) {
        return $.post(ctx + '/operation/bagCode/batchCodeDelete', {"ids": ids});
    }


    /**
     * 根据id查询
     * @param id
     * @returns {*}
     */
    this.batchCodeGetById = function (id) {
        return $.post(ctx + '/operation/bagCode/batchCodeGetById', {"id": id});
    }

    /**
     * 根据id查询
     * @param id
     * @returns {*}
     */
    this.getBatchCodeExcel = function (id) {
        return $.post(ctx + '/operation/bagCode/getBatchCodeExcel', {"id": id});
    }

    /**
     * 分页查询
     * @param bagCode
     * @param pageIndex
     * @param pageSize
     * @returns {*}
     */
    this.batchCodeGetPage = function (bagCode, pageIndex, pageSize) {
        if (bagCode.gameId == "") {
            bagCode.gameId = -1;
        }
        return $.post(ctx + '/operation/bagCode/codeBatchPageList', {
            "from": bagCode.from,
            "to": bagCode.to,
            "batchName": bagCode.batchName,
            "gameId": bagCode.gameId,
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "sort": null,
            "order": null
        });
    }
});

/*
 礼包码-批次礼包Service
 */
adminApp.service('batchGiftBagFnService', function () {

    /**
     * 添加
     * @param giftBagData
     * @returns {*}
     */
    this.giftBagAdd = function (giftBagData) {
        return $.post(ctx + '/operation/bagCode/giftBagAdd', giftBagData);
    }

    /**
     * 修改
     * @param giftBagData
     * @returns {*}
     */
    this.giftBagUpdate = function (giftBagData) {
        return $.post(ctx + '/operation/bagCode/giftBagUpdate', giftBagData);
    }

    /**
     * 分页查询
     * @param giftBag
     * @param pageIndex
     * @param pageSize
     * @returns {*}
     */
    this.gameGiftBagGetPage = function (giftBag, pageIndex, pageSize) {
        if (giftBag.gameId == "") {
            giftBag.gameId = -1;
        }
        return $.post(ctx + '/operation/bagCode/pageList', {
            "packageName": giftBag.packageName,
            "gameId": giftBag.gameId,
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "sort": null,
            "order": null
        });
    }

    /*
     删除信息
     */
    this.giftBagDelete = function (ids) {
        return $.post(ctx + '/operation/bagCode/giftBagDelete', {"ids": ids});
    }

    /**
     * 根据id查询
     * @param id
     * @returns {*}
     */
    this.giftBagGetById = function (id) {
        return $.post(ctx + '/operation/bagCode/gameGiftBagGetById', {"id": id});
    }

    /**
     * 查询礼包集合
     * @param giftBag
     * @returns {*}
     */
    this.giftBagList = function (giftBag) {
        if (giftBag.gameId == "") {
            giftBag.gameId = -1;
        }
        return $.post(ctx + "/operation/bagCode/getList", {gameId: giftBag.gameId});
    }
});


/*
 礼包码-礼包码查询Service
 */
adminApp.service('bagCodeQueryFnService', function () {

    /**
     * 分页查询
     * @param bagCodeSearch
     * @param pageIndex
     * @param pageSize
     * @returns {*}
     */
    this.batchCodeQueryPage = function (bagCodeSearch, pageIndex, pageSize) {
        if (bagCodeSearch.gameId == "") {
            bagCodeSearch.gameId = -1;
        }
        return $.post(ctx + '/operation/bagCode/getPromoteCodeList', {
            "codeName": bagCodeSearch.codeName,
            "gameId": bagCodeSearch.gameId,
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "sort": null,
            "order": null
        });
    }


});

