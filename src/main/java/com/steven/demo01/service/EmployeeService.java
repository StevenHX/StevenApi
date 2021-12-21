package com.steven.demo01.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.steven.demo01.entity.Employees;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    IPage<Employees> pageGetAllEmployees(Employees queryParam);
}
