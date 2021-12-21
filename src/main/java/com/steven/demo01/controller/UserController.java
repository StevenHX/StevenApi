package com.steven.demo01.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.steven.demo01.bean.BasePageResult;
import com.steven.demo01.bean.CommonResult;
import com.steven.demo01.bean.employees;
import com.steven.demo01.dto.EmployeeDto;
import com.steven.demo01.service.UserService;
import com.steven.demo01.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "用户管理相关接口")
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 处理 /user/ GET请求
     *
     * @return
     */
    @GetMapping("/")
    @ApiOperation("查询用户的接口")
    public List<employees> getUserList() {
        System.out.println(userService.getAllUsers());
        return userService.getAllUsers();
    }

    @PostMapping("/")
    @ApiOperation("分页查询用户的接口")
    public CommonResult<BasePageResult<employees>> postPageAllUses(@RequestBody EmployeeDto employee) {
        IPage<employees> page = userService.pageGetAllUses(employee);
        return CommonResult.success(BasePageResult.newInstance(page));
    }

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
    public CommonResult<String> login(@RequestParam String username,@RequestParam String password) {
        Long userId = 10001L;
        String token = JwtUtils.makeToken(userId);
        return CommonResult.success(token);
    }
}
