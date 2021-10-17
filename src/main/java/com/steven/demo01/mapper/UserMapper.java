package com.steven.demo01.mapper;

import com.steven.demo01.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM employees.employees where gender = 'M' and birth_date > '1965-01-31'")
    List<User> getAllUsers();
}
