package com.steven.demo01.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.steven.demo01.bean.BasePageResult;
import com.steven.demo01.bean.CommonResult;
import com.steven.demo01.bean.employees;
import com.steven.demo01.dto.EmployeeDto;
import com.steven.demo01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 处理 /user/ GET请求
     * @return
     */
    @GetMapping("/")
    public List<employees> getUserList() {
        System.out.println(userService.getAllUsers());
        return userService.getAllUsers();
    }

    @PostMapping("/")
    public CommonResult<BasePageResult<employees>> postPageAllUses(@RequestBody EmployeeDto employee) {
        IPage<employees> page = userService.pageGetAllUses(employee);
        return CommonResult.success(BasePageResult.newInstance(page));
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "success delete";
    }
}
