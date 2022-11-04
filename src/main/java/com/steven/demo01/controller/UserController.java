package com.steven.demo01.controller;

import com.steven.demo01.domain.CommonResult;
import com.steven.demo01.domain.entity.User;
import com.steven.demo01.domain.ValidationGroup;
import com.steven.demo01.service.UserService;
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

    /**
     * 登录接口
     */
    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody @Validated(ValidationGroup.CustomGroup.class) User user) {
        String token = userService.login(user);
        log.info("result: {}",CommonResult.success(token));
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
