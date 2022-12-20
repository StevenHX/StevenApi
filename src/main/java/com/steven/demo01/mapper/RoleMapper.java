package com.steven.demo01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.steven.demo01.domain.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<SysRole> {

    SysRole selectRoleById(Long roleId);
    SysRole selectRoleByName(String roleName);
    List<SysRole> selectRolesByUserId(Long userId);
}
