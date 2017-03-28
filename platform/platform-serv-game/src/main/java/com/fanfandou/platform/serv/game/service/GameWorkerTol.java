package com.fanfandou.platform.serv.game.service;

import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.util.SpringUtils;
import com.fanfandou.platform.api.game.entity.GameArea;
import com.fanfandou.platform.api.game.entity.GameOperation;
import com.fanfandou.platform.api.game.entity.GameRole;
import com.fanfandou.platform.api.game.entity.GameToy;
import com.fanfandou.platform.api.game.entity.Message;
import com.fanfandou.platform.api.game.exception.GameException;
import com.fanfandou.platform.api.game.service.GameAreaService;
import com.fanfandou.platform.api.game.service.GameRoleService;
import com.fanfandou.platform.api.game.service.GameToyService;
import com.fanfandou.platform.api.game.service.OperationDispatchService;
import com.fanfandou.platform.api.user.service.UserService;
import com.fanfandou.platform.serv.game.entity.tol.CommonMsgType;
import com.fanfandou.platform.serv.game.entity.tol.account.AccountRes;
import com.fanfandou.platform.serv.game.entity.tol.account.Msg_Account2Center_CheckToyActivate_Res;
import com.fanfandou.platform.serv.game.entity.tol.account.Msg_Account2Center_Create_Player_Res;
import com.fanfandou.platform.serv.game.entity.tol.account.Msg_Account2Center_ToyActivate_Res;
import com.fanfandou.platform.serv.game.entity.tol.account.Msg_Center2Account_CheckToyActivate_Req;
import com.fanfandou.platform.serv.game.entity.tol.account.Msg_Center2Account_Create_Player_Req;
import com.fanfandou.platform.serv.game.entity.tol.account.Msg_Center2Account_Login_Req;
import com.fanfandou.platform.serv.game.entity.tol.account.Msg_Center2Account_Logout_Req;
import com.fanfandou.platform.serv.game.entity.tol.account.Msg_Center2Account_ToyActivate_Req;
import com.fanfandou.platform.serv.game.entity.tol.gm.GMErrorCode;
import com.fanfandou.platform.serv.game.entity.tol.gm.Msg_Logic2Gm_Deposit_Res;
import com.fanfandou.platform.serv.game.entity.tol.gm.Msg_Logic2Gm_Mail_Res;
import com.fanfandou.platform.serv.game.entity.tol.gm.Msg_Logic2Gm_Notice_Res;
import com.fanfandou.platform.serv.game.entity.tol.gm.Msg_Logic2Gm_Shop_Res;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description: tol game worker.
 * <p/>
 * Date: 2016-06-14 17:59.
 * author: Arvin.
 */
public class GameWorkerTol extends AbstractGameWorker {
    private GameToyService gameToyService;
    private OperationDispatchService operationDispatchService;
    private GameRoleService gameRoleService;
    private UserService userService;
    private GameAreaService gameAreaService;

    /**
     * constructor.
     */
    public GameWorkerTol(GameCode gameCode, int areaId) {
        this.gameCode = gameCode;
        this.areaId = areaId;
        init();
    }

    //可以处理一些参数的初始化
    private void init() {
        logger.info("GameWorkerTol:" + SpringUtils.getBean("gameToyService"));
        gameToyService = (GameToyService) SpringUtils.getBean("gameToyService");
        operationDispatchService = (OperationDispatchService) SpringUtils.getBean("operationDispatchService");
        gameRoleService = (GameRoleService) SpringUtils.getBean("gameRoleService");
        userService = (UserService) SpringUtils.getBean("userService");
        gameAreaService = (GameAreaService) SpringUtils.getBean("gameAreaService");

    }

    @Override
    protected Runnable getWorkThread(Message msg) {
        CommonMsgType msgType = CommonMsgType.valueOf(msg.getProtocolType());
        if (msgType == null) {
            return null;
        }
        switch (msgType) {
            case MSG_CENTER2ACCOUNT_TOYACTIVATE_REQ:
                return new ToyActivateThread((Msg_Center2Account_ToyActivate_Req) msg.getMsgBody());
            case MSG_CENTER2ACCOUNT_CHECKTOYACTIVATE_REQ:
                return new ToyCheckThread((Msg_Center2Account_CheckToyActivate_Req) msg.getMsgBody());
            case MSG_CENTER2ACCOUNT_CREATE_PLAYER_REQ:
                return new ToyCreateThread((Msg_Center2Account_Create_Player_Req) msg.getMsgBody());
            case MSG_CENTER2ACCOUNT_LOGIN_REQ:
                return new RoleEventThread((Msg_Center2Account_Login_Req) msg.getMsgBody());
            case MSG_CENTER2ACCOUNT_LOGOUT_REQ:
                return new RoleEventThread((Msg_Center2Account_Logout_Req) msg.getMsgBody());
            case MSG_LOGIC2GM_DEPOSIT_RES:
                return new DepositResThread((Msg_Logic2Gm_Deposit_Res) msg.getMsgBody());
            case MSG_LOGIC2GM_NOTICE_RES:
                return new NoticeResThread((Msg_Logic2Gm_Notice_Res) msg.getMsgBody());
            case MSG_LOGIC2GM_MAIL_RES:
                return new MailResThread((Msg_Logic2Gm_Mail_Res) msg.getMsgBody());
            case MSG_LOGIC2GM_SHOP_RES:
                return new GemShopResThread((Msg_Logic2Gm_Shop_Res) msg.getMsgBody());
            default:
                return null;

        }
    }

