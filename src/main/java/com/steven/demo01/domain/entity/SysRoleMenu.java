package com.steven.demo01.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色和菜单关联
 */
@Data
public class SysRoleMenu implements Serializable {
    /** 角色ID */
    private Long roleId;

    /** 菜单ID */
    private Long menuId;
}
