package com.steven.demo01.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.steven.demo01.domain.BasePageResult;
import com.steven.demo01.domain.CommonResult;
import com.steven.demo01.domain.ValidationGroup;
import com.steven.demo01.domain.entity.SysRole;
import com.steven.demo01.domain.entity.User;
import com.steven.demo01.exception.CustomException;
import com.steven.demo01.service.RoleService;
import com.steven.demo01.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "用户管理相关接口")
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
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
    /**
     * 新增用户授权角色
     */
    @PutMapping("/authRole")
    public CommonResult<String> insertAuthRole(Long userId, Long[] roleIds)
    {
        userService.insertUserAuth(userId,roleIds);
        return CommonResult.success("授权成功");
    }
    /**
     * 根据用户编号获取授权角色
     */
    @GetMapping("/authRole/{userId}")
    public CommonResult<List<SysRole>> authRole(@PathVariable("userId") Long userId)
    {
        return  CommonResult.success(roleService.selectRolesByUserId(userId));
    }
}
