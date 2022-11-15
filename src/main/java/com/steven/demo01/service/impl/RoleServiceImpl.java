package com.steven.demo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.steven.demo01.domain.entity.SysRole;
import com.steven.demo01.mapper.RoleMapper;
import com.steven.demo01.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

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
}
