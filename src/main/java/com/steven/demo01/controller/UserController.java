package com.steven.demo01.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.steven.demo01.domain.BasePageResult;
import com.steven.demo01.domain.CommonResult;
import com.steven.demo01.domain.ValidationGroup;
import com.steven.demo01.domain.entity.SysRole;
import com.steven.demo01.domain.entity.SysUserRole;
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
import java.util.Set;

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
        User newUser = userService.register(user);
        // 获取新增的用户
        userService.insertUserAuth(newUser.getUserId(), user.getRoleIds());
        return CommonResult.success("新增成功");
    }

    /**
     * 修改用户
     */
    @PostMapping("/edit")
    public CommonResult<String> edit(@RequestBody @Validated(ValidationGroup.EditGroup.class) User user) {
        // 获取登录用户拥有角色
        Set<String> roles = getCurrentLoginUser().getRoles();
        long loginUserId = getCurrentLoginUser().getUser().getUserId();
        // 非超级管理员，并且用户id和修改的用户不一致，不允许修改
        if (!roles.contains("admin") && user.getUserId() != null && loginUserId != user.getUserId())
            throw new CustomException(CommonResult.error("不允许修改他人信息", ""));
        userService.edit(user);
        userService.insertUserAuth(user.getUserId(), user.getRoleIds());
        return CommonResult.success("修改成功");
    }

    /**
     * 新增用户授权角色
     */
    @PutMapping("/authRole")
    public CommonResult<String> insertAuthRole(Long userId, Long[] roleIds) {
        userService.insertUserAuth(userId, roleIds);
        return CommonResult.success("授权成功");
    }

    /**
     * 根据用户编号获取授权角色
     */
    @GetMapping("/getRolesByUserId/{userId}")
    public CommonResult<List<SysRole>> authRole(@PathVariable("userId") Long userId) {
        return CommonResult.success(roleService.selectRolesByUserId(userId));
    }

    /**
     * 根据userId获取用户详情
     */
    @GetMapping("/{userId}")
    public CommonResult<User> getUserById(@PathVariable("userId") Long userId) {
        // 获取用户详情
        User user = userService.queryUserByUserId(userId);
        // 根据userId 获取角色
        List<SysUserRole> sysUserRoleList = userService.queryRoleIdsByUserId(userId);
        Long[] rolesId = new Long[sysUserRoleList.size()];
        for (int i = 0; i < sysUserRoleList.size(); i++) {
            rolesId[i] = sysUserRoleList.get(i).getRoleId();
        }
        user.setRoleIds(rolesId);
        return CommonResult.success(user);
    }
}
