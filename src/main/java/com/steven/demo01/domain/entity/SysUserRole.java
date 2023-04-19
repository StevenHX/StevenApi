package com.steven.demo01.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色关联
 */
@Data
public class SysUserRole implements Serializable {
    /** 用户ID */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /** 角色ID */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;
}
