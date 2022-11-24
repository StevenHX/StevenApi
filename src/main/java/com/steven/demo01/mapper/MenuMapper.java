package com.steven.demo01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.steven.demo01.domain.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper extends BaseMapper<SysMenu> {

    SysMenu selectMenuById(Long menuId);
    SysMenu selectMenuByName(String menuName);
}
