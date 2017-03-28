package com.fanfandou.admin.operation.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fanfandou.admin.api.exception.AdminException;
import com.fanfandou.admin.operation.dao.MailOrderMapper;
import com.fanfandou.admin.operation.entity.Approve;
import com.fanfandou.admin.operation.entity.MailOrder;
import com.fanfandou.admin.operation.entity.MailOrderFailure;
import com.fanfandou.admin.operation.entity.MailOrderTask;
import com.fanfandou.admin.operation.service.ApproveService;
import com.fanfandou.admin.operation.service.InputExcelService;
import com.fanfandou.admin.operation.service.MailOrderFailureService;
import com.fanfandou.admin.operation.service.MailOrderService;
import com.fanfandou.admin.operation.service.MailOrderTaskService;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.billing.entity.GoodsItem;
import com.fanfandou.platform.api.game.entity.GameRole;
import com.fanfandou.platform.api.game.service.GameRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangzhenwei on 2016/6/30.
 * Description 游戏物品类型service实现类
 */
@Service("mailOrderService")
public class MailOrderServiceImpl extends BaseLogger implements MailOrderService {

    @Autowired
    private MailOrderMapper mailOrderMapper;

    @Autowired
    private ApproveService approveService;

    @Autowired
    private MailOrderTaskService mailOrderTaskService;

    @Autowired
    private ThreadPoolTaskExecutor addTaskExecutor;

    @Autowired
    private InputExcelService orderExcelService;

    @Autowired
    private GameRoleService gameRoleService;

    @Autowired
    private MailOrderFailureService mailOrderFailureService;

