package com.fanfandou.admin;

import com.fanfandou.admin.operation.service.InputExcelService;
import com.fanfandou.admin.operation.service.MailOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * Created by wangzhenwei on 2016/7/21.
 * Description test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/*.xml")
public class test233 {


    @Autowired
    private InputExcelService orderExcelService;

    @Autowired
    private MailOrderService mailOrderService;

    @Autowired
    private InputExcelService inputExcelService;


//    @Test
//    public void test() throws Exception{
//        FileInputStream is = new FileInputStream(new File("C:/Users/Administrator/Desktop/MailOrder.xls"));
//        String fileName = "MailOrder.xls";
//        String applyReason = "testa测试专用";
//        List list = orderExcelService.importExcel(is,fileName,applyReason);
//        System.out.println(list);
//    }

//    @Test
//    public void test() throws Exception{
//        String url = "C:/Users/Administrator/Desktop/MailOrder 3.xls";
//        String fileName = "MailOrder 3.xls";
//        String applyReason = "testa测试专用";
//        List list = mailOrderService.importExcel(url,fileName,applyReason);
//        System.out.println(list);
//    }

    @Test
    public void test() throws Exception {
        String url = "C:/Users/Administrator/Desktop/Item.xls";
        FileInputStream is = new FileInputStream(new File(url));
        String fileName = "Item.xls";
        List list = inputExcelService.importItemExcel(is, fileName);
        System.out.println(list);
    }

//    @Test
//    public void create() throws Exception{
//        orderExcelService.creat2003Excel();
//    }


//        MailOrder mailOrder = new MailOrder();
//        List<GoodsItem> items = new ArrayList<>();
//        GoodsItem g1 = new GoodsItem();
//        GoodsItem g2 = new GoodsItem();
//        GoodsItem g3 = new GoodsItem();
//        g1.setItemId(1);
//        g2.setItemId(2);
//        g3.setItemId(3);
//        g1.setItemName("111");
//        g2.setItemName("222");
//        g3.setItemName("333");
//        items.add(g1);
//        items.add(g2);
//        items.add(g3);
//
//        String json = mailOrder.setGoodsItemJson(items);
//        mailOrder.setItemJson(json);
//        System.out.println(mailOrder);
}
