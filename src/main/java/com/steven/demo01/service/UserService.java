package com.steven.demo01.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.steven.demo01.bean.employees;
import com.steven.demo01.dto.EmployeeDto;

import java.util.List;

public interface UserService {
    List<employees> getAllUsers();

    IPage<employees> pageGetAllUses(EmployeeDto queryParam);
}
