package com.fanfandou.admin.system.test.operation.mailApprove;

import com.fanfandou.admin.operation.entity.MailOrder;
import com.fanfandou.admin.operation.service.impl.ApproveServiceImpl;
import com.fanfandou.admin.operation.service.impl.MailOrderServiceImpl;

/**
 * Created by wangzhenwei on 2016/7/18.
 * Description test.
 */
public class TestService {

    public static void main(String[] args){
        MailOrderServiceImpl mailOrderService = new MailOrderServiceImpl();
        ApproveServiceImpl approveService = new ApproveServiceImpl();

        //添加
        MailOrder mailOrder = new MailOrder();
        mailOrder.setGameId(1);
        mailOrder.setAreaIds(",1,2,3,4,5");
        mailOrder.setMailTitle("test1");
        mailOrder.setMailType(1);

        mailOrderService.addMailOrder(mailOrder);
        System.out.println("添加成功");

    }
}
