package com.steven.demo01.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.steven.demo01.domain.BaseSearchDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysRole extends BaseSearchDto implements Serializable {
    @TableId
    private Long roleId;
    private String roleName;
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