    /**
     * 查询所有.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<MailOrder> selectAll() {
        return this.mailOrderMapper.selectAll();
    }

    /**
     * 添加.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addMailOrder(MailOrder mailOrder) {
        Subject subject = SecurityUtils.getSubject();
        //添加订单
        mailOrder.setCreateTime(new Date());
        mailOrder.setSendStatus(1);
        String applyUser = subject.getPrincipal().toString();
        this.mailOrderMapper.insert(mailOrder);
        //添加审批
        Approve approve = new Approve();
        approve.setApplyUser(applyUser);
        //审批状态 1未提交 2提交审批中 3审批通过 4审批打回
        approve.setApprovalStatus(1);
        approve.setMailOrder(mailOrder);
        approveService.addApprove(approve);
    }

    /**
     * 删除.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void delMailOrder(List<Integer> idList) throws ServiceException {
        for (int i = 0; i < idList.size(); i++) {
            int id = idList.get(i);
            Approve approve = approveService.selByOrderId(id);
            int approvalStatus = approve.getApprovalStatus();
            if (approvalStatus == 1 || approvalStatus == 4) {
                //删除订单
                this.mailOrderMapper.delete(id);
                //删除审核
                approveService.delByOrderId(id);
            } else {
                throw new ServiceException(AdminException.ADMIN_MAIL_APPROVALSTATUS);
            }
        }
    }

    /**
     * 更新.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void updMailOrder(MailOrder mailOrder) {
        MailOrder mailOrderOld = this.selMailOrderById(mailOrder.getId());
        mailOrder.setCreateTime(mailOrderOld.getCreateTime());
        this.mailOrderMapper.update(mailOrder);
    }

    /**
     * 通过id查找.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public MailOrder selMailOrderById(int id) {
        return this.mailOrderMapper.selectById(id);
    }

    /**
     * 提交.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void submitOrder(int mailOrderId) {
        Approve approve = approveService.selByOrderId(mailOrderId);
        int approvalStatus = approve.getApprovalStatus();
        if (approvalStatus == 1 || approvalStatus == 4) {
            //赋值
            approve.setApprovalStatus(2);
            approve.setApplyTime(new Date());
            //更新
            approveService.updByOrderId(approve);
        }
    }

    /**
     * 审核.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void approvalOrder(int mailOrderId, int status, String approvalContent) {
        if (status == 3 || status == 4) {
            Approve approve = approveService.selByOrderId(mailOrderId);
            int approvalStatus = approve.getApprovalStatus();
            if (approvalStatus == 2) {
                approve.setApprovalStatus(status);
                approve.setApprovalTime(new Date());
                approve.setApprovalUser(SecurityUtils.getSubject().getPrincipal().toString());
                approve.setApprovalContent(approvalContent);

                approveService.updByOrderId(approve);
                if (status == 3) {
                    //审核成功后要向task中添加邮件订单记录
                    addMailOrderTask(approve);
                }
            }
        }
    }

    /**
     * 添加task.
     */
    private void addMailOrderTask(Approve approve) {
        String failedReason = "";
        boolean flag = true;
        MailOrderTask mailOrderTask = new MailOrderTask();
        //gameCode
        GameCode gameCode = GameCode.getById(approve.getMailOrder().getGameId());
        mailOrderTask.setGameId(approve.getMailOrder().getGameId());
        //areaId
        int areaId = Integer.parseInt(approve.getMailOrder().getAreaIds());
        mailOrderTask.setAreaIds(approve.getMailOrder().getAreaIds());

        int sendType = approve.getMailOrder().getSendType();
        //个人邮件

        try {
            if (sendType == 1) {
                //1平台id 2用户id 3用户名
                int sendByType = approve.getMailOrder().getSendByType();

                String sendByValue = approve.getMailOrder().getSendByValue();
                String[] sendByValues = sendByValue.split(",");
                String roleIds = "";
                if (sendByType == 2) {
                    for (String sendByValueStr : sendByValues) {
                        int roleId = Integer.parseInt(sendByValueStr);
                        try {
                            GameRole gameRole = gameRoleService.getRoleById(gameCode, roleId);
                            if (gameRole == null) {
                                flag = false;
                                failedReason = failedReason + "(roleId)," + sendByValueStr + ":找不到该用户(区服id:" + approve.getMailOrder().getAreaIds() + ")";
                                logger.debug("失败：" + roleId + "，找不到该用户");
                            } else {
                                roleIds = roleIds + "," + gameRole.getRoleId();
                            }
                        } catch (Exception e) {
                            flag = false;
                            failedReason = failedReason + "(roleId)," + sendByValueStr + ":找不到该用户(区服id:" + approve.getMailOrder().getAreaIds() + ")";
                            logger.debug("失败：" + roleId + "，找不到该用户");
                            e.printStackTrace();
                        }

                    }
                }
                if (sendByType == 1) {
                    for (String sendByValueStr : sendByValues) {
                        int userId = Integer.parseInt(sendByValueStr);
                        try {
                            GameRole gameRole = gameRoleService.getRoleByUserId(gameCode, areaId, userId);
                            if (gameRole == null) {
                                flag = false;
                                failedReason = failedReason + "userId," + sendByValueStr + ":找不到该用户(区服id:" + approve.getMailOrder().getAreaIds() + ")";
                                logger.debug("失败：" + userId + "，找不到该用户");
                            } else {
                                roleIds = roleIds + "," + gameRole.getRoleId();
                            }
                        } catch (Exception e) {
                            flag = false;
                            failedReason = failedReason + "userId," + sendByValueStr + ":找不到该用户(区服id:" + approve.getMailOrder().getAreaIds() + ")";
                            logger.debug("失败：" + userId + "，找不到该用户");
                            e.printStackTrace();
                        }

                    }
                }
                if (sendByType == 3) {
                    for (String sendByValueStr : sendByValues) {
                        try {
                            GameRole gameRole = gameRoleService.getRoleByName(gameCode, areaId, sendByValueStr);
                            if (gameRole == null) {
                                flag = false;
                                failedReason = failedReason + "userName," + sendByValueStr + ":找不到该用户(区服id:" + approve.getMailOrder().getAreaIds() + ")";
                                logger.debug("失败：" + sendByValueStr + "，找不到该用户");
                            } else {
                                roleIds = roleIds + "," + gameRole.getRoleId();
                            }
                        } catch (Exception e) {
                            flag = false;
                            failedReason = failedReason + "userName," + sendByValueStr + ":找不到该用户(区服id:" + approve.getMailOrder().getAreaIds() + ")";
                            logger.debug("失败：" + sendByValueStr + "，找不到该用户");
                            e.printStackTrace();
                        }

                    }
                }
                if (roleIds.length() >= 2) {
                    roleIds = roleIds.substring(1);
                }

                mailOrderTask.setRoleIds(roleIds);
            }
        } catch (Exception e) {
            MailOrder mailOrder = approve.getMailOrder();
            mailOrder.setSendStatus(4);
            this.updMailOrder(mailOrder);
            //录入异常信息
            MailOrderFailure mailOrderFailure = new MailOrderFailure();
            mailOrderFailure.setMailTitle(approve.getMailOrder().getMailTitle());
            mailOrderFailure.setMailOrderId(approve.getMailOrder().getId());
            mailOrderFailure.setFailureReasons("发送对象格式错误！");
            mailOrderFailure.setRemark("审核邮件检测异常信息");
            mailOrderFailureService.addMailOrderFailure(mailOrderFailure);

        }

        //全服邮件
        if (sendType == 2) {
            mailOrderTask.setRoleIds("0");
        }
        if (sendType != 1 && sendType != 2) {
            flag = false;
            logger.debug("失败：" + sendType + "，找不到该邮件类型");
        }
        //1未发送 2发送中 3发送成功 4发送失败
        mailOrderTask.setSendStatus(1);
        mailOrderTask.setMailTitle(approve.getMailOrder().getMailTitle());
        mailOrderTask.setMailContent(approve.getMailOrder().getMailContent());
        mailOrderTask.setItemJson(approve.getMailOrder().getItemJson());
        mailOrderTask.setCreateTime(new Date());
        mailOrderTask.setMailOrderId(approve.getMailOrder().getId());
        if (flag) {
            //执行添加
            addTaskExecutor.execute(new MailOrderTaskDispatchThread(mailOrderTask));
        } else {

            MailOrder mailOrder = approve.getMailOrder();
            mailOrder.setSendStatus(4);
            //录入异常信息
            MailOrderFailure mailOrderFailure = new MailOrderFailure();
            mailOrderFailure.setMailTitle(approve.getMailOrder().getMailTitle());
            mailOrderFailure.setMailOrderId(approve.getMailOrder().getId());
            mailOrderFailure.setFailureReasons(failedReason);
            mailOrderFailure.setRemark("审核邮件检测异常信息");
            mailOrderFailureService.addMailOrderFailure(mailOrderFailure);


            //更新订单状态
            this.updMailOrder(mailOrder);
        }
    }

