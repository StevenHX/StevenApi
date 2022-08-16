package com.steven.demo01.interceptor;

import com.alibaba.fastjson.JSON;
import com.steven.demo01.entity.CommonResult;
import com.steven.demo01.utils.JwtUtils;
import com.steven.demo01.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisUtil redisUtil;

    public Long getCurrentUid(String token) {
        try {
            return JwtUtils.getClaimsValue(token, "uid").asLong();
        } catch (Exception e) {
            return 0L;
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestMethod = "OPTIONS";
        if (StringUtils.equals(requestMethod, request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        String token = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(token)
                && JwtUtils.verifyToken(token)
                && redisUtil.get(getCurrentUid(token).toString()) != null
        ) {
            return true;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(401);
        response.getWriter().append(JSON.toJSONString(CommonResult.unVerify("")));
        return false;
    }
}
