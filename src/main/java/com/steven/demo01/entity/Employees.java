package com.steven.demo01.entity;

import lombok.Data;

@Data
public class Employees extends BaseSearchDto {
    private String emp_no;
    private String birth_date;
    private String first_name;
    private String last_name;
    private String gender;
    private String hire_date;
}
