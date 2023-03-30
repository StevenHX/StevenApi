package com.steven.demo01.domain.entity;

import com.steven.demo01.domain.BaseSearchDto;
import com.steven.demo01.domain.ValidationGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
public class SysDictData extends BaseSearchDto implements Serializable {
    /**
     * 字典数据id
     */
    private Long dictDataId;
    /**
     * 字典类型编码
     */
    @NotBlank(message = "字典类型编码不能为空", groups = {
            ValidationGroup.AddGroup.class,
            ValidationGroup.CustomGroup.class
    })
    private String dictType;
    /**
     * 字典类型编码
     */
    @NotBlank(message = "字典类型名称不能为空", groups = {
            ValidationGroup.AddGroup.class
    })
    private String dictName;
    /**
     * 字典排序
     */
    private Integer dictSort;
    /**
     * 字典数据名称
     */
    @NotBlank(message = "字典数据名称不能为空", groups = {
            ValidationGroup.AddGroup.class
    })
    private String dictLabel;
    /**
     * 字典数据编码
     */
    @NotBlank(message = "字典数据编码不能为空", groups = {
            ValidationGroup.AddGroup.class
    })
    private String dictValue;
    /**
     * 样式属性（其他样式扩展）
     */
    private String cssClass;
    /**
     * 表格字典样式
     */
    private String listClass;
    /**
     * 是否默认（Y是 N否）
     */
    private String isDefault;
    /**
     * 状态（0正常 1停用）
     */
    private String status;

    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;
}
