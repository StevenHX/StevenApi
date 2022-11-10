package com.steven.demo01.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.steven.demo01.domain.BasePageResult;
import com.steven.demo01.domain.CommonResult;
import com.steven.demo01.domain.ValidationGroup;
import com.steven.demo01.domain.entity.User;
import com.steven.demo01.exception.CustomException;
import com.steven.demo01.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "用户管理相关接口")
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;


    /**
     * 获取用户列表 分页
     */
    @PostMapping("/list")
    public CommonResult<BasePageResult<User>> list(@RequestBody User user) {
        IPage<User> page = userService.selectUserList(user);
        return CommonResult.success(BasePageResult.newInstance(page));
    }

    /**
     * 新增用户
     */
    @PostMapping("/add")
    public CommonResult<String> add(@RequestBody @Validated(ValidationGroup.AddGroup.class) User user) {
        userService.register(user);
        return CommonResult.success("新增成功");
    }

    /**
     * 修改用户
     */
    @PostMapping("/edit")
    public CommonResult<String> edit(@RequestBody @Validated(ValidationGroup.EditGroup.class) User user) {
        long loginUserId =  getCurrentLoginUser().getUser().getUserId();
        if (user.getUserId() != null && loginUserId != user.getUserId()) throw new CustomException(CommonResult.error("不允许修改他人信息",""));
        user.setUserId(loginUserId);
        userService.edit(user);
        return CommonResult.success("修改成功");
    }
}
