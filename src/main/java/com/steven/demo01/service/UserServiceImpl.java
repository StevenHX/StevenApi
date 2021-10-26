package com.steven.demo01.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.steven.demo01.bean.employees;
import com.steven.demo01.dto.EmployeeDto;
import com.steven.demo01.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Override
    public List<employees> getAllUsers() {
        return userMapper.selectList(new QueryWrapper<employees>().like("birth_date","1965-01-31"));
    }

    @Override
    public IPage<employees> pageGetAllUses(EmployeeDto queryParam) {
        IPage<employees> pages = new Page<>(queryParam.getPageNum(), queryParam.getPageSize());
        return userMapper.selectPage(pages, null);
    }
}