    /**
     * 添加邮件任务线程
     * 创建线程放入线程池中执行.
     */
    private class MailOrderTaskDispatchThread implements Runnable {

        private MailOrderTask mailOrderTask;

        public MailOrderTaskDispatchThread(MailOrderTask mailOrderTask) {
            this.mailOrderTask = mailOrderTask;
        }

        @Override
        public void run() {
            mailOrderTaskService.addMailOrderTask(mailOrderTask);
        }
    }

    /**
     * 一键提交.
     *
     * @param orderIds 订单id字符串
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void oneKeySubmit(String orderIds) {
        String[] orderId = orderIds.split(",");
        for (String mailOrderIdStr : orderId) {
            int mailOrderId = Integer.parseInt(mailOrderIdStr);
            submitOrder(mailOrderId);
        }
    }

    /**
     * 一键审核.
     *
     * @param mailOrderIds    订单id Str
     * @param status          3审核成功 4审核失败
     * @param approvalContent 审核批注
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void oneKeyApprove(String mailOrderIds, int status, String approvalContent) {
        String[] mailOrderId = mailOrderIds.split(",");
        for (String mailOrderIdStr : mailOrderId) {
            int orderId = Integer.parseInt(mailOrderIdStr);
            approvalOrder(orderId, status, approvalContent);
        }
    }

    /**
     * 根据json得到list.
     */
    public List<GoodsItem> getGoodsItemList(String itemJson) {
        List<GoodsItem> itemList = new ArrayList<>();
        try {
            List list = JSON.parseObject(itemJson, new TypeReference<ArrayList>() {
            });

            for (int i = 0; i < list.size(); i++) {
                GoodsItem items = JSON.parseObject(list.get(i).toString(), GoodsItem.class);
                itemList.add(items);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    /**
     * 把list转化成json.
     */
    public String setGoodsItemJson(List<GoodsItem> list) {
        return JSON.toJSONString(list, true);
    }

    /**
     * excel导入
     *
     * @param url      路径
     * @param fileName 文件名.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<String> importExcel(String url, String fileName, String applyReason) throws Exception {
        FileInputStream is = new FileInputStream(new File(url));
        return orderExcelService.importOrderExcel(is, fileName, applyReason);
    }
}
