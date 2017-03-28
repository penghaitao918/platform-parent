package com.fanfandou.platform.serv.user.service;

import com.fanfandou.common.service.cache.CacheService;
import com.fanfandou.platform.api.user.entity.AuthToken;
import com.fanfandou.platform.api.user.entity.AuthTokenType;
import com.fanfandou.platform.api.user.service.TokenService;
import com.google.common.io.BaseEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Description: token接口实现.
 * <p/>
 * Date: 2016-03-15 15:04.
 * author: Arvin.
 */
@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    @Autowired
    private CacheService cacheService;

    @Override
    public AuthToken createRefreshToken(long userId, int appId) {
        AuthToken refreshToken = new AuthToken();
        refreshToken.setCreateTime(new Date());
        refreshToken.setAppId(appId);
        refreshToken.setExpireSec(AuthTokenType.REFRESH.getExpireSec());
        refreshToken.setUserId(userId);
        refreshToken.setTokenType(AuthTokenType.REFRESH);
        refreshToken.setToken(generateToken(userId + ""));
        cacheService.put(refreshToken.getToken(), refreshToken, refreshToken.getExpireSec());
        return refreshToken;
    }

    @Override
    public AuthToken createAccessToken(AuthToken refreshToken) {
        if (!refreshToken.getTokenType().equals(AuthTokenType.REFRESH)) {
            return null;
        }
        AuthToken accessToken = new AuthToken();
        accessToken.setTokenType(AuthTokenType.ACCESS);
        accessToken.setUserId(refreshToken.getUserId());
        accessToken.setAppId(refreshToken.getAppId());
        accessToken.setExpireSec(AuthTokenType.ACCESS.getExpireSec());
        accessToken.setCreateTime(new Date());
        accessToken.setToken(new String(BaseEncoding.base64().decode(refreshToken.getToken())));
        cacheService.put(accessToken.getToken(), accessToken, accessToken.getExpireSec());
        return accessToken;
    }

    @Override
    public boolean verifyToken(String token, long userId) {
        AuthToken authToken = cacheService.get(token, AuthToken.class);
        return authToken != null && authToken.getUserId() == userId;

    }

    @Override
    public AuthToken createToken(long userId, int appId, AuthTokenType tokenType) {
        AuthToken token = new AuthToken();
        token.setUserId(userId);
        token.setAppId(appId);
        token.setToken(generateToken(userId + ""));
        token.setCreateTime(new Date());
        token.setExpireSec(tokenType.getExpireSec());
        token.setTokenType(tokenType);
        cacheService.put(token.getToken(), token, token.getExpireSec());
        return token;
    }

    @Override
    public AuthToken getToken(String token) {
        return cacheService.get(token, AuthToken.class);
    }

    /**
     * 生成token：种子hash+uuid+随机数.
     *
     * @param seed 种子
     * @return token
     */
    private String generateToken(String seed) {
        Random random = new Random();
        int prefix = Math.abs(seed.hashCode());
        String suffix = String.valueOf(random.nextLong() + 1000).substring(1, 9);
        System.out.println("prefix=" + prefix + ",suffix=" + suffix);
        String tokenSrc = prefix + UUID.randomUUID().toString().replaceAll("\\-", "") + suffix;
        return BaseEncoding.base64().encode(tokenSrc.getBytes()).replaceAll("=", "");
    }

}
