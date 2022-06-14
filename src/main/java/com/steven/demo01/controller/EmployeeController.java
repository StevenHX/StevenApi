package com.steven.demo01.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.steven.demo01.entity.BasePageResult;
import com.steven.demo01.entity.CommonResult;
import com.steven.demo01.entity.Employees;
import com.steven.demo01.service.EmployeeService;
import com.steven.demo01.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(tags = "雇员相关接口")
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    @ApiOperation("分页查询雇员的接口")
    public CommonResult<BasePageResult<Employees>> postPageAllEmployees(@RequestHeader("Authorization") String token, @RequestBody Employees employee) {
        log.info("token:{}", JwtUtils.getClaims(token).isPresent() ? JwtUtils.getClaims(token).get().get("uid").asLong() : "");
        IPage<Employees> page = employeeService.pageGetAllEmployees(employee);
        return CommonResult.success(BasePageResult.newInstance(page));
    }

}