    /**
     * 处理游戏服发过来的角色事件（登录、登出）.
     */
    class RoleEventThread implements Runnable {

        private Msg_Center2Account_Login_Req loginReq;
        private Msg_Center2Account_Logout_Req logoutReq;

        public RoleEventThread(Msg_Center2Account_Login_Req loginReq) {
            this.loginReq = loginReq;
        }

        public RoleEventThread(Msg_Center2Account_Logout_Req logoutReq) {
            this.logoutReq = logoutReq;
        }

        @Override
        public void run() {
            logger.info("RoleEventThread start");
            GameArea gameArea = null;
            if (loginReq != null) {
                logger.info("RoleEventThread loginReq start");
                try {
                    logger.info("RoleEventThread loginReq get gameArea " + loginReq.getMServerId());
                    gameArea = gameAreaService.getGameAreaByCode(gameCode.getId(), loginReq.getMServerId() + "");
                } catch (ServiceException e) {
                    logger.error("RoleEventThread -> get area failed.", e);
                }

                if (gameArea != null) {
                    logger.info("RoleEventThread loginReq get getRoleByUserId areaId = {}, gameCode = {}, userId = {}", gameArea.getId(), gameCode.getId(), loginReq.getMAccountId());
                    GameRole gameRole = gameRoleService.getRoleByUserId(gameCode, gameArea.getId(), loginReq.getMAccountId());
                    try {
                        if (gameRole == null) {
                            gameRole = new GameRole();
                            gameRole.setCreateTime(new Date());
                            gameRole.setLastLoginTime(new Date(loginReq.getMTimeLogin() * 1000));
                            gameRole.setAreaId(areaId);
                            gameRole.setGameId(gameCode.getId());
                            gameRole.setUserId((long) loginReq.getMAccountId());
                            logger.info("RoleEventThread loginReq get createRole");
                            gameRoleService.createRole(gameRole, gameCode);
                            logger.info("RoleEventThread loginReq get userOnline");
                        }
                        logger.info("RoleEventThread loginReq 开始记录角色上线");
                        userService.userOnline(gameRole.getUserId(),
                                new Date(loginReq.getMTimeLogin() * 1000), gameArea.getId());

                    } catch (ServiceException e) {
                        logger.error("RoleEventThread -> create role failed.", e);
                    }
                }
            } else if (logoutReq != null) {
                logger.info("RoleEventThread logoutReq start");
                try {
                    logger.info("RoleEventThread logoutReq get getGameAreaByCode");
                    gameArea = gameAreaService.getGameAreaByCode(gameCode.getId(), logoutReq.getMServerId() + "");
                } catch (ServiceException e) {
                    logger.error("RoleEventThread -> get area failed.", e);
                }
                if (gameArea != null) {
                    logger.info("RoleEventThread logoutReq get getRoleByUserId");
                    GameRole gameRole = gameRoleService.getRoleByUserId(gameCode,
                            gameArea.getId(), logoutReq.getMAccountId());
                    if (gameRole != null) {
                        gameRole.setLastLogoutTime(new Date(logoutReq.getMTimeLogout() * 1000));
                        gameRole.setRoleHeadIcon(logoutReq.getMHead() + "");
                        gameRole.setRoleName(logoutReq.getMName());
                        gameRole.setRoleLevel(logoutReq.getMSummonerLv());
                        logger.info("RoleEventThread logoutReq get updateRole");
                        gameRoleService.updateRole(gameRole);
                    }
                    logger.info("RoleEventThread logoutReq get userOffline");
                    userService.userOffline(logoutReq.getMAccountId(),
                            new Date(logoutReq.getMTimeLogout() * 1000), gameArea.getId());
                }
            }
        }
    }

    class ToyActivateThread implements Runnable {

        private Msg_Center2Account_ToyActivate_Req toyActivateReq;

        public ToyActivateThread(Msg_Center2Account_ToyActivate_Req toyActivateReq) {
            this.toyActivateReq = toyActivateReq;
        }

