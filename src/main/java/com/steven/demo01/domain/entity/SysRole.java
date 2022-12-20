package com.steven.demo01.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.steven.demo01.domain.BaseSearchDto;
import com.steven.demo01.domain.ValidationGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class SysRole extends BaseSearchDto implements Serializable {
    @TableId
    private Long roleId;
    @NotBlank(message = "角色名不能为空", groups = {
            ValidationGroup.AddGroup.class
    })
    private String roleName;
    @NotBlank(message = "角色key不能为空", groups = {
            ValidationGroup.AddGroup.class
    })
    private String roleKey;
    private Integer roleSort;
    private String dataScope;
    private Integer menuCheckStrictly;
    private Integer deptCheckStrictly;
    private String status;
    private String delFlag;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    @NotEmpty(message = "菜单不能为空", groups = {
            ValidationGroup.AddGroup.class,
            ValidationGroup.EditGroup.class
    })
    private List<Long> menuIdList;
}
