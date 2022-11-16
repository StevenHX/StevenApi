package com.steven.demo01.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.steven.demo01.domain.entity.SysRole;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    /**
     * 通过角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    SysRole selectRoleById(Long roleId);

    IPage<SysRole> selectRoleList(SysRole sysRole);

    void addRole(SysRole sysRole);
    void editRole(SysRole sysRole);
}
