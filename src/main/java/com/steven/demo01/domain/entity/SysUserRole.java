package com.steven.demo01.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色关联
 */
@Data
public class SysUserRole implements Serializable {
    /** 用户ID */
    private Long userId;

    /** 角色ID */
    private Long roleId;
}