        @Override
        public void run() {
            //调用toyservice中的玩具绑定
            AccountRes accountRes = AccountRes.AccountRes_ToyActivate_Success;
            try {
                gameToyService.bindToUser(Long.valueOf(toyActivateReq.getMToyGuid()), toyActivateReq.getMToyType(),
                        toyActivateReq.getMOperatorAccountId(), 0, 1);
            } catch (ServiceException e) {
                if (e.getId() == GameException.GAME_TOY_TYPE_ERROR) {
                    accountRes = AccountRes.AccountRes_ToyActivate_TypeError;
                } else if (e.getId() == GameException.GAME_TOY_CODE_BOUND) {
                    accountRes = AccountRes.AccountRes_ToyActivate_OthersBound;
                } else if (e.getId() == GameException.GAME_TOY_CODE_BOUND_FOR_SELF) {
                    accountRes = AccountRes.AccountRes_ToyActivate_SelfBound;
                } else if (e.getId() == GameException.GAME_TOY_GUID_ERROR) {
                    accountRes = AccountRes.AccountRes_ToyActivate_GuidError;
                } else {
                    accountRes = AccountRes.AccountRes_ToyActivate_BindError;
                }
            }
            logger.info("ToyActivateThread >>>>>> accountRes = " + accountRes);
            Msg_Account2Center_ToyActivate_Res toyActivateRes = new
                    Msg_Account2Center_ToyActivate_Res(toyActivateReq.getMOperatorAccountId(),
                    toyActivateReq.getMOperatorServerId(), toyActivateReq.getMToyGuid(), toyActivateReq.getMToyType(),
                    accountRes.getNumber());
            Message<Msg_Account2Center_ToyActivate_Res> response
                    = new Message<Msg_Account2Center_ToyActivate_Res>("", toyActivateRes, CommonMsgType.MSG_ACCOUNT2CENTER_TOYACTIVATE_RES.getId());
            sendMsg(response);

        }
    }

    class ToyCheckThread implements Runnable {

        private Msg_Center2Account_CheckToyActivate_Req toyCheckReq;

        public ToyCheckThread(Msg_Center2Account_CheckToyActivate_Req toyCheckReq) {
            this.toyCheckReq = toyCheckReq;
        }

        @Override
        public void run() {
            //调用toyservcie中的玩具校验
            AccountRes accountRes = AccountRes.AccountRes_ToyCheck_Success;
            long bindUid = 0;
            try {
                gameToyService.verifyToy(Long.valueOf(toyCheckReq.getMToyGuid()), toyCheckReq.getMToyType(), toyCheckReq.getMOperatorAccountId(),
                        0, 1);

            } catch (ServiceException e) {
                if (e.getId() == GameException.GAME_TOY_CHECK_OTHER_BOUND) {
                    accountRes = AccountRes.AccountRes_ToyCheck_OthersBound;
                } else if (e.getId() == GameException.GAME_TOY_GUID_ERROR) {
                    accountRes = AccountRes.AccountRes_ToyCheck_GuidError;
                } else if (e.getId() == GameException.GAME_TOY_TYPE_ERROR) {
                    accountRes = AccountRes.AccountRes_ToyCheck_TypeError;
                } else if (e.getId() == GameException.GAME_TOY_CODE_BOUND_FOR_SELF) {
                    accountRes = AccountRes.AccountRes_ToyCheck_SelfBound;
                }
            }

            GameToy gameToy = gameToyService.getGameToy(Long.valueOf(toyCheckReq.getMToyGuid()));
            if (gameToy != null) {
                bindUid = gameToy.getBindUid();
            }
            logger.info("ToyCheckThread >>>>>> accountRes = {}, bindUid = {}", accountRes, bindUid);
            Msg_Account2Center_CheckToyActivate_Res toyCheckRes =
                    new Msg_Account2Center_CheckToyActivate_Res(toyCheckReq.getMOperatorAccountId(),
                            toyCheckReq.getMOperatorServerId(), toyCheckReq.getMToyGuid(), toyCheckReq.getMToyType(),
                            (int) bindUid, accountRes.getNumber());

            Message<Msg_Account2Center_CheckToyActivate_Res> response
                    = new Message<Msg_Account2Center_CheckToyActivate_Res>("", toyCheckRes, CommonMsgType.MSG_ACCOUNT2CENTER_CHECKTOYACTIVATE_RES.getId());
            sendMsg(response);
        }
    }


    class ToyCreateThread implements Runnable {

        private Msg_Center2Account_Create_Player_Req createPlayeReq;

        private ToyCreateThread(Msg_Center2Account_Create_Player_Req createPlayeReq) {
            this.createPlayeReq = createPlayeReq;
        }

