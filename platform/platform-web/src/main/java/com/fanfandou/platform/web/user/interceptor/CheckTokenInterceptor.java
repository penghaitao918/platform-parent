package com.fanfandou.platform.web.user.interceptor;

import com.fanfandou.platform.api.user.exception.UserException;
import com.fanfandou.platform.api.user.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.StringTokenizer;

/**
 * Created by wudi.
 * Descreption:token验证拦截器.
 * Date:2016/5/20
 */
public class CheckTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        String accessToken = httpServletRequest.getParameter("accessToken");

        Long userId = 0L;
        if (httpServletRequest.getParameter("userId") != null) {
            userId = Long.parseLong(httpServletRequest.getParameter("userId"));
        }

        /*if (!accessToken.equals("1568738716b5a8de4cf6ac8d7ca1e5ad525984729413")) {
            System.out.println("token = " + accessToken);
            throw new UserException(UserException.USER_TOKEN_INVALID, "accessToken无效");
        }*/

        if (accessToken == null || !tokenService.verifyToken(accessToken, userId)) {
            throw new UserException(UserException.USER_TOKEN_INVALID, "accessToken无效");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}
