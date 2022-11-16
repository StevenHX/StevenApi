package com.steven.demo01.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.steven.demo01.domain.BasePageResult;
import com.steven.demo01.domain.CommonResult;
import com.steven.demo01.domain.ValidationGroup;
import com.steven.demo01.domain.entity.SysRole;
import com.steven.demo01.domain.entity.User;
import com.steven.demo01.service.RoleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@Api(tags = "角色相关接口")
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    @Autowired
    RoleService roleService;

    /**
     * 获取角色列表
     * @param role
     * @return
     */
    @PostMapping("/list")
    public CommonResult<BasePageResult<SysRole>> list(@RequestBody  SysRole role) {
        IPage<SysRole> page = roleService.selectRoleList(role);
        return CommonResult.success(BasePageResult.newInstance(page));
    }


    /**
     * 根据角色编号获取详细信息
     */
    @GetMapping(value = "/{roleId}")
    public CommonResult<SysRole> getInfo(@PathVariable Long roleId) {
        SysRole sysRole = roleService.selectRoleById(roleId);
        return CommonResult.success(sysRole);
    }

    /**
     * 新增角色
     */
    @PostMapping
    public CommonResult<String> add(@Validated(ValidationGroup.AddGroup.class) @RequestBody SysRole role) {
        User loginUser = getCurrentLoginUser().getUser();
        role.setCreateBy(loginUser.getUserName());
        role.setCreateTime(new Date());
        roleService.addRole(role);
        return CommonResult.success("新增成功");
    }

    /**
     * 修改保存角色
     */
    @PutMapping
    public CommonResult<String> edit(@Validated({ValidationGroup.EditGroup.class}) @RequestBody SysRole role) {
        User loginUser = getCurrentLoginUser().getUser();
        role.setUpdateBy(loginUser.getUserName());
        role.setUpdateTime(new Date());
        roleService.editRole(role);
        return CommonResult.success("修改成功");
    }


    /**
     * 删除角色
     */
//    @DeleteMapping("/{roleIds}")
//    public CommonResult<String> remove(@PathVariable Long[] roleIds) {
//        return CommonResult.success("删除成功");
//    }

}
