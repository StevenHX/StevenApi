package com.steven.demo01.domain.model;

import com.steven.demo01.domain.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 缓存 登录用户
 */
@Data
public class LoginUser implements Serializable {
    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 用户角色key
     */
    private Set<String> roles;

    /**
     * 用户信息
     */
    private User user;
}
