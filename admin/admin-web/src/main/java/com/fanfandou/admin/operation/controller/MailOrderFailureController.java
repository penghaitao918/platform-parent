package com.fanfandou.admin.operation.controller;


import com.fanfandou.admin.operation.entity.Approve;
import com.fanfandou.admin.operation.entity.MailOrder;
import com.fanfandou.admin.operation.entity.MailOrderFailure;
import com.fanfandou.admin.operation.service.ApproveService;
import com.fanfandou.admin.operation.service.MailOrderFailureService;
import com.fanfandou.admin.operation.service.MailOrderService;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.List;


/**
 * author zhongliang.
 * Description:物品发送错误信息操作.
 */
@RestController
@RequestMapping(value = "/operation/mailOrderFailure")
public class MailOrderFailureController {

    @Autowired
    private MailOrderFailureService mailOrderFailureService;

    /**
     * 跳转到mailOrderFailure List页面
     *
     * @return list页面
     */
    @RequestMapping(value = "/mailOrderFailureInit")
    public ModelAndView toArticleList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/operation/MailOrderFailure");
        return mav;
    }

    /**
     * 分页查询
     *
     * @param mailTitle     邮件标题
     * @param page     分页对象
     * @return  page对象
     */
    @ResponseBody
    @RequestMapping("/pageList")
    public PageResult<MailOrderFailure> getPageList(Page page, String mailTitle,String from,String to) throws Exception {
        return this.mailOrderFailureService.getPageList(mailTitle, page,from,to);
    }

    /**
     * 根据id删除.
     *
     * @param mailOrderFailureIds id集合
     */
    @RequestMapping(value = "/mailOrderFailureDelete")
    public void mailOrderFailureDelete(String mailOrderFailureIds) throws ServiceException {
        String[] idsList = mailOrderFailureIds.split(",");
        List<Integer> idsListByInt = new ArrayList<>();
        for (int i = 0; i < idsList.length; i++) {
            idsListByInt.add(Integer.parseInt(idsList[i]));
        }
        this.mailOrderFailureService.delMailOrderFailure(idsListByInt);
    }

    /**
     * 根据id查.
     *
     * @param id id
     */
    @ResponseBody
    @RequestMapping(value = "/getMailOrderFailure/{id}")
    public MailOrderFailure getById(@PathVariable(value = "id") int id) throws ServiceException {
        return mailOrderFailureService.selMailOrderFailureById(id);
    }

}
