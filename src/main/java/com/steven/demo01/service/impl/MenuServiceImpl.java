package com.steven.demo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.steven.demo01.domain.CommonResult;
import com.steven.demo01.domain.entity.SysMenu;
import com.steven.demo01.exception.CustomException;
import com.steven.demo01.mapper.MenuMapper;
import com.steven.demo01.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public SysMenu selectMenuById(Long menuId) {
        return menuMapper.selectMenuById(menuId);
    }

    @Override
    public IPage<SysMenu> selectMenuList(SysMenu sysMenu) {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(sysMenu.getMenuName())) {
            queryWrapper.like("menu_name", sysMenu.getMenuName());
        }
        IPage<SysMenu> page = new Page<>(sysMenu.getPageNum(), sysMenu.getPageSize());
        return menuMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void addMenu(SysMenu sysMenu) {
        SysMenu menu = menuMapper.selectMenuByName(sysMenu.getMenuName());
        if (menu != null) {
            throw new CustomException(CommonResult.error("该菜单已存在", ""));
        }
        menuMapper.insert(sysMenu);
    }

    @Override
    public void editMenu(SysMenu sysMenu) {
        UpdateWrapper<SysMenu> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("menu_id", sysMenu.getMenuId());
        int rows = menuMapper.update(sysMenu, updateWrapper);
        if (rows == 0) throw new CustomException(CommonResult.error("修改失败", ""));
    }

    @Override
    public void deleteMenu(Long menuId) {
        int rows = menuMapper.deleteById(menuId);
        if (rows == 0) throw new CustomException(CommonResult.error("删除失败", ""));
    }
}
