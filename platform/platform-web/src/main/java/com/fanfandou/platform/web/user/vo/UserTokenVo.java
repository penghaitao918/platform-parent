package com.fanfandou.platform.web.user.vo;

import com.fanfandou.platform.api.user.entity.PlatformUser;
import com.fanfandou.platform.web.game.vo.GameAreaRoleVo;

/**
 * Description: 用户登录信息.
 * <p/>
 * Date: 2016-03-31 14:51.
 * author: Arvin.
 */
public class UserTokenVo {

    private long userId;
    private long accountId;
    private int siteId;
    private int gameId;
    private String accessToken;
    private String refreshToken;
    private String accountName;
    private String nickName;
    private GameAreaRoleVo areaRoleProps;
    private String gameVersionInfos;


    public UserTokenVo() {
    }

    /**
     * 将user转为vo.
     *
     * @param user vo
     */
    public UserTokenVo(PlatformUser user) {
        if (user.getCurrentAccount() != null) {
            this.accountId = user.getCurrentAccount().getAccountId();
            this.accountName = user.getCurrentAccount().getAccountName();
        }
        this.userId = user.getUserId();
        this.siteId = user.getUserProfile().getSiteId();
        this.gameId = user.getUserProfile().getGameId();
        this.nickName = user.getUserProfile().getNickName();
        this.refreshToken = user.getRefreshToken();

    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public GameAreaRoleVo getAreaRoleProps() {
        return areaRoleProps;
    }

    public void setAreaRoleProps(GameAreaRoleVo areaRoleProps) {
        this.areaRoleProps = areaRoleProps;
    }

    public String getGameVersionInfos() {
        return gameVersionInfos;
    }

    public void setGameVersionInfos(String gameVersionInfos) {
        this.gameVersionInfos = gameVersionInfos;
    }
}
