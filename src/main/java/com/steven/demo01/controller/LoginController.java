package com.steven.demo01.controller;

import com.steven.demo01.domain.CommonResult;
import com.steven.demo01.domain.ValidationGroup;
import com.steven.demo01.domain.entity.User;
import com.steven.demo01.domain.model.LoginInfo;
import com.steven.demo01.domain.model.LoginUser;
import com.steven.demo01.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(tags = "登录相关接口")
@RequestMapping(value = "/login")
public class LoginController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 登录接口
     */
    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody @Validated(ValidationGroup.CustomGroup.class) User user) {
        String token = userService.login(user);
        log.info("result: {}", CommonResult.success(token));
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


    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public CommonResult<LoginInfo> getInfo() {
        // 获取redis中缓存的当前登录人信息
        LoginUser loginUser = getCurrentLoginUser();
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setLoginUser(loginUser);
        return CommonResult.success(loginInfo);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public CommonResult<String> getRouters() {
        return CommonResult.success("ok");
    }
}
