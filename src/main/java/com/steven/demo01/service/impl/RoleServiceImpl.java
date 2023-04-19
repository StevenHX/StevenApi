package com.steven.demo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.steven.demo01.domain.CommonResult;
import com.steven.demo01.domain.entity.SysRole;
import com.steven.demo01.domain.entity.SysRoleMenu;
import com.steven.demo01.exception.CustomException;
import com.steven.demo01.mapper.RoleMapper;
import com.steven.demo01.mapper.SysRoleMenuMapper;
import com.steven.demo01.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    SysRoleMenuMapper roleMenuMapper;

    @Override
    public SysRole selectRoleById(Long roleId) {
        return roleMapper.selectRoleById(roleId);
    }

    @Override
    public IPage<SysRole> selectRoleList(SysRole sysRole) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(sysRole.getRoleName())) {
            queryWrapper.like("role_name", sysRole.getRoleName());
        }
        if (StringUtils.isNotBlank(sysRole.getStatus())) {
            queryWrapper.eq("status", sysRole.getStatus());
        }
        IPage<SysRole> page = new Page<>(sysRole.getPageNum(), sysRole.getPageSize());
        return roleMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void addRole(SysRole sysRole) {
        SysRole role = roleMapper.selectRoleByName(sysRole.getRoleName());
        if (role != null) {
            throw new CustomException(CommonResult.error("该角色已存在", ""));
        }
        roleMapper.insert(sysRole);
        // 添加 角色和菜单关系表数据
        addRoleMenu(sysRole);
    }

    @Override
    public void editRole(SysRole sysRole) {
        UpdateWrapper<SysRole> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("role_id", sysRole.getRoleId());
        int rows = roleMapper.update(sysRole, updateWrapper);
        if (rows == 0) throw new CustomException(CommonResult.error("修改失败", ""));
        // 清除角色和菜单关系
        roleMenuMapper.deleteRoleMenuByRoleId(sysRole.getRoleId());
        // 新增角色和菜单关系
        addRoleMenu(sysRole);
    }

    @Override
    public List<SysRole> selectRolesByUserId(Long userId) {
        return roleMapper.selectRolesByUserId(userId);
    }

    /**
     * 添加角色和菜单关系表记录
     * @param sysRole
     */
    private void addRoleMenu(SysRole sysRole) {
        List<SysRoleMenu> sysRoleMenuList = new ArrayList<>();
        sysRole.getMenuIdList().forEach(item -> {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(sysRole.getRoleId());
            sysRoleMenu.setMenuId(Long.valueOf(item));
            sysRoleMenuList.add(sysRoleMenu);
        });
        roleMenuMapper.batchRoleMenu(sysRoleMenuList);
    }


}
