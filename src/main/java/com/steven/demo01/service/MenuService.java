package com.steven.demo01.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.steven.demo01.domain.entity.SysMenu;
import org.springframework.stereotype.Service;

@Service
public interface MenuService {
    SysMenu selectMenuById(Long menuId);

    IPage<SysMenu> selectMenuList(SysMenu sysMenu);

    void addMenu(SysMenu sysMenu);

    void editMenu(SysMenu sysMenu);

    void deleteMenu(Long menuId);
}
