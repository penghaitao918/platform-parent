package com.fanfandou.admin.operation.service.impl;

import com.fanfandou.admin.operation.entity.MailOrder;
import com.fanfandou.admin.operation.entity.MailOrderFailure;
import com.fanfandou.admin.operation.entity.MailOrderTask;
import com.fanfandou.admin.operation.service.MailOrderFailureService;
import com.fanfandou.admin.operation.service.MailOrderService;
import com.fanfandou.admin.operation.service.MailOrderTaskService;
import com.fanfandou.admin.operation.service.MailSendService;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.platform.api.billing.entity.GoodsItem;
import com.fanfandou.platform.api.billing.entity.GoodsItemPackage;
import com.fanfandou.platform.api.game.entity.GameRole;
import com.fanfandou.platform.api.game.entity.OperationType;
import com.fanfandou.platform.api.game.service.GameRoleService;
import com.fanfandou.platform.api.game.service.OperationDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wangzhenwei on 2016/8/1.
 * Description 邮件发送.
 */
@Service("mailSendService")
public class MailSendServiceImpl implements MailSendService {

    private static final long TASK_PERIOD = 30000L;

    @Autowired
    private ThreadPoolTaskExecutor approveExecutor;

    @Autowired
    private MailOrderTaskService mailOrderTaskService;

    @Autowired
    private MailOrderService mailOrderService;

    @Autowired
    private OperationDispatchService operationDispatchService;

    @Autowired
    private GameRoleService gameRoleService;

    @Autowired
    private MailOrderFailureService mailOrderFailureService;

    public MailSendServiceImpl() {
        Timer pingTimer = new Timer();
        pingTimer.schedule(new MailOrderTimerTask(), TASK_PERIOD, TASK_PERIOD);
    }

    /**
     * 发送.
     *
     * @param mailOrderTask 订单任务
     */
    private void sendMailOrder(MailOrderTask mailOrderTask) {
        //修改状态 2发送中
        mailOrderTask.setSendStatus(2);
        mailOrderTask.setSendCount(mailOrderTask.getSendStatus() + 1);
        //更新
        mailOrderTaskService.updMailOrderTask(mailOrderTask);
        int mailOrderId = mailOrderTask.getMailOrderId();
        MailOrder mailOrder = mailOrderService.selMailOrderById(mailOrderId);
        mailOrder.setSendStatus(2);
        mailOrderService.updMailOrder(mailOrder);
        //执行发送
        approveExecutor.execute(new MailOrderTaskDispatchThread(mailOrderTask));
    }

    /**
     * 邮件发送线程
     * 用于邮件发送时，创建线程放入线程池中执行.
     */
    private class MailOrderTaskDispatchThread implements Runnable {

        private MailOrderTask mailOrderTask;

        public MailOrderTaskDispatchThread(MailOrderTask mailOrderTask) {
            this.mailOrderTask = mailOrderTask;
        }

