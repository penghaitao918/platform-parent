package com.fanfandou.admin.operation.controller;

import com.fanfandou.common.entity.ValidStatus;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.activity.entity.PromoteAwardPackage;
import com.fanfandou.platform.api.activity.entity.PromoteCategory;
import com.fanfandou.platform.api.activity.entity.PromoteCodeBatch;
import com.fanfandou.platform.api.activity.service.PromoteAwardPackageService;
import com.fanfandou.platform.api.activity.service.PromoteCodeBatchService;
import com.fanfandou.platform.api.activity.service.PromoteCodeService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * author zhongliang.
 * Description:礼包码-批次管理.
 */
@RestController
@RequestMapping(value = "/operation/bagCode")
public class BagCodeController {

    @Autowired
    private PromoteAwardPackageService promoteAwardPackageService;
    @Autowired
    private PromoteCodeBatchService promoteCodeBatchService;
    @Autowired
    private PromoteCodeService promoteCodeService;

    /**
     * 跳转到礼包码-批次管理List页面
     *
     * @return 分类页面
     */
    @RequestMapping(value = "/bagCodeBatchInit")
    public ModelAndView toBagCodeBatchList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/operation/BagCodeBatch");
        return mav;
    }

    /**
     * 跳转到礼包码-批次管理Edit页面
     *
     * @return 分类页面
     */
    @RequestMapping(value = "/bagCodeBatchEdit")
    public ModelAndView toBagCodeBatchEdit() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/operation/BagCodeBatchEdit");
        return mav;
    }

    /**
     * 跳转到礼包码-批次礼包管理List页面
     *
     * @return 分类页面
     */
    @RequestMapping(value = "/batchGiftBagInit")
    public ModelAndView toBatchGiftBagList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/operation/BatchGiftBag");
        return mav;
    }

    /**
     * 跳转到礼包码-批次礼包管理Edit页面
     *
     * @return 分类页面
     */
    @RequestMapping(value = "/batchGiftBagEdit")
    public ModelAndView toBatchGiftBagEdit() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/operation/BatchGiftBagEdit");
        return mav;
    }

    /**
     * 跳转到礼包码-礼包码查询List页面
     *
     * @return 分类页面
     */
    @RequestMapping(value = "/bagCodeQueryInit")
    public ModelAndView toBagCodeQuery() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/operation/BagCodeQuery");
        return mav;
    }

    /**
     * 跳转到礼包码-礼包码查询List页面
     *
     * @return 分类页面
     */
    @RequestMapping(value = "/bagCodeDisposeInit")
    public ModelAndView toBagCodeDispose() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/operation/BagCodeDispose");
        return mav;
    }

    /**
     * 礼包添加.
     *
     * @param promoteAwardPackage 礼包对象.
     * @param validStatusVal      状态.
     * @throws Exception
     */
    @RequestMapping(value = "/giftBagAdd")
    public void addGiftBag(PromoteAwardPackage promoteAwardPackage, Integer validStatusVal, Integer promoteCategoryVal) throws Exception {
        promoteAwardPackage.setValidStatus(ValidStatus.valueOf(validStatusVal));
        promoteAwardPackage.setPromoteCategory(PromoteCategory.valueOf(promoteCategoryVal));
        Subject subject = SecurityUtils.getSubject();
        String applyUser = subject.getPrincipal().toString();
        promoteAwardPackage.setCreateIp(applyUser);
        this.promoteAwardPackageService.createAwardPackage(promoteAwardPackage);
    }

    /**
     * 礼包修改.
     *
     * @param promoteAwardPackage 礼包对象.
     * @param validStatusVal      状态.
     * @throws Exception
     */
    @RequestMapping(value = "/giftBagUpdate")
    public void updateGiftBag(PromoteAwardPackage promoteAwardPackage, Integer validStatusVal) throws Exception {
        promoteAwardPackage.setValidStatus(ValidStatus.valueOf(validStatusVal));
        this.promoteAwardPackageService.updateAwardPackage(promoteAwardPackage);
    }

    /**
     * 礼包删除 （批量）
     *
     * @param ids .
     * @throws Exception
     */
    @RequestMapping(value = "/giftBagDelete")
    public void deleteGiftBag(String ids) throws Exception {
        String[] bagId = ids.split(",");
        List<Integer> idLists = new ArrayList<Integer>();
        for (int i = 0; i < bagId.length; i++) {
            idLists.add(Integer.parseInt(bagId[i]));
        }
        this.promoteAwardPackageService.deleteAwardPackageById(idLists);
    }

    /**
     * 分页查询
     *
     * @param packageName .
     * @param gameId      .
     * @param page        .
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/pageList")
    public PageResult getPageList(String packageName, Integer gameId, Page page) throws Exception {
        return this.promoteAwardPackageService.getAwardPackage(gameId, packageName, page);
    }

    /**
     * 礼包list
     *
     * @param gameId .
     * @return list
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getList")
    public List<PromoteAwardPackage> getList(Integer gameId) throws Exception {
        return this.promoteAwardPackageService.getList(gameId);
    }

    /**
     * 根据id查询
     *
     * @param id id
     * @return 礼包对象
     * @throws ServiceException
     */
    @ResponseBody
    @RequestMapping(value = "/gameGiftBagGetById")
    public PromoteAwardPackage getGiftBagById(Integer id) throws ServiceException {
        return this.promoteAwardPackageService.getAwardPackageById(id);
    }

    /**
     * 批次添加.
     *
     * @param promoteCodeBatch 批次对象.
     * @param validStatusVal   状态.
     * @throws Exception
     */
    @RequestMapping(value = "/batchCodeAdd")
    public void addBatchCode(PromoteCodeBatch promoteCodeBatch, Integer validStatusVal) throws Exception {
        promoteCodeBatch.setValidStatus(ValidStatus.valueOf(validStatusVal));
        /*
        获取登录用户
         */
        Subject subject = SecurityUtils.getSubject();
        String applyUser = subject.getPrincipal().toString();
        promoteCodeBatch.setCreateIp(applyUser);
        this.promoteCodeBatchService.createCodeBatch(promoteCodeBatch);
    }

    /**
     * 批次修改.
     *
     * @param promoteCodeBatch 批次对象.
     * @param validStatusVal   状态.
     * @throws Exception
     */
    @RequestMapping(value = "/batchCodeUpdate")
    public void updateBatchCode(PromoteCodeBatch promoteCodeBatch, Integer validStatusVal) throws Exception {
        promoteCodeBatch.setValidStatus(ValidStatus.valueOf(validStatusVal));
        /*
        获取登录用户
         */
        Subject subject = SecurityUtils.getSubject();
        String applyUser = subject.getPrincipal().toString();
        promoteCodeBatch.setCreateIp(applyUser);
        this.promoteCodeBatchService.updateCodeBatch(promoteCodeBatch);

    }

    /**
     * 批次删除 （批量）
     *
     * @param ids .
     * @throws Exception
     */
    @RequestMapping(value = "/batchCodeDelete")
    public void deleteBatchCode(String ids) throws Exception {
        String[] codeId = ids.split(",");
        List<Integer> idLists = new ArrayList<Integer>();
        for (int i = 0; i < codeId.length; i++) {
            idLists.add(Integer.parseInt(codeId[i]));
        }
        this.promoteCodeBatchService.deleteCodeBatchById(idLists);
    }


    /**
     * 分页查询
     *
     * @param batchName .
     * @param gameId    .
     * @param page      .
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/codeBatchPageList")
    public PageResult getPageList(String batchName, Integer gameId, Page page, String from, String to) throws Exception {
        return this.promoteCodeBatchService.getCodeBatch(gameId, batchName, page, from, to);
    }

    /**
     * 根据id查询
     *
     * @param id id
     * @return 批次对象
     * @throws ServiceException
     */
    @ResponseBody
    @RequestMapping(value = "/batchCodeGetById")
    public PromoteCodeBatch batchCodeById(Integer id) throws Exception {
        return this.promoteCodeBatchService.getCodeBatchById(id);
    }

    /**
     * 查询code码集合，缓存
     *
     * @return 集合
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getPromoteCodeList")
    public PageResult getPromoteCodeList(String codeName, Integer gameId, Page page) throws Exception {
        return this.promoteCodeService.codeListPage(gameId, codeName, page);
    }

    /**
     * 批次码导出
     *
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getBatchCodeExcel/{id}")
    public ModelAndView getBatchCodeExcel(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") Integer id) throws Exception {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("批次code表");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("code码");
        // 第五步，写入实体数据
        PromoteCodeBatch promoteCodeBatch = this.promoteCodeBatchService.getCodeBatchById(id);
        List<String> list = this.promoteCodeBatchService.generatePromoteCode(promoteCodeBatch);
        //获取项目根目录
        String ctxPath = request.getSession().getServletContext()
                .getRealPath("");

        String storeName = "Code.xls";
        //获取下载文件露肩
        String downLoadPath = ctxPath + "/resource/Uploads/" + storeName;
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow((int) i + 1);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(list.get(i));
        }

        try {
            FileOutputStream fout = new FileOutputStream(downLoadPath);
            wb.write(fout);
//            //获取文件的长度
//            long fileLength = new File(downLoadPath).length();
//            //设置文件输出类型
//            response.setContentType("application/octet-stream");
//            response.setHeader("Content-disposition", "attachment; filename="
//                    + new String(storeName.getBytes("utf-8"), "ISO8859-1"));
//            //设置输出长度
//            response.setHeader("Content-Length", String.valueOf(fileLength));

            fout.close();
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String contentType = "application/octet-stream";
        ArticleController.download(request, response, storeName, contentType);
        return null;
    }


}
