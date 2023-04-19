package com.steven.demo01.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色和菜单关联
 */
@Data
public class SysRoleMenu implements Serializable {
    /** 角色ID */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    /** 菜单ID */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long menuId;
}
