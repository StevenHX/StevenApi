package com.steven.demo01.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
        QueryWrapper<Employees> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("gender",queryParam.getGender())
                .or()
                .eq("emp_no",queryParam.getEmp_no())
                .or()
                .eq("first_name",queryParam.getFirst_name())
                .or()
                .eq("last_name",queryParam.getLast_name())
                .or()
                .gt("birth_date",queryParam.getBirth_date())
                .or()
                .gt("hire_date",queryParam.getHire_date())
        ;
        IPage<Employees> pages = new Page<>(queryParam.getPageNum(), queryParam.getPageSize());
        return employeeMapper.selectPage(pages,/*null*/queryWrapper);
    }
}