        @Override
        public void run() {
            AccountRes accountRes = AccountRes.AccountRes_CreatePlayer_ToyExists;
            List<Integer> toyTypes = new ArrayList<>();
            logger.info("createPlayeReq operatorAccountId = {}, reateToyType = {}",
                    createPlayeReq.getMOperatorAccountId(), createPlayeReq.getMCreateToyType());
            try {
                toyTypes = gameToyService.getActedToyType(createPlayeReq.getMOperatorAccountId(), createPlayeReq.getMCreateToyType());
                if (toyTypes.size() == 0) {
                    accountRes = AccountRes.AccountRes_CreatePlayer_ToyNotExists;
                }
            } catch (ServiceException e) {
                logger.error("ToyCreateThread error", e);
            }
            Msg_Account2Center_Create_Player_Res createPlayerRes =
                    new Msg_Account2Center_Create_Player_Res(createPlayeReq.getMOperatorAccountId(),
                            createPlayeReq.getMOperatorServerId(), createPlayeReq.getMName(),
                            createPlayeReq.getMCreateToyType(), accountRes.getNumber());

            logger.info("ToyCreateThread toyTypeSize = {}", toyTypes.size());

            createPlayerRes.getMActivateToyTypeList().addAll(toyTypes);

            logger.info("ToyCreateThread ActivateToyTypeListSize = {}", createPlayerRes.getMActivateToyTypeList().size());

            Message<Msg_Account2Center_Create_Player_Res> response =
                    new Message<Msg_Account2Center_Create_Player_Res>("", createPlayerRes,
                            CommonMsgType.MSG_ACCOUNT2CENTER_CREATE_PLAYER_RES.getId());
            logger.info("ToyCreateThread response id = {}", response.getMsgId());

            sendMsg(response);
        }
    }

    /**
     * 充值宝石回调.
     */
    class DepositResThread implements Runnable {
        private Msg_Logic2Gm_Deposit_Res depositRes;

        private DepositResThread(Msg_Logic2Gm_Deposit_Res depositRes) {
            this.depositRes = depositRes;
        }

        @Override
        public void run() {
            int resultCode = depositRes.getMRes();
            logger.info("DepositResThread >>>>>> deleverPayResult = " + resultCode);
            if (resultCode != GMErrorCode.GMErrorCode_Deposit_Success.getNumber()) {
                logger.error("DepositResThread", "errror:" + GameException.PAY_DILEVER_FAIL);
            } else {
                completeOperation(depositRes.getMSerialNumber());
            }

        }
    }

    class MailResThread implements Runnable {
        private Msg_Logic2Gm_Mail_Res mailRes;

        private MailResThread(Msg_Logic2Gm_Mail_Res mailRes) {
            this.mailRes = mailRes;
        }

        @Override
        public void run() {
            int resultCode = mailRes.getMRes();
            logger.info("MailResThread >>>>>> mailRes = " + resultCode);
            if (resultCode != GMErrorCode.GMErrorCode_Mail_Success.getNumber()) {
                logger.error("MailResThread", "errror:" + GameException.MAIL_DELEBER_FAIL);
            } else {
                completeOperation(mailRes.getMSerialNumber());
            }
        }
    }

    class GemShopResThread implements Runnable {
        private Msg_Logic2Gm_Shop_Res shopRes;

        private GemShopResThread(Msg_Logic2Gm_Shop_Res shopRes) {
            this.shopRes = shopRes;
        }

        @Override
        public void run() {
            int resultCode = shopRes.getMRes();
            logger.info("GemShopResThread >>>>>> shopRes = " + resultCode);
            if (resultCode != GMErrorCode.GMErrorCode_Shop_Success.getNumber()) {
                logger.error("GemShopResThread", "errror:" + GameException.PAY_SHOP_GEM_FAIL);
            } else {
                completeOperation(shopRes.getMSerialNumber());
            }
        }
    }

    /**
     * 发送滚动公告(跑马灯)回调.
     */
    class NoticeResThread implements Runnable {
        private Msg_Logic2Gm_Notice_Res noticeRes;

        private NoticeResThread(Msg_Logic2Gm_Notice_Res noticeRes) {
            this.noticeRes = noticeRes;
        }

        @Override
        public void run() {
            int resultCode = noticeRes.getMRet();
            logger.info("NoticeResThread >>>>>> NoticRes = " + resultCode);
            if (resultCode != GMErrorCode.GMErrorCode_Success.getNumber()) {
                logger.error("NoticeResThread", "errror:" + GameException.GAME_NOTICE_ERROR);
            }
        }
    }

    private void completeOperation(String optId) {
        GameOperation operation = new GameOperation();
        operation.setOptId(Long.valueOf(optId));
        operationDispatchService.completeOperation(operation);
    }

    private void sendMsg(Message response) {
        try {
            operationDispatchService.sendMsg(gameCode, areaId, response);
        } catch (GameException e) {
            logger.error("GameWorkerTol.sendMsg -> send response failed!", e);
        }
    }
}
