package com.steven.demo01.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class BaseSearchDto {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)//不为空则显示，要不然就不展示了
    @TableField(exist = false)// 表中无此字段标识表中无此字段
    private Long pageNum;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Long pageSize;
}
