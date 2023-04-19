package com.steven.demo01.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.steven.demo01.domain.entity.SysMenu;
import com.steven.demo01.domain.model.vo.RouterVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface MenuService {
    SysMenu selectMenuById(Long menuId);

    IPage<SysMenu> selectMenuList(SysMenu sysMenu);

    void addMenu(SysMenu sysMenu);

    void editMenu(SysMenu sysMenu);

    /**
     *
     * @param menuId
     */
    void deleteMenu(Long menuId);

    /**
     * 根据用户ID查询菜单树信息
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenu> selectMenuTreeByUserId(Long userId);

    /**
     * 根据角色ID获取菜单信息
     * @param roleId
     * @return
     */
    List<SysMenu> selectMenuByRoleId(Long roleId);
    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    List<RouterVo> buildMenus(List<SysMenu> menus);
}