        @Override
        public void run() {
            MailOrderFailure mailOrderFailure = new MailOrderFailure();
            mailOrderFailure.setMailTitle(mailOrderTask.getMailTitle());
            mailOrderFailure.setMailOrderId(mailOrderTask.getMailOrderId());
            GameCode gameCode = GameCode.getById(mailOrderTask.getGameId());
            //记录数据错误
            if (gameCode == null) {
                mailOrderFailure.setFailureReasons("游戏Id不存在");
                mailOrderFailure.setRemark("邮件发送空值异常");
                mailOrderFailureService.addMailOrderFailure(mailOrderFailure);
            }

            int areaId = Integer.parseInt(mailOrderTask.getAreaIds());
            int mailOrderId = mailOrderTask.getMailOrderId();
            MailOrder mailOrder = mailOrderService.selMailOrderById(mailOrderId);
            int sendByType = mailOrder.getSendByType();
            //组装gameItemPackage
            GoodsItemPackage goodsItemPackage = new GoodsItemPackage();
            goodsItemPackage.setAwardPackageId(String.valueOf(mailOrderTask.getId()));
            List<GoodsItem> goodsItemList = mailOrderService.getGoodsItemList(mailOrderTask.getItemJson());
            goodsItemPackage.setGoodsItems(goodsItemList);
            String mailContent = mailOrder.getMailContent();
            goodsItemPackage.setPackageDesc(mailContent);
            goodsItemPackage.setTitle(mailOrder.getMailTitle());

            int exNum = 0;
            String failedReason = mailOrderTask.getFailedReason();
            String[] roleIds = mailOrderTask.getRoleIds().split(",");
            List<String> roleListArr = new ArrayList<>();
            List<String> roleList = new ArrayList<>();

            for (String roleIdStr : roleIds) {
                roleList.add(roleIdStr);
            }
            if (roleList.size() > 1) {
                Long userId = 0L;
                Long roleId = 0L;
                GameRole gameRole = new GameRole();
                OperationType optType = OperationType.SEND_ITEM;
                for (int i = 0; i < roleList.size(); i++) {

                    if (!roleList.get(i).equals("")) {
                        roleId = Long.parseLong(roleList.get(i));
                    }

                    try {
                        gameRole = gameRoleService.getRoleById(gameCode, roleId);

                        //记录数据错误
                        if (gameRole == null) {
                            mailOrderFailure.setFailureReasons("游戏角色" + roleId + "不存在");
                            mailOrderFailure.setRemark("邮件发送空值异常");
                            mailOrderFailureService.addMailOrderFailure(mailOrderFailure);
                            roleListArr.add(roleList.get(i));
                        } else {
                            userId = gameRole.getUserId();
                        }

                    } catch (Exception e) {
                        mailOrderFailure.setFailureReasons("游戏角色" + roleId + "不存在");
                        mailOrderFailure.setRemark("邮件发送空值异常");
                        mailOrderFailureService.addMailOrderFailure(mailOrderFailure);
                        roleListArr.add(roleList.get(i));
                        e.printStackTrace();
                    }
                    int mailType = mailOrder.getMailType();

                    if (mailType == 4) {
                        optType = OperationType.DELIVER_OF_PAY;
                    }

                }

                for (int i = 0; i < roleList.size(); i++) {
                    for (int j = 0; j < roleListArr.size(); j++) {
                        if (roleList.get(i).equals(roleListArr.get(j))) {
                            roleList.remove(i);
                        }
                    }
                }
                try {
                    //调用发送
                    operationDispatchService.sendPackage(gameCode, areaId, goodsItemPackage, userId, roleId, roleList, optType);

                } catch (Exception e) {
                    exNum = 1;
                    if (sendByType == 1) {
                        failedReason = failedReason + "," + roleId;
                    }
                    if (sendByType == 2) {
                        failedReason = failedReason + "," + userId;
                    }
                    if (sendByType == 3) {
                        String roleName = "";
                        if (gameRole != null) {
                            roleName = gameRole.getRoleName();
                        }

                        failedReason = failedReason + "," + roleName;
                    }

                    mailOrderFailure.setFailureReasons(failedReason);
                    mailOrderFailure.setRemark("邮件检测异常信息");
                    mailOrderFailureService.addMailOrderFailure(mailOrderFailure);

                }
            } else {

                for (String roleIdStr : roleIds) {
                    Long roleId = 0L;

                    if (!roleIdStr.equals("")) {
                        roleId = Long.parseLong(roleIdStr);
                    }


                    Long userId = 0L;
                    GameRole gameRole = new GameRole();
                    try {
                        gameRole = gameRoleService.getRoleById(gameCode, roleId);

                        //记录数据错误
                        if (gameRole == null) {
                            mailOrderFailure.setFailureReasons("游戏角色" + roleId + "不存在");
                            mailOrderFailure.setRemark("邮件发送空值异常");
                            mailOrderFailureService.addMailOrderFailure(mailOrderFailure);

                        } else {
                            userId = gameRole.getUserId();
                        }

                    } catch (Exception e) {
                        mailOrderFailure.setFailureReasons("游戏角色" + roleId + "不存在");
                        mailOrderFailure.setRemark("邮件发送空值异常");
                        mailOrderFailureService.addMailOrderFailure(mailOrderFailure);

                        e.printStackTrace();
                    }
                    int mailType = mailOrder.getMailType();
                    OperationType optType = OperationType.SEND_ITEM;
                    if (mailType == 4) {
                        optType = OperationType.DELIVER_OF_PAY;
                    }
                    try {
                        //调用发送
                        operationDispatchService.sendPackage(gameCode, areaId, goodsItemPackage, userId, roleId, null, optType);

                    } catch (Exception e) {
                        exNum = 1;
                        if (sendByType == 1) {
                            failedReason = failedReason + "," + roleId;
                        }
                        if (sendByType == 2) {
                            failedReason = failedReason + "," + userId;
                        }
                        if (sendByType == 3) {
                            String roleName = "";
                            if (gameRole != null) {
                                roleName = gameRole.getRoleName();
                            }

                            failedReason = failedReason + "," + roleName;
                        }

                        mailOrderFailure.setFailureReasons(failedReason);
                        mailOrderFailure.setRemark("邮件检测异常信息");
                        mailOrderFailureService.addMailOrderFailure(mailOrderFailure);

                    }
                }


            }


            if (exNum == 1) {
                //修改状态 4发送失败
                mailOrderTask.setFailedReason(failedReason);
                mailOrderTask.setSendStatus(4);
                //更新
                mailOrderTaskService.updMailOrderTask(mailOrderTask);
                mailOrder.setSendStatus(4);
                mailOrderService.updMailOrder(mailOrder);
            } else {
                //修改状态 3发送成功
                mailOrderTask.setFailedReason(failedReason);
                mailOrderTask.setSendStatus(3);
                //更新
                mailOrderTaskService.updMailOrderTask(mailOrderTask);
                mailOrder.setSendStatus(3);
                mailOrderService.updMailOrder(mailOrder);
            }
            System.out.println("发送。。");
        }
    }

    /**
     * 定时从数据库中取出未发送的邮件task任务来执行.
     */
    class MailOrderTimerTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("发送嘎嘎嘎嘎嘎");
            //从数据库获取未执行的task
            List<MailOrderTask> list = mailOrderTaskService.selectNotSend();
            for (MailOrderTask mailOrderTask : list) {
                System.out.println("调用");
                //超过五次的任务将不会被发送
                if (mailOrderTask.getSendCount() < 5) {
                    sendMailOrder(mailOrderTask);
                }

            }
            System.out.println("发送啊啊啊啊啊啊");
        }
    }
}
