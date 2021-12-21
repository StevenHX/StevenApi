package com.steven.demo01.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.steven.demo01.entity.Employees;
import com.steven.demo01.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public IPage<Employees> pageGetAllEmployees(Employees queryParam) {
        IPage<Employees> pages = new Page<>(queryParam.getPageNum(), queryParam.getPageSize());
//        QueryWrapper<Employees> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("emp_no",queryParam.getEmp_no());
//        queryWrapper.eq("birth_date",queryParam.getBirth_date());
//        queryWrapper.eq("first_name",queryParam.getFirst_name());
//        queryWrapper.eq("last_name",queryParam.getLast_name());
//        queryWrapper.eq("gender",queryParam.getGender());
//        queryWrapper.eq("hire_date",queryParam.getHire_date());
        return employeeMapper.selectPage(pages,null/*, queryWrapper*/);
    }
}
