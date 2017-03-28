package com.platform.serv.game.service;

import com.fanfandou.common.entity.resource.DicItem;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.billing.entity.GoodsItem;
import com.fanfandou.platform.api.billing.entity.GoodsItemPackage;
import com.fanfandou.platform.api.game.entity.OperationType;
import com.fanfandou.platform.api.game.service.OperationDispatchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description: 测试物品发送流程.
 * <p/>
 * Date: 2016-08-24 14:14.
 * author: Arvin.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/*.xml")
public class PackageDispatchTest {

    @Autowired
    private OperationDispatchService operationDispatchService;

    @Test
    public void testDispatch() throws ServiceException {
        GoodsItemPackage goodsItemPackage = new GoodsItemPackage();
        goodsItemPackage.setAwardPackageId("1");
        goodsItemPackage.setPackageDesc("测试desc");
        goodsItemPackage.setPackageType(1);
        goodsItemPackage.setValue(10);
        GoodsItem item1 = new GoodsItem();
        item1.setValue(10);
        item1.setItemId(1001);
        item1.setItemName("物品1");
        item1.setItemType(new DicItem());
        GoodsItem item2 = new GoodsItem();
        item2.setValue(20);
        item2.setItemId(1002);
        item2.setItemName("物品2");
        item2.setItemType(new DicItem());
        goodsItemPackage.getGoodsItems().add(item1);
        goodsItemPackage.getGoodsItems().add(item2);

        //operationDispatchService.sendPackage(GameCode.getById(1), 1,goodsItemPackage,1,1, OperationType.SEND_ITEM);
    }
}
