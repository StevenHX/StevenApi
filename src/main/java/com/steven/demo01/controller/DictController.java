package com.steven.demo01.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.steven.demo01.domain.BasePageResult;
import com.steven.demo01.domain.CommonResult;
import com.steven.demo01.domain.ValidationGroup;
import com.steven.demo01.domain.entity.SysDictData;
import com.steven.demo01.domain.entity.SysDictType;
import com.steven.demo01.domain.entity.User;
import com.steven.demo01.service.DictService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@Api(tags = "字典相关接口")
@RequestMapping(value = "/dict")
public class DictController extends BaseController {
    @Autowired
    DictService dictService;

    @PostMapping("/pageListType")
    public CommonResult<BasePageResult<SysDictType>> listType(@RequestBody SysDictType dictType) {
        IPage<SysDictType> page = dictService.pageListType(dictType);
        return CommonResult.success(BasePageResult.newInstance(page));
    }

    @PostMapping("/addType")
    public CommonResult<String> addType(@Validated(ValidationGroup.AddGroup.class) @RequestBody SysDictType dictType) {
        User loginUser = getCurrentLoginUser().getUser();
        dictType.setCreateBy(loginUser.getUserName());
        dictType.setCreateTime(new Date());
        dictService.addDictType(dictType);
        return CommonResult.success("新增字典类型成功");
    }

    @GetMapping(value = "/queryDictType/{dictId}")
    public CommonResult<SysDictType> getDictTypeById(@PathVariable Long dictId) {
        SysDictType dictType = dictService.queryDictTypeById(dictId);
        return CommonResult.success(dictType);
    }

    @PostMapping("editType")
    public CommonResult<String> editType(@RequestBody SysDictType dictType) {
        User loginUser = getCurrentLoginUser().getUser();
        dictType.setUpdateBy(loginUser.getUserName());
        dictType.setUpdateTime(new Date());
        dictService.editDictType(dictType);
        return CommonResult.success("修改成功");
    }
    @DeleteMapping("deleteType/{dictId}")
    public CommonResult<String> deleteType(@PathVariable Long dictId) {
       dictService.delDitType(dictId);
        return CommonResult.success("删除成功");
    }

    //===================================================================================================================

    @PostMapping("/pageListData")
    public CommonResult<BasePageResult<SysDictData>> listData(@Validated(ValidationGroup.CustomGroup.class) @RequestBody SysDictData dictData) {
        IPage<SysDictData> page = dictService.pageListData(dictData);
        return CommonResult.success(BasePageResult.newInstance(page));
    }

    @PostMapping("/addData")
    public CommonResult<String> addData(@Validated(ValidationGroup.AddGroup.class) @RequestBody SysDictData dictData) {
        User loginUser = getCurrentLoginUser().getUser();
        dictData.setCreateBy(loginUser.getUserName());
        dictData.setCreateTime(new Date());
        dictService.addDictData(dictData);
        return CommonResult.success("新增字典数据成功");
    }

    @GetMapping(value = "/queryDictData/{dictDataId}")
    public CommonResult<SysDictData> getDictDataById(@PathVariable Long dictDataId) {
        SysDictData dictData = dictService.queryDictDataById(dictDataId);
        return CommonResult.success(dictData);
    }

    @PostMapping("editData")
    public CommonResult<String> editData(@RequestBody SysDictData dictData) {
        User loginUser = getCurrentLoginUser().getUser();
        dictData.setUpdateBy(loginUser.getUserName());
        dictData.setUpdateTime(new Date());
        dictService.editDictData(dictData);
        return CommonResult.success("修改成功");
    }
    @DeleteMapping("deleteData/{dictDataId}")
    public CommonResult<String> deleteData(@PathVariable Long dictDataId) {
        dictService.delDictData(dictDataId);
        return CommonResult.success("删除成功");
    }
}
