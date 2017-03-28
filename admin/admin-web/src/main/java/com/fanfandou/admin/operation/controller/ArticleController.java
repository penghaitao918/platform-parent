package com.fanfandou.admin.operation.controller;


import com.fanfandou.admin.operation.entity.Approve;
import com.fanfandou.admin.operation.entity.MailOrder;
import com.fanfandou.admin.operation.service.ApproveService;
import com.fanfandou.admin.operation.service.MailOrderService;
import com.fanfandou.admin.system.entity.Menu;
import com.fanfandou.admin.system.service.MenuService;
import com.fanfandou.admin.system.service.PermissionService;
import com.fanfandou.admin.system.service.SysLogService;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * author zhongliang.
 * Description:物品申请操作.
 */
@RestController
@RequestMapping(value = "/operation/article")
public class ArticleController {

    @Autowired
    private ApproveService approveService;
    @Autowired
    private MailOrderService mailOrderService;
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private MenuService menuService;

    /**
     * 跳转到article List页面
     *
     * @return list页面
     */
    @RequestMapping(value = "/articleInit")
    public ModelAndView toArticleList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/operation/ArticleApplication");
        return mav;
    }

    /**
     * 验证是否有申请，审核的权限
     *
     * @return list页面
     */
    @RequestMapping(value = "/articleAuthorityInit")
    public boolean checkArticleList(String menuName) {
        if (!sysLogService.getUserName().equals("admin")) {
            Menu menu = this.menuService.getMenuByName(menuName);
            List<Integer> actionIds = this.permissionService.selActionId(sysLogService.getUserId(), 3, menu.getId());
            for (int i = 0; i < actionIds.size(); i++) {
                if (actionIds.get(i) == 4) {
                    return true;
                }
            }
        } else {
            return true;
        }
        return false;
    }


    /**
     * 跳转到article List页面
     *
     * @return list页面
     */
    @RequestMapping(value = "/approvalInit")
    public ModelAndView toApprovalList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/operation/ApprovalList");
        return mav;
    }

    @RequestMapping(value = "/approvalExcel")
    public ModelAndView approvalExcel() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/operation/MailOrder");
        return mav;
    }

    /**
     * 跳转到物品申请 添加页面
     *
     * @return 添加页面
     */
    @RequestMapping(value = "/articleEdit")
    public ModelAndView toAdd() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/operation/ArticleApplicationEdit");
        return mav;
    }

    /**
     * 添加
     *
     * @param mailOrder 邮件对象
     * @throws ServiceException
     */
    @RequestMapping(value = "/insert")
    public void insert(MailOrder mailOrder) throws ServiceException {
        mailOrderService.addMailOrder(mailOrder);
    }

    /**
     * 分页查询
     *
     * @param name     邮件标题
     * @param gameId   游戏id
     * @param typeCode 1订单 2审核
     * @param page     分页对象
     * @return page对象
     */
    @ResponseBody
    @RequestMapping("/pageList")
    public PageResult<Approve> getPageList(Integer sendType, Integer sendStatus, Integer mailType, Integer approvalStatus, String name, Integer gameId, int typeCode, Page page, String from, String to) throws Exception {
        return this.approveService.getMailOrderPageList(name, page, from, to, typeCode, sendType, mailType, sendStatus, approvalStatus, gameId);
    }

    /**
     * 根据id删除.
     *
     * @param articleIds id集合
     */
    @RequestMapping(value = "/articleDelete")
    public void articleDelete(String articleIds) throws ServiceException {
        String[] idsList = articleIds.split(",");
        List<Integer> idsListByInt = new ArrayList<>();
        for (int i = 0; i < idsList.length; i++) {
            idsListByInt.add(Integer.parseInt(idsList[i]));
        }
        this.mailOrderService.delMailOrder(idsListByInt);
    }

    /**
     * 根据id删除.
     *
     * @param articleIds id集合
     */
    @RequestMapping(value = "/articleApplyFor")
    public void articleApplyFor(String articleIds) throws ServiceException {
        this.mailOrderService.oneKeySubmit(articleIds);
    }

    /**
     * 根据id查.
     *
     * @param id id
     */
    @ResponseBody
    @RequestMapping(value = "/getArticle/{id}")
    public MailOrder getById(@PathVariable(value = "id") int id) throws ServiceException {
        return mailOrderService.selMailOrderById(id);
    }

    /**
     * 根据id查审批.
     *
     * @param id id
     */
    @ResponseBody
    @RequestMapping(value = "/getApprove/{id}")
    public Approve getApproveById(@PathVariable(value = "id") int id) throws ServiceException {
        return approveService.selByOrderId(id);
    }

    /**
     * 修改
     *
     * @param mailOrder。
     * @throws ServiceException
     */
    @RequestMapping(value = "/update")
    public void update(MailOrder mailOrder) throws ServiceException {
        mailOrderService.updMailOrder(mailOrder);
    }

    /**
     * 一键审批
     *
     * @param articleId       id集合
     * @param status          状态码
     * @param approvalContent 审批正文
     * @throws ServiceException
     */
    @RequestMapping(value = "/approvalAdd")
    public void approvalAdd(String articleId, int status, String approvalContent) throws ServiceException {
        mailOrderService.oneKeyApprove(articleId, status, approvalContent);
    }

    /**
     * 根据邮件状态，审批状态查询订单数
     *
     * @param sendStatus     邮件状态
     * @param approvalStatus 审批状态
     * @throws ServiceException
     */
    @RequestMapping(value = "/selectCountByMid")
    public int selectCountByMid(Integer sendStatus, Integer approvalStatus) throws ServiceException {
        return approveService.selectCountByMid(sendStatus, approvalStatus);
    }


    /**
     * @param request  请求
     * @param response 响应
     * @return ModelAndView
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/download")
    public ModelAndView download(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

//		String storeName = "Spring3.xAPI_zh.chm";
        String storeName = "MailOrder.xls";
        String contentType = "application/octet-stream";
        ArticleController.download(request, response, storeName, contentType);
        return null;
    }


    /**
     * @param request     请求
     * @param response    响应
     * @param storeName   文件名
     * @param contentType 类型
     * @throws Exception
     */

    //文件下载 主要方法
    public static void download(HttpServletRequest request,
                                HttpServletResponse response, String storeName, String contentType
    ) throws Exception {

        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        //获取项目根目录
        String ctxPath = request.getSession().getServletContext()
                .getRealPath("");

        //获取下载文件露肩
        String downLoadPath = ctxPath + "/resource/Uploads/" + storeName;

        //获取文件的长度
        long fileLength = new File(downLoadPath).length();

        //设置文件输出类型
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment; filename="
                + new String(storeName.getBytes("utf-8"), "ISO8859-1"));
        //设置输出长度
        response.setHeader("Content-Length", String.valueOf(fileLength));
        //获取输入流
        bis = new BufferedInputStream(new FileInputStream(downLoadPath));
        //输出流
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        //关闭流
        bis.close();
        bos.close();
    }


    /**
     * @param applyReason 上传理由
     * @param file        文件
     * @param request     请求
     * @return ModelAndView
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/upload")
    public ModelAndView upload(String applyReason, @RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {

        //获取文件 存储位置
        String realPath = request.getSession().getServletContext()
                .getRealPath("/resource/Uploads");

        File pathFile = new File(realPath);

        if (!pathFile.exists()) {
            //文件夹不存 创建文件
            pathFile.mkdirs();
        }
        System.out.println("文件类型：" + file.getContentType());
        System.out.println("文件名称：" + file.getOriginalFilename());
        System.out.println("文件大小:" + file.getSize());
        System.out.println(".................................................");
        //将文件copy上传到服务器
        file.transferTo(new File(realPath + "/" + file.getOriginalFilename()));
        //FileUtils.copy


        //获取modelandview对象
        ModelAndView view = new ModelAndView();
        view.setViewName("/operation/UploadWrong");
        view.addObject("returnList", mailOrderService.importExcel(realPath + "/" + file.getOriginalFilename(), file.getOriginalFilename(), applyReason));
        return view;
    }
}
