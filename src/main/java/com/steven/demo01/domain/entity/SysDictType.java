package com.steven.demo01.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.steven.demo01.domain.BaseSearchDto;
import com.steven.demo01.domain.ValidationGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
public class SysDictType extends BaseSearchDto implements Serializable {
    /**
     * 字典类型id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long dictId;
    /**
     * 字典类型名称
     */
    @NotBlank(message = "字典类型名称不能为空", groups = {
            ValidationGroup.AddGroup.class
    })
    private String dictName;
    /**
     * 字典类型编码
     */
    @NotBlank(message = "字典类型编码不能为空", groups = {
            ValidationGroup.AddGroup.class
    })
    private String dictType;
    /**
     * 字典类型状态 （0正常 1停用）
     */
    private String status;

    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;
}
