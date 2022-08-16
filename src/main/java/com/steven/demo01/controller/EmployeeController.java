package com.steven.demo01.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.steven.demo01.entity.BasePageResult;
import com.steven.demo01.entity.CommonResult;
import com.steven.demo01.entity.Employees;
import com.steven.demo01.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "雇员相关接口")
@RequestMapping(value = "/employee")
public class EmployeeController extends BaseController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    @ApiOperation("分页查询雇员的接口")
    public CommonResult<BasePageResult<Employees>> postPageAllEmployees(@RequestBody Employees employee) {
        Long uid = getCurrentUid();
        IPage<Employees> page = employeeService.pageGetAllEmployees(employee);
        return CommonResult.success(BasePageResult.newInstance(page));
    }

}
