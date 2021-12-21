package com.steven.demo01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.steven.demo01.entity.Employees;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employees> {

}
