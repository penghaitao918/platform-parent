package com.fanfandou.admin.operation.service;

import java.io.InputStream;
import java.util.List;

/**
 * Created by wangzhenwei on 2016/7/21.
 * Description 订单接口.
 */
public interface InputExcelService {

    /**
     * 邮件订单excel导入接口.
     * @param in 输入流
     * @param filename 文件名
     * @param applyReason 申请原因
     * @return 错误信息集合
     */
    List<String> importOrderExcel(InputStream in, String filename,String applyReason) throws Exception;

    /**
     * 物品excel导入接口.
     */
    List<String> importItemExcel(InputStream in, String filename) throws Exception;

    void creat2003Excel() throws Exception ;
}
