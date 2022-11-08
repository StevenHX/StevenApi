package com.steven.demo01.controller;

import com.steven.demo01.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "用户管理相关接口")
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
}
