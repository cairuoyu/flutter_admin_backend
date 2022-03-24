package com.cry.flutter.admin.common;

import com.cry.flutter.admin.constants.Constant;
import com.cry.flutter.admin.constants.ResponseCodeConstant;
import com.cry.flutter.admin.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2020-10-12
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisUtil redisUtil;
    private List<String> whiteList = new ArrayList<String>(){{
        add("/loginByCode");
        add("/user/login");
        add("/user/loginByFace");
        add("/user/register");
    }};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath();
        if(whiteList.contains(servletPath)){
            return true;
        }
        String method = request.getMethod();
        if (method.equals(RequestMethod.OPTIONS)) {
            return true;
        }

        response.setContentType("application/json;charset=utf-8");
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(authorization)) {
            return false;
        }
        String token = authorization;

        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            return false;
        }
        String userId = claims.getSubject();
        request.setAttribute("userId", userId);
        Long expire = redisUtil.getExpire(Constant.REDIS_TOKEN_PRE + userId);
        if (expire < 0) {
            String res = new ObjectMapper().writeValueAsString(new ResponseBodyApi<>(ResponseCodeConstant.SESSION_EXPIRE_CODE, false, ResponseCodeConstant.SESSION_EXPIRE_MESSAGE));
            response.getWriter().println(res);
            return false;
        }
        return true;
    }
}
