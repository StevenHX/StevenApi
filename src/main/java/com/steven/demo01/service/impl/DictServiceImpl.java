package com.steven.demo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.steven.demo01.domain.CommonResult;
import com.steven.demo01.domain.entity.SysDictData;
import com.steven.demo01.domain.entity.SysDictType;
import com.steven.demo01.exception.CustomException;
import com.steven.demo01.mapper.DictDataMapper;
import com.steven.demo01.mapper.DictTypeMapper;
import com.steven.demo01.service.DictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DictServiceImpl implements DictService {
    @Autowired
    DictTypeMapper dictTypeMapper;
    @Autowired
    DictDataMapper dictDataMapper;

    @Override
    public IPage<SysDictType> pageListType(SysDictType dictType) {
        QueryWrapper<SysDictType> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(dictType.getDictName())) {
            queryWrapper.like("dict_name", dictType.getDictName());
        }
        if (StringUtils.isNotBlank(dictType.getStatus())) {
            queryWrapper.eq("status", dictType.getStatus());
        }
        IPage<SysDictType> page = new Page<>(dictType.getPageNum(), dictType.getPageSize());
        return dictTypeMapper.selectPage(page, queryWrapper);
    }

    @Override
    public SysDictType queryDictTypeById(Long dictId) {
        return dictTypeMapper.selectTypeById(dictId);
    }

    @Override
    public void addDictType(SysDictType dictType) {
       dictTypeMapper.insert(dictType);
    }

    @Override
    public void editDictType(SysDictType dictType) {
        UpdateWrapper<SysDictType> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("dict_id", dictType.getDictId());
        int rows = dictTypeMapper.update(dictType, updateWrapper);
        if (rows == 0) throw new CustomException(CommonResult.error("修改失败", ""));
    }

    @Override
    public void delDitType(Long dictId) {
        UpdateWrapper<SysDictType> delWrapper = new UpdateWrapper<>();
        delWrapper.eq("dict_id", dictId);
        int rows = dictTypeMapper.delete(delWrapper);
        if (rows == 0) throw new CustomException(CommonResult.error("删除失败", ""));
    }

    @Override
    public IPage<SysDictData> pageListData(SysDictData dictData) {
        QueryWrapper<SysDictData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dict_type", dictData.getDictType());
        if (StringUtils.isNotBlank(dictData.getDictLabel())) {
            queryWrapper.eq("dict_label", dictData.getDictLabel());
        }
        if (StringUtils.isNotBlank(dictData.getStatus())) {
            queryWrapper.eq("status", dictData.getStatus());
        }
        IPage<SysDictData> page = new Page<>(dictData.getPageNum(), dictData.getPageSize());
        return dictDataMapper.selectPage(page, queryWrapper);
    }

    @Override
    public SysDictData queryDictDataById(Long dictDataId) {
        return dictDataMapper.selectDataById(dictDataId);
    }

    @Override
    public List<SysDictData> queryDictByDictType(String dictType) {
        return dictDataMapper.selectDictDataByType(dictType);
    }

    @Override
    public void addDictData(SysDictData dictData) {
        dictDataMapper.insert(dictData);
    }

    @Override
    public void editDictData(SysDictData dictData) {
        UpdateWrapper<SysDictData> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("dict_data_id", dictData.getDictDataId());
        int rows = dictDataMapper.update(dictData, updateWrapper);
        if (rows == 0) throw new CustomException(CommonResult.error("修改失败", ""));
    }

    @Override
    public void delDictData(Long dictDataId) {
        UpdateWrapper<SysDictData> delWrapper = new UpdateWrapper<>();
        delWrapper.eq("dict_data_id", dictDataId);
        int rows = dictDataMapper.delete(delWrapper);
        if (rows == 0) throw new CustomException(CommonResult.error("删除失败", ""));
    }
}
