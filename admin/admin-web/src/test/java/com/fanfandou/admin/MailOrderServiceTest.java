package com.fanfandou.admin;

import com.fanfandou.admin.operation.entity.Approve;
import com.fanfandou.admin.operation.entity.MailOrder;
import com.fanfandou.admin.operation.service.ApproveService;
import com.fanfandou.admin.operation.service.MailOrderService;
import com.fanfandou.platform.api.billing.entity.GoodsItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzhenwei on 2016/7/27.
 * Description test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/*.xml")
public class MailOrderServiceTest {

    @Autowired
    private MailOrderService mailOrderService;

    @Autowired
    private ApproveService approveService;

    @Test
    public void testAdd() {
        MailOrder mailOrder = new MailOrder();
        mailOrder.setGameId(1);
        mailOrder.setAreaIds(",1,2,3,");
        mailOrder.setSendByValue(",1,2,3,");
        mailOrder.setSendType(1);
        mailOrder.setSendByType(1);
        mailOrder.setSendStatus(1);
        mailOrder.setMailType(1);
        mailOrder.setMailTitle("test3");
        mailOrder.setMailContent("testtesttest");
        mailOrder.setApplyReason("test111");

        GoodsItem goodsItem1 = new GoodsItem();
        goodsItem1.setItemId(1);
        goodsItem1.setValue(111);
       /* goodsItem1.setItemType(121);*/

        GoodsItem goodsItem2 = new GoodsItem();
        goodsItem2.setItemId(1);
        goodsItem2.setValue(111);
     /*   goodsItem2.setItemType(121);*/

        List<GoodsItem> list = new ArrayList<>();
        list.add(goodsItem1);
        list.add(goodsItem2);

        String itemJson = mailOrderService.setGoodsItemJson(list);
        mailOrder.setItemJson(itemJson);
        try {
            mailOrderService.addMailOrder(mailOrder);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testDel(){
        int id1 = 19;
        int id2 = 20;
        List<Integer> list = new ArrayList<>();
        list.add(id1);
        list.add(id2);
       /* mailOrderService.delMailOrder(list);*/
    }

    @Test
    public void testSubmit(){
        int mailId = 21;
        mailOrderService.submitOrder(21);
    }

    @Test
    public void testApprove(){
        int mailId = 17;
        int state = 3;
        String approvalContent = "试试看";
        mailOrderService.approvalOrder(mailId,state,approvalContent);
    }

    @Test
    public void testSelApprove(){
        List<Approve> list = approveService.selectAll();
        for(Approve approve:list){
            System.out.println(approve);
        }
    }

    @Test
    public void testSelMailOrder(){
        mailOrderService.selectAll();
    }
}
