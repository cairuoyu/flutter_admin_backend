package com.cry.flutter.admin.common;

import com.cry.flutter.admin.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath();
        if (servletPath.indexOf("loginByCode") > 0 || servletPath.indexOf("user/login") > 0) {
            return true;
        }
        String method = request.getMethod();
        if (method.equals(RequestMethod.OPTIONS)) {
            return true;
        }

        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(authorization)) {
            String token = authorization;

            Claims claims = null;
            try {
                claims = JwtUtil.parseJWT(token);
            } catch (Exception e) {
                return true;
            }
            String userId = claims.getSubject();
            request.setAttribute("userId", userId);
            return true;
        }

        return super.preHandle(request, response, handler);
    }


}
