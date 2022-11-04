package com.steven.demo01.controller;

import com.steven.demo01.constant.CacheConstants;
import com.steven.demo01.constant.Constants;
import com.steven.demo01.domain.model.LoginUser;
import com.steven.demo01.utils.JwtUtils;
import com.steven.demo01.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class BaseController {
    @Autowired
    private RedisUtil redisUtil;

    public String getCurrentToken() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            return request.getHeader("Authorization");
        } catch (Exception e) {
            return "";
        }
    }

    public Long getCurrentUid() {
        try {
            return JwtUtils.getClaimsValue(getCurrentToken(), Constants.LOGIN_USER_KEY).asLong();
        } catch (Exception e) {
            return 0L;
        }
    }

    public LoginUser getCurrentLoginUser() {
        Object object = redisUtil.get(getCurrentToken());
        return (LoginUser)object;
    }
}
