package com.steven.demo01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.steven.demo01.domain.entity.SysDictData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictDataMapper extends BaseMapper<SysDictData> {
    SysDictData selectDataById(Long dictDataId);
    List<SysDictData> selectDictDataByType(String dictType);
}
