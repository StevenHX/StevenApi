package com.steven.demo01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.steven.demo01.domain.entity.SysDictType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictTypeMapper extends BaseMapper<SysDictType> {
    SysDictType selectTypeById(Long dictId);
}
