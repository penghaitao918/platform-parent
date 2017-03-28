package com.fanfandou.admin.system.test.operation.mailApprove;

import com.fanfandou.admin.operation.entity.Approve;
import com.fanfandou.admin.operation.service.impl.ApproveServiceImpl;

/**
 * Created by wangzhenwei on 2016/7/18.
 * Description test
 */
public class test121 {
    public static void main(String[] args) throws Exception {
        ApproveServiceImpl asi = new ApproveServiceImpl();
        Approve approve = asi.selApproveById(1);
        System.out.println(approve);
    }
}
