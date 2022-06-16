package com.steven.demo01.controller;

import com.steven.demo01.entity.CommonResult;
import com.steven.demo01.entity.User;
import com.steven.demo01.entity.ValidationGroup;
import com.steven.demo01.service.UserService;
import com.steven.demo01.utils.JwtUtils;
import com.steven.demo01.utils.RedisUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@Api(tags = "用户管理相关接口")
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;
    /**
     * 验证token
     */
    @PostMapping("/verify")
    public CommonResult<Boolean> verify(@RequestParam String token) {
        Boolean valid = JwtUtils.verifyToken(token);
        return CommonResult.success(valid);
    }

    /**
     * 登录接口
     */
    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody @Validated(ValidationGroup.CustomGroup.class) User user) {
        log.info("================login=============");
        log.info("token={}",redisUtil.get("token"));
        log.info("request: {}",user);
        String token = userService.login(user);
        log.info("result: {}",CommonResult.success(token));
        redisUtil.set("token", token,8L * 60L * 60L);
        return CommonResult.success(token);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public CommonResult<String> register(@RequestBody @Validated(ValidationGroup.AddGroup.class) User user) {
        userService.register(user);
        return CommonResult.success("注册成功");
    }

}
