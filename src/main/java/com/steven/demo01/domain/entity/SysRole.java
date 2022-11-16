package com.steven.demo01.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.steven.demo01.domain.BaseSearchDto;
import com.steven.demo01.domain.ValidationGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
public class SysRole extends BaseSearchDto implements Serializable {
    @TableId
    private Long roleId;
    @NotBlank(message = "角色名不能为空", groups = {
            ValidationGroup.AddGroup.class,
            ValidationGroup.EditGroup.class
    })
    private String roleName;
    @NotBlank(message = "角色key不能为空", groups = {
            ValidationGroup.AddGroup.class,
            ValidationGroup.EditGroup.class
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
}
