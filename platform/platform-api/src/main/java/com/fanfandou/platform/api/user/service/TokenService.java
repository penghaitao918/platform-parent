package com.fanfandou.platform.api.user.service;

import com.fanfandou.platform.api.user.entity.AuthToken;
import com.fanfandou.platform.api.user.entity.AuthTokenType;

/**
 * Description: 提供token相关的接口.
 * <p/>
 * Date: 2016-03-15 13:56.
 * author: Arvin.
 */
public interface TokenService {

    AuthToken createRefreshToken(long userId, int appId);

    AuthToken createAccessToken(AuthToken refreshToken);

    boolean verifyToken(String token, long userId);

    AuthToken createToken(long userId, int appId, AuthTokenType tokenType);

    AuthToken getToken(String token);
}
