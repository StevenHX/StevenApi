package com.steven.demo01.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.steven.demo01.domain.BaseSearchDto;
import lombok.Data;

import java.util.Date;

@Data
public class Employees extends BaseSearchDto {
    @TableId
    private String emp_no;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birth_date;
    private String first_name;
    private String last_name;
    private String gender;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date hire_date;
}
