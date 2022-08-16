package com.steven.demo01.controller;

import com.steven.demo01.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class BaseController {

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
            return JwtUtils.getClaimsValue(getCurrentToken(), "uid").asLong();
        } catch (Exception e) {
            return 0L;
        }
    }
}
