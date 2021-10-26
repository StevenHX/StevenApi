package com.steven.demo01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.steven.demo01.bean.employees;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<employees> {

//    @Select("SELECT * FROM employees.employees where gender = 'M' and birth_date > '1965-01-31'")
//    List<User> getAllUsers();
}
